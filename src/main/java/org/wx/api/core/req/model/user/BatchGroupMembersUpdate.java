package org.wx.api.core.req.model.user;

import java.util.List;

import org.wx.api.core.annotation.ReqType;
import org.wx.api.core.req.model.WeixinReqParam;

/**
 * 
 * 批量更新用户
 * @author sfli.sir
 * 
 */
@ReqType("batchGroupMembersUpdate")
public class BatchGroupMembersUpdate extends WeixinReqParam {
	
	private List<String> openid_list;
	
	private int to_groupid;


	public List<String> getOpenid_list() {
		return openid_list;
	}

	public void setOpenid_list(List<String> openid_list) {
		this.openid_list = openid_list;
	}

	public int getTo_groupid() {
		return to_groupid;
	}

	public void setTo_groupid(int to_groupid) {
		this.to_groupid = to_groupid;
	}

	
}
