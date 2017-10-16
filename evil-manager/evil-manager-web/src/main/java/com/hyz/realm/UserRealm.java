package com.hyz.realm;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.beust.jcommander.internal.Maps;
import com.hyz.evil.util.MD5Util;
import com.hyz.exception.IPForbitException;
import com.hyz.pojo.PermissionDO;
import com.hyz.pojo.UserDO;
import com.hyz.service.permissionservice.PermissionService;
import com.hyz.service.userservice.UserService;


public class UserRealm extends AuthorizingRealm{
	
	
	@Autowired
	private UserService userServuice;
	
	@Autowired
	private PermissionService permissionService;
	
	
	private Cache<String, AtomicInteger> shiroCacheManager;
	
	private CredentialsMatcher credentialsMatcher;//=super.getCredentialsMatcher();
	
	
	public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
		this.credentialsMatcher = credentialsMatcher;
	}
	@Override
	public CredentialsMatcher getCredentialsMatcher() {
		return credentialsMatcher;
	}



	public void setShiroCacheManager(CacheManager CacheManager) {
		this.shiroCacheManager = CacheManager.getCache("passwordRetryCache");
	}
	

	//授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//将getPrimaryPrincipal方法返回值转为真实身份类型（在下边的doGetAuthenticationInfo认证通过填充到SimpleAuthenticationInfo中身份类型），
		UserDO user = (UserDO)principals.getPrimaryPrincipal();
		List<PermissionDO> permissionList=permissionService.getPermissionListByUserId(user.getUserId());
		AuthorizationInfo authzation = new SimpleAuthorizationInfo();
		System.out.println("!!!!");
		return authzation;
	}
	
	//验证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
		
		//将token 转换成所需的类型
		UsernamePasswordToken userToken =(UsernamePasswordToken)token;
		//账号
		String useraccount=(String)userToken.getPrincipal();
		//用户
		String currentUsername = userToken.getUsername();
		//ip地址
		String host = userToken.getHost();
		//证明
		Object credentials = userToken.getCredentials();
		//从数据库查询出的信息
		UserDO user = userServuice.findUserInfoByAccount(currentUsername);
		if(user==null){
			throw new UnknownAccountException();
		}
		//账户锁定
		if("1".equals(user.getLockStatus())){
			throw new LockedAccountException();
		}
		if(user.getAllowIps()!=null){
			String[] ips = user.getAllowIps().split(",");
			if(Arrays.asList(ips).contains(host)){
				throw new IPForbitException();
			}
		}
		//设置登入的地址
		user.setAddress(host);
		String account=user.getAccountNo();
		String password=user.getPassword();
		ByteSource Salt=ByteSource.Util.bytes(user.getSalt());
		SimpleAuthenticationInfo authenticationInfo= new SimpleAuthenticationInfo(account,password,Salt,getName());
		boolean CredentialsMatch = credentialsMatcher.doCredentialsMatch(token,authenticationInfo);
		//用户名密码正确
		if(CredentialsMatch) {
			SecurityUtils.getSubject().getSession().setAttribute("user", user);
			Map<String, String> newHashMap = Maps.newHashMap();
			newHashMap.put("userId", user.getUserId());
			newHashMap.put("accountNo", user.getAccountNo());
			newHashMap.put("nickName", user.getNickName());
			newHashMap.put("realName", user.getRealName());
			SecurityUtils.getSubject().getSession().setAttribute("UserJson", JSON.toJSON(newHashMap));
			return authenticationInfo;
		}else {
			throw new IncorrectCredentialsException();
		}
		
	}
	
	public static void main(String[] args) {
		int hashIterations = 1;//加密的次数  
        Object salt = "123";//盐值  
        Object credentials = "admin";//密码  
        String hashAlgorithmName = "MD5";//加密方式  
        Object simpleHash = new SimpleHash(hashAlgorithmName, credentials,  
                salt, hashIterations);  
        System.out.println("加密后的值-->" + simpleHash.toString()); 
        String md5encrypt = new Md5Hash(credentials,salt,1).toString();
        System.out.println(md5encrypt);
        try {
			System.out.println(MD5Util.MD5encrypt("123"));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

}
