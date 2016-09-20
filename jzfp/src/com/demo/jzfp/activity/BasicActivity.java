package com.demo.jzfp.activity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.demo.jzfp.R;
import com.demo.jzfp.dao.AreaDataDao;
import com.demo.jzfp.dao.impl.AreaDataDaoImpl;
import com.demo.jzfp.dao.impl.BasicDaoImpl;
import com.demo.jzfp.database.DatabaseHelper;
import com.demo.jzfp.utils.MyApplication;
import com.demo.jzfp.utils.StringUtil;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class BasicActivity extends BaseActivity implements OnClickListener{

	@ViewInject(R.id.lv_listview)
	private ListView lv_listview;
	@ViewInject(R.id.tv_organization)
	private TextView tv_organization;
	private TextView tx_xzdwsj,tx_xzdwsjdh,tx_xzz,tx_xzzdh,tx_csj,tx_csjdh,tx_czr,tx_czrdh,tv_commit;
	private EditText et_xzdwsj,et_xzdwsjdh,et_xzz,et_xzzdh,et_csj,et_csjdh,et_czr,et_czrdh;
	@ViewInject(R.id.ll_add)
	private LinearLayout ll_add;
	@ViewInject(R.id.ll_name)
	private LinearLayout ll_name;
	@ViewInject(R.id.ll_phone)
	private LinearLayout ll_phone;
	@ViewInject(R.id.et_name)
	private EditText et_name;
	private ImageView iv_save;
	
	private AreaDataDao areaDataDao = new AreaDataDaoImpl();
	private BasicDaoImpl basicdao = new BasicDaoImpl();
	private SQLiteDatabase db = null;
	private Map map;
	
	@Override
	protected void setView() {
		View view = View.inflate(this, R.layout.activity_basic, null);
		setContentView(view);
		ViewUtils.inject(this,view);
		setTitleText("基础信息");
		setOnback(this);
		db = (new DatabaseHelper(this)).getWritableDatabase();
	}

	@Override
	protected void initView() {
		tx_xzdwsj = (TextView) findViewById(R.id.tx_xzdwsj);
		tx_xzdwsjdh = (TextView)findViewById(R.id.tx_xzdwsjdh);
		tx_xzz = (TextView)findViewById(R.id.tx_xzz);
		tx_xzzdh = (TextView)findViewById(R.id.tx_xzzdh);
		tx_csj = (TextView)findViewById(R.id.tx_csj);
		tx_csjdh = (TextView)findViewById(R.id.tx_csjdh);
		tx_czr = (TextView)findViewById(R.id.tx_czr);
		tx_czrdh = (TextView)findViewById(R.id.tx_czrdh);
		tv_commit = (TextView)findViewById(R.id.tv_commit);
		iv_save = (ImageView)findViewById(R.id.iv_save);
		et_xzdwsj = (EditText) findViewById(R.id.et_xzdwsj);
		et_xzdwsjdh = (EditText) findViewById(R.id.et_xzdwsjdh);
		et_xzz = (EditText) findViewById(R.id.et_xzz);
		et_xzzdh = (EditText) findViewById(R.id.et_xzzdh);
		et_csj = (EditText) findViewById(R.id.et_csj);
		et_csjdh = (EditText) findViewById(R.id.et_csjdh);
		et_czr = (EditText) findViewById(R.id.et_czr);
		et_czrdh = (EditText) findViewById(R.id.et_czrdh);
		
		iv_save.setOnClickListener(this);
		tv_commit.setOnClickListener(this);
	}

	@Override
	protected void initData() {
		map = basicdao.queryBasicAll(db);
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
		
		tx_xzdwsj.setText(StringUtil.toString(map.get("xzdwsj")));
		tx_xzdwsjdh.setText(StringUtil.toString(map.get("xzdwsjdh")));
		tx_xzz.setText(StringUtil.toString(map.get("xzz")));
		tx_xzzdh.setText(StringUtil.toString(map.get("xzzdh")));
		tx_csj.setText(StringUtil.toString(map.get("csj")));
		tx_csjdh.setText(StringUtil.toString(map.get("csjdh")));
		tx_czr.setText(StringUtil.toString(map.get("czr")));
		tx_czrdh.setText(StringUtil.toString(map.get("czrdh")));
	}

	private void save(){
		Map map2 = new HashMap();
		String xzdwsj = et_xzdwsj.getText().toString().trim();
		String xzdwsjdh = et_xzdwsjdh.getText().toString().trim();
		String xzz = et_xzz.getText().toString().trim();
		String xzzdh = et_xzzdh.getText().toString().trim();
		String csj = et_csj.getText().toString().trim();
		String csjdh = et_csjdh.getText().toString().trim();
		String czr = et_czr.getText().toString().trim();
		String czrdh = et_czrdh.getText().toString().trim();
		
		map2.put("xzdwsj", xzdwsj);
		map2.put("xzdwsjdh", xzdwsjdh);
		map2.put("xzz", xzz);
		map2.put("xzzdh", xzzdh);
		map2.put("csj", csj);
		map2.put("csjdh", csjdh);
		map2.put("czr", czr);
		map2.put("czrdh", czrdh);
		if(null == map || map.size()==0){
			try {
				basicdao.insertBasic(map2, db);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			map2.put("id", "1");
			basicdao.updateBasic(db,map2);
		}
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(db!=null){
			db.close();
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.tv_commit:
				save();
				initData();
				iv_save.setVisibility(View.VISIBLE);
				tv_commit.setVisibility(View.GONE);
				et_xzdwsj.setVisibility(View.GONE);
				et_xzdwsjdh.setVisibility(View.GONE);
				et_xzz.setVisibility(View.GONE);
				et_xzzdh.setVisibility(View.GONE);
				et_csj.setVisibility(View.GONE);
				et_csjdh.setVisibility(View.GONE);
				et_czr.setVisibility(View.GONE);
				et_czrdh.setVisibility(View.GONE);
				tx_xzdwsj.setVisibility(View.VISIBLE);
				tx_xzdwsjdh.setVisibility(View.VISIBLE);
				tx_xzz.setVisibility(View.VISIBLE);
				tx_xzzdh.setVisibility(View.VISIBLE);
				tx_csj.setVisibility(View.VISIBLE);
				tx_csjdh.setVisibility(View.VISIBLE);
				tx_czr.setVisibility(View.VISIBLE);
				tx_czrdh.setVisibility(View.VISIBLE);
				break;
			case R.id.iv_save:
				iv_save.setVisibility(View.GONE);
				tv_commit.setVisibility(View.VISIBLE);
				tx_xzdwsj.setVisibility(View.GONE);
				tx_xzdwsjdh.setVisibility(View.GONE);
				tx_xzz.setVisibility(View.GONE);
				tx_xzzdh.setVisibility(View.GONE);
				tx_csj.setVisibility(View.GONE);
				tx_csjdh.setVisibility(View.GONE);
				tx_czr.setVisibility(View.GONE);
				tx_czrdh.setVisibility(View.GONE);
				et_xzdwsj.setVisibility(View.VISIBLE);
				et_xzdwsjdh.setVisibility(View.VISIBLE);
				et_xzz.setVisibility(View.VISIBLE);
				et_xzzdh.setVisibility(View.VISIBLE);
				et_csj.setVisibility(View.VISIBLE);
				et_csjdh.setVisibility(View.VISIBLE);
				et_czr.setVisibility(View.VISIBLE);
				et_czrdh.setVisibility(View.VISIBLE);
				break;
			default:
				break;
		}
	}
}
