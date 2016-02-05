package org.wx.api.core.req.model.message.event;

/**
 * 消息模版推送事件
 * @author Administrator
 *
 */
public class TemplateEvent extends BaseEvent{
	
	private int MsgID;
	
	private String Status;

	public int getMsgID() {
		return MsgID;
	}

	public void setMsgID(int msgID) {
		MsgID = msgID;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

}
