package com.demo.jzfp.utils;

import java.io.InputStream;
import java.util.Map;
import java.util.Random;
import org.xmlpull.v1.XmlPullParser;
import android.util.Xml;

/**
 * File Name: StringUtil.java <br>
 * 
 * Description:  <br>
 * 
 * @version 1.0 
 */

public class StringUtil {
	/**
	 * 
	 * Description: 
	 * 分割字符串
	 * @param str
	 * @param regularExpression
	 * @return
	 * <br><br>
	 * 
	 * Version: 1.0 <br>
	 * 
	 * ***************Modify History*************** <br>
	 * 
	 * Modify Date: <br>
	 * 
	 * Modify By: <br>
	 * 
	 * Modify Description: <br>
	 * 
	 * Latest Version: <br>
	 * 
	 * ******************************************** <br>
	 */
	public static String[] split(String str,String regularExpression) {
		return str.split(regularExpression);
	}
	
	/**
	 * 
	 * Description: 
	 * null 转换 为 ""
	 * @param str
	 * @return
	 * <br><br>
	 * 
	 * Version: 1.0 <br>
	 * 
	 * ***************Modify History*************** <br>
	 * 
	 * Modify Date: <br>
	 * 
	 * Modify By: <br>
	 * 
	 * Modify Description: <br>
	 * 
	 * Latest Version: <br>
	 * 
	 * ******************************************** <br>
	 */
	public static String deNull(String str) {
		if (str == null) {
			return "";
		}
		return str;
	}
	
	/**
	 * 
	 * Description: 
	 * 对象转换为字符串
	 * @param obj
	 * @return
	 * <br><br>
	 * 
	 * Version: 1.0 <br>
	 * 
	 * ***************Modify History*************** <br>
	 * 
	 * Modify Date: <br>
	 * 
	 * Modify By: <br>
	 * 
	 * Modify Description: <br>
	 * 
	 * Latest Version: <br>
	 * 
	 * ******************************************** <br>
	 */
	public static String Object2String(Object obj) {
		if (obj == null) {
			return "";
		}
		return obj.toString();
	}
	
	
	public static String getUpdataInfo(InputStream is) throws Exception {
		String versionInfo = null;
		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(is, "utf-8");// 设置解析的数据源
		int type = parser.getEventType();
		while (type != XmlPullParser.END_DOCUMENT) {
			switch (type) {
			case XmlPullParser.START_TAG:
				if ("version".equals(parser.getName())) {
					versionInfo = parser.nextText(); //获取版本号
				}
				break;
			}
			type = parser.next();
		}
		return versionInfo;
	}
	
	public static boolean isEmpty(String str) {
		if ((str == null) || (str.trim().length() == 0)) {
			return true;
		}
		return false;
	}
	
	public static String removeOrgLDLast(String orgLD){
		try {
			if(!"-1".equals(orgLD)&&!"".equals(orgLD)){
				return orgLD.substring(0, orgLD.length()-1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static String GetRandomString(int Len) {
		String[] baseString = { "0", "1", "2", "3", "4", "5", "6", "7", "8",
				"9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
				"l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w",
				"x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I",
				"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
				"V", "W", "X", "Y", "Z" };
		Random random = new Random();
		int length = baseString.length;
		String randomString = "";
		for (int i = 0; i < length; i++) {
			randomString += baseString[random.nextInt(length)];
		}
		random = new Random(System.currentTimeMillis());
		String resultStr = "";
		for (int i = 0; i < Len; i++) {
			resultStr += randomString.charAt(random.nextInt(randomString
					.length() - 1));
		}
		return resultStr;
	}
}
