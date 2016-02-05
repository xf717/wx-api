package org.wx.api.core.req.model.user;

import java.util.List;

import org.wx.api.core.annotation.ReqType;
import org.wx.api.core.req.model.WeixinReqParam;

/**
 * 批量获取有用户信息
 * @author Administrator
 *
 */
@ReqType("batchgetUserInfo")
public class BatchUserInfo extends WeixinReqParam {
	
	private List<UserInfo> user_list;

	public List<UserInfo> getUser_list() {
		return user_list;
	}

	public void setUser_list(List<UserInfo> user_list) {
		this.user_list = user_list;
	}
	
	

}
