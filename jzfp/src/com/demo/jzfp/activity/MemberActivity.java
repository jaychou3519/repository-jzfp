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
import com.demo.jzfp.entity.Member;
import com.demo.jzfp.utils.Constant;
import com.demo.jzfp.utils.MyApplication;

public class MemberActivity extends BaseActivity implements OnClickListener {
	private MyApplication activityList;
	private ListView lv_member;
	private MemberAdapter adapter;
	private List<Member> members = new ArrayList<Member>();

	@Override
	protected void setView() {
		setContentView(R.layout.activity_member);
		activityList = (MyApplication) getApplicationContext();
		activityList.addActivity(this);
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
		members.addAll(Constant.members);
		if (members.isEmpty()) {
			members.add(new Member());
		}
		Constant.members.add(new Member());
		adapter = new MemberAdapter(this,Constant.members);
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
			Constant.members.add(new Member());
			adapter.notifyDataSetChanged();
			break;
		}
	}

	private void setData(){
		for (int i = 0; i < members.size(); i++) {
			Member member = members.get(i);
			if(TextUtils.isEmpty(member.getMemberName()) || TextUtils.isEmpty(member.getYhzgx()) || TextUtils.isEmpty(member.getMemberSex())){
				members.remove(i);
				i--;
			}
		}
		Constant.members = members;
		
	}
}
