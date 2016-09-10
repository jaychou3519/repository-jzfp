package com.demo.jzfp.apdater;

import com.demo.jzfp.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MeasureAdapter extends BaseAdapter{

	private Context context;
	public MeasureAdapter(Context context) {
		this.context = context;
	}
	@Override
	public int getCount() {
		return 4;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if(convertView==null){
			holder = new ViewHolder();
			convertView = View.inflate(context, R.layout.measure_item, null);
			holder.tv_measure = (TextView) convertView.findViewById(R.id.tv_measure);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		if(holder.tv_measure.getText().toString().length()>5)
			holder.tv_measure.setText(holder.tv_measure.getText().toString().substring(0, 5)+"\n"+holder.tv_measure.getText().toString().substring(5, holder.tv_measure.getText().toString().length()));
		
		return convertView;
	}

	class ViewHolder{
		TextView tv_measure;
	}
}
