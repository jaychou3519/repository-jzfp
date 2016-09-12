package com.demo.jzfp.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.demo.jzfp.R;
import com.demo.jzfp.apdater.MeasuresAdapter;
import com.demo.jzfp.utils.MyApplication;

public class MeasuresActivity extends BaseActivity {
	private MyApplication activityList;
	private ListView lv_measures;
	private MeasuresAdapter adapter;
	
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
		final List<String> measureses = new ArrayList<String>();
		measureses.add("发展生产脱贫");
		measureses.add("易地扶贫搬迁脱贫");
		measureses.add("发展教育脱贫");
		measureses.add("社会保障兜底");
		measureses.add("生态补偿脱贫");
		measureses.add("转移就业脱贫");
		measureses.add("医疗保障求助");
		measureses.add("政策性补助");
		measureses.add("水、电、路等帮扶工程收益情况");
		
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
				bundle.putInt("position",position);
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
