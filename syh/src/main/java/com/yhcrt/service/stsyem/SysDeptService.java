
package com.yhcrt.service.stsyem;

import static com.yhcrt.utils.Constants.SYS_DEPT;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yhcrt.dao.DaoSupport;
import com.yhcrt.entity.system.SysDept;
import com.yhcrt.utils.GetSequence;

/**
 * 系统部门的service
 * @author 陈伟
 * 2017年5月23日 下午1:10:51
 * 版权所有：武汉炎黄创新服务有限公司
 */
@Service("sysDeptService")
public class SysDeptService {
	@Resource
	private DaoSupport dao;
	
	/* 
	 * @Title: saveSelective
	 * @Description: 新增信息
	 */
	public void saveSelective(SysDept sysDept) throws Exception {
		int deptCid = GetSequence.getSequenceByName(dao, SYS_DEPT);
		sysDept.setCid(deptCid);
		dao.save("SysDeptMapper.saveSelective", sysDept);
		
		//建立部门用户关系
		Integer [] userCids = sysDept.getUserCids();
		if(userCids !=null && userCids.length!=0){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userCids", userCids);
			map.put("deptCid", deptCid);
			
			dao.save("SysDeptUserMapper.saveSelectives",map);
		}
	}
	/**
	 * 
	 * @Title: updateByPrimaryKeySelective
	 * @Description: 更新信息
	 * @return: void
	 * @throws Exception 
	 */
	public void updateByPrimaryKeySelective(SysDept sysDept) throws Exception{
		dao.update("SysDeptMapper.updateByPrimaryKeySelective",sysDept);
		
		//建立部门用户关系
		Integer deptCid = sysDept.getCid();
		dao.delete("SysDeptUserMapper.removeByDeptCid", deptCid);
		Integer [] userCids = sysDept.getUserCids();
		if(userCids !=null && userCids.length!=0){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("deptCid", deptCid);
			map.put("userCids", userCids);
			
			dao.delete("SysDeptUserMapper.removeByUserCid",userCids);
			dao.save("SysDeptUserMapper.saveSelectives",map);
		}
	}
	/**
	 * 
	 * @Title: getByCid
	 * @Description: 根据ID查询
	 * @return: SysRole
	 */
	public SysDept getByCid(Integer deptCid) throws Exception{
		return (SysDept) dao.findForObject("SysDeptMapper.getByCid", deptCid);
	}
	/**
	 * 获取部门的节点树
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SysDept> queryToTree() throws Exception{
		return (List<SysDept>) dao.findForList("SysDeptMapper.queryToTree",null);
	}
	/**
	 * 批量删除资源
	 * @param deleteAll
	 * @return
	 * @throws Exception
	 */
	public String deleteAll(Integer cid) throws Exception {
		Integer count = (Integer) dao.findForObject("SysDeptMapper.countByPid", cid);
		if(count>0){
			return "nodel";
		}else{
			dao.delete("SysDeptUserMapper.removeByDeptCid",cid);
			dao.delete("SysDeptMapper.removeByCid",cid);
			return "success";
		}
	}
}
