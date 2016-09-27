package com.demo.jzfp.apdater;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import junit.framework.Test;

import java.util.List;

import com.demo.jzfp.R;
import com.demo.jzfp.activity.ArchivesPoorActivity;
import com.demo.jzfp.entity.ToFiles;
import com.demo.jzfp.utils.Tools;

public class ArchivesAdapter extends BaseAdapter{

	private Context context;
	private List<ToFiles> toFiles;
	private String toForm = null;
	public ArchivesAdapter(Context context,List<ToFiles> toFiles,String toForm) {
		this.context = context;
		this.toFiles = toFiles;
		this.toForm = toForm;
	}
	@Override
	public int getCount() {
		return toFiles.size();
	}

	@Override
	public Object getItem(int position) {
		return toFiles.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
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
		holder.tv_address.setText(toFiles.get(position).getAddressC()+"");
		holder.tv_number.setText(toFiles.get(position).getJtstRjsr()+"");
		holder.rl_archives.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Bundle bundle = new Bundle();
				bundle.putBoolean("state", true);
				bundle.putString("toForm", toForm);
				bundle.putString("countrymanId", toFiles.get(position).getCountrymanId()+"");
				Tools.i("CountrymanId",  toFiles.get(position).getCountrymanId()+"");
				Tools.setOpenActivityBundle(context, ArchivesPoorActivity.class, bundle);
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
