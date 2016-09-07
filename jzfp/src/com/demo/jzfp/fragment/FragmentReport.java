package com.demo.jzfp.fragment;

import com.demo.jzfp.R;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FragmentReport extends Fragment implements OnClickListener{
	private View v;
	private int sex = 0; //姓别  0：未选   1：男    2：女
	private ImageView iv_sex_women;
	private TextView tv_sex_women;
	private ImageView iv_sex_man;
	private TextView tv_sex_man;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		v = inflater.inflate(R.layout.fragment_report, null);
		initView();
		return v;
	}
	
	/**
	 * 初始化view
	 */
	private void initView(){
		RelativeLayout rl_photo = (RelativeLayout) v.findViewById(R.id.rl_photo);
		ImageView iv_headpicture = (ImageView) v.findViewById(R.id.iv_headpicture);
		
		EditText et_name = (EditText) v.findViewById(R.id.et_name);
		
		RelativeLayout rl_state = (RelativeLayout) v.findViewById(R.id.rl_state);
		TextView tv_state = (TextView) v.findViewById(R.id.tv_state);
		
		LinearLayout ll_women = (LinearLayout) v.findViewById(R.id.ll_women);
		iv_sex_women = (ImageView) v.findViewById(R.id.iv_sex_women);
		tv_sex_women = (TextView) v.findViewById(R.id.tv_sex_women);
		LinearLayout ll_man = (LinearLayout) v.findViewById(R.id.ll_man);
		iv_sex_man = (ImageView) v.findViewById(R.id.iv_sex_man);
		tv_sex_man = (TextView) v.findViewById(R.id.tv_sex_man);
		
		EditText et_age = (EditText) v.findViewById(R.id.et_age);
		EditText et_identity = (EditText) v.findViewById(R.id.et_identity);
		EditText et_tel = (EditText) v.findViewById(R.id.et_tel);
		
		RelativeLayout rl_education = (RelativeLayout) v.findViewById(R.id.rl_education);
		TextView tv_education = (TextView) v.findViewById(R.id.tv_education);
		
		RelativeLayout rl_health = (RelativeLayout) v.findViewById(R.id.rl_health);
		TextView tv_health = (TextView) v.findViewById(R.id.tv_health);
		
		RelativeLayout rl_economy = (RelativeLayout) v.findViewById(R.id.rl_economy);
		TextView tv_economy = (TextView) v.findViewById(R.id.tv_economy);
		
		RelativeLayout rl_member = (RelativeLayout) v.findViewById(R.id.rl_member);
		TextView tv_member = (TextView) v.findViewById(R.id.tv_member);
		
		RelativeLayout rl_reason = (RelativeLayout) v.findViewById(R.id.rl_reason);
		TextView tv_reason = (TextView) v.findViewById(R.id.tv_reason);
		
		RelativeLayout rl_measures = (RelativeLayout) v.findViewById(R.id.rl_measures);
		TextView tv_measures = (TextView) v.findViewById(R.id.tv_measures);
		
		RelativeLayout rl_effect = (RelativeLayout) v.findViewById(R.id.rl_effect);
		TextView tv_effect = (TextView) v.findViewById(R.id.tv_effect);
		
		//设置点击事件
		rl_photo.setOnClickListener(this);
		rl_state.setOnClickListener(this);
		ll_women.setOnClickListener(this);
		ll_man.setOnClickListener(this);
		rl_education.setOnClickListener(this);
		rl_health.setOnClickListener(this);
		rl_economy.setOnClickListener(this);
		rl_reason.setOnClickListener(this);
		rl_measures.setOnClickListener(this);
		rl_effect.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.rl_photo:
			break;
		case R.id.rl_state:
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
		case R.id.rl_education:
			break;
		case R.id.rl_health:
			break;
		case R.id.rl_economy:
			break;
		case R.id.rl_reason:
			break;
		case R.id.rl_measures:
			break;
		case R.id.rl_effect:
			break;
		}
		
	}
}
