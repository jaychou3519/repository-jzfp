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
	private String[] securitys = new String[]{"有保障","无保障"};
	private int state=-1;
	private String condition;
	private TextView tv_time, tv_condition,tv_medical,tv_education,tv_houseSafe;
	private EditText et_income, et_allIncome;  /*, et_houseSafe, et_Captain, et_Village*/
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
		RelativeLayout rl_condition = (RelativeLayout) findViewById(R.id.rl_condition);
		RelativeLayout rl_houseSafe = (RelativeLayout) findViewById(R.id.rl_houseSafe);
		RelativeLayout rl_into_education = (RelativeLayout) findViewById(R.id.rl_into_education);
		RelativeLayout rl_medical = (RelativeLayout) findViewById(R.id.rl_medical);
		
		tv_save.setOnClickListener(this);
		rl_time.setOnClickListener(this);
		rl_condition.setOnClickListener(this);
		rl_houseSafe.setOnClickListener(this);
		rl_into_education.setOnClickListener(this);
		rl_medical.setOnClickListener(this);
		
		tv_time = (TextView) findViewById(R.id.tv_time);
		et_income = (EditText) findViewById(R.id.et_income);
		et_allIncome = (EditText) findViewById(R.id.et_allIncome);
		tv_houseSafe = (TextView) findViewById(R.id.tv_houseSafe);
		tv_medical = (TextView) findViewById(R.id.tv_medical);
		tv_education = (TextView) findViewById(R.id.tv_education);
		tv_condition = (TextView) findViewById(R.id.tv_condition);
		
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
				tv_houseSafe.setText(Constant.poor.getTdataResult().getAddressSafe());
			if (!TextUtils.isEmpty(Constant.poor.getTdataResult().getJbshbzYl()))
				tv_medical.setText(Constant.poor.getTdataResult().getJbshbzYl());
			if (!TextUtils.isEmpty(Constant.poor.getTdataResult().getJbshbzYwjy()))
				tv_education.setText(Constant.poor.getTdataResult().getJbshbzYwjy());
			if (!TextUtils.isEmpty(Constant.poor.getTdataResult().getJffhtptj())){
					String s = dictDataDao.queryValueByDictCode(db, Constant.poor.getTdataResult().getJffhtptj(),"sf");
					tv_condition.setText(s);
			}
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
			state = 0;
			showWheelView("是否符合脱贫条件", conditions, state);
			break;
		case R.id.rl_houseSafe:
			state = 1;
			showWheelView("有无保障", securitys, state);
			break;
		case R.id.rl_into_education:
			state = 2;
			showWheelView("有无保障", securitys, state);
			break;
		case R.id.rl_medical:
			state = 3;
			showWheelView("有无保障", securitys, state);
			break;
		case R.id.txt_sure:
			dialog.dismiss();
			switch (state) {
			case 0:
				if (TextUtils.isEmpty(condition))
					condition = conditions[1];
				tv_condition.setText(condition);
				condition = "";
				break;
			case 1:
				if (TextUtils.isEmpty(condition))
					condition = securitys[1];
				tv_houseSafe.setText(condition);
				condition = "";
				break;
			case 2:
				if (TextUtils.isEmpty(condition))
					condition = securitys[1];
				tv_education.setText(condition);
				condition = "";
				break;
			case 3:
				if (TextUtils.isEmpty(condition))
					condition = securitys[1];
				tv_medical.setText(condition);
				condition = "";
				break;
			}
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
		TdataResult result = Constant.poor.getTdataResult();
		if(result==null){
			result = new TdataResult();
		}
		result.setTpDate(tv_time.getText().toString().trim() + "");
		result.setJtsrRjsr(et_income.getText().toString().trim() + "");
		result.setJtsrZsr(et_allIncome.getText().toString().trim() + "");
		result.setAddressSafe(tv_houseSafe.getText().toString().trim() + "");
		result.setJbshbzYl(tv_medical.getText().toString().trim() + "");
		result.setJbshbzYwjy(tv_education.getText().toString().trim() + "");
		if (!TextUtils.isEmpty(tv_condition.getText().toString().trim())) {
			String jffhtptj = dictDataDao.queryDictCodeByValue(db, tv_condition.getText().toString().trim(),"sf");
			result.setJffhtptj(jffhtptj);
		}
		Constant.poor.setTdataResult(result);
	}

}
