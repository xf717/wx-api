package org.wx.api.wxbase.serviceip;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wx.api.core.exception.WexinReqException;
import org.wx.api.core.req.WeiXinReqService;
import org.wx.api.core.req.model.ServiceIP;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ServiceIpAPI {
	
	private static Logger logger = LoggerFactory.getLogger(ServiceIpAPI.class);
	
	/**
	 * 获取微信返回的服务器IP地址列表
	 * @param accessToken
	 * @return
	 * @throws WexinReqException
	 */
	@SuppressWarnings("unchecked")
	public static List<String> getCallbackipList(String accessToken) throws WexinReqException{
		List<String> ipList = null;
		ServiceIP serviceip = new ServiceIP();
		serviceip.setAccess_token(accessToken);
		JSONObject jsonObject = WeiXinReqService.getInstance().doWeinxinReqJson(serviceip);
		if (null != jsonObject) {  
			try {
				ipList = JSONArray.toList(jsonObject.getJSONArray("ip_list"), List.class);
			} catch (Exception e) {
				ipList = null;  
				logger.error("微信返回的服务器IP地址列表失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg")); 
			}
		}
		return ipList;
	}

}
