package com.yhcrt.utils;

import com.yhcrt.dao.DaoSupport;
import com.yhcrt.entity.system.SysSequence;

/**
 * 获取序列号的方法
 * @author kejunzhong
 * 2017年5月22日
 * 版权所有：武汉炎黄创新服务有限公司
 */
public class GetSequence {
	/**
	 * 
	 * @Title: getSequenceByName
	 * @Description: 默认 +1 获取序列号
	 * @return: int
	 */
	public static int getSequenceByName(DaoSupport dao,String tableName) throws Exception{
		SysSequence sequence =  (SysSequence) dao.findForObject("SysSequenceMapper.getBySequName",tableName);
		Integer cid = sequence.getSequValue();
		sequence.setSequValue(cid+1);
		dao.update("SysSequenceMapper.updateBySequValue", sequence);
		return cid;
	} 
	/**
	 * 
	 * @Title: getSequencesByName
	 * @Description: 动态获取一定长度的序列号数组
	 * @return: int
	 */
	public static int[] getSequencesByName(DaoSupport dao,String tableName,Integer sum) throws Exception{
		int [] cids = new int[sum];
		SysSequence sequence =  (SysSequence) dao.findForObject("SysSequenceMapper.getBySequName",tableName);
		Integer cid = sequence.getSequValue();
		sequence.setSequValue(cid+sum);
		dao.update("SysSequenceMapper.updateBySequValue", sequence);
		for (int i = 0; i < cids.length; i++) {
			cids[i] = cid+i;
		}
		return cids;
	} 
}
