package org.wx.api.core.req;

import org.wx.api.core.annotation.ReqType;
import org.wx.api.core.exception.WexinReqException;
import org.wx.api.core.handler.WeiXinReqHandler;
import org.wx.api.core.req.model.WeixinReqConfig;
import org.wx.api.core.req.model.WeixinReqParam;
import org.wx.api.core.util.WeiXinConstant;
import org.wx.api.core.util.WeiXinReqUtil;

import net.sf.json.JSONObject;

/**
 * 使用单例模式管理获取微信接口信息
 * 
 * @author Administrator
 *
 */
public class WeiXinReqService {

	private static WeiXinReqService weiXinReqUtil = null;

	private WeiXinReqService() {
		try {
			WeiXinReqUtil.initReqConfig("weixin-reqcongfig.xml");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * 获取公共的调用处理
	 * 
	 * @return
	 */
	public static WeiXinReqService getInstance() {
		if (weiXinReqUtil == null) {
			// 同步块，线程安全的创建实例
			synchronized (WeiXinReqService.class) {
				// 再次检查实例是否存在，如果不存在才真正的创建实例
				if (weiXinReqUtil == null) {
					weiXinReqUtil = new WeiXinReqService();
				}
			}
		}
		return weiXinReqUtil;
	}

	/**
	 * 传入请求的参数，获取对应接口返回信息
	 * @param weixinReqParam
	 * @return
	 * @throws WexinReqException
	 */
	public String doWeinxinReq(WeixinReqParam weixinReqParam) throws WexinReqException {
		String strReturnInfo = "";
		{
			if (weixinReqParam.getClass().isAnnotationPresent(ReqType.class)) {
				ReqType reqType = weixinReqParam.getClass().getAnnotation(ReqType.class);
				//从缓存中获取WeixinReqConfig 信息
				WeixinReqConfig objConfig = WeiXinReqUtil.getWeixinReqConfig(reqType.value());
				//注册映射服务处理
				WeiXinReqHandler handler = WeiXinReqUtil.getMappingHander(objConfig.getMappingHandler());
				strReturnInfo = handler.doRequest(weixinReqParam);
			}
		}
		return strReturnInfo;
	}
	
	public JSONObject doWeinxinReqJson(WeixinReqParam weixinReqParam) throws WexinReqException{
		String strResult = this.doWeinxinReq(weixinReqParam);
		System.out.println("strResult==="+strResult);
		JSONObject result = JSONObject.fromObject(strResult);
		Object error = result.get(WeiXinConstant.RETURN_ERROR_INFO_CODE);
		System.out.println("error===="+error);
		if(error !=null && Integer.parseInt(error.toString())!=0){
			throw new WexinReqException(result.toString());
		}
		return result;
	}

}
