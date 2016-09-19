package com.demo.jzfp.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.demo.jzfp.dao.AreaDataDao;
import com.demo.jzfp.dao.DictDataInfoDao;
import com.demo.jzfp.dao.TdataConfigDao;
import com.demo.jzfp.dao.impl.AreaDataDaoImpl;
import com.demo.jzfp.dao.impl.DictDataInfoDaoImpl;
import com.demo.jzfp.dao.impl.TdataConfigDaoImpl;
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
	private TdataConfigDao tdataConfigDao = new TdataConfigDaoImpl();
	private AreaDataDao areaDataDao = new AreaDataDaoImpl();
	private String result = null;
	public final static  String[] SERVICESYN =  {"2001","2002","2003"};
	
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
						case 2002:
							tdataConfigSyn();
							break;
						case 2003:
							areaDataSyn();
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
				Log.e("executeQueryDictDataInfo>>>>", ">>>>>同步数据字典异常>>>>>");
			}
		}
	}
	/**
	 * 七个一批数据同步
	 */
	public synchronized void tdataConfigSyn() {
		try {
			int count = tdataConfigDao.existTdataConfigInfo(db);
			if(count<=0){
				executeQueryTdataConfig();
			}
		} catch (Exception e) {
			Log.e("tdataConfigSyn", e.getMessage());
		}
	}
	private void executeQueryTdataConfig() {
		result = WebServiceClient.callWeb("selectTdataConfig", null);
		if(null != result){
			try {
				List<Map> mapList = JSON.parseObject(result, List.class);
				if(mapList.size() > 0){
					tdataConfigDao.deleteTdataConfig(db);
					db.beginTransaction();
					for (Map map : mapList) {
						tdataConfigDao.insertTdataConfig(map, db);
					}
					db.setTransactionSuccessful();//设置事务处理成功，不设置会自动回滚不提交
					db.endTransaction();//处理完成
				}
			} catch (Exception e) {
				e.printStackTrace();
				Log.e("executeQueryTdataConfig>>>>", ">>>>>同步七个一批数据异常>>>>>");
			}
		}
	}
	/**
	 * 机构信息同步
	 */
	public synchronized void areaDataSyn() {
		try {
			int count = areaDataDao.existAreaDataInfo(db);
			if(count<=0){
				executeQueryAreaDataSyn();
			}
		} catch (Exception e) {
			Log.e("areaDataSyn", e.getMessage());
		}
	}
	private void executeQueryAreaDataSyn() {
		result = WebServiceClient.callWeb("selectToRegister", null);
		if(null != result){
			try {
				List<Map> mapList = JSON.parseObject(result, List.class);
				if(mapList.size() > 0){
					areaDataDao.deleteAreaData(db);
					db.beginTransaction();
					for (Map map : mapList) {
						areaDataDao.insertAreaData(map, db);
					}
					db.setTransactionSuccessful();//设置事务处理成功，不设置会自动回滚不提交
					db.endTransaction();//处理完成
				}
			} catch (Exception e) {
				e.printStackTrace();
				Log.e("executeQueryAreaDataSyn>>>>", ">>>>>同步机构信息异常>>>>>");
			}
		}
	}
	
}
