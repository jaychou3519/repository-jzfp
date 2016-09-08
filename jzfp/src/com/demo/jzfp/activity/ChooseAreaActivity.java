package com.demo.jzfp.activity;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import com.alibaba.fastjson.JSON;
import com.demo.jzfp.R;
import com.demo.jzfp.utils.WebServiceClient;

public class ChooseAreaActivity extends BaseActivity{
	
	private static String result;
    private static String METHOD_NAME = "selectToRegister";
	

	@Override
	protected void setView() {
		setContentView(R.layout.choose_area);
	}

	@Override
	protected void initView() {
		
	}

	@Override
	protected void initData() {
		
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
	
	static class MyHandler extends Handler{
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
	        
	        public void generateViewList(ChooseAreaActivity context){
	    		List<Map> map = JSON.parseArray(result, Map.class);
	    		TextView area = (TextView)context.findViewById(R.id.area);
	    		List<Map> towns = new ArrayList<Map>();
	    		List<Map> villages = new ArrayList<Map>();
	    		for (Map map2 : map) {
	    			String name = (String) map2.get("name");
	    			if(name.contains("县") || name.contains("镇") || name.contains("乡")){
	    				towns.add(map2);
	    			}else{
	    				villages.add(map2);
	    			}
				}
	        }
	}

}
