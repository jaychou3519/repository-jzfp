package com.demo.jzfp.fragment;

import com.demo.jzfp.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class EffectFragment extends Fragment{
	private ListView lv_listview,lv_listview1;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_effect, null);
		lv_listview = (ListView) view.findViewById(R.id.lv_listview);
		lv_listview1 = (ListView) view.findViewById(R.id.lv_listview1);
		return view;
	}
}
