
package com.yhcrt.service.stsyem;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yhcrt.dao.DaoSupport;
import com.yhcrt.entity.system.SysMuserInfo;
/**
 * 系统部门的service
 * @author 陈伟
 * 2017年5月23日 下午1:10:51
 * 版权所有：武汉炎黄创新服务有限公司
 */
@Service("sysDeptUserService")
public class SysDeptUserService {
	@Resource
	private DaoSupport dao;
	
	/**
	 * 
	 * @Title: getByDeptCid
	 * @Description: 查询部门下的用户
	 * @return: List<SysMuserInfo>
	 */
	@SuppressWarnings("unchecked")
	public List<SysMuserInfo> getByDeptCid(Integer deptCid) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("deptCid",  deptCid);
		return (List<SysMuserInfo>) dao.findForList("SysMuserInfoMapper.getByDeptCid", map);
	}
	/**
	 * 
	 * @Title: getByDeptCid
	 * @Description: 查询不在某部门下的用户
	 * @return: List<SysMuserInfo>
	 */
	@SuppressWarnings("unchecked")
	public List<SysMuserInfo> getByNotDeptCid(Integer deptCid) throws Exception{
		List<SysMuserInfo> list = new ArrayList<SysMuserInfo>();
		Map<String, Object> map = new HashMap<String, Object>();
		if(deptCid==null){//新增部门是获取所有用户
			list = (List<SysMuserInfo>)  dao.findForList("SysMuserInfoMapper.quertByParam",null);
		}else{//编辑部门
			map.put("deptCid",  deptCid);
			List<Integer> listUserCids =  (List<Integer>) dao.findForList("SysMuserInfoMapper.getByDeptUserCid", map);
			if(listUserCids.size()==0){//该部门没有用户时获取所有用户
				list = (List<SysMuserInfo>)  dao.findForList("SysMuserInfoMapper.quertByParam",null);
			}else{
				list = (List<SysMuserInfo>) dao.findForList("SysMuserInfoMapper.getByNotDeptCid", listUserCids);
			}
		}
		return list;
	}
	
	
}
