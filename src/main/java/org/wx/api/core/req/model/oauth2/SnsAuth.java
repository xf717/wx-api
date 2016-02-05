package org.wx.api.core.req.model.oauth2;

import org.wx.api.core.annotation.ReqType;
import org.wx.api.core.req.model.WeixinReqParam;

/**
 * 检验授权凭证（access_token）是否有效
 * @author Administrator
 *
 */
@ReqType("snsAuth")
public class SnsAuth extends WeixinReqParam{
	
	private String openid;

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	} 

}
