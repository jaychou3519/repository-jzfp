package com.demo.jzfp.dao;

import java.util.List;
import java.util.Map;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public interface AreaDataDao {
	public int existAreaDataInfo(SQLiteDatabase db) throws SQLException;
	public void deleteAreaData(SQLiteDatabase db)throws SQLException;
	public boolean insertAreaData(Map map,SQLiteDatabase db) throws Exception;
	public List<Map> queryAreaData(SQLiteDatabase db);
	public String queryNameById(SQLiteDatabase db,String id);
}
