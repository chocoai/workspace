package com.yhcrt.healthcloud.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhcrt.healthcloud.cms.entity.CmsChannel;
import com.yhcrt.healthcloud.cms.entity.CmsChannelExample;
import com.yhcrt.healthcloud.cms.mapper.CmsChannelMapper;
import com.yhcrt.healthcloud.cms.service.CmsChannelService;
import com.yhcrt.healthcloud.common.Constants;

@Service
public class CmsChannelServiceImpl implements CmsChannelService {
	
	@Autowired
	private CmsChannelMapper channelMapper;

	@Override
	public int insert(CmsChannel record) {
		return channelMapper.insert(record);
	}

	@Override
	public int delete(Integer channelId) {
		return channelMapper.deleteByPrimaryKey(channelId);
	}

	@Override
	public int update(CmsChannel channel) {
		return channelMapper.updateByPrimaryKey(channel);
	}

	@Override
	public CmsChannel getRootChannel() {
		return channelMapper.getRootChannel();
	}

	@Override
	public List<CmsChannel> getChildChannelByParentId(Integer parentId) {
		return channelMapper.getChildChannelByParentId(parentId);
	}

	@Override
	public CmsChannel getChannelByChannelId(Integer channelId) {
		return channelMapper.selectByPrimaryKey(channelId);
	}

	@Override
	public List<CmsChannel> getAllChannel() {
		CmsChannelExample example = new CmsChannelExample();
		example.createCriteria().andStatusNotEqualTo(Constants.STATUS_DISABLE);
		List<CmsChannel> channels = channelMapper.selectByExample(example);
		return channels;
	}

}
