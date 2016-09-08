package com.demo.jzfp.activity;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.demo.jzfp.R;
import com.demo.jzfp.utils.MyApplication;

public class EditPwdActivity  extends BaseActivity implements OnClickListener{
	
	private MyApplication activityList;

	@Override
	protected void setView() {
		setContentView(R.layout.fragment_options_pwd);
		activityList = (MyApplication) getApplicationContext();
		activityList.addActivity(this);
	}

	@Override
	protected void initView() {
		TextView textView = (TextView) findViewById(R.id.tv_title);
		textView.setText("密码修改");
		setOnback(this);
	}

	@Override
	protected void initData() {
		
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.iv_back:
				openActivity(MainActivity.class, null);
				break;
		}
	}

}
