package com.demo.jzfp.activity;

import com.demo.jzfp.R;

import android.view.View;
import android.widget.ListView;

public class VillagesActivity extends BaseActivity{

	private ListView lv_listview;
	@Override
	protected void setView() {
		View view = View.inflate(this, R.layout.activity_villages, null);
		setContentView(view);
		lv_listview = (ListView) findViewById(R.id.lv_listview);
		
	}

	@Override
	protected void initView() {
		
	}

	@Override
	protected void initData() {
		
	}

}
