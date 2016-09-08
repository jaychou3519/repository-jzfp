package com.demo.jzfp.activity;

import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.demo.jzfp.R;
import com.demo.jzfp.utils.MyApplication;

public class MemberActivity extends BaseActivity implements OnClickListener {
	private int sex = 0; // 姓别 0：未选 1：男 2：女
	private ImageView iv_sex_women;
	private TextView tv_sex_women;
	private ImageView iv_sex_man;
	private TextView tv_sex_man;
	private TextView tv_education;
	private MyApplication activityList;

	@Override
	protected void setView() {
		setContentView(R.layout.activity_member);
		activityList = (MyApplication) getApplicationContext();
		activityList.addActivity(this);
		setTitleText("家庭成员");
		setOnback(this);
	}

	@Override
	protected void initView() {
		TextView tv_save = (TextView) findViewById(R.id.tv_save);
		
		LinearLayout ll_add = (LinearLayout) findViewById(R.id.ll_add);

		EditText et_name = (EditText) findViewById(R.id.et_name);

		RelativeLayout rl_relation = (RelativeLayout) findViewById(R.id.rl_relation);

		LinearLayout ll_women = (LinearLayout) findViewById(R.id.ll_women);
		iv_sex_women = (ImageView) findViewById(R.id.iv_sex_women);
		tv_sex_women = (TextView) findViewById(R.id.tv_sex_women);
		LinearLayout ll_man = (LinearLayout) findViewById(R.id.ll_man);
		iv_sex_man = (ImageView) findViewById(R.id.iv_sex_man);
		tv_sex_man = (TextView) findViewById(R.id.tv_sex_man);

		RelativeLayout rl_birthday = (RelativeLayout) findViewById(R.id.rl_birthday);
		RelativeLayout rl_health = (RelativeLayout) findViewById(R.id.rl_health);
		EditText et_work = (EditText) findViewById(R.id.et_work);
		
		tv_save.setOnClickListener(this);
		ll_add.setOnClickListener(this);
		rl_relation.setOnClickListener(this);
		ll_women.setOnClickListener(this);
		ll_man.setOnClickListener(this);
		rl_birthday.setOnClickListener(this);
		rl_health.setOnClickListener(this);
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_save:
			break;
		case R.id.ll_add:
			break;
		case R.id.rl_relation:
			break;
		case R.id.ll_women:
			sex = 2;
			iv_sex_women.setImageResource(R.drawable.woman_yes);
			tv_sex_women.setTextColor(Color.rgb(155, 89, 182));
			iv_sex_man.setImageResource(R.drawable.man_no);
			tv_sex_man.setTextColor(Color.rgb(220, 220, 200));
			break;
		case R.id.ll_man:
			sex = 1;
			iv_sex_women.setImageResource(R.drawable.woman_no);
			tv_sex_women.setTextColor(Color.rgb(220, 220, 220));
			iv_sex_man.setImageResource(R.drawable.man_yes);
			tv_sex_man.setTextColor(Color.rgb(52, 152, 219));
			break;
		case R.id.rl_birthday:
			break;
		case R.id.rl_health:
			break;
			
			
	
		}

	}

}
