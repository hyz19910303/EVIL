package com.hyz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hyz.pojo.Content;
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
		
	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/save")
	public String saveContent(Content c) {
		return contentService.saveContent(c);
	}
}
