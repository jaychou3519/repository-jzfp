package com.demo.jzfp.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

import com.demo.jzfp.R;
import com.demo.jzfp.fragment.FragmentHome;
import com.demo.jzfp.fragment.FragmentOptions;
import com.demo.jzfp.fragment.FragmentReport;
import com.demo.jzfp.utils.Tools;

public class MainActivity extends BaseActivity implements OnClickListener {
	private long time;
	private List<LinearLayout> lls;
	private FragmentTransaction transaction;
	private FragmentOptions fr_options;
	private FragmentHome fr_home;
	private FragmentReport fr_report;
	private int count;

	@Override
	protected void setView() {
		setContentView(R.layout.activity_main);
	}

	@Override
	protected void initView() {
		lls = new ArrayList<LinearLayout>();
		lls.add((LinearLayout) findViewById(R.id.ll_home));
		lls.add((LinearLayout) findViewById(R.id.ll_options));
		lls.add((LinearLayout) findViewById(R.id.ll_report));
		for (LinearLayout ll : lls) {
			ll.setOnClickListener(this);
		}
	}

	@Override
	protected void initData() {
		setTabSelection(0);
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
		hideFragments(transaction);
		switch (index) {
		case 0:
			if (fr_home == null) {
				// 如果FragmentHome为空，则创建一个并添加到界面上
				fr_home = new FragmentHome();
				transaction.replace(R.id.frameLayout, fr_home);
			} else {
				// 如果FragmentHome不为空，则直接将它显示出来
				transaction.show(fr_home);
			}
			break;
		case 1:
			if (fr_report == null) {
				fr_report = new FragmentReport();
				transaction.add(R.id.frameLayout, fr_report);
			} else {
				transaction.show(fr_report);
			}
			break;
		case 2:
			if (fr_options == null) {
				fr_options = new FragmentOptions();
				transaction.add(R.id.frameLayout, fr_options);
			} else {
				transaction.show(fr_options);
			}
			break;
		}
		transaction.commit();
	}

	private void hideFragments(FragmentTransaction transaction) {
		if (fr_report != null) {
			transaction.hide(fr_report);
		}
		if (fr_home != null) {
			transaction.hide(fr_home);
		}
		if (fr_options != null) {
			transaction.hide(fr_options);
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// 判断是否点击返回键
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& +event.getAction() == KeyEvent.ACTION_DOWN) {
			// 获得系统时间判断是否在限定时间内连续点击
			if (System.currentTimeMillis() - time > 3000) {
				Tools.showNewToast(this, "再按一次退出");
				time = System.currentTimeMillis();
			} else {
				// 获取当前应用进程码
				int pid = android.os.Process.myPid();
				// 杀死当前应用进程
				android.os.Process.killProcess(pid);
				return false;
				// finish(); //终止当前Activity
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ll_home:
			count = 0;
			break;
		case R.id.ll_report:
			count = 1;
			break;
		case R.id.ll_options:
			count = 2;
			break;
		}
		setTabSelection(count);
		offKeyboard();
	}
	
}
