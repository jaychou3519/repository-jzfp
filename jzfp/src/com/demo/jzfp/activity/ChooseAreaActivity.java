package com.demo.jzfp.activity;

import java.lang.ref.WeakReference;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.demo.jzfp.R;
import com.demo.jzfp.utils.Constant;
import com.demo.jzfp.utils.WebServiceClient;

public class ChooseAreaActivity extends BaseActivity{
	
	private static String result;
    private static String METHOD_NAME = "selectToRegister";
    private static String INTERFACE_NAME = "selectToRegister";
	
	@Override
	protected void setView() {
		setContentView(R.layout.choose_area);
	}

	@Override
	protected void initView() {
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
                result = WebServiceClient.callWeb(Constant.NAME_SPACE,METHOD_NAME,Constant.SERVER_URL,INTERFACE_NAME,null);
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
	        
	        public void generateViewList(Context context){
	    		
	    	}
	}
	
	
}
