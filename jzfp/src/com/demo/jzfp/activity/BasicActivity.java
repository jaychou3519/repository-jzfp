package com.demo.jzfp.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;import java.util.Map;
import com.demo.jzfp.R;
import com.demo.jzfp.apdater.BasicAdapter;
import com.demo.jzfp.apdater.EffectAdapter;
import com.demo.jzfp.dao.AreaDataDao;
import com.demo.jzfp.dao.impl.AreaDataDaoImpl;
import com.demo.jzfp.dao.impl.BasicDaoImpl;
import com.demo.jzfp.database.DatabaseHelper;
import com.demo.jzfp.entity.Basic;
import com.demo.jzfp.entity.Effect;
import com.demo.jzfp.utils.RequestWebService.WebServiceCallback;
import com.demo.jzfp.utils.MyApplication;
import com.demo.jzfp.utils.Tools;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class BasicActivity extends BaseActivity{

	@ViewInject(R.id.lv_listview)
	private ListView lv_listview;
	@ViewInject(R.id.tv_organization)
	private TextView tv_organization;
	@ViewInject(R.id.ll_add)
	private LinearLayout ll_add;
	@ViewInject(R.id.ll_name)
	private LinearLayout ll_name;
	@ViewInject(R.id.ll_phone)
	private LinearLayout ll_phone;
	@ViewInject(R.id.et_name)
	private EditText et_name;
	@ViewInject(R.id.et_phone)
	private EditText et_phone;
	
	private List<Basic> basics = new ArrayList<Basic>();
	private String[] string  = new String[]{"乡镇党委书记姓名","乡镇村","村书记","村主任"};
	//private List<Effect> basics1 = new ArrayList<Effect>();
	//private String[] string1 = new String[]{"责任单位","负责人","联系电话","结对帮扶责任人","职务","联系电话"};
	//private String[] string2 = new String[]{"炎陵镇财务局","财神爷","22034567","周密","科员","13908488899"};
	
	private AreaDataDao areaDataDao = new AreaDataDaoImpl();
	private BasicDaoImpl basicdao = new BasicDaoImpl();
	private SQLiteDatabase db = null;
	private Map map;
	private BasicAdapter adapter;
	private List<Map> maps;
	@Override
	protected void setView() {
		View view = View.inflate(this, R.layout.activity_basic, null);
		setContentView(view);
		ViewUtils.inject(this,view);
		setTitleText("帮扶基础信息");
		setOnback(this);
		db = (new DatabaseHelper(this)).getWritableDatabase();
	}

	@Override
	protected void initView() {
		for(int i=0;i<string.length;i++){
			Basic basic = new Basic();
			basic.setName_title(string[i]);
			basics.add(basic);
		}
	}

	@Override
	protected void initData() {
		maps = basicdao.queryBasicAll(db);
		adapter = new BasicAdapter(this, maps);
		lv_listview.setAdapter(adapter);
		List<Map> list = areaDataDao.queryAreaData(db);
		String id ;
		for (int i = 0; i < list.size(); i++) {
			if(MyApplication.login!=null){
				id = MyApplication.login.getOrgId();
			}else{
				SharedPreferences sp = getSharedPreferences("user", Context.MODE_PRIVATE);
				id = sp.getString("orgId", "");
			}
			if(id.equals(list.get(i).get("id"))){
				tv_organization.setText(list.get(i).get("name")+"");
				break;
			}
		}
	}

	@OnClick({R.id.ll_add,R.id.iv_save})
	public void mClick(View view){
		switch (view.getId()) {
		case R.id.ll_add:
			setGone(false);
			break;
		case R.id.iv_save:
			try {
				if(isSave()){
					basicdao.insertBasic(map, db);
					setGone(true);
					adapter = new BasicAdapter(BasicActivity.this, maps);
					lv_listview.setAdapter(adapter);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		default:
			break;
		}
	}
	private boolean isSave(){
		map = new HashMap();
		String name = et_name.getText().toString().trim();
		String phone = et_phone.getText().toString().trim();
		if(TextUtils.isEmpty(name)){
			Tools.showNewToast(this, "名字不能为空！");
			return false;
		}
		if(TextUtils.isEmpty(phone)){
			Tools.showNewToast(this, "电话不能为空！");
			return false;
		}
		map.put("name", name);
		map.put("phone", phone);
		maps.add(map);
		return true;
	}
	private void setGone(boolean isb){
		if(isb){
			ll_name.setVisibility(View.GONE);
			ll_phone.setVisibility(View.GONE);
		}else{
			ll_name.setVisibility(View.VISIBLE);
			ll_phone.setVisibility(View.VISIBLE);
		}
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(db!=null){
			db.close();
		}
	}
}
