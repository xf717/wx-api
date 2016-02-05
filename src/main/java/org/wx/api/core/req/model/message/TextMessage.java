package org.wx.api.core.req.model.message;

/**
 * 用户请求的文本内容
 * @author Administrator
 */
public class TextMessage extends BaseReqMessage{

	//文本消息内容
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	} 
	
}
