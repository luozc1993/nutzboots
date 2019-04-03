package cn.luozc.etu_web.util;


import net.sf.json.JSONObject;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>Http工具类
 * 
 * <p>Http工具类，为系统提供通用Http访问操作方法：
 * 
 * <p>1、发送GET请求；
 * <p>2、发送POST请求。
 * 
 */
public class HttpUtil {
	
	
	public static JSONObject Post(String url,Map<String,Object> map){
		
   		PostMethod method = null;
		try {
			HttpClient httpClient = new HttpClient();
			// 设置http头
			List<Header> headers = new ArrayList<Header>();
			headers.add(new Header("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)"));
			httpClient.getHostConfiguration().getParams().setParameter("http.default-headers", headers);
			method = new PostMethod(url);
			method.setDoAuthentication(true);
			method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, false));

			// List<NameValuePair> list = new ArrayList<NameValuePair>();
			// Iterator<?> iterator = map.entrySet().iterator();
			//
			JSONObject jsonObject = JSONObject.fromObject(map);

			String transJson = jsonObject.toString();
			RequestEntity requestEntity = new StringRequestEntity(transJson, "application/json", "UTF-8");
			method.setRequestEntity(requestEntity);
			int statusCode = httpClient.executeMethod(method);
			String str = new String(method.getResponseBody(), "utf-8");
			System.out.println(str);
		}catch (Exception e) {
			e.printStackTrace();
			return new JSONObject().accumulate("status", false).accumulate("msg", "接口异常");
		} finally {
			method.releaseConnection();
		}
		return null;
	}
	/** 
     * http degist 认证
     *  
     */    
   	public static JSONObject httpGet(String url){
   		GetMethod method = null;
		try {
			HttpClient httpClient = new HttpClient();
			// 设置http头
			List<Header> headers = new ArrayList<Header>();
			headers.add(new Header("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)"));
			httpClient.getHostConfiguration().getParams().setParameter("http.default-headers", headers);

			method = new GetMethod(url);
			method.setDoAuthentication(true);
			method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, false));
			httpClient.executeMethod(method);
			String str = new String(method.getResponseBody(), "utf-8");
			return JSONObject.fromObject(str);
		}catch (Exception e) {
			return new JSONObject().accumulate("status", false).accumulate("msg", "接口异常");
		} finally {
			method.releaseConnection();
		}
	}
	/** 
     * http degist 认证
     *  
     */    
   	public static JSONObject get(String url){
   		GetMethod method = null;
		try {
			HttpClient httpClient = new HttpClient();
			// 设置http头
			List<Header> headers = new ArrayList<Header>();
			headers.add(new Header("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)"));
			httpClient.getHostConfiguration().getParams().setParameter("http.default-headers", headers);

			method = new GetMethod(url);
			method.setDoAuthentication(true);
			method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, false));
			httpClient.executeMethod(method);
			
			String str = new String(method.getResponseBody(), "utf-8");
			str = str.substring(1, str.length()-1);
			return JSONObject.fromObject(str);
		}catch (Exception e) {
			return new JSONObject().accumulate("status", false).accumulate("msg", "接口异常");
		} finally {
			method.releaseConnection();
		}
	}
	
   


}
