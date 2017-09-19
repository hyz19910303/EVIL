package com.hyz.service.content.impl;

import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hyz.dao.hibernate.contentdao.ContentDao;
import com.hyz.pojo.Content;
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
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public String saveContent(Content content) {
		content.setContent_id(UUID.randomUUID().toString().replaceAll("-", ""));
		content.setCreateTime(DateFormatUtils.format(new Date(), "yyyyMMddHHmmss"));
		int saveContent = contentdao.saveContent(content);
		return saveContent==1?"success":"fail";
	}

}
