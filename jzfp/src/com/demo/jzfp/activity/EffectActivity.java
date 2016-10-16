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

public class EffectActivity extends BaseActivity implements OnClickListener {
	private AlertDialog dialog;
	private String[] conditions;
	private String condition;
	private TextView tv_time, tv_condition;
	private EditText et_income, et_allIncome, et_houseSafe, et_medical, et_education, et_organization, et_name, et_tel, et_helpMan, et_Captain, et_Village;
	private EditText et_dwsjName,et_dwsjPhone, et_zzName, et_zzPhone, et_csjName, et_csjPhone, et_czrName, et_czrPhone;
	private SQLiteDatabase db = null;
	private DictDataInfoDao dictDataDao = new DictDataInfoDaoImpl();

	@Override
	protected void setView() {
		setContentView(R.layout.activity_effect);
		MyApplication.addActivity(EffectActivity.this);
		setTitleText("帮扶成效");
		setOnback(this);
	}

	@Override
	protected void initView() {
		TextView tv_save = (TextView) findViewById(R.id.tv_save);

		RelativeLayout rl_time = (RelativeLayout) findViewById(R.id.rl_time);
		tv_time = (TextView) findViewById(R.id.tv_time);

		et_income = (EditText) findViewById(R.id.et_income);
		et_allIncome = (EditText) findViewById(R.id.et_allIncome);
		et_houseSafe = (EditText) findViewById(R.id.et_houseSafe);
		et_medical = (EditText) findViewById(R.id.et_medical);
		et_education = (EditText) findViewById(R.id.et_education);
		et_Captain = (EditText) findViewById(R.id.et_Captain);
		et_Village = (EditText) findViewById(R.id.et_Village);
		
		RelativeLayout rl_condition = (RelativeLayout) findViewById(R.id.rl_condition);
		tv_condition = (TextView) findViewById(R.id.tv_condition);

		et_organization = (EditText) findViewById(R.id.et_organization);
		et_name = (EditText) findViewById(R.id.et_name);
		et_tel = (EditText) findViewById(R.id.et_tel);
		et_helpMan = (EditText) findViewById(R.id.et_helpMan);
		et_dwsjName = (EditText) findViewById(R.id.et_dwsjName);
		et_dwsjPhone = (EditText) findViewById(R.id.et_dwsjPhone);
		et_zzName = (EditText) findViewById(R.id.et_zzName);
		et_zzPhone = (EditText) findViewById(R.id.et_zzPhone);
		et_csjName = (EditText) findViewById(R.id.et_csjName);
		et_csjPhone = (EditText) findViewById(R.id.et_csjPhone);
		et_czrName = (EditText) findViewById(R.id.et_czrName);
		et_czrPhone = (EditText) findViewById(R.id.et_czrPhone);

		tv_save.setOnClickListener(this);
		rl_time.setOnClickListener(this);
		rl_condition.setOnClickListener(this);

	}

	@Override
	protected void initData() {
		db = (new DatabaseHelper(this)).getWritableDatabase();
		conditions = new String[] { "是", "否" };
		if (Constant.poor.getTdataResult() != null) {
			if (!TextUtils.isEmpty(Constant.poor.getTdataResult().getTpDate()))
				tv_time.setText(Constant.poor.getTdataResult().getTpDate());
			if (!TextUtils.isEmpty(Constant.poor.getTdataResult().getJtsrRjsr()))
				et_income.setText(Constant.poor.getTdataResult().getJtsrRjsr());
			if (!TextUtils.isEmpty(Constant.poor.getTdataResult().getJtsrZsr()))
				et_allIncome.setText(Constant.poor.getTdataResult().getJtsrZsr());
			if (!TextUtils.isEmpty(Constant.poor.getTdataResult().getAddressSafe()))
				et_houseSafe.setText(Constant.poor.getTdataResult().getAddressSafe());
			if (!TextUtils.isEmpty(Constant.poor.getTdataResult().getJbshbzYl()))
				et_medical.setText(Constant.poor.getTdataResult().getJbshbzYl());
			if (!TextUtils.isEmpty(Constant.poor.getTdataResult().getJbshbzYwjy()))
				et_education.setText(Constant.poor.getTdataResult().getJbshbzYwjy());
			if (!TextUtils.isEmpty(Constant.poor.getTdataResult().getzcgjddz()))
				et_Captain.setText(Constant.poor.getTdataResult().getzcgjddz());
			if (!TextUtils.isEmpty(Constant.poor.getTdataResult().getcfzr()))
				et_Village.setText(Constant.poor.getTdataResult().getcfzr());
			if (!TextUtils.isEmpty(Constant.poor.getTdataResult().getJffhtptj()))
				tv_condition.setText(Constant.poor.getTdataResult().getJffhtptj());
		}

		if (Constant.poor.getTdataHelper() != null) {
			if (!TextUtils.isEmpty(Constant.poor.getTdataHelper().getJdbfzrOrgname()))
				et_organization.setText(Constant.poor.getTdataHelper().getJdbfzrOrgname());
			if (!TextUtils.isEmpty(Constant.poor.getTdataHelper().getJdbfzrOrger()))
				et_name.setText(Constant.poor.getTdataHelper().getJdbfzrOrger());
			if (!TextUtils.isEmpty(Constant.poor.getTdataHelper().getBfzrrPhone()))
				et_tel.setText(Constant.poor.getTdataHelper().getBfzrrPhone());
			if (!TextUtils.isEmpty(Constant.poor.getTdataHelper().getJdbfzrr()))
				et_helpMan.setText(Constant.poor.getTdataHelper().getJdbfzrr());
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
		case R.id.rl_time:
			Calendar c = Calendar.getInstance();
			// 最后一个false表示不显示日期，如果要显示日期，最后参数可以是true或者不用输入
			new DoubleDatePickerDialog(this, 0, new DoubleDatePickerDialog.OnDateSetListener() {
				@Override
				public void onDateSet(DatePicker startDatePicker, int startYear, int startMonthOfYear, int startDayOfMonth) {
					String textString = startYear + "-" + (startMonthOfYear + 1) + "-" + startDayOfMonth;
					tv_time.setText(textString);
				}
			}, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), false).show();
			break;
		case R.id.rl_condition:
			showWheelView("是否符合脱贫条件", conditions, 0);
			break;
		case R.id.txt_sure:
			if (TextUtils.isEmpty(condition))
				condition = conditions[1];
			tv_condition.setText(condition);
			condition = "";
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
				condition = item;
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
		TdataResult result = new TdataResult();
		result.setTpDate(tv_time.getText().toString().trim() + "");
		result.setJtsrRjsr(et_income.getText().toString().trim() + "");
		result.setJtsrZsr(et_allIncome.getText().toString().trim() + "");
		result.setAddressSafe(et_houseSafe.getText().toString().trim() + "");
		result.setJbshbzYl(et_medical.getText().toString().trim() + "");
		result.setJbshbzYwjy(et_education.getText().toString().trim() + "");
		if (!TextUtils.isEmpty(tv_condition.getText().toString().trim())) {
			String jffhtptj = dictDataDao.queryDictCodeByValue(db, tv_condition.getText().toString().trim(),"sf");
			result.setJffhtptj(jffhtptj);
		}
		Constant.poor.setTdataResult(result);

		TdataHelper helper = new TdataHelper();
		helper.setJdbfzrOrgname(et_organization.getText().toString().trim() + "");
		helper.setJdbfzrOrger(et_name.getText().toString().trim() + "");
		helper.setBfzrrPhone(et_tel.getText().toString().trim() + "");
		helper.setJdbfzrr(et_helpMan.getText().toString().trim() + "");
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
