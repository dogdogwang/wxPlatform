package net.wjd.wx.service;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.wjd.wx.model.response.TextMessage;
import net.wjd.wx.util.MessageUtil;

public class CoreService {
	
	public static String processRequest(HttpServletRequest request){
		String respXml = null;
		//Ĭ�Ϸ��ص��ı���Ϣ����
		String respContent = "δ֪����Ϣ����";
		
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
				respContent = "�����͵����ı���Ϣ��";
			}else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)){
				respContent = "�����͵���ͼƬ��Ϣ��";
			}else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)){
				respContent = "�����͵���������Ϣ��";
			}else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)){
				respContent = "�����͵�����Ƶ��Ϣ��";
			}else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)){
				respContent = "�����͵��ǵ���λ����Ϣ��";
			}else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)){
				respContent = "�����͵���������Ϣ��";
			}else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)){
				String eventType = requestMap.get("Event");
				if(eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)){
					respContent = "лл���Ĺ�ע��";
				}else if(eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)){
					//ȡ�����ĺ��û��������յ������˺ŷ��͵���Ϣ����˲���Ҫ�ظ�
				}else if(eventType.equals(MessageUtil.EVENT_TYPE_SCAN)){
					
				}else if(eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)){
					
				}else if(eventType.equals(MessageUtil.EVENT_TYPE_CLICK)){
					respContent = "�������һ����ť����";
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
