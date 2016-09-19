package com.demo.jzfp.apdater;

import java.util.List;

import com.demo.jzfp.R;
import com.demo.jzfp.entity.TdataAction;
import com.demo.jzfp.utils.Tools;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MeasureAdapter extends BaseAdapter{

	private Context context;
	private List<TdataAction> actions;
	public MeasureAdapter(Context context,List<TdataAction> actions) {
		this.context = context;
		this.actions = actions;
	}
	@Override
	public int getCount() {
		return actions.size();
	}

	@Override
	public Object getItem(int position) {
		return actions.get(position);
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
			convertView = View.inflate(context, R.layout.measure_item, null);
			holder.tv_measure = (TextView) convertView.findViewById(R.id.tv_measure);
			holder.tv_monery = (TextView) convertView.findViewById(R.id.tv_monery);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tv_monery.setText(Tools.parseEmpty(actions.get(position).getActionMoney())+"");
		String content = Tools.parseEmpty(actions.get(position).getActionXl());
		if(content.length()>5)
			holder.tv_measure.setText(content.substring(0, 5)+"\n"+content.substring(5, content.length()));
		holder.tv_measure.setText(content);
		return convertView;
	}

	class ViewHolder{
		TextView tv_measure;
		TextView tv_monery;
	}
}
