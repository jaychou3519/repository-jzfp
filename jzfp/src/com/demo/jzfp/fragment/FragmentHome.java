package com.demo.jzfp.fragment;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.demo.jzfp.R;
import com.demo.jzfp.activity.ArchivesActivity;
import com.demo.jzfp.activity.ArchivesActivity2;
import com.demo.jzfp.activity.ArchivesPoorActivity;
import com.demo.jzfp.activity.SupportActivity;
import com.demo.jzfp.activity.VillagesActivity2;
import com.demo.jzfp.activity.VillagesTextActivity2;
import com.demo.jzfp.apdater.ImageAdapter;
import com.demo.jzfp.dao.AreaDataDao;
import com.demo.jzfp.dao.impl.AreaDataDaoImpl;
import com.demo.jzfp.database.DatabaseHelper;
import com.demo.jzfp.entity.CountryMans;
import com.demo.jzfp.entity.Root;
import com.demo.jzfp.entity.Villages;
import com.demo.jzfp.utils.Asynce_NetWork;
import com.demo.jzfp.utils.Asynce_NetWork.AsynceHttpInterface;
import com.demo.jzfp.utils.Constant;
import com.demo.jzfp.utils.MyApplication;
import com.demo.jzfp.utils.RequestWebService;
import com.demo.jzfp.utils.RequestWebService.WebServiceCallback;
import com.demo.jzfp.utils.Tools;
import com.demo.jzfp.view.FlowLayout;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.event.OnClick;

public class FragmentHome extends BaseFragment implements AsynceHttpInterface,
		OnClickListener, WebServiceCallback {
	private static final String TAG = "FragmentHome";
	private ViewPager viewPager;
	private ImageHandler handler = new ImageHandler(
			new WeakReference<FragmentHome>(this));
	private TextView tv_year_mday, tv_week, tv_time, tv_county, tv_temperature,
			tv_temperature_range, tv_search;
	private EditText ed_search;
	private List<CountryMans> countrys;

	//
	private SQLiteDatabase db = null;
	private List<Map> maps;
	private AreaDataDao areaDataDao = new AreaDataDaoImpl();

	private LayoutInflater inf = null;
	private FlowLayout mFlowLayout = null;;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		GetWeather();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_home, null);
		this.inf = inflater;
		mFlowLayout = (FlowLayout) v.findViewById(R.id.id_flowlayout);
		initView(v, inflater);
		initData();

		ViewUtils.inject(this, v);
		return v;
	}

	private void initView(View v, LayoutInflater inflater) {
		viewPager = (ViewPager) v.findViewById(R.id.vp_lunbo_tp);
		tv_year_mday = (TextView) v.findViewById(R.id.tv_year_mday);
		tv_week = (TextView) v.findViewById(R.id.tv_week);
		tv_time = (TextView) v.findViewById(R.id.tv_time);
		tv_county = (TextView) v.findViewById(R.id.tv_county);
		tv_temperature = (TextView) v.findViewById(R.id.tv_temperature);
		tv_temperature_range = (TextView) v
				.findViewById(R.id.tv_temperature_range);
		tv_search = (TextView) v.findViewById(R.id.tv_search);
		ed_search = (EditText) v.findViewById(R.id.ed_search);

		ed_search.addTextChangedListener(new MyTextWatcher());
		tv_search.setOnClickListener(this);

		ImageView view1 = new ImageView(getActivity());
		view1.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));
		ImageView view2 = new ImageView(getActivity());
		view2.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));
		view1.setBackgroundResource(R.drawable.lunbo1);
		view2.setBackgroundResource(R.drawable.lunbo2);
		ArrayList<ImageView> views = new ArrayList<ImageView>();
		views.add(view1);
		views.add(view2);
		viewPager.setAdapter(new ImageAdapter(views, getActivity()));
		viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			// 配合Adapter的currentItem字段进行设置。
			@Override
			public void onPageSelected(int arg0) {
				handler.sendMessage(Message.obtain(handler,
						ImageHandler.MSG_PAGE_CHANGED, arg0, 0));
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			// 覆写该方法实现轮播效果的暂停和恢复
			@Override
			public void onPageScrollStateChanged(int arg0) {
				switch (arg0) {
				case ViewPager.SCROLL_STATE_DRAGGING:
					handler.sendEmptyMessage(ImageHandler.MSG_KEEP_SILENT);
					break;
				case ViewPager.SCROLL_STATE_IDLE:
					handler.sendEmptyMessageDelayed(
							ImageHandler.MSG_UPDATE_IMAGE,
							ImageHandler.MSG_DELAY);
					break;
				default:
					break;
				}
			}

		});
		viewPager.setCurrentItem(0);// 默认在中间，使用户看不到边界
		// 开始轮播效果
		handler.sendEmptyMessageDelayed(ImageHandler.MSG_UPDATE_IMAGE,
				ImageHandler.MSG_DELAY);
	}

	private void GetWeather() {
		Asynce_NetWork.asyncHttpGet(getActivity(), Constant.weather,
				FragmentHome.this, 101);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_search:
			if (!TextUtils.isEmpty(ed_search.getText().toString().trim()))
				;
			SearchPoor(ed_search.getText().toString().trim());
			break;

		default:
			break;
		}
	}

	@Override
	public void getNetData(int requestCode, String data) {
		JSONObject json;
		switch (requestCode) {
		case 101:
			Tools.i(TAG, "data=" + data.toString());
			try {
				json = new JSONObject(data);
				JSONArray jsonArrays = json.getJSONArray("results");
				Root results = new Root();
				results = Tools.GG().fromJson(json.toString(), Root.class);
				if (results == null) {
					Tools.showNewToast(getActivity(), "获取天气失败！");
					return;
				}
				tv_year_mday.setText(results.getDate());
				// "周三 09月07日 (实时：30℃)"
				String date = results.getResults().get(0).getWeather_data()
						.get(0).getDate();
				if (date.contains("周")) {
					int weeklocation = date.indexOf("周");
					tv_week.setText("星期"
							+ date.substring(weeklocation + 1, weeklocation + 2));
				}
				if (date.contains("实时：")) {
					int weeklocation = date.indexOf("实时：");
					tv_temperature.setText(date.substring(weeklocation + 3,
							weeklocation + 5) + "°");
				}
				String temperature = results.getResults().get(0)
						.getWeather_data().get(0).getTemperature();
				if (temperature.contains(" ~ ")) {
					int start = temperature.indexOf("~");
					int end = temperature.indexOf("℃");
					tv_temperature_range.setText(temperature.substring(
							start + 2, end)
							+ "°/"
							+ temperature.substring(0, start - 1) + "°");
				}

			} catch (JSONException e) {
				e.printStackTrace();
			}

			break;

		default:
			break;
		}
	}

	@Override
	public void requestDefeated(int requestCode, String data) {

	}

	@OnClick({ R.id.rl_archives, R.id.rl_policy, R.id.rl_info,R.id.rl_chengx })
	public void mClick(View view) {
		switch (view.getId()) {
		case R.id.rl_archives:
			Tools.setOpenActivity(getActivity(), ArchivesActivity.class);
			break;
		case R.id.rl_policy:
			Tools.setOpenActivity(getActivity(), SupportActivity.class);
			break;
		case R.id.rl_info:
			Tools.setOpenActivity(getActivity(), VillagesActivity2.class);
			break;
		case R.id.rl_chengx:
//			Tools.setOpenActivity(getActivity(), BasicActivity.class);
			Tools.setOpenActivity(getActivity(), ArchivesActivity2.class);
			break;
		}
	}

	private static class ImageHandler extends Handler {
		/**
		 * 请求更新显示的View。
		 */
		protected static final int MSG_UPDATE_IMAGE = 1;
		/**
		 * 请求暂停轮播。
		 */
		protected static final int MSG_KEEP_SILENT = 2;
		/**
		 * 请求恢复轮播。
		 */
		protected static final int MSG_BREAK_SILENT = 3;
		/**
		 * 记录最新的页号，当用户手动滑动时需要记录新页号，否则会使轮播的页面出错。
		 * 例如当前如果在第一页，本来准备播放的是第二页，而这时候用户滑动到了末页，
		 * 则应该播放的是第一页，如果继续按照原来的第二页播放，则逻辑上有问题。
		 */
		protected static final int MSG_PAGE_CHANGED = 4;

		// 轮播间隔时间
		protected static final long MSG_DELAY = 3000;

		// 使用弱引用避免Handler泄露.这里的泛型参数可以不是Activity，也可以是Fragment等
		private WeakReference<FragmentHome> weakReference;
		private int currentItem = 0;

		protected ImageHandler(WeakReference<FragmentHome> wk) {
			weakReference = wk;
		}

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			Log.d(TAG, "receive message " + msg.what);
			FragmentHome activity = weakReference.get();
			if (activity == null) {
				// Activity已经回收，无需再处理UI了
				return;
			}
			// 检查消息队列并移除未发送的消息，这主要是避免在复杂环境下消息出现重复等问题。
			if (activity.handler.hasMessages(MSG_UPDATE_IMAGE)) {
				activity.handler.removeMessages(MSG_UPDATE_IMAGE);
			}
			switch (msg.what) {
			case MSG_UPDATE_IMAGE:
				currentItem++;
				activity.viewPager.setCurrentItem(currentItem);
				// 准备下次播放
				activity.handler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE,
						MSG_DELAY);
				break;
			case MSG_KEEP_SILENT:
				// 只要不发送消息就暂停了
				break;
			case MSG_BREAK_SILENT:
				activity.handler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE,
						MSG_DELAY);
				break;
			case MSG_PAGE_CHANGED:
				// 记录当前的页号，避免播放的时候页面显示不正确。
				currentItem = msg.arg1;
				break;
			default:
				break;
			}
		}
	}

	class MyTextWatcher implements TextWatcher {

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {

		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			if (s.toString().trim().length() > 0) {
				// SearchPoor(s.toString().trim());
			}
		}

		@Override
		public void afterTextChanged(Editable s) {
		}

	}

	private void SearchPoor(String data) {
		LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
		if (MyApplication.login != null) {
			linkedHashMap.put("arg0", MyApplication.login.getOrgId() + "");
		} else {
			SharedPreferences sp = getActivity().getSharedPreferences("user",
					Context.MODE_PRIVATE);
			linkedHashMap.put("arg0", sp.getString("orgId", "") + "");
		}
		linkedHashMap.put("arg1", ed_search.getText().toString().trim());
		RequestWebService.send("selectToCountrymans", linkedHashMap, this, 101);
		Tools.i("selectToCountrymans", linkedHashMap.values().toString());
	}

	@Override
	public void result(String reulst, int requestCode) {
		if (reulst == null) {
			Tools.showNewToast(getActivity(), "链接服务器失败");
		} else {
			if (requestCode == 101) {
				try {
					Tools.i("selectToCountrymans", reulst.toString());
					countrys = JSON.parseArray(reulst, CountryMans.class);
					Bundle bundle = new Bundle();
					bundle.putBoolean("state", false);
					bundle.putSerializable("countrys", (Serializable) countrys);
					Tools.setOpenActivityBundle(getActivity(),
							ArchivesPoorActivity.class, bundle);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (requestCode == 102) {
				try {
					final Villages village = JSON.parseObject(reulst, Villages.class);
					TextView tv = (TextView) inf.inflate(R.layout.search_label_tv, mFlowLayout, false);
					tv.setText(village.getOrgName());
					tv.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View arg0) {
							Bundle bundle = new Bundle();
							bundle.putString("content", village.getRemark());
							bundle.putString("title", village.getOrgName());
							Tools.setOpenActivityBundle(getMainActivity(), VillagesTextActivity2.class,bundle);
						}
					});
					mFlowLayout.addView(tv);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}

		}
	}

	protected void initData() {
		db = (new DatabaseHelper(getMainActivity())).getWritableDatabase();
		maps = areaDataDao.queryAreaData(db);
		for (int i = 0; i < maps.size(); i++) {
			if (!maps.get(i).get("name").toString().contains("村")) {
				LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
				linkedHashMap.put("arg0", maps.get(i).get("id") + "");
				RequestWebService.send("selectorgRemark", linkedHashMap, this,102);
			}
		}
	}
}
