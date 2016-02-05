package org.wx.api.wxmessage.model.template;

import org.wx.api.wxmessage.model.base.BaseMessage;
/**
 * 获取模版接口返回的数据
 * @author Administrator
 *
 */
public class AddTemplate extends BaseMessage {
	
	private String template_id; //返回的模版ID

	public String getTemplate_id() {
		return template_id;
	}

	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}

}
