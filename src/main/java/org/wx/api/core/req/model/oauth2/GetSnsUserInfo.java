package org.wx.api.core.req.model.oauth2;

import org.wx.api.core.annotation.ReqType;
import org.wx.api.core.req.model.WeixinReqParam;

/**
 * 通过网页授权获取用户信息
 * @author Administrator
 *
 */
@ReqType("getSnsUserInfo")
public class GetSnsUserInfo extends WeixinReqParam{
	
	private String openid;
	
	private String lang = "zh_CN"; //返回国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

}
