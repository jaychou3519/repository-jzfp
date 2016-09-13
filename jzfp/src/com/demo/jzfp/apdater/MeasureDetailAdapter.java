package com.demo.jzfp.apdater;

import java.util.List;

import com.demo.jzfp.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

public class MeasureDetailAdapter extends BaseAdapter {
	
	private String[] strs;
	private LayoutInflater inflater;
	public MeasureDetailAdapter(Context context, String[] values) {
		this.inflater = LayoutInflater.from(context);
		this.strs = values;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return strs == null ? 0 : strs.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return strs[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		String str = strs[position];
		View v = inflater.inflate(R.layout.gv_measure, null);
		CheckBox cb = (CheckBox) v.findViewById(R.id.cb);
		cb.setText(str);
		return v;
	}

}
