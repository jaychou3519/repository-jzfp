package com.demo.jzfp.apdater;

import java.util.List;

import com.demo.jzfp.R;
import com.demo.jzfp.entity.Basic;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class BasicAdapter extends BaseAdapter{

	private List<Basic> basics;
	private Context context;
	public BasicAdapter(Context context,List<Basic> basics) {
		this.context = context;
		this.basics = basics;
	}
	@Override
	public int getCount() {
		return basics.size();
	}

	@Override
	public Object getItem(int position) {
		return basics.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null){
			convertView = View.inflate(context, R.layout.basic_item, null);
		}
		return convertView;
	}

	class ViewHolder{
	}
}
