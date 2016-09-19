package com.demo.jzfp.dao;

import java.util.List;
import java.util.Map;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public interface BasicDao {
	public int existBasicInfo(SQLiteDatabase db) throws SQLException;
	public void deleteBasic(SQLiteDatabase db)throws SQLException;
	public boolean insertBasic(Map map,SQLiteDatabase db) throws Exception;
	public List<Map> queryBasicAll(SQLiteDatabase db);
	public String queryNameById(SQLiteDatabase db,String id);
}
