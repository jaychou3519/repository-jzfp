package com.demo.jzfp.utils;

import java.util.ArrayList;
import java.util.List;

import com.demo.jzfp.entity.Login;

import android.app.Activity;
import android.app.Application;

public class MyApplication extends Application {
	
	public static Login login;
	
	private List<Activity> list = new ArrayList<Activity>();
	/**
	 * 
	 * Description: 
	 * 添加一个activity到集合
	 * @param activity
	 */
	public void addActivity(Activity activity) {
		if (list == null) {
			list = new ArrayList<Activity>();
		}
		list.add(activity);
	}
	/**
	 * 
	 * Description: 
	 * 结束所有activity
	 */
	public void finishAll() {
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
	public int getListSize(){
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
	public void removeActivity(Activity activity){
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
	public boolean isHasActivity(Activity activity){
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
	public void finishActivityByName(Activity activity){
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
}
