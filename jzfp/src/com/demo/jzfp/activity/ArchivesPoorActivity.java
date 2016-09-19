package com.demo.jzfp.activity;

import java.util.LinkedHashMap;
import java.util.List;
import com.alibaba.fastjson.JSON;
import com.demo.jzfp.R;
import com.demo.jzfp.apdater.ArchivesPoorAdapter;
import com.demo.jzfp.entity.CountryMans;
import com.demo.jzfp.utils.RequestWebService;
import com.demo.jzfp.utils.Tools;
import com.demo.jzfp.utils.RequestWebService.WebServiceCallback;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.os.Handler;
import android.view.View;
import android.widget.ListView;

public class ArchivesPoorActivity extends BaseActivity implements WebServiceCallback{

	@ViewInject(R.id.lv_listview)
	private ListView lv_listview;
	private String methodName = "selectToCountrymans";
	private List<CountryMans> countrys;
	@Override
	protected void setView() {
		View view = View.inflate(this, R.layout.activity_poor, null);
		setContentView(view);
		ViewUtils.inject(this,view);
		setTitleText("扶贫档案");
		setOnback(this);
	}

	@Override
	protected void initView() {

		
	}
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 201:
				adapter = new ArchivesPoorAdapter(ArchivesPoorActivity.this,countrys);
				lv_listview.setAdapter(adapter);
				break;

			default:
				break;
			}
		};
	};
	private ArchivesPoorAdapter adapter;
	@Override
	protected void initData() {
		LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
		linkedHashMap.put("arg0", getIntent().getExtras().getString("countrymanId"));
		RequestWebService.send(methodName, linkedHashMap, this, 101);
/*		RequestWebService.send("selectByAction", linkedHashMap, this, 102);
		RequestWebService.send("selectByResult", linkedHashMap, this, 103);
		Tools.i("JJY", getIntent().getExtras().getString("countrymanId"));*/
	}

	@Override
	public void result(String reulst, int requestCode) {
		if(reulst == null){
			Tools.showNewToast(getApplication(), "链接服务器失败");
		}else{
			switch (requestCode) {
			case 101:
				try {
	    			countrys = JSON.parseArray(reulst, CountryMans.class);
					handler.sendEmptyMessage(201);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case 102:
				try {
					Tools.i("selectByAction", reulst.toString());
					Tools.showNewToast(ArchivesPoorActivity.this, "selectByAction="+reulst.toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case 103:
				try {
					Tools.i("selectByResult", reulst.toString());
					Tools.showNewToast(ArchivesPoorActivity.this, "selectByResult="+reulst.toString());
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
