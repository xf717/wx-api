package org.wx.api.core.resp.model.message.base;

public class Music {

	// 音乐标题 是否必须：否
	private String Title;

	// 音乐描述 是否必须：否
	private String Description;
	// 音乐链接 是否必须：否
	private String MusicUrl;
	// 高品质音乐链接，wifi环境优先使用该链接播放音乐 是否必须：否
	private String HQMusicUrl;
	
	private String ThumbMediaId;

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getMusicUrl() {
		return MusicUrl;
	}

	public void setMusicUrl(String musicUrl) {
		MusicUrl = musicUrl;
	}

	public String getHQMusicUrl() {
		return HQMusicUrl;
	}

	public void setHQMusicUrl(String musicUrl) {
		HQMusicUrl = musicUrl;
	}

	public String getThumbMediaId() {
		return ThumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}

}
