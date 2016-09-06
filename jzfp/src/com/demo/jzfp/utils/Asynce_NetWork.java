package com.demo.jzfp.utils;

import org.apache.http.Header;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

/**
 * 异步网络请求
 */
public class Asynce_NetWork {
	static AsyncHttpClient http = new AsyncHttpClient();
	static {
		http.setTimeout(15000);// 设置超时
	}

	/**
	 * 网络请求对外接口
	 * 
	 * @author gxf
	 *
	 */
	public interface AsynceHttpInterface {
		/**
		 * 网络请求成功
		 * 
		 * @param requestCode
		 *            对应请求码
		 * @param data
		 *            返回网络数据
		 */
		public void getNetData(int requestCode, String data);

		/**
		 * 网络请求失败
		 * 
		 * @param requestCode
		 *            对应请求码
		 * @param data
		 *            返回网络数据
		 */
		public void requestDefeated(int requestCode, String data);
	}

	/**
	 * get请求
	 * 
	 * @param context
	 * 
	 * @param url
	 *            请求地址
	 * @param ahi
	 *            传入 接口
	 * @param repuestCode
	 *            对应请求码
	 */
	public static void asyncHttpGet(final Context context, final String url, final AsynceHttpInterface ahi, final int requestCode) {
		http.get(url, new TextHttpResponseHandler() {
			@Override
			public void onFailure(int arg0, Header[] arg1, String arg2, Throwable arg3) {
				Log.i("haha", "请求失败" + url);
				ahi.requestDefeated(requestCode, arg2);
			}

			@Override
			public void onSuccess(int arg0, Header[] arg1, String arg2) {
				Log.i("haha", url + "---" + arg2);
				ahi.getNetData(requestCode, arg2);
			}
		});
	}

	/**
	 * post请求
	 * 
	 * @return
	 */
	public static void asyncHttpPost(final Context context, final String url, RequestParams params, final AsynceHttpInterface ahi, final int requestCode) {
		http.post(url, params, new TextHttpResponseHandler() {
			@Override
			public void onFailure(int arg0, Header[] arg1, String arg2, Throwable arg3) {
				Log.i("haha", "请求失败" + url);
				ahi.requestDefeated(requestCode, arg2);
			}

			@Override
			public void onSuccess(int arg0, Header[] arg1, String arg2) {
				Log.i("haha", url + "---" + arg2);
				ahi.getNetData(requestCode, arg2);
			}
		});
	}

	/**
	 * 文件下载
	 * 
	 * @param url
	 * @param params
	 * @param ahi
	 * @param requestCode
	 * @param context
	 */
	public static RequestHandle downLoadFile(final String url, RequestParams params, final UpDownInterface udi, final int requestCode, final Context context) {
		RequestHandle rh = http.get(url, new BinaryHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				// 上传成功后要做的工作
				udi.onSuccess(requestCode, responseBody);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
				// 上传失败后要做到工作
				udi.onFailure(requestCode, responseBody);
			}

			@Override
			public void onProgress(int bytesWritten, int totalSize) {
				// TODO Auto-generated method stub
				super.onProgress(bytesWritten, totalSize);
				// 下载进度
				udi.onProgress(bytesWritten, totalSize);
			}

			@Override
			public void onRetry(int retryNo) {
				// TODO Auto-generated method stub
				super.onRetry(retryNo);
				// 返回重复次数
				udi.onRetry(retryNo);
			}
		});
		return rh;
	}

	/**
	 * 上传文件
	 * 
	 * @param url
	 * @param params
	 * @param ahi
	 * @param requestCode
	 * @param context
	 */
	public static RequestHandle uploadFile(final String url, RequestParams params, final UpDownInterface udi, final int requestCode, final Context context) {
		// 上传文件
		RequestHandle rh = http.post(url, params, new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				// 上传成功后要做的工作
				udi.onSuccess(requestCode, responseBody);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
				// 上传失败后要做到工作
				udi.onFailure(requestCode, responseBody);
			}

			@Override
			public void onProgress(int bytesWritten, int totalSize) {
				// TODO Auto-generated method stub
				super.onProgress(bytesWritten, totalSize);
				// 上传进度
				udi.onProgress(bytesWritten, totalSize);
			}

			@Override
			public void onRetry(int retryNo) {
				// TODO Auto-generated method stub
				super.onRetry(retryNo);
				// 返回重试次数
				udi.onRetry(retryNo);
			}

		});

		return rh;
	}

	/**
	 * 网络请求上传或下载对外接口
	 * 
	 * @author gxf
	 *
	 */
	public interface UpDownInterface {
		/**
		 * 网络请求成功
		 * 
		 * @param requestCode
		 *            对应请求码
		 * @param responseBody
		 *            返回网络数据
		 */
		public void onSuccess(int requestCode, byte[] responseBody);

		/**
		 * 网络请求失败
		 * 
		 * @param requestCode
		 *            对应请求码
		 * @param responseBody
		 *            返回网络数据
		 */
		public void onFailure(int requestCode, byte[] responseBody);

		/**
		 * 上传或下载进�?
		 * 
		 * @param bytesWritten
		 *            当前下载量
		 * @param totalSize
		 *            总下载量
		 */
		public void onProgress(int bytesWritten, int totalSize);

		/**
		 * 上传或下载重试
		 * 
		 * @param retryNo
		 *            上传或下载重试次数
		 */
		public void onRetry(int retryNo);
	}
}
