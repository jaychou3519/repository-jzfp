package com.demo.jzfp.activity;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.demo.jzfp.R;

public class ReasonActivity extends BaseActivity implements OnClickListener {

	@Override
	protected void setView() {
		setContentView(R.layout.activity_reason);
		setTitleText("致贫说明");
		setOnback(this);
	}

	@Override
	protected void initView() {
		TextView tv_save = (TextView) findViewById(R.id.tv_save);
		RelativeLayout rl_reason = (RelativeLayout) findViewById(R.id.rl_reason);
		EditText et_illustrate = (EditText) findViewById(R.id.et_illustrate);
		
		tv_save.setOnClickListener(this);
		rl_reason.setOnClickListener(this);
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
		case R.id.rl_reason:
			break;
		}
	}

}
