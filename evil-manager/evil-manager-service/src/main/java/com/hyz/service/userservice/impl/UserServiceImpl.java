package com.hyz.service.userservice.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyz.dao.hibernate.userdao.UserDao;
import com.hyz.pojo.UserDO;
import com.hyz.service.userservice.UserService;
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userdao;
	
	
	@Override
//	@Transactional
	public UserDO login(String userName) throws Exception {
		UserDO user = new UserDO();
		UserDO currentUser = userdao.getCurrentUser(userName);
		return currentUser;	
	}

	@Override
	public UserDO findUserInfoByAccount(String userName) {
		UserDO currentUser = userdao.getCurrentUser(userName);
		return currentUser;	
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public int registerNewUser(UserDO userdo) {
		userdo.setUserId(UUID.randomUUID().toString().replaceAll("-", ""));
		return userdao.insertByselective(userdo);
	}

}
