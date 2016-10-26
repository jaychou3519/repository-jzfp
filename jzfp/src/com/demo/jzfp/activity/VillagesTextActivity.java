package com.demo.jzfp.activity;

import com.demo.jzfp.R;
import com.demo.jzfp.apdater.VillagesAdapter;
import com.demo.jzfp.utils.MyApplication;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;

public class VillagesTextActivity extends BaseActivity{

	@ViewInject(R.id.tv_context)
	private WebView tv_context;
	@Override
	protected void setView() {
		View view = View.inflate(this, R.layout.activity_villages_text, null);
		MyApplication.addActivity(VillagesTextActivity.this);
		setContentView(view);
		ViewUtils.inject(this,view);
		setOnback(this);
		int state  = getIntent().getExtras().getInt("villages");
		if(state==VillagesAdapter.SUPPORT){
			setTitleText("政策与公告");
		}else{
			setTitleText("乡镇介绍");
		}
		
	}

	@Override
	protected void initView() {
		String url = getIntent().getExtras().getString("url");
		if(!TextUtils.isEmpty(url))
			tv_context.loadUrl(url);
		
	}

	@Override
	protected void initData() {
	}

}
