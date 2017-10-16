package com.hyz.service.content.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.hyz.dao.hibernate.contentdao.ContentDao;
import com.hyz.evil.util.UUIDUtil;
import com.hyz.pojo.Category;
import com.hyz.pojo.Content;
import com.hyz.service.Exception.ParamErrorException;
import com.hyz.service.content.ContentService;

/**
*Create at 2017年9月19日 下午12:34:26
*
*@autuor EVIL
*
*@version 1.0
*
*ProjectName evil-manager-service
*
*Description: 
*        
*/
@Service
public class ContentServiceImpl implements ContentService {
	
	@Autowired
	private ContentDao contentdao;
	
	@Autowired
	private com.hyz.dao.Mybatis.ContentDao.ContentDao mybatisContentDao;
	
	@Override
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
	public String saveContent(Category category,Content content) {
		String categoryid=UUIDUtil.randonUUID();
		category.setCategory_id(categoryid);
		int effec=contentdao.saveCategory(category);
		content.setCategory_id(categoryid);
		content.setContent_id(UUIDUtil.randonUUID());
		content.setCreateTime(DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
		int saveContent = contentdao.saveContent(content);
		return saveContent+effec==2?"success":"fail";
	}


	@Override
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
	public String operateCategory(Category category,String param) throws ParamErrorException {
		int effec=0;
		if("save".equals(param)) {
			category.setCategory_id(UUIDUtil.randonUUID());
			category.setFlag("1");
			effec=contentdao.saveCategory(category);
		}else if ("edit".equals(param)) {
			if(category.getCategory_id()==null) {
				throw new ParamErrorException("数据格式不正确");
			}
			category.setFlag("1");
			effec=contentdao.editCategory(category);
		}else if("delete".equals(param)) {
			if(category.getCategory_id()==null) {
				throw new ParamErrorException("数据格式不正确");
			}
			category.setFlag("0");
			effec=contentdao.deleteCategory(category);
		}
		return effec==1?"success":"fail";
	}


	@Override
	public String getCategory(String userId) {
		List<Category> categoryList = mybatisContentDao.getCategoryList(userId);
		return JSON.toJSONString(categoryList).toString();
	}

}
