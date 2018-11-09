package com.yhcrt.iHealthCloud.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yhcrt.iHealthCloud.base.BaseService;
import com.yhcrt.iHealthCloud.common.Constants;
import com.yhcrt.iHealthCloud.entity.CmsChannel;
import com.yhcrt.iHealthCloud.entity.CmsContent;
import com.yhcrt.iHealthCloud.entity.CmsContentExample;
import com.yhcrt.iHealthCloud.entity.Goods;
import com.yhcrt.iHealthCloud.entity.GoodsExample;
import com.yhcrt.iHealthCloud.entity.Member;
import com.yhcrt.iHealthCloud.entity.UserComment;
import com.yhcrt.iHealthCloud.entity.UserCommentExample;
import com.yhcrt.iHealthCloud.mapper.CmsChannelMapper;
import com.yhcrt.iHealthCloud.mapper.CmsContentMapper;
import com.yhcrt.iHealthCloud.mapper.GoodsMapper;
import com.yhcrt.iHealthCloud.mapper.MemberMapper;
import com.yhcrt.iHealthCloud.mapper.UserCommentMapper;
import com.yhcrt.iHealthCloud.service.CmsContentService;
import com.yhcrt.iHealthCloud.service.SysSequenceService;
import com.yhcrt.iHealthCloud.util.Const;
import com.yhcrt.iHealthCloud.util.DateUtil;


@Service
public class CmsContentServiceImpl extends BaseService implements CmsContentService {

	@Autowired
	private CmsContentMapper contentMapper;
	@Autowired
	private CmsChannelMapper channelMapper;
	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private GoodsMapper goodsMapper;
	@Autowired
	private SysSequenceService sysSequenceService;
	
    
    @Autowired	
    private UserCommentMapper userCommentMapper;

	@Override
	public int insert(CmsContent content) {
		content.setContentId(sysSequenceService.getSequenceValue("sys_res"));
		content.setReleaseTime(DateUtil.getDateTime());
		content.setStatus(1);
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
				.andStatusNotEqualTo(2);
		return contentMapper.selectByExample(example);
	}

	@Override
	public int batchArchive(String[] contentIds) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("contentIds", contentIds);
		return contentMapper.batchArchive(params);
	}
	
	
	@Override
	public String getRecommendCms(JSONObject pdataObj) {
		// 向数据库请求数据
		CmsContentExample example = new CmsContentExample();
		example.createCriteria().andIsRecommendEqualTo(1).andStatusGreaterThanOrEqualTo(1); //推荐 并且审核通过
		List<CmsContent> cmsContentList = contentMapper.selectByExample(example); 
		JSONArray jsonarray = new JSONArray();
		for(CmsContent cmsContent : cmsContentList){
			JSONObject json = new JSONObject();
			json.put("contentId", cmsContent.getContentId());
			json.put("titleImg", cmsContent.getTitleImg());
			jsonarray.add(json);
		}
		pdataObj.put(Const.TAG_BIZ, jsonarray);
		pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
		pdataObj.put(Const.TAG_RMK, "获取成功！");
		return pdataObj.toJSONString();
	}
	
	@Override
	public String getCmsContentList(JSONObject pdataObj) {
		// 获取参数
		JSONObject biz = pdataObj.getJSONObject("biz");
		String channelid = biz.getString("channel_id");
		String currentPage = biz.getString(Const.TAG_CURRENT_PAGE);
		String pageSize = biz.getString(Const.TAG_PAGE_SIZE);
		// 对参数进行非空判断
		if (judgeAgumentsIsLegal(pdataObj, currentPage,pageSize)) {
			// 向数据库请求数据并判断是否分页
			List<CmsContent> list;
			if (judgePageInfoIsLegal(currentPage, pageSize)) {
				PageHelper.startPage(Integer.parseInt(currentPage), Integer.parseInt(pageSize));
				list = getCmsContentList(channelid);
				PageInfo<CmsContent> pageInfo = new PageInfo<>(list);
				setPagingData(pdataObj, pageInfo.getPages(), pageInfo.getPageNum());
			} else {
				list = getCmsContentList(channelid);
			}
			JSONArray cmsContentJsonList = new JSONArray();
			for(CmsContent cmsContent : list){
				if(cmsContent.getTitle()!=null && cmsContent.getTitle().length()>13){
					cmsContent.setTitle(cmsContent.getTitle().substring(0,13)+"...");
				}
				if(cmsContent.getDescription()!=null && cmsContent.getDescription().length()>45){
					cmsContent.setDescription(cmsContent.getDescription().substring(0,45)+"...");
				}
				if(cmsContent.getKeyword()!=null && cmsContent.getKeyword().length()>4){
					cmsContent.setKeyword(cmsContent.getKeyword().substring(0,4)+"...");
				}
				JSONObject cmsContentJson = (JSONObject) JSON.toJSON(cmsContent);
				CmsChannel channel = channelMapper.selectByPrimaryKey(cmsContent.getChannelId());
				cmsContentJson.put("channelName", channel.getChannelName());
				cmsContentJsonList.add(cmsContentJson);
			}
			pdataObj.put(Const.TAG_BIZ, cmsContentJsonList);
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			pdataObj.put(Const.TAG_RMK, "获取成功！");
		} else {
			requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
		}
		return pdataObj.toJSONString();
	}
	
	@Override
	public String getImages(JSONObject pdataObj) {
		JSONArray imagejsonarray = new JSONArray();
		CmsContentExample cmsexample = new CmsContentExample();
		cmsexample.createCriteria().andStatusEqualTo(Constants.STATUS_NORMAL).andIsSmallproEqualTo(1);
		List<CmsContent> contentList = contentMapper.selectByExample(cmsexample);
		for(CmsContent cmsContent : contentList){
			JSONObject cmsjson = new JSONObject();
			cmsjson.put("imageId", cmsContent.getContentId());
			cmsjson.put("imageUrl", cmsContent.getTitleImg());
			cmsjson.put("topType", "cms");
			imagejsonarray.add(cmsjson);
		}
		GoodsExample goodsexample = new GoodsExample();
		goodsexample.createCriteria().andStatusEqualTo(Constants.STATUS_NORMAL).andIext1EqualTo(1);
		List<Goods> goodsList = goodsMapper.selectByExample(goodsexample);
		for(Goods goods : goodsList){
			JSONObject goodsjson = new JSONObject();
			goodsjson.put("imageId", goods.getGoodsId());
			goodsjson.put("imageUrl", goods.getTitleImg());
			goodsjson.put("topType", "goods");
			imagejsonarray.add(goodsjson);
		}
		pdataObj.put(Const.TAG_BIZ, imagejsonarray);
		pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
		pdataObj.put(Const.TAG_RMK, "获取成功！");
		
		return pdataObj.toJSONString();
	}
	
	@Override
	public String getCmsContentDetail(JSONObject pdataObj) {
		// 获取参数
		JSONObject biz = pdataObj.getJSONObject("biz");
		String contentid = biz.getString("content_id");
		// 对参数进行非空判断
		if (judgeAgumentsIsLegal(pdataObj, contentid)) {
			// 向数据库请求数据
			CmsContent content = contentMapper.selectByPrimaryKey(Integer.valueOf(contentid));
			JSONObject contentjson = (JSONObject) JSON.toJSON(content);
			Integer channelId = content.getChannelId();
			CmsChannel channel = channelMapper.selectByPrimaryKey(channelId);
			contentjson.put("channelName", channel.getChannelName());
			CmsContentExample example = new CmsContentExample();
			example.createCriteria().andChannelIdEqualTo(Integer.valueOf(channelId)).andContentIdNotEqualTo(content.getContentId());
			List<CmsContent> contentList = contentMapper.selectByExample(example);
			contentjson.put(" suggestContents", contentList);
			UserCommentExample commentExample = new UserCommentExample();
			commentExample.createCriteria().andRefIdEqualTo(content.getContentId()).andCommentTypeEqualTo("cms");
			List<UserComment> commentListAll = userCommentMapper.selectByExample(commentExample);
			JSONArray commentJsonList = new JSONArray();
			for(UserComment userComment : commentListAll){
				JSONObject userCommentJson = (JSONObject) JSON.toJSON(userComment);
				Member member = memberMapper.selectByPrimaryKey(Integer.valueOf(userComment.getMemberId()));
				userCommentJson.put("userLogo", member.getHeadPic());
				userCommentJson.put("nickName", member.getNickName());
				if(commentJsonList.size()<2){
					commentJsonList.add(userCommentJson);
				}
			}
			contentjson.put("comments", commentJsonList);
			contentjson.put("moreComments", commentListAll.size());
			pdataObj.put(Const.TAG_BIZ, contentjson);
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			pdataObj.put(Const.TAG_RMK, "获取成功！");
		} else {
			requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
		}
		return pdataObj.toJSONString();
	}
	
	private List<CmsContent> getCmsContentList(String channelid) {
		CmsContentExample example = new CmsContentExample();
		if(!StringUtils.isEmpty(channelid)){
			example.createCriteria().andChannelIdEqualTo(Integer.valueOf(channelid)).andStatusEqualTo(1);
		}
		List<CmsContent> empList = contentMapper.selectByExample(example);
		return empList;
	}
	

}
