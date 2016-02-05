package org.wx.api.wxmenu;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wx.api.core.exception.WexinReqException;
import org.wx.api.core.req.WeiXinReqService;
import org.wx.api.core.req.model.menu.Button;
import org.wx.api.core.req.model.menu.Menu;
import org.wx.api.core.req.model.menu.MenuConfigureGet;
import org.wx.api.core.req.model.menu.MenuDelete;
import org.wx.api.core.req.model.menu.MenuGet;
import org.wx.api.core.util.WeiXinConstant;
import org.wx.api.extend.CustomJsonConfig;
import org.wx.api.wxmenu.model.CustomWeixinButtonConfig;
import org.wx.api.wxmenu.model.WeixinButtonExtend;
import org.wx.api.wxmessage.model.WxArticleConfig;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

/**
 * 微信菜单API
 * @author Administrator
 *
 */
public class MenuAPI {
	
	private static Logger logger = LoggerFactory.getLogger(MenuAPI.class);
	
	/**
	 * 创建菜单
	 * @param accessToken
	 * @param buttons
	 * @return
	 * @throws WexinReqException
	 */
	public static int createMenu(String accessToken,Button [] buttons) throws WexinReqException{
		int result = 0;
		Menu menu = new Menu();
		menu.setAccess_token(accessToken);
		menu.setButton(buttons);
		JSONObject jsonObject = WeiXinReqService.getInstance().doWeinxinReqJson(menu);
		if(null != jsonObject){
			 if (0 != jsonObject.getInt("errcode")) {  
				 result = jsonObject.getInt("errcode"); 
				 logger.error("创建菜单失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg")); 
			 }
		}
		return result;
	}
	
	/**
	 * 查询自定义菜单
	 * @param accessToken
	 * @return
	 * @throws WexinReqException 
	 */
	public static String getMenu(String accessToken) throws WexinReqException{
		MenuGet get = new MenuGet();
		get.setAccess_token(accessToken);
		String result = null;
		JSONObject jsonObject =  WeiXinReqService.getInstance().doWeinxinReqJson(get);
		if (null != jsonObject) {
			result = jsonObject.toString();
		}
		return result;
	}
	
	/**
	 * 删除菜单
	 * @param accessToken
	 * @return
	 * @throws WexinReqException
	 */
	public static boolean deleteMenu(String accessToken) throws WexinReqException{
		MenuDelete delete = new MenuDelete();
		delete.setAccess_token(accessToken);
		JSONObject jsonObject =  WeiXinReqService.getInstance().doWeinxinReqJson(delete);
		if(null != jsonObject){
			int errorCode = jsonObject.getInt(WeiXinConstant.RETURN_ERROR_INFO_CODE);
			String errorMsg = jsonObject.getString(WeiXinConstant.RETURN_ERROR_INFO_MSG);
			if(0 == errorCode){
				return true;
			}else{
				logger.error("删除菜单失败 errcode:{} errmsg:{}", errorCode, errorMsg);
				return false;
			}
		}
		return false;
	}
	
	/**
	 * 获取自定义菜单配置接口
	 * @param accessToken
	 * @return
	 * @throws WexinReqException
	 */
	public static CustomWeixinButtonConfig getAllMenuConfigure(String accessToken) throws WexinReqException{
		MenuConfigureGet cmcg = new MenuConfigureGet();
		cmcg.setAccess_token(accessToken);
		JSONObject result = WeiXinReqService.getInstance().doWeinxinReqJson(cmcg);
		CustomWeixinButtonConfig customWeixinButtonConfig = (CustomWeixinButtonConfig) JSONObject.toBean(result, new CustomJsonConfig(CustomWeixinButtonConfig.class,"selfmenu_info"));
		JSONObject selfmenuInfo = result.getJSONObject("selfmenu_info");
		if(selfmenuInfo!=null && !JSONUtils.isNull(selfmenuInfo)){ 
			/**处理父类菜单 */
			JSONArray buttons = selfmenuInfo.getJSONArray("button");
			List<WeixinButtonExtend> listButton = new ArrayList<WeixinButtonExtend>();
			for(int i=0;i<buttons.size();i++){
				WeixinButtonExtend weixinButtonExtend = (WeixinButtonExtend) JSONObject.toBean(buttons.getJSONObject(i),new CustomJsonConfig(WeixinButtonExtend.class,"sub_button"));
				/**处理子类菜单 */
				JSONObject subButtonJsonObj = buttons.getJSONObject(i).getJSONObject("sub_button");
				if(subButtonJsonObj!=null && !JSONUtils.isNull(subButtonJsonObj)){
					JSONArray subButtons = subButtonJsonObj.getJSONArray("list");
					if (subButtons != null) {
						List<WeixinButtonExtend> listSubButton = new ArrayList<WeixinButtonExtend>();
						for (int j = 0; j < subButtons.size(); j++) {
							WeixinButtonExtend subBtn = (WeixinButtonExtend) JSONObject.toBean(subButtons.getJSONObject(j), new CustomJsonConfig(WeixinButtonExtend.class,"news_info"));
							/**处理菜单关联的图文消息 */
							JSONObject newsInfoJsonObj = subButtons.getJSONObject(j).getJSONObject("news_info");
							if(newsInfoJsonObj!=null && !JSONUtils.isNull(newsInfoJsonObj)){
								JSONArray newsInfos = newsInfoJsonObj.getJSONArray("list");
								List<WxArticleConfig> listNewsInfo = new ArrayList<WxArticleConfig>();
								for (int k = 0; k < newsInfos.size(); k++) {
									WxArticleConfig wxArticleConfig = (WxArticleConfig) JSONObject.toBean(newsInfos.getJSONObject(k), WxArticleConfig.class);
									listNewsInfo.add(wxArticleConfig);
								}
								subBtn.setNews_info(listNewsInfo);
							}
							listSubButton.add(subBtn);
						}
						weixinButtonExtend.setSub_button(listSubButton);
					}
				}
				listButton.add(weixinButtonExtend);
			}
			customWeixinButtonConfig.setSelfmenu_info(listButton);
		}
		return customWeixinButtonConfig;
	}
	

}
