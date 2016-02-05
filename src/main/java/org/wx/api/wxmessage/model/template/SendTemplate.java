package org.wx.api.wxmessage.model.template;

import org.wx.api.wxmessage.model.base.BaseMessage;
/**
 * 发送模版消息接口返回的数据
 * @author Administrator
 *
 */
public class SendTemplate extends BaseMessage {
	
	private int msgid;

	public int getMsgid() {
		return msgid;
	}

	public void setMsgid(int msgid) {
		this.msgid = msgid;
	} 


}
