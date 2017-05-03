package com.unuse.util;

import org.apache.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Create by Unuse on 2016-10-28
 */
public class MD5Encryption {

	private static Logger logger = Logger.getLogger(MD5Encryption.class);

	public static String toEncryption(String origin) {
		
		if (null == origin) {
			return null;
		}
		
		return toEncryption(origin.getBytes());
	}
	
	public static String toEncryption(byte[] origin) {
		StringBuilder result = new StringBuilder();
		
		try {
			// 指定加密算法
			MessageDigest md = MessageDigest.getInstance("MD5");
			
			// 进行加密运算
			byte[] bytes = md.digest(origin);
			for (int i = 0; i < bytes.length; i++) {
				// 将整数字符串转化为十六进制，这里与0xff进行与运算的原因是保证转换结果为32位
				String str = Integer.toHexString(bytes[i] & 0xFF);
				if (1 == str.length()) {
					str += "F";
				}
				
				result.append(str);
			}
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			logger.error("===============>  加密失败!", e);
		}
		
		return result.toString();
	}
	
}
