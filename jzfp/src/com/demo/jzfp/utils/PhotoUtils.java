package com.demo.jzfp.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import com.demo.jzfp.R;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;

public class PhotoUtils {
	private String TAG = "PhotoUtils";
	private Button bt_cancle, bt_change_photo, bt_change_album;// 取消，相机，相册
	private View viewhead, view;
	private File mCurrentPhotoFile;// 照相机拍照得到的图片
	public static final int REQUEST_PICK_PICTURE = 0x1955;
	public static final int REQUEST_TAKE_PICTURE = 0x1956;
	private Activity activity;
	private Fragment fragment;
	public PhotoUtils() {
	}

	public PhotoUtils(Activity activity, View view,Fragment fragment) {
		this.activity = activity;
		this.fragment = fragment;
		this.view = view;
		initView();
		initDate();
		formatContent();
	}

	private void initView() {
	}

	private void initDate() {
	}

	private void formatContent() {
	}

	/**
	 * 选择图片的方式
	 */
	public void selectImage() {
		viewhead = View.inflate(activity, R.layout.pop_headphoto_layout, null);
		Tools.showPopWindow2(activity, view, viewhead, Tools.getScreenWidth(activity));
		bt_cancle = (Button) viewhead.findViewById(R.id.bt_cancle);
		bt_change_photo = (Button) viewhead.findViewById(R.id.bt_change_photo);
		bt_change_album = (Button) viewhead.findViewById(R.id.bt_change_album);
		bt_change_album.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Tools.removeDialog(viewhead);
				Intent intent1 = new Intent();
				intent1.setAction(Intent.ACTION_PICK);
				intent1.setType("image/*");
				try {
					fragment.startActivityForResult(intent1, REQUEST_PICK_PICTURE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		bt_cancle.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Tools.removeDialog(viewhead);
			}
		});
		bt_change_photo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Tools.removeDialog(viewhead);
				try {
					String mFileName = System.currentTimeMillis() + ".jpg";
					mCurrentPhotoFile = new File(AbFileUtil.getCacheDownloadDir(activity), mFileName);
					Intent intent = new Intent();
					intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
					intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mCurrentPhotoFile));
					intent.putExtra("return-data", true);
					try {
						fragment.startActivityForResult(intent, REQUEST_TAKE_PICTURE);
					} catch (Exception e) {
						e.printStackTrace();
					}

				} catch (Exception e) {
					Tools.showNewToast(activity, activity.getResources().getString(R.string.system_camera_not_found));
				}
			}
		});
	}

	public String getFilePath() {
		return mCurrentPhotoFile.getAbsolutePath().trim();
	}
}
