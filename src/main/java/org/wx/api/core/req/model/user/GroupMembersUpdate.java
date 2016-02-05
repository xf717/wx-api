package org.wx.api.core.req.model.user;

import org.wx.api.core.annotation.ReqType;
import org.wx.api.core.req.model.WeixinReqParam;

/**
 * 
 * 批量移动用户分组
 */
@ReqType("groupMembersUpdate")
public class GroupMembersUpdate extends WeixinReqParam {
	
	private String openid;
	
	private int to_groupid;

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public int getTo_groupid() {
		return to_groupid;
	}

	public void setTo_groupid(int to_groupid) {
		this.to_groupid = to_groupid;
	}

	
}
