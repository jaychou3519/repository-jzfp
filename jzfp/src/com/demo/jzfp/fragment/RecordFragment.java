package com.demo.jzfp.fragment;

import java.util.LinkedHashMap;

import com.alibaba.fastjson.JSON;
import com.demo.jzfp.R;
import com.demo.jzfp.apdater.RecordAdapter;
import com.demo.jzfp.dao.DictDataInfoDao;
import com.demo.jzfp.dao.impl.DictDataInfoDaoImpl;
import com.demo.jzfp.database.DatabaseHelper;
import com.demo.jzfp.entity.TdataAction;
import com.demo.jzfp.entity.TdataCountryman;
import com.demo.jzfp.entity.TdataHelper;
import com.demo.jzfp.entity.TdataResult;
import com.demo.jzfp.utils.Constant;
import com.demo.jzfp.utils.RequestWebService;
import com.demo.jzfp.utils.Tools;
import com.demo.jzfp.utils.Asynce_NetWork.AsynceHttpInterface;
import com.demo.jzfp.utils.RequestWebService.WebServiceCallback;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.app.Fragment;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

public class RecordFragment extends Fragment implements AsynceHttpInterface,
OnClickListener, WebServiceCallback {

	private ScrollView sv_scroll;
	private ListView lv_listview;
	private RecordAdapter adapter;
	private SQLiteDatabase db = null;
	private DictDataInfoDao dictDataDao = new DictDataInfoDaoImpl();
	private TextView tv_name,tv_state,tv_gender,tv_idcard,tv_age,tv_phone,tv_educational
					,tv_home_flat,tv_home_area,tv_plough_area,tv_mountains_area,et_organization,et_name,et_name_job,et_tel,et_Captain
					,et_Village,et_dwsjName,et_dwsjPhone,et_zzName,et_zzPhone,et_csjName,et_csjPhone,et_czrName,et_czrPhone
					,tv_reason,tv_explain,jtcy;  /*tv_year_money,*/
	private ImageView iv_photo;
	private TdataCountryman countryman;
	private TdataHelper tHelper;
	private TdataResult Tresult;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_record, null);
		initView(view);
		return view;
	}

	private void initView(View view) {
		sv_scroll = (ScrollView) view.findViewById(R.id.sv_scroll);
		lv_listview = (ListView) view.findViewById(R.id.lv_listview);
		tv_name = 	(TextView) view.findViewById(R.id.tv_name);
		tv_state = 	(TextView) view.findViewById(R.id.tv_state);
		tv_gender = 	(TextView) view.findViewById(R.id.tv_gender);
		tv_idcard = 	(TextView) view.findViewById(R.id.tv_idcard);
		tv_age = 	(TextView) view.findViewById(R.id.tv_age);
		tv_phone = 	(TextView) view.findViewById(R.id.tv_phone);
		tv_educational = 	(TextView) view.findViewById(R.id.tv_educational);
		tv_home_flat = 	(TextView) view.findViewById(R.id.tv_home_flat);
		tv_home_area = 	(TextView) view.findViewById(R.id.tv_home_area);
		tv_plough_area = 	(TextView) view.findViewById(R.id.tv_plough_area);
		tv_mountains_area = 	(TextView) view.findViewById(R.id.tv_mountains_area);
		/*tv_year_money = 	(TextView) view.findViewById(R.id.tv_year_money);*/
		tv_reason = 	(TextView) view.findViewById(R.id.tv_reason);
		tv_explain = 	(TextView) view.findViewById(R.id.tv_explain);
		iv_photo = 	(ImageView) view.findViewById(R.id.iv_photo);
		jtcy = (TextView) view.findViewById(R.id.jtcy);
		et_organization = 	(TextView) view.findViewById(R.id.et_organization);//结对帮扶单位
		et_name = 	(TextView) view.findViewById(R.id.et_name); //帮扶责任人
		et_name_job = 	(TextView) view.findViewById(R.id.et_name_job); //帮扶责任人职务
		et_tel = 	(TextView) view.findViewById(R.id.et_tel);//联系电话
		et_Captain = 	(TextView) view.findViewById(R.id.et_Captain);//驻村队长信息
		et_Village = 	(TextView) view.findViewById(R.id.et_Village);//队长联系电话
		et_dwsjName = 	(TextView) view.findViewById(R.id.et_dwsjName);
		et_dwsjPhone = 	(TextView) view.findViewById(R.id.et_dwsjPhone);
		et_zzName = 	(TextView) view.findViewById(R.id.et_zzName);//乡（镇）长姓名
		et_zzPhone = 	(TextView) view.findViewById(R.id.et_zzPhone);//乡（镇）长电话
		et_csjName = 	(TextView) view.findViewById(R.id.et_csjName);//村书记姓名
		et_csjPhone = 	(TextView) view.findViewById(R.id.et_csjPhone);//村书记电话
		et_czrName = 	(TextView) view.findViewById(R.id.et_czrName);//村主任姓名
		et_czrPhone = 	(TextView) view.findViewById(R.id.et_czrPhone);//村主任电话
		sv_scroll.smoothScrollTo(0, 20);
		sv_scroll.setFocusable(true);
	}
	
	private void initData(){
		db = (new DatabaseHelper(getActivity())).getWritableDatabase();
		if(countryman!=null) {
			tv_name.setText(Tools.parseEmpty(countryman.getCountryman()));
			tv_state.setText(dictDataDao.queryValueByDictCode(db,countryman.getPoorState(),"poorState"));
			tv_gender.setText(Tools.parseEmpty(countryman.getSex()));
			tv_idcard.setText(Tools.parseEmpty(countryman.getCard()));
			tv_age.setText(Tools.parseEmpty(countryman.getAge()));
			tv_phone.setText(Tools.parseEmpty(countryman.getTelphone()));
			String whcd = dictDataDao.queryValueByDictCode(db,countryman.getWhcd(),"whcd");
			tv_educational.setText(whcd);
			tv_home_flat.setText(Tools.parseEmpty(countryman.getZfjg()));
			tv_home_area.setText(Tools.parseEmpty(countryman.getZzArea()));
			tv_plough_area.setText(Tools.parseEmpty(countryman.getGdArea()));
			tv_mountains_area.setText(Tools.parseEmpty(countryman.getSlArea()));
			/*tv_year_money.setText(Tools.parseEmpty(countryman.getRjsrqk()));*/
			tv_reason.setText(Tools.parseEmpty(countryman.getPoorReason()));
			tv_explain.setText(Tools.parseEmpty(countryman.getRemark()));
			jtcy.setText("家庭成员(" + countryman.getTdataFamilys().size() + ")");
			
			if(!TextUtils.isEmpty(countryman.getPkhimg()))
				ImageLoader.getInstance().displayImage(countryman.getPkhimg(), iv_photo);
			adapter = new RecordAdapter(getActivity(),countryman.getTdataFamilys());
			lv_listview.setAdapter(adapter);
			Tools.setListViewHeight(lv_listview);
			sv_scroll.smoothScrollTo(0, 20);
			sv_scroll.setFocusable(true);
		}
		
		LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
		linkedHashMap.put("arg0", countryman.getCountrymanId());
		RequestWebService.send("selectByHelper", linkedHashMap, this, 101);
		
		/*if(Tresult==null) return;
		et_Captain.setText(Tools.parseEmpty(Tresult.getzcgjddz()));*/
	}
	public void setCountryMan(TdataCountryman countryMan){
		this.countryman = countryMan;
		initData();
	}
	
	public TdataCountryman getCountryMan(){
		TdataCountryman tdataCountryman = new TdataCountryman();
		return null;
	}

	@Override
	public void result(String reulst, int requestCode) {
		if(reulst == null){
			Tools.showNewToast(getActivity(), "链接服务器失败");
		}else{
    		switch (requestCode) {
			case 101:
				try {
					Tools.i("selectByHelper", reulst.toString());
					tHelper = JSON.parseObject(reulst, TdataHelper.class);
					et_organization.setText(Tools.parseEmpty(tHelper.getJdbfzrOrgname()));
					et_name.setText(Tools.parseEmpty(tHelper.getJdbfzrOrger()));
					et_name_job.setText(Tools.parseEmpty(tHelper.getJdbfzrOrgname()));
					et_tel.setText(Tools.parseEmpty(tHelper.getBfzrrPhone()));
					//et_Village.setText(Tools.parseEmpty(tHelper.getZzArea()));
					et_dwsjName.setText(Tools.parseEmpty(tHelper.getDwsjName()));
					et_dwsjPhone.setText(Tools.parseEmpty(tHelper.getDwsjPhone()));
					et_zzName.setText(Tools.parseEmpty(tHelper.getZzName()));
					et_zzPhone.setText(Tools.parseEmpty(tHelper.getZzPhone()));
					et_csjName.setText(Tools.parseEmpty(tHelper.getCsjName()));
					et_csjPhone.setText(Tools.parseEmpty(tHelper.getCxjPhone()));
					et_czrName.setText(Tools.parseEmpty(tHelper.getCzrName()));
					et_czrPhone.setText(Tools.parseEmpty(tHelper.getCzrPhone()));
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			default:
				break;
			}
    	}
	}

	@Override
	public void onClick(View v) {
		
	}

	@Override
	public void getNetData(int requestCode, String data) {
		
	}

	@Override
	public void requestDefeated(int requestCode, String data) {
		
	}
}
