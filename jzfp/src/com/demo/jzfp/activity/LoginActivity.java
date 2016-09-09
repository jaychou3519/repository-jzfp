package com.demo.jzfp.activity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.alibaba.fastjson.JSON;
import com.demo.jzfp.R;
import com.demo.jzfp.activity.ChooseAreaActivity.MyHandler;
import com.demo.jzfp.apdater.VillagesAdapter2;
import com.demo.jzfp.entity.TsysUserinfo;
import com.demo.jzfp.utils.MyApplication;
import com.demo.jzfp.utils.WebServiceClient;


public class LoginActivity extends BaseActivity implements OnClickListener{
	private EditText et_username,et_password;
	private CheckBox cb_keep;
	private SharedPreferences sp;
	private MyApplication activityList;
	private static String METHOD_NAME = "selectLogin";
	private static String result;
	
	@Override
	protected void setView() {
		setContentView(R.layout.activity_login);
		activityList = (MyApplication) getApplicationContext();
		activityList.addActivity(this);
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
			intoMain();
			break;
		case R.id.tv_register:
			openActivity(RegisterActivity.class, null);
			break;
		}
	}
	
	public void intoMain(){
		if(null ==  et_username.getText() || et_username.getText().toString().isEmpty()){
			showDialog(this, "提示", "用户名不能为空!",0);
			return;
		}
		if(null ==  et_password.getText() || et_password.getText().toString().isEmpty()){
			showDialog(this, "提示", "密码不能为空!",0);
			return;
		}
		String username = et_username.getText().toString();
		String password = et_password.getText().toString();
		TsysUserinfo tsysUserinfo = new TsysUserinfo();
		tsysUserinfo.setLoginName(username);
		tsysUserinfo.setPassword(password);
		Map map = new HashMap();
		map.put("arg0", JSON.toJSONString(tsysUserinfo));
		callWeb(map);
	}

	
	MyHandler myHandler = new MyHandler(this);
	
	public void callWeb(final Map map){
        new Thread(new Runnable() {
            @Override
            public void run() {
                result = WebServiceClient.callWeb(METHOD_NAME,map);
                myHandler.sendEmptyMessage(1);
            }
        }).start();
     }
	
	class MyHandler extends Handler{
	        WeakReference<LoginActivity> mActivity;
	
	        MyHandler(LoginActivity theActivity) {
	            mActivity = new WeakReference<LoginActivity>(theActivity);
	        }
	
	        @Override
	        public void handleMessage(Message msg) {
	        	LoginActivity myActivity = mActivity.get();
	            switch (msg.what) {
	                case 1:
	                	if(null != result && ("1").equals(result)){
	                		showDialog(myActivity, "提示", "账号不存在，请重新输入!",0);
	                	}else if(null != result && ("2").equals(result)){
	                		showDialog(myActivity, "提示", "密码错误，请重新输入!",0);
	                	}else if(null != result && ("3").equals(result)){
	                		showDialog(myActivity, "提示", "用户已停用,请与系统管理员联系!",0);
	                	}else{
	                		openActivity(MainActivity.class, null);
		            		finish();
	                	}
	                    break;
	            }
	            super.handleMessage(msg);
	        }
	}
	
	public void showDialog(Context context,String title,String message,final Integer code){
		AlertDialog.Builder builder = new Builder(context);
		builder.setIcon(android.R.drawable.ic_dialog_alert);
		builder.setTitle(title);
		builder.setMessage(message);
		builder.setPositiveButton("确定", new android.content.DialogInterface.OnClickListener() {
		        @Override
		        public void onClick(DialogInterface dialog, int which) {
		        	
		        }
		});
		AlertDialog dialog = builder.create();
		dialog.show();
	}


}
