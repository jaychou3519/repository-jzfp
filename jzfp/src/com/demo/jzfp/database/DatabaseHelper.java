package com.demo.jzfp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View.OnCreateContextMenuListener;

import com.demo.jzfp.utils.StringUtil;
import com.demo.jzfp.utils.Tools;


public class DatabaseHelper extends SQLiteOpenHelper {
	private String TAG = "DatabaseHelper";
	private static final String DATABASE_NAME = "jzfp.db"; //数据库名称
	private static final int DATABASE_VERSION = 9; //数据库版本
	
	private Context context;
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);//CursorFactory设置为null,使用默认值
		this.context = context;
	}

	/**
	 * Description: </br>
	 * 数据库第一次被创建时onCreate会被调用
	 * @param db
	 * <br><br>
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		Tools.i(TAG, "进入创建数据库...........");
		
		db.execSQL("DROP TABLE IF EXISTS dict_data");
		db.execSQL("DROP TABLE IF EXISTS tdata_config");
		db.execSQL("DROP TABLE IF EXISTS area_data");
		db.execSQL("DROP TABLE IF EXISTS baisc");
		
		//字典信息表
		db.execSQL("create table IF NOT EXISTS dict_data(dictId text,dictType text,dictName text,dictValue text,dictCode text,isdefault text,parentType text)");
		db.execSQL("create table IF NOT EXISTS tdata_config(configId text,actionType text,actionDl text,actionXl text,actionDlCode text,actionXlCode text,configNo text)");
		db.execSQL("create table IF NOT EXISTS area_data(id text,pid text,name text,areacode text)");
		db.execSQL("create table IF NOT EXISTS baisc(xzdwsj text,xzdwsjdh text,xzz text,xzzdh text,csj text,csjdh text,czr text,czrdh text)");
	}
	/**
	 * 
	 * @param db
	 * @param sql
	 */
	public void exeSQL(SQLiteDatabase db, String sql) {
		try {
			db.execSQL(sql);
		} catch (Exception e) {
			Log.e(TAG, StringUtil.deNull(e==null?"":e.getMessage()));
		}
	}
	/**
	 * 
	 * Description: </br>
	 * 如果DATABASE_VERSION值被改为2,系统发现现有数据库版本不同,即会调用onUpgrade
	 * @param db
	 * @param oldVersion
	 * @param newVersion
	 * <br><br>
	 * 
	 * ******************************************** <br>
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Tools.i(TAG, "进入创建数据库...........");
		
		db.execSQL("DROP TABLE IF EXISTS dict_data");
		db.execSQL("DROP TABLE IF EXISTS tdata_config");
		db.execSQL("DROP TABLE IF EXISTS area_data");
		db.execSQL("DROP TABLE IF EXISTS baisc");
		
		db.execSQL("create table IF NOT EXISTS dict_data(dictId text,dictType text,dictName text,dictValue text,dictCode text,isdefault text,parentType text)");
		db.execSQL("create table IF NOT EXISTS tdata_config(configId text,actionType text,actionDl text,actionXl text,actionDlCode text,actionXlCode text,configNo text)");
		db.execSQL("create table IF NOT EXISTS area_data(id text,pid text,name text,areacode text)");
		db.execSQL("create table IF NOT EXISTS baisc(id text,xzdwsj text,xzdwsjdh text,xzz text,xzzdh text,csj text,csjdh text,czr text,czrdh text)");
	}
	/**
	 * 
	 * Description: 
	 * 初初化系统需配置的数据库
	 * @param db
	 * <br><br>
	 * 
	 * ******************************************** <br>
	 */
	public void iniConfigData(SQLiteDatabase db) {
		
	}
}
