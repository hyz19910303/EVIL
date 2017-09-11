package com.hyz.evil.util;

import java.util.List;

import com.alibaba.fastjson.JSON;

public class ObjectConvertJsonUtil {
	
	private ObjectConvertJsonUtil(){};
	
	public static  String ListConvertJson(List list){
		String jsonString = JSON.toJSONString(list);
		int count=list.size();
		return "{"+"count:"+count+",roWs:"+jsonString+"}";
	};
	
	public static String ObjectConvertJson(Object obj){
		return JSON.toJSONString(obj);
	}
}
