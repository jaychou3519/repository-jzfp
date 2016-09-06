package com.demo.jzfp.apdater;

import java.util.List;

import com.demo.jzfp.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class VillagesAdapter extends BaseAdapter{

	private Context context;
	private List<String> datas;
	public VillagesAdapter(Context context,List<String> datas) {
		this.context = context;
	}
	@Override
	public int getCount() {
		return 0;
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
			convertView = View.inflate(context, R.layout.villages_item, null);
			holder.ll_villages = (LinearLayout) convertView.findViewById(R.id.ll_villages);
			holder.tv_villages_name = (TextView) convertView.findViewById(R.id.tv_villages_name);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag(); 
		}
		return convertView;
	}

	class ViewHolder{
		LinearLayout ll_villages;
		TextView tv_villages_name;
	}
}
