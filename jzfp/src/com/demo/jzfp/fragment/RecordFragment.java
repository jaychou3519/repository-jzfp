package com.demo.jzfp.fragment;

import com.demo.jzfp.R;
import com.demo.jzfp.apdater.RecordAdapter;
import com.demo.jzfp.utils.Tools;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

public class RecordFragment extends Fragment{

	private ScrollView sv_scroll;
	private ListView lv_listview;
	private RecordAdapter adapter;
	private TextView tv_name,tv_state,tv_gender,tv_idcard,tv_age,tv_phone,tv_educational
					,tv_health,tv_home_flat,tv_home_area,tv_plough_area,tv_mountains_area
					,tv_year_money,tv_reason,tv_explain;
	private ImageView iv_photo;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		adapter = new RecordAdapter(getActivity());
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_record, null);
		initView(view);
		lv_listview.setAdapter(adapter);
		Tools.setListViewHeight(lv_listview);
		return view;
	}

	private void initView(View view) {
		sv_scroll = (ScrollView) view.findViewById(R.id.sv_scroll);
		sv_scroll.smoothScrollTo(0, 0);
		lv_listview = (ListView) view.findViewById(R.id.lv_listview);
		tv_name = 	(TextView) view.findViewById(R.id.tv_name);
		tv_state = 	(TextView) view.findViewById(R.id.tv_state);
		tv_gender = 	(TextView) view.findViewById(R.id.tv_gender);
		tv_idcard = 	(TextView) view.findViewById(R.id.tv_idcard);
		tv_age = 	(TextView) view.findViewById(R.id.tv_age);
		tv_phone = 	(TextView) view.findViewById(R.id.tv_phone);
		tv_educational = 	(TextView) view.findViewById(R.id.tv_educational);
		tv_health = 	(TextView) view.findViewById(R.id.tv_health);
		tv_home_flat = 	(TextView) view.findViewById(R.id.tv_home_flat);
		tv_home_area = 	(TextView) view.findViewById(R.id.tv_home_area);
		tv_plough_area = 	(TextView) view.findViewById(R.id.tv_plough_area);
		tv_mountains_area = 	(TextView) view.findViewById(R.id.tv_mountains_area);
		tv_year_money = 	(TextView) view.findViewById(R.id.tv_year_money);
		tv_reason = 	(TextView) view.findViewById(R.id.tv_reason);
		tv_explain = 	(TextView) view.findViewById(R.id.tv_explain);
		iv_photo = 	(ImageView) view.findViewById(R.id.iv_photo);
	}
	
}
