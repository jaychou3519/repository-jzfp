package com.demo.jzfp.activity;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.demo.jzfp.R;

public class EconomyActivity extends BaseActivity implements OnClickListener {

	@Override
	protected void setView() {
		setContentView(R.layout.activity_ecomomy);
		setTitleText("家庭经济");
		setOnback(this);

	}

	@Override
	protected void initView() {
		TextView tv_save = (TextView) findViewById(R.id.tv_save);
		RelativeLayout rl_construction = (RelativeLayout) findViewById(R.id.rl_construction);
		EditText et_area = (EditText) findViewById(R.id.et_area);
		EditText et_land = (EditText) findViewById(R.id.et_land);
		EditText et_hill = (EditText) findViewById(R.id.et_hill);
		EditText et_dominate = (EditText) findViewById(R.id.et_dominate);
		
		tv_save.setOnClickListener(this);
		rl_construction.setOnClickListener(this);
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_save:
			break;
		case R.id.rl_construction:
			break;
		}

	}

}
