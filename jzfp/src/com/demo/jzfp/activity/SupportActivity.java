package com.demo.jzfp.activity;

import java.util.ArrayList;
import java.util.List;
import com.demo.jzfp.R;
import com.demo.jzfp.apdater.VillagesAdapter;
import android.view.View;
import android.widget.ListView;

public class SupportActivity extends BaseActivity{
	private ListView lv_listview;
	private List<String> datas = new ArrayList<String>();
	@Override
	protected void setView() {
		View view = View.inflate(this, R.layout.activity_villages, null);
		setContentView(view);
		lv_listview = (ListView) findViewById(R.id.lv_listview);
		setTitleText("帮扶政策");
		setOnback(this);
	}

	@Override
	protected void initView() {
		
	}

	@Override
	protected void initData() {
		datas.add("结对帮扶干部职责");
		datas.add("精准扶贫新型农业经营主体贷款...");
		datas.add("中办国办印发《关于建立贫困退...");
		datas.add("湖南省农村扶贫开发条例");
		VillagesAdapter adapter = new VillagesAdapter(SupportActivity.this, datas,VillagesAdapter.SUPPORT);
		lv_listview.setAdapter(adapter);
	}

}
