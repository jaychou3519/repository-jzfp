package com.demo.jzfp.apdater;

import com.demo.jzfp.R;
import com.demo.jzfp.activity.MeasureDetailActivity;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class MeasureDetailAdapter extends BaseAdapter {
	
	private String[] strs;
	private String[] checkedValues;
	private LayoutInflater inflater;
	private StringBuffer sb;
	private MeasureDetailActivity context;
	
	public MeasureDetailAdapter(MeasureDetailActivity context, String[] values, StringBuffer sb) {
		this.inflater = LayoutInflater.from(context);
		this.strs = values;
		this.checkedValues = sb.toString().split(",");
		this.context = context;
		this.sb = sb;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return strs == null ? 0 : strs.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return strs[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		String str = strs[position];
		View v = inflater.inflate(R.layout.gv_measure, null);
		final CheckBox cb = (CheckBox) v.findViewById(R.id.cb);
		cb.setText(str);
		
		if(checkedValues != null && checkedValues.length > 0){
			for (int i = 0; i < checkedValues.length; i++) {
				if(cb.getText().toString().equals(checkedValues[i])){
					cb.setChecked(true);
					break;
				}else{
					cb.setChecked(false);
				}
			}
		}else {
			cb.setChecked(false);
		}
		
		
		cb.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				String cbText = cb.getText().toString();
				if(isChecked){
					if(!sb.toString().contains(cbText))
						sb.append(cbText+",");
				}else{
					if(sb.toString().contains(cbText)){
						String str = sb.toString().replace(cbText+",", "");
						sb = new StringBuffer(str);
						context.setXl(sb);
					}
				}
				
				
			}
		});
		
		return v;
	}
	

}
