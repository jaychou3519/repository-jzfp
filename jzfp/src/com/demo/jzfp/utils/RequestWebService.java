package com.demo.jzfp.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.LinkedHashMap;
import java.util.Map;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.os.AsyncTask;
import android.util.Log;

public class RequestWebService {
	public static String NAME_SPACE = "http://webservice.item.hihsoft.com/";
	public static String SERVER_URL = "http://115.28.14.1:9999/ylppbd/services/ylppbdWS?wsdl";

	public interface WebServiceCallback {
		/**
		 * 结果集(通过WebServiceTask 回到主线程)
		 * 
		 * @param reulst
		 */
		public void result(String reulst, int requestCode);

	}

	/**
	 * 发送WebService请求
	 * 
	 * @param methodName
	 *            方法名
	 * @param map
	 *            参数集合 需要创建LinkedHashMap 因为webservice需要参数顺序对应
	 * @param ws
	 *            回调接口
	 * @param requestCode
	 *            请求码
	 */
	public static void send(final String methodName, final LinkedHashMap<String, String> map, final WebServiceCallback ws, final int requestCode) {
		new WebServiceTask(ws, map, methodName, requestCode).execute();
		// String reulst = register(urls, property);
		// ws.reulst(reulst);

	}

	static class WebServiceTask extends AsyncTask<String, Integer, String> {
		private WebServiceCallback ws;
		private String methodName;
		private LinkedHashMap<String, String> property;
		private int requestCode;

		public WebServiceTask(final WebServiceCallback ws, LinkedHashMap<String, String> property, String methodName, int requestCode) {
			this.ws = ws;
			this.methodName = methodName;
			this.property = property;
			this.requestCode = requestCode;
		}

		@Override
		protected String doInBackground(String... params) {
			return register(methodName, property);
		}

		@Override
		protected void onPostExecute(String result) {
			ws.result(result, requestCode);
		}
	}

	private static String register(String methodName, LinkedHashMap<String, String> property) {
		SoapObject soapObject = null;
		// 创建soapObject对象，参数为命名控件和调用方法名，也就是soap_action
		SoapObject object = new SoapObject(NAME_SPACE, methodName);
		setProperty(object, property);
		// object.addProperty("key", "v");// 设置属性
		// 根据版本号创建SoapSerializationEnvelope
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.bodyOut = object;
		// envelope.dotNet = true;
		// envelope.setOutputSoapObject(object);
		HttpTransportSE httpTransportSE = new HttpTransportSE(SERVER_URL + methodName, 10 * 1000);
		try {
			httpTransportSE.debug = true;
			httpTransportSE.call(null, envelope);
			soapObject = (SoapObject) envelope.bodyIn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 如果有返回值，得到返回值并且返回
		String result = soapObject != null ? soapObject.getProperty(0).toString() : null;
		Log.i("haha", methodName + "------" + result);
		return result;
	}

	/**
	 * 设置属性 把MAP 转成SoapObject属性
	 * 
	 * @param object
	 * @param property
	 */
	private static void setProperty(SoapObject object, LinkedHashMap<String, String> property) {
		if (property == null)
			return;
		for (Map.Entry<String, String> entry : property.entrySet()) {
			object.addProperty(entry.getKey(), entry.getValue());
		}
	}

	/**
	 * 上传图片
	 * @param countrymanid
	 * @param imageBuffer
	 * @param methodName
	 * @return
	 */
	public static void uploadImage(WebServiceCallback ws, String countrymanid,String imageBuffer, String methodName, int requestCode) {
		new UploadImageTask(ws, methodName, countrymanid, imageBuffer, requestCode).execute();
	}
	
	static class UploadImageTask extends AsyncTask<String, Integer, String> {
		private WebServiceCallback ws;
		private String methodName;
		private String countrymanid;
		private String imageBuffer;
		private int requestCode;

		public UploadImageTask(final WebServiceCallback ws, String methodName, String countrymanid, String imageBuffer, int requestCode) {
			this.ws = ws;
			this.methodName = methodName;
			this.requestCode = requestCode;
			this.imageBuffer = imageBuffer;
			this.countrymanid = countrymanid;
		}

		@Override
		protected String doInBackground(String... params) {
			SoapObject soapObject = new SoapObject(NAME_SPACE, methodName);
			soapObject.addProperty("arg0", countrymanid); // 贫困户ID
			soapObject.addProperty("arg1", countrymanid + ".jpg"); // 参数1 图片名
			soapObject.addProperty("arg2", imageBuffer); // 参数2 图片字符串
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.bodyOut = soapObject;
			HttpTransportSE httpTransportSE = new HttpTransportSE(SERVER_URL + methodName, 10 * 1000);
			try {
				httpTransportSE.debug = true;
				httpTransportSE.call(null, envelope);
				soapObject = (SoapObject) envelope.bodyIn;
			} catch (Exception e) {
				e.printStackTrace();
			}
			String result = soapObject != null ? soapObject.getProperty(0).toString() : null;
			Log.i("haha", methodName + "------" + result);
			return result;
		}

		@Override
		protected void onPostExecute(String result) {
			ws.result(result, requestCode);
		}
	}

}
