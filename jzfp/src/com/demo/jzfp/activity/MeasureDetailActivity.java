package com.demo.jzfp.activity;


import java.util.ArrayList;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.demo.jzfp.R;
import com.demo.jzfp.apdater.MeasureDetailAdapter;
import com.demo.jzfp.dao.TdataConfigDao;
import com.demo.jzfp.dao.impl.TdataConfigDaoImpl;
import com.demo.jzfp.database.DatabaseHelper;
import com.demo.jzfp.entity.TdataAction;
import com.demo.jzfp.utils.Constant;
import com.demo.jzfp.utils.MyApplication;

public class MeasureDetailActivity extends BaseActivity implements OnClickListener {
	private GridView gv_checkbox;
	private EditText et_income, et_illustrate;
	private String title;
	private SQLiteDatabase db = null;
	private TdataConfigDao tdataConfigDao = new TdataConfigDaoImpl();
	private TdataAction action;
	private static StringBuffer sb;

	@Override
	protected void setView() {
		setContentView(R.layout.activity_measure_detail);
		MyApplication.addActivity(MeasureDetailActivity.this);
		Bundle bundle = getIntent().getExtras();
		title = bundle.getString("title");
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
		action = new TdataAction();
		db = (new DatabaseHelper(this)).getWritableDatabase();
		String strs = tdataConfigDao.queryActionXlByActionDl(db, title);
		String[] values = strs.split(",");
		
		if(Constant.poor.getTdataActions() != null){
			for (int i = 0; i < Constant.poor.getTdataActions().size(); i++) {
				TdataAction tdataAction = Constant.poor.getTdataActions().get(i);
				int x = 0;
				if(title.equals(tdataAction.getActionDl())){
					if(!TextUtils.isEmpty(tdataAction.getActionMoney()))
						et_income.setText(tdataAction.getActionMoney());
					if(!TextUtils.isEmpty(tdataAction.getRemark()))
						et_illustrate.setText(tdataAction.getRemark());
					sb = new StringBuffer(TextUtils.isEmpty(tdataAction.getActionXl()) ? "" : tdataAction.getActionXl());
					x = 1;
					break;
				}
				
				if(x == 0)
					sb = new StringBuffer();
			}
		}else{
			sb = new StringBuffer();
		}
		
		MeasureDetailAdapter adapter = new MeasureDetailAdapter(this, values, sb);
		gv_checkbox.setAdapter(adapter);
		
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
	 * 保存帮扶措施信息
	 */
	private void setData(){
		if(TextUtils.isEmpty(et_income.getText().toString()) && TextUtils.isEmpty(sb.toString()) && TextUtils.isEmpty(et_illustrate.getText().toString()))
			return;
		
		if(("其他").equals(title) || ("政策性补助").equals(title)){
			action.setActionType("2");
		}else{
			action.setActionType("1");
		}
		action.setActionXl(sb.toString());
		Log.i("haha", "sb---"+sb.toString());
		String actionDlCode = tdataConfigDao.queryActionDlCodeByActionDl(db, title);
		action.setActionDl(actionDlCode);
		//action.setActionDl(title);
		/*action.setActionDlCode(actionDlCode);*/
		action.setActionMoney(et_income.getText().toString().trim()+"");
		action.setRemark(et_illustrate.getText().toString().trim()+"");
		if(Constant.poor.getTdataActions() != null){
			int x = 0;
			for (int i = 0; i < Constant.poor.getTdataActions().size(); i++) {
				TdataAction tdataAction = Constant.poor.getTdataActions().get(i);
				if(title.equals(tdataAction.getActionDl())){
					x = 1;
					Constant.poor.getTdataActions().remove(i);
					Constant.poor.getTdataActions().add(action);
					break;
				}
			}
			if(x == 0)
				Constant.poor.getTdataActions().add(action);
		} else {
			Constant.poor.setTdataActions(new ArrayList<TdataAction>());
			Constant.poor.getTdataActions().add(action);
		}
	}
	
	public static void setXl(StringBuffer str){
		sb = str;
	}
	
}
