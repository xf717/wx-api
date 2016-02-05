package org.wx.api.core.req.model.user;

import org.wx.api.core.annotation.ReqType;
import org.wx.api.core.req.model.WeixinReqParam;

/**
 * 设置用户备注名
 * @author Administrator
 *
 */
@ReqType("userRemarkUpdate")
public class UserRemark extends WeixinReqParam {

	private String openid; //用户标识
	
	private String remark; //新的备注名，长度必须小于30字符

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
