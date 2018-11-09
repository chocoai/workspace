package com.yhcrt.iHealthCloud.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yhcrt.iHealthCloud.base.BaseService;
import com.yhcrt.iHealthCloud.common.Constants;
import com.yhcrt.iHealthCloud.entity.Member;
import com.yhcrt.iHealthCloud.entity.ServiceLog;
import com.yhcrt.iHealthCloud.entity.ServiceLogExample;
import com.yhcrt.iHealthCloud.entity.UserComment;
import com.yhcrt.iHealthCloud.entity.UserCommentExample;
import com.yhcrt.iHealthCloud.mapper.MemberMapper;
import com.yhcrt.iHealthCloud.mapper.ServiceLogMapper;
import com.yhcrt.iHealthCloud.mapper.UserCommentMapper;
import com.yhcrt.iHealthCloud.service.SysSequenceService;
import com.yhcrt.iHealthCloud.service.UserCommentService;
import com.yhcrt.iHealthCloud.service.WorkOrderService;
import com.yhcrt.iHealthCloud.service.YwOrderService;
import com.yhcrt.iHealthCloud.util.Const;
import com.yhcrt.iHealthCloud.util.EmojiUtil;
import com.yhcrt.iHealthCloud.util.StringUtil;
	
/* @Description: 	
 * @version 1.0     2017年9月7日	
 * @author jimmy	
*/	
@Service	
public class UserCommentServiceImpl  extends BaseService implements UserCommentService{	
	
    @Autowired	
    private UserCommentMapper userCommentMapper;	
    @Autowired	
    private MemberMapper memberMapper;
    @Autowired	
    private SysSequenceService sysSequenceService;
    @Autowired	
    private WorkOrderService workOrderService;
    @Autowired	
    private YwOrderService ywOrderService;
    @Autowired	
    private ServiceLogMapper serviceLogMapper;
    	
    /* (non-Javadoc)	
     * @see com.yhcrt.healthcloud.mall.service.UserCommentService#add(com.yhcrt.healthcloud.mall.entity.UserComment)	
     */	
    @Override	
    public Integer add(UserComment userComment) {	
        return userCommentMapper.insert(userComment);	
    }	
	
    /* (non-Javadoc)	
     * @see com.yhcrt.healthcloud.mall.service.UserCommentService#del(java.lang.String)	
     */	
    @Override	
    public Integer del(Integer cid) {	
        return userCommentMapper.deleteByPrimaryKey(cid);	
    }	
	
    /* (non-Javadoc)	
     * @see com.yhcrt.healthcloud.mall.service.UserCommentService#upd(com.yhcrt.healthcloud.mall.entity.UserComment)	
     */	
    @Override	
    public Integer upd(UserComment userComment) {	
        return userCommentMapper.updateByPrimaryKey(userComment);	
    }	
	
    /* (non-Javadoc)	
     * @see com.yhcrt.healthcloud.mall.service.UserCommentService#get(java.lang.String)	
     */	
    @Override	
    public UserComment get(Integer cid) {	
        return userCommentMapper.selectByPrimaryKey(cid);	
    }	
	
    /* (non-Javadoc)	
     * @see com.yhcrt.healthcloud.mall.service.UserCommentService#getAll()	
     */	
    @Override	
    public List<UserComment> getAll() {	
        return userCommentMapper.selectByExample(null);	
    }	
    
	@Override
	public String getMoreGoodsComment(JSONObject pdataObj) {
		// 获取参数
		JSONObject biz = pdataObj.getJSONObject("biz");
		String goodsid = biz.getString("goods_id");
		String currentPage = biz.getString(Const.TAG_CURRENT_PAGE);
		String pageSize = biz.getString(Const.TAG_PAGE_SIZE);
		// 对参数进行非空判断
		if (judgeAgumentsIsLegal(pdataObj, goodsid,currentPage,pageSize)) {
			// 向数据库请求数据并判断是否分页
			List<UserComment> list;
			if (judgePageInfoIsLegal(currentPage, pageSize)) {
				PageHelper.startPage(Integer.parseInt(currentPage), Integer.parseInt(pageSize));
				list = selectCommentListByGoodsId(goodsid);
				PageInfo<UserComment> pageInfo = new PageInfo<>(list);
				setPagingData(pdataObj, pageInfo.getPages(), pageInfo.getPageNum());
			} else {
				list = selectCommentListByGoodsId(goodsid);
			}
			JSONArray commentJsonList = new JSONArray();
			for(UserComment userComment : list){
				JSONObject userCommentJson = (JSONObject) JSON.toJSON(userComment);
				Member member = memberMapper.selectByPrimaryKey(Integer.valueOf(userComment.getMemberId()));
				userCommentJson.put("userLogo", member.getHeadPic());
				userCommentJson.put("nickName", member.getNickName());
				commentJsonList.add(userCommentJson);
			}
			pdataObj.put(Const.TAG_BIZ, commentJsonList);
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			pdataObj.put(Const.TAG_RMK, "获取成功！");
		} else {
			requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
		}
		return pdataObj.toJSONString();
	}
	
	@Override
	public String getMoreOrgComment(JSONObject pdataObj) {
		// 获取参数
		JSONObject biz = pdataObj.getJSONObject("biz");
		String orgid = biz.getString("org_id");
		String currentPage = biz.getString(Const.TAG_CURRENT_PAGE);
		String pageSize = biz.getString(Const.TAG_PAGE_SIZE);
		// 对参数进行非空判断
		if (judgeAgumentsIsLegal(pdataObj, orgid,currentPage,pageSize)) {
			// 向数据库请求数据并判断是否分页
			List<UserComment> list;
			if (judgePageInfoIsLegal(currentPage, pageSize)) {
				PageHelper.startPage(Integer.parseInt(currentPage), Integer.parseInt(pageSize));
				list = selectCommentListByOrgId(orgid);
				PageInfo<UserComment> pageInfo = new PageInfo<>(list);
				setPagingData(pdataObj, pageInfo.getPages(), pageInfo.getPageNum());
			} else {
				list = selectCommentListByOrgId(orgid);
			}
			JSONArray commentJsonList = new JSONArray();
			for(UserComment userComment : list){
				JSONObject userCommentJson = (JSONObject) JSON.toJSON(userComment);
				Member member = memberMapper.selectByPrimaryKey(Integer.valueOf(userComment.getMemberId()));
				userCommentJson.put("userLogo", member.getHeadPic());
				userCommentJson.put("nickName", member.getNickName());
				commentJsonList.add(userCommentJson);
			}
			pdataObj.put(Const.TAG_BIZ, commentJsonList);
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			pdataObj.put(Const.TAG_RMK, "获取成功！");
		} else {
			requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
		}
		return pdataObj.toJSONString();
	}
	
	@Override
	public String getMoreContentComment(JSONObject pdataObj) {
		// 获取参数
		JSONObject biz = pdataObj.getJSONObject("biz");
		String contentid = biz.getString("content_id");
		String currentPage = biz.getString(Const.TAG_CURRENT_PAGE);
		String pageSize = biz.getString(Const.TAG_PAGE_SIZE);
		// 对参数进行非空判断
		if (judgeAgumentsIsLegal(pdataObj, contentid,currentPage,pageSize)) {
			// 向数据库请求数据并判断是否分页
			List<UserComment> list;
			if (judgePageInfoIsLegal(currentPage, pageSize)) {
				PageHelper.startPage(Integer.parseInt(currentPage), Integer.parseInt(pageSize));
				list = selectCommentListByContentId(contentid);
				PageInfo<UserComment> pageInfo = new PageInfo<>(list);
				setPagingData(pdataObj, pageInfo.getPages(), pageInfo.getPageNum());
			} else {
				list = selectCommentListByContentId(contentid);
			}
			JSONArray commentJsonList = new JSONArray();
			for(UserComment userComment : list){
				JSONObject userCommentJson = (JSONObject) JSON.toJSON(userComment);
				Member member = memberMapper.selectByPrimaryKey(Integer.valueOf(userComment.getMemberId()));
				userCommentJson.put("userLogo", member.getHeadPic());
				userCommentJson.put("nickName", member.getNickName());
				commentJsonList.add(userCommentJson);
			}
			pdataObj.put(Const.TAG_BIZ, commentJsonList);
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			pdataObj.put(Const.TAG_RMK, "获取成功！");
		} else {
			requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
		}
		return pdataObj.toJSONString();
	}
	
	private List<UserComment> selectCommentListByGoodsId(String goodsId) {
		UserCommentExample example = new UserCommentExample();
		example.createCriteria().andRefIdEqualTo(Integer.valueOf(goodsId)).andCommentTypeEqualTo(Constants.GOODS);
		example.setOrderByClause("create_time desc");
		List<UserComment> empList = userCommentMapper.selectByExample(example);
		return empList;
	}
	
	private List<UserComment> selectCommentListByOrgId(String orgId) {
		UserCommentExample example = new UserCommentExample();
		example.createCriteria().andRefIdEqualTo(Integer.valueOf(orgId)).andCommentTypeEqualTo(Constants.ORG);
		example.setOrderByClause("create_time desc");
		List<UserComment> empList = userCommentMapper.selectByExample(example);
		return empList;
	}
	
	private List<UserComment> selectCommentListByContentId(String contentId) {
		UserCommentExample example = new UserCommentExample();
		example.createCriteria().andRefIdEqualTo(Integer.valueOf(contentId)).andCommentTypeEqualTo("content");
		example.setOrderByClause("create_time desc");
		List<UserComment> empList = userCommentMapper.selectByExample(example);
		return empList;
	}
	
	public String addUserComment(JSONObject pdataObj) {
		// 获取参数
		JSONObject biz = pdataObj.getJSONObject(Const.TAG_BIZ);
		String memberId = biz.getString("member_id");
		String orderid = biz.getString("iext1");
		String type = biz.getString("comment_type");
		
		JSONArray rids = biz.getJSONArray("ref_id");
		JSONArray contents = biz.getJSONArray("content");
		JSONArray startss = biz.getJSONArray("starts");
		
		
		// 对参数进行非空判断
		if (!judgeAgumentsIsLegal(pdataObj, memberId,orderid,type)) {
			requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
			return toJsonStringWithOutNull(pdataObj);
		} 
		// 
		if (rids.size()==0 || startss.size() ==0) {
			requestFailed(pdataObj, Const.RMK_BIZ_PARAM_NULL);
			return toJsonStringWithOutNull(pdataObj);
		} 
		Integer[] rid = StringUtil.JSONArrayToInt(rids);
		Integer[] starts = StringUtil.JSONArrayToInt(startss);
		String [] content = StringUtil.JSONArrayToStr(contents);
		
		try {
			saveUserComment(rid, Integer.parseInt(orderid), Integer.parseInt(memberId), type, content, starts);
			pdataObj.put(Const.TAG_STS, Const.TAG_STS_SUCCESS);
			pdataObj.put(Const.TAG_RMK, "添加成功！");
		} catch (Exception e) {
			requestFailed(pdataObj, Const.TAG_STS_FAIL);
			requestFailed(pdataObj, Constants.ExceptionMsg.SERVER_ERROR);
			e.printStackTrace();
		}
		return pdataObj.toJSONString();
	}
	@Override
	public void saveUserComment(Integer[] rid,Integer orderid,Integer memberId,String type,String[] content,
			Integer[] stars) {
		for (int i = 0; i < rid.length; i++) {
			UserComment userComment = new UserComment();
			userComment.setCommentId(sysSequenceService.getSequenceValue(Constants.SequenceName.USER_COMMENT));
			userComment.setCommentType(type);
			userComment.setRefId(rid[i]);
			if(content!=null&&content.length>0){
				userComment.setContent(EmojiUtil.emojiConvert(content[i]));
			}
			userComment.setMemberId(String.valueOf(memberId));
			userComment.setIext1(orderid);
			userComment.setStars(stars[i]);
			userComment.setStatus(Constants.STATUS_NORMAL);
			userComment.setCreateTime(new Date());
			userCommentMapper.insert(userComment);
			
			//服务日志
			if(type.equals(Constants.SERVICE)){
				ServiceLogExample example = new ServiceLogExample();
		        example.createCriteria().andOrderIdEqualTo(orderid);
		        List<ServiceLog> serviceLogList = serviceLogMapper.selectByExample(example);
		        if(serviceLogList.size()>0){
		        	ServiceLog log = serviceLogList.get(0);
		        	log.setServiceComment(content[i]);
		        	log.setScore(stars[i]);
		        	serviceLogMapper.updateByPrimaryKeySelective(log);
		        }
			}
			
		}
		if(type.equals(Constants.GOODS)){
			ywOrderService.updateStatus(orderid, Constants.OrderType.ORDER_DONE,null);
		}else{
			workOrderService.updateStatus(orderid, Constants.OrderType.SERVICE_RETURNORDER,null);
		}
		
	}

	@Override
	public List<UserComment> selectAll(UserComment comment,Integer limit) {
		if(comment==null){
			comment = new UserComment();
		}
		return userCommentMapper.selectAll(comment,limit);
	}

	@Override
	public List<UserComment> getCommentsListByRefIdAndCommentType(Integer refId, String commentType) {
		UserCommentExample example = new UserCommentExample();
		example.createCriteria().andRefIdEqualTo(refId).andCommentTypeEqualTo(commentType);
		example.setOrderByClause("create_time desc");
		List<UserComment> commentList = userCommentMapper.selectByExample(example);
		return commentList;
	}
	
}	
