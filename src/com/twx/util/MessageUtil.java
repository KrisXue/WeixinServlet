package com.twx.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;
import com.twx.po.TextMessage;

public class MessageUtil {
	public static final String MSGTYPE_TEXT = "text";
	public static final String MSGTYPE_IMAGE = "image";
	public static final String MSGTYPE_VOICE = "voice";
	public static final String MSGTYPE_LOCATION = "location";
	public static final String MSGTYPE_LINK = "link";
	public static final String MSGTYPE_EVENT = "event";
	public static final String EVENT_SUBSCRIBE = "subscribe";
	public static final String EVENT_SCAN = "SCAN";
	public static final String EVENT_LOCATION = "LOCATION";
	public static final String EVENT_CLICK = "CLICK";
	public static final String EVENT_VIEW = "VIEW";
	
	/**
	 * xmlתmap
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> xmlToMap(HttpServletRequest request ) throws IOException, DocumentException{
		SAXReader reader = new SAXReader();
		Map<String, String> map = new HashMap<String,String>();
		
		InputStream input = request.getInputStream();
		Document doc = reader.read(input);
		
		List<Element> list = new ArrayList<>();
		
		Element root = doc.getRootElement();
		
		list = root.elements();
		
		for(Element e: list){
			map.put(e.getName(), e.getText());
		}
		input.close();
		return map;
	}
	
	/**
	 * ���ص���Ϣ
	 * @param toUser
	 * @param fromUser
	 * @param content
	 * @return
	 */
	public static String initText(String toUser,String fromUser,String content){
		TextMessage tm = new TextMessage();
		tm.setFromUserName(toUser);
		tm.setToUserName(fromUser);
		tm.setMsgType(MSGTYPE_TEXT);
		tm.setCreateTime(new Date().getTime());
		tm.setContent(content);
		return MessageUtil.textMessageToXml(tm);
	}
	public static String textMessageToXml(TextMessage text){
		XStream stream = new XStream();
		stream.alias("xml", text.getClass());
		return stream.toXML(text);
	}
	
	public static String mainMenu(){
		StringBuffer sb = new StringBuffer();
		sb.append("��ӭ��ע�ҵĸ���΢�Ź��ںţ�����������Ը������Ի��߸����������\n")
		.append("1����ȡ�ҵĸ�����Ϣ\n\n")
		.append("2����ȡ�ҵ�shadowsocks�˺�����");
		return sb.toString();
	}
	
	public static String keyWord(String word){
		
		StringBuffer sb = new StringBuffer();
		if("1".equals(word)){
			sb.append("QQ: 843571091\n")
			.append("΢��: twx843571091\n")
			.append("�ֻ�����: 18051292045\n")
			.append("email: twx843571091@gmail.com");
		}else if("2".equals(word)){
			sb.append("������:108.61.183.107\n")
			.append("���룺nexusqaz\n")
			.append("�˿ںţ�4000\n")
			.append("���ܷ�ʽ��aes-256-cfb");
		}else{
			sb.append("ָ����ЧŶ(���`)");
		}
		return sb.toString();
	}
}
