package org.wx.api.core.handler;

import org.wx.api.core.exception.WexinReqException;
import org.wx.api.core.req.model.WeixinReqParam;

/**
 * 获取微信接口信息
 * @author Administrator
 *
 */
public interface WeiXinReqHandler {
	
	public String doRequest(WeixinReqParam weixinReqParam) throws WexinReqException;

}
