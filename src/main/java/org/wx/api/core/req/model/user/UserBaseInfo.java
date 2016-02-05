package org.wx.api.core.req.model.user;

import org.wx.api.core.annotation.ReqType;
import org.wx.api.core.req.model.WeixinReqParam;

/**
 * 获取用户基本信息（包括UnionID机制）
 * 
 * @author Administrator
 *
 */
@ReqType("getUserBaseInfo")
public class UserBaseInfo extends WeixinReqParam {

	private String openid;

	private String lang = "zh_CN";  //默认简体

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
