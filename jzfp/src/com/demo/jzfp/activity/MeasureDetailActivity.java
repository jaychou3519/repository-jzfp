package com.demo.jzfp.activity;

import java.util.List;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.demo.jzfp.R;
import com.demo.jzfp.apdater.MeasureDetailAdapter;
import com.demo.jzfp.dao.DictDataInfoDao;
import com.demo.jzfp.dao.impl.DictDataInfoDaoImpl;
import com.demo.jzfp.database.DatabaseHelper;
import com.demo.jzfp.utils.Constant;
import com.demo.jzfp.utils.MyApplication;

public class MeasureDetailActivity extends BaseActivity implements OnClickListener {
	private int POSITION = 0;
	private GridView gv_checkbox;
	private EditText et_income, et_illustrate;
	private String title;
	private SQLiteDatabase db = null;
	private DictDataInfoDao dictDataDao = new DictDataInfoDaoImpl();

	@Override
	protected void setView() {
		setContentView(R.layout.activity_measure_detail);
		MyApplication activityList = (MyApplication) getApplicationContext();
		activityList.addActivity(this);
		Bundle bundle = getIntent().getExtras();
		title = bundle.getString("title");
		POSITION = bundle.getInt("position");
		setTitleText(title);
		setOnback(this);
	}

	@Override
	protected void initView() {
		TextView tv_save = (TextView) findViewById(R.id.tv_save);

		gv_checkbox = (GridView) findViewById(R.id.gv_checkbox);
		et_income = (EditText) findViewById(R.id.et_income);
		et_illustrate = (EditText) findViewById(R.id.et_illustrate);
		
		tv_save.setOnClickListener(this);
	}

	@Override
	protected void initData() {
		db = (new DatabaseHelper(this)).getWritableDatabase();
		List<String> strs = dictDataDao.queryDictValueByType(db, title);

		MeasureDetailAdapter adapter = new MeasureDetailAdapter(this, strs);
		gv_checkbox.setAdapter(adapter);
		
		if(!TextUtils.isEmpty(Constant.poor.getTdataAction().getActionMoney()))
			et_income.setText(Constant.poor.getTdataAction().getActionMoney());
		if(!TextUtils.isEmpty(Constant.poor.getTdataAction().getRemark()))
			et_illustrate.setText(Constant.poor.getTdataAction().getRemark());
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.tv_save:
			setData();
			finish();
			break;
		}
		
	}
	
	/**
	 * 设置贫因户信息
	 */
	private void setData(){
		if(("其他").equals(title) || ("政策性补助").equals(title)){
			Constant.poor.getTdataAction().setActionType("2");
		}else{
			Constant.poor.getTdataAction().setActionType("1");
		}
		Constant.poor.getTdataAction().setActionXl("");
		Constant.poor.getTdataAction().setActionDl("");
		Constant.poor.getTdataAction().setActionMoney(et_income.getText().toString().trim()+"");
		Constant.poor.getTdataAction().setRemark(et_illustrate.getText().toString().trim()+"");
	}
	

}
