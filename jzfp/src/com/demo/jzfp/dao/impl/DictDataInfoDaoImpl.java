package com.demo.jzfp.dao.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.demo.jzfp.dao.DictDataInfoDao;
import com.demo.jzfp.utils.StringUtil;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DictDataInfoDaoImpl implements DictDataInfoDao {

	@Override
	public int existDictDataInfo(SQLiteDatabase db)throws SQLException {
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
	public void deleteDictDataInfo(SQLiteDatabase db) throws SQLException {
		db.beginTransaction();
		db.execSQL("delete from tdata_config");
		db.setTransactionSuccessful();//设置事务处理成功，不设置会自动回滚不提交
		db.endTransaction();//处理完成
	}

	
	
	@Override
	public boolean insertDictDataInfo(Map map, SQLiteDatabase db)throws Exception {
		String dictId = StringUtil.Object2String(map.get("dictId"));
		String dictType =  StringUtil.Object2String(map.get("dictType"));
		String dictName =  StringUtil.Object2String(map.get("dictName"));
		String dictValue =  StringUtil.Object2String(map.get("dictValue"));
		String dictCode =  StringUtil.Object2String(map.get("dictCode"));
		String isdefault =  StringUtil.Object2String(map.get("isdefault"));
		String parentType =  StringUtil.Object2String(map.get("parentType"));
		db.execSQL("insert into tdata_config(dictId,dictType,dictName,dictValue,dictCode,isdefault,parentType) values(?,?,?,?,?,?,?)",
				new Object[] {dictId,dictType,dictName,dictValue,dictCode,isdefault,parentType});
		return true;
	}

	@Override
	public List<String> queryDictValueByType(SQLiteDatabase db,String dictType) {
		List<String> dictValue = new ArrayList<String>(); 
		StringBuffer sql = new StringBuffer();
		sql.append("select * from tdata_config dd where dd.dictType=?");
		Cursor cursor = db.rawQuery(sql.toString(), new String[] {dictType});
		while (cursor.moveToNext()) {
			String _value = cursor.getString(cursor.getColumnIndex("dictValue"));
			dictValue.add(_value); 
		}
		cursor.close();
		return dictValue;
	}

	@Override
	public String queryDictCodeByValue(SQLiteDatabase db, String value) {
		String dictCode = null; 
		StringBuffer sql = new StringBuffer();
		sql.append("select * from tdata_config dd where dd.dictValue=?");
		Cursor cursor = db.rawQuery(sql.toString(), new String[] {value});
		while (cursor.moveToNext()) {
			 dictCode = cursor.getString(cursor.getColumnIndex("dictCode"));
		}
		cursor.close();
		return dictCode;
	}
}
