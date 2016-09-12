package com.demo.jzfp.activity;

import java.util.Arrays;

import android.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.demo.jzfp.R;
import com.demo.jzfp.utils.Constant;
import com.demo.jzfp.utils.MyApplication;
import com.demo.jzfp.view.WheelView;

public class ReasonActivity extends BaseActivity implements OnClickListener {
	private AlertDialog dialog;
	private String[] reasons;
	private TextView tv_reason;
	private String reason = "";
	private EditText et_illustrate;

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

		RelativeLayout rl_reason = (RelativeLayout) findViewById(R.id.rl_reason);
		tv_reason = (TextView) findViewById(R.id.tv_reason);

		et_illustrate = (EditText) findViewById(R.id.et_illustrate);

		tv_save.setOnClickListener(this);
		rl_reason.setOnClickListener(this);
	}

	@Override
	protected void initData() {
		reasons = new String[] { "因学", "因灾", "缺资源、耕地", "因交通、电力等条件", "缺资金", "缺技术", "因病", "其 他", "缺劳动力" };
		if(!TextUtils.isEmpty(Constant.poor.getPoorReason()))
			tv_reason.setText(Constant.poor.getPoorReason());
		if(!TextUtils.isEmpty(Constant.poor.getReasonIllustrate()))
			et_illustrate.setText(Constant.poor.getReasonIllustrate());	
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_save:
			Constant.poor.setPoorReason(tv_reason.getText().toString().trim()+"");
			Constant.poor.setReasonIllustrate(et_illustrate.getText().toString().trim()+"");
			finish();
			break;
		case R.id.rl_reason:
			showWheelView("请选择致贫原因", reasons, 0);
			break;
		case R.id.txt_sure:
			if(TextUtils.isEmpty(reason))
				reason = reasons[1];
			tv_reason.setText(reason);
			reason = "";
			dialog.dismiss();
			break;
		case R.id.txt_cancel:
			dialog.dismiss();
			break;
		}
	}

	/**
	 * 选择弹出窗
	 * 
	 * @param title
	 *            弹出窗标题
	 * @param strs
	 *            弹出窗选择内容
	 * @param table
	 *            弹出窗标记
	 */
	private void showWheelView(String title, String[] strs, final int table) {
		View outerView = LayoutInflater.from(this).inflate(R.layout.wheel_view, null);
		WheelView wv = (WheelView) outerView.findViewById(R.id.wheel_view_wv);
		wv.setOffset(1);
		wv.setItems(Arrays.asList(strs));
		wv.setSeletion(1);
		wv.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
			@Override
			public void onSelected(int selectedIndex, String item) {
				reason = item;
			}
		});
		dialog = new AlertDialog.Builder(this).setTitle(title).setView(outerView).show();

		TextView txtSure = (TextView) outerView.findViewById(R.id.txt_sure);
		TextView txtCancle = (TextView) outerView.findViewById(R.id.txt_cancel);
		txtSure.setOnClickListener(this);
		txtCancle.setOnClickListener(this);
	}

}
