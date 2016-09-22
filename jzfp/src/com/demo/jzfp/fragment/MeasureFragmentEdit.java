package com.demo.jzfp.fragment;

import java.util.List;
import com.demo.jzfp.R;
import com.demo.jzfp.apdater.MeasureAdapterEdit;
import com.demo.jzfp.entity.TdataAction;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class MeasureFragmentEdit extends Fragment{

	private ListView lv_listview;
	private MeasureAdapterEdit adapter;

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
			return view;
		}
		if(adapter==null){
			return view;
		}
		return view;
	}
	
	public void setTdataAction(List<TdataAction> tdataActions){
		this.tdataActions = tdataActions;
		initData();
	}

	private void initData() {
		adapter = new MeasureAdapterEdit(getActivity(),tdataActions);
		lv_listview.setAdapter(adapter);
	}
}
