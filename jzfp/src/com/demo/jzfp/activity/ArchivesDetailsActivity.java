package com.demo.jzfp.activity;

import com.demo.jzfp.R;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import android.content.res.Resources;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

public class ArchivesDetailsActivity extends BaseActivity{

	
	@ViewInject(R.id.fl_framelayout)
	private FrameLayout fl_framelayout;
	@ViewInject(R.id.tv_record)
	private TextView tv_record;
	@ViewInject(R.id.tv_measure)
	private TextView tv_measure;
	@ViewInject(R.id.tv_effect)
	private TextView tv_effect;
	@Override
	protected void setView() {
		View view = View.inflate(ArchivesDetailsActivity.this, R.layout.activity_archives_details, null);
		setContentView(view);
		ViewUtils.inject(this,view);
	}

	@Override
	protected void initView() {
		
	}

	@Override
	protected void initData() {
		
	}

	@OnClick({R.id.iv_back,R.id.tv_record,R.id.tv_measure,R.id.tv_effect,R.id.iv_edit})
	public void mClick(View view){
		switch (view.getId()) {
		case R.id.iv_back:
			finish();
			break;
		case R.id.tv_record:
			selectId(0);
			break;
		case R.id.tv_measure:
			selectId(1);
			break;
		case R.id.tv_effect:
			selectId(2);
			break;
		case R.id.iv_edit:
			
			break;

		default:
			break;
		}
	}
	
	private void selectId(int state){
		Resources resources  = getResources();
		switch (state) {
		case 0:
			tv_record.setBackgroundColor(resources.getColor(R.color.bai));
			tv_measure.setBackgroundColor(resources.getColor(R.color.red_ff4242));
			tv_effect.setBackgroundColor(resources.getColor(R.color.red_ff4242));
			break;
		case 1:
			tv_record.setBackgroundColor(resources.getColor(R.color.red_ff4242));
			tv_measure.setBackgroundColor(resources.getColor(R.color.bai));
			tv_effect.setBackgroundColor(resources.getColor(R.color.red_ff4242));
			break;
		case 2:
			tv_record.setBackgroundColor(resources.getColor(R.color.red_ff4242));
			tv_measure.setBackgroundColor(resources.getColor(R.color.red_ff4242));
			tv_effect.setBackgroundColor(resources.getColor(R.color.bai));
			break;

		default:
			break;
		}
	}
}
