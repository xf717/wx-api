package org.wx.api.core.common.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wx.api.core.common.MyX509TrustManager;
import org.wx.api.core.exception.WexinReqException;
import org.wx.api.wxbase.token.model.AccessToken;

import net.sf.json.JSONObject;

/**
 * 微信连接HTTPS工具
 * 众平台通用接口工具类
 * @author Administrator
 *
 */
public class CommonUtil {
	
	private static Logger logger = LoggerFactory.getLogger(CommonUtil.class);
	
	// 获取access_token的接口地址（GET） 限200（次/天）  
	public static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	
	/**
	 * 发起https请求并获取结果 
	 * @param requestUrl 请求地址
	 * @param requestMethod 请求方式（GET、POST）
	 * @param outputStr 提交的数据 
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值) 
	 */
	public static JSONObject httpsRequest(String requestUrl, String requestMethod, String outputStr){
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化 
			TrustManager[] tm = { new MyX509TrustManager() };  
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			 // 从上述SSLContext对象中得到SSLSocketFactory对象  
			SSLSocketFactory ssf = sslContext.getSocketFactory(); 
			URL url = new URL(requestUrl);  
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);  
			
			httpUrlConn.setDoOutput(true);//建立用于输出的链接
			httpUrlConn.setDoInput(true);  //建立用于输入的链接
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）  
			httpUrlConn.setRequestMethod(requestMethod);
			if ("GET".equalsIgnoreCase(requestMethod))
				 httpUrlConn.connect();  
			// 当有数据需要提交时 
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();  
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8")); 
				outputStream.close();  
			}  
			// 将返回的输入流转换成字符串 
			InputStream inputStream = httpUrlConn.getInputStream(); 
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");  
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  
			String str = null;  
			while ((str = bufferedReader.readLine()) != null) {  
				 buffer.append(str);  
			}
			// 释放资源  
			bufferedReader.close();  
			inputStreamReader.close();  
			inputStream.close();  
			inputStream = null;  
			httpUrlConn.disconnect();  
			jsonObject = JSONObject.fromObject(buffer.toString()); 
			if (jsonObject.containsKey("errcode") && jsonObject.getInt("errcode") != 0) {
				logger.debug("********* ERROR********{}",buffer.toString());
				logger.debug("*********HTTPREQUEST END********");
				throw new WexinReqException("httpRequest Method！errcode="
						+ jsonObject.getString("errcode") + ",errmsg = "
						+ jsonObject.getString("errmsg"));
			} else {
				logger.debug("********* SUCCESS END********");
			}
		}catch (ConnectException ce) { 
			logger.error("Weixin server connection timed out.");  
		}catch (Exception e) {
			logger.error("https request error:{}", e);  
			e.printStackTrace();
		}
		return jsonObject;
	}
	
	/**
	 * 发送http请求
	 * @param requestUrl 请求地址
	 * @return String
	 */
	public static String httpRequest(String requestUrl) {
		StringBuffer buffer = new StringBuffer();
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
			httpUrlConn.setDoInput(true);
			httpUrlConn.setRequestMethod("GET");
			httpUrlConn.connect();

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}

	
	/** 
	 * 获取access_token 
	 * @param appid 凭证 
	 * @param appsecret 密钥 
	 * @return 
	 */  
	public static AccessToken getAccessToken(String appid, String appsecret){
		AccessToken accessToken = null;  
		String requestUrl = access_token_url.replace("APPID", appid).replace("APPSECRET", appsecret); 
		System.out.println("requestUrl=="+requestUrl);
		JSONObject jsonObject = httpsRequest(requestUrl, "GET", null);  
		// 如果请求成功
		if (null != jsonObject) {  
			try {
				accessToken = new AccessToken(); 
				accessToken.setToken(jsonObject.getString("access_token"));
				accessToken.setExpiresIn(jsonObject.getInt("expires_in")); 
			} catch (Exception e) {
				accessToken = null;  
				logger.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg")); 
			}
		}
		return accessToken;
	}
	
	
	/**
	 * URL编码（utf-8）
	 * @param source
	 * @return
	 */
	public static String urlEncodeUTF8(String source) {
		String result = source;
		try {
			result = java.net.URLEncoder.encode(source, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据内容类型判断文件扩展名
	 * 
	 * @param contentType 内容类型
	 * @return
	 */
	public static String getFileExt(String contentType) {
		String fileExt = "";
		if ("image/jpeg".equals(contentType))
			fileExt = ".jpg";
		else if ("audio/mpeg".equals(contentType))
			fileExt = ".mp3";
		else if ("audio/amr".equals(contentType))
			fileExt = ".amr";
		else if ("video/mp4".equals(contentType))
			fileExt = ".mp4";
		else if ("video/mpeg4".equals(contentType))
			fileExt = ".mp4";
		return fileExt;
	}
	
	/**
	 * 判断是否微信浏览器
	 * @param request
	 * @return
	 */
	public static boolean isMicroMessenger(HttpServletRequest request){
		boolean result = false;
		String userAgent = request.getHeader("User-Agent");
		System.out.println("userAgent==="+userAgent);
		if(userAgent.contains("MicroMessenger"))
			result = true;
		return result;
	}
	
	
}
