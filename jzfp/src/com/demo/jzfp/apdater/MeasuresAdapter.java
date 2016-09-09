package com.demo.jzfp.apdater;

import java.util.List;

import com.demo.jzfp.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MeasuresAdapter extends BaseAdapter {
	private List<String> measureses;
	private LayoutInflater inflater;
	public MeasuresAdapter(Context context, List<String> measureses) {
		this.inflater = LayoutInflater.from(context);
		this.measureses = measureses;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return measureses == null ? 0 : measureses.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return measureses.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		String measures = measureses.get(position);
		Holder holder;
		if(convertView == null){
			holder = new Holder();
			convertView = inflater.inflate(R.layout.lv_measures, null);
			holder.tv_measures = (TextView) convertView.findViewById(R.id.tv_measures);
			convertView.setTag(holder);
		}else {
			holder = (Holder) convertView.getTag();
		}
		holder.tv_measures.setText(measures);
		return convertView;
	}
	
	class Holder{
		TextView tv_measures;
	}

}
