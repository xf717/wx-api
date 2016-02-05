package org.wx.api.core.resp.model.message.base;


/**
 * 发送回复信息父类
 * @author Administrator
 *
 */
public class BaseRespMessage {

	// 接收方帐号（收到的OpenID）不能为空
	private String ToUserName;
	// 开发者微信号不能为空
	private String FromUserName;
	// 创建时间不能为空
	private long CreateTime;
	// 消息类型(text/music/image……)不能为空
	private String MsgType;
	// 位0x0001被标志时，星标刚收到的消息
//	private int FuncFlag;

	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

}
