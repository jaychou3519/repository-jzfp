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
import com.demo.jzfp.utils.Tools;
import com.demo.jzfp.view.WheelView;

public class EconomyActivity extends BaseActivity implements OnClickListener {

	private AlertDialog dialog;
	private String construction;
	private TextView tv_construction;
	private String[] constructions;
	private EditText et_area, et_land, et_hill, et_dominate;

	@Override
	protected void setView() {
		setContentView(R.layout.activity_ecomomy);
		MyApplication activityList = (MyApplication) getApplicationContext();
		activityList.addActivity(this);
		setTitleText("家庭经济");
		setOnback(this);
	}

	@Override
	protected void initView() {
		TextView tv_save = (TextView) findViewById(R.id.tv_save);
		RelativeLayout rl_construction = (RelativeLayout) findViewById(R.id.rl_construction);
		tv_construction = (TextView) findViewById(R.id.tv_construction);
		et_area = (EditText) findViewById(R.id.et_area);
		et_land = (EditText) findViewById(R.id.et_land);
		et_hill = (EditText) findViewById(R.id.et_hill);
		et_dominate = (EditText) findViewById(R.id.et_dominate);

		tv_save.setOnClickListener(this);
		rl_construction.setOnClickListener(this);
	}

	@Override
	protected void initData() {
		constructions = new String[] { "土坯", "砖瓦"};
		if (!TextUtils.isEmpty(Constant.poor.getZfjg()))
			tv_construction.setText(Constant.poor.getZfjg());
		if (!TextUtils.isEmpty(Constant.poor.getZzArea()))
			et_area.setText(Constant.poor.getZzArea());
		if (!TextUtils.isEmpty(Constant.poor.getGdArea()))
			et_land.setText(Constant.poor.getGdArea());
		if (!TextUtils.isEmpty(Constant.poor.getSlArea()))
			et_hill.setText(Constant.poor.getSlArea());
		if (!TextUtils.isEmpty(Constant.poor.getRjsrqk()))
			et_dominate.setText(Constant.poor.getRjsrqk());

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_save:
			if (TextUtils.isEmpty(tv_construction.getText().toString())) {
				Tools.showNewToast(this, "请选择住房结构");
				return;
			} else if (TextUtils.isEmpty(et_area.getText().toString())) {
				Tools.showNewToast(this, "请填写住房面积");
				return;
			} else if (TextUtils.isEmpty(et_land.getText().toString())) {
				Tools.showNewToast(this, "请填写耕地面积");
				return;
			} else if (TextUtils.isEmpty(et_hill.getText().toString())) {
				Tools.showNewToast(this, "请填写山林面积");
				return;
			} else if (TextUtils.isEmpty(et_dominate.getText().toString())) {
				Tools.showNewToast(this, "请填写可支配收入");
				return;
			}
			Constant.poor.setZfjg(tv_construction.getText().toString().trim()+"");
			Constant.poor.setZzArea(et_area.getText().toString().trim()+"");
			Constant.poor.setGdArea(et_land.getText().toString().trim()+"");
			Constant.poor.setSlArea(et_hill.getText().toString().trim()+"");
			Constant.poor.setRjsrqk(et_dominate.getText().toString().trim()+"");
			finish();
			break;
		case R.id.rl_construction:
			showWheelView("请选择住房结构", constructions, 0);
			break;
		case R.id.txt_sure:
			if(TextUtils.isEmpty(construction))
				construction = constructions[1];
			tv_construction.setText(construction);
			construction = "";
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
				construction = item;
			}
		});
		dialog = new AlertDialog.Builder(this).setTitle(title).setView(outerView).show();

		TextView txtSure = (TextView) outerView.findViewById(R.id.txt_sure);
		TextView txtCancle = (TextView) outerView.findViewById(R.id.txt_cancel);
		txtSure.setOnClickListener(this);
		txtCancle.setOnClickListener(this);
	}

	
	
}
