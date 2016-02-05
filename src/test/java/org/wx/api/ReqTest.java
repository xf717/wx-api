package org.wx.api;

import java.util.List;

import org.junit.Test;

import org.wx.api.core.exception.WexinReqException;
import org.wx.api.core.req.model.message.template.IndustryTemplateMessageSend;
import org.wx.api.core.req.model.message.template.TemplateData;
import org.wx.api.core.req.model.message.template.TemplateMessage;
import org.wx.api.wxbase.token.TokenAPI;
import org.wx.api.wxmessage.GetAutoReplyRuleAPI;
import org.wx.api.wxmessage.TemplateMessageAPI;
import org.wx.api.wxmessage.model.auto.AutoReplyInfoRule;
import org.wx.api.wxmessage.model.template.AddTemplate;
import org.wx.api.wxuser.GroupAPI;
import org.wx.api.wxuser.model.group.WeixinGroup;

import junit.framework.TestCase;
import net.sf.json.JSONObject;

/**
 * Unit test for simple App.
 */
public class ReqTest extends TestCase{
 
	@Test
	public void testGetToken() throws WexinReqException{
		String access_token = TokenAPI.getToken("wxfb35e313189f6359", "86cab7ed990ea26b5520a082dd38b5e8");
	}
	
	@Test
	public void testTemplateMessage() throws WexinReqException{
		String access_token = TokenAPI.getToken("wxfb35e313189f6359", "86cab7ed990ea26b5520a082dd38b5e8");
		AddTemplate getTemplate = TemplateMessageAPI.addTemplate(access_token, "OPENTM206956452");
		IndustryTemplateMessageSend industryTemplateMessageSend = new IndustryTemplateMessageSend();
		industryTemplateMessageSend.setAccess_token(access_token);
		industryTemplateMessageSend.setTemplate_id(getTemplate.getTemplate_id());
		industryTemplateMessageSend.setTouser("ocyeyuPuDbavDJTFmGlLi-nUJ13c");
		industryTemplateMessageSend.setUrl("www.baidu.com");
		TemplateMessage data = new TemplateMessage();
		TemplateData first = new TemplateData();
		first.setColor("#173177");
		first.setValue("恭喜你购买成2323功！");
		
		
		TemplateData keynote1= new TemplateData();
		keynote1.setColor("#173177");
		keynote1.setValue("巧克22力");
		
		TemplateData keynote2= new TemplateData();
		keynote2.setColor("39.8元");
		keynote2.setValue("恭喜你购买成功！");
		
		TemplateData keynote3= new TemplateData();
		keynote3.setColor("#173177");
		keynote3.setValue("2014年9月16日");
		
		TemplateData remark= new TemplateData();
		remark.setColor("#173177");
		remark.setValue("欢迎再次购买！");
		data.setFirst(first);
		data.setKeynote1(keynote1);
		data.setKeynote2(keynote2);
		data.setKeynote3(keynote3);
		data.setRemark(remark);
		industryTemplateMessageSend.setData(data);
		String jsonString1 = JSONObject.fromObject(industryTemplateMessageSend).toString();
		System.out.println(jsonString1);
	}
	
	@Test
	public void testGetAutoReplyRule() throws WexinReqException{
		String access_token = TokenAPI.getToken("wxfb35e313189f6359", "86cab7ed990ea26b5520a082dd38b5e8");
		AutoReplyInfoRule autoReplyInfoRule = GetAutoReplyRuleAPI.getAutoReplyInfoRule(access_token);
		System.out.println("is_add_friend_reply_open=="+autoReplyInfoRule.getIs_add_friend_reply_open());
		System.out.println("is_autoreply_open=="+autoReplyInfoRule.getIs_autoreply_open());
		
	}
	
	@Test
	public void testCreateGroup() throws WexinReqException{
		String access_token = TokenAPI.getToken("wxfb35e313189f6359", "86cab7ed990ea26b5520a082dd38b5e8");
		WeixinGroup weixinGroup =  GroupAPI.createGroup(access_token, "test-api-1");
		System.out.println("id=="+weixinGroup.getId());
		System.out.println("name=="+weixinGroup.getName());
	}
	
}
