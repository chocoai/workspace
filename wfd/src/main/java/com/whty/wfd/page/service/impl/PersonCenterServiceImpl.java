package com.whty.wfd.page.service.impl;

import com.whty.wfd.page.dao.MessageMapper;
import com.whty.wfd.page.service.PersonCenterService;
import com.whty.wfd.page.vo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * \* User: zjd \* Date: 2018/8/18 \* Time: 9:54 \* Description: \
 */
@Service
public class PersonCenterServiceImpl implements PersonCenterService {

	@Autowired
	private MessageMapper messageMapper;

	@Override
	public Message getMessage(Integer userId) {
		Message message = messageMapper.getMessage(userId);
		Message message2 = messageMapper.getNoReadMessage(userId);
		message.setTotal(message2.getCommentTotal() + message2.getPostLikeTotal() + message2.getCommentLikeTotal()
				+ message2.getReplyTotal());
		message.setCommentTotal(message2.getCommentTotal());
		message.setPostLikeTotal(message2.getPostLikeTotal());
		message.setCommentLikeTotal(message2.getCommentLikeTotal());
		message.setReplyTotal(message2.getReplyTotal());
		return message;
	}
}