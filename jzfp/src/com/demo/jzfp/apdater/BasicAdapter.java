package com.demo.jzfp.apdater;

import java.util.List;

import com.demo.jzfp.R;
import com.demo.jzfp.entity.Basic;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

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
		ViewHolder holder = null;
		if(convertView == null){
			holder = new ViewHolder();
			convertView = View.inflate(context, R.layout.basic_item, null);
			holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
			holder.tv_phone = (TextView) convertView.findViewById(R.id.tv_phone);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		return convertView;
	}

	class ViewHolder{
		TextView tv_name;
		TextView tv_phone;
	}
}
