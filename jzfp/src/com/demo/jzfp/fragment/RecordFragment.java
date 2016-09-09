package com.demo.jzfp.fragment;

import com.demo.jzfp.R;
import com.demo.jzfp.apdater.RecordAdapter;
import com.demo.jzfp.utils.Tools;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class RecordFragment extends Fragment{

	private ListView lv_listview;
	private RecordAdapter adapter;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		adapter = new RecordAdapter(getActivity());
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_record, null);
		lv_listview = (ListView) view.findViewById(R.id.lv_listview);
		lv_listview.setAdapter(adapter);
		Tools.setListViewHeight(lv_listview);
		return view;
	}
}
