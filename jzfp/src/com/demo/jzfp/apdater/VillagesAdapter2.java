package com.demo.jzfp.apdater;

import java.util.List;

import com.demo.jzfp.R;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class VillagesAdapter2  extends BaseAdapter{
	
	private Context context;
	private List<String> datas;
	private int state;
	public int selectid = -1;
	
	public int getSelectid() {
		return selectid;
	}

	public void setSelectid(int selectid) {
		this.selectid = selectid;
	}

	public VillagesAdapter2(Context context,List<String> datas,int state) {
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
		View view = View.inflate(context, R.layout.choose_villages,null);
		TextView textView = (TextView) view.findViewById(R.id.villageText);
		textView.setText(datas.get(position));
		ImageView imageView = (ImageView) view.findViewById(R.id.selectImg);
		if(selectid != -1&&position==selectid){
			imageView.setVisibility(View.VISIBLE);
		}else{
			imageView.setVisibility(View.GONE);
		}
		return view;
	}

	
}
