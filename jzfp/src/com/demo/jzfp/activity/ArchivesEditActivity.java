package com.demo.jzfp.activity;

import java.util.LinkedHashMap;
import java.util.List;
import com.alibaba.fastjson.JSON;
import com.demo.jzfp.R;
import com.demo.jzfp.entity.TdataAction;
import com.demo.jzfp.entity.TdataCountryman;
import com.demo.jzfp.entity.TdataResult;
import com.demo.jzfp.fragment.EffectFragmentEdit;
import com.demo.jzfp.fragment.MeasureFragmentEdit;
import com.demo.jzfp.fragment.RecordFragmentEdit;
import com.demo.jzfp.utils.Constant;
import com.demo.jzfp.utils.MyApplication;
import com.demo.jzfp.utils.RequestWebService;
import com.demo.jzfp.utils.Tools;
import com.demo.jzfp.utils.RequestWebService.WebServiceCallback;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Handler;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

public class ArchivesEditActivity extends BaseActivity implements WebServiceCallback{
	private String TAG  = "ArchivesEditActivity";
	private MyApplication activityList;

	@ViewInject(R.id.fl_framelayout)
	private FrameLayout fl_framelayout;
	@ViewInject(R.id.tv_record)
	private TextView tv_record;
	@ViewInject(R.id.tv_measure)
	private TextView tv_measure;
	@ViewInject(R.id.tv_effect)
	private TextView tv_effect;

	private RecordFragmentEdit rdfragment;
	private EffectFragmentEdit etfragment;
	private MeasureFragmentEdit msfragment;
	private FragmentTransaction transaction;
	private String methodName = "selectByCountryman";

	private TdataCountryman countryMan;
	private TdataResult tresult;
	private List<TdataAction> tactions;

	private LinkedHashMap<String, String> linkedHashMap;
	private boolean rd=false,ms=false,et=false;
	@Override
	protected void setView() {
		View view = View.inflate(this, R.layout.activity_archives_edit, null);
		setContentView(view);
		activityList = (MyApplication) getApplicationContext();
		activityList.addActivity(this);
		ViewUtils.inject(this, view);
	}

	@Override
	protected void initView() {
		Tools.i("JJY", getIntent().getExtras().getString("countrymanId"));
		linkedHashMap = new LinkedHashMap<String, String>();
		linkedHashMap.put("arg0", getIntent().getExtras().getString("countrymanId"));
		RequestWebService.send(methodName, linkedHashMap, this, 101);
		RequestWebService.send("selectByResult", linkedHashMap, this, 103);
		RequestWebService.send("selectByAction", linkedHashMap, this, 102);
	}

	@Override
	protected void initData() {
		setTabSelection(0);
	}

	@OnClick({ R.id.iv_back, R.id.tv_record, R.id.tv_measure, R.id.tv_effect, R.id.tv_sumbit })
	public void mClick(View view) {
		switch (view.getId()) {
		case R.id.iv_back:
			finish();
			break;
		case R.id.tv_record:
			setTabSelection(0);
			break;
		case R.id.tv_measure:
			if(tactions==null&&!ms)
			RequestWebService.send("selectByAction", linkedHashMap, this, 102);
			setTabSelection(1);
			handler.sendEmptyMessage(205);
			break;
		case R.id.tv_effect:
			if(tresult==null&&!et)
			RequestWebService.send("selectByResult", linkedHashMap, this, 103);
			setTabSelection(2);
			handler.sendEmptyMessage(206);
			break;
		case R.id.tv_sumbit:
			getData();
			break;

		default:
			break;
		}
	}

	private void selectId(int state) {
		Resources resources = getResources();
		switch (state) {
		case 0:
			tv_record.setBackgroundColor(resources.getColor(R.color.bai));
			tv_measure.setBackgroundColor(resources.getColor(R.color.red_ff4242));
			tv_effect.setBackgroundColor(resources.getColor(R.color.red_ff4242));
			tv_record.setTextColor(resources.getColor(R.color.red_ff4242));
			tv_measure.setTextColor(resources.getColor(R.color.bai));
			tv_effect.setTextColor(resources.getColor(R.color.bai));
			break;
		case 1:
			tv_record.setBackgroundColor(resources.getColor(R.color.red_ff4242));
			tv_measure.setBackgroundColor(resources.getColor(R.color.bai));
			tv_effect.setBackgroundColor(resources.getColor(R.color.red_ff4242));
			tv_record.setTextColor(resources.getColor(R.color.bai));
			tv_measure.setTextColor(resources.getColor(R.color.red_ff4242));
			tv_effect.setTextColor(resources.getColor(R.color.bai));
			break;
		case 2:
			tv_record.setBackgroundColor(resources.getColor(R.color.red_ff4242));
			tv_measure.setBackgroundColor(resources.getColor(R.color.red_ff4242));
			tv_effect.setBackgroundColor(resources.getColor(R.color.bai));
			tv_record.setTextColor(resources.getColor(R.color.bai));
			tv_measure.setTextColor(resources.getColor(R.color.bai));
			tv_effect.setTextColor(resources.getColor(R.color.red_ff4242));
			break;

		default:
			break;
		}
	}

	/***
	 * 根据传入的index参数来设置选中的tab页。
	 * 
	 * @param index
	 */
	private void setTabSelection(int index) {
		// 每次选中之前先清楚掉上次的选中状态
		transaction = getFragmentManager().beginTransaction();
		// 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
		selectId(index);
		hideFragments(transaction);
		switch (index) {
		case 0:
			if (rdfragment == null) {
				// 如果FragmentHome为空，则创建一个并添加到界面上
				rdfragment = new RecordFragmentEdit();
				transaction.replace(R.id.fl_framelayout, rdfragment);
			} else {
				// 如果FragmentHome不为空，则直接将它显示出来
				transaction.show(rdfragment);
			}
			break;
		case 1:
			if (msfragment == null) {
				msfragment = new MeasureFragmentEdit();
				transaction.add(R.id.fl_framelayout, msfragment);
			} else {
				transaction.show(msfragment);
			}
			break;
		case 2:
			if (etfragment == null) {
				etfragment = new EffectFragmentEdit();
				transaction.add(R.id.fl_framelayout, etfragment);
			} else {
				transaction.show(etfragment);
			}
			break;
		}
		transaction.commit();
	}

	private void hideFragments(FragmentTransaction transaction) {
		if (rdfragment != null) {
			transaction.hide(rdfragment);
		}
		if (msfragment != null) {
			transaction.hide(msfragment);
		}
		if (etfragment != null) {
			transaction.hide(etfragment);
		}
	}

	@Override
	public void result(String reulst, int requestCode) {
		if(reulst == null){
			Tools.showNewToast(getApplication(), "链接服务器失败");
		}else{
    		switch (requestCode) {
    		case 100:
    			if (requestCode == 100) {// 提交贫困户信息
    				if ("1".equals(reulst)) {
    					Intent intent = new Intent(this,ArchivesActivity.class);
    					startActivity(intent);
    					finish();
    				}
    			}
    			break;
			case 101:
				try {
					Tools.i("selectByCountryman", reulst.toString());
					countryMan = JSON.parseObject(reulst, TdataCountryman.class);
	    			handler.sendEmptyMessage(204);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case 102:
				try {
					Tools.i("selectByAction", reulst.toString());
					tactions = JSON.parseArray(reulst, TdataAction.class);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case 103:
				try {
					Tools.i("selectByResult", reulst.toString());
					tresult = JSON.parseObject(reulst, TdataResult.class);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;

			default:
				break;
			}
    	}
	}
	
	private void getData(){
		if(rdfragment!=null){
			countryMan = rdfragment.getCountryman();
			Tools.i(TAG, "tdataCountryman结束"+countryMan.toString());
		}
		if(msfragment!=null){
			tactions = msfragment.getTdataActions();
			Tools.i(TAG, "tdataActions结束"+tactions.toString());
			}
		if(etfragment!=null){
			tresult = etfragment.getTdataResult();
			Tools.i(TAG, "tdataResult结束"+tresult.toString());
		}
		countryMan.setTdataActions(tactions);
		countryMan.setTdataResult(tresult);
		String data = JSON.toJSONString(countryMan);
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		map.put("arg0", data);
		map.put("arg1", "edit");
		RequestWebService.send("insertTDataCountryman", map, this, 100);
	}
	
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 204:
				setTabSelection(0);
				if(!rd){
					rd = true;
					rdfragment.setCountryMan(countryMan);
					Tools.i(TAG,  "countryMan开始="+countryMan.toString());
				}
				break;
			case 205:
				setTabSelection(1);
				if(tactions!=null&&tactions.size()>0&&!ms){
					ms = true;
					msfragment.setTdataAction(tactions);
					Tools.i(TAG, "tactions开始="+tactions.toString());
				}
				break;
			case 206:
				setTabSelection(2);
				if(!et){
					et = true;
					etfragment.setTdataResult(tresult);
					Tools.i(TAG, "tresult开始="+tresult.toString());
				}
				break;

			default:
				break;
			}
		};
	};
	
}
