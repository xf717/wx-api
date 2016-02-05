package org.wx.api.core.resp.model.message;

import org.wx.api.core.resp.model.message.base.BaseRespMessage;

/**
 * 回复的消息内容  text类型
 * 字段的属性的大小写必须要和接口里的一样,否则无法实现自动回复的信息,
 * 如接口里的文本内容字段是Content则不能写成content
 * @author Administrator
 *
 */
public class RespTextMessage extends BaseRespMessage{
	
	//回复的消息内容（换行：在content中能够换行，微信客户端就支持换行显示）
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	
	

}
