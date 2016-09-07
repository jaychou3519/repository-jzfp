package com.demo.jzfp.activity;

import java.util.ArrayList;
import java.util.List;

import com.demo.jzfp.R;
import com.demo.jzfp.apdater.VillagesAdapter;
import com.demo.jzfp.utils.MyApplication;

import android.view.View;
import android.widget.ListView;

public class VillagesActivity extends BaseActivity{

	private ListView lv_listview;
	private List<String> datas = new ArrayList<String>();
	private MyApplication activityList;
	@Override
	protected void setView() {
		View view = View.inflate(this, R.layout.activity_villages, null);
		setContentView(view);
		activityList = (MyApplication) getApplicationContext();
		activityList.addActivity(this);
		lv_listview = (ListView) findViewById(R.id.lv_listview);
		setTitleText("乡镇介绍");
		setOnback(this);
	}

	@Override
	protected void initView() {
		
	}

	@Override
	protected void initData() {
		datas.add("霞阳镇");
		datas.add("沔渡镇");
		datas.add("十都镇");
		datas.add("水口镇");
		VillagesAdapter adapter = new VillagesAdapter(VillagesActivity.this, datas);
		lv_listview.setAdapter(adapter);
	}

}
