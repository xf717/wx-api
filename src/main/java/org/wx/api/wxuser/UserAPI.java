package org.wx.api.wxuser;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wx.api.core.exception.WexinReqException;
import org.wx.api.core.req.WeiXinReqService;
import org.wx.api.core.req.model.user.BatchUserInfo;
import org.wx.api.core.req.model.user.UserBaseInfo;
import org.wx.api.core.req.model.user.UserInfo;
import org.wx.api.core.req.model.user.UserInfoListGet;
import org.wx.api.core.req.model.user.UserRemark;
import org.wx.api.core.util.WeiXinConstant;
import org.wx.api.wxuser.model.user.WeixinUserInfo;
import org.wx.api.wxuser.model.user.WeixinUserList;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 * 微信用户API
 * @author Administrator
 *
 */
public class UserAPI {
	
	private static Logger logger = LoggerFactory.getLogger(UserAPI.class);
	
	/**
	 * 设置用户备注名
	 * @param accessToken 调用接口凭证
	 * @param openid  用户标识
	 * @return true | false
	 * @throws WexinReqException 
	 */
	public static boolean userRemarkUpdate(String accessToken,String openid,String remark) throws WexinReqException{
		boolean result = false;
		UserRemark userRemark = new UserRemark();
		userRemark.setAccess_token(accessToken);
		userRemark.setOpenid(openid);
		userRemark.setRemark(remark);
		JSONObject jsonObject = WeiXinReqService.getInstance().doWeinxinReqJson(userRemark);
		if (null != jsonObject) {
			int errorCode = jsonObject.getInt(WeiXinConstant.RETURN_ERROR_INFO_CODE);
			String errorMsg = jsonObject.getString(WeiXinConstant.RETURN_ERROR_INFO_MSG);
			if (0 == errorCode) {
				result = true;
				logger.info("设置用户备注名成功 errcode:{} errmsg:{}", errorCode, errorMsg);
			}else{
				logger.info("设置用户备注名失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		return result;
	} 
	
	/**
	 * 获取用户的基本信息
	 * @param accessToken
	 * @param openId
	 * @return
	 * @throws WexinReqException 
	 */
	public static WeixinUserInfo getUserInfo(String accessToken, String openId) throws WexinReqException {
		UserBaseInfo user = new UserBaseInfo();
		user.setAccess_token(accessToken);
		user.setOpenid(openId);
		// 获取用户信息
		JSONObject jsonObject = WeiXinReqService.getInstance().doWeinxinReqJson(user);
		WeixinUserInfo weixinUserInfo = null;
		if (null != jsonObject) {
			try {
				weixinUserInfo = new WeixinUserInfo();
				// 用户的标识
				weixinUserInfo.setOpenId(jsonObject.getString("openid"));
				// 关注状态（1是关注，0是未关注），未关注时获取不到其余信息
				weixinUserInfo.setSubscribe(jsonObject.getInt("subscribe"));
				// 用户关注时间
				weixinUserInfo.setSubscribeTime(jsonObject.getString("subscribe_time"));
				// 昵称
				weixinUserInfo.setNickname(jsonObject.getString("nickname"));
				// 用户的性别（1是男性，2是女性，0是未知）
				weixinUserInfo.setSex(jsonObject.getInt("sex"));
				// 用户所在国家
				weixinUserInfo.setCountry(jsonObject.getString("country"));
				// 用户所在省份
				weixinUserInfo.setProvince(jsonObject.getString("province"));
				// 用户所在城市
				weixinUserInfo.setCity(jsonObject.getString("city"));
				// 用户的语言，简体中文为zh_CN
				weixinUserInfo.setLanguage(jsonObject.getString("language"));
				// 用户头像
				weixinUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
				//用户备注名
				weixinUserInfo.setRemark(jsonObject.getString("remark"));
				//用户所在组ID
				weixinUserInfo.setGroupid(jsonObject.getInt("groupid"));
				weixinUserInfo.setUnionid(jsonObject.getString("unionid"));
			} catch (Exception e) {
				if (0 == weixinUserInfo.getSubscribe()) {
					logger.error("用户{}已取消关注", weixinUserInfo.getOpenId());
				} else {
					Object error = jsonObject.get(WeiXinConstant.RETURN_ERROR_INFO_CODE);
					if(null != error){
						int errorCode = jsonObject.getInt(WeiXinConstant.RETURN_ERROR_INFO_CODE);
						String errorMsg = jsonObject.getString(WeiXinConstant.RETURN_ERROR_INFO_MSG);
						logger.error("获取用户信息失败 errcode:{} errmsg:{}", errorCode, errorMsg);
					}
				}
			}
		}
		return weixinUserInfo;				 
	}
	
	/**
	 * 批量获取用户基本信息
	 * @return 用户信息列表
	 * @throws WexinReqException 
	 */
	@SuppressWarnings("unchecked")
	public static List<WeixinUserInfo> batchWeixinUserInfoList(String accessToken,List<UserInfo> user_list) throws WexinReqException{
		BatchUserInfo batchUserInfo = new BatchUserInfo();
		batchUserInfo.setAccess_token(accessToken);
		batchUserInfo.setUser_list(user_list);
		JSONObject jsonObject = WeiXinReqService.getInstance().doWeinxinReqJson(batchUserInfo);
		List<WeixinUserInfo> weixinUserInfoList = new ArrayList<>();
		if (null != jsonObject) {
			try {
				weixinUserInfoList = JSONArray.toList(jsonObject.getJSONArray("user_info_list"), WeixinUserInfo.class);
			} catch (Exception e) {
				weixinUserInfoList = null;
				int errorCode = jsonObject.getInt(WeiXinConstant.RETURN_ERROR_INFO_CODE);
				String errorMsg = jsonObject.getString(WeiXinConstant.RETURN_ERROR_INFO_MSG);
				logger.error("批量获取用户基本信息失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		return weixinUserInfoList;
	}
	
	/**
	 * 获取关注者列表
	 * @param accessToken 调用接口凭证
	 * @param nextOpenId 第一个拉取的openId，不填默认从头开始拉取
	 * @return WeixinUserList
	 * @throws WexinReqException 
	 */
	@SuppressWarnings("unchecked")
	public static WeixinUserList getUserList(String accessToken, String nextOpenId) throws WexinReqException {
		WeixinUserList weixinUserList = null;
		if (null == nextOpenId)
			nextOpenId = "";
		// 获取关注者列表
		UserInfoListGet userInfoList = new UserInfoListGet();
		userInfoList.setAccess_token(accessToken);
		userInfoList.setNext_openid(nextOpenId);
		JSONObject jsonObject = WeiXinReqService.getInstance().doWeinxinReqJson(userInfoList);
		// 如果请求成功
		if (null != jsonObject) {
			try {
				weixinUserList = new WeixinUserList();
				weixinUserList.setTotal(jsonObject.getInt("total"));
				weixinUserList.setCount(jsonObject.getInt("count"));
				weixinUserList.setNextOpenId(jsonObject.getString("next_openid"));
				JSONObject dataObject = (JSONObject) jsonObject.get("data");
				weixinUserList.setOpenIdList(JSONArray.toList(dataObject.getJSONArray("openid"), List.class));
			} catch (JSONException e) {
				weixinUserList = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				logger.error("获取关注者列表失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		return weixinUserList;
	}

}
