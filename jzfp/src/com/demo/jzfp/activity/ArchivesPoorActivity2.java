package com.demo.jzfp.activity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import com.alibaba.fastjson.JSON;
import com.demo.jzfp.R;
import com.demo.jzfp.apdater.ArchivesPoorAdapter2;
import com.demo.jzfp.entity.CountryMans;
import com.demo.jzfp.utils.MyApplication;
import com.demo.jzfp.utils.RequestWebService;
import com.demo.jzfp.utils.Tools;
import com.demo.jzfp.utils.RequestWebService.WebServiceCallback;
import com.demo.jzfp.view.MyGridView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

public class ArchivesPoorActivity2 extends BaseActivity implements WebServiceCallback{

	private String TAG = "ArchivesPoorActivity2";
	@ViewInject(R.id.gv_photo)
	private MyGridView gv_photo;
	private List<CountryMans> countrys = new ArrayList<CountryMans>();
	private ArchivesPoorAdapter2 adapter;
	@Override
	protected void setView() {
		View view = View.inflate(this, R.layout.activity_poor, null);
		setContentView(view);
		MyApplication.addActivity(this);
		ViewUtils.inject(this,view);
		setTitleText("基本信息");
		setOnback(this);
	}

	@Override
	protected void initView() {

		
	}
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 201:
				Tools.i("ArchivesPoorActivity", "数量="+countrys.size());
				adapter = new ArchivesPoorAdapter2(ArchivesPoorActivity2.this,countrys);
				gv_photo.setAdapter(adapter);
				break;

			default:
				break;
			}
		};
	};

	@Override
	protected void initData() {
		Bundle bundle = getIntent().getExtras();
		int countid = bundle.getInt("countid", -1);
		if(countid==-1){
		}else if(countid==1||countid==2){
			if(bundle.getBoolean("state")){
				LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
				linkedHashMap.put("arg0", bundle.getString("countrymanId"));
				linkedHashMap.put("arg1", countid+"");
				RequestWebService.send("selectFpcxdetail", linkedHashMap, this, 102);
				Tools.i(TAG, "接口=selectFpcxdetail"+"  arg0="+bundle.getString("countrymanId")+"  arg1="+countid);
			}else{
				countrys =  (List<CountryMans>) bundle.getSerializable("countrys");
				if(countrys.size()>0){
					adapter = new ArchivesPoorAdapter2(ArchivesPoorActivity2.this,countrys);
					gv_photo.setAdapter(adapter);
				}
			}
		}
	}

	@Override
	public void result(String reulst, int requestCode) {
		if(reulst == null){
			Tools.showNewToast(getApplication(), "链接服务器失败");
		}else{
			switch (requestCode) {
			case 102:
				try {
					Tools.i(TAG, reulst.toString());
	    			countrys = JSON.parseArray(reulst, CountryMans.class);
					handler.sendEmptyMessage(201);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			default:
				break;
			}
    		
    	}
	}

}
