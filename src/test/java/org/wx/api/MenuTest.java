package org.wx.api;

import org.junit.Test;
import org.wx.api.core.common.util.CommonUtil;
import org.wx.api.core.exception.WexinReqException;
import org.wx.api.core.req.model.AccessToken;
import org.wx.api.core.req.model.menu.Button;
import org.wx.api.core.req.model.menu.ComplexButton;
import org.wx.api.core.req.model.menu.ViewButton;
import org.wx.api.wxbase.token.TokenAPI;
import org.wx.api.wxmenu.MenuAPI;
import org.wx.api.wxmenu.model.CustomWeixinButtonConfig;
import org.wx.api.wxmenu.model.WeixinButtonExtend;

import junit.framework.TestCase;

public class MenuTest extends TestCase{
	
	@Test
	public void testCreateMenu() throws WexinReqException{
		
		//一级栏目子菜单
		ViewButton btn1 = new ViewButton();   
    	btn1.setName("今天");  
    	btn1.setType("view");
    	btn1.setUrl("http://www.li.cc");
    	
    	ViewButton btn2 = new ViewButton();
    	btn2.setName("明天");
    	btn2.setType("view");
    	btn2.setUrl("http://www.li.cc");
    	
    	ViewButton btn3 = new ViewButton();
    	btn3.setName("后天");
    	btn3.setType("view");
    	btn3.setUrl("http://www.li.cc");
    	
    	ViewButton btn4 = new ViewButton();
    	btn4.setName("最后一周");
    	btn4.setType("view");
    	btn4.setUrl("http://www.li.cc");
    	
    	//一级菜单
    	ComplexButton mainBtn1 = new ComplexButton(); 
    	mainBtn1.setName("近期拍卖");
    	mainBtn1.setSub_button(new Button[]{btn1,btn2,btn3,btn4});
    	
    	//二级栏目
    	ViewButton btn5 = new ViewButton();
    	btn5.setName("拍卖平台");
    	btn5.setType("view");
    	btn5.setUrl("http://www.li.cc");
    	
    	ViewButton btn6 = new ViewButton();
    	btn6.setName("拍卖检索");
    	btn6.setType("view");
    	btn6.setUrl("http://www.li.cc");
    	
    	ViewButton btn7 = new ViewButton();
    	btn7.setName("行情参考");
    	btn7.setType("view");
    	btn7.setUrl("http://www.li.cc");
    	
    	ComplexButton mainBtn2 = new ComplexButton(); 
    	mainBtn2.setName("搜一搜");
    	mainBtn2.setSub_button(new Button[]{btn5,btn6,btn7});
    	
    	//三级栏目
    	ViewButton btn8 = new ViewButton();
    	btn8.setName("域名秀");
    	btn8.setType("view");
    	btn8.setUrl("http://www.li.cc");
    	
    	ViewButton btn9 = new ViewButton();
    	btn9.setName("我要拍卖");
    	btn9.setType("view");
    	btn9.setUrl("http://www.li.cc");
    	
    	ViewButton btn10 = new ViewButton();
    	btn10.setName("广告合作");
    	btn10.setType("view");
    	btn10.setUrl("http://www.li.cc");
    	
    	ComplexButton mainBtn3 = new ComplexButton(); 
    	mainBtn3.setName("关于我们");
    	mainBtn3.setSub_button(new Button[]{btn8,btn9,btn10});
    	
    	Button [] buttons = new Button[]{mainBtn1,mainBtn2,mainBtn3};
    	
    	String access_token = TokenAPI.getToken("wxfb35e313189f6359", "86cab7ed990ea26b5520a082dd38b5e8");
    	int createMenu = MenuAPI.createMenu(access_token, buttons);
    	System.out.println("createMenu=="+createMenu);
	}
	
	@Test
	public void testGetMenu(){
		try {
			String access_token = TokenAPI.getToken("wxfb35e313189f6359", "86cab7ed990ea26b5520a082dd38b5e8");
			String result = MenuAPI.getMenu(access_token);
			System.out.println(result);
		} catch (WexinReqException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDeleteMenu() throws WexinReqException{
		String access_token = TokenAPI.getToken("wxfb35e313189f6359", "86cab7ed990ea26b5520a082dd38b5e8");
		boolean delete = MenuAPI.deleteMenu(access_token);
		System.out.println("删除是否成功=="+delete);
	}
	
	@Test
	public void testCustomWeixinButtonConfig() throws WexinReqException{
		String access_token = TokenAPI.getToken("wxfb35e313189f6359", "86cab7ed990ea26b5520a082dd38b5e8");
		CustomWeixinButtonConfig cbc = MenuAPI.getAllMenuConfigure(access_token);
		System.out.println(cbc.getIs_menu_open());
		for (WeixinButtonExtend wbe : cbc.getSelfmenu_info()) {
			System.out.println("type:"+wbe.getType());
			System.out.println("name:"+wbe.getName());
			System.out.println("key:"+wbe.getKey());
			System.out.println("value:"+wbe.getValue());
			System.out.println("url:"+wbe.getUrl());
		}
		
	}

}
