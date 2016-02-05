package org.wx.api.core.req.model.menu;

import org.wx.api.core.annotation.ReqType;
import org.wx.api.core.req.model.WeixinReqParam;

/**
 * 创建菜单
 * @author Administrator
 */
@ReqType(value = "createMenu")
public class Menu extends WeixinReqParam{
	
	private Button[] button;

	public Button[] getButton() {
		return button;
	}

	public void setButton(Button[] button) {
		this.button = button;
	}  

}
