package com.demo.jzfp.activity;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.demo.jzfp.R;
import com.demo.jzfp.apdater.VillagesAdapter2;
import com.demo.jzfp.utils.MyApplication;
import com.demo.jzfp.utils.Tools;
import com.demo.jzfp.utils.WebServiceClient;

public class ChooseAreaActivity extends BaseActivity implements OnClickListener{
	
	private static String result;
    private static String METHOD_NAME = "selectToRegister";
    private static Map mm = null;
	private VillagesAdapter2 villagesAdapter;
	private MyApplication activityList;

	@Override
	protected void setView() {
		setContentView(R.layout.choose_area);
		activityList = (MyApplication) getApplicationContext();
		activityList.addActivity(this);
	}

	@Override
	protected void initView() {
		TextView tv_title = (TextView) findViewById(R.id.tv_title);
		TextView tv_commit = (TextView) findViewById(R.id.tv_commit);
		tv_commit.setVisibility(View.VISIBLE);
		tv_title.setText("区域选择");
		tv_commit.setOnClickListener(this);
		setOnback(this);
	}

	@Override
	protected void initData() {
		callWeb();
	}
	
	MyHandler myHandler = new MyHandler(this);
	
	public void callWeb(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                result = WebServiceClient.callWeb(METHOD_NAME,null);
                myHandler.sendEmptyMessage(1);
            }
        }).start();
    }
	
		class MyHandler extends Handler{
	        WeakReference<ChooseAreaActivity> mActivity;
	
	        MyHandler(ChooseAreaActivity theActivity) {
	            mActivity = new WeakReference<ChooseAreaActivity>(theActivity);
	        }
	
	        @Override
	        public void handleMessage(Message msg) {
	        	ChooseAreaActivity myActivity = mActivity.get();
	            switch (msg.what) {
	                case 1:
	                	generateViewList(myActivity);
	                    break;
	            }
	            super.handleMessage(msg);
	        }
	        
	        List<String> towns = new ArrayList<String>();
	    	List<String> villages = new ArrayList<String>();
	    	List<Map> map = new ArrayList<Map>();
	    	ListView villagesListView;
	    	ListView townsListView;
	        
	        public void generateViewList(final ChooseAreaActivity context){
	        	map = JSON.parseArray(result, Map.class);
	    		townsListView = (ListView)context.findViewById(R.id.towns);
	    		villagesListView = (ListView)context.findViewById(R.id.villages);
	    		for (Map map2 : map) {
	    			String name = (String) map2.get("name");
	    			if(name.contains("县") || name.contains("镇") || name.contains("乡")){
	    				towns.add(map2.get("name").toString());
	    			}
				}
	    		ArrayAdapter<String> townsAdapter = new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,towns);
	    		townsListView.setAdapter(townsAdapter);
	    		
	    		townsListView.setOnItemClickListener(new OnItemClickListener(){
	                @Override
	                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
	                        long arg3) {
	                	villages.clear();
	                	String town = towns.get(arg2);
	                	for (Map m : map) {
							if(town.equals(m.get("name"))){
								for (Map m2 : map) {
									if(m2.get("pid").equals(m.get("id"))){
										villages.add((String) m2.get("name"));
									}
								}
								break;
							}
						}
	                	villagesAdapter = new VillagesAdapter2(context,villages,arg2);
	    	    		villagesListView.setAdapter(villagesAdapter);
	                }
	            });
	    		
	    		villagesListView.setOnItemClickListener(new OnItemClickListener(){
	                @Override
	                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
	                        long arg3) {
	                	villagesAdapter.setSelectid(arg2);
	                	String village = villages.get(arg2);
	                	for (Map m : map) {
	                		if(village.equals(m.get("name"))){
	                			mm = m;
	                			break;
	                		}
						}
	                	villagesAdapter.notifyDataSetChanged();
	                }
	            });
	        }
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.tv_commit:
			   Bundle bundle=new Bundle();
		       bundle.putString("name", (String) mm.get("name"));
		       bundle.putString("areacode", (String) mm.get("areacode"));
		       Intent intent = new Intent(this, RegisterActivity.class);
		       intent.putExtras(bundle);
		   	   startActivity(intent);
			   break;
		}
	}

}
