package org.wx.api.core.resp.model.message;

import org.wx.api.core.resp.model.message.base.BaseRespMessage;

/**
 * 回复视频消息 类型为video
 * @author Administrator
 */
public class RespVideoMessage extends BaseRespMessage{

	//通过上传多媒体文件，得到的id，不能为空
	private String mediaId;
	//视频消息的标题 可以为空
	private String title;
	//视频消息的描述 可以为空
	private String description;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
}
