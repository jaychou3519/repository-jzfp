package com.demo.jzfp.activity;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.demo.jzfp.R;
import com.demo.jzfp.entity.TsysUserinfo;
import com.demo.jzfp.utils.MyApplication;
import com.demo.jzfp.utils.WebServiceClient;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;


public class RegisterActivity extends BaseActivity {

	@ViewInject(R.id.ed_username)
	private EditText ed_username;
	@ViewInject(R.id.ed_password)
	private EditText ed_password;
	@ViewInject(R.id.tv_institution)
	private TextView tv_institution;
	@ViewInject(R.id.ed_realname)
	private TextView ed_realname;
	private MyApplication activityList;
	private String name;
	private String areacode;
	private String result;
	private static String METHOD_NAME = "insertTsysUserinfo";
	
	
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
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();        
        if(null  != bundle){
        	 name = bundle.getString("name");
             areacode = bundle.getString("areacode");
     		 tv_institution.setText(name);
     		 ed_username.setText(bundle.getString("ed_username"));
     		 ed_password.setText(bundle.getString("ed_password"));
     		 ed_realname.setText(bundle.getString("ed_realname"));
        }
	}

	@OnClick({R.id.ll_institution,R.id.btn_register})
	public void mClick(View view){
		switch (view.getId()) {
		case R.id.ll_institution:
		    Bundle bundle=new Bundle();
		    if(null != ed_username.getText()){
		    	 bundle.putString("ed_username", ed_username.getText().toString());
		    }
		    if(null != ed_password.getText()){
		    	 bundle.putString("ed_password", ed_password.getText().toString());
		    }
		    if(null != ed_realname.getText()){
		    	 bundle.putString("ed_realname", ed_realname.getText().toString());
		    }
			openActivity(ChooseAreaActivity.class, bundle);
			break;
		case R.id.btn_register:
			registeUser();
			break;
		}
	}
	
	
	public void registeUser(){
		TsysUserinfo tsysUserinfo = new TsysUserinfo();
		if(null != ed_username.getText() && !ed_username.getText().toString().isEmpty()){
			tsysUserinfo.setLoginName(ed_username.getText().toString());
		}else{
			showDialog(this, "提示", "用户名不能为空!",0);
			return;
		}
		if(null != ed_password.getText() && !ed_password.getText().toString().isEmpty()){
			tsysUserinfo.setPassword(ed_password.getText().toString());
		}else{
			showDialog(this, "提示", "密码不能为空!",0);
			return;
		}
		if(null != areacode && !areacode.isEmpty()){
			tsysUserinfo.setOrgId(areacode);
		}else{
			showDialog(this, "提示", "机构不能为空!",0);
			return;
		}
		if(null != ed_realname.getText() && !ed_realname.getText().toString().isEmpty()){
			tsysUserinfo.setUserRealname(ed_realname.getText().toString());
		}else{
			showDialog(this, "提示", "真实姓名不能为空!",0);
		}
		tsysUserinfo.setState("1");
		Map map = new HashMap();
		String jsonuserinfo = JSON.toJSONString(tsysUserinfo);
		map.put("arg0",jsonuserinfo);
		callWeb(map);
	}
	
	MyHandler myHandler = new MyHandler(this);
	
	public void callWeb(final Map params){
        new Thread(new Runnable() {
            @Override
            public void run() {
                result =WebServiceClient.callWeb(METHOD_NAME, params);
                myHandler.sendEmptyMessage(1);
            }
        }).start();
    }
	
	class MyHandler extends Handler{
        WeakReference<RegisterActivity> mActivity;

        MyHandler(RegisterActivity theActivity) {
            mActivity = new WeakReference<RegisterActivity>(theActivity);
        }

        @Override
        public void handleMessage(Message msg) {
        	RegisterActivity myActivity = mActivity.get();
            switch (msg.what) {
                case 1:
                	if(null != result && ("0").equals(result)){
                		showDialog(myActivity, "提示", "服务端异常，注册失败",0);
                	}else if(null != result && ("1").equals(result)){
                		showDialog(myActivity, "提示", "注册成功",1);
                	}else if(null != result && ("2").equals(result)){
                		showDialog(myActivity, "提示", "用户已存在",0);
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
		        		openActivity(LoginActivity.class, null);
		        	}
		        }
		});
		AlertDialog dialog = builder.create();
		dialog.show();
	}
}
