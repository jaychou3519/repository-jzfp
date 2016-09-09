package com.demo.jzfp.activity;

import com.demo.jzfp.R;
import com.demo.jzfp.apdater.ArchivesPoorAdapter;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.view.View;
import android.widget.ListView;

public class ArchivesPoorActivity extends BaseActivity{

	@ViewInject(R.id.lv_listview)
	private ListView lv_listview;
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
		ArchivesPoorAdapter adapter = new ArchivesPoorAdapter(this);
		lv_listview.setAdapter(adapter);
	}

	@Override
	protected void initData() {
		
	}

}
