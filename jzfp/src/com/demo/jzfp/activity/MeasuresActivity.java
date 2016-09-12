package com.demo.jzfp.activity;

import java.util.List;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.demo.jzfp.R;
import com.demo.jzfp.apdater.MeasuresAdapter;
import com.demo.jzfp.dao.DictDataInfoDao;
import com.demo.jzfp.dao.impl.DictDataInfoDaoImpl;
import com.demo.jzfp.database.DatabaseHelper;
import com.demo.jzfp.utils.MyApplication;

public class MeasuresActivity extends BaseActivity {
	private MyApplication activityList;
	private ListView lv_measures;
	private MeasuresAdapter adapter;
	private List<String> measureses;
	private SQLiteDatabase db = null;
	private DictDataInfoDao dictDataDao = new DictDataInfoDaoImpl();
	
	@Override
	protected void setView() {
		setContentView(R.layout.activity_measures);
		activityList = (MyApplication) getApplicationContext();
		activityList.addActivity(this);
		setTitleText("帮扶措施");
		setOnback(this);
	}

	@Override
	protected void initView() {
		lv_measures = (ListView) findViewById(R.id.lv_measures);
	}

	@Override
	protected void initData() {
		db = (new DatabaseHelper(this)).getWritableDatabase();
		measureses = dictDataDao.queryDictValueByType(db, "qgyp");
		
		adapter = new MeasuresAdapter(this, measureses);
		lv_measures.setAdapter(adapter);
		
		lv_measures.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Bundle bundle = new Bundle();
				if(position == measureses.size() - 1){
					bundle.putString("title", "帮扶工程收益");
				} else {
					bundle.putString("title", measureses.get(position));
				}
				openActivity(MeasureDetailActivity.class, bundle);
			}
		});
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		adapter.notifyDataSetChanged();
	}
}
