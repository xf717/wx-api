package org.wx.api.wxbase.token;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.wx.api.wxbase.token.model.AccessToken;
import org.wx.api.core.exception.WexinReqException;
import org.wx.api.core.req.WeiXinReqService;

import net.sf.json.JSONObject;

/**
 * 微信--token信息
 * 
 * @author Administrator
 * 
 */
public class TokenAPI {
	
	private static Logger logger = LoggerFactory.getLogger(TokenAPI.class);

	/**
	 * 获取权限令牌信息
	 * @param appid
	 * @param appscret
	 * @return kY9Y9rfdcr8AEtYZ9gPaRUjIAuJBvXO5ZOnbv2PYFxox__uSUQcqOnaGYN1xc4N1rI7NDCaPm_0ysFYjRVnPwCJHE7v7uF_l1hI6qi6QBsA
	 * @throws WexinReqException
	 */
	public static AccessToken getAccessToken(String appid, String appsecret) throws WexinReqException{
		org.wx.api.core.req.model.AccessToken token = new org.wx.api.core.req.model.AccessToken();
		token.setAppid(appid);
		token.setSecret(appsecret);
		JSONObject jsonObject = WeiXinReqService.getInstance().doWeinxinReqJson(token);
		AccessToken accessToken = null;
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
	
	public static String getToken(String appid, String appsecret) throws WexinReqException{
		return getAccessToken(appid,appsecret).getToken();
	}
	
	public static String getToken() throws WexinReqException{
		return getToken("wxfb35e313189f6359","86cab7ed990ea26b5520a082dd38b5e8");
	}
	 
}
