package com.wechat.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import com.thoughtworks.xstream.XStream;
import com.wechat.po.TextMessage;

public class MessageUtils {
	
	/**
	 * xml
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static Map<String, String> xmlToMap(HttpServletRequest request) throws IOException, DocumentException {
		
		Map<String, String> map = new HashMap<String, String>();
		SAXReader reader = new SAXReader();
		
		//从request中获取输入流
		InputStream ins = request.getInputStream();
		Document document = reader.read(ins);
		
		//获取xml中根元素
		Element root = document.getRootElement();
		
		@SuppressWarnings("unchecked")
		List<Element> list = root.elements();
		
		for(Element e : list){
			
			map.put(e.getName(), e.getText());
		
		}
		ins.close();
		
		return map;
		
	}
	/**
	 * 文本类型转xml
	 * @param textMessage
	 * @return
	 */
	public static String textToXml(TextMessage textMessage) {
		
		XStream xStream = new XStream();
		
		xStream.alias("xml", textMessage.getClass());
		
		return xStream.toXML(textMessage);
		
	}

}
