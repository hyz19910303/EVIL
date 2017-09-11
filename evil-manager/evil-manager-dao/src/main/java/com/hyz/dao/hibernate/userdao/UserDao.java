package com.hyz.dao.hibernate.userdao;

import com.hyz.pojo.UserDO;


public interface UserDao {
	/**
	 * 根据用户id获取当前用户的信息
	 * @param userID
	 * @return 用户对象
	 */
	UserDO getCurrentUser(String userID);
	/**
	 * 新增一条用户信息
	 * @return 影响条数
	 */
	int insertByselective(UserDO userdo);
}
