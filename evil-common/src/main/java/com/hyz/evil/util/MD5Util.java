package com.hyz.evil.util;

import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {

	private MD5Util(){};
	public static void main(String[] args) {
		String md5deciphering;
		try {
			md5deciphering = MD5encrypt("dasdas11");
			System.out.println(md5deciphering);
			md5deciphering = MD5encrypt("admin123");
			System.out.println(md5deciphering);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * MD5加密
	 * @param pwd 要MD5加密的内容
	 * @return MD5加密后的内容
	 * @throws NoSuchAlgorithmException 
	 */
	public static String MD5encrypt(String pwd) throws NoSuchAlgorithmException {
		String md5Hex = DigestUtils.md5Hex(pwd);
		return md5Hex;
		
	}
	/**
	 * 
	 * @param pwd  MD5加密过得密文
	 * @return   
	 */
	public static String MD5deciphering(String pwd){
		
		return null;
	}
}
