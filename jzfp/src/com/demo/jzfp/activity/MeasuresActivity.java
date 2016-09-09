package com.demo.jzfp.activity;

import java.util.ArrayList;
import java.util.List;

import android.widget.ListView;

import com.demo.jzfp.R;
import com.demo.jzfp.apdater.MeasuresAdapter;
import com.demo.jzfp.utils.MyApplication;

public class MeasuresActivity extends BaseActivity {
	private MyApplication activityList;
	private ListView lv_measures;
	
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
		List<String> measureses = new ArrayList<String>();
		measureses.add("发展生产脱贫贫因农户小额贷款");
		measureses.add("异地扶贫搬迁脱贫或危旧房改造");
		measureses.add("发展教育脱贫");
		measureses.add("社会保障兜底");
		measureses.add("生态补偿脱贫");
		measureses.add("转移就业脱贫");
		measureses.add("医疗保障求助");
		measureses.add("政策性补助");
		measureses.add("水、电、路等帮扶工程收益情况");
		
		MeasuresAdapter adapter = new MeasuresAdapter(this, measureses);
		lv_measures.setAdapter(adapter);
	}

}
