package com.demo.jzfp.fragment;

import java.util.Arrays;

import com.demo.jzfp.R;
import com.demo.jzfp.activity.AducationActivity;
import com.demo.jzfp.activity.EconomyActivity;
import com.demo.jzfp.activity.EffectActivity;
import com.demo.jzfp.activity.MeasuresActivity;
import com.demo.jzfp.activity.MemberActivity;
import com.demo.jzfp.activity.ReasonActivity;
import com.demo.jzfp.utils.AbImageUtil;
import com.demo.jzfp.utils.PermissionsUtils;
import com.demo.jzfp.utils.PhotoUtils;
import com.demo.jzfp.utils.Tools;
import com.demo.jzfp.view.WheelView;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FragmentReport extends Fragment implements OnClickListener {
	private View v;
	private int sex = 0; // 姓别 0：未选 1：男 2：女
	private ImageView iv_sex_women,iv_headpicture;
	private TextView tv_sex_women;
	private ImageView iv_sex_man;
	private TextView tv_sex_man;
	private TextView tv_education;
	private PermissionsUtils pu;
	private AlertDialog dialog;
	private String[] states, healths;
	private String state, health,filePath;
	private int table;// 1:贫因状态 2:健康状态
	private TextView tv_state, tv_health;
	private EditText et_name, et_age, et_identity, et_tel;
	private PhotoUtils photoUtils;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		v = inflater.inflate(R.layout.fragment_report, null);
		initView();
		initData();
		return v;
	}

	/**
	 * 初始化view
	 */
	private void initView() {
		TextView tv_commit = (TextView) v.findViewById(R.id.tv_commit);
		
		RelativeLayout rl_photo = (RelativeLayout) v.findViewById(R.id.rl_photo);
		iv_headpicture = (ImageView) v.findViewById(R.id.iv_headpicture);

		et_name = (EditText) v.findViewById(R.id.et_name);

		RelativeLayout rl_state = (RelativeLayout) v.findViewById(R.id.rl_state);
		tv_state = (TextView) v.findViewById(R.id.tv_state);

		LinearLayout ll_women = (LinearLayout) v.findViewById(R.id.ll_women);
		iv_sex_women = (ImageView) v.findViewById(R.id.iv_sex_women);
		tv_sex_women = (TextView) v.findViewById(R.id.tv_sex_women);
		LinearLayout ll_man = (LinearLayout) v.findViewById(R.id.ll_man);
		iv_sex_man = (ImageView) v.findViewById(R.id.iv_sex_man);
		tv_sex_man = (TextView) v.findViewById(R.id.tv_sex_man);

		et_age = (EditText) v.findViewById(R.id.et_age);
		et_identity = (EditText) v.findViewById(R.id.et_identity);
		et_tel = (EditText) v.findViewById(R.id.et_tel);

		RelativeLayout rl_education = (RelativeLayout) v.findViewById(R.id.rl_education);
		tv_education = (TextView) v.findViewById(R.id.tv_education);

		RelativeLayout rl_health = (RelativeLayout) v.findViewById(R.id.rl_health);
		tv_health = (TextView) v.findViewById(R.id.tv_health);

		RelativeLayout rl_economy = (RelativeLayout) v.findViewById(R.id.rl_economy);
		TextView tv_economy = (TextView) v.findViewById(R.id.tv_economy);

		RelativeLayout rl_member = (RelativeLayout) v.findViewById(R.id.rl_member);
		TextView tv_member = (TextView) v.findViewById(R.id.tv_member);

		RelativeLayout rl_reason = (RelativeLayout) v.findViewById(R.id.rl_reason);
		TextView tv_reason = (TextView) v.findViewById(R.id.tv_reason);

		RelativeLayout rl_measures = (RelativeLayout) v.findViewById(R.id.rl_measures);
		TextView tv_measures = (TextView) v.findViewById(R.id.tv_measures);

		RelativeLayout rl_effect = (RelativeLayout) v.findViewById(R.id.rl_effect);
		TextView tv_effect = (TextView) v.findViewById(R.id.tv_effect);

		// 设置点击事件
		tv_commit.setOnClickListener(this);
		rl_photo.setOnClickListener(this);
		rl_state.setOnClickListener(this);
		ll_women.setOnClickListener(this);
		ll_man.setOnClickListener(this);
		rl_education.setOnClickListener(this);
		rl_health.setOnClickListener(this);
		rl_economy.setOnClickListener(this);
		rl_member.setOnClickListener(this);
		rl_reason.setOnClickListener(this);
		rl_measures.setOnClickListener(this);
		rl_effect.setOnClickListener(this);
	}

	/**
	 * 初始化数据
	 */
	private void initData() {
		pu = new PermissionsUtils(getActivity());
		states = new String[] { "精准识别户", "一般贫困户", "五保户", "低保户" };
		healths = new String[] { "患有大病", "残疾", "长期慢性病", "健康" };
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rl_photo:
			//if (pu.camera()) {
				Tools.showNewToast(getActivity(), "进入....相机");
				photoUtils = new PhotoUtils(getActivity(), v,this);
				photoUtils.selectImage();
			//}
			break;
		case R.id.rl_state:
			String title = "请选择贫因户状态";
			table = 1;
			showWheelView(title, states, table);
			break;
		case R.id.ll_women:
			sex = 2;
			iv_sex_women.setImageResource(R.drawable.woman_yes);
			tv_sex_women.setTextColor(Color.rgb(155, 89, 182));
			iv_sex_man.setImageResource(R.drawable.man_no);
			tv_sex_man.setTextColor(Color.rgb(220, 220, 200));
			break;
		case R.id.ll_man:
			sex = 1;
			iv_sex_women.setImageResource(R.drawable.woman_no);
			tv_sex_women.setTextColor(Color.rgb(220, 220, 220));
			iv_sex_man.setImageResource(R.drawable.man_yes);
			tv_sex_man.setTextColor(Color.rgb(52, 152, 219));
			break;
		case R.id.rl_education:
			Intent intent = new Intent(getActivity(), AducationActivity.class);
			startActivityForResult(intent, 300);
			break;
		case R.id.rl_health:
			String title1 = "请选择健康状态";
			table = 2;
			showWheelView(title1, healths, table);
			break;
		case R.id.rl_economy:
			Intent intent1 = new Intent(getActivity(), EconomyActivity.class);
			startActivity(intent1);
			break;
		case R.id.rl_member:
			Intent intent2 = new Intent(getActivity(), MemberActivity.class);
			startActivity(intent2);
			break;
		case R.id.rl_reason:
			Intent intent3 = new Intent(getActivity(), ReasonActivity.class);
			startActivity(intent3);
			break;
		case R.id.rl_measures:
			Intent intent4 = new Intent(getActivity(), MeasuresActivity.class);
			startActivity(intent4);
			break;
		case R.id.rl_effect:
			Intent intent5 = new Intent(getActivity(), EffectActivity.class);
			startActivity(intent5);
			break;
		case R.id.txt_sure:
			if (table == 1) {
				tv_state.setText(state);
			} else if (table == 2) {
				tv_health.setText(health);
			}
			dialog.dismiss();
			break;
		case R.id.txt_cancel:
			dialog.dismiss();
			break;
		case R.id.tv_commit:
			if(TextUtils.isEmpty(et_name.getText().toString())){
				Tools.showNewToast(getActivity(), "请填写姓名");
				return;
			}else if(TextUtils.isEmpty(tv_state.getText().toString())){
				Tools.showNewToast(getActivity(), "请选择贫困户状态");
				return;
			}else if(sex == 0){
				Tools.showNewToast(getActivity(), "请选择性别");
				return;
			}else if(TextUtils.isEmpty(et_age.getText().toString())){
				Tools.showNewToast(getActivity(), "请填写年龄");
				return;
			}else if(TextUtils.isEmpty(et_identity.getText().toString())){
				Tools.showNewToast(getActivity(), "请填写身份证号码");
				return;
			}else if(TextUtils.isEmpty(et_tel.getText().toString())){
				Tools.showNewToast(getActivity(), "请填写联系电话");
				return;
			}else if(TextUtils.isEmpty(tv_education.getText().toString())){
				Tools.showNewToast(getActivity(), "请选择文化程度");
				return;
			}else if(TextUtils.isEmpty(tv_health.getText().toString())){
				Tools.showNewToast(getActivity(), "请选择健康状态");
				return;
			}
			
			break;
		}

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
			switch (requestCode) {
			case 300:
				tv_education.setText(data.getStringExtra("education"));
				break;
			case PhotoUtils.REQUEST_TAKE_PICTURE:// 相机返回结果
				filePath = photoUtils.getFilePath();
				Tools.showNewToast(getActivity(), "filePath="+filePath);
				Bitmap bitmap = Tools.Readimg(filePath);
				if(bitmap!=null){
					iv_headpicture.setImageBitmap(bitmap);
				}
				break;
			case PhotoUtils.REQUEST_PICK_PICTURE:// 相册返回结果
				if (data == null) {
					Tools.showNewToast(getActivity(), "获取照片失败！");
					return;
				}
				Uri uri = data.getData();
				filePath = AbImageUtil.getPath(uri,getActivity());
				Tools.showNewToast(getActivity(), "filePath="+filePath);
				Bitmap bitmaps = Tools.Readimg(filePath);
				if(bitmaps!=null){
					iv_headpicture.setImageBitmap(bitmaps);
				}
				break;
		}

	}

	/**
	 * 选择弹出窗
	 * 
	 * @param title
	 *            弹出窗标题
	 * @param strs
	 *            弹出窗选择内容
	 * @param table
	 *            弹出窗标记
	 */
	private void showWheelView(String title, String[] strs, final int table) {
		View outerView = LayoutInflater.from(getActivity()).inflate(R.layout.wheel_view, null);
		WheelView wv = (WheelView) outerView.findViewById(R.id.wheel_view_wv);
		wv.setOffset(1);
		wv.setItems(Arrays.asList(strs));
		wv.setSeletion(1);
		wv.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
			@Override
			public void onSelected(int selectedIndex, String item) {
				if (table == 1) {
					state = item;
				} else if (table == 2) {
					health = item;
				}
			}
		});
		dialog = new AlertDialog.Builder(getActivity()).setTitle(title).setView(outerView).show();

		TextView txtSure = (TextView) outerView.findViewById(R.id.txt_sure);
		TextView txtCancle = (TextView) outerView.findViewById(R.id.txt_cancel);
		txtSure.setOnClickListener(this);
		txtCancle.setOnClickListener(this);
	}
}
