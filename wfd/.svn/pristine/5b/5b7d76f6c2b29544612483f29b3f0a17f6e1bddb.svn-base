package com.whty.wfd.page.service.impl;

import com.whty.wfd.page.dao.TPostMessageReplyMapper;
import com.whty.wfd.page.model.TPostMessageReply;
import com.whty.wfd.page.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * \* User: zjd \* Date: 2018/8/17 \* Time: 10:06 \* Description: \
 */
@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private TPostMessageReplyMapper tPostMessageReplyMapper;

	@Override
	public List<TPostMessageReply> getReplys(Integer userId) {
		return tPostMessageReplyMapper.getMessageReplys(userId);
	}
}