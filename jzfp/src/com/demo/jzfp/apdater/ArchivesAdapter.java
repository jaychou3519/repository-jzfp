package com.demo.jzfp.apdater;

import com.demo.jzfp.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ArchivesAdapter extends BaseAdapter{

	private Context context;
	public ArchivesAdapter(Context context) {
		this.context = context;
	}
	@Override
	public int getCount() {
		return 0;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView==null){
			convertView = View.inflate(context, R.layout.activity_archives, null);
		}else{
			
		}
		return null;
	}

}
