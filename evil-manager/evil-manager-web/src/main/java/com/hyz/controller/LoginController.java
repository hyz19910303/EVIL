package com.hyz.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ConcurrentAccessException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.hyz.evil.util.GenelateRandomNumberUtil;
import com.hyz.pojo.UserDO;
import com.hyz.service.roleservice.RoleService;
import com.hyz.service.userservice.UserService;
@Controller()
@RequestMapping("/Sys")
public class LoginController {
	
	public static  final Logger log=LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private CacheManager cachemanager;
	
	//private Cache<String, AtomicInteger> shiroCacheManager;
	
	@RequestMapping("/index")
	public String RedirectToWelcomePage(){
		return "login/index";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if(StringUtils.isNotBlank(username)&& StringUtils.isNotBlank(password)) {
			request.setAttribute("username", username);
			request.setAttribute("password", password);
		}
		return "login/index";
	}
	
	/**
	 * 
	 * <p>MethodName: login</p>
	 * <p>Description: </p>
	 * @param request
	 * @param response
	 * @return
	 * 
	 * @author EVIL
	 * @date 2017年9月7日
	 * @version 1.0
	 * Create At 2017年9月7日 下午12:45:46
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public Object login(HttpServletRequest request,HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		Map<String,String> errMap= new HashMap<>();
		String verifyCode=request.getParameter("verifyCode");
		String attribute = (String) request.getSession().getAttribute("verifyCode");
		if(verifyCode !=null && !verifyCode.equals(attribute)) {
			errMap.put("login_err_msg", "验证码不正确");
			return errMap;
		}
		String userName=request.getParameter("username");
		String password=request.getParameter("password");
		String remeberme=request.getParameter("remeberMe");
		String remoteHost = request.getRemoteHost();
		boolean remeberFlag=false;
		if(remeberme!=null && !"".equals(remeberme)){
			remeberFlag=true;
		}
		UsernamePasswordToken token = null;
		Subject subject = null;
		AtomicInteger  atomicinteger=null;
		try {
			//缓存
			Cache<String, AtomicInteger> shiroCacheManager = cachemanager.getCache("passwordRetryCache");
			atomicinteger= shiroCacheManager.get(userName);
			if (atomicinteger == null) {  
				atomicinteger = new AtomicInteger(0);  
				shiroCacheManager.put(userName, atomicinteger);  
	        }  
			token = new UsernamePasswordToken(userName,password,remeberFlag,remoteHost);
			subject = SecurityUtils.getSubject();
			//尝试次数过多
			if(atomicinteger.incrementAndGet()>5){
				//进行账号锁定1小时.
				//TO-DO 
				throw new ExcessiveAttemptsException();
			}
			subject.login(token);
			
		}catch(UnknownAccountException e){//未知账户
			errMap.put("login_err_msg", "用户名或者密码错误,登入失败,总共可以尝试5次,还剩下"+(5-atomicinteger.get())+"次");
		}catch(LockedAccountException e){//账号锁定
			errMap.put("login_err_msg", "账号已经锁定,登入失败,总共可以尝试5次,还剩下"+(5-atomicinteger.get())+"次");
		}catch(ExcessiveAttemptsException e){//尝试次数过多
			errMap.put("login_err_msg", "尝试次数过多");
		}catch(IncorrectCredentialsException e){//错误的凭证
			errMap.put("login_err_msg", "用户名或者密码错误,登入失败,总共可以尝试5次,还剩下"+(5-atomicinteger.get())+"次");
		}catch(ConcurrentAccessException e){
			errMap.put("login_err_msg", "用户已经登入");
		}catch (AuthenticationException e) {
			errMap.put("login_err_msg", "用户名或者密码错误,登入失败,总共可以尝试5次,还剩下"+(5-atomicinteger.get())+"次");
		}
		//验证是否合法
		if(subject.isAuthenticated()){
			//将登入次数设置为0
			atomicinteger.set(0);
			//一个账户同一个时刻只能一个地方登入
			DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
			DefaultWebSessionManager sessionManager = (DefaultWebSessionManager)securityManager.getSessionManager();
			Collection<Session> activeSessions = sessionManager.getSessionDAO().getActiveSessions();
			for (Session session : activeSessions) {
				UserDO udo=(UserDO) session.getAttribute("user");
				if(udo==null) continue;
				if(userName.equals(udo.getAccountNo())){
					//其他登入的地址下线
					sessionManager.getSessionDAO().delete(session); 
				}
			}
			try {
				UserDO userDo = userService.login(userName);
				Session session = subject.getSession();
				session.setAttribute("user", userDo);
				errMap.put("login_err_msg", null);
			} catch (Exception e) {
				e.printStackTrace();
			}
//			return ":/Sys/success.do";
		}else{
			token.clear();
		}
		//return "redirect:/index.jsp";
		return JSON.toJSONString(errMap);
	}
	
	@RequestMapping("/success")
	public String welcome(){
		return "system/login/welcome";
	}
	@RequestMapping("/register")
	@ResponseBody
	public String register(HttpServletRequest request,HttpServletResponse response){
		String userName=request.getParameter("username");
		String password=request.getParameter("password");
		if(StringUtils.isNotBlank(userName)||StringUtils.isNotBlank(password)){
			return "注册失败";
		}
		UserDO  userdo = new UserDO();
		try {
			String salt=GenelateRandomNumberUtil.genalateRandomString(3);
			userdo.setSalt(salt);
			SimpleHash simpleHash = new SimpleHash("MD5", password, salt, 1);  
			userdo.setAccountNo(userName);
			userdo.setPassword(simpleHash.toString());
			int flag=userService.registerNewUser(userdo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "login/welcome";
	}
	
	@RequestMapping("/logout.do")
	public String logout(){
		SecurityUtils.getSubject().logout();
		return "redirect:/index.jsp";
	}
	
	@RequestMapping("/welcome.html")
	public String welcomeIndex() {
		return "Content/admin";
	}
	
}
