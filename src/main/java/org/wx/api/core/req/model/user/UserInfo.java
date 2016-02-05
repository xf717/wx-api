package org.wx.api.core.req.model.user;

public class UserInfo {
	
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
