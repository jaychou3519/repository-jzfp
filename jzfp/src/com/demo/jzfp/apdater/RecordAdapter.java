package com.demo.jzfp.apdater;

import java.util.List;

import com.demo.jzfp.R;
import com.demo.jzfp.entity.Basic;
import com.demo.jzfp.entity.TdataFamily;
import com.demo.jzfp.utils.Tools;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class RecordAdapter extends BaseAdapter{

	private Context context;
	private List<TdataFamily> families;
	public RecordAdapter(Context context,List<TdataFamily> families) {
		this.context = context;
		this.families = families;
	}
	@Override
	public int getCount() {
		return families.size();
	}

	@Override
	public Object getItem(int position) {
		return families.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if(convertView == null){
			holder = new ViewHolder();
			convertView = View.inflate(context, R.layout.record_item, null);
			holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
			holder.tv_relation = (TextView) convertView.findViewById(R.id.tv_relation);
			holder.tv_gender = (TextView) convertView.findViewById(R.id.tv_gender);
			holder.tv_YearM = (TextView) convertView.findViewById(R.id.tv_YearM);
			holder.tv_health = (TextView) convertView.findViewById(R.id.tv_health);
			holder.tv_other = (TextView) convertView.findViewById(R.id.tv_other);
			holder.vw_lines = convertView.findViewById(R.id.vw_lines);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tv_name.setText(Tools.parseEmpty(families.get(position).getFamilyName()));
		holder.tv_relation.setText(Tools.parseEmpty(families.get(position).getYhzgx()+""));
		holder.tv_gender.setText(Tools.parseEmpty(families.get(position).getSex()+""));
		holder.tv_YearM.setText(Tools.parseEmpty(families.get(position).getBirthday()+""));
		holder.tv_health.setText(Tools.parseEmpty(families.get(position).getJkzk()+""));
		holder.tv_other.setText(Tools.parseEmpty(families.get(position).getWorkDesc()+""));
		if(getCount()==(position+1)){
			holder.vw_lines.setVisibility(View.GONE);
		}
		return convertView;
	}


	class ViewHolder{
		TextView tv_name;
		TextView tv_relation;
		TextView tv_gender;
		TextView tv_YearM;
		TextView tv_health;
		TextView tv_other;
		View vw_lines;
	}
}
