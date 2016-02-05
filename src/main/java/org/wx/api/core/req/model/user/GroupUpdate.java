package org.wx.api.core.req.model.user;

import org.wx.api.core.annotation.ReqType;
import org.wx.api.core.req.model.WeixinReqParam;

/**
 * 修改分组名
 * 
 * @author Administrator
 * 
 */
@ReqType("groupUpdate")
public class GroupUpdate extends WeixinReqParam {
	
	private Group group;

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}
	
	
}
