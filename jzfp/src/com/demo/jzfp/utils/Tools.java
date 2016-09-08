package com.demo.jzfp.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.demo.jzfp.activity.LoginActivity;
import com.google.gson.Gson;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Tools {
	
	protected static Toast toast = null;
	private static long oneTime = 0;
	private static long twoTime = 0;
	private static String oldMess;

	public static void showNewToast(Context context, String mess) {
		if (toast == null) {
			toast = Toast.makeText(context, mess, Toast.LENGTH_SHORT);
			toast.show();
			oneTime = System.currentTimeMillis();
		} else {
			twoTime = System.currentTimeMillis();
			if (mess.equals(oldMess)) {
				if (twoTime - oneTime > Toast.LENGTH_SHORT) {
					toast.show();
				}
			} else {
				oldMess = mess;
				toast.setText(mess);
				toast.show();
			}
		}
		oneTime = twoTime;
	}
	
	
	/**
	 *Gson 单例 
	 */
	private static Gson gson=null;
	
	private static Gson getGson(){
		if(gson==null){
			gson=new Gson();
		}
		return gson;
	}
	public static Gson GG(){
		return getGson();
	}
	
	/*
	 * fragment 跳转
	 */
	public static void setOpenActivity(Context context, Class<?> pclass) {
		Intent intent = new Intent(context, pclass);
		context.startActivity(intent);
	}
	
	/**
	 * fragment 跳转
	 */
	public static void setOpenActivityBundle(Context context, Class<?> pclass,Bundle bundle) {
		Intent intent = new Intent(context, pclass);
		if(bundle!=null){
			intent.putExtras(bundle);
		}
		context.startActivity(intent);
	}
	/**
	 * info日志
	 * 
	 * @param tag
	 * @param message
	 */
	public static void i(String tag, String message) {
		Log.i(tag, message);
	}

	
	/**
	 * 将时间戳转换成日期 和时间 "yyyy-MM-dd HH:mm:ss.SSS"
	 * 
	 * @param mill
	 *            时间戳 long
	 * @return String "yyyy-MM-dd HH:mm:ss"
	 */
	public static String getDate(long mill) {
		Date date = new Date(mill);
		String strs = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			strs = sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strs;
	}

	/**
	 * 将时间戳转换成日期 "yyyy-MM-dd"
	 * 
	 * @param mill
	 *            时间戳 long
	 * @return String "yyyy-MM-dd"
	 */
	public static String getDateNotTime(long mill) {
		Date date = new Date(mill);
		String strs = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			strs = sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strs;
	}

	/**
	 * 将字符串转为时间戳
	 * 
	 * @param user_time
	 *            格式 yyyy-MM-dd HH:mm:ss.SSS
	 * @return String 时间戳
	 */
	public static String getTime(String user_time) {
		String re_time = null;
		// 2016-01-15 10:52:07.683
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date d;
		try {
			d = sdf.parse(user_time);
			long l = d.getTime();
			String str = String.valueOf(l);
			re_time = str.substring(0, 13);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return re_time;
	}

	/**
	 * 将字符串转为时间戳
	 * 
	 * @param user_time
	 *            格式 yyyy-MM-dd HH:mm:ss
	 * @return String 时间戳
	 */
	public static long getTimestamp(String date) {
		// 2016-01-15 10:52:07.683
		if (date == null) {
			date = "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d;
		long l = 0;
		try {
			d = sdf.parse(date);
			l = d.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
		}
		return l;
	}

	/**
	 * md5加密
	 * 
	 * @param str
	 *            需要转换的 字符串
	 * @return String md5秘文
	 */
	public static String md5(String str) {
		byte[] hash;
		try {
			hash = MessageDigest.getInstance("MD5").digest(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Huh, MD5 should be supported?", e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Huh, UTF-8 should be supported?", e);
		}

		StringBuilder hex = new StringBuilder(hash.length * 2);
		for (byte b : hash) {
			if ((b & 0xFF) < 0x10)
				hex.append("0");
			hex.append(Integer.toHexString(b & 0xFF));
		}
		return hex.toString();
	}


	/**
	 * 获取设备屏幕宽高
	 * 
	 * @param context
	 * @return Point
	 */
	@SuppressLint("NewApi")
	public static Point getPoint(Context context) {
		Point point = new Point();
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		wm.getDefaultDisplay().getSize(point);
		return point;
	}


	/**
	 * 判断网络链接是否可用
	 * 
	 * @param context
	 * @return boolean true:网络链接可用 false:网络链接不可用
	 */
	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (cm == null) {
		} else {
			// 如果仅仅是用来判断网络连接
			// 则可以使用 cm.getActiveNetworkInfo().isAvailable();
			NetworkInfo[] info = cm.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}
		return false;
	}


	/**
	 * 设定listview布局宽高
	 */
	public static void setListViewHeight5(ListView listview) {
		ListAdapter listAdapter = listview.getAdapter();
		if (listAdapter == null) {
			return;
		}
		int totalHeight = 0;
		int count = 0;
		if (listAdapter.getCount() >= 5) {
			count = 5;
		} else {
			count = listAdapter.getCount();
		}
		for (int i = 0; i < count; i++) {
			View listItem = listAdapter.getView(i, null, listview);
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
		}
		ViewGroup.LayoutParams params = listview.getLayoutParams();
		params.height = totalHeight;
		params.height = totalHeight + (listview.getDividerHeight() * (listAdapter.getCount() - 1));
		params.height += 5;
		listview.setLayoutParams(params);
	}



	/**
	 * 获取系统版本
	 * 
	 * @param context
	 * @return
	 */
	public static String getAppVerson(Context context) {
		String versionName;
		try {
			String pkName = context.getPackageName();
			versionName = context.getPackageManager().getPackageInfo(pkName, 0).versionName;
			// int versionCode = this.getPackageManager()
			// .getPackageInfo(pkName, 0).versionCode;
			// return pkName + " " + versionName + " " + versionCode;
		} catch (Exception e) {
			// e.printStackTrace();
			versionName = "版本号未知";
		}
		return versionName;
	}
}
