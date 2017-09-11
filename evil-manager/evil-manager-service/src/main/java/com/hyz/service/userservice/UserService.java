package com.hyz.service.userservice;

import com.hyz.pojo.UserDO;


public interface UserService {
	/**
	 * 登入方法
	 * @return 
	 */
	UserDO login(String userName) throws Exception;
	
	/**
	 * 根据用户名查询
	 * @param userName 用户名
	 * @throws Exception
	 */
	UserDO  findUserInfoByAccount(String userName) ;
	/**
	 * 注册新用户
	 * @param userdo
	 * @return
	 */
	int registerNewUser(UserDO userdo);
}
