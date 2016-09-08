package com.demo.jzfp.activity;

import com.demo.jzfp.R;
import com.demo.jzfp.apdater.ArchivesAdapter;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.event.OnClick;

import android.view.View;
import android.widget.ListView;

public class ArchivesActivity extends BaseActivity{

	@Override
	protected void setView() {
		View view = View.inflate(ArchivesActivity.this, R.layout.activity_archives, null);
		setContentView(view);
		ViewUtils.inject(this,view);
		setTitleText("扶贫档案");
		setOnback(this);
	}

	@Override
	protected void initView() {
		ListView listView  = (ListView) findViewById(R.id.lv_listview);
		ArchivesAdapter adapter = new ArchivesAdapter(ArchivesActivity.this);
		listView.setAdapter(adapter);
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
}
