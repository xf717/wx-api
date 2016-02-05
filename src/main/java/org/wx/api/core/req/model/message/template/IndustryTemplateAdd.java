package org.wx.api.core.req.model.message.template;

import org.wx.api.core.annotation.ReqType;
import org.wx.api.core.req.model.WeixinReqParam;

/**
 * 获取模版ID
 * 
 * @author Administrator
 * 
 */
@ReqType("industryTemplateAdd")
public class IndustryTemplateAdd extends WeixinReqParam {

	private String template_id_short; //模版编号

	public String getTemplate_id_short() {
		return template_id_short;
	}

	public void setTemplate_id_short(String template_id_short) {
		this.template_id_short = template_id_short;
	}
	
	
	
}
