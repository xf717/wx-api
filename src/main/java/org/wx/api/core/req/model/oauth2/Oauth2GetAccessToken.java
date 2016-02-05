package org.wx.api.core.req.model.oauth2;

import org.wx.api.core.annotation.ReqType;
import org.wx.api.core.req.model.WeixinReqParam;

/**
 * 通过code换取网页授权access_token
 * 
 * @author Administrator
 *
 */
@ReqType("oauth2GetAccessToken")
public class Oauth2GetAccessToken extends WeixinReqParam {

	private String appid;

	private String secret;

	private String code;

	private String grant_type = "authorization_code";

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getGrant_type() {
		return grant_type;
	}

	public void setGrant_type(String grant_type) {
		this.grant_type = grant_type;
	}

}
