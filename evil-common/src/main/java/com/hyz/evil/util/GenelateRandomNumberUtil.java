package com.hyz.evil.util;

/**
 * 生成随机数字，可以作为盐
 * @author EVIL
 *
 */
public final class GenelateRandomNumberUtil {
	
	private GenelateRandomNumberUtil(){};
	
	private static final String moduleString ="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	 
	public static String genalateRandomString(int digit) throws Exception{
		int length = moduleString.length();
		if(digit==0){
			throw new Exception("生成随机数位数大于等于1");
		}
		StringBuilder build = new StringBuilder();
		for(int i=0;i<digit;i++){
			int random=(int)(Math.random()*100);
			if(random>length){
				random=random%length;
			}
			build.append(moduleString.toCharArray()[random]);
		}
		return build.toString();
	}
	
	public static String genalateRandomNumber(int digit) throws Exception{
		if(digit==0){
			throw new Exception("生成随机数位数大于等于1");
		}
		StringBuilder build = new StringBuilder();
		for(int i=0;i<digit;i++){
			int random=(int)(Math.random()*10);
			build.append(random);
		}
		return build.toString();
	}
	public static void main(String[] args) {
		String genalateRandomNumber="";
		try {
			genalateRandomNumber = genalateRandomString(3);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(genalateRandomNumber);
	}
}
