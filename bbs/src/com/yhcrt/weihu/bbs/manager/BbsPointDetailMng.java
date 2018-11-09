package com.yhcrt.weihu.bbs.manager;

import com.yhcrt.weihu.bbs.entity.BbsPointDetail;
import com.yhcrt.weihu.common.page.Page;

public interface BbsPointDetailMng {

	public Integer getPointsAll(Integer userId);
	/**
	 * 根据类型判断该类型任务今日是否已完成,每日一次的任务
	 * @param userId   用户
	 * @param type   类型
	 * @return
	 */
	public boolean isTodayComplete(Integer userId,String type);
	public boolean isUploadImg(Integer userId);
	public boolean isSign(Integer userId);
	public Page<BbsPointDetail> getPage(Integer userId,Integer pageSize,Integer pageNo);
	public void save(Integer userId,Integer points,String description,String operation,String type);
	
}
