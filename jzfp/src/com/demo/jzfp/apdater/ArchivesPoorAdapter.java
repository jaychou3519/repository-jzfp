package com.demo.jzfp.apdater;

import com.demo.jzfp.R;
import com.demo.jzfp.activity.ArchivesDetailsActivity;
import com.demo.jzfp.utils.Tools;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ArchivesPoorAdapter extends BaseAdapter{

	private Context context;
	public ArchivesPoorAdapter(Context context) {
		this.context = context;
	}
	@Override
	public int getCount() {
		return 3;
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
			convertView = View.inflate(context, R.layout.archives_poor_item, null);
			holder.rl_poor = (RelativeLayout) convertView.findViewById(R.id.rl_poor);
			holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
			holder.tv_age = (TextView) convertView.findViewById(R.id.tv_age);
			holder.tv_state = (TextView) convertView.findViewById(R.id.tv_state);
			holder.tv_phone = (TextView) convertView.findViewById(R.id.tv_phone);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.rl_poor.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Tools.setOpenActivity(context, ArchivesDetailsActivity.class);
			}
		});
		return convertView;
	}

	class ViewHolder{
		RelativeLayout rl_poor;
		TextView tv_name;
		TextView tv_age;
		TextView tv_state;
		TextView tv_phone;
	}
}
