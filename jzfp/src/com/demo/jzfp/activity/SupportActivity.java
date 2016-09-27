package com.demo.jzfp.activity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.demo.jzfp.R;
import com.demo.jzfp.apdater.VillagesAdapter;
import com.demo.jzfp.entity.Policy;
import com.demo.jzfp.utils.MyApplication;
import com.demo.jzfp.utils.RequestWebService;
import com.demo.jzfp.utils.Tools;
import com.demo.jzfp.utils.RequestWebService.WebServiceCallback;

import android.view.View;
import android.widget.ListView;

public class SupportActivity extends BaseActivity implements WebServiceCallback {
	private ListView lv_listview;
	private List<String> datas = new ArrayList<String>();
	private List<Policy> policies;

	@Override
	protected void setView() {
		View view = View.inflate(this, R.layout.activity_villages, null);
		setContentView(view);
		MyApplication.addActivity(SupportActivity.this);
		lv_listview = (ListView) findViewById(R.id.lv_listview);
		setTitleText("帮扶政策");
		setOnback(this);
	}

	@Override
	protected void initView() {
		LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
		RequestWebService.send("selectNewsList", linkedHashMap, this, 101);
	}

	@Override
	protected void initData() {
		datas.add("结对帮扶干部职责");
		datas.add("精准扶贫新型农业经营主体贷款...");
		datas.add("中办国办印发《关于建立贫困退...");
		datas.add("湖南省农村扶贫开发条例");

	}

	@Override
	public void result(String reulst, int requestCode) {
		if (reulst == null) {
			Tools.showNewToast(this, "为空");
			return;
		}
		policies = JSON.parseArray(reulst, Policy.class);
		VillagesAdapter adapter = new VillagesAdapter(SupportActivity.this, policies, VillagesAdapter.SUPPORT);
		lv_listview.setAdapter(adapter);
	}

}
