package com.yhcrt.iHealthCloud.service.impl;

import java.util.List;











import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.yhcrt.iHealthCloud.common.Constants;
import com.yhcrt.iHealthCloud.entity.CmsChannel;
import com.yhcrt.iHealthCloud.entity.CmsChannelExample;
import com.yhcrt.iHealthCloud.mapper.CmsChannelMapper;
import com.yhcrt.iHealthCloud.service.CmsChannelService;
import com.yhcrt.iHealthCloud.util.Const;


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
	
	@Override
	public String getCmsChannel(JSONObject pdata){
		CmsChannelExample example = new CmsChannelExample();
		example.createCriteria().andStatusNotEqualTo(Constants.STATUS_DISABLE).andParentIdIsNotNull();
		List<CmsChannel> channels = channelMapper.selectByExample(example);
		pdata.put(Const.TAG_BIZ, channels);
		pdata.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
		pdata.put(Const.TAG_RMK, "获取成功！");
		return pdata.toJSONString();
	}

}
