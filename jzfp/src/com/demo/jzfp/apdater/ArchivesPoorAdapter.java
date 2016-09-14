package com.demo.jzfp.apdater;

import java.util.List;

import com.demo.jzfp.R;
import com.demo.jzfp.activity.ArchivesDetailsActivity;
import com.demo.jzfp.activity.ArchivesPoorActivity;
import com.demo.jzfp.entity.CountryMans;
import com.demo.jzfp.utils.Tools;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ArchivesPoorAdapter extends BaseAdapter{

	private Context context;
	private List<CountryMans> countrys;
	public ArchivesPoorAdapter(Context context,List<CountryMans> countrys) {
		this.context = context;
		this.countrys = countrys;
	}
	@Override
	public int getCount() {
		return countrys.size();
	}

	@Override
	public Object getItem(int position) {
		return countrys.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
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
		holder.tv_name.setText(countrys.get(position).getCountryman()+"");
		holder.tv_age.setText(countrys.get(position).getAge()+"");
		holder.tv_state.setText(countrys.get(position).getPoorState()+"");
		holder.tv_phone.setText(countrys.get(position).getTelphone()+"");
		holder.rl_poor.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Bundle bundle = new Bundle();
				bundle.putString("countrymanId", countrys.get(position).getCountrymanId()+"");
				Tools.setOpenActivityBundle(context, ArchivesDetailsActivity.class, bundle);
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
