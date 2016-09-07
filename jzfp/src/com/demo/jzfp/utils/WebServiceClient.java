package com.demo.jzfp.utils;

import java.util.Map;
import java.util.Set;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.util.Log;

public class WebServiceClient {
	
    public static void getConnByMethodName(String methodName,Map<String,Object> params){
    	 //创建httpTransportSE传输对象
    	 HttpTransportSE ht = new HttpTransportSE(Constant.SERVICE_URL);
    	 ht.debug = true;
    	 //使用soap1.1协议创建Envelop对象
         SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
         //实例化SoapObject对象
         SoapObject request = new SoapObject(Constant.SERVICE_NS, methodName);
         /**
          * 设置参数，参数名不一定需要跟调用的服务器端的参数名相同，只需要对应的顺序相同即可
          * */
         Set<String> keySet = params.keySet();
         for(String key:keySet){
        	 request.addProperty(key, params.get(key));
         }
         //将SoapObject对象设置为SoapSerializationEnvelope对象的传出SOAP消息
         envelope.bodyOut = request;
         try{
             //调用webService
             ht.call(null, envelope);
             if(envelope.getResponse() != null){
                 SoapObject result = (SoapObject) envelope.bodyIn;
                 String name = result.getProperty(0).toString();
             }else{
            	 Log.i("webservice", "获取数据失败!!");
             }
         }catch (Exception e) {
             e.printStackTrace();
             Log.i("webservice", "调用接口异常,获取数据失败");
         }
    }
}
