package org.wx.api.wxmessage.model.base;

/**
 * 返回信息
 * @author Administrator
 *
 */
public class BaseMessage {
	
	private int errcode; //错误编码
	
	private String errmsg; //错误信息

	public int getErrcode() {
		return errcode;
	}

	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

}
