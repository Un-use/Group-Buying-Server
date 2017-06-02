package com.unuse.util;

import org.apache.commons.lang.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Random;
import java.util.UUID;

/**
 * Created by Unuse on 2017年2月6日
 */

public class StringUtil extends StringUtils {
	
	private static final String ENC_UTF_8 = "UTF-8";

	/**
	 * URL Encode
	 * @param url
	 * @param enc 字符编码
	 * @return 编码后的URL
	 */
	public static String urlEncode(String url, String enc) {
		if (null == enc) {
			enc = ENC_UTF_8;
		}

		try {
			return URLEncoder.encode(url, enc);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	/**
	 * URL Decode
	 * @param url
	 * @param enc 字符编码
	 * @return 解码后的URL
	 */
	public static String urlDecode(String url, String enc) {
		if (null == enc) {
			enc = ENC_UTF_8;
		}

		try {
			return URLDecoder.decode(url, enc);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * @return 获取随机的UURL
	 */
	public static String getUUIDString() {
		UUID uuid = UUID.randomUUID();

		return uuid.toString().replaceAll("-", "");
	}

    /**
     * 判断字符串是不是数字
     * @param str
     * @return 是否为数字
     */
	public static boolean isNumber(String str) {
		if (StringUtils.isBlank(str)) {
			return false;
		}

		return str.trim().matches("^[0-9]*$");
	}

    /**
     * 生成6字密码
     * @return 6字密码
     */
    public static String createSecretKey6(){
        Random random = new Random();

        String[] chars = new String[] { "a" , "b" , "c" , "d" , "e" , "f" , "g" , "h" ,
                "i" , "j" , "k" , "l" , "m" , "n" , "o" , "p" , "q" , "r" , "s" , "t" ,
                "u" , "v" , "w" , "x" , "y" , "z" , "0" , "1" , "2" , "3" , "4" , "5" ,
                "6" , "7" , "8" , "9" , "A" , "B" , "C" , "D" , "E" , "F" , "G" , "H" ,
                "I" , "J" , "K" , "L" , "M" , "N" , "O" , "P" , "Q" , "R" , "S" , "T" ,
                "U" , "V" , "W" , "X" , "Y" , "Z"
        };

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 6; i++) {
            sb.append(chars[random.nextInt(62)]);
        }

        return sb.toString();
    }

	/**
	 * 生成6字Code
	 * @return 6字Code
	 */
	public static String createCode(){
		Random random = new Random();

		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 6; i++) {
			sb.append(random.nextInt(10));
		}

		return sb.toString();
	}

	/**
	 * 生成图片前缀URL
	 * @return 图片前缀URL
	 */
	public static String makePicturePreUrl(String mainPath, String secret, String id, String subPath) {
		StringBuilder pictureUrl = new StringBuilder(mainPath);

		if (null != secret) {
			pictureUrl.append("/");
			pictureUrl.append(secret);
		}

		if (null != id) {
			pictureUrl.append("_");
			pictureUrl.append(id);
			pictureUrl.append("/");
		}

		if (null != subPath) {
			pictureUrl.append("/");
			pictureUrl.append(subPath);
			pictureUrl.append("/");
		}

		return pictureUrl.toString();
	}
}
