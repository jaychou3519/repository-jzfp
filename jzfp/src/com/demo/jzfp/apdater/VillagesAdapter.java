package com.demo.jzfp.apdater;

import java.util.List;

import com.demo.jzfp.R;
import com.demo.jzfp.activity.VillagesTextActivity;
import com.demo.jzfp.entity.Policy;
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
	private List<Policy> policies;
	public static final int SUPPORT = 12;
	public static final int VILLAGES = 19;
	private int state;
	public VillagesAdapter(Context context,List<Policy> policies,int state) {
		this.context = context;
		this.policies = policies;
		this.state = state;
	}
	@Override
	public int getCount() {
		return policies.size();
	}

	@Override
	public Object getItem(int position) {
		return policies.get(position);
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
		holder.tv_villages_name.setText(policies.get(position).getNewTitle());
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
