package com.hyz.dao.Mybatis.roleDao;

import java.util.List;

import com.hyz.pojo.RoleDO;

public interface RoleDao {
	/**
	 * 根据用户id获取角色
	 * @param userid
	 * @return
	 */
	List<RoleDO> findByUserId(String userid);
	

}
