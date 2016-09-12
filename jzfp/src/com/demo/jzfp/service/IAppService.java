package com.demo.jzfp.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.demo.jzfp.dao.DictDataInfoDao;
import com.demo.jzfp.dao.impl.DictDataInfoDaoImpl;
import com.demo.jzfp.database.DatabaseHelper;
import com.demo.jzfp.utils.ThreadPoolUtils;
import com.demo.jzfp.utils.WebServiceClient;

import android.app.Service;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.util.Log;

/**
 * 后台服务类 Copyright: Copyright (c) <br>
 * 
 * 
 * 
 * @version 1.0
 */

public class IAppService extends Service {

	private boolean isSyn = true;
	private SQLiteDatabase db = null;
	private DictDataInfoDao dictDataDao = new DictDataInfoDaoImpl();
	private String result = null;
	public final static  String[] SERVICESYN =  {"2001"};
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// 开启服务主线程，进行数据同步
		String[] its = SERVICESYN;
		for (String it : its) {
			Log.e("线程开始加载数据", "发送加载命令"+Integer.parseInt(it));
			ThreadPoolUtils.execute(new DataSynThread(Integer.parseInt(it)));
		}
		return super.onStartCommand(intent, flags, startId);
	}

	
	  
	@Override
	public void onCreate() {
		super.onCreate();
		db = (new DatabaseHelper(IAppService.this)).getWritableDatabase();
	}

	@Override
	public void onDestroy() {
		isSyn = false;
		super.onDestroy();
	}

	/**
	 * 数据同步线程
	 * 
	 * 
	 */
	class DataSynThread implements Runnable {
		
		private int msg;
		
		public DataSynThread(int msg) {
			this.msg = msg;
		}
		
		@Override
		public synchronized void run() {
			while (isSyn) {
				try {
					switch (msg) {
						case 2001:
							dictDataSyn();
							break;
						default:
							break;
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						wait(60*1000);// 每1分钟执行一次
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	/**
	 * 字典数据同步
	 */
	public synchronized void dictDataSyn() {
		try {
			int count = dictDataDao.existDictDataInfo(db);
			if(count<=0){
				executeQueryDictDataInfo();
			}
		} catch (Exception e) {
			Log.e("dictDataSyn", e.getMessage());
		}
	}
	private void executeQueryDictDataInfo() {
		result = WebServiceClient.callWeb("selectTsysDict", null);
		if(null != result){
			try {
				List<Map> mapList = JSON.parseObject(result, List.class);
				if(mapList.size() > 0){
					dictDataDao.deleteDictDataInfo(db);
					db.beginTransaction();
					for (Map map : mapList) {
						dictDataDao.insertDictDataInfo(map, db);
					}
					db.setTransactionSuccessful();//设置事务处理成功，不设置会自动回滚不提交
					db.endTransaction();//处理完成
				}
			} catch (Exception e) {
				e.printStackTrace();
				Log.e("dictDataDao>>>>", ">>>>>同步数据字典异常>>>>>");
			}
		}
	}
	
}