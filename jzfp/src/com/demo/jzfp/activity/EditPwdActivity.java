package com.demo.jzfp.activity;

import android.content.SharedPreferences.Editor;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.jzfp.R;

public class EditPwdActivity  extends BaseActivity implements OnClickListener{

	@Override
	protected void setView() {
		setContentView(R.layout.fragment_options_pwd);
	}

	@Override
	protected void initView() {
		ImageView imageView = (ImageView) findViewById(R.id.iv_back);
		TextView textView = (TextView) findViewById(R.id.tv_title);
		textView.setText("密码修改");
		imageView.setOnClickListener(this);
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
