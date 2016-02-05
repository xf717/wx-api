package org.wx.api.core.common;

/**
 * 微信全局说明
 * @author Administrator
 *
 */
public enum Global {
	SYS_REQ_ERROR(-1,"系统繁忙，此时请开发者稍候再试"),
	SYS_REQ_SUNCCES(0,"请求成功"),
	
	SYS_40001(40001,"获取access_token时AppSecret错误，或者access_token无效。请开发者认真比对AppSecret的正确性，或查看是否正在为恰当的公众号调用接口"),
	SYS_40002(40002,"不合法的凭证类型"),
	SYS_40003(40003,"不合法的OpenID，请开发者确认OpenID（该用户）是否已关注公众号，或是否是其他公众号的OpenID"),
	SYS_40004(40004,"不合法的媒体文件类型"),
	SYS_40005(40005,"不合法的文件类型"),
	SYS_40006(40006,"不合法的文件大小"),
	SYS_40007(40007,"不合法的媒体文件id"),
	SYS_40008(40008,"不合法的消息类型"),
	SYS_40009(40009,"不合法的图片文件大小"),
	SYS_40010(40010,"不合法的语音文件大小"),
	SYS_40011(40011,"不合法的视频文件大小"),
	SYS_40012(40012,"不合法的缩略图文件大小"),
	SYS_40013(40013,"不合法的AppID，请开发者检查AppID的正确性，避免异常字符，注意大小写"),
	SYS_40014(40014,"不合法的access_token，请开发者认真比对access_token的有效性（如是否过期），或查看是否正在为恰当的公众号调用接口"),
	SYS_40015(40015,"不合法的菜单类型"),
	SYS_40016(40016,"不合法的按钮个数");
	
	Global(int code,String message){
		this.code = code;
		this.message = message;
	}
	
	private int code;
	
	private String message;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
