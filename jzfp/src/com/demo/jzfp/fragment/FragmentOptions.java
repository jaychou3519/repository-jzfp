package com.demo.jzfp.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.jzfp.R;
import com.demo.jzfp.activity.EditPwdActivity;
import com.demo.jzfp.utils.MyApplication;
import com.demo.jzfp.utils.Tools;

public class FragmentOptions extends Fragment implements OnClickListener{
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_options, null);
		initView(v);
		return v;
	}

	private void initView(View v) {
		ImageView imageView = (ImageView) v.findViewById(R.id.iv_back);
		imageView.setVisibility(View.INVISIBLE);
		TextView textView = (TextView) v.findViewById(R.id.tv_title);
		textView.setText("设置");
		TextView editPwd = (TextView) v.findViewById(R.id.editPwd);
		Button btn_loginout = (Button) v.findViewById(R.id.btn_loginout);
		editPwd.setOnClickListener(this);
		btn_loginout.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.editPwd:
				Tools.setOpenActivity(getActivity(), EditPwdActivity.class);
				break;
			case R.id.btn_loginout:
				MyApplication.finishAll();
				break;
		}
	}
}
