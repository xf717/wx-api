package org.wx.api.wxuser;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wx.api.core.exception.WexinReqException;
import org.wx.api.core.req.WeiXinReqService;
import org.wx.api.core.req.model.user.BatchGroupMembersUpdate;
import org.wx.api.core.req.model.user.Group;
import org.wx.api.core.req.model.user.GroupCreate;
import org.wx.api.core.req.model.user.GroupDelete;
import org.wx.api.core.req.model.user.GroupGet;
import org.wx.api.core.req.model.user.GroupGetId;
import org.wx.api.core.req.model.user.GroupMembersUpdate;
import org.wx.api.core.req.model.user.GroupUpdate;
import org.wx.api.core.util.WeiXinConstant;
import org.wx.api.wxuser.model.group.WeixinGroup;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class GroupAPI {
	
	private static Logger logger = LoggerFactory.getLogger(GroupAPI.class);
	
	/**
	 * 创建分组
	 * @param accesstoken
	 * @param groupName
	 * @return
	 * @throws WexinReqException
	 */
	public static WeixinGroup createGroup(String accesstoken ,String groupName) throws WexinReqException{
		GroupCreate group = new GroupCreate();
		Group g = new Group();
		g.setName(groupName);
		
		group.setAccess_token(accesstoken);
		group.setGroup(g);
		
		WeixinGroup weixinGroup = null;
		
		JSONObject jsonObject = WeiXinReqService.getInstance().doWeinxinReqJson(group);
		if(null != jsonObject){
			weixinGroup = new WeixinGroup();
			weixinGroup.setId(jsonObject.getJSONObject("group").getInt("id"));
			weixinGroup.setName(jsonObject.getJSONObject("group").getString("name"));
		}
		return weixinGroup;
	}
	
	/**
	 * 查询所有分组
	 * @param accessToken
	 * @return
	 * @throws WexinReqException 
	 */
	@SuppressWarnings("unchecked")
	public static List<WeixinGroup> getGroups(String accessToken) throws WexinReqException {
		GroupGet groupGet = new GroupGet();
		groupGet.setAccess_token(accessToken);
		List<WeixinGroup> weixinGroupList = null;
		JSONObject jsonObject = WeiXinReqService.getInstance().doWeinxinReqJson(groupGet);
		if (null != jsonObject) {
			try {
				weixinGroupList = JSONArray.toList(jsonObject.getJSONArray("groups"), WeixinGroup.class);
			} catch (JSONException e) {
				weixinGroupList = null;
				int errorCode = jsonObject.getInt(WeiXinConstant.RETURN_ERROR_INFO_CODE);
				String errorMsg = jsonObject.getString(WeiXinConstant.RETURN_ERROR_INFO_MSG);
				logger.error("查询分组失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		return weixinGroupList;
	}
	
	/**
	 * 查询用户所在分组
	 * @param accessToken
	 * @param openid
	 * @return
	 * @throws WexinReqException
	 */
	public static int getGroupId(String accessToken,String openid) throws WexinReqException{
		int groupId = 0;
		GroupGetId groupGetId = new GroupGetId();
		groupGetId.setAccess_token(accessToken);
		groupGetId.setOpenid(openid);
		JSONObject jsonObject = WeiXinReqService.getInstance().doWeinxinReqJson(groupGetId);
		if (null != jsonObject) {
			try {
				groupId = jsonObject.getInt("groupid");
			} catch (Exception e) {
				int errorCode = jsonObject.getInt(WeiXinConstant.RETURN_ERROR_INFO_CODE);
				String errorMsg = jsonObject.getString(WeiXinConstant.RETURN_ERROR_INFO_MSG);
				logger.error("查询用户所在的分组失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		return groupId;
	}
	
	/**
	 * 修改分组名称
	 * @param accessToken
	 * @param groupId
	 * @param groupName
	 * @return
	 * @throws WexinReqException 
	 */
	public static boolean updateGroup(String accessToken, int groupId, String groupName) throws WexinReqException {
		boolean result = false;
		GroupUpdate groupUpdate = new GroupUpdate();
		groupUpdate.setAccess_token(accessToken);
		Group group = new Group();
		group.setId(groupId);
		group.setName(groupName);
		groupUpdate.setGroup(group);
		JSONObject jsonObject = WeiXinReqService.getInstance().doWeinxinReqJson(groupUpdate);
		if (null != jsonObject) {
			int errorCode = jsonObject.getInt(WeiXinConstant.RETURN_ERROR_INFO_CODE);
			String errorMsg = jsonObject.getString(WeiXinConstant.RETURN_ERROR_INFO_MSG);
			if (0 == errorCode) {
				result = true;
				logger.info("修改分组名成功 errcode:{} errmsg:{}", errorCode, errorMsg);
			} else {
				logger.error("修改分组名失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		return result;
	}
	
	/**
	 * 移动用户分组
	 * @param accessToken
	 * @param openId
	 * @param groupId
	 * @return
	 * @throws WexinReqException 
	 */
	public static boolean updateMemberGroup(String accessToken, String openId, int groupId) throws WexinReqException {
		boolean result = false;
		GroupMembersUpdate groupMembersUpdate = new GroupMembersUpdate();
		groupMembersUpdate.setAccess_token(accessToken);
		groupMembersUpdate.setOpenid(openId);
		groupMembersUpdate.setTo_groupid(groupId);
		JSONObject jsonObject = WeiXinReqService.getInstance().doWeinxinReqJson(groupMembersUpdate);
		if (null != jsonObject) {
			int errorCode = jsonObject.getInt(WeiXinConstant.RETURN_ERROR_INFO_CODE);
			String errorMsg = jsonObject.getString(WeiXinConstant.RETURN_ERROR_INFO_MSG);
			if (0 == errorCode) {
				result = true;
				logger.info("移动用户分组成功 errcode:{} errmsg:{}", errorCode, errorMsg);
			} else {
				logger.error("移动用户分组失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		return result;
	}
	
	/**
	 * 批量移动用户分组
	 * @param accessToken
	 * @param openidList
	 * @param groupId
	 * @return
	 * @throws WexinReqException
	 */
	public static boolean batchGroupMembersUpdate(String accessToken, List<String> openidList, int groupId) throws WexinReqException{
		boolean result = false;
		BatchGroupMembersUpdate bgm = new BatchGroupMembersUpdate();
		bgm.setAccess_token(accessToken);
		bgm.setOpenid_list(openidList);
		bgm.setTo_groupid(groupId);
		JSONObject jsonObject = WeiXinReqService.getInstance().doWeinxinReqJson(bgm);
		if (null != jsonObject) {
			int errorCode = jsonObject.getInt(WeiXinConstant.RETURN_ERROR_INFO_CODE);
			String errorMsg = jsonObject.getString(WeiXinConstant.RETURN_ERROR_INFO_MSG);
			if (0 == errorCode) {
				result = true;
				logger.info("批量移动用户分组成功 errcode:{} errmsg:{}", errorCode, errorMsg);
			} else {
				logger.error("批量移动用户分组失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		return result;
	}
	
	/**
	 * 删除分组  接口可以删除,删除成功后接口无法正常返回成功JSON,只返回格式为{}又括号的字符,所以无法获取errorcode的值,return false
	 * @param accessToken
	 * @param groupid
	 * @return
	 * @throws WexinReqException
	 */
	public static boolean deleteGroup(String accessToken,int groupid) throws WexinReqException{
		boolean result = false;
		GroupDelete groupDelete = new GroupDelete();
		groupDelete.setAccess_token(accessToken);
		Group group = new Group();
		group.setId(groupid);
		groupDelete.setGroup(group);
		JSONObject jsonObject = WeiXinReqService.getInstance().doWeinxinReqJson(groupDelete);
		System.out.println("删除组的JSON=="+jsonObject.toString());
		if (null != jsonObject) {
			Object error = jsonObject.get(WeiXinConstant.RETURN_ERROR_INFO_CODE);
			if(null != error){
				int errorCode = jsonObject.getInt(WeiXinConstant.RETURN_ERROR_INFO_CODE);
				String errorMsg = jsonObject.getString(WeiXinConstant.RETURN_ERROR_INFO_MSG);
				if (0 == errorCode) {
					result = true;
					logger.info("删除分组 errcode:{} errmsg:{}", errorCode, errorMsg);
				} else {
					logger.error("删除分组 errcode:{} errmsg:{}", errorCode, errorMsg);
				}
			}
		}
		return result;
	}
	
	

}
