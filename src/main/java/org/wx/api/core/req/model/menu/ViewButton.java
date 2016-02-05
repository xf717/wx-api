package org.wx.api.core.req.model.menu;

/**
 *  view类型的菜单
 * @author Administrator
 *
 */
public class ViewButton extends Button {
	
	private String type; //类型为view
	private String url; //链接地址

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
