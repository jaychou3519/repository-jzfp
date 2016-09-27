package com.demo.jzfp.activity;

import com.demo.jzfp.R;
import com.demo.jzfp.apdater.VillagesAdapter;
import com.demo.jzfp.utils.MyApplication;
import com.demo.jzfp.utils.Tools;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

public class VillagesTextActivity2 extends BaseActivity{

	@ViewInject(R.id.tv_context)
	private TextView tv_context;
	@ViewInject(R.id.tv_title_name)
	private TextView tv_title_name;
	@Override
	protected void setView() {
		View view = View.inflate(this, R.layout.activity_villages_text2, null);
		MyApplication.addActivity(VillagesTextActivity2.this);
		setContentView(view);
		ViewUtils.inject(this,view);
		setOnback(this);
		setTitleText("乡镇介绍");
	}

	@Override
	protected void initView() {
		String content  = getIntent().getExtras().getString("content");
		String title  = getIntent().getExtras().getString("title");
		if(!TextUtils.isEmpty(Tools.parseEmpty(content))){
			tv_context.setText(content);
		}else{
			tv_context.setText("没有相关介绍");
		}
		if(title!=null)
			tv_title_name.setText(title);
	}

	@Override
	protected void initData() {
	}

}
