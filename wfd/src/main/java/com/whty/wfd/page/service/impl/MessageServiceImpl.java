package com.whty.wfd.page.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whty.wfd.common.utils.SendMessageUtils;
import com.whty.wfd.common.utils.SysConfigUtils;
import com.whty.wfd.common.utils.TimeUtils;
import com.whty.wfd.page.dao.*;
import com.whty.wfd.page.model.*;
import com.whty.wfd.page.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * \* User: zjd \* Date: 2018/8/16 \* Time: 15:44 \* Description: \
 */
@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private TPostMessageMapper tPostMessageMapper;
	@Autowired
	private TPostMessageImgMapper tPostMessageImgMapper;
	@Autowired
	private TUserMapper tUserMapper;
	@Autowired
	private TPostMessageAltMapper tPostMessageAltMapper;
	@Autowired
	private TPostMessageAudioMapper tPostMessageAudioMapper;

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public void addMessage(final TUser tUser, TPostMessage tPostMessage, List<TPostMessageImg> tPostMessageImgs,
			Integer[] userIds, List<TPostMessageAudio> tPostMessageAudios) {

		try {
			final List<String> sendeeIds = new ArrayList<>();
			String threadContent = null;
			if (userIds != null && userIds.length > 0) {
				String reg = "";
				String content = tPostMessage.getContent();
				threadContent = tPostMessage.getContent();
				for (Integer userId : userIds) {
					TUser enUser = tUserMapper.selectByPrimaryKey(userId);
					reg = "@" + enUser.getName();
					if (content.contains(reg)) {
						content = content.replace(reg, "<span style=\"color:#01c7f9\">" + reg + "</span>");
						TPostMessageAlt tPostMessageAlt = new TPostMessageAlt();
						tPostMessageAlt.setIsRead(false);
						tPostMessageAlt.setAltUserId(userId);
						tPostMessageAlt.setMessageId(tPostMessage.getId());
						tPostMessageAlt.setCreateTime(new Date());
						tPostMessageAlt.setUpdateTime(new Date());
						tPostMessageAltMapper.insert(tPostMessageAlt);
						sendeeIds.add(enUser.getPersonId());
					}
				}
				tPostMessage.setContent(content);
			}
			tPostMessageMapper.insert(tPostMessage);
			if (tPostMessageImgs.size() > 0) {
				tPostMessageImgMapper.insertManyImg(tPostMessageImgs);
			}

			if (tPostMessageAudios.size() > 0) {
				tPostMessageAudioMapper.insertManyAudio(tPostMessageAudios);
			}

			final String wfdUrl = SysConfigUtils.getStrValue("domain") + "/getPostDetail?postId="
					+ tPostMessage.getPlatePostId() + "&plateId=" + tPostMessage.getPlatePostId();

			final TUser threaBean = tUser;
			final String messageContent = threadContent;

			// 回复
			TUser platePostUser = tUserMapper.selectByPlatePostId(tPostMessage.getPlatePostId());

			final List<String> hueifu = new ArrayList<String>();
			hueifu.add(platePostUser.getPersonId());
			new Thread(new Runnable() {
				@Override
				public void run() {
					SendMessageUtils.sendOaMessage(tUser, hueifu, wfdUrl, messageContent, tUser.getName() + "回复了你的帖子",
							TimeUtils.date2String(new Date(), TimeUtils.DAY_FORMAT_1));
				}
			}).start();

			// alt人
			new Thread(new Runnable() {
				@Override
				public void run() {
					SendMessageUtils.sendOaMessage(threaBean, sendeeIds, wfdUrl, messageContent,
							tUser.getName() + "回复帖子提到了你", TimeUtils.date2String(new Date(), TimeUtils.DAY_FORMAT_1));
				}
			}).start();
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
	}

	@Override
	public void addMessageAudio(final TUser tUser, TPostMessage tPostMessage, TPostMessageAudio tPostMessageAudio) {
		try {
			tPostMessageMapper.insert(tPostMessage);
			tPostMessageAudioMapper.insert(tPostMessageAudio);
			final String wfdUrl = SysConfigUtils.getStrValue("domain") + "/getPostDetail?postId="
					+ tPostMessage.getPlatePostId() + "&plateId=" + tPostMessage.getPlatePostId();

			final TUser threaBean = tUser;
			final String messageContent = "【语音回复】";

			// 回复
			TUser platePostUser = tUserMapper.selectByPlatePostId(tPostMessage.getPlatePostId());

			final List<String> hueifu = new ArrayList<String>();
			hueifu.add(platePostUser.getPersonId());
			new Thread(new Runnable() {
				@Override
				public void run() {
					SendMessageUtils.sendOaMessage(tUser, hueifu, wfdUrl, messageContent, tUser.getName() + "回复了你的帖子",
							TimeUtils.date2String(new Date(), TimeUtils.DAY_FORMAT_1));
				}
			}).start();
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
	}

	@Override
	public List<TPostMessage> getPostMessagesByPostId(String plateId, Integer userId, String orderByClause) {
		return tPostMessageMapper.getPostMessagesByPostId(plateId, userId, orderByClause);
	}

	@Override
	public PageInfo<TPostMessage> getPostMessagesByUserId(Integer userId, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<TPostMessage> tPostMessages = tPostMessageMapper.getPostMessagesByUserId(userId);
		PageInfo<TPostMessage> pageInfo = new PageInfo<>(tPostMessages);
		return pageInfo;
	}

	@Override
	public int updateByMessageId(String messageId) {
		return tPostMessageMapper.updateByMessageId(messageId);
	}

	@Override
	public int updateByMessageIdAnswer(String messageId, boolean type) {
		TPostMessage tPostMessage = tPostMessageMapper.selectByPrimaryKey(messageId);
		tPostMessage.setIsTop(!type);
		return tPostMessageMapper.updateByPrimaryKey(tPostMessage);
	}

	@Override
	public int updateByMessageIdIsRead(String messageId, boolean isRead) {
		TPostMessage tPostMessage = new TPostMessage();
		tPostMessage.setId(messageId);
		tPostMessage.setIsRead(!isRead);
		return tPostMessageMapper.updateByPrimaryKeySelective(tPostMessage);
	}

	@Override
	public void updateMessageIsRead(Integer userId) {
		tPostMessageMapper.updateMessageIsRead(userId);
	}

	@Override
	public List<TPostMessage> getReply(Integer userId) {
		return tPostMessageMapper.getReply(userId);
	}

	@Override
	public int updateIsRead(Integer userId) {
		return tPostMessageAltMapper.updateByUserdId(userId);
	}
}