package com.whty.wfd.page.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.whty.wfd.page.dao.*;
import com.whty.wfd.page.model.*;
import com.whty.wfd.page.util.BaiduYun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whty.wfd.common.utils.GUIDGenerator;
import com.whty.wfd.common.utils.SendMessageUtils;
import com.whty.wfd.common.utils.SysConfigUtils;
import com.whty.wfd.common.utils.TimeUtils;
import com.whty.wfd.page.service.PostService;
import sun.misc.BASE64Decoder;

/**
 * \* User: zjd \* Date: 2018/8/21 \* Time: 16:04 \* Description: \
 */
@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private TPlatePostMapper tPlatePostMapper;
	@Autowired
	private TPlatePostImgMapper tPlatePostImgMapper;
	@Autowired
	private TPostMessageMapper tPostMessageMapper;
	@Autowired
	private TPlatePostEditMapper tPlatePostEditMapper;
	@Autowired
	private TPlateUserMapper tPlateUserMapper;
	@Autowired
	private TUserMapper tUserMapper;
	@Autowired
	private TPlatePostEditImgMapper tPlatePostEditImgMapper;

	@Override
	public List<TPlatePost> getPostByUserId(Integer userId, Integer pageNum, Integer pageSize) {
		return tPlatePostMapper.getPostByUserId(userId, pageNum, pageSize);
	}

	@Override
	public Integer getTotal(Integer userId) {
		TPlatePostExample example = new TPlatePostExample();
		example.createCriteria().andCreatorEqualTo(userId).andIsDeleteEqualTo(false);
		return (int) tPlatePostMapper.countByExample(example);
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public void addPost(TUser tUser, TPlatePost tPlatePost, List<TPlatePostImg> tPlatePostImgs, Integer[] userIds) {
		try {
			if (tPlatePostImgs.size() > 0) {
				tPlatePostImgMapper.insertManyImg(tPlatePostImgs);
			}
			TPlatePostEditExample editExample = new TPlatePostEditExample();
			editExample.createCriteria().andCreatorEqualTo(tPlatePost.getCreator());
			tPlatePostEditMapper.deleteByExample(editExample);

			TPlatePostEditImgExample example = new TPlatePostEditImgExample();
			example.createCriteria().andCreatorEqualTo(tPlatePost.getCreator());
			tPlatePostEditImgMapper.deleteByExample(example);

			final List<String> sendeeIds = tPlateUserMapper.selectByPlateId(tPlatePost.getPlateId());// 版块块相关人

			final String finalContent = tPlatePost.getContent();

			if (userIds != null && userIds.length > 0) {
				String reg = "";
				String content = tPlatePost.getContent();
				for (Integer userId : userIds) {
					TUser enUser = tUserMapper.selectByPrimaryKey(userId);
					reg = "@" + enUser.getName();
					if (content.contains(reg)) {
						content = content.replace(reg, "<span style=\"color:#01c7f9\">" + reg + "</span>");
						sendeeIds.add(enUser.getPersonId());// 添加atl的人
					}
				}
				tPlatePost.setContent(content);
			}
			tPlatePostMapper.insert(tPlatePost);
			final String wfdUrl = SysConfigUtils.getStrValue("domain") + "/getPostDetail?postId=" + tPlatePost.getId()
					+ "&plateId=" + tPlatePost.getPlateId();

			final TUser threaBean = tUser;
			// final TPlatePost threaTPlatePost = tPlatePost;
			new Thread(new Runnable() {
				@Override
				public void run() {
					SendMessageUtils.sendOaMessage(threaBean, sendeeIds, wfdUrl, finalContent,
							threaBean.getName() + "发布了提问", TimeUtils.date2String(new Date(), TimeUtils.DAY_FORMAT_1));
				}
			}).start();
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}

	}

	@Override
	public List<TPlatePost> getPostByPlateIdAllPage(String plateId, Integer pageNum, Integer pageSize, Integer userId,
													Integer orderByClause) {
		List<TPlatePost> tPlatePosts = tPlatePostMapper.getPostByPlateId(plateId, userId, orderByClause, pageNum,
				pageSize);
		return tPlatePosts;
	}

	@Override
	public List<TPlatePost> getPostByPlateIdAllTeacherPage(String plateId, Integer pageNum, Integer pageSize,
			Integer userId, Integer orderByClause) {
		List<TPlatePost> tPlatePosts = tPlatePostMapper.getPostByPlateIdAllTeacher(plateId, userId, orderByClause,
				pageNum, pageSize);
		return tPlatePosts;
	}

	@Override
	public List<TPlatePost> getPostByPlateIdAllMasterPage(String plateId, Integer pageNum, Integer pageSize,
			Integer userId, Integer orderByClause, Integer schoolId) {
		List<TPlatePost> tPlatePosts = tPlatePostMapper.getPostByPlateIdAll(plateId, userId, orderByClause, schoolId,
				pageNum, pageSize);
		return tPlatePosts;
	}

	@Override
	public List<TPlatePost> getPostByPlateIdAllPage1(String plateId, Integer pageNum, Integer pageSize, Integer userId,
													 Integer orderByClause) {
		return tPlatePostMapper.getPostByPlateId1(plateId, userId, orderByClause, pageNum, pageSize);
	}

	@Override
	public List<TPlatePost> getPostByPlateIdAllTeacherPage2(String plateId, Integer pageNum, Integer pageSize,
			Integer userId, Integer orderByClause) {
		return tPlatePostMapper.getPostByPlateIdAllTeacher2(plateId, userId, orderByClause, pageNum, pageSize);
	}

	@Override
	public List<TPlatePost> getPostByPlateIdAllMasterPage3(String plateId, Integer pageNum, Integer pageSize,
			Integer userId, Integer orderByClause, Integer schoolId) {
		return tPlatePostMapper.getPostByPlateIdAll3(plateId, userId, orderByClause, schoolId, pageNum, pageSize);
	}

	@Override
	public List<TPlatePost> getPostByPlateIdAll(String plateId, Integer userId, String orderByClause) {
		return tPlatePostMapper.getPostByPlateIdIsTop(plateId, userId, orderByClause);
	}

	@Override
	public List<TPlatePost> getPostByPlateIdAllTeacher(String plateId, Integer userId, String orderByClause) {
		return tPlatePostMapper.getPostByPlateIdAllTeacherIsTop(plateId, userId, orderByClause);
	}

	@Override
	public List<TPlatePost> getPostByPlateIdAllMaster(String plateId, Integer userId, String orderByClause) {
		return tPlatePostMapper.getPostByPlateIdAllIsTop(plateId, userId, orderByClause);
	}

	@Override
	public TPlatePost getPostByPostId(String postId, Integer userId) {
		return tPlatePostMapper.getPostByPostId(postId, userId);
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public int updatePostByPostId(TPlatePost tPlatePost) {
		try {
			TPlatePost entity = tPlatePostMapper.selectByPrimaryKey(tPlatePost.getId());
			TPlatePostExample tPlatePostExample = new TPlatePostExample();
			tPlatePostExample.createCriteria().andPlateIdEqualTo(entity.getPlateId()).andIsTopEqualTo(true);
			tPlatePostExample.setOrderByClause("update_time");
			List<TPlatePost> list = tPlatePostMapper.selectByExample(tPlatePostExample);
			if (list.size() >= 3) {
				list.get(0).setIsTop(false);
				tPlatePostMapper.updateByPrimaryKeySelective(list.get(0));
			}
			return tPlatePostMapper.updateByPrimaryKeySelective(tPlatePost);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public int deletePostByPostId(TPlatePost tPlatePost) {
		int i = 0;
		try {
			tPlatePostMapper.updateByPrimaryKeySelective(tPlatePost);
			tPostMessageMapper.updateByPostId(tPlatePost.getId());
			i = 1;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
		return i;
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public int savePostContent(Integer userId, String content, String plateId, String[] imgUrls, String[] imgHttpUrls,
			String userIds) {
		try {
			TPlatePostEditExample example = new TPlatePostEditExample();
			example.createCriteria().andCreatorEqualTo(userId);
			tPlatePostEditMapper.deleteByExample(example);

			TPlatePostEdit tPlatePostEdit = new TPlatePostEdit();
			tPlatePostEdit.setContent(content);
			tPlatePostEdit.setCreateTime(new Date());
			tPlatePostEdit.setUpdateTime(new Date());
			tPlatePostEdit.setCreator(userId);
			tPlatePostEdit.setId(GUIDGenerator.getGUID());
			tPlatePostEdit.setPlateId(plateId);
			tPlatePostEdit.setAtUserId(userIds);
			tPlatePostEditMapper.insert(tPlatePostEdit);

			TPlatePostEditImgExample tPlatePostEditImgExample = new TPlatePostEditImgExample();
			tPlatePostEditImgExample.createCriteria().andCreatorEqualTo(userId);
			tPlatePostEditImgMapper.deleteByExample(tPlatePostEditImgExample);

			List<TPlatePostEditImg> tPlatePostEditImgs = new ArrayList<>();
			BASE64Decoder decoder = new BASE64Decoder();
			String reg = "data:image/jpeg;base64,";
			String reg2 = "data:image/jpeg;base64";
			if (imgUrls != null && imgUrls.length > 0) {
				for (int i = 0; i < imgUrls.length; i++) {
					if (reg2.equals(imgUrls[i])) {
						continue;
					}
					String base64 = imgUrls[i].replace(reg, "");
					byte[] imageByte = decoder.decodeBuffer(base64);
					String bosAddress = BaiduYun.upload(imageByte, ".jpg");
					TPlatePostEditImg tPlatePostEditImg = new TPlatePostEditImg();
					tPlatePostEditImg.setCreateTime(new Date());
					tPlatePostEditImg.setUpdateTime(new Date());
					tPlatePostEditImg.setCreator(userId);
					tPlatePostEditImg.setPlatePostEditId(tPlatePostEdit.getId());
					tPlatePostEditImg.setImgUrl(bosAddress);
					tPlatePostEditImgs.add(tPlatePostEditImg);
				}
			}
			if (imgHttpUrls != null && imgHttpUrls.length > 0) {
				for (int i = 0; i < imgHttpUrls.length; i++) {
					TPlatePostEditImg tPlatePostEditImg = new TPlatePostEditImg();
					tPlatePostEditImg.setCreateTime(new Date());
					tPlatePostEditImg.setUpdateTime(new Date());
					tPlatePostEditImg.setCreator(userId);
					tPlatePostEditImg.setPlatePostEditId(tPlatePostEdit.getId());
					tPlatePostEditImg.setImgUrl(imgHttpUrls[i]);
					tPlatePostEditImgs.add(tPlatePostEditImg);
				}
			}
			if (tPlatePostEditImgs.size() > 0) {
				tPlatePostEditImgMapper.insertManyImg(tPlatePostEditImgs);
			}
			return 1;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public int deletePostContent(String id) {
		try {
			TPlatePostEditImgExample example = new TPlatePostEditImgExample();
			example.createCriteria().andPlatePostEditIdEqualTo(id);
			tPlatePostEditImgMapper.deleteByExample(example);
			tPlatePostEditMapper.deleteByPrimaryKey(id);
			return 1;
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public TPlatePostEdit getTPlatePostEditByUserId(Integer userId) {
		TPlatePostEditExample example = new TPlatePostEditExample();
		example.createCriteria().andCreatorEqualTo(userId);
		List<TPlatePostEdit> list = tPlatePostEditMapper.selectByExample(example);
		return list.size() > 0 ? list.get(0) : null;
	}

	@Override
	public List<TPlatePostEditImg> getTPlatePostEditImgByUserId(Integer userId) {
		TPlatePostEditImgExample tPlatePostEditImgExample = new TPlatePostEditImgExample();
		tPlatePostEditImgExample.createCriteria().andCreatorEqualTo(userId);
		List<TPlatePostEditImg> tPlatePostEditImgs = tPlatePostEditImgMapper.selectByExample(tPlatePostEditImgExample);
		return tPlatePostEditImgs;
	}

	@Override
	public TPlatePost selectByKey(String postId) {
		return tPlatePostMapper.selectByPrimaryKey(postId);
	}

	@Override
	public List<TPlatePost> getHostPost(Integer userId,Integer schoolId) {
		return tPlatePostMapper.getHostPost(userId,schoolId);
	}
}