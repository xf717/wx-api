package org.wx.api;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.wx.api.core.exception.WexinReqException;
import org.wx.api.core.req.model.user.UserInfo;
import org.wx.api.wxbase.token.TokenAPI;
import org.wx.api.wxuser.GroupAPI;
import org.wx.api.wxuser.UserAPI;
import org.wx.api.wxuser.model.group.WeixinGroup;
import org.wx.api.wxuser.model.user.WeixinUserInfo;
import org.wx.api.wxuser.model.user.WeixinUserList;

import junit.framework.TestCase;

/**
 * 用户组接口及用户接口测试
 */
public class UserTest extends TestCase{
 

	@Test
	public void testCreateGroup() throws WexinReqException{
		String access_token = TokenAPI.getToken("wxfb35e313189f6359", "86cab7ed990ea26b5520a082dd38b5e8");
		WeixinGroup weixinGroup =  GroupAPI.createGroup(access_token, "feng");
		System.out.println("id=="+weixinGroup.getId() + "                    name=="+weixinGroup.getName());
	}
	
	@Test
	public void testGetGroups() throws WexinReqException{
		List<WeixinGroup> weixinGroupList = GroupAPI.getGroups(TokenAPI.getToken());
		for (WeixinGroup weixinGroup : weixinGroupList) {
			System.out.println("id=="+weixinGroup.getId()+"    组名称=="+weixinGroup.getName() +"   组人员总数==="+weixinGroup.getCount());
		}
	}
	
	@Test
	public void testGetGroupId() throws WexinReqException{
		int groupid = GroupAPI.getGroupId(TokenAPI.getToken(), "oK9t-wgrak8MYLKseVYJx5xmZb-s");
		System.out.println(groupid);
	}
	
	@Test
	public void testUpdateGroup() throws WexinReqException{
		boolean update = GroupAPI.updateGroup(TokenAPI.getToken(), 100, "li.cc");
		System.out.println(update);
	}
	
	@Test
	public void testUpdateMemberGroup() throws WexinReqException{
		boolean result = GroupAPI.updateMemberGroup(TokenAPI.getToken(), "oK9t-wgrak8MYLKseVYJx5xmZb-s", 101);
		System.out.println("result=="+result);
	}
	
	@Test
	public void testBatchGroupMembersUpdate() throws WexinReqException{
		List<String> openidList = new ArrayList<>();
		openidList.add("oK9t-wrCc2gdz7jtPZcn7cbVNXPg");
		openidList.add("oK9t-wjOLL-P_BqAAxNK1Gskof48");
		openidList.add("oK9t-wk2Dlk-0Sk9mxi7jC60KMY0");
		boolean result = GroupAPI.batchGroupMembersUpdate(TokenAPI.getToken(), openidList, 101);
		System.out.println("result=="+result);
	}
	
	@Test
	public void testDeleteGroup() throws WexinReqException{
		boolean delete = GroupAPI.deleteGroup(TokenAPI.getToken(), 100);
		System.out.println(delete);
	}
	/*********************************用户信息
	 * @throws WexinReqException ****************************************************************/
	@Test
	public void testUserRemarkUpdate() throws WexinReqException{
		boolean result = UserAPI.userRemarkUpdate(TokenAPI.getToken(), "oK9t-wgrak8MYLKseVYJx5xmZb-s", "枫叶");
		System.out.println("result=="+result);
	}
	
	@Test
	public void testGetUserInfo() throws WexinReqException{
		WeixinUserInfo weixinUserInfo = UserAPI.getUserInfo(TokenAPI.getToken(),"oK9t-wgrak8MYLKseVYJx5xmZb-s");
		System.out.println("OPENID："+weixinUserInfo.getOpenId());
		System.out.println("关注时间："+weixinUserInfo.getSubscribeTime());
		System.out.println("关注状态："+weixinUserInfo.getSubscribe());
		System.out.println("昵称："+weixinUserInfo.getNickname());
		System.out.println("性别："+weixinUserInfo.getSex());
		System.out.println("国家："+weixinUserInfo.getCountry());
		System.out.println("省份："+weixinUserInfo.getProvince());
		System.out.println("城市："+weixinUserInfo.getCity());
		System.out.println("语言："+weixinUserInfo.getLanguage());
		System.out.println("头像："+weixinUserInfo.getHeadImgUrl());
		System.out.println("unionid=="+weixinUserInfo.getUnionid());
		System.out.println("备注："+weixinUserInfo.getRemark());
		System.out.println("所在组ID=="+weixinUserInfo.getGroupid());
	}
	
	@Test
	public void testBatchUserInfoList() throws WexinReqException{
		UserInfo userInfo = new UserInfo();
		userInfo.setOpenid("oK9t-wgrak8MYLKseVYJx5xmZb-s");
		
		UserInfo userInfo1 = new UserInfo();
		userInfo1.setOpenid("oK9t-wrCc2gdz7jtPZcn7cbVNXPg");
		List<UserInfo> userInfoList = new ArrayList<UserInfo>();
		userInfoList.add(userInfo);
		userInfoList.add(userInfo1);
		List<WeixinUserInfo> weixinUserInfoList = UserAPI.batchWeixinUserInfoList(TokenAPI.getToken(),userInfoList);
		for (WeixinUserInfo weixinUserInfo : weixinUserInfoList) {
			System.out.println("用户昵称=="+weixinUserInfo.getNickname());
		}
	}
	
	@Test
	public void testgetWeixinUserList() throws WexinReqException{
		String accessToken = TokenAPI.getToken();
		WeixinUserList weixinUserList = UserAPI.getUserList(accessToken, "oK9t-wgrak8MYLKseVYJx5xmZb-s");
		System.out.println("总数=="+weixinUserList.getTotal());
		System.out.println("个数=="+weixinUserList.getCount());
		for (String openid : weixinUserList.getOpenIdList()) {
			WeixinUserInfo weixinUserInfo = UserAPI.getUserInfo(accessToken,openid);
			System.out.println("openid===="+weixinUserInfo.getOpenId() + "  用户昵称==="+weixinUserInfo.getNickname());
		}
	}
	
	@Test
	public void testGetUserList(){
		
	}
	
}
