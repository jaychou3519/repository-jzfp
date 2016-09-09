package com.demo.jzfp.activity;

import java.util.Calendar;

import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.demo.jzfp.R;
import com.demo.jzfp.utils.MyApplication;
import com.demo.jzfp.view.DoubleDatePickerDialog;

public class EffectActivity extends BaseActivity implements OnClickListener {

	private TextView tv_time;

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
		
		EditText et_income = (EditText) findViewById(R.id.et_income);
		EditText et_allIncome = (EditText) findViewById(R.id.et_allIncome);
		EditText et_houseSafe = (EditText) findViewById(R.id.et_houseSafe);
		EditText et_medical = (EditText) findViewById(R.id.et_medical);
		EditText et_education = (EditText) findViewById(R.id.et_education);
		
		RelativeLayout rl_condition = (RelativeLayout) findViewById(R.id.rl_condition);
		TextView tv_condition = (TextView) findViewById(R.id.tv_condition);
		
		EditText et_organization = (EditText) findViewById(R.id.et_organization);
		EditText et_name = (EditText) findViewById(R.id.et_name);
		EditText et_tel = (EditText) findViewById(R.id.et_tel);
		EditText et_helpMan = (EditText) findViewById(R.id.et_helpMan);
		
		tv_save.setOnClickListener(this);
		rl_time.setOnClickListener(this);
		rl_condition.setOnClickListener(this);

	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.tv_save:
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
			break;
		}
		
	}


}
