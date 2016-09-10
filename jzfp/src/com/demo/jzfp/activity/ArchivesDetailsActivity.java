package com.demo.jzfp.activity;

import com.demo.jzfp.R;
import com.demo.jzfp.fragment.EffectFragment;
import com.demo.jzfp.fragment.MeasureFragment;
import com.demo.jzfp.fragment.RecordFragment;
import com.demo.jzfp.utils.MyApplication;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import android.app.FragmentTransaction;
import android.content.res.Resources;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

public class ArchivesDetailsActivity extends BaseActivity {
	private MyApplication activityList;

	@ViewInject(R.id.fl_framelayout)
	private FrameLayout fl_framelayout;
	@ViewInject(R.id.tv_record)
	private TextView tv_record;
	@ViewInject(R.id.tv_measure)
	private TextView tv_measure;
	@ViewInject(R.id.tv_effect)
	private TextView tv_effect;

	private RecordFragment rdfragment;
	private EffectFragment etfragment;
	private MeasureFragment msfragment;
	private FragmentTransaction transaction;

	@Override
	protected void setView() {
		View view = View.inflate(this, R.layout.activity_archives_details, null);
		setContentView(view);
		activityList = (MyApplication) getApplicationContext();
		activityList.addActivity(this);
		ViewUtils.inject(this, view);
	}

	@Override
	protected void initView() {

	}

	@Override
	protected void initData() {
		setTabSelection(0);
	}

	@OnClick({ R.id.iv_back, R.id.tv_record, R.id.tv_measure, R.id.tv_effect, R.id.iv_edit })
	public void mClick(View view) {
		switch (view.getId()) {
		case R.id.iv_back:
			finish();
			break;
		case R.id.tv_record:
			setTabSelection(0);
			break;
		case R.id.tv_measure:
			setTabSelection(1);
			break;
		case R.id.tv_effect:
			setTabSelection(2);
			break;
		case R.id.iv_edit:

			break;

		default:
			break;
		}
	}

	private void selectId(int state) {
		Resources resources = getResources();
		switch (state) {
		case 0:
			tv_record.setBackgroundColor(resources.getColor(R.color.bai));
			tv_measure.setBackgroundColor(resources.getColor(R.color.red_ff4242));
			tv_effect.setBackgroundColor(resources.getColor(R.color.red_ff4242));
			tv_record.setTextColor(resources.getColor(R.color.red_ff4242));
			tv_measure.setTextColor(resources.getColor(R.color.bai));
			tv_effect.setTextColor(resources.getColor(R.color.bai));
			break;
		case 1:
			tv_record.setBackgroundColor(resources.getColor(R.color.red_ff4242));
			tv_measure.setBackgroundColor(resources.getColor(R.color.bai));
			tv_effect.setBackgroundColor(resources.getColor(R.color.red_ff4242));
			tv_record.setTextColor(resources.getColor(R.color.bai));
			tv_measure.setTextColor(resources.getColor(R.color.red_ff4242));
			tv_effect.setTextColor(resources.getColor(R.color.bai));
			break;
		case 2:
			tv_record.setBackgroundColor(resources.getColor(R.color.red_ff4242));
			tv_measure.setBackgroundColor(resources.getColor(R.color.red_ff4242));
			tv_effect.setBackgroundColor(resources.getColor(R.color.bai));
			tv_record.setTextColor(resources.getColor(R.color.bai));
			tv_measure.setTextColor(resources.getColor(R.color.bai));
			tv_effect.setTextColor(resources.getColor(R.color.red_ff4242));
			break;

		default:
			break;
		}
	}

	/***
	 * 根据传入的index参数来设置选中的tab页。
	 * 
	 * @param index
	 */
	private void setTabSelection(int index) {
		// 每次选中之前先清楚掉上次的选中状态
		transaction = getFragmentManager().beginTransaction();
		// 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
		selectId(index);
		hideFragments(transaction);
		switch (index) {
		case 0:
			if (rdfragment == null) {
				// 如果FragmentHome为空，则创建一个并添加到界面上
				rdfragment = new RecordFragment();
				transaction.replace(R.id.fl_framelayout, rdfragment);
			} else {
				// 如果FragmentHome不为空，则直接将它显示出来
				transaction.show(rdfragment);
			}
			break;
		case 1:
			if (msfragment == null) {
				msfragment = new MeasureFragment();
				transaction.add(R.id.fl_framelayout, msfragment);
			} else {
				transaction.show(msfragment);
			}
			break;
		case 2:
			if (etfragment == null) {
				etfragment = new EffectFragment();
				transaction.add(R.id.fl_framelayout, etfragment);
			} else {
				transaction.show(etfragment);
			}
			break;
		}
		transaction.commit();
	}

	private void hideFragments(FragmentTransaction transaction) {
		if (rdfragment != null) {
			transaction.hide(rdfragment);
		}
		if (msfragment != null) {
			transaction.hide(msfragment);
		}
		if (etfragment != null) {
			transaction.hide(etfragment);
		}
	}

}
