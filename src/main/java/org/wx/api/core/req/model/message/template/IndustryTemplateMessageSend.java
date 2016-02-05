package org.wx.api.core.req.model.message.template;

import org.wx.api.core.annotation.ReqType;
import org.wx.api.core.req.model.WeixinReqParam;

/**
 * 发送模版消息
 * @author Administrator
 * 
 */
@ReqType("industryTemplateMessageSend")
public class IndustryTemplateMessageSend extends WeixinReqParam {


	private String touser;
	
	private String template_id;
	
	private String url;
	
	private TemplateMessage data;

	public String getTouser() {
		return touser;
	}

	public void setTouser(String touser) {
		this.touser = touser;
	}

	public String getTemplate_id() {
		return template_id;
	}

	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public TemplateMessage getData() {
		return data;
	}

	public void setData(TemplateMessage data) {
		this.data = data;
	}
	
	
}
