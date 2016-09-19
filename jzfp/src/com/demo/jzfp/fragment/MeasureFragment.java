package com.demo.jzfp.fragment;

import java.util.List;

import com.demo.jzfp.R;
import com.demo.jzfp.apdater.ImageAdapter;
import com.demo.jzfp.apdater.MeasureAdapter;
import com.demo.jzfp.entity.TdataAction;
import com.demo.jzfp.entity.TdataCountryman;
import com.demo.jzfp.entity.TdataResult;
import com.demo.jzfp.utils.Tools;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class MeasureFragment extends Fragment{

	private ListView lv_listview;
	private MeasureAdapter adapter;

	private List<TdataAction> tdataActions;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_measure, null);
		lv_listview = (ListView) view.findViewById(R.id.lv_listview_measure);
		if(lv_listview==null){
			Tools.showNewToast(getActivity(), "listview 空指针。。。。");
			return view;
		}
		if(adapter==null){
			Tools.showNewToast(getActivity(), "adapter 空指针。。。。");
			return view;
		}
		return view;
	}
	
	public void setTdataAction(List<TdataAction> tdataActions){
		this.tdataActions = tdataActions;
		initData();
	}

	private void initData() {
		adapter = new MeasureAdapter(getActivity(),tdataActions);
		lv_listview.setAdapter(adapter);
	}
}
