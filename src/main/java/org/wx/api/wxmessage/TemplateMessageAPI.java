package org.wx.api.wxmessage;

import org.wx.api.core.exception.WexinReqException;
import org.wx.api.core.req.WeiXinReqService;
import org.wx.api.core.req.model.message.template.IndustryTemplateAdd;
import org.wx.api.core.req.model.message.template.IndustryTemplateMessageSend;
import org.wx.api.core.req.model.message.template.IndustryTemplateSet;
import org.wx.api.core.util.WeiXinConstant;
import org.wx.api.wxmessage.model.template.AddTemplate;
import org.wx.api.wxmessage.model.template.SendTemplate;

import net.sf.json.JSONObject;


public class TemplateMessageAPI {
	
	/**
	 * 设置所属行业
	 * @param accessToken
	 * @param industry_id1
	 * @param industry_id2
	 * @return
	 * @throws WexinReqException 
	 */
	public static String setIndustry(String accessToken,String industry_id1,String industry_id2) throws WexinReqException{
		IndustryTemplateSet its = new IndustryTemplateSet(); 
		its.setAccess_token(accessToken);
		its.setIndustry_id1(industry_id1);
		its.setIndustry_id2(industry_id2);
		JSONObject result = WeiXinReqService.getInstance().doWeinxinReqJson(its);
		String msg = result.getString(WeiXinConstant.RETURN_ERROR_INFO_MSG);
		return msg;
	}
	
	/**
	 * 获取模版
	 * @param accessToken
	 * @param template_id_short
	 * @return
	 * @throws WexinReqException
	 */
	public static AddTemplate addTemplate(String accessToken,String template_id_short) throws WexinReqException{
		IndustryTemplateAdd ita = new IndustryTemplateAdd();
		ita.setAccess_token(accessToken);
		ita.setTemplate_id_short(template_id_short);
		AddTemplate addTemplate = null;
		JSONObject result = WeiXinReqService.getInstance().doWeinxinReqJson(ita);
		if(null != result){
			addTemplate = new AddTemplate();
			addTemplate.setErrcode(result.getInt(WeiXinConstant.RETURN_ERROR_INFO_CODE));
			addTemplate.setErrmsg(result.getString(WeiXinConstant.RETURN_ERROR_INFO_MSG));
			addTemplate.setTemplate_id(result.getString("template_id"));
		}
		return addTemplate;
	}
	
	/**
	 * 发送模版
	 * @param industryTemplateMessageSend  发送参数实体
	 * @return
	 * @throws WexinReqException 
	 */
	public static SendTemplate sendTemplate(IndustryTemplateMessageSend industryTemplateMessageSend) throws WexinReqException{
		SendTemplate sendTemplate = null;
		JSONObject result = WeiXinReqService.getInstance().doWeinxinReqJson(industryTemplateMessageSend);
		if(null != result){
			sendTemplate = new SendTemplate();
			sendTemplate.setErrcode(result.getInt(WeiXinConstant.RETURN_ERROR_INFO_CODE));
			sendTemplate.setErrmsg(result.getString(WeiXinConstant.RETURN_ERROR_INFO_MSG));
			sendTemplate.setMsgid(result.getInt("msgid"));
		}
		return sendTemplate;
	}

}
