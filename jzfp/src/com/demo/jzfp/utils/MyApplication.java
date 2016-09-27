package com.demo.jzfp.utils;

import java.util.ArrayList;
import java.util.List;

import com.demo.jzfp.activity.ArchivesDetailsActivity;
import com.demo.jzfp.activity.BaseActivity;
import com.demo.jzfp.database.DatabaseHelper;
import com.demo.jzfp.entity.Login;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import android.app.Activity;
import android.app.Application;

public class MyApplication extends Application {
	
	@Override
	public void onCreate() {
		super.onCreate();
		ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this));
	}
	public static Login login;
	
	public static List<Activity> list = new ArrayList<Activity>();
	/**
	 * 
	 * Description: 
	 * 添加一个activity到集合
	 * @param activity
	 */
	public static void addActivity(Activity activity) {
		if (list == null) {
			list = new ArrayList<Activity>();
		}
		if(!list.contains(activity))
				list.add(activity);
	}
	/**
	 * 
	 * Description: 
	 * 结束所有activity
	 */
	public static void finishAll() {
		for (Activity activity : list) {
			activity.finish();
		}
		list = null;
	}

	/**
	 * 
	 * Description: 
	 * 获取集合大小
	 * @return
	 */
	public static int getListSize(){
		if (list == null) {
			return 0;
		}
		return list.size();
	}
	
	/**
	 * 
	 * Description: 
	 * 移除指定activity
	 * @param activity
	 */
	public static void removeActivity(Activity activity){
		if (list != null) {
			list.remove(activity);
		}
	}
	
	/**
	 * 
	 * Description: 
	 * 判断是否有此activity
	 * @param activity
	 * @return
	 */
	public static boolean isHasActivity(Activity activity){
		if (list != null) {
			return list.contains(activity);
		}
		return false;
	}
	
	/**
	 * 
	 * Description: 
	 * 结束指定的同类activity
	 * @param activity
	 */
	public static void finishActivityByName(Activity activity){
		String className = activity.getClass().getName();
		List<Activity> removeAcList = new ArrayList<Activity>();
		if(list != null){
			for(Activity ac : list){
				if(className.equals(ac.getClass().getName())){
					ac.finish();
					removeAcList.add(ac);
				}
			}
			list.removeAll(removeAcList);
		}
	}
	public static void finishActivityByName(String className) {
		List<Activity> removeAcList = new ArrayList<Activity>();
		if(list != null){
			for(Activity ac : list){
				Tools.i("list=", ac.getClass().getName());
				if(ac.getClass().getName().contains(className)){
					ac.finish();
					removeAcList.add(ac);
					Tools.i("classname", className);
				}
			}
			list.removeAll(removeAcList);
		}		
	}
}
