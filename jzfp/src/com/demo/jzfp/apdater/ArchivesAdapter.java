package com.demo.jzfp.apdater;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import junit.framework.Test;

import com.demo.jzfp.R;
import com.demo.jzfp.activity.ArchivesPoorActivity;
import com.demo.jzfp.utils.Tools;

public class ArchivesAdapter extends BaseAdapter{

	private Context context;
	public ArchivesAdapter(Context context) {
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
			convertView = View.inflate(context, R.layout.archives_item, null);
			holder = new ViewHolder();
			holder.rl_archives = (RelativeLayout) convertView.findViewById(R.id.rl_archives);
			holder.tv_address = (TextView) convertView.findViewById(R.id.tv_address);
			holder.tv_number_name = (TextView) convertView.findViewById(R.id.tv_number_name);
			holder.tv_number = (TextView) convertView.findViewById(R.id.tv_number);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.rl_archives.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Tools.setOpenActivity(context, ArchivesPoorActivity.class);
			}
		});
		return convertView;
	}

	class ViewHolder{
		RelativeLayout rl_archives;
		TextView tv_address;
		TextView tv_number_name;
		TextView tv_number;
		
	}
}
