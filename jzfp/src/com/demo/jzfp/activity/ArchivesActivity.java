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
