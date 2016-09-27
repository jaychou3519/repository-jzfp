package com.demo.jzfp.activity;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.demo.jzfp.R;
import com.demo.jzfp.apdater.ReasonAdapter;
import com.demo.jzfp.utils.Constant;
import com.demo.jzfp.utils.MyApplication;

public class ReasonActivity extends BaseActivity implements OnClickListener {
	private String[] reasons;
	private static StringBuffer sb;
	private EditText et_illustrate;
	private GridView gv_checkbox;

	@Override
	protected void setView() {
		setContentView(R.layout.activity_reason);
		MyApplication activityList = (MyApplication) getApplicationContext();
		activityList.addActivity(this);
		setTitleText("致贫说明");
		setOnback(this);
	}

	@Override
	protected void initView() {
		TextView tv_save = (TextView) findViewById(R.id.tv_save);
		tv_save.setOnClickListener(this);
		gv_checkbox = (GridView) findViewById(R.id.gv_checkbox);
		et_illustrate = (EditText) findViewById(R.id.et_illustrate);
	}

	@Override
	protected void initData() {
		reasons = new String[] { "因学", "因灾", "缺资源、耕地", "因交通、电力等条件", "缺资金", "缺技术", "因病", "其 他", "缺劳动力" };
		if (!TextUtils.isEmpty(Constant.poor.getRemark()))
			et_illustrate.setText(Constant.poor.getRemark());

		sb = new StringBuffer(TextUtils.isEmpty(Constant.poor.getPoorReason()) ? "" : Constant.poor.getPoorReason());
		ReasonAdapter adapter = new ReasonAdapter(this, reasons, sb);
		gv_checkbox.setAdapter(adapter);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_save:
			Constant.poor.setPoorReason(sb.toString().trim() + "");
			Constant.poor.setRemark(et_illustrate.getText().toString().trim() + "");
			finish();
			break;
		}
	}
	
	public static void setXl(StringBuffer str){
		sb = str;
	}

}
