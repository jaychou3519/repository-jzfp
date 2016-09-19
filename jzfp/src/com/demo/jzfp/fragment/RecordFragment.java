package com.demo.jzfp.fragment;

import com.demo.jzfp.R;
import com.demo.jzfp.apdater.RecordAdapter;
import com.demo.jzfp.entity.TdataCountryman;
import com.demo.jzfp.utils.Tools;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
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
	private TdataCountryman countryman;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_record, null);
		initView(view);
		return view;
	}

	private void initView(View view) {
		sv_scroll = (ScrollView) view.findViewById(R.id.sv_scroll);
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
		sv_scroll.smoothScrollTo(0, 20);
		sv_scroll.setFocusable(true);
	}
	
	private void initData(){
		if(countryman==null) return;
		tv_name.setText(Tools.parseEmpty(countryman.getCountryman()));
		tv_state.setText(Tools.parseEmpty(countryman.getPoorState()));
		tv_gender.setText(Tools.parseEmpty(countryman.getSex()));
		tv_idcard.setText(Tools.parseEmpty(countryman.getCard()));
		tv_age.setText(Tools.parseEmpty(countryman.getAge()));
		tv_phone.setText(Tools.parseEmpty(countryman.getTelphone()));
		tv_educational.setText(Tools.parseEmpty(countryman.getWhcd()));
		tv_health.setText(Tools.parseEmpty(countryman.getJkzk()));
		tv_home_flat.setText(Tools.parseEmpty(countryman.getZfjg()));
		tv_home_area.setText(Tools.parseEmpty(countryman.getZzArea()));
		tv_plough_area.setText(Tools.parseEmpty(countryman.getGdArea()));
		tv_mountains_area.setText(Tools.parseEmpty(countryman.getSlArea()));
		tv_year_money.setText(Tools.parseEmpty(countryman.getRjsrqk()));
		tv_reason.setText(Tools.parseEmpty(countryman.getPoorReason()));
		tv_explain.setText(Tools.parseEmpty(countryman.getRemark()));
		
		if(!TextUtils.isEmpty(countryman.getPkhimg()))
			ImageLoader.getInstance().displayImage(countryman.getPkhimg(), iv_photo);
		adapter = new RecordAdapter(getActivity(),countryman.getTdataFamilys());
		lv_listview.setAdapter(adapter);
		Tools.setListViewHeight(lv_listview);
		sv_scroll.smoothScrollTo(0, 20);
		sv_scroll.setFocusable(true);
	}
	public void setCountryMan(TdataCountryman countryMan){
		this.countryman = countryMan;
		initData();
	}
}
