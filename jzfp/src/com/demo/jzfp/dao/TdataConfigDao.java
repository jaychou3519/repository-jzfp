package com.demo.jzfp.dao;

import java.util.List;
import java.util.Map;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public interface TdataConfigDao {
	public int existTdataConfigInfo(SQLiteDatabase db) throws SQLException;
	public void deleteTdataConfig(SQLiteDatabase db)throws SQLException;
	public boolean insertTdataConfig(Map map,SQLiteDatabase db) throws Exception;
	public List<String> queryActionDl(SQLiteDatabase db);
	public List<String> queryActionDlCode(SQLiteDatabase db);
	public String queryActionXlByActionDl(SQLiteDatabase db,String actionDl);
	public String queryActionDlCodeByActionDl(SQLiteDatabase db,String actionDl);
}
