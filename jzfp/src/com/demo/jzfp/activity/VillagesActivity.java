package com.demo.jzfp.activity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.demo.jzfp.R;
import com.demo.jzfp.apdater.VillagesAdapter;
import com.demo.jzfp.dao.AreaDataDao;
import com.demo.jzfp.dao.impl.AreaDataDaoImpl;
import com.demo.jzfp.database.DatabaseHelper;
import com.demo.jzfp.entity.Policy;
import com.demo.jzfp.entity.TdataCountryman;
import com.demo.jzfp.entity.Villages;
import com.demo.jzfp.service.IAppService;
import com.demo.jzfp.utils.MyApplication;
import com.demo.jzfp.utils.RequestWebService;
import com.demo.jzfp.utils.Tools;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.demo.jzfp.utils.RequestWebService.WebServiceCallback;

import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class VillagesActivity extends BaseActivity implements WebServiceCallback{

	private ListView lv_listview;
	private List<Villages> villages = new ArrayList<Villages>();
	private AreaDataDao areaDataDao = new AreaDataDaoImpl();
	private SQLiteDatabase db = null;
	private List<Map> maps;
	private int number = 0;
	@ViewInject(R.id.rl_jindu)
	private RelativeLayout rl_jindu;
	
	@Override
	protected void setView() {
		View view = View.inflate(this, R.layout.activity_villages, null);
		setContentView(view);
		ViewUtils.inject(this,view);
		MyApplication.addActivity(VillagesActivity.this);
		lv_listview = (ListView) findViewById(R.id.lv_listview);
		setTitleText("乡镇介绍");
		setOnback(this);
	}

	@Override
	protected void initView() {
		handler.sendEmptyMessage(1);
	}

	@Override
	protected void initData() {
		db = (new DatabaseHelper(this)).getWritableDatabase();
		maps = areaDataDao.queryAreaData(db);
		number = 0;
		for (int i = 0; i < maps.size(); i++) {
			if(!maps.get(i).get("name").toString().contains("村")){
				LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
				linkedHashMap.put("arg0", maps.get(i).get("id")+"");
				RequestWebService.send("selectorgRemark", linkedHashMap, this, 101);
				number++;
			}
		}
	}

	@Override
	public void result(String reulst, int requestCode) {
		if(reulst == null){
			Tools.showNewToast(getApplication(), "链接服务器失败");
		}else{
			Tools.i("selectorgRemark", reulst.toString());
		}
		Villages village = JSON.parseObject(reulst, Villages.class);
		villages.add(village);
		if(villages.size()==number){
			VillagesAdapter adapter = new VillagesAdapter(VillagesActivity.this,  VillagesAdapter.VILLAGES,villages);
			lv_listview.setAdapter(adapter);
			handler.sendEmptyMessage(2);
		}
	}

	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1:
				rl_jindu.setVisibility(View.VISIBLE);
				break;
			case 2:
				rl_jindu.setVisibility(View.GONE);
				break;
			}
		};
	};
}
