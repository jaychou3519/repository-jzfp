package com.demo.jzfp.activity;

import java.util.ArrayList;
import java.util.List;

import com.demo.jzfp.R;
import com.demo.jzfp.apdater.BasicAdapter;
import com.demo.jzfp.entity.Basic;
import com.lidroid.xutils.ViewUtils;

import android.view.View;
import android.widget.ListView;

public class BasicActivity extends BaseActivity{

	private ListView lv_listview;
	private List<Basic> basics = new ArrayList<Basic>();
	private String[] string  = new String[]{"乡镇党委书记姓名","乡镇村","村书记","村主任"};
	@Override
	protected void setView() {
		View view = View.inflate(this, R.layout.activity_basic, null);
		setContentView(view);
		ViewUtils.inject(this,view);
		setTitleText("帮扶基础信息");
		setOnback(this);
	}

	@Override
	protected void initView() {
		for(int i=0;i<string.length;i++){
			Basic basic = new Basic();
			basic.setName_title(string[i]);
			basics.add(basic);
		}
		lv_listview = (ListView) findViewById(R.id.lv_listview);
		BasicAdapter adapter = new BasicAdapter(this, basics);
		lv_listview.setAdapter(adapter);
	}

	@Override
	protected void initData() {
		
	}

}
