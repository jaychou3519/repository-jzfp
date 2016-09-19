package com.demo.jzfp.dao;

import java.util.List;
import java.util.Map;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public interface DictDataInfoDao {
	public int existDictDataInfo(SQLiteDatabase db) throws SQLException;
	public void deleteDictDataInfo(SQLiteDatabase db)throws SQLException;
	public boolean insertDictDataInfo(Map map,SQLiteDatabase db) throws Exception;
	public List<String> queryDictValueByType(SQLiteDatabase db,String type);
	public String queryDictCodeByValue(SQLiteDatabase db,String value);
	public String queryValueByDictCode(SQLiteDatabase db,String dictCode,String type);
}
