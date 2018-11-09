package com.whty.ebp.manage.service;

import java.util.List;
import java.util.Map;

import com.whty.ebp.manage.model.Message;
import com.whty.page.util.HandlerResult;

public interface MessageService {
	/**
	 * 查询消息列表
	 * @param paraMap
	 * @return
	 */
	public HandlerResult queryMessage(Map paraMap);
	
	/**
	 * 根据消息ID查询消息详情
	 * @param id
	 * @return
	 */
	public Message queryMessageDetail(String id);
	
	/**
	 * 发布消息
	 * @param message
	 */
	public void addMessage(Message message);
	
	/**
	 * 更新消息状态
	 * @param paraMap
	 */
	public void updateMessageStatus(Map paraMap);
	
	/**
	 * 查询后台发送通知页面的消息类型
	 * @return
	 */
	public List<Map> queryMessageType();
	
	/**
	 * 接口查询列表
	 * @param paraMap
	 * @return
	 */
	public HandlerResult queryMessageForApi(Map paraMap);
	
	/**
	 * 接口操作消息
	 * @param paramap
	 */
	public void optMessage(Map paramap);
}
