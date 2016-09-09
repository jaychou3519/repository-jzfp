package com.demo.jzfp.utils;

import java.util.LinkedHashMap;
import java.util.Map;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.os.AsyncTask;

import com.google.gson.Gson;

public class RequestWebService {
	private static Gson gson = new Gson();
	
	public static String NAME_SPACE = "http://webservice.item.hihsoft.com/";
	public static String SERVER_URL = "http://115.28.14.1:9145/ylppbd/services/ylppbdWS/";

	public interface WebServiceCallback {
		/**
		 * 结果集(通过WebServiceTask 回到主线程)
		 * @param reulst
		 */
		public void reulst(String reulst,int requestCode);

	}
	
	/**
	 * 发送WebService请求
	 * @param urls	请求地址集
	 * @param property	参数集合	
	 * 需要创建LinkedHashMap 因为webservice需要参数顺序对应
	 * @param ws	回调接口
	 * @param requestCode	请求码
	 */
	public static void send(final String[] urls,final LinkedHashMap<String, String> property,
			final WebServiceCallback ws, final int requestCode) {
			new WebServiceTask(ws,property,urls,requestCode).execute();
				//String reulst = register(urls, property);
				//ws.reulst(reulst);
		
	}
	static class WebServiceTask extends AsyncTask<String, Integer, String>{
		private WebServiceCallback ws;
		private String[] urls;
		private LinkedHashMap<String, String> property;
		private int requestCode;
		public WebServiceTask(final WebServiceCallback ws,LinkedHashMap<String, String> property,String[] urls,int requestCode){
			this.ws = ws;
			this.urls = urls;
			this.property = property;
			this.requestCode = requestCode;
		}
		@Override
		protected String doInBackground(String... params) {
			return register(urls, property);
		}
		@Override
		protected void onPostExecute(String result) {
			ws.reulst(result,requestCode);
		}
	}
	
	

	private static String register(String[] urls, LinkedHashMap<String, String> property) {
		SoapObject soapObject = null;
		// 创建soapObject对象，参数为命名控件和调用方法名，也就是soap_action
		SoapObject object = new SoapObject(NAME_SPACE, urls[1]);
		setProperty(object, property);
		//object.addProperty("key", "v");// 设置属性
		// 根据版本号创建SoapSerializationEnvelope
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.bodyOut = object;
		envelope.dotNet = true;
		envelope.setOutputSoapObject(object);
		HttpTransportSE httpTransportSE = new HttpTransportSE(urls[0]);
		try {
			httpTransportSE.call(null, envelope);
			soapObject = (SoapObject) envelope.bodyIn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 如果有返回值，得到返回值并且返回
		return soapObject != null ? soapObject.getProperty(0).toString() : null;
	}
	/**
	 * 设置属性
	 * 把MAP 转成SoapObject属性
	 * @param object
	 * @param property
	 */
	private static void setProperty(SoapObject object,
			LinkedHashMap<String, String> property) {
		if(property == null)
			return;
		for (Map.Entry<String ,String> entry : property.entrySet()) {
			object.addProperty(entry.getKey(), entry.getValue());
		}
	}
}
