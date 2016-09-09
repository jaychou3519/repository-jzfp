package com.demo.jzfp.apdater;

import java.util.List;

import com.demo.jzfp.R;
import com.demo.jzfp.entity.Effect;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class EffectAdapter extends BaseAdapter{

	private List<Effect> effects;
	private Context context;
	public EffectAdapter(Context context,List<Effect> effects) {
		this.context = context;
		this.effects = effects;
	}
	@Override
	public int getCount() {
		return effects.size();
	}

	@Override
	public Object getItem(int position) {
		return effects.get(position);
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
			convertView = View.inflate(context, R.layout.effect_itme, null);
			holder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
			holder.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tv_title.setText(effects.get(position).getTitle());
		holder.tv_content.setText(effects.get(position).getContent());
		return convertView;
	}

	class ViewHolder{
		TextView tv_title;
		TextView tv_content;
	}
}
