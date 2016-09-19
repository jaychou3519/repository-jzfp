package com.demo.jzfp.apdater;

import java.util.List;
import java.util.Map;

import org.apache.http.message.BasicStatusLine;

import com.demo.jzfp.R;
import com.demo.jzfp.entity.Basic;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class BasicAdapter extends BaseAdapter{

	private List<Map> maps;
	private Context context;
	public BasicAdapter(Context context,List<Map> maps) {
		this.context = context;
		this.maps = maps;
	}
	@Override
	public int getCount() {
		return maps.size();
	}

	@Override
	public Object getItem(int position) {
		return maps.get(position);
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
			holder.vw_lines = convertView.findViewById(R.id.vw_lines);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tv_name.setText(maps.get(position).get("name")+"");
		holder.tv_phone.setText(maps.get(position).get("phone")+"");
		if(position==(maps.size()-1)){
			holder.vw_lines.setVisibility(View.GONE);
		}
		return convertView;
	}

	class ViewHolder{
		TextView tv_name;
		TextView tv_phone;
		View vw_lines;
	}
}
