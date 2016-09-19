package com.demo.jzfp.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.demo.jzfp.dao.AreaDataDao;
import com.demo.jzfp.dao.BasicDao;
import com.demo.jzfp.utils.StringUtil;

public class BasicDaoImpl implements BasicDao {

	@Override
	public int existBasicInfo(SQLiteDatabase db) throws SQLException {
		int count = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) as count from baisc");
		Cursor cursor = db.rawQuery(sql.toString(), new String[] {});
		if (cursor.moveToNext()) {
			count=cursor.getInt(cursor.getColumnIndex("count"));
		}
		cursor.close();
		return count;
	}

	@Override
	public void deleteBasic(SQLiteDatabase db) throws SQLException {
		db.beginTransaction();
		db.execSQL("delete from baisc");
		db.setTransactionSuccessful();//设置事务处理成功，不设置会自动回滚不提交
		db.endTransaction();//处理完成
	}

	@Override
	public boolean insertBasic(Map map, SQLiteDatabase db) throws Exception {
		String name =  StringUtil.Object2String(map.get("name"));
		String phone =  StringUtil.Object2String(map.get("phone"));
		db.execSQL("insert into baisc(name,phone) values(?,?)",
				new Object[] {name,phone});
		return true;
	}

	@Override
	public List<Map> queryBasicAll(SQLiteDatabase db) {
		List<Map> mapList = new ArrayList<Map>(); 
		StringBuffer sql = new StringBuffer();
		sql.append("select * from baisc");
		Cursor cursor = db.rawQuery(sql.toString(), new String[]{});
		while (cursor.moveToNext()) {
			Map map = new HashMap();
			map.put("name", cursor.getString(cursor.getColumnIndex("name")));
			map.put("phone", cursor.getString(cursor.getColumnIndex("phone")));
			mapList.add(map);
		}
		cursor.close();
		return mapList;
	}

	@Override
	public String queryNameById(SQLiteDatabase db, String phone) {
		String name = null; 
		StringBuffer sql = new StringBuffer();
		sql.append("select * from baisc dd where dd.phone=?");
		Cursor cursor = db.rawQuery(sql.toString(), new String[] {phone});
		while (cursor.moveToNext()) {
			name = cursor.getString(cursor.getColumnIndex("phone"));
		}
		cursor.close();
		return name;
	}


}
