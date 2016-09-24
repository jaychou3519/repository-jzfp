package com.demo.jzfp.apdater;

import java.util.List;

import com.demo.jzfp.R;
import com.demo.jzfp.entity.TdataAction;
import com.demo.jzfp.utils.Tools;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

public class MeasureAdapterEdit extends BaseAdapter{

	private Context context;
	private List<TdataAction> actions;
	public MeasureAdapterEdit(Context context,List<TdataAction> actions) {
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
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if(convertView==null){
			holder = new ViewHolder();
			convertView = View.inflate(context, R.layout.measure_item_edit, null);
			holder.tv_measure = (TextView) convertView.findViewById(R.id.tv_measure);
			holder.ed_monery = (EditText) convertView.findViewById(R.id.ed_monery);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.ed_monery.setText(Tools.parseEmpty(actions.get(position).getActionMoney())+"");
		holder.ed_monery.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if(s.length()>=0)
					actions.get(position).setActionMoney(s.toString().trim());
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				
			}
		});
		if(position==0){
			holder.ed_monery.setSelection(holder.ed_monery.getText().toString().length());
		}
		String content = Tools.parseEmpty(actions.get(position).getActionXl());
		if(content.length()>5)
			holder.tv_measure.setText(content.substring(0, 5)+"\n"+content.substring(5, content.length()));
		holder.tv_measure.setText(content);
		return convertView;
	}

	class ViewHolder{
		TextView tv_measure;
		EditText ed_monery;
	}
}
