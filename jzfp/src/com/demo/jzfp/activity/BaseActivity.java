package com.demo.jzfp.activity;

import com.demo.jzfp.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

public abstract class BaseActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);// ��ֹ����
		requestWindowFeature(Window.FEATURE_NO_TITLE);// ȥ����
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);// Ĭ������̲�����
		setView(); // 初始化布局
		initView(); // 初始化控件
		initData(); // 数据初始化
	}

	protected abstract void setView();

	protected abstract void initView();

	protected abstract void initData();

	/**
	 * 跳转到其它Activity
	 * 
	 * @param pClass
	 * @param pBundle
	 */
	public void openActivity(Class<?> pClass, Bundle pBundle) {
		Intent intent = new Intent(this, pClass);
		if (pBundle != null) {
			intent.putExtras(pBundle);
		}
		startActivity(intent);
	}

	/**
	 * 设置title名称
	 *
	 * @param text
	 */
	public void setTitleText(String text) {
		TextView tv_title = (TextView) findViewById(R.id.tv_title);
		tv_title.setText(text);
	}

	/**
	 * 设置标题是否显示
	 * 
	 * @param isb
	 */
	public void setTitleVisible(Boolean isb) {
		TextView tv_title = (TextView) findViewById(R.id.tv_title);
		if (isb) {
			tv_title.setVisibility(View.VISIBLE);
		} else {
			tv_title.setVisibility(View.GONE);
		}
	}

	/**
	 * 退出当前activity
	 *
	 * @param
	 */
	public void setOnback(final Activity context) {
		ImageView iv = (ImageView) findViewById(R.id.iv_back);
		if (null != iv) {
			iv.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					offKeyboard();
					context.finish();
				}
			});
		}
	}

	/**
	 * 设置返回键是否显示
	 * 
	 * @param isb
	 */
	public void setOnbackVisible(Boolean isb) {
		ImageView iv = (ImageView) findViewById(R.id.iv_back);
		if (isb) {
			iv.setVisibility(View.VISIBLE);
		} else {
			iv.setVisibility(View.GONE);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (null != this.getCurrentFocus()) {
			/**
			 * 点击空白位置 隐藏软键盘
			 */
			InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
			return mInputMethodManager.hideSoftInputFromWindow(this
					.getCurrentFocus().getWindowToken(), 0);
		}
		return super.onTouchEvent(event);
	}

	/**
	 * 关闭键盘
	 */
	public void offKeyboard() {
		View view = getWindow().peekDecorView();
		if (view != null) {
			InputMethodManager inputmanger = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
		}
	}

}
