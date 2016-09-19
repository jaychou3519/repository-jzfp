package com.demo.jzfp.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.demo.jzfp.dao.AreaDataDao;
import com.demo.jzfp.utils.StringUtil;

public class AreaDataDaoImpl implements AreaDataDao {

	@Override
	public int existAreaDataInfo(SQLiteDatabase db) throws SQLException {
		int count = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) as count from area_data");
		Cursor cursor = db.rawQuery(sql.toString(), new String[] {});
		if (cursor.moveToNext()) {
			count=cursor.getInt(cursor.getColumnIndex("count"));
		}
		cursor.close();
		return count;
	}

	@Override
	public void deleteAreaData(SQLiteDatabase db) throws SQLException {
		db.beginTransaction();
		db.execSQL("delete from area_data");
		db.setTransactionSuccessful();//设置事务处理成功，不设置会自动回滚不提交
		db.endTransaction();//处理完成
	}

	@Override
	public boolean insertAreaData(Map map, SQLiteDatabase db) throws Exception {
		String id = StringUtil.Object2String(map.get("id"));
		String pid =  StringUtil.Object2String(map.get("pid"));
		String name =  StringUtil.Object2String(map.get("name"));
		String areacode =  StringUtil.Object2String(map.get("areacode"));
		db.execSQL("insert into area_data(id,pid,name,areacode) values(?,?,?,?)",
				new Object[] {id,pid,name,areacode});
		return true;
	}

	@Override
	public List<Map> queryAreaData(SQLiteDatabase db) {
		List<Map> mapList = new ArrayList<Map>(); 
		StringBuffer sql = new StringBuffer();
		sql.append("select * from area_data a");
		Cursor cursor = db.rawQuery(sql.toString(), new String[]{});
		while (cursor.moveToNext()) {
			Map map = new HashMap();
			map.put("id", cursor.getString(cursor.getColumnIndex("id")));
			map.put("name", cursor.getString(cursor.getColumnIndex("name")));
			mapList.add(map);
		}
		cursor.close();
		return mapList;
	}

	@Override
	public String queryNameById(SQLiteDatabase db, String id) {
		String name = null; 
		StringBuffer sql = new StringBuffer();
		sql.append("select * from area_data dd where dd.id=?");
		Cursor cursor = db.rawQuery(sql.toString(), new String[] {id});
		while (cursor.moveToNext()) {
			name = cursor.getString(cursor.getColumnIndex("name"));
		}
		cursor.close();
		return name;
	}

}
