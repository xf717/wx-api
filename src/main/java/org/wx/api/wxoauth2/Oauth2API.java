package org.wx.api.wxoauth2;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wx.api.core.exception.WexinReqException;
import org.wx.api.core.req.WeiXinReqService;
import org.wx.api.core.req.model.oauth2.GetSnsUserInfo;
import org.wx.api.core.req.model.oauth2.Oauth2GetAccessToken;
import org.wx.api.core.req.model.oauth2.Oauth2RefreshToken;
import org.wx.api.core.req.model.oauth2.SnsAuth;
import org.wx.api.core.util.WeiXinConstant;
import org.wx.api.wxoauth2.model.SNSUserInfo;
import org.wx.api.wxoauth2.model.WeixinOauth2Token;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 网页授权API
 * @author Administrator
 *
 */
public class Oauth2API {
	
	private static Logger logger = LoggerFactory.getLogger(Oauth2API.class);
	
	/**
	 * 授权第二步,通过code换取网页授权access_token
	 * @param appId 公众账号的唯一标识
	 * @param appSecret 公众账号的密钥
	 * @param code
	 * @return WeixinAouth2Token
	 * @throws WexinReqException 
	 */
	public static WeixinOauth2Token getOauth2AccessToken(String appId,String appSecret,String code) throws WexinReqException{
		WeixinOauth2Token wat = null;
		Oauth2GetAccessToken oauth2GetAccessToken = new Oauth2GetAccessToken();
		oauth2GetAccessToken.setAppid(appId);
		oauth2GetAccessToken.setSecret(appSecret);
		oauth2GetAccessToken.setCode(code);
		JSONObject jsonObject = WeiXinReqService.getInstance().doWeinxinReqJson(oauth2GetAccessToken);
		if (null != jsonObject) {
			try {
				wat = new WeixinOauth2Token();
				wat.setAccessToken(jsonObject.getString("access_token"));
				wat.setExpiresIn(jsonObject.getInt("expires_in"));
				wat.setRefreshToken(jsonObject.getString("refresh_token"));
				wat.setOpenId(jsonObject.getString("openid"));
				wat.setScope(jsonObject.getString("scope"));
			} catch (Exception e) {
				wat = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				logger.error("获取网页授权凭证失败 errcode:{} errmsg:{}", errorCode, errorMsg);
				e.printStackTrace();
			}
		}
		return wat;
	}
	
	/**
	 * 第三步刷新网页授权凭证
	 * @param appId 公众账号的唯一标识
	 * @param refreshToken
	 * @return WeixinAouth2Token
	 * @throws WexinReqException 
	 */
	public static WeixinOauth2Token refreshOauth2AccessToken(String appId,String refreshToken) throws WexinReqException{
		WeixinOauth2Token wat = null;
		Oauth2RefreshToken oauth2RefreshToken = new Oauth2RefreshToken();
		oauth2RefreshToken.setAppid(appId);
		oauth2RefreshToken.setRefresh_token(refreshToken);
		JSONObject jsonObject = WeiXinReqService.getInstance().doWeinxinReqJson(oauth2RefreshToken);
		if (null != jsonObject) {
			try {
				wat = new WeixinOauth2Token();
				wat.setAccessToken(jsonObject.getString("access_token"));
				wat.setExpiresIn(jsonObject.getInt("expires_in"));
				wat.setRefreshToken(jsonObject.getString("refresh_token"));
				wat.setOpenId(jsonObject.getString("openid"));
				wat.setScope(jsonObject.getString("scope"));
			} catch (Exception e) {
				wat = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				logger.error("刷新网页授权凭证失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		return wat;
	}
	
	/**
	 * 第四步通过网页授权获取用户信息
	 * @param accessToken 网页授权接口调用凭证
	 * @param openId 用户标识
	 * @return SNSUserInfo
	 * @throws WexinReqException 
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public static SNSUserInfo getSNSUserInfo(String accessToken, String openId) throws WexinReqException {
		SNSUserInfo snsUserInfo = null;
		GetSnsUserInfo getSnsUserInfo = new GetSnsUserInfo();
		getSnsUserInfo.setAccess_token(accessToken);
		getSnsUserInfo.setOpenid(openId);
		JSONObject jsonObject =  WeiXinReqService.getInstance().doWeinxinReqJson(getSnsUserInfo);
		if (null != jsonObject) {
			try {
				snsUserInfo = new SNSUserInfo();
				// 用户的标识
				snsUserInfo.setOpenId(jsonObject.getString("openid"));
				// 昵称
				snsUserInfo.setNickname(jsonObject.getString("nickname"));
				// 性别（1是男性，2是女性，0是未知）
				snsUserInfo.setSex(jsonObject.getInt("sex"));
				// 用户所在国家
				snsUserInfo.setCountry(jsonObject.getString("country"));
				// 用户所在省份
				snsUserInfo.setProvince(jsonObject.getString("province"));
				// 用户所在城市
				snsUserInfo.setCity(jsonObject.getString("city"));
				// 用户头像
				snsUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
				// 用户特权信息
				snsUserInfo.setPrivilegeList(JSONArray.toList(jsonObject.getJSONArray("privilege"), List.class));
				snsUserInfo.setUnionid(jsonObject.getString("unionid"));
			} catch (Exception e) {
				snsUserInfo = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				logger.error("获取用户信息失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		return snsUserInfo;
	}
	
	/**
	 * 检验授权凭证（access_token）是否有效
	 * @param accessToken
	 * @param openId
	 * @return
	 * @throws WexinReqException
	 */
	public static boolean snsAuth(String accessToken, String openId) throws WexinReqException{
		boolean result = false;
		SnsAuth snsAuth = new SnsAuth();
		snsAuth.setAccess_token(accessToken);
		snsAuth.setOpenid(openId);
		JSONObject jsonObject = WeiXinReqService.getInstance().doWeinxinReqJson(snsAuth);
		if (null != jsonObject) {
			int errorCode = jsonObject.getInt(WeiXinConstant.RETURN_ERROR_INFO_CODE);
			String errorMsg = jsonObject.getString(WeiXinConstant.RETURN_ERROR_INFO_MSG);
			if (0 == errorCode) {
				result = true;
				logger.info("设检验授权凭证 errcode:{} errmsg:{}", errorCode, errorMsg);
			}else{
				logger.info("设检验授权凭证 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		return result;
	}

}
