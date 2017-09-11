package com.hyz.service.roleservice.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyz.dao.Mybatis.roleDao.RoleDao;
import com.hyz.evil.util.ObjectConvertJsonUtil;
import com.hyz.pojo.RoleDO;
import com.hyz.service.roleservice.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleDao roledao;

	@Override
	public String findRolesInfoByUserId(String userId) {
		List<RoleDO> roles = roledao.findByUserId(userId);
		String jsonStr = ObjectConvertJsonUtil.ListConvertJson(roles);
		return jsonStr;
	}
	
	
	
}
