package com.demo.jzfp.activity;

import com.demo.jzfp.R;
import com.demo.jzfp.utils.MyApplication;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class RegisterActivity extends BaseActivity {

	@ViewInject(R.id.ed_username)
	private EditText ed_username;
	@ViewInject(R.id.ed_password)
	private EditText ed_password;
	@ViewInject(R.id.tv_institution)
	private TextView tv_institution;
	@ViewInject(R.id.tv_groud)
	private TextView tv_groud;
	private MyApplication activityList;
	
	@Override
	protected void setView() {
		View view = View.inflate(this, R.layout.activity_register, null);
		activityList = (MyApplication) getApplicationContext();
		activityList.addActivity(this);
		setContentView(view);
		ViewUtils.inject(this,view);
		setTitleText("用户注册");
		setOnback(this);
	}

	@Override
	protected void initView() {

	}

	@Override
	protected void initData() {
	}

	@OnClick({R.id.ll_institution,R.id.ll_groud,R.id.btn_register})
	public void mClick(View view){
		switch (view.getId()) {
		case R.id.ll_institution:
			openActivity(ChooseAreaActivity.class, null);
			break;
		case R.id.ll_groud:
			
			break;
		case R.id.btn_register:
			
			break;

		default:
			break;
		}
	}
}
