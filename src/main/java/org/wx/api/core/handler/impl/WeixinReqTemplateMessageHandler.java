package org.wx.api.core.handler.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.wx.api.core.annotation.ReqType;
import org.wx.api.core.exception.WexinReqException;
import org.wx.api.core.handler.WeiXinReqHandler;
import org.wx.api.core.req.model.WeixinReqConfig;
import org.wx.api.core.req.model.WeixinReqParam;
import org.wx.api.core.req.model.message.template.IndustryTemplateMessageSend;
import org.wx.api.core.util.HttpRequestProxy;
import org.wx.api.core.util.WeiXinReqUtil;

public class WeixinReqTemplateMessageHandler implements WeiXinReqHandler{
	
	private static Logger logger = Logger.getLogger(WeixinReqTemplateMessageHandler.class);

	@Override
	public String doRequest(WeixinReqParam weixinReqParam) throws WexinReqException {
		String strReturnInfo = "";
		if(weixinReqParam.getClass().isAnnotationPresent(ReqType.class)){
			ReqType reqType = weixinReqParam.getClass().getAnnotation(ReqType.class);
			WeixinReqConfig objConfig = WeiXinReqUtil.getWeixinReqConfig(reqType.value());
			if(objConfig != null){
				String reqUrl = objConfig.getUrl();
				IndustryTemplateMessageSend mc = (IndustryTemplateMessageSend) weixinReqParam;
				Map<String, String> parameters = new HashMap<String, String>();
				parameters.put("access_token", mc.getAccess_token());
				String jsonData = getMsgJson(mc) ;
				logger.info("处理模板消息"+jsonData);
				strReturnInfo = HttpRequestProxy.doJsonPost(reqUrl, parameters, jsonData);
			}
		}else{
			logger.info("没有找到对应的配置信息");
		}
		return strReturnInfo;
	}

	private String getMsgJson(IndustryTemplateMessageSend mc) {
		
		return null;
	}

}
