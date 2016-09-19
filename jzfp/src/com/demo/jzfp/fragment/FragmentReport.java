package com.demo.jzfp.fragment;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.demo.jzfp.R;
import com.demo.jzfp.activity.AducationActivity;
import com.demo.jzfp.activity.ChooseAreaActivity;
import com.demo.jzfp.activity.EconomyActivity;
import com.demo.jzfp.activity.EffectActivity;
import com.demo.jzfp.activity.MeasuresActivity;
import com.demo.jzfp.activity.MemberActivity;
import com.demo.jzfp.activity.ReasonActivity;
import com.demo.jzfp.dao.DictDataInfoDao;
import com.demo.jzfp.dao.impl.DictDataInfoDaoImpl;
import com.demo.jzfp.database.DatabaseHelper;
import com.demo.jzfp.entity.TdataCountryman;
import com.demo.jzfp.utils.AbImageUtil;
import com.demo.jzfp.utils.Constant;
import com.demo.jzfp.utils.MyApplication;
import com.demo.jzfp.utils.PermissionsUtils;
import com.demo.jzfp.utils.PhotoUtils;
import com.demo.jzfp.utils.RequestWebService;
import com.demo.jzfp.utils.RequestWebService.WebServiceCallback;
import com.demo.jzfp.utils.Tools;
import com.demo.jzfp.view.WheelView;

public class FragmentReport extends Fragment implements OnClickListener, WebServiceCallback {
	private View v;
	private String sex = ""; // 姓别 0：未选 1：男 2：女
	private ImageView iv_sex_women, iv_headpicture;
	private TextView tv_sex_women;
	private ImageView iv_sex_man;
	private TextView tv_sex_man;
	private TextView tv_education;
	private PermissionsUtils pu;
	private AlertDialog dialog;
	private List<String> poorCards,states;
	private String state, poorCard, filePath;
	private int table;// 1:贫因状态 2:健康状态
	private TextView tv_state, tv_poorCard, tv_economy, tv_member, tv_reason, tv_measures, tv_effect, et_countryId;
	private EditText et_name, et_age, et_identity, et_tel;
	private PhotoUtils photoUtils;
	private SQLiteDatabase db = null;
	private DictDataInfoDao dictDataDao = new DictDataInfoDaoImpl();
	private Bitmap bitmap;
	private RelativeLayout rl_jindu;
	private String areacode;

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

		RelativeLayout rl_poorCard = (RelativeLayout) v.findViewById(R.id.rl_poorCard);
		tv_poorCard = (TextView) v.findViewById(R.id.tv_poorCard);

		RelativeLayout rl_economy = (RelativeLayout) v.findViewById(R.id.rl_economy);
		tv_economy = (TextView) v.findViewById(R.id.tv_economy);

		RelativeLayout rl_member = (RelativeLayout) v.findViewById(R.id.rl_member);
		tv_member = (TextView) v.findViewById(R.id.tv_member);

		RelativeLayout rl_reason = (RelativeLayout) v.findViewById(R.id.rl_reason);
		tv_reason = (TextView) v.findViewById(R.id.tv_reason);

		RelativeLayout rl_measures = (RelativeLayout) v.findViewById(R.id.rl_measures);
		tv_measures = (TextView) v.findViewById(R.id.tv_measures);

		RelativeLayout rl_effect = (RelativeLayout) v.findViewById(R.id.rl_effect);
		tv_effect = (TextView) v.findViewById(R.id.tv_effect);

		RelativeLayout rl_countryId = (RelativeLayout) v.findViewById(R.id.rl_countryId);
		et_countryId = (TextView) v.findViewById(R.id.et_countryId);
		
		if(MyApplication.login.getLoginName().equals("admin")){
			rl_countryId.setVisibility(View.VISIBLE);
		}

		rl_jindu = (RelativeLayout) v.findViewById(R.id.rl_jindu);

		// 设置点击事件
		tv_commit.setOnClickListener(this);
		rl_photo.setOnClickListener(this);
		rl_state.setOnClickListener(this);
		ll_women.setOnClickListener(this);
		ll_man.setOnClickListener(this);
		rl_education.setOnClickListener(this);
		rl_poorCard.setOnClickListener(this);
		rl_economy.setOnClickListener(this);
		rl_member.setOnClickListener(this);
		rl_reason.setOnClickListener(this);
		rl_measures.setOnClickListener(this);
		rl_effect.setOnClickListener(this);
		rl_countryId.setOnClickListener(this);
	}

	/**
	 * 初始化数据
	 */
	private void initData() {
		db = (new DatabaseHelper(getActivity())).getWritableDatabase();
		pu = new PermissionsUtils(getActivity());
		states = dictDataDao.queryDictValueByType(db, "poorState");
		poorCards = dictDataDao.queryDictValueByType(db, "poorCard");

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rl_photo:
			Log.i("haha", "头像");
			if (pu.camera()) {
				Log.i("haha", "打开照相机");
				photoUtils = new PhotoUtils(getActivity(), this.v, this);
				photoUtils.selectImage();
			}
			break;
		case R.id.rl_state:
			String title = "请选择贫因户状态";
			table = 1;
			showWheelView(title, (String[]) states.toArray(new String[states.size()]), table);
			break;
		case R.id.ll_women:
			sex = "女";
			iv_sex_women.setImageResource(R.drawable.woman_yes);
			tv_sex_women.setTextColor(Color.rgb(155, 89, 182));
			iv_sex_man.setImageResource(R.drawable.man_no);
			tv_sex_man.setTextColor(Color.rgb(220, 220, 200));
			break;
		case R.id.ll_man:
			sex = "男";
			iv_sex_women.setImageResource(R.drawable.woman_no);
			tv_sex_women.setTextColor(Color.rgb(220, 220, 220));
			iv_sex_man.setImageResource(R.drawable.man_yes);
			tv_sex_man.setTextColor(Color.rgb(52, 152, 219));
			break;
		case R.id.rl_education:
			Intent intent = new Intent(getActivity(), AducationActivity.class);
			startActivityForResult(intent, 300);
			break;
		case R.id.rl_poorCard:
			String title1 = "请选择贫困户属性";
			table = 2;
			showWheelView(title1, (String[]) poorCards.toArray(new String[poorCards.size()]), table);
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
		case R.id.rl_countryId:
			Intent intent6 = new Intent(getActivity(), ChooseAreaActivity.class);
			Bundle bundle = new Bundle();
			intent6.putExtras(bundle);
			startActivityForResult(intent6, 102);
			break;
		case R.id.txt_sure:
			if (table == 1) {
				if (TextUtils.isEmpty(state))
					state = states.get(1);
				tv_state.setText(state);
				state = "";
			} else if (table == 2) {
				if (TextUtils.isEmpty(poorCard))
					poorCard = poorCards.get(1);
				tv_poorCard.setText(poorCard);
				poorCard = "";
			}
			dialog.dismiss();
			break;
		case R.id.txt_cancel:
			dialog.dismiss();
			break;
		case R.id.tv_commit:
			if (TextUtils.isEmpty(et_name.getText().toString())) {
				Tools.showNewToast(getActivity(), "请填写姓名");
				return;
			} else if (TextUtils.isEmpty(tv_state.getText().toString())) {
				Tools.showNewToast(getActivity(), "请选择贫困户状态");
				return;
			} else if (TextUtils.isEmpty(sex)) {
				Tools.showNewToast(getActivity(), "请选择性别");
				return;
			} else if (TextUtils.isEmpty(et_age.getText().toString())) {
				Tools.showNewToast(getActivity(), "请填写年龄");
				return;
			} else if (TextUtils.isEmpty(et_identity.getText().toString())) {
				Tools.showNewToast(getActivity(), "请填写身份证号码");
				return;
			} else if (TextUtils.isEmpty(et_tel.getText().toString())) {
				Tools.showNewToast(getActivity(), "请填写联系电话");
				return;
			} else if (TextUtils.isEmpty(tv_education.getText().toString())) {
				Tools.showNewToast(getActivity(), "请选择文化程度");
				return;
			} else if (TextUtils.isEmpty(tv_poorCard.getText().toString())) {
				Tools.showNewToast(getActivity(), "请选择贫困户属性");
				return;
			} else if (TextUtils.isEmpty(tv_economy.getText().toString())) {
				Tools.showNewToast(getActivity(), "请填写家庭经济信息");
				return;
			} else if (TextUtils.isEmpty(tv_member.getText().toString())) {
				Tools.showNewToast(getActivity(), "请填写家庭成员信息");
				return;
			} else if (TextUtils.isEmpty(tv_reason.getText().toString())) {
				Tools.showNewToast(getActivity(), "请填写致贫原因信息");
				return;
			} else if (TextUtils.isEmpty(tv_economy.getText().toString())) {
				Tools.showNewToast(getActivity(), "请填写帮扶措施信息");
				return;
			} else if (TextUtils.isEmpty(tv_effect.getText().toString())) {
				Tools.showNewToast(getActivity(), "请填写帮扶成效信息");
				return;
			} 
			setData();
			rl_jindu.setVisibility(View.VISIBLE);
			uploadImage();
			break;
		}

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
		case 300:
			if (data != null && !TextUtils.isEmpty(data.getStringExtra("education")))
				tv_education.setText(data.getStringExtra("education"));
			break;
		case 102:
			if (data != null && !TextUtils.isEmpty(data.getStringExtra("name")))
				et_countryId.setText(data.getStringExtra("name"));
				areacode = data.getStringExtra("areacode");
			break;
		case PhotoUtils.REQUEST_TAKE_PICTURE:// 相机返回结果
			filePath = photoUtils.getFilePath();
			new Thread(new Runnable() {

				@Override
				public void run() {
					bitmap = Tools.Readimg(filePath);
					handler.sendEmptyMessage(101);
				}
			}).start();
			break;
		case PhotoUtils.REQUEST_PICK_PICTURE:// 相册返回结果
			if (data == null) {
				Tools.showNewToast(getActivity(), "获取照片失败！");
				return;
			}
			Uri uri = data.getData();
			filePath = AbImageUtil.getPath(uri, getActivity());
			new Thread(new Runnable() {

				@Override
				public void run() {
					bitmap = Tools.Readimg(filePath);
					handler.sendEmptyMessage(101);
				}
			}).start();

			break;
		}

	}

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 101:
				if (bitmap != null) {
					iv_headpicture.setImageBitmap(bitmap);
				}
				break;
			}
		};
	};

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
					poorCard = item;
				}
			}
		});
		dialog = new AlertDialog.Builder(getActivity()).setTitle(title).setView(outerView).show();

		TextView txtSure = (TextView) outerView.findViewById(R.id.txt_sure);
		TextView txtCancle = (TextView) outerView.findViewById(R.id.txt_cancel);
		txtSure.setOnClickListener(this);
		txtCancle.setOnClickListener(this);
	}

	@Override
	public void onStart() {
		super.onStart();
		if (TextUtils.isEmpty(Constant.poor.getZfjg()) && TextUtils.isEmpty(Constant.poor.getZzArea()) && TextUtils.isEmpty(Constant.poor.getGdArea()) && TextUtils.isEmpty(Constant.poor.getSlArea())
				&& TextUtils.isEmpty(Constant.poor.getRjsrqk())) {
			tv_economy.setText("");
		} else {
			tv_economy.setText("修改");
		}

		if (Constant.poor.getTdataFamilys() == null || Constant.poor.getTdataFamilys().isEmpty()) {
			tv_member.setText("");
		} else {
			tv_member.setText("修改");
		}

		if (TextUtils.isEmpty(Constant.poor.getPoorReason()) && TextUtils.isEmpty(Constant.poor.getRemark())) {
			tv_reason.setText("");
		} else {
			tv_reason.setText("修改");
		}

		if (Constant.poor.getTdataActions() == null || Constant.poor.getTdataActions().isEmpty()) {
			tv_measures.setText("");
		} else {
			tv_measures.setText("修改");
		}

		if (Constant.poor.getTdataHelper() == null && Constant.poor.getTdataResult() == null) {
			tv_effect.setText("");
		} else {
			tv_effect.setText("修改");
		}
	}

	@Override
	public void onStop() {
		super.onStop();
		setData();
	}

	/**
	 * 设置贫困户信息
	 */
	private void setData() {
		Constant.poor.setCountryman(et_name.getText().toString().trim() + "");
		String poorState = dictDataDao.queryDictCodeByValue(db, tv_state.getText().toString().trim() + "");
		Constant.poor.setPoorState(poorState);
		Constant.poor.setSex(sex);
		Constant.poor.setAge(et_age.getText().toString().trim() + "");
		Constant.poor.setCard(et_identity.getText().toString().trim() + "");
		Constant.poor.setTelphone(et_tel.getText().toString().trim() + "");
		Constant.poor.setCountrymanId(UUID.randomUUID().toString().replace("-", ""));

		if (!TextUtils.isEmpty(tv_education.getText().toString().trim())) {
			String whcd = dictDataDao.queryDictCodeByValue(db, tv_education.getText().toString().trim());
			Constant.poor.setWhcd(whcd);
		}
		if(MyApplication.login.getLoginName().equals("admin")){
			Constant.poor.setCountryId(areacode);
		}else{
			Constant.poor.setCountryId(MyApplication.login.getOrgId());
		}
		String poorCard = dictDataDao.queryDictCodeByValue(db, tv_poorCard.getText().toString().trim() + "");
		Constant.poor.setPoorCard(poorCard);
	}

	@Override
	public void result(String result, int requestCode) {
		// TODO Auto-generated method stub
		if (requestCode == 100) {// 提交贫困户信息
			if ("1".equals(result)) {
				Tools.showNewToast(getActivity(), "提交成功！");
				et_name.setText("");
				tv_state.setText("");
				iv_sex_women.setImageResource(R.drawable.woman_no);
				tv_sex_women.setTextColor(Color.rgb(220, 220, 220));
				iv_sex_man.setImageResource(R.drawable.man_no);
				tv_sex_man.setTextColor(Color.rgb(220, 220, 220));
				et_age.setText("");
				et_identity.setText("");
				et_tel.setText("");
				tv_education.setText("");
				tv_poorCard.setText("");
				tv_economy.setText("");
				tv_member.setText("");
				tv_reason.setText("");
				tv_measures.setText("");
				tv_effect.setText("");
				et_countryId.setText("");
				Constant.poor = new TdataCountryman();
			} else {
				Tools.showNewToast(getActivity(), "提交失败！");
			}
			rl_jindu.setVisibility(View.GONE);
		} else if (requestCode == 200) {// 上传图片
			if (TextUtils.isEmpty(result)) {
				Tools.showNewToast(getActivity(), "图片上传失败！");
				rl_jindu.setVisibility(View.GONE);
			} else {
				commit();
			}
		}
	}

	/**
	 * 图片上传
	 */
	private void uploadImage() {
		try {
			if (TextUtils.isEmpty(filePath)) {
				commit();
				return;
			}
			String imageBuffer = bitmapToString(filePath);
			if (TextUtils.isEmpty(imageBuffer)) {
				commit();
				return;
			}
			String methodName = "insertUploadImage";
			RequestWebService.uploadImage(this, Constant.poor.getCountrymanId(), imageBuffer, methodName, 200);
		} catch (Exception e) {
			e.printStackTrace();
			rl_jindu.setVisibility(View.GONE);
			Tools.showNewToast(getActivity().getApplication(), "图片上传失败");
		}

	}

	/**
	 * 提交
	 */
	private void commit() {
		String data = JSON.toJSONString(Constant.poor);
		Log.i("haha", data);
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		map.put("arg0", data);
		RequestWebService.send("insertTDataCountryman", map, this, 100);
	}

	// 根据路径获得图片并压缩，返回bitmap用于显示
	private Bitmap getSmallBitmap(String filePath) {
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(filePath, options);
		options.inSampleSize = calculateInSampleSize(options, 480, 800);
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeFile(filePath, options);
	}

	//计算图片的缩放值
	private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;
		if (height > reqHeight || width > reqWidth) {
			final int heightRatio = Math.round((float) height / (float) reqHeight);
			final int widthRatio = Math.round((float) width / (float) reqWidth);
			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
		}
		return inSampleSize;
	}
	
	//把bitmap转换成String 
	private String bitmapToString(String filePath) { 
		Bitmap bm = getSmallBitmap(filePath); 
		ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
		bm.compress(Bitmap.CompressFormat.JPEG, 40, baos); 
		byte[] b = baos.toByteArray(); 
		return Base64.encodeToString(b, Base64.DEFAULT); 
	} 

}
