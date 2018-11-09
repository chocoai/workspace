/**
 * 
 */
package com.whty.assis.basicdata.model;

import java.util.Date;

import com.whty.assis.base.model.BaseModel;

/**
 * @author zhangzheng
 * @date 2018年5月29日
 */
public class IotMessagesLog extends BaseModel {


	private static final long serialVersionUID = 8826219462148620440L;

	private String topic;
	private String body;
	private String tag;
	private String key;
	private String msgId;
	private Date bornTimestamp;
	private String bornHost;

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public Date getBornTimestamp() {
		return bornTimestamp;
	}

	public void setBornTimestamp(Date bornTimestamp) {
		this.bornTimestamp = bornTimestamp;
	}

	public String getBornHost() {
		return bornHost;
	}

	public void setBornHost(String bornHost) {
		this.bornHost = bornHost;
	}

}
