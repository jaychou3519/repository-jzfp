package com.demo.jzfp.fragment;

import java.util.Arrays;
import java.util.List;

import com.demo.jzfp.R;
import com.demo.jzfp.activity.AducationActivity;
import com.demo.jzfp.activity.ReasonActivity;
import com.demo.jzfp.apdater.RecordAdapter;
import com.demo.jzfp.apdater.RecordAdapterEdit;
import com.demo.jzfp.dao.DictDataInfoDao;
import com.demo.jzfp.dao.impl.DictDataInfoDaoImpl;
import com.demo.jzfp.database.DatabaseHelper;
import com.demo.jzfp.entity.TdataCountryman;
import com.demo.jzfp.utils.AbImageUtil;
import com.demo.jzfp.utils.PermissionsUtils;
import com.demo.jzfp.utils.PhotoUtils;
import com.demo.jzfp.utils.Tools;
import com.demo.jzfp.view.WheelView;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

public class RecordFragmentEdit extends Fragment implements OnClickListener{

	private ScrollView sv_scroll;
	private ListView lv_listview;
	private RecordAdapterEdit adapter;
	private SQLiteDatabase db = null;
	private DictDataInfoDao dictDataDao = new DictDataInfoDaoImpl();
	private TextView tv_state,tv_gender,tv_educational
					,tv_home_flat,tv_reason;
	private EditText ed_name,ed_age,ed_phone,ed_home_area,ed_plough_area,ed_mountains_area,ed_year_money,ed_explain,ed_idcard;
	private ImageView iv_photo;
	private TdataCountryman countryman;
	private AlertDialog dialog;
	private int table;// 1:贫因状态 2:健康状态
	private List<String> poorCards,states;
	private String state, poorCard,reason,construction,gender,filePath;
	private PhotoUtils photoUtils;
	private PermissionsUtils pu;
	private View view;
	private Bitmap bitmap;
	private String[] gedners = new String[] { "男", "女"};
	private String[] constructions = new String[] { "土坯", "砖瓦"};
	private String[] reasons = new String[] { "因学", "因灾", "缺资源、耕地", "因交通、电力等条件", "缺资金", "缺技术", "因病", "其 他", "缺劳动力" };
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_record_edit, null);
		initView(view);
		initData();
		return view;
	}

	private void initView(View view) {
		sv_scroll = (ScrollView) view.findViewById(R.id.sv_scroll);
		lv_listview = (ListView) view.findViewById(R.id.lv_listview);
		ed_name = 	(EditText) view.findViewById(R.id.ed_name);
		tv_state = 	(TextView) view.findViewById(R.id.tv_state);
		tv_gender = 	(TextView) view.findViewById(R.id.tv_gender);
		ed_idcard = 	(EditText) view.findViewById(R.id.ed_idcard);
		ed_age = 	(EditText) view.findViewById(R.id.ed_age);
		ed_phone = 	(EditText) view.findViewById(R.id.ed_phone);
		tv_educational = 	(TextView) view.findViewById(R.id.tv_educational);
		tv_home_flat = 	(TextView) view.findViewById(R.id.tv_home_flat);
		ed_home_area = 	(EditText) view.findViewById(R.id.ed_home_area);
		ed_plough_area = 	(EditText) view.findViewById(R.id.ed_plough_area);
		ed_mountains_area = 	(EditText) view.findViewById(R.id.ed_mountains_area);
		ed_year_money = 	(EditText) view.findViewById(R.id.ed_year_money);
		tv_reason = 	(TextView) view.findViewById(R.id.tv_reason);
		ed_explain = 	(EditText) view.findViewById(R.id.ed_explain);
		iv_photo = 	(ImageView) view.findViewById(R.id.iv_photo);
		
		tv_state.setOnClickListener(this);
		iv_photo.setOnClickListener(this);
		tv_educational.setOnClickListener(this);
		tv_educational.setOnClickListener(this);
		tv_home_flat.setOnClickListener(this);
		tv_reason.setOnClickListener(this);
		tv_gender.setOnClickListener(this);
		ed_name.setFocusable(true);
		ed_name.setSelection(ed_name.getText().toString().length());
	}
	
	private void initData(){
		db = (new DatabaseHelper(getActivity())).getWritableDatabase();
		pu = new PermissionsUtils(getActivity());
		states = dictDataDao.queryDictValueByType(db, "poorState");
		poorCards = dictDataDao.queryDictValueByType(db, "poorCard");
	}
	
	private void SetData(){
		db = (new DatabaseHelper(getActivity())).getWritableDatabase();
		if(countryman==null) return;
		ed_name.setText(Tools.parseEmpty(countryman.getCountryman())+"");
		tv_state.setText(dictDataDao.queryValueByDictCode(db,countryman.getPoorState(),"poorState"));
		tv_gender.setText(Tools.parseEmpty(countryman.getSex()));
		ed_idcard.setText(Tools.parseEmpty(countryman.getCard()));
		ed_age.setText(Tools.parseEmpty(countryman.getAge()));
		ed_phone.setText(Tools.parseEmpty(countryman.getTelphone()));
		String whcd = dictDataDao.queryValueByDictCode(db,countryman.getWhcd(),"whcd");
		tv_educational.setText(whcd);
		tv_home_flat.setText(Tools.parseEmpty(countryman.getZfjg()));
		ed_home_area.setText(Tools.parseEmpty(countryman.getZzArea()));
		ed_plough_area.setText(Tools.parseEmpty(countryman.getGdArea()));
		ed_mountains_area.setText(Tools.parseEmpty(countryman.getSlArea()));
		ed_year_money.setText(Tools.parseEmpty(countryman.getRjsrqk()));
		tv_reason.setText(Tools.parseEmpty(countryman.getPoorReason()));
		ed_explain.setText(Tools.parseEmpty(countryman.getRemark()));
		
		if(!TextUtils.isEmpty(countryman.getPkhimg()))
			ImageLoader.getInstance().displayImage(countryman.getPkhimg(), iv_photo);
		filePath = countryman.getPkhimg();
		
		adapter = new RecordAdapterEdit(getActivity(),countryman.getTdataFamilys());
		lv_listview.setAdapter(adapter);
		Tools.setListViewHeight(lv_listview);
		sv_scroll.smoothScrollTo(0, 20);
		sv_scroll.setFocusable(true);
		ed_name.setSelection(ed_name.getText().toString().length());
		
	}
	public void setCountryMan(TdataCountryman countryMan){
		this.countryman = countryMan;
		SetData();
	}

	public TdataCountryman getCountryman() {
		countryman.setCountryman(ed_name.getText().toString().trim());
		countryman.setSex(tv_gender.getText().toString().trim());
		countryman.setCard(ed_idcard.getText().toString().trim());
		countryman.setAge(ed_age.getText().toString().trim());
		countryman.setTelphone(ed_phone.getText().toString().trim());
		countryman.setZfjg(tv_home_flat.getText().toString().trim());
		countryman.setZzArea(ed_home_area.getText().toString().trim());
		countryman.setGdArea(ed_plough_area.getText().toString().trim());
		countryman.setSlArea(ed_mountains_area.getText().toString().trim());
		countryman.setRjsrqk(ed_year_money.getText().toString().trim());
		countryman.setPoorReason(tv_reason.getText().toString().trim());
		countryman.setRemark(ed_explain.getText().toString().trim());
		countryman.setPoorState(dictDataDao.queryDictCodeByValue(db,tv_state.getText().toString().trim(),"poorState"));
		countryman.setWhcd(dictDataDao.queryDictCodeByValue(db,tv_educational.getText().toString().trim(),"whcd"));
		countryman.setPkhimg(filePath);
		return countryman;
	}
	@Override
	public void onClick(View v) {
		String title = "";
		switch (v.getId()) {
		case R.id.iv_photo:
			/*if (pu.camera()) {
				Log.i("haha", "打开照相机");
				photoUtils = new PhotoUtils(getActivity(), view, this);
				photoUtils.selectImage();
			}*/
			break;
		case R.id.tv_state:
			title = "请选择贫因户状态";
			table = 1;
			showWheelView(title, (String[]) states.toArray(new String[states.size()]), table);
			break;
		case R.id.txt_sure:
			switch (table) {
			case 1:
				if (TextUtils.isEmpty(state))
					state = states.get(1);
				tv_state.setText(state);
				state = "";
				break;
			case 2:
				if (TextUtils.isEmpty(poorCard))
					poorCard = poorCards.get(1);
				//tv_poorCard.setText(poorCard);
				poorCard = "";
				break;
			case 3:
				if(TextUtils.isEmpty(reason))
					reason = reasons[1];
				tv_reason.setText(reason);
				reason = "";
				break;
			case 4:
				if(TextUtils.isEmpty(construction))
					construction = constructions[1];
				tv_home_flat.setText(construction);
				construction = "";
				break;
			case 5:
				if(TextUtils.isEmpty(gender))
					gender = gedners[1];
				tv_gender.setText(gender);
				gender = "";
				break;
			}
			dialog.dismiss();
			break;
		case R.id.txt_cancel:
			dialog.dismiss();
			break;
		case R.id.tv_educational:
			Intent intent = new Intent(getActivity(), AducationActivity.class);
			startActivityForResult(intent, 300);
			break;
		case R.id.tv_reason:
			title = "请选择致贫原因";
			table = 3;
			showWheelView(title, reasons, table);
			break;
		case R.id.tv_home_flat:
			title = "请选择住房结构";
			table = 4;
			showWheelView(title, constructions, table);
			break;
		case R.id.tv_gender:
			title = "请选择性别";
			table = 5;
			showWheelView(title, gedners, table);
			break;
		default:
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
				switch (table) {
				case 1:
					state = item;
					break;
				case 2:
					poorCard = item;
					break;
				case 3:
					reason = item;
					break;
				case 4:
					construction = item;
					break;
				case 5:
					gender = item;
					break;
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
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case 300:
			if (data != null && !TextUtils.isEmpty(data.getStringExtra("education")))
				tv_educational.setText(data.getStringExtra("education"));
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
					iv_photo.setImageBitmap(bitmap);
				}
				break;
			}
		};
	};
	
}

