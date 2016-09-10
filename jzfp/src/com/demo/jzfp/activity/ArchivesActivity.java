package com.demo.jzfp.activity;

import java.util.LinkedHashMap;

import com.demo.jzfp.R;
import com.demo.jzfp.apdater.ArchivesAdapter;
import com.demo.jzfp.utils.MyApplication;
import com.demo.jzfp.utils.RequestWebService;
import com.demo.jzfp.utils.RequestWebService.WebServiceCallback;
import com.demo.jzfp.utils.Tools;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.event.OnClick;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class ArchivesActivity extends BaseActivity implements WebServiceCallback{
	
	private String TAG = "ArchivesActivity";
	private MyApplication activityList;
	private ArchivesAdapter adapter;
	private String methodName = "selectToFiles";
	
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
		ListView listView  = (ListView) findViewById(R.id.lv_listview);
		adapter = new ArchivesAdapter(this);
		listView.setAdapter(adapter);
		
		LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
		linkedHashMap.put("arg0", "412500005003");
		RequestWebService.send(methodName, linkedHashMap, this, 101);
		RequestWebService.send("selectToCountrymans", linkedHashMap, this, 101);
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
			openResultActivity(ChooseAreaActivity.class,null);
			break;

		default:
			break;
		}
	}
	
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
		if(reulst==null){ 
			Tools.showNewToast(this, "为空");
			return;
		}
		Tools.i(TAG, reulst.toString());
	}
}
