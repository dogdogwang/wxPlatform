package net.wjd.wx.util;

import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;





import net.wjd.wx.model.response.Article;
import net.wjd.wx.model.response.ImageMessage;
import net.wjd.wx.model.response.NewsMessage;
import net.wjd.wx.model.response.TextMessage;
import net.wjd.wx.model.response.VoiceMessage;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

public class MessageUtil {
	
	public static final String REQ_MESSAGE_TYPE_TEXT = "text";
	
	public static final String REQ_MESSAGE_TYPE_IMAGE = "image";
	
	public static final String REQ_MESSAGE_TYPE_VOICE = "voice";
	
	public static final String REQ_MESSAGE_TYPE_VIDEO = "video";
	
	public static final String REQ_MESSAGE_TYPE_LOCATION = "location";
	
	public static final String REQ_MESSAGE_TYPE_LINK = "link";
	
	public static final String REQ_MESSAGE_TYPE_EVENT = "event";
	
	public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
	
	public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
	
	public static final String EVENT_TYPE_SCAN = "scan";
	
	public static final String EVENT_TYPE_LOCATION = "LOCATION";
	
	public static final String EVENT_TYPE_CLICK = "CLICK";
	
	public static final String RESP_MESSAGE_TYPE_TEXT = "text";
	
	public static final String RESP_MESSAGE_TYPE_IMAGE = "image";
	
	public static final String RESP_MESSAGE_TYPE_VOICE = "voice";
	
	public static final String RESP_MESSAGE_TYPE_VIDEO = "video";
	
	public static final String RESP_MESSAGE_TYPE_MUSIC = "music";
	
	public static final String RESP_MESSAGE_TYPE_NEWS = "news";
	
	public static Map<String, String> parseXml(HttpServletRequest request)throws Exception{
		
		Map<String, String> map = new HashMap<String, String>();
		
		InputStream inputStream = request.getInputStream();
		
		SAXReader reader = new SAXReader();
		
		Document document = reader.read(inputStream);
		
		Element root = document.getRootElement();
		
		List<Element> elementList = root.elements();
		
		for(Element e : elementList){
			map.put(e.getName(), e.getText());
		}
		
		inputStream.close();
		
		inputStream = null;
		
		return map;
	}
	
	private static XStream xstream = new XStream(new XppDriver(){
		public HierarchicalStreamWriter createWriter(Writer out) {
			return new PrettyPrintWriter(out){
				//对所有xml节点的转换都增加CDATA标记
				boolean cdata = true;
				public void startNode(String name,Class clazz){
					super.startNode(name, clazz);
				}
				protected void writeText(QuickWriter writer,String text){
					if(cdata){
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					}else{
						writer.write(text);
					}
				}
				
			};
		}
	});
	

	public static String messageToXml(TextMessage textMessage){
		xstream.alias("xml", textMessage.getClass());
		return xstream.toXML(textMessage);
	}
	
	public static String messageToXml(ImageMessage message){
		xstream.alias("xml", message.getClass());
		return xstream.toXML(message);
	}
	public static String messageToXml(VoiceMessage message){
		xstream.alias("xml", message.getClass());
		return xstream.toXML(message);
	}
	
	public static String messageToXml(NewsMessage message){
		xstream.alias("xml", message.getClass());
		xstream.alias("item", new Article().getClass());
		return xstream.toXML(message);
	}
	
}
