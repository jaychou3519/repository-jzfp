package com.demo.jzfp.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.demo.jzfp.R;
import com.demo.jzfp.apdater.MeasureDetailAdapter;
import com.demo.jzfp.utils.MyApplication;

public class MeasureDetailActivity extends BaseActivity {
	private int POSITION = 0;
	private GridView gv_checkbox;

	@Override
	protected void setView() {
		setContentView(R.layout.activity_measure_detail);
		MyApplication activityList = (MyApplication) getApplicationContext();
		activityList.addActivity(this);
		Bundle bundle = getIntent().getExtras();
		String title = bundle.getString("title");
		POSITION = bundle.getInt("position");
		setTitleText(title);
		setOnback(this);
	}

	@Override
	protected void initView() {
		TextView tv_save = (TextView) findViewById(R.id.tv_save);

		gv_checkbox = (GridView) findViewById(R.id.gv_checkbox);
		if (0 != POSITION) {
			gv_checkbox.setVisibility(View.GONE);
		}
		EditText et_income = (EditText) findViewById(R.id.et_income);
		EditText et_illustrate = (EditText) findViewById(R.id.et_illustrate);
	}

	@Override
	protected void initData() {
		List<String> strs = new ArrayList<String>();
		strs.add("含种苗");
		strs.add("资金直接帮扶");
		strs.add("委托企业帮扶返利");
		strs.add("入股合作社分红");
		strs.add("产权收益扶贫");
		strs.add("光伏扶贫");
		strs.add("旅游从业扶贫");
		strs.add("种养技术培训");

		MeasureDetailAdapter adapter = new MeasureDetailAdapter(this, strs);
		gv_checkbox.setAdapter(adapter);
	}

}
