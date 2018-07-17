package com.wechat.po;

/**
 * 文本消息
 * @author Dick
 *
 */
public class TextMessage {
	
	private String ToUserName;    //消息接收者
	private String FromUserName;  //消息发送者
	private long CreateTime;      //消息发送时间
	private String MsgType;       //消息类型
	private String Content;       //消息内容
	private String MsgId;         //消息ID
	
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public long getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public String getMsgId() {
		return MsgId;
	}
	public void setMsgId(String msgId) {
		MsgId = msgId;
	}


}
