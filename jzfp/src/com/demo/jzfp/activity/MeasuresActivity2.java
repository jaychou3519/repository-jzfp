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
import com.demo.jzfp.dao.TdataConfigDao;
import com.demo.jzfp.dao.impl.TdataConfigDaoImpl;
import com.demo.jzfp.database.DatabaseHelper;
import com.demo.jzfp.utils.MyApplication;
import com.demo.jzfp.utils.Tools;

public class MeasuresActivity2 extends BaseActivity {
	private ListView lv_measures;
	private MeasuresAdapter adapter;
	private List<String> measureses;
	private List<String> measuresesCode;
	private String countrymanId;
	private SQLiteDatabase db = null;
	private TdataConfigDao tdataConfigDao = new TdataConfigDaoImpl();

	@Override
	protected void setView() {
		setContentView(R.layout.activity_measures);
		MyApplication.addActivity(MeasuresActivity2.this);
		setTitleText("选择帮扶措施大类");
		setOnback(this);
	}

	@Override
	protected void initView() {
		lv_measures = (ListView) findViewById(R.id.lv_measures);
	}

	@Override
	protected void initData() {
		Bundle bundle = getIntent().getExtras();
		countrymanId = bundle.getString("countrymanId");
		
		db = (new DatabaseHelper(this)).getWritableDatabase();
		measureses = tdataConfigDao.queryActionDl(db);
		measuresesCode = tdataConfigDao.queryActionDlCode(db);

		adapter = new MeasuresAdapter(this, measureses);
		lv_measures.setAdapter(adapter);

		lv_measures.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				/*Bundle bundle = new Bundle();
				bundle.putString("title", measureses.get(position));
				openActivity(MeasureDetailActivity.class, bundle);*/
				
				Bundle bundle = new Bundle();
				bundle.putBoolean("state", true);
				bundle.putString("toForm", null);
				bundle.putString("countid", measuresesCode.get(position));
				bundle.putString("countrymanId", countrymanId);
				Tools.i("countid",  measuresesCode.get(position));
				openActivity(ArchivesPoorActivity3.class, bundle);
			}
		});
	}

	@Override
	protected void onStart() {
		super.onStart();
		adapter.notifyDataSetChanged();
	}
}
