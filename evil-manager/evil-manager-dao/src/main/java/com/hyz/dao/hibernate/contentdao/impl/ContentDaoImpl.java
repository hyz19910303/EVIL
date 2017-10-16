package com.hyz.dao.hibernate.contentdao.impl;

import java.io.Serializable;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hyz.dao.hibernate.contentdao.ContentDao;
import com.hyz.pojo.Category;
import com.hyz.pojo.Content;

/**
*Create at 2017年9月19日 下午12:48:36
*
*@autuor EVIL
*
*@version 1.0
*
*ProjectName evil-manager-dao
*
*Description: 
*        
*/
@Repository
public class ContentDaoImpl implements ContentDao {
	
	@Autowired
	private SessionFactory sessionfactory;
	
	@Override
	public int saveContent(Content content) {
		sessionfactory.getCurrentSession().save(content);
		return 1;
	}

	@Override
	public int saveCategory(Category category) {
		sessionfactory.getCurrentSession().saveOrUpdate(category);
		return 1;
	}

	@Override
	public int editCategory(Category category) {
		return saveCategory(category);
	}

	@Override
	public int deleteCategory(Category category) {
		sessionfactory.getCurrentSession().update(category);
		return 1;
	}

}
