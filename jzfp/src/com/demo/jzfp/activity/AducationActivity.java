package com.demo.jzfp.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.demo.jzfp.R;
import com.demo.jzfp.utils.MyApplication;

public class AducationActivity extends BaseActivity implements OnClickListener{
	private List<RelativeLayout> rls = new ArrayList<RelativeLayout>();
	private List<ImageView> ivs = new ArrayList<ImageView>();
	private String[] aducations = {"研究生教育", "大学本科/专科教育", "中等职业教育", "普通高级中学教育", "初级中学教育", "小学教育", "其它"};
	private String aducation = "";
	private Intent intent;
	private MyApplication activityList;
 	
	@Override
	protected void setView() {
		setContentView(R.layout.activity_aducation);
		activityList = (MyApplication) getApplicationContext();
		activityList.addActivity(this);
		setTitleText("文化程度");
		setOnback(this);
	}

	@Override
	protected void initView() {
		rls.add((RelativeLayout) findViewById(R.id.rl_yjs));
		rls.add((RelativeLayout) findViewById(R.id.rl_bkzk));
		rls.add((RelativeLayout) findViewById(R.id.rl_zy));
		rls.add((RelativeLayout) findViewById(R.id.rl_gz));
		rls.add((RelativeLayout) findViewById(R.id.rl_cz));
		rls.add((RelativeLayout) findViewById(R.id.rl_xx));
		rls.add((RelativeLayout) findViewById(R.id.rl_other));
		
		for (RelativeLayout rl : rls) {
			rl.setOnClickListener(this);
		}
		
		ivs.add((ImageView) findViewById(R.id.iv_checked_yjs));
		ivs.add((ImageView) findViewById(R.id.iv_checked_bkzk));
		ivs.add((ImageView) findViewById(R.id.iv_checked_zy));
		ivs.add((ImageView) findViewById(R.id.iv_checked_gz));
		ivs.add((ImageView) findViewById(R.id.iv_checked_cz));
		ivs.add((ImageView) findViewById(R.id.iv_checked_xx));
		ivs.add((ImageView) findViewById(R.id.iv_checked_other));
	}

	@Override
	protected void initData() {
		intent = new Intent();
	}

	@Override
	public void onClick(View v) {
		for (ImageView iv : ivs) {
			iv.setVisibility(View.INVISIBLE);
		}
		switch(v.getId()){
		case R.id.rl_yjs:
			ivs.get(0).setVisibility(View.VISIBLE);
			aducation = aducations[0];
			break;
		case R.id.rl_bkzk:
			ivs.get(1).setVisibility(View.VISIBLE);
			aducation = aducations[1];
			break;
		case R.id.rl_zy:
			ivs.get(2).setVisibility(View.VISIBLE);
			aducation = aducations[2];
			break;
		case R.id.rl_gz:
			ivs.get(3).setVisibility(View.VISIBLE);
			aducation = aducations[3];
			break;
		case R.id.rl_cz:
			ivs.get(4).setVisibility(View.VISIBLE);
			aducation = aducations[4];
			break;
		case R.id.rl_xx:
			ivs.get(5).setVisibility(View.VISIBLE);
			aducation = aducations[5];
			break;
		case R.id.rl_other:
			ivs.get(6).setVisibility(View.VISIBLE);
			aducation = aducations[6];
			break;
		}
		intent.putExtra("education", aducation);
		setResult(300, intent);
	}

}
