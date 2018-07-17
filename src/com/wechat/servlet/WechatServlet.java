package com.wechat.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;

import com.wechat.po.TextMessage;
import com.wechat.util.CheckUtils;
import com.wechat.util.MessageUtils;

public class WechatServlet extends HttpServlet {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public WechatServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * 验证token
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String signature = request.getParameter("signature");   //微信加密签名
		String timestamp = request.getParameter("timestamp");   //时间戳
		String nonce = request.getParameter("nonce");           //随机数
		String echostr = request.getParameter("echostr");       //随机字符串
	
		PrintWriter out = response.getWriter();
		
		if(CheckUtils.checkSignature(signature, timestamp, nonce)){  //验证方法
			
			out.print(echostr);
			
		}
		
	}

	/**
	 * 处理用户消息
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		try {
			
			//将接收到的xml格式的消息转化为map集合
			Map<String, String> map = MessageUtils.xmlToMap(request);
			//提取消息中的信息
			String toUserName = map.get("ToUserName");    
			String fromUserName = map.get("FromUserName");
			String msgType = map.get("MsgType");
			String content = map.get("Content");
			
			String message = null;
			//回复用户消息
			if("text".equals(msgType)){     //判断消息类型
				TextMessage textMessage = new TextMessage();
				textMessage.setFromUserName(toUserName);
				textMessage.setToUserName(fromUserName);
				textMessage.setCreateTime(new Date().getTime());
				textMessage.setMsgType("text");
				textMessage.setContent("您发送的消息是" + content);
				message = MessageUtils.textToXml(textMessage);
				
			}
			out.print(message);
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			
			out.close();
		
		}
		
	}

	public void init() throws ServletException {
		// Put your code here
	}

}
