package org.wx.api.core.handler.impl;

import java.util.Map;

import org.apache.log4j.Logger;
import org.wx.api.core.annotation.ReqType;
import org.wx.api.core.exception.WexinReqException;
import org.wx.api.core.handler.WeiXinReqHandler;
import org.wx.api.core.req.model.WeixinReqConfig;
import org.wx.api.core.req.model.WeixinReqParam;
import org.wx.api.core.util.HttpRequestProxy;
import org.wx.api.core.util.WeiXinConstant;
import org.wx.api.core.util.WeiXinReqUtil;

/**
 * 默认请求处理实现
 * @author Administrator
 *
 */
public class WeixinReqDefaultHandler implements WeiXinReqHandler {
	
	private static Logger logger = Logger.getLogger(WeixinReqDefaultHandler.class);

	@Override
	public String doRequest(WeixinReqParam weixinReqParam) throws WexinReqException {
		String strReturnInfo = "";
		if(weixinReqParam.getClass().isAnnotationPresent(ReqType.class)){
			ReqType reqType = weixinReqParam.getClass().getAnnotation(ReqType.class);
			WeixinReqConfig objConfig = WeiXinReqUtil.getWeixinReqConfig(reqType.value());
			if(objConfig != null){
				String reqUrl = objConfig.getUrl();
				String method = objConfig.getMethod();
				String datatype = objConfig.getDatatype();
				Map<String,Object> parameters = WeiXinReqUtil.getWeixinReqParam(weixinReqParam);
				if(WeiXinConstant.JSON_DATA_TYPE.equalsIgnoreCase(datatype)){
					parameters.clear();
					parameters.put("access_token", weixinReqParam.getAccess_token());
					String jsonData = WeiXinReqUtil.getWeixinParamJson(weixinReqParam);
					System.out.println("jsonData=="+jsonData);
					strReturnInfo = HttpRequestProxy.doJsonPost(reqUrl, parameters, jsonData);
				}else{
					if(WeiXinConstant.REQUEST_GET.equalsIgnoreCase(method)){
						strReturnInfo = HttpRequestProxy.doGet(reqUrl, parameters, "UTF-8");
					}else{
						strReturnInfo = HttpRequestProxy.doPost(reqUrl, parameters, "UTF-8");
					}
				}
			}
		}else{
			logger.info("没有找到对应的配置信息");
		}
		return strReturnInfo;
	}

}
