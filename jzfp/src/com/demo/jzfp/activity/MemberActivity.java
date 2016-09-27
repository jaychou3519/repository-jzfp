package com.demo.jzfp.activity;

import java.util.ArrayList;
import java.util.List;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.demo.jzfp.R;
import com.demo.jzfp.apdater.MemberAdapter;
import com.demo.jzfp.entity.TdataFamily;
import com.demo.jzfp.utils.Constant;
import com.demo.jzfp.utils.MyApplication;

public class MemberActivity extends BaseActivity implements OnClickListener {
	private ListView lv_member;
	private MemberAdapter adapter;
	private List<TdataFamily> members = new ArrayList<TdataFamily>();

	@Override
	protected void setView() {
		setContentView(R.layout.activity_member);
		MyApplication.addActivity(MemberActivity.this);
		setTitleText("家庭成员");
		setOnback(this);
	}

	@Override
	protected void initView() {
		TextView tv_save = (TextView) findViewById(R.id.tv_save);
		LinearLayout ll_add = (LinearLayout) findViewById(R.id.ll_add);
		
		lv_member = (ListView) findViewById(R.id.lv_member);

		tv_save.setOnClickListener(this);
		ll_add.setOnClickListener(this);
	}

	@Override
	protected void initData() {
		if(Constant.poor.getTdataFamilys() != null )
			members.addAll(Constant.poor.getTdataFamilys());
		if (members.isEmpty()) {
			members.add(new TdataFamily());
		}
		adapter = new MemberAdapter(this,members);
		lv_member.setAdapter(adapter);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_save:
			setData();
			finish();
			break;
		case R.id.ll_add:
			members.add(new TdataFamily());
			adapter.notifyDataSetChanged();
			break;
		}
	}

	private void setData(){
		for (int i = 0; i < members.size(); i++) {
			TdataFamily member = members.get(i);
			if(TextUtils.isEmpty(member.getFamilyName()) || TextUtils.isEmpty(member.getYhzgx()) || TextUtils.isEmpty(member.getSex())){
				members.remove(i);
				i--;
			}
		}
		Constant.poor.setTdataFamilys(members);
		
	}
}
