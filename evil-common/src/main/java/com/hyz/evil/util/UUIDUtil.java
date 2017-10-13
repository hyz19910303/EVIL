package com.hyz.evil.util;

import java.util.UUID;

/**
*Create at 2017年10月13日 上午9:19:09
*
*@autuor EVIL
*
*@version 1.0
*
*ProjectName evil-common
*
*Description: 
*        
*/
public class UUIDUtil {
	private UUIDUtil() {};
	
	public static String randonUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
