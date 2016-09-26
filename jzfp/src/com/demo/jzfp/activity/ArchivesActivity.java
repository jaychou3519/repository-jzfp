package com.demo.jzfp.activity;

import java.util.LinkedHashMap;
import java.util.List;
import com.alibaba.fastjson.JSON;
import com.demo.jzfp.R;
import com.demo.jzfp.apdater.ArchivesAdapter;
import com.demo.jzfp.entity.ToFiles;
import com.demo.jzfp.utils.MyApplication;
import com.demo.jzfp.utils.RequestWebService;
import com.demo.jzfp.utils.RequestWebService.WebServiceCallback;
import com.demo.jzfp.utils.Tools;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ListView;

public class ArchivesActivity extends BaseActivity implements WebServiceCallback{
	
	private String TAG = "ArchivesActivity";
	private MyApplication activityList;
	private ArchivesAdapter adapter;
	private String methodName = "selectToFiles";
	private List<ToFiles> toFiles;
	@ViewInject(R.id.lv_listview)
	private ListView lv_listview;
	@Override
	protected void setView() {
		View view = View.inflate(ArchivesActivity.this, R.layout.activity_archives, null);
		setContentView(view);
		activityList = (MyApplication) getApplicationContext();
		activityList.addActivity(this);
		ViewUtils.inject(this,view);
		setTitleText("扶贫档案");
		setOnback(this);
	}

	@Override
	protected void initView() {
		LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
		if(MyApplication.login!=null){
			linkedHashMap.put("arg0", MyApplication.login.getOrgId()+"");
		}else{
			SharedPreferences sp = getSharedPreferences("user", Context.MODE_PRIVATE);
			linkedHashMap.put("arg0", sp.getString("orgId", "")+"");
		}
		RequestWebService.send(methodName, linkedHashMap, this, 101);
	}

	@Override
	protected void initData() {
		
	}

	@OnClick({R.id.ll_other,R.id.ll_location})
	public void mClick(View view){
		switch (view.getId()) {
		case R.id.ll_other:
			
			break;
		case R.id.ll_location:
			//openResultActivity(ChooseAreaActivity.class,null);
			break;

		default:
			break;
		}
	}
	
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 201:
				adapter = new ArchivesAdapter(ArchivesActivity.this,toFiles);
				lv_listview.setAdapter(adapter);
				break;

			default:
				break;
			}
		};
	};
	/**
	 * 跳转到其它Activity
	 * 
	 * @param pClass
	 * @param pBundle
	 */
	public void openResultActivity(Class<?> pClass, Bundle pBundle) {
		Intent intent = new Intent(this, pClass);
		if (pBundle != null) {
			intent.putExtras(pBundle);
		}
		startActivityForResult(intent, 101);
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data){
		switch (requestCode) {
		case 101:
			Bundle bundle = data.getExtras();        
	        if(null  != bundle){
	        	 String name = bundle.getString("name");
	        }
			break;
		default:
			break;
		}
	}

	@Override
	public void result(String reulst, int requestCode) {
		if(reulst == null){
			Tools.showNewToast(getApplication(), "链接服务器失败");
		}else{
    		try {
    			Tools.i(TAG, "ArchivesActivity="+reulst.toString());
				toFiles = JSON.parseArray(reulst, ToFiles.class);
				handler.sendEmptyMessage(201);
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
	}
}
