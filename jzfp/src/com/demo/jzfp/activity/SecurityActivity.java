package com.demo.jzfp.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.demo.jzfp.R;
import com.demo.jzfp.utils.MyApplication;

public class SecurityActivity extends BaseActivity implements OnClickListener{
	private List<RelativeLayout> rls = new ArrayList<RelativeLayout>();
	private List<ImageView> ivs = new ArrayList<ImageView>();
	private String[] securitys = {"A", "B", "C", "D", "无房"};
	private String security = "";
	private Intent intent;
 	
	@Override
	protected void setView() {
		setContentView(R.layout.activity_security);
		MyApplication.addActivity(SecurityActivity.this);
		setTitleText("住房安全");
		setOnback(this);
	}

	@Override
	protected void initView() {
		rls.add((RelativeLayout) findViewById(R.id.rl_bkzk));
		rls.add((RelativeLayout) findViewById(R.id.rl_gz));
		rls.add((RelativeLayout) findViewById(R.id.rl_cz));
		rls.add((RelativeLayout) findViewById(R.id.rl_xx));
		rls.add((RelativeLayout) findViewById(R.id.rl_other));
		
		for (RelativeLayout rl : rls) {
			rl.setOnClickListener(this);
		}
		
		ivs.add((ImageView) findViewById(R.id.iv_checked_bkzk));
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
		case R.id.rl_bkzk:
			ivs.get(0).setVisibility(View.VISIBLE);
			security = securitys[0];
			break;
		case R.id.rl_gz:
			ivs.get(1).setVisibility(View.VISIBLE);
			security = securitys[1];
			break;
		case R.id.rl_cz:
			ivs.get(2).setVisibility(View.VISIBLE);
			security = securitys[2];
			break;
		case R.id.rl_xx:
			ivs.get(3).setVisibility(View.VISIBLE);
			security = securitys[3];
			break;
		case R.id.rl_other:
			ivs.get(4).setVisibility(View.VISIBLE);
			security = securitys[4];
			break;
		}
		Log.i("JJY", security);
		intent.putExtra("security", security);
		setResult(300, intent);
	}

}
