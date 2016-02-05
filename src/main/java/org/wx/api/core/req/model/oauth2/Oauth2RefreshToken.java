package org.wx.api.core.req.model.oauth2;

import org.wx.api.core.annotation.ReqType;
import org.wx.api.core.req.model.WeixinReqParam;

/**
 * 网页授权刷新TOKEN
 * @author Administrator
 *
 */
@ReqType("oauth2RefreshToken")
public class Oauth2RefreshToken extends WeixinReqParam{
	
	private String appid;
	
	private String grant_type = "refresh_token";
	
	private String refresh_token;

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getGrant_type() {
		return grant_type;
	}

	public void setGrant_type(String grant_type) {
		this.grant_type = grant_type;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

}
