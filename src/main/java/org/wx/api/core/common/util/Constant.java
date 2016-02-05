package org.wx.api.core.common.util;

/**
 * 微信全局返回说明
 * @author Administrator
 *
 */
public class Constant {
	
	/**
	 * 成功返回0
	 */
	 public static final int SUCCEED = 0;  
	 /**
	  * 系统返忙
	  */
	 public static final int SYS_BUSY = -1;
	 
	 public static final String SYS_40001 = "获取access_token时AppSecret错误，或者access_token无效";
	 public static final String SYS_40002 = "不合法的凭证类型";
	 public static final String SYS_40003 = "不合法的OpenID";
	 public static final String SYS_40004 = "不合法的媒体文件类型";
	 public static final String SYS_40005 = "不合法的文件类型";
	 public static final String SYS_40006 = "不合法的文件大小";
	 public static final String SYS_40007 = "不合法的媒体文件id";
	 public static final String SYS_40008 = "不合法的消息类型";
	 public static final String SYS_40009 = "不合法的图片文件大小";
	 public static final String SYS_40010 = "不合法的语音文件大小";
	 
	 public static final String SYS_40011 = "不合法的视频文件大小";
	 public static final String SYS_40012 = "不合法的缩略图文件大小";
	 public static final String SYS_40013 = "不合法的APPID";
	 public static final String SYS_40014 = "不合法的access_token";
	 public static final String SYS_40015 = "不合法的菜单类型";
	 public static final String SYS_40016 = "不合法的按钮个数";
	 public static final String SYS_40017 = "不合法的按钮个数";
	 public static final String SYS_40018 = "不合法的按钮名字长度";
	 public static final String SYS_40019 = "不合法的按钮KEY长度";
	 public static final String SYS_40020 = "不合法的按钮URL长度";
	 
	 public static final String SYS_40021 = "不合法的菜单版本号";
	 public static final String SYS_40022 = "不合法的子菜单级数";
	 public static final String SYS_40023 = "不合法的子菜单按钮个数";
	 public static final String SYS_40024 = "不合法的子菜单按钮类型";
	 public static final String SYS_40025 = "不合法的子菜单按钮名字长度";
	 public static final String SYS_40026 = "不合法的子菜单按钮KEY长度";
	 public static final String SYS_40027 = "不合法的子菜单按钮URL长度";
	 public static final String SYS_40028 = "不合法的自定义菜单使用用户";
	 public static final String SYS_40029 = "不合法的oauth_code";
	 public static final String SYS_40030 = "不合法的refresh_token";
	 
	 public static final String SYS_40031 = "不合法的openid列表";
	 public static final String SYS_40032 = "不合法的openid列表长度";
	 public static final String SYS_40033 = "不合法的请求字符，不能包含uxxxx格式的字符";
	 public static final String SYS_40035 = "不合法的参数";
	 public static final String SYS_40038 = "不合法的请求格式";
	 public static final String SYS_40039 = "不合法的URL长度";
	 public static final String SYS_40050 = "不合法的分组id";
	 
	 public static final String SYS_40051 = "分组名字不合法";
	 public static final String SYS_41001 = "缺少access_token参数";
	 public static final String SYS_41002 = "缺少appid参数";
	 public static final String SYS_41003 = "缺少refresh_token参数";
	 public static final String SYS_41004 = "缺少secret参数";
	 public static final String SYS_41005 = "缺少多媒体文件数据";
	 public static final String SYS_41006 = "缺少media_id参数";
	 
	 public static final String SYS_41007 = "缺少子菜单数据";
	 public static final String SYS_41008 = "缺少oauth code ";
	 public static final String SYS_41009 = "缺少openid";
	 public static final String SYS_42001 = "access_token超时";
	 public static final String SYS_42002 = "refresh_token超时";
	 public static final String SYS_42003 = "oauth_code超时";
	 public static final String SYS_43001 = "需要GET请求";
	 public static final String SYS_43002 = "需要POST请求";
	 public static final String SYS_43003 = "需要HTTPS请求";
	 public static final String SYS_43004 = "需要接收者关注";
	 public static final String SYS_43005 = "需要好友关系";
	 public static final String SYS_44001 = "多媒体文件为空";
	 public static final String SYS_44002 = "POST的数据包为空";
	 public static final String SYS_44003 = "图文消息内容为空";
	 public static final String SYS_44004 = "文本消息内容为空";
	 
	 public static final String SYS_45001 = "多媒体文件大小超过限制";
	 public static final String SYS_45002 = "消息内容超过限制";
	 public static final String SYS_45003 = "标题字段超过限制";
	 public static final String SYS_45004 = "描述字段超过限制";
	 public static final String SYS_45005 = "链接字段超过限制";
	 public static final String SYS_45006 = "图片链接字段超过限制";
	 public static final String SYS_45007 = "语音播放时间超过限制";
	 public static final String SYS_45008 = "图文消息超过限制";
	 public static final String SYS_45009 = "接口调用超过限制";
	 public static final String SYS_45010 = "创建菜单个数超过限制";
	 public static final String SYS_45015 = "回复时间超过限制";
	 public static final String SYS_45016 = "系统分组，不允许修改";
	 public static final String SYS_45017 = "分组名字过长";
	 public static final String SYS_45018 = "分组数量超过上限";
	 
	 public static final String SYS_46001 = "不存在媒体数据";
	 public static final String SYS_46002 = "不存在的菜单版本";
	 public static final String SYS_46003 = "不存在的菜单数据";
	 public static final String SYS_46004 = "不存在的用户";
	 public static final String SYS_47001 = "解析JSON/XML内容错误";
	 public static final String SYS_48001 = "api功能未授权";
	 public static final String SYS_50001 = "用户未授权该api";
}	 
