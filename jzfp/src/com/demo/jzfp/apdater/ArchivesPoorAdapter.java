package com.demo.jzfp.apdater;

import java.util.List;

import com.demo.jzfp.R;
import com.demo.jzfp.activity.ArchivesDetailsActivity;
import com.demo.jzfp.activity.ArchivesPoorActivity;
import com.demo.jzfp.entity.CountryMans;
import com.demo.jzfp.utils.Tools;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ArchivesPoorAdapter extends BaseAdapter{

	private Context context;
	private List<CountryMans> countrys;
	private String toForm = null;
	
	public ArchivesPoorAdapter(Context context,List<CountryMans> countrys,String toForm) {
		this.context = context;
		this.countrys = countrys;
		this.toForm = toForm;
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
			holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
			holder.iv_photo = (ImageView) convertView.findViewById(R.id.iv_photo);
			holder.ll_linear = (LinearLayout) convertView.findViewById(R.id.ll_linear);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tv_name.setText(countrys.get(position).getCountryman()+"");
		Point p = Tools.getPoint(context);
		LayoutParams para = holder.iv_photo.getLayoutParams();
		para.height = (p.x) / 4;
		para.width = (p.x - 40) / 4;
		holder.iv_photo.setLayoutParams(para);
		Tools.i("ArchivesPoorAdapter", countrys.get(position).getPkhimg());
		if(countrys.get(position).getPkhimg()!=null)
			ImageLoader.getInstance().displayImage(countrys.get(position).getPkhimg(), holder.iv_photo); 
		holder.ll_linear.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Bundle bundle = new Bundle();
				bundle.putString("countrymanId", countrys.get(position).getCountrymanId()+"");
				bundle.putString("toForm", toForm);
				Tools.setOpenActivityBundle(context, ArchivesDetailsActivity.class, bundle);
			}
		});
		return convertView;
	}

	class ViewHolder{
		TextView tv_name;
		ImageView iv_photo;
		LinearLayout ll_linear;
	}
}
