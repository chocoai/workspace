package com.yhcrt.service.signUp;

import static com.yhcrt.utils.Constants.UNIT_INFO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yhcrt.dao.DaoSupport;
import com.yhcrt.entity.signUp.UnitInfo;
import com.yhcrt.shiro.TokenManager.TokenManager;
import com.yhcrt.utils.GetSequence;

/**
 * 单位信息的service
 * @author kejunzhong
 * 2017年5月11日
 * 版权所有：武汉炎黄创新服务有限公司
 */
@Service("unitInfoService")
public class UnitInfoService {
	@Resource
	private DaoSupport dao;
	
	
	/**
	 * 新增参赛单位信息
	 * @param UnitInfo
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void insert(UnitInfo unitInfo) throws Exception{
		int cid = GetSequence.getSequenceByName(dao, UNIT_INFO);
		unitInfo.setCid(cid);
		dao.save("UnitInfoMapper.insert", unitInfo);
	}
	/**
	 * 更新参赛单位信息
	 * @param UnitInfo
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void updateByPrimaryKey(UnitInfo unitInfo) throws Exception{
		dao.update("UnitInfoMapper.updateByPrimaryKey", unitInfo);
	}
	/**
	 * 根据ID获取参赛单位信息
	 * @param getById
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public UnitInfo getById(Integer cid) throws Exception{
		if(cid!= null){
			return (UnitInfo) dao.findForObject("UnitInfoMapper.getById", cid);
		}
		return  null;
	}
	/**
	 * 批量删除参赛单位
	 * @param getById
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Boolean deleteAll(Integer[] cids) throws Exception{
		long count = (Long) dao.findForObject("AthleteBaseInfoMapper.countByUnitCids", cids);
		//判断该单位下是否存在运动员
		if(count==0){
			dao.delete("UnitInfoMapper.deleteAll",cids);
			return true;
		}
		return false;
	}
	/**
	 * 判断参赛单位是否已录入
	 * @param name
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public boolean isName(String name) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("unitName",name);
		map.put("ersionNum", TokenManager.getErsionNum());
		Integer count = (Integer) dao.findForObject("UnitInfoMapper.countByName", map);
		return count >0 ?true :false;
	}
	
	/**
	 * 通过公司名称/简称查询参赛单位信息
	 * @param findContent
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<UnitInfo> quertByUnitContent(String findContent) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("findContent", findContent);
		map.put("ersionNum", TokenManager.getErsionNum());
		return (ArrayList<UnitInfo>)dao.findForList("UnitInfoMapper.quertByUnitContent", map);
	}
	
	/**
	 * 通过单位简称查询单位信息
	 * @param abbreviation
	 * @return
	 * @throws Exception
	 */
	public List<UnitInfo> quertByAbbreviation(Map<String, Object> model) throws Exception{
		return (ArrayList<UnitInfo>)dao.findForList("UnitInfoMapper.quertByAbbreviation", model);
	}
	/**
	 * 通过单位名称查询公司信息
	 * @param unitName
	 * @return
	 * @throws Exception
	 */
	public List<UnitInfo> quertByUnitName(Map<String, Object> model) throws Exception{
		return (ArrayList<UnitInfo>)dao.findForList("UnitInfoMapper.quertByUnitName",model);
	}
}