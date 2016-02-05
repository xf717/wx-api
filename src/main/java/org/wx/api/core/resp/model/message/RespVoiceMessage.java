package org.wx.api.core.resp.model.message;

import org.wx.api.core.resp.model.message.base.BaseRespMessage;
import org.wx.api.core.resp.model.message.base.Voice;

/**
 * 回复语音消息 voice类型
 * 
 * @author Administrator
 * 
 */
public class RespVoiceMessage extends BaseRespMessage {

	private Voice Voice;

	public Voice getVoice() {
		return Voice;
	}

	public void setVoice(Voice voice) {
		Voice = voice;
	}

}
