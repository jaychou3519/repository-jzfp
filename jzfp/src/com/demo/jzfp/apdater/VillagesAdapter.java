package com.demo.jzfp.apdater;

import java.util.List;

import com.demo.jzfp.R;
import com.demo.jzfp.activity.VillagesTextActivity;
import com.demo.jzfp.utils.Tools;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class VillagesAdapter extends BaseAdapter{

	private Context context;
	private List<String> datas;
	public static final int SUPPORT = 12;
	public static final int VILLAGES = 19;
	private int state;
	public VillagesAdapter(Context context,List<String> datas,int state) {
		this.context = context;
		this.datas = datas;
		this.state = state;
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
				Bundle bundle = new Bundle();
				switch (state) {
				case SUPPORT:
					bundle.putInt("villages", SUPPORT);
					Tools.setOpenActivityBundle(context, VillagesTextActivity.class,bundle);
					break;
				case VILLAGES:
					bundle.putInt("villages", VILLAGES);
					Tools.setOpenActivityBundle(context, VillagesTextActivity.class,bundle);
					break;

				default:
					break;
				}
			}
		});
		return convertView;
	}

	class ViewHolder{
		LinearLayout ll_villages;
		TextView tv_villages_name;
	}
}
