package org.wx.api.core.req.model.user;

import org.wx.api.core.annotation.ReqType;
import org.wx.api.core.req.model.WeixinReqParam;

/**
 * 获取用户列表
 * @author Administrator
 *
 */
@ReqType("getUserInfoList")
public class UserInfoListGet extends WeixinReqParam {

	private String next_openid;

	public String getNext_openid() {
		return next_openid;
	}

	public void setNext_openid(String next_openid) {
		this.next_openid = next_openid;
	}

}
