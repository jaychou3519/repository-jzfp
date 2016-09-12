package com.demo.jzfp.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.demo.jzfp.R;
import com.demo.jzfp.apdater.MeasureDetailAdapter;
import com.demo.jzfp.utils.Constant;
import com.demo.jzfp.utils.MyApplication;

public class MeasureDetailActivity extends BaseActivity implements OnClickListener {
	private int POSITION = 0;
	private GridView gv_checkbox;
	private EditText et_income, et_illustrate;
	private String title;

	@Override
	protected void setView() {
		setContentView(R.layout.activity_measure_detail);
		MyApplication activityList = (MyApplication) getApplicationContext();
		activityList.addActivity(this);
		Bundle bundle = getIntent().getExtras();
		title = bundle.getString("title");
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
		et_income = (EditText) findViewById(R.id.et_income);
		et_illustrate = (EditText) findViewById(R.id.et_illustrate);
		
		tv_save.setOnClickListener(this);
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
		
		if(title.equals(Constant.poor.getMeasure())){
			if(!TextUtils.isEmpty(Constant.poor.getMoney()))
				et_income.setText(Constant.poor.getMoney());
			if(!TextUtils.isEmpty(Constant.poor.getRealCase()))
				et_illustrate.setText(Constant.poor.getRealCase());
		}
		
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.tv_save:
			setData();
			finish();
			break;
		}
		
	}
	
	/**
	 * 设置贫因户信息
	 */
	private void setData(){
		Constant.poor.setMeasure(title);
		Constant.poor.setMeasureDetail("");
		Constant.poor.setMoney(et_income.getText().toString().trim()+"");
		Constant.poor.setRealCase(et_illustrate.getText().toString().trim()+"");
	}
	

}
