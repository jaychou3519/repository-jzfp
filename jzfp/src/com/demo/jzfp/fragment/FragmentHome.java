package com.demo.jzfp.fragment;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import com.demo.jzfp.R;
import com.demo.jzfp.activity.VillagesActivity;
import com.demo.jzfp.apdater.ImageAdapter;
import com.demo.jzfp.utils.Tools;
import com.demo.jzfp.utils.Asynce_NetWork;
import com.demo.jzfp.utils.Asynce_NetWork.AsynceHttpInterface;
import com.demo.jzfp.utils.Constant;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

public class FragmentHome extends BaseFragment implements AsynceHttpInterface{
	private static final String TAG = "FragmentHome";
	private ViewPager viewPager;
	private ImageHandler handler = new ImageHandler(new WeakReference<FragmentHome>(this));
	private boolean isboolean = false;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		GetWeather();
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_home, null);
		initView(v, inflater);
		return v;
	}

	private void initView(View v, LayoutInflater inflater) {
		viewPager = (ViewPager) v.findViewById(R.id.vp_lunbo_tp);

		ImageView view1 = new ImageView(getActivity());
		view1.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		ImageView view2 = new ImageView(getActivity());
		view2.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		//ImageView view2 = (ImageView) inflater.inflate(R.layout.imageview_item, null);
		view1.setBackgroundResource(R.drawable.lunbo1);
		view2.setBackgroundResource(R.drawable.lunbo2);
		ArrayList<ImageView> views = new ArrayList<ImageView>();
		views.add(view1);
		views.add(view2);
		viewPager.setAdapter(new ImageAdapter(views));
		viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			// 配合Adapter的currentItem字段进行设置。
			@Override
			public void onPageSelected(int arg0) {
				handler.sendMessage(Message.obtain(handler, ImageHandler.MSG_PAGE_CHANGED, arg0, 0));
				if(!isboolean){
					isboolean = true;
					Tools.setOpenActivity(getActivity(), VillagesActivity.class);
				}
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
					handler.sendEmptyMessageDelayed(ImageHandler.MSG_UPDATE_IMAGE, ImageHandler.MSG_DELAY);
					break;
				default:
					break;
				}
			}
		});
		viewPager.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Tools.setOpenActivity(getActivity(), VillagesActivity.class);
			}
		});
		viewPager.setCurrentItem(0);// 默认在中间，使用户看不到边界
		// 开始轮播效果
		handler.sendEmptyMessageDelayed(ImageHandler.MSG_UPDATE_IMAGE, ImageHandler.MSG_DELAY);
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
				activity.handler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
				break;
			case MSG_KEEP_SILENT:
				// 只要不发送消息就暂停了
				break;
			case MSG_BREAK_SILENT:
				activity.handler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
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
	
	@Override
	public void onStop() {
		super.onStop();
		isboolean = false;
	}

	private void GetWeather(){
		Asynce_NetWork.asyncHttpGet(getActivity(), Constant.weather, FragmentHome.this, 101);
	}
	@Override
	public void getNetData(int requestCode, String data) {
		switch (requestCode) {
		case 101:
			Tools.i(TAG, "data="+data.toString());
			break;

		default:
			break;
		}
	}

	@Override
	public void requestDefeated(int requestCode, String data) {
		
	}
}
