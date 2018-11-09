package com.yhcrt.healthcloud.cms.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhcrt.healthcloud.cms.entity.CmsContent;
import com.yhcrt.healthcloud.cms.entity.CmsContentExample;
import com.yhcrt.healthcloud.cms.mapper.CmsContentMapper;
import com.yhcrt.healthcloud.cms.service.CmsContentService;
import com.yhcrt.healthcloud.common.Constants;
import com.yhcrt.healthcloud.system.service.SysSequenceService;
import com.yhcrt.healthcloud.util.DateUtil;

@Service
public class CmsContentServiceImpl implements CmsContentService {

	@Autowired
	private CmsContentMapper contentMapper;
	@Autowired
	private SysSequenceService sysSequenceService;

	@Override
	public int insert(CmsContent content) {
		content.setContentId(sysSequenceService.getSequenceValue(Constants.SequenceName.SYS_RES));
		content.setReleaseTime(DateUtil.getDateTime());
		content.setStatus(Constants.Content.STATUS_PASS);
		return contentMapper.insert(content);
	}

	@Override
	public int delete(Integer contentId) {
		return contentMapper.deleteByPrimaryKey(contentId);
	}

	@Override
	public int update(CmsContent content) {
		return contentMapper.updateByPrimaryKeySelective(content);
	}

	@Override
	public CmsContent getContentById(Integer contentId) {
		return contentMapper.selectByPrimaryKey(contentId);
	}

	@Override
	public List<CmsContent> listContentsByChannelId(String channelId) {
		CmsContentExample example = new CmsContentExample();
		example.createCriteria().andChannelIdEqualTo(Integer.parseInt(channelId))
				.andStatusNotEqualTo(Constants.Content.STATUS_ARCHIVED);
		return contentMapper.selectByExample(example);
	}
	
	@Override
	public List<CmsContent> getArchivedContents(Integer channelId) {
		CmsContentExample example = new CmsContentExample();
		example.createCriteria().andChannelIdEqualTo(channelId)
				.andStatusEqualTo(Constants.Content.STATUS_ARCHIVED);
		return contentMapper.selectByExample(example);
	}

	@Override
	public int batchArchive(String[] contentIds) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("contentIds", contentIds);
		return contentMapper.batchArchive(params);
	}
	
	@Override
	public int batchCancleArchive(String[] contentIds) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("contentIds", contentIds);
		return contentMapper.batchCancleArchive(params);
	}
	
	@Override
	public int batchDelete(String[] contentIds) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("contentIds", contentIds);
		return contentMapper.batchDelete(params);
	}

}
