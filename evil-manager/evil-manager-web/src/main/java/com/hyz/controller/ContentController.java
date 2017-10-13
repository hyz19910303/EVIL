package com.hyz.controller;


import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.beust.jcommander.internal.Maps;
import com.hyz.dao.hibernate.userdao.UserDao;
import com.hyz.pojo.Category;
import com.hyz.pojo.Content;
import com.hyz.pojo.UserDO;
import com.hyz.service.content.ContentService;

/**
*Create at 2017年9月19日 下午12:38:15
*
*@autuor EVIL
*
*@version 1.0
*
*ProjectName evil-manager-web
*
*Description: 
*        
*/
@Controller
@RequestMapping("/contentManager")
public class ContentController {
	
	private Logger log=LoggerFactory.getLogger(ContentController.class);
	
	@Autowired
	private ContentService contentService;
	
	@RequestMapping(value= {"/Save","/Release"},method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public Map<String,String> saveContent(Category category,Content c,HttpSession session) {
		Map<String,String> resultMap=Maps.newHashMap();
		if(StringUtils.isBlank(c.getContent())) {
			resultMap.put("resultInfo", "内容不能为空");
		}
		UserDO user=(UserDO) session.getAttribute("user");
		try {
			;
			category.setUser_id(user.getUserId());
			String saveContent = contentService.saveContent(category,c);
			resultMap.put("resultInfo", saveContent);
		} catch (Exception e) {
			resultMap.put("resultInfo", "fail");
		}
		return resultMap;
	}
	
	
	@RequestMapping("/saveCategory")
	@ResponseBody
	public Map<String,String> ReleaseContent(Category category,HttpSession session) {
		UserDO user=(UserDO) session.getAttribute("user");
		
		try {
			category.setUser_id(user.getUserId());
			String res=contentService.saveCategory(category);
			return Maps.newHashMap("resultInfo",res);
		} catch (Exception e) {
			log.error(e.toString());
		}
		return Maps.newHashMap("resultInfo","fail"); 
	}
}
