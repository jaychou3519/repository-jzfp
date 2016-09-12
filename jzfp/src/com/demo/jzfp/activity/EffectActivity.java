package com.demo.jzfp.activity;

import java.util.Arrays;
import java.util.Calendar;

import android.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.demo.jzfp.R;
import com.demo.jzfp.utils.Constant;
import com.demo.jzfp.utils.MyApplication;
import com.demo.jzfp.view.DoubleDatePickerDialog;
import com.demo.jzfp.view.WheelView;

public class EffectActivity extends BaseActivity implements OnClickListener {
	private AlertDialog dialog;
	private String[] conditions;
	private String condition;
	private TextView tv_time, tv_condition;
	private EditText et_income, et_allIncome, et_houseSafe, et_medical, et_education, et_organization, et_name, et_tel, et_helpMan;

	@Override
	protected void setView() {
		setContentView(R.layout.activity_effect);
		MyApplication activityList = (MyApplication) getApplicationContext();
		activityList.addActivity(this);
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
		
		RelativeLayout rl_condition = (RelativeLayout) findViewById(R.id.rl_condition);
		tv_condition = (TextView) findViewById(R.id.tv_condition);
		
		et_organization = (EditText) findViewById(R.id.et_organization);
		et_name = (EditText) findViewById(R.id.et_name);
		et_tel = (EditText) findViewById(R.id.et_tel);
		et_helpMan = (EditText) findViewById(R.id.et_helpMan);
		
		tv_save.setOnClickListener(this);
		rl_time.setOnClickListener(this);
		rl_condition.setOnClickListener(this);

	}

	@Override
	protected void initData() {
		conditions = new String[]{"是", "否"};
		
		if(!TextUtils.isEmpty(Constant.poor.getDate()))
			tv_time.setText(Constant.poor.getDate());
		if(!TextUtils.isEmpty(Constant.poor.getIncome()))
			et_income.setText(Constant.poor.getIncome());
		if(!TextUtils.isEmpty(Constant.poor.getAllIncome()))
			et_allIncome.setText(Constant.poor.getAllIncome());
		if(!TextUtils.isEmpty(Constant.poor.getHouseSafe()))
			et_houseSafe.setText(Constant.poor.getHouseSafe());
		if(!TextUtils.isEmpty(Constant.poor.getMedical()))
			et_medical.setText(Constant.poor.getMedical());
		if(!TextUtils.isEmpty(Constant.poor.getCompulsoryEducation()))
			et_education.setText(Constant.poor.getCompulsoryEducation());
		if(!TextUtils.isEmpty(Constant.poor.getCondition()))
			tv_condition.setText(Constant.poor.getCondition());
		if(!TextUtils.isEmpty(Constant.poor.getOrganization()))
			et_organization.setText(Constant.poor.getOrganization());
		if(!TextUtils.isEmpty(Constant.poor.getFzr()))
			et_name.setText(Constant.poor.getFzr());
		if(!TextUtils.isEmpty(Constant.poor.getFzrTel()))
			et_tel.setText(Constant.poor.getFzrTel());
		if(!TextUtils.isEmpty(Constant.poor.getBffzr()))
			et_helpMan.setText(Constant.poor.getBffzr());
		}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
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
					String textString = startYear + "年" + (startMonthOfYear+1) + "月";
					tv_time.setText(textString);
				}
			}, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), false).show();
			break;
		case R.id.rl_condition:
			showWheelView("是否符合脱贫条件", conditions, 0);
			break;
		case R.id.txt_sure:
			if(TextUtils.isEmpty(condition))
				condition = conditions[1];
			tv_condition.setText(condition);
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
	 * 设置贫因户信息
	 */
	private void setData(){
		Constant.poor.setDate(tv_time.getText().toString().trim()+"");
		Constant.poor.setIncome(et_income.getText().toString().trim()+"");
		Constant.poor.setAllIncome(et_allIncome.getText().toString().trim()+"");
		Constant.poor.setHouseSafe(et_houseSafe.getText().toString().trim()+"");
		Constant.poor.setMedical(et_medical.getText().toString().trim()+"");
		Constant.poor.setCompulsoryEducation(et_education.getText().toString().trim()+"");
		Constant.poor.setCondition(tv_condition.getText().toString().trim()+"");
		Constant.poor.setOrganization(et_organization.getText().toString().trim()+"");
		Constant.poor.setFzr(et_name.getText().toString().trim()+"");
		Constant.poor.setFzrTel(et_tel.getText().toString().trim()+"");
		Constant.poor.setBffzr(et_helpMan.getText().toString().trim()+"");
	}

}
