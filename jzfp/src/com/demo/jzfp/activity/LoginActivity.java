package com.demo.jzfp.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.demo.jzfp.R;


public class LoginActivity extends BaseActivity implements OnClickListener{
	private EditText et_username,et_password;
	private CheckBox cb_keep;
	private SharedPreferences sp;
	
	@Override
	protected void setView() {
		setContentView(R.layout.activity_login);
	}

	@Override
	protected void initView() {
		et_username = (EditText) findViewById(R.id.et_username);
		et_password = (EditText) findViewById(R.id.et_password);
		Button btn_login = (Button) findViewById(R.id.btn_login);
		btn_login.setOnClickListener(this);
		cb_keep = (CheckBox) findViewById(R.id.cb_keeps);
		TextView tv_register = (TextView) findViewById(R.id.tv_register);
		tv_register.setOnClickListener(this);
		
	}

	@Override
	protected void initData() {
		sp = getSharedPreferences("user", Context.MODE_PRIVATE);
		et_username.setText(sp.getString("username", ""));
		et_password.setText(sp.getString("password", ""));
		cb_keep.setChecked(sp.getBoolean("keep", false));
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.btn_login:
			Editor ed = sp.edit();
			if(cb_keep.isChecked()){
				ed.putString("username", et_username.getText().toString().trim());
				ed.putString("password", et_password.getText().toString().trim());
				ed.putBoolean("keep", true);
			}else{
				ed.putString("username", "");
				ed.putString("password", "");
				ed.putBoolean("keep", false);
			}
			ed.apply();
			openActivity(MainActivity.class, null);
			finish();
			break;
		case R.id.tv_register:
			openActivity(RegisterActivity.class, null);
			break;
		}
		
	}


}
