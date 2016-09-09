package com.demo.jzfp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.demo.jzfp.utils.StringUtil;


public class DatabaseHelper extends SQLiteOpenHelper {
	private String TAG = "DatabaseHelper";
	private static final String DATABASE_NAME = "jzfp.db"; //数据库名称
	private static final int DATABASE_VERSION = 1; //数据库版本
	
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);//CursorFactory设置为null,使用默认值
	}

	/**
	 * Description: </br>
	 * 数据库第一次被创建时onCreate会被调用
	 * @param db
	 * <br><br>
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		//字典信息表
		db.execSQL("create table dict_data(dictId text,dictType text,dictName text,dictValue text,dictCode text,isdefault text,parentType text)");
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
		db.execSQL("create table  dict_data(dictId text,dictType text,dictName text,dictValue text,dictCode text,isdefault text,parentType text)");
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
