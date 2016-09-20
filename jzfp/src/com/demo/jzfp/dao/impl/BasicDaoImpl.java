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
		String xzdwsj =  StringUtil.Object2String(map.get("xzdwsj"));
		String xzdwsjdh =  StringUtil.Object2String(map.get("xzdwsjdh"));
		String xzz =  StringUtil.Object2String(map.get("xzz"));
		String xzzdh =  StringUtil.Object2String(map.get("xzzdh"));
		String csj =  StringUtil.Object2String(map.get("csj"));
		String csjdh =  StringUtil.Object2String(map.get("csjdh"));
		String czr =  StringUtil.Object2String(map.get("czr"));
		String czrdh =  StringUtil.Object2String(map.get("czrdh"));
		db.execSQL("insert into baisc(id,xzdwsj,xzdwsjdh,xzz,xzzdh,csj,csjdh,czr,czrdh) values(?,?,?,?,?,?,?,?,?)",
				new Object[] {"1",xzdwsj,xzdwsjdh,xzz,xzzdh,csj,csjdh,czr,czrdh});
		return true;
	}

	@Override
	public Map queryBasicAll(SQLiteDatabase db) {
		Map map = new HashMap(); 
		StringBuffer sql = new StringBuffer();
		sql.append("select * from baisc");
		Cursor cursor = db.rawQuery(sql.toString(), new String[]{});
		while (cursor.moveToNext()) {
			map.put("id", cursor.getString(cursor.getColumnIndex("id")));
			map.put("xzdwsj", cursor.getString(cursor.getColumnIndex("xzdwsj")));
			map.put("xzdwsjdh", cursor.getString(cursor.getColumnIndex("xzdwsjdh")));
			map.put("xzz", cursor.getString(cursor.getColumnIndex("xzz")));
			map.put("xzzdh", cursor.getString(cursor.getColumnIndex("xzzdh")));
			map.put("csj", cursor.getString(cursor.getColumnIndex("csj")));
			map.put("csjdh", cursor.getString(cursor.getColumnIndex("csjdh")));
			map.put("czr", cursor.getString(cursor.getColumnIndex("czr")));
			map.put("czrdh", cursor.getString(cursor.getColumnIndex("czrdh")));
		}
		cursor.close();
		return map;
	}

	@Override
	public boolean updateBasic(SQLiteDatabase db, Map map) {
		String xzdwsj =  StringUtil.Object2String(map.get("xzdwsj"));
		String xzdwsjdh =  StringUtil.Object2String(map.get("xzdwsjdh"));
		String xzz =  StringUtil.Object2String(map.get("xzz"));
		String xzzdh =  StringUtil.Object2String(map.get("xzzdh"));
		String csj =  StringUtil.Object2String(map.get("csj"));
		String csjdh =  StringUtil.Object2String(map.get("csjdh"));
		String czr =  StringUtil.Object2String(map.get("czr"));
		String czrdh =  StringUtil.Object2String(map.get("czrdh"));
		db.execSQL("update baisc set xzdwsj=?,xzdwsjdh=?,xzz=?,xzzdh=?,csj=?,csjdh=?,czr=?,czrdh=? where id=?",
				new Object[] {xzdwsj,xzdwsjdh,xzz,xzzdh,csj,csjdh,czr,czrdh,"1"});
		return true;
	}
}
