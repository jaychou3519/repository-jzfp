package com.demo.jzfp.apdater;

import java.util.List;

import com.demo.jzfp.R;
import com.demo.jzfp.entity.Basic;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class RecordAdapter extends BaseAdapter{

	private Context context;
	public RecordAdapter(Context context) {
		this.context = context;
	}
	@Override
	public int getCount() {
		return 2;
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
		if(convertView == null){
			holder = new ViewHolder();
			convertView = View.inflate(context, R.layout.record_item, null);
			holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
			holder.tv_relation = (TextView) convertView.findViewById(R.id.tv_relation);
			holder.tv_gender = (TextView) convertView.findViewById(R.id.tv_gender);
			holder.tv_YearM = (TextView) convertView.findViewById(R.id.tv_YearM);
			holder.tv_health = (TextView) convertView.findViewById(R.id.tv_health);
			holder.tv_other = (TextView) convertView.findViewById(R.id.tv_other);
			holder.vw_lines = convertView.findViewById(R.id.vw_lines);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		if(getCount()==(position+1)){
			holder.vw_lines.setVisibility(View.GONE);
		}
		return convertView;
	}


	class ViewHolder{
		TextView tv_name;
		TextView tv_relation;
		TextView tv_gender;
		TextView tv_YearM;
		TextView tv_health;
		TextView tv_other;
		View vw_lines;
	}
}
