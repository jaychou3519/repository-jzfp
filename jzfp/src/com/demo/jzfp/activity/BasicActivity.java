package com.demo.jzfp.activity;

import java.util.ArrayList;
import java.util.List;

import com.demo.jzfp.R;
import com.demo.jzfp.apdater.BasicAdapter;
import com.demo.jzfp.apdater.EffectAdapter;
import com.demo.jzfp.entity.Basic;
import com.demo.jzfp.entity.Effect;
import com.demo.jzfp.utils.Tools;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.view.View;
import android.widget.ListView;

public class BasicActivity extends BaseActivity{

	@ViewInject(R.id.lv_listview)
	private ListView lv_listview;
	@ViewInject(R.id.lv_listview1)
	private ListView lv_listview1;
	private List<Basic> basics = new ArrayList<Basic>();
	private List<Effect> basics1 = new ArrayList<Effect>();
	private String[] string  = new String[]{"乡镇党委书记姓名","乡镇村","村书记","村主任"};
	private String[] string1 = new String[]{"责任单位","负责人","联系电话","结对帮扶责任人","职务","联系电话"};
	private String[] string2 = new String[]{"炎陵镇财务局","财神爷","22034567","周密","科员","13908488899"};
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
		BasicAdapter adapter = new BasicAdapter(this, basics);
		lv_listview.setAdapter(adapter);
		Tools.setListViewHeight(lv_listview);
		
		for(int i =0;i<string1.length;i++){
			Effect effect = new Effect();
			effect.setContent(string2[i]);
			effect.setTitle(string1[i]);
			basics1.add(effect);
		}
		EffectAdapter effectAdapter = new EffectAdapter(this, basics1);
		lv_listview1.setAdapter(effectAdapter);
		Tools.setListViewHeight(lv_listview1);
	}

	@Override
	protected void initData() {
		
	}

}
