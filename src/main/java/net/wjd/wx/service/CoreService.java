package net.wjd.wx.service;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.wjd.wx.model.response.TextMessage;
import net.wjd.wx.util.MessageUtil;

public class CoreService {
	
	public static String processRequest(HttpServletRequest request){
		String respXml = null;
		//默认返回的文本消息内容
		String respContent = "未知的消息类型";
		
		try{
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			System.out.println(requestMap);
			String fromUserName = requestMap.get("FromUserName");
			String toUserName = requestMap.get("ToUserName");
			String msgType = requestMap.get("MsgType");
			
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			
			if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)){
				respContent = "您发送的是文本消息！";
			}else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)){
				respContent = "您发送的是图片消息！";
			}else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)){
				respContent = "您发送的是语音消息！";
			}else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)){
				respContent = "您发送的是视频消息！";
			}else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)){
				respContent = "您发送的是地理位置消息！";
			}else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)){
				respContent = "您发送的是链接消息！";
			}else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)){
				String eventType = requestMap.get("Event");
				if(eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)){
					respContent = "谢谢您的关注！";
				}else if(eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)){
					//取消订阅后，用户不会再收到公众账号发送的消息，因此不需要回复
				}else if(eventType.equals(MessageUtil.EVENT_TYPE_SCAN)){
					
				}else if(eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)){
					
				}else if(eventType.equals(MessageUtil.EVENT_TYPE_CLICK)){
					respContent = "您点击了一个按钮！！";
				} 
			}
			textMessage.setContent(respContent);
			respXml = MessageUtil.messageToXml(textMessage);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return respXml;
	}

}
