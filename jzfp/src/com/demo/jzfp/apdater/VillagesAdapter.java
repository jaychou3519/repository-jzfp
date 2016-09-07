package com.demo.jzfp.apdater;

import java.util.List;

import com.demo.jzfp.R;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class VillagesAdapter extends BaseAdapter{

	private Context context;
	private List<String> datas;
	public VillagesAdapter(Context context,List<String> datas) {
		this.context = context;
		this.datas = datas;
	}
	@Override
	public int getCount() {
		return datas.size();
	}

	@Override
	public Object getItem(int position) {
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if(convertView==null){
			holder = new ViewHolder();
			convertView = View.inflate(context, R.layout.villages_item, null);
			holder.ll_villages = (LinearLayout) convertView.findViewById(R.id.ll_villages);
			holder.tv_villages_name = (TextView) convertView.findViewById(R.id.tv_villages_name);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag(); 
		}
		holder.tv_villages_name.setText(datas.get(position));
		holder.ll_villages.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			}
		});
		return convertView;
	}

	class ViewHolder{
		LinearLayout ll_villages;
		TextView tv_villages_name;
	}
}