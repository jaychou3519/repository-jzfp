package com.demo.jzfp.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.view.View.MeasureSpec;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
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
	 * Gson 单例
	 */
	private static Gson gson = null;

	private static Gson getGson() {
		if (gson == null) {
			gson = new Gson();
		}
		return gson;
	}

	public static Gson GG() {
		return getGson();
	}

	 /**
     * 描述：将null转化为“”.
     *
     * @param str 指定的字符串
     * @return 字符串的String类型
     */
    public static String parseEmpty(String str) {
        if(str==null || "null".equals(str.trim())){
        	str = "";
        }
        return str.trim();
    }
    
	/**
	 * 获得屏幕宽度
	 * 
	 * @param context
	 * @return
	 */
	public static int getScreenWidth(Context context) {
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		return outMetrics.widthPixels;
	}

	/**
	 * 弹出popupwindow
	 */
	public static PopupWindow showPopWindow2(Context context, View targetView, View contentView, Integer width) {
		PopupWindow popupWindow = null;
		popupWindow = new PopupWindow(contentView, -2, -2);
		popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
		if (width != null) {
			popupWindow.setWidth(width);
		}
		popupWindow.setOutsideTouchable(true);
		popupWindow.showAtLocation(targetView, Gravity.BOTTOM, 0, 0);
		return popupWindow;
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
	public static void setOpenActivityBundle(Context context, Class<?> pclass, Bundle bundle) {
		Intent intent = new Intent(context, pclass);
		if (bundle != null) {
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
	 * debug日志
	 * 
	 * @param clazz
	 * @param message
	 */
	public static void d(Class<?> clazz, String message) {
		String tag = clazz.getSimpleName();
		Log.d(tag, message);
	}

	/**
	 * error日志
	 * 
	 * @param clazz
	 * @param message
	 */
	public static void e(Class<?> clazz, String message) {
		String tag = clazz.getSimpleName();
		Log.e(tag, message);
	}

	/**
	 * 获取包信息.
	 * 
	 * @param context
	 *            the context
	 */
	public static PackageInfo getPackageInfo(Context context) {
		PackageInfo info = null;
		try {
			String packageName = context.getPackageName();
			info = context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}

	/**
	 * 描述：移除Fragment和View
	 * 
	 * @param view
	 */
	public static void removeDialog(View view) {
		removeDialog(view.getContext());
		removeSelfFromParent(view);
	}

	/**
	 * 描述：移除Fragment.
	 * 
	 * @param context
	 *            the context
	 */
	public static void removeDialog(Context context) {
		try {
			FragmentActivity activity = (FragmentActivity) context;
			FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
			// 指定一个系统转场动画
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
			Fragment prev = activity.getFragmentManager().findFragmentByTag("dialog");
			if (prev != null) {
				ft.remove(prev);
			}
			ft.addToBackStack(null);
			if (context != null) {
				ft.commit();
			}
		} catch (Exception e) {
			// 可能有Activity已经被销毁的异常
			e.printStackTrace();
		}
	}

	/**
	 * 从父亲布局中移除自己
	 * 
	 * @param v
	 */
	public static void removeSelfFromParent(View v) {
		ViewParent parent = v.getParent();
		if (parent != null) {
			if (parent instanceof ViewGroup) {
				((ViewGroup) parent).removeView(v);
			}
		}
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

	/**
	 * 设定listview布局宽高
	 */
	public static void setListViewHeight(ListView listview) {
		ListAdapter listAdapter = listview.getAdapter();
		if (listAdapter == null) {
			return;
		}
		int totalHeight = 0;
		Tools.i("Tools", " listAdapter.getCount()=" + listAdapter.getCount());
		for (int i = 0; i < listAdapter.getCount(); i++) {
			View listItem = listAdapter.getView(i, null, listview);
			listItem.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
					MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
			totalHeight += listItem.getMeasuredHeight();
		}
		ViewGroup.LayoutParams params = listview.getLayoutParams();
		params.height = totalHeight;
		params.height = totalHeight + (listview.getDividerHeight() * (listAdapter.getCount() - 1));
		params.height += 5;
		listview.setLayoutParams(params);
	}

	/**
	 * 
	 * Description: 用来判断服务是否运行.
	 * 
	 * @param context
	 * @param className
	 *            判断的服务名字：包名+类名
	 * @return true 在运行, false 不在运行 <br>
	 *         <br>
	 * 
	 *         ******************************************** <br>
	 */
	public static boolean isServiceRunning(Context context, String className) {
		boolean isRunning = false;
		ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<ActivityManager.RunningServiceInfo> serviceList = activityManager.getRunningServices(Integer.MAX_VALUE);
		if (!(serviceList.size() > 0)) {
			return false;
		}
		for (int i = 0; i < serviceList.size(); i++) {
			if (serviceList.get(i).service.getClassName().equals(className) == true) {
				isRunning = true;
				break;
			}
		}
		return isRunning;
	}
	
	/**
	 * 读取相册照片
	 * 
	 * @param path
	 *            照片路径
	 * @return 返回照片
	 */
	public static Bitmap Readimg(String path) {
		FileInputStream fis = null;
		Bitmap bitmap = null;
		if (path != null) {
			try {
				fis = new FileInputStream(path);
				bitmap = BitmapFactory.decodeStream(fis);
				if (bitmap != null)
					return bitmap;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} finally {
				try {
					if (fis != null)
						fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
