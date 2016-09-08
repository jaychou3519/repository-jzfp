package com.demo.jzfp.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

public class PermissionsUtils {
	//请求码只能设256以下
	public static final int PERMISSIONS_PHONE = 10;// 关于电话权限请求码
	public static final int PERMISSIONS_CAMERA = 20;// 关于照相机权限请求码
	public static final int PERMISSIONS_STORAGE = 30;// 关于存储权限请求码
	public static final int PERMISSIONS_LOCATION = 40;// 关于位置权限请求码
	public static final int PERMISSIONS_CONTACTS = 50;// 关于联系人权限请求码
	public static final int PERMISSIONS_SMS = 60;// 关于短信权限请求码
	private Activity activity;

	public PermissionsUtils(Activity activity) {
		this.activity = activity;
	}

	// 电话
	public boolean phone() {
		String[] permissions = { Manifest.permission.CALL_PHONE };// 需要请求的所有权限
		int permissionCheck = ActivityCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE);
		if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
			ActivityCompat.requestPermissions(activity, permissions, PERMISSIONS_PHONE);
			return false;
		}
		return true;
	}

	// 照相机
	public boolean camera() {
		String[] permissions = { Manifest.permission.CAMERA };
		int permissionCheck = ActivityCompat.checkSelfPermission(activity, Manifest.permission.CAMERA);
		if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
			ActivityCompat.requestPermissions(activity, permissions, PERMISSIONS_CAMERA);
			return false;
		}
		return true;
	}

	// 存储
	public boolean storage() {
		String[] permissions = { Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE };
		int permissionCheck = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
		if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
			ActivityCompat.requestPermissions(activity, permissions, PERMISSIONS_STORAGE);
			return false;
		}
		return true;
	}

	// 位置
	public boolean location() {
		String[] permissions = { Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION };
		int permissionCheck = ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION);
		if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
			ActivityCompat.requestPermissions(activity, permissions, PERMISSIONS_LOCATION);
			return false;
		}
		return true;
	}

	// 联系人
	public boolean contacts() {
		String[] permissions = { Manifest.permission.READ_CONTACTS };
		int permissionCheck = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_CONTACTS);
		if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
			ActivityCompat.requestPermissions(activity, permissions, PERMISSIONS_CONTACTS);
			return false;
		}
		return true;
	}

	// 短信
	public boolean sms() {
		String[] permissions = { Manifest.permission.READ_SMS };
		int permissionCheck = ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_SMS);
		if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
			ActivityCompat.requestPermissions(activity, permissions, PERMISSIONS_SMS);
			return false;
		}
		return true;
	}

}
