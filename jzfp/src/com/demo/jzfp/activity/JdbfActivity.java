package com.demo.jzfp.activity;

import java.util.Arrays;
import java.util.Calendar;

import android.app.AlertDialog;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.demo.jzfp.R;
import com.demo.jzfp.dao.DictDataInfoDao;
import com.demo.jzfp.dao.impl.DictDataInfoDaoImpl;
import com.demo.jzfp.database.DatabaseHelper;
import com.demo.jzfp.entity.TdataHelper;
import com.demo.jzfp.entity.TdataResult;
import com.demo.jzfp.utils.Constant;
import com.demo.jzfp.utils.MyApplication;
import com.demo.jzfp.view.DoubleDatePickerDialog;
import com.demo.jzfp.view.WheelView;

public class JdbfActivity extends BaseActivity implements OnClickListener {
	private AlertDialog dialog;
	private EditText  et_organization, et_name, et_tel;  /*, et_houseSafe, , */
	private EditText et_dwsjName,et_dwsjPhone, et_zzName, et_zzPhone, et_csjName, et_csjPhone, et_czrName, et_czrPhone,et_name_job,et_Captain,et_Village;

	@Override
	protected void setView() {
		setContentView(R.layout.activity_jdbf);
		MyApplication.addActivity(JdbfActivity.this);
		setTitleText("结对帮扶");
		setOnback(this);
	}

	@Override
	protected void initView() {
		TextView tv_save = (TextView) findViewById(R.id.tv_save);
		tv_save.setOnClickListener(this);
		et_organization = (EditText) findViewById(R.id.et_organization);//请填写结对帮扶单位
		et_name = (EditText) findViewById(R.id.et_name); //请填写帮扶责任人
		et_name_job = (EditText) findViewById(R.id.et_name_job); //请填写帮扶责任人职务
		et_tel = (EditText) findViewById(R.id.et_tel);//请填写联系电话
		et_Captain = (EditText) findViewById(R.id.et_Captain);//请填写驻村队长信息
		et_Village = (EditText) findViewById(R.id.et_Village);//请填写队长联系电话
		et_dwsjName = (EditText) findViewById(R.id.et_dwsjName);
		et_dwsjPhone = (EditText) findViewById(R.id.et_dwsjPhone);
		et_zzName = (EditText) findViewById(R.id.et_zzName);//请填写乡（镇）长姓名
		et_zzPhone = (EditText) findViewById(R.id.et_zzPhone);//请填写乡（镇）长电话
		et_csjName = (EditText) findViewById(R.id.et_csjName);//请填写村书记姓名
		et_csjPhone = (EditText) findViewById(R.id.et_csjPhone);//请填写村书记电话
		et_czrName = (EditText) findViewById(R.id.et_czrName);//请填写村主任姓名
		et_czrPhone = (EditText) findViewById(R.id.et_czrPhone);//请填写村主任电话
	}

	@Override
	protected void initData() {
		if (Constant.poor.getTdataHelper() != null) {
			if (!TextUtils.isEmpty(Constant.poor.getTdataHelper().getJdbfzrOrgname()))
				et_organization.setText(Constant.poor.getTdataHelper().getJdbfzrOrgname());
			if (!TextUtils.isEmpty(Constant.poor.getTdataHelper().getJdbfzrOrger()))
				et_name.setText(Constant.poor.getTdataHelper().getJdbfzrOrger());
			if (!TextUtils.isEmpty(Constant.poor.getTdataHelper().getJdbfzrOrgname()))
				et_name_job.setText(Constant.poor.getTdataHelper().getJdbfzrOrgname());
			if (!TextUtils.isEmpty(Constant.poor.getTdataHelper().getBfzrrPhone()))
				et_tel.setText(Constant.poor.getTdataHelper().getBfzrrPhone());
			if (!TextUtils.isEmpty(Constant.poor.getTdataHelper().getDwsjName()))
				et_dwsjName.setText(Constant.poor.getTdataHelper().getDwsjName());
			if (!TextUtils.isEmpty(Constant.poor.getTdataHelper().getDwsjPhone()))
				et_dwsjPhone.setText(Constant.poor.getTdataHelper().getDwsjPhone());
			if (!TextUtils.isEmpty(Constant.poor.getTdataHelper().getZzName()))
				et_zzName.setText(Constant.poor.getTdataHelper().getZzName());
			if (!TextUtils.isEmpty(Constant.poor.getTdataHelper().getZzPhone()))
				et_zzPhone.setText(Constant.poor.getTdataHelper().getZzPhone());
			if (!TextUtils.isEmpty(Constant.poor.getTdataHelper().getCsjName()))
				et_csjName.setText(Constant.poor.getTdataHelper().getCsjName());
			if (!TextUtils.isEmpty(Constant.poor.getTdataHelper().getCxjPhone()))
				et_csjPhone.setText(Constant.poor.getTdataHelper().getCxjPhone());
			if (!TextUtils.isEmpty(Constant.poor.getTdataHelper().getCzrName()))
				et_czrName.setText(Constant.poor.getTdataHelper().getCzrName());
			if (!TextUtils.isEmpty(Constant.poor.getTdataHelper().getCzrPhone()))
				et_czrPhone.setText(Constant.poor.getTdataHelper().getCzrPhone());
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_save:
			setData();
			finish();
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
			}
		});
		dialog = new AlertDialog.Builder(this).setTitle(title).setView(outerView).show();

		TextView txtSure = (TextView) outerView.findViewById(R.id.txt_sure);
		TextView txtCancle = (TextView) outerView.findViewById(R.id.txt_cancel);
		txtSure.setOnClickListener(this);
		txtCancle.setOnClickListener(this);
	}

	/**
	 * 保存帮扶成效信息
	 */
	private void setData() {
		TdataResult result = Constant.poor.getTdataResult();
		if(result==null){
			result = new TdataResult();
		}
		result.setzcgjddz(et_Captain.getText().toString().trim()+"");
		//result.setzcgjddz(et_Village.getText().toString().trim()+"");
		Constant.poor.setTdataResult(result);
		
		TdataHelper helper = new TdataHelper();
		helper.setJdbfzrOrgname(et_organization.getText().toString().trim() + "");
		helper.setJdbfzrOrger(et_name.getText().toString().trim() + "");
		helper.setBfzrrPhone(et_tel.getText().toString().trim() + "");
		helper.setDwsjName(et_dwsjName.getText().toString().trim()+"");
		helper.setDwsjPhone(et_dwsjPhone.getText().toString().trim()+"");
		helper.setZzName(et_zzName.getText().toString().trim()+"");
		helper.setZzPhone(et_zzPhone.getText().toString().trim()+"");
		helper.setCsjName(et_csjName.getText().toString().trim()+"");
		helper.setCxjPhone(et_csjName.getText().toString().trim()+"");
		helper.setCzrName(et_czrName.getText().toString().trim()+"");
		helper.setCzrPhone(et_czrPhone.getText().toString().trim()+"");
		Constant.poor.setTdataHelper(helper);
	}

}
