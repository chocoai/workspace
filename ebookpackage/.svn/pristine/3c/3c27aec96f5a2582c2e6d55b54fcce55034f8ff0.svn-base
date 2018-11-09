package com.whty.ebp.manage.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.ebp.manage.dao.MessageDao;
import com.whty.ebp.manage.model.Message;
import com.whty.ebp.manage.service.MessageService;
import com.whty.page.util.HandlerResult;
@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageDao messageDao;
	
	public HandlerResult queryMessage(Map paraMap) {
		HandlerResult rs = new HandlerResult();
		rs.setResultList(messageDao.queryMessage(paraMap));
		return rs;
	}

	public Message queryMessageDetail(String id) {
		return messageDao.queryMessageDetail(id);
	}

	public void addMessage(Message message) {
		messageDao.addMessage(message);
	}

	public void updateMessageStatus(Map paraMap) {
		messageDao.updateMessageStatus(paraMap);
	}
	
	public List<Map> queryMessageType(){
		return messageDao.queryMessageType();
	}

	/**
	 * 接口查询消息列表
	 * 1.当用户ID和产品ID都有时，查询用户所有的消息
	 * 2.当有产品ID没有用户ID时，按照产品ID查询可以查到的消息
	 * 3.当用户ID和产品ID都没有时，只能查询针对产品发送的消息
	 */
	public HandlerResult queryMessageForApi(Map paraMap) {
		HandlerResult rs = new HandlerResult();
		List<Message> list = new ArrayList<Message>();
		if(paraMap.get("productId")!=null && paraMap.get("userId")!=null) {
			list = messageDao.queryMessageForApi3(paraMap);
		} else if(paraMap.get("productId")!=null) {
			list = messageDao.queryMessageForApi2(paraMap);
		} else {
			list = messageDao.queryMessageForApi1(paraMap);
		}
				
		
		rs.setResultList(list);
		return rs;
	}

	public void optMessage(Map paramap) {
		/*
		 * 操作类型：
		 * 1—标记为已读，2—删除，3—全部标记为已读
		 */
		switch ((Integer)paramap.get("optType")) {
			case 1:
				this.markMessageRead(paramap);
				break;
			case 2:
				this.markMessageDelete(paramap);
				break;
			case 3:
				this.batchMarkMessageRead(paramap);
				break;
		}
	}
	
	/**
	 * 标记为已读：
	 *  已经标记为已读的数据，不再做标记操作
	 */
	private void markMessageRead(Map paramap){
		Map map = new HashMap();
		map.put("userId", paramap.get("userId"));
		map.put("productId", paramap.get("productId"));
		map.put("status", "0");
		map.put("idList", Arrays.asList(paramap.get("id").toString().split(",")));
		messageDao.markMessage(map);
	}
	
	/**
	 * 标记为删除：
	 * 	1.删除已读消息
	 *  2.删除未读消息
	 */
	private void markMessageDelete(Map paramap){
		Map map = new HashMap();
		map.put("userId", paramap.get("userId"));
		map.put("productId", paramap.get("productId"));
		map.put("status", "2");
		map.put("idList", Arrays.asList(paramap.get("id").toString().split(",")));
		//删除已读消息
		messageDao.markMessage(map);
		//删除未读消息
		messageDao.markMessageDelete(map);
	}
	
	/**
	 * 批量标记为已读：
	 *  已经标记为已读的数据，不再做标记操作
	 */
	private void batchMarkMessageRead(Map paramap){
		Map map = new HashMap();
		map.put("userId", paramap.get("userId"));
		map.put("productId", paramap.get("productId"));
		messageDao.batchMarkMessageRead(map);
	}

}
