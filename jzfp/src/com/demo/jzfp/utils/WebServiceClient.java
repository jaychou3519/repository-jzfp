package com.demo.jzfp.utils;

import java.util.Map;
import java.util.Set;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.util.Log;

public class WebServiceClient {
    
    public static String callWeb(String nameSpace, String methodName,String serverURL, 
    		String interfaceName, Map<String,Object> params){
        //每一次网络请求都会重新调用
        String resultString = null;
        // 实例化SoapObject对象
        SoapObject request = new SoapObject(nameSpace, methodName);
        /**
         * 设置参数，参数名不一定需要跟调用的服务器端的参数名相同，只需要对应的顺序相同即可
         * */
        if(null != params){
	         Set<String> keySet = params.keySet();
	         for(String key:keySet){
	        	 request.addProperty(key, params.get(key));
	         }
        }
        // 使用soap1.1协议创建Envelop对象
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        HttpTransportSE ht = null;
        ht = new HttpTransportSE(serverURL + interfaceName, 10*1000);
        ht.debug = true;
        try {
            // 将SoapObject对象设置为SoapSerializationEnvelope对象的传出SOAP消息
            envelope.bodyOut = request;
            // 调用webService
            ht.call(null, envelope);
            if (envelope.getResponse() != null) {
                SoapObject result = (SoapObject) envelope.bodyIn;
                resultString = result.getProperty(0).toString();
                Log.i("调用返回信息", resultString+"");
            }else {
                Log.i("调用返回信息", "调用失败，返回数据为null");
            }
        }catch(OutOfMemoryError e){
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return resultString;
    }
}
