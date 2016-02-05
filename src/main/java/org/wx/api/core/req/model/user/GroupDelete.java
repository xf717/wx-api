package org.wx.api.core.req.model.user;

import org.wx.api.core.annotation.ReqType;
import org.wx.api.core.req.model.WeixinReqParam;

/**
 * 分组删除
 * 
 */
@ReqType("groupDelete")
public class GroupDelete extends WeixinReqParam {

	private Group group;

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}
	
}
