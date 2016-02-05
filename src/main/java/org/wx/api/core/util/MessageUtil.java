package org.wx.api.core.util;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wx.api.core.resp.model.message.RespImageMessage;
import org.wx.api.core.resp.model.message.RespMusicMessage;
import org.wx.api.core.resp.model.message.RespNewsMessage;
import org.wx.api.core.resp.model.message.RespTextMessage;
import org.wx.api.core.resp.model.message.RespVideoMessage;
import org.wx.api.core.resp.model.message.RespVoiceMessage;
import org.wx.api.core.resp.model.message.base.Article;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;


/**
 * 消息工具类
 * @author Administrator
 *
 */
public class MessageUtil {
	
	private static Logger logger = LoggerFactory.getLogger(MessageUtil.class);
	
	/**************************近回消息类型**********************************/
	 /** 
     * 返回消息类型：文本 
     */  
    public static final String RESP_MESSAGE_TYPE_TEXT = "text";  
    
    /**
     * 近回消息类型：图片
     */
    public static final String RESP_MESSAGE_TYPE_IMAGE = "image";
    
    /**
     * 返回消息类型：语音
     */
    public static final String RESP_MESSAGE_TYPE_VOICE = "voice";
    
    /**
     * 返回消息类型：视频
     */
    public static final String RESP_MESSAGE_TYPE_VIDEO = "video";
    
    /** 
     * 返回消息类型：音乐 
     */  
    public static final String RESP_MESSAGE_TYPE_MUSIC = "music"; 
    
    /** 
     * 返回消息类型：图文 
     */  
    public static final String RESP_MESSAGE_TYPE_NEWS = "news";  
    
    
    
    
    
    /**************************请求消息类型**********************************/
    
    /** 
     * 请求消息类型：文本 
     */  
    public static final String REQ_MESSAGE_TYPE_TEXT = "text";  
    
    /** 
     * 请求消息类型：图片 
     */  
    public static final String REQ_MESSAGE_TYPE_IMAGE = "image";  
    
    /** 
     * 请求消息类型：音频 
     */  
    public static final String REQ_MESSAGE_TYPE_VOICE = "voice"; 
    
    /**
     * 请求消息类型：视频
     */
    public static final String REQ_MESSAGE_TYPE_VIDEO = "video"; 
    
    /** 
     * 请求消息类型：地理位置 
     */  
    public static final String REQ_MESSAGE_TYPE_LOCATION = "location";
    
    /** 
    * 请求消息类型：链接 
    */  
   public static final String REQ_MESSAGE_TYPE_LINK = "link"; 

   /** 
    * 请求消息类型：推送 
    */  
   public static final String REQ_MESSAGE_TYPE_EVENT = "event";
   
   
   /** 
    * 事件类型：subscribe(订阅) 
    */  
   public static final String EVENT_TYPE_SUBSCRIBE = "subscribe"; 
   
   /** 
    * 事件类型：unsubscribe(取消订阅) 
    */  
   public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";  
   
  /**
   * 事件类型：scan(用户已关注时的扫描带参数二维码)
   */
   public static final String EVENT_TYPE_SCAN = "scan";

   /**
    * 事件类型：LOCATION(上报地理位置)
    */
   public static final String EVENT_TYPE_LOCATION = "LOCATION";
   
   /** 
    * 事件类型：CLICK(自定义菜单点击事件) 
    */  
   public static final String EVENT_TYPE_CLICK = "CLICK";  
	
	/**
	 * 解析微信发来的请求（XML）
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> parseXml(HttpServletRequest request) throws Exception{
		// 将解析结果存储在HashMap中  
		Map<String, String> map = new HashMap<String, String>();
		// 从request中取得输入流  
		InputStream inputStream = request.getInputStream();
		// 读取输入流 
		SAXReader reader = new SAXReader();  
		Document document = reader.read(inputStream); 
		// 得到xml根元素
		Element root = document.getRootElement();  
		// 得到根元素的所有子节点  
		List<Element> elementList = root.elements();
		// 遍历所有子节点
		for (Element element : elementList) {
			System.out.println("element.name=="+element.getName()  +"    element.text=="+element.getText());
			logger.info("节点名称:"+element.getName()  + "    节点文本值:"+element.getText());
			map.put(element.getName(), element.getText());  
		}
		// 释放资源  
	    inputStream.close();  
	    inputStream = null; 
	    return map;
	}  
	
	 /** 
     * 文本消息对象转换成xml 
     * @param textMessage 文本消息对象 
     * @return xml 
     */ 
	public static String textMessageToXml(RespTextMessage textMessage) {
		xstream.alias("xml", textMessage.getClass());
		return xstream.toXML(textMessage);  
	}
	
	/**
	 * 图片消息对象转换成xml
	 * @param imageMessage 图片消息对象
	 * @return
	 */
	public static String imageMessageToXml(RespImageMessage imageMessage){
		xstream.alias("xml", imageMessage.getClass());
		return xstream.toXML(imageMessage);
	}
	
	/**
	 * 语音消息对象转换成XML
	 * @param voiceMessage  语音消息对象
	 * @return
	 */
	public static String voiceMessageToXml(RespVoiceMessage voiceMessage){
		xstream.alias("xml", voiceMessage.getClass()); 
		return xstream.toXML(voiceMessage);
	}
	
	/**
	 * 视频消息对象转换成XML
	 * @param videoMessage
	 * @return
	 */
	public static String videoMessageToXml(RespVideoMessage videoMessage){
		xstream.alias("xml", videoMessage.getClass());
		return xstream.toXML(videoMessage);
	}
	
    /** 
     * 音乐消息对象转换成xml 
     * @param musicMessage 音乐消息对象 
     * @return xml 
     */  
    public static String musicMessageToXml(RespMusicMessage musicMessage) {  
        xstream.alias("xml", musicMessage.getClass());  
        return xstream.toXML(musicMessage);  
    }  
    
    
    /** 
     * 图文消息对象转换成xml 
     * @param newsMessage 图文消息对象 
     * @return xml 
     */  
    public static String newsMessageToXml(RespNewsMessage newsMessage) {  
        xstream.alias("xml", newsMessage.getClass());  
        xstream.alias("item", new Article().getClass());  
        return xstream.toXML(newsMessage);  
    }  
	
    /** 
     * 扩展xstream，使其支持CDATA块 
     *  
     */ 
	 private static XStream xstream = new XStream(new XppDriver() {
		 public HierarchicalStreamWriter createWriter(Writer out) {
			 return new PrettyPrintWriter(out) {
				 // 对所有xml节点的转换都增加CDATA标记  
				 boolean cdata = true;
				 @SuppressWarnings("unchecked")
				public void startNode(String name, Class clazz) {
					 super.startNode(name, clazz);  
				 }  
				 
				 protected void writeText(QuickWriter writer, String text) {
					 if (cdata) {  
	                       writer.write("<![CDATA[");  
	                       writer.write(text);  
	                       writer.write("]]>");  
	                    } else {  
	                       writer.write(text);  
	                    }  
				 }
			 };
		 }
	 });
	 
	 /**
	  * 计算采用utf-8编码方式时字符串所占字节数 
	  * @param content  文本内容
	  * @return 字节大小
	  */
	 public static int getByteSize(String content){
		 int size = 0;  
		 if(null != content){
			 try {
				size = content.getBytes("utf-8").length;
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}  
		 }
		 return size;
	 }
	 
	 public static boolean isQqFace(String content) {
		 boolean result = false;
		// 判断QQ表情的正则表达式  
		 String qqfaceRegex = "/::\\)|/::~|/::B|/::\\||/:8-\\)|/::<|/::$|/::X|/::Z|/::'\\(|/::-\\||/::@|/::P|/::D|/::O|/::\\(|/::\\+|/:--b|/::Q|/::T|/:" +
		 		",@P|/:,@-D|/::d|/:,@o|/::g|/:\\|-\\)|/::!|/::L|/::>|/::,@|/:,@f|/::-S|/:\\?|/:,@x|/:,@@|/::8|/:,@!|/:!!!|/:xx|/:bye|/:wipe|/:dig|/:handclap|/:&-\\(|/:B-\\)|/:<@|/:@>|/::-O|/:>-\\||" +
		 		"/:P-\\(|/::'\\||/:X-\\)|/::\\*|/:@x|/:8\\*|/:pd|/:" +
		 		"<W>|/:beer|/:basketb|/:oo|/:coffee|/:eat|/:pig|/:rose|/:fade|/:showlove|/:heart|/:break|/:cake|/:li|/:bome|/:kn|/:footb|/:ladybug|/:shit|/:moon|/:sun|/:gift|/:hug|/:strong|/:weak|/:share|/:v|/:@\\)|/:jj|/:@@|/:bad|/:lvu|/:no|/:ok|/:love|/:" +
		 		"<L>|/:jump|/:shake|/:<O>|/:circle|/:kotow|/:turn|/:skip|/:oY|/:#-0|/:hiphot|/:kiss|/:<&|/:&>";
		 Pattern p = Pattern.compile(qqfaceRegex);
		 Matcher m = p.matcher(content);
		 if (m.matches()) {
			 result = true;
		 }
		 return result;
	 }
	 
	 /** 
	  * 文本换行,在手机上换两行用2个\n 
	  *  
	  * @return 
	  */  
	 public static String getMainMenu() {  
	     StringBuffer buffer = new StringBuffer();  
	     buffer.append("您好，我是小q，请回复数字选择服务：").append("\n\n");  
	     buffer.append("1  天气预报").append("\n");  
	     buffer.append("2  公交查询").append("\n");  
	     buffer.append("3  周边搜索").append("\n");  
	     buffer.append("4  歌曲点播").append("\n");  
	     buffer.append("5  经典游戏").append("\n");  
	     buffer.append("6  美女电台").append("\n");  
	     buffer.append("7  人脸识别").append("\n");  
	     buffer.append("8  聊天唠嗑").append("\n\n");  
	     buffer.append("回复“?”显示此帮助菜单");  
	     return buffer.toString();  
	 }  
	 
	public static void main(String [] args){
		System.out.println(getMainMenu());
		
	}

}
