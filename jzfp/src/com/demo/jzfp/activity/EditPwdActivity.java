package com.demo.jzfp.activity;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.demo.jzfp.R;
import com.demo.jzfp.entity.TsysUserinfo;
import com.demo.jzfp.utils.MyApplication;
import com.demo.jzfp.utils.WebServiceClient;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

public class EditPwdActivity  extends BaseActivity implements android.view.View.OnClickListener{
	
	private MyApplication activityList;
	private Button btn_submit;
	private EditText oldPwd;
	private EditText newPwd;
	private EditText confirmPwd;
	private SharedPreferences sp;
	private static String METHOD_NAME = "updateTsysUserinfo";
	private String result;

	@Override
	protected void setView() {
		setContentView(R.layout.fragment_options_pwd);
		View view = View.inflate(this, R.layout.activity_register, null);
		activityList = (MyApplication) getApplicationContext();
		activityList.addActivity(this);
	}

	@Override
	protected void initView() {
		btn_submit = (Button) findViewById(R.id.btn_submit);
		oldPwd = (EditText) findViewById(R.id.oldPwd);
		newPwd = (EditText) findViewById(R.id.newPwd);
		confirmPwd = (EditText) findViewById(R.id.confirmPwd);
		TextView textView = (TextView) findViewById(R.id.tv_title);
		textView.setText("密码修改");
		btn_submit.setOnClickListener(this);
		setOnback(this);
	}

	@Override
	protected void initData() {
		sp = getSharedPreferences("user", Context.MODE_PRIVATE);
	}

	public void updatePwd(){
		if(null == oldPwd.getText() || oldPwd.getText().toString().isEmpty()){
			showDialog(this, "提示", "请输入旧密码",0);
			return;
		}
		if(null == newPwd.getText() || newPwd.getText().toString().isEmpty()){
			showDialog(this, "提示", "请输入新密码",0);
			return;
		}
		if(null == confirmPwd.getText() || confirmPwd.getText().toString().isEmpty()){
			showDialog(this, "提示", "请再次输入新密码",0);
			return;
		}
		
		String oldPwdStr = oldPwd.getText().toString();
		String oldPwdStr2 = sp.getString("password", "");
		
		if(!oldPwdStr2.equals(oldPwdStr)){
			showDialog(this, "提示", "旧密码错误,请重新输入",0);
			oldPwd.setFocusable(true);
			oldPwd.setFocusableInTouchMode(true);
			oldPwd.requestFocus();
			return;
		}
		
		String newPwdStr = newPwd.getText().toString();
		String confirmPwdStr = confirmPwd.getText().toString();
		
		if(!newPwdStr.equals(confirmPwdStr)){
			showDialog(this, "提示", "两次密码不一致",0);
			return;
		}

		TsysUserinfo tsysUserinfo = new TsysUserinfo();
		tsysUserinfo.setLoginName(sp.getString("username", ""));
		tsysUserinfo.setPassword(newPwdStr);
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
	        WeakReference<EditPwdActivity> mActivity;
	
	        MyHandler(EditPwdActivity theActivity) {
	            mActivity = new WeakReference<EditPwdActivity>(theActivity);
	        }
	
	        @Override
	        public void handleMessage(Message msg) {
	        	EditPwdActivity myActivity = mActivity.get();
	            switch (msg.what) {
	                case 1:
	                	if(null != result && ("1").equals(result)){
	                		showDialog(myActivity, "提示", "密码修改成功!",1);
	                	}else{
	                		showDialog(myActivity, "提示", "密码修改失败!",0);
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
		builder.setPositiveButton("确定", new OnClickListener() {
		        @Override
		        public void onClick(DialogInterface dialog, int which) {
		        	if(code == 1){
		        		Editor ed = sp.edit();
                		ed.putString("username", "");
        				ed.putString("password", "");
        				ed.putBoolean("keep", false);
        				ed.apply();
	            		finish();
		        		openActivity(LoginActivity.class, null);
		        	}
		        }
		});
		AlertDialog dialog = builder.create();
		dialog.show();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn_submit:
				updatePwd();
				break;
		}
	}
}
