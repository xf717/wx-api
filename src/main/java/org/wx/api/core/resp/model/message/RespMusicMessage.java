package org.wx.api.core.resp.model.message;

import org.wx.api.core.resp.model.message.base.BaseRespMessage;
import org.wx.api.core.resp.model.message.base.Music;

/**
 * 回复音乐消息 类型为music
 * @author Administrator
 */
public class RespMusicMessage extends BaseRespMessage{
	
	//大小写需要注意，需要根据API的来定义
	private Music Music;

	public Music getMusic() {
		return Music;
	}

	public void setMusic(Music music) {
		Music = music;
	}

}
