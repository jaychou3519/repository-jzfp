package com.demo.jzfp.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.demo.jzfp.dao.TdataConfigDao;
import com.demo.jzfp.utils.StringUtil;

public class TdataConfigDaoImpl implements TdataConfigDao{

	@Override
	public int existTdataConfigInfo(SQLiteDatabase db) throws SQLException {
		int count = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) as count from tdata_config");
		Cursor cursor = db.rawQuery(sql.toString(), new String[] {});
		if (cursor.moveToNext()) {
			count=cursor.getInt(cursor.getColumnIndex("count"));
		}
		cursor.close();
		return count;
	}

	@Override
	public void deleteTdataConfig(SQLiteDatabase db) throws SQLException {
		db.beginTransaction();
		db.execSQL("delete from tdata_config");
		db.setTransactionSuccessful();//设置事务处理成功，不设置会自动回滚不提交
		db.endTransaction();//处理完成
	}

	@Override
	public boolean insertTdataConfig(Map map, SQLiteDatabase db)
			throws Exception {
		String configId = StringUtil.Object2String(map.get("configId"));
		String actionType =  StringUtil.Object2String(map.get("actionType"));
		String actionDl =  StringUtil.Object2String(map.get("actionDl"));
		String actionXl =  StringUtil.Object2String(map.get("actionXl"));
		String actionDlCode =  StringUtil.Object2String(map.get("actionDlCode"));
		String actionXlCode =  StringUtil.Object2String(map.get("actionXlCode"));
		String configNo =  StringUtil.Object2String(map.get("configNo"));
		db.execSQL("insert into tdata_config(configId,actionType,actionDl,actionXl,actionDlCode,actionXlCode,configNo) values(?,?,?,?,?,?,?)",
				new Object[] {configId,actionType,actionDl,actionXl,actionDlCode,actionXlCode,configNo});
		return true;
	}

	@Override
	public List<String> queryActionDl(SQLiteDatabase db) {
		List<String> actionDl = new ArrayList<String>(); 
		StringBuffer sql = new StringBuffer();
		sql.append("select * from tdata_config cc");
		Cursor cursor = db.rawQuery(sql.toString(), new String[] {});
		while (cursor.moveToNext()) {
			String _value = cursor.getString(cursor.getColumnIndex("actionDl"));
			actionDl.add(_value); 
		}
		cursor.close();
		return actionDl;
	}
	
	@Override
	public List<String> queryActionDlCode(SQLiteDatabase db) {
		List<String> actionDl = new ArrayList<String>(); 
		StringBuffer sql = new StringBuffer();
		sql.append("select * from tdata_config cc");
		Cursor cursor = db.rawQuery(sql.toString(), new String[] {});
		while (cursor.moveToNext()) {
			String _value = cursor.getString(cursor.getColumnIndex("actionDlCode"));
			actionDl.add(_value); 
		}
		cursor.close();
		return actionDl;
	}

	@Override
	public String queryActionXlByActionDl(SQLiteDatabase db,String actionDl){
		String actionXl = null; 
		StringBuffer sql = new StringBuffer();
		sql.append("select * from tdata_config cc where cc.actionDl=?");
		Cursor cursor = db.rawQuery(sql.toString(), new String[] {actionDl});
		while (cursor.moveToNext()) {
			actionXl = cursor.getString(cursor.getColumnIndex("actionXl"));
		}
		cursor.close();
		return actionXl;
	}

}
