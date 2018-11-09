package com.whty.ebp.manage.dao;

import java.util.List;
import java.util.Map;

import com.whty.ebp.base.dao.IBaseDao;
import com.whty.ebp.manage.model.Message;


public interface MessageDao extends IBaseDao<Message>{
	
	/**
	 * 查询消息列表
	 * @param paraMap
	 * @return
	 */
	public List<Message> queryMessage(Map paraMap);
	
	/**
	 * 查询消息列表提供给接口
	 * 查询针对产品发送的消息
	 * @param paraMap
	 * @return
	 */
	public List<Message> queryMessageForApi1(Map paraMap);
	
	/**
	 * 查询消息列表提供给接口
	 * 查询针对某一产品的消息
	 * @param paraMap
	 * @return
	 */
	public List<Message> queryMessageForApi2(Map paraMap);
	
	/**
	 * 查询消息列表提供给接口
	 * 查询针对某个用户的消息
	 * @param paraMap
	 * @return
	 */
	public List<Message> queryMessageForApi3(Map paraMap);
	
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
	 * 标记为已读
	 * @param paramap
	 */
	public void markMessage(Map paramap);
	
	/**
	 * 标记为删除
	 * @param paramap
	 */
	public void markMessageDelete(Map paramap);
	
	/**
	 * 批量标记为已读
	 * @param paramap
	 */
	public void batchMarkMessageRead(Map paramap);
}
