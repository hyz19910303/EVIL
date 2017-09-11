package com.hyz.dao.hibernate.userdao.userdoimpl;

import java.io.Serializable;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hyz.dao.hibernate.userdao.UserDao;
import com.hyz.pojo.UserDO;
@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private SessionFactory sessionfactory;
	
	@Override
	public UserDO getCurrentUser(String userID) {
		String sql="select t.* from tbl_user t where t.accountNo=? and t.lockStatus='0'";
		SQLQuery createQuery = sessionfactory.getCurrentSession().createSQLQuery(sql).addEntity(UserDO.class);
		createQuery.setString(0, userID);
		UserDO userdo=(UserDO) createQuery.uniqueResult();
		return userdo;
	}

	@Override
	public int insertByselective(UserDO userdo) {
		String sql="insert into tbl_user(userId,accountNo,password,salt) values(?,?,?,?)";
		SQLQuery createSQLQuery = sessionfactory.getCurrentSession().createSQLQuery(sql);
		//sessionfactory.getCurrentSession().save(userdo);
		createSQLQuery.setString(0, userdo.getUserId());
		createSQLQuery.setString(1, userdo.getAccountNo());
		createSQLQuery.setString(2, userdo.getPassword());
		createSQLQuery.setString(3, userdo.getSalt());
		createSQLQuery.executeUpdate();
		return 0;
	}

}
