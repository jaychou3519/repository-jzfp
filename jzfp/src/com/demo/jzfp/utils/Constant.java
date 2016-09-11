package com.demo.jzfp.utils;

import java.util.ArrayList;
import java.util.List;

import com.demo.jzfp.entity.Member;
import com.demo.jzfp.entity.PoorMessage;


public class Constant {
	
	public static PoorMessage poor = new PoorMessage();
	public static List<Member> members = new ArrayList<Member>();
	
	/**
	 * 基础url
	 * */
	public static String baseUrl = "http://mmj.zksr.cn/gold/app/salesman_app!";
	public static String baseUrl_use = "http://mmj.zksr.cn/gold/app/app!";
	public static String imageUrl="http://mmj.zksr.cn/gold";
	
	//webservice 
	public static String NAME_SPACE = "http://webservice.item.hihsoft.com/";
	public static String SERVER_URL = "http://115.28.14.1:9145/ylppbd/services/ylppbdWS/";
	


	public static String weather="http://api.map.baidu.com/telematics/v3/weather?location=炎陵县&output=json&ak=9GC5VwC4G9UkbyVi9HDFjgyynxiTfnxG&mcode=79:C3:04:C7:71:1E:A4:BA:BB:24:43:94:61:2A:78:B9:68:68:6E:22;com.demo.jzfp";
}
