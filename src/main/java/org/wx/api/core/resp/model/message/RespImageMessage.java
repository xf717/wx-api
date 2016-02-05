package org.wx.api.core.resp.model.message;

import org.wx.api.core.resp.model.message.base.BaseRespMessage;

/**
 * 回复图片消息 image类型
 * @author Administrator
 *
 */
public class RespImageMessage extends BaseRespMessage{
	
	//通过上传多媒体文件，得到的id,不能为空
	private String mediaId;

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	
	
	
	

}
