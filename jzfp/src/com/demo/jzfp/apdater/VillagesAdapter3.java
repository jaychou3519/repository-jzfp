package com.demo.jzfp.apdater;

import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.demo.jzfp.R;
import com.demo.jzfp.activity.MeasuresActivity2;
import com.demo.jzfp.activity.VillagesTextActivity;
import com.demo.jzfp.entity.Policy;
import com.demo.jzfp.entity.Villages;
import com.demo.jzfp.utils.Tools;

public class VillagesAdapter3 extends BaseAdapter{

	private Context context;
	private List<Policy> policies;
	private List<Villages> villages;
	public static final int SUPPORT = 12;
	public static final int VILLAGES = 19;
	private int state;
	public VillagesAdapter3(Context context,List<Policy> policies,int state) {
		this.context = context;
		this.policies = policies;
		this.state = state;
	}
	public VillagesAdapter3(Context context,int state,List<Villages> villages) {
		this.context = context;
		this.villages = villages;
		this.state = state;
	}
	@Override
	public int getCount() {
		if(state==VILLAGES){
			return villages.size();
		}else{
			return policies.size();
		}
		
	}

	@Override
	public Object getItem(int position) {
		if(state==VILLAGES){
			return villages.get(position);
		}else{
			return policies.get(position);
		}
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
			convertView = View.inflate(context, R.layout.villages_item, null);
			holder.ll_villages = (LinearLayout) convertView.findViewById(R.id.ll_villages);
			holder.tv_villages_name = (TextView) convertView.findViewById(R.id.tv_villages_name);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag(); 
		}
		if(state==VILLAGES){
			holder.tv_villages_name.setText(villages.get(position).getOrgName());
		}else{
			holder.tv_villages_name.setText(policies.get(position).getNewTitle());
		}
		holder.ll_villages.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Bundle bundle = new Bundle();
				switch (state) {
				case SUPPORT:
					bundle.putInt("villages", SUPPORT);
					bundle.putString("url", policies.get(position).getFw_url()+"");
					Tools.setOpenActivityBundle(context, VillagesTextActivity.class,bundle);
					break;
				case VILLAGES:
					bundle.putString("content", villages.get(position).getRemark()+"");
					bundle.putString("title", villages.get(position).getOrgName()+"");
					bundle.putString("countrymanId", villages.get(position).getOrgId()+"");
					Tools.setOpenActivityBundle(context, MeasuresActivity2.class,bundle);
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
