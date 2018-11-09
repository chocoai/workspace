
package com.yhcrt.service.stsyem;

import static com.yhcrt.utils.Constants.SYS_RES;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yhcrt.dao.DaoSupport;
import com.yhcrt.entity.Tree;
import com.yhcrt.entity.system.SysRes;
import com.yhcrt.utils.Constants;
import com.yhcrt.utils.GetSequence;

/**
 * 系统权限的service
 * @author 陈伟
 * 2017年5月23日 下午1:11:21
 * 版权所有：武汉炎黄创新服务有限公司
 */
@Service("sysResService")
public class SysResService {
	@Resource
	private DaoSupport dao;
	@Resource(name="sysRoleResService")
	private SysRoleResService sysRoleResService;
	
	/**
	 * @Title: saveSelective
	 * @Description: 新增信息
	 * @return: void
	 */
	public void saveSelective(SysRes sysRes) throws Exception {
		Integer resCid = GetSequence.getSequenceByName(dao, SYS_RES);
		sysRes.setCid(resCid);
		dao.save("SysResMapper.saveSelective", sysRes);
		/**新增权限时默认给管理员添加保证其拥有最大权限***/
		Map<String, Object> map = new HashMap<String, Object>();
		Integer[] resIds = {resCid};
		map.put("roleId", Constants.ADMIN_ISTRATOR);
		map.put("resIds", resIds);
		dao.save("SysRolePowerMapper.saveSelectives",map);
		
		
	}
	
	
	/**
	 * 
	 * @Title: updateByPrimaryKeySelective
	 * @Description: 更新信息
	 * @return: void
	 */
	public void updateByPrimaryKeySelective(SysRes sysRes) throws Exception{
		dao.update("SysResMapper.updateByPrimaryKeySelective", sysRes);
	}
	/**
	 * 
	 * @Title: getByCid
	 * @Description: 根据ID查询信息
	 * @return: SysRes
	 */
	public SysRes getByCid(Integer resCid) throws Exception{
		return (SysRes) dao.findForObject("SysResMapper.getByCid", resCid);
	}
	/**
	 * 批量删除权限资源
	 * @param getById
	 * @return
	 * @throws Exception
	 */
	public String deleteAll(Integer[] cids) throws Exception{
		Integer count = (Integer) dao.findForObject("SysResMapper.countByPid", cids);
		if(count>0){
			return "nodel";
		}else{
			dao.delete("SysRolePowerMapper.removeByResCids",cids);
			dao.delete("SysResMapper.deleteAll",cids);
			return "success";
		}
	}
	
	
	/**
	 * 获取资源分类的节点树
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SysRes> queryToTree(Integer pid) throws Exception{
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("pid", pid);
		return (List<SysRes>) dao.findForList("SysResMapper.queryToTree",map);
	}
	/**
	 * 获取资源选中的节点树
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Tree> queryToCheckedTree(Integer roleCid) throws Exception{
		List<SysRes> list = (List<SysRes>) dao.findForList("SysResMapper.queryToTree",null);
		List<Integer> listCid = (List<Integer>) dao.findForList("SysRolePowerMapper.getByRoleCid",roleCid);
		
		List<Tree> trees = new ArrayList<Tree>();
		for (SysRes sysRes :list) {
			Tree tree=new Tree();
			tree.setId(sysRes.getCid());
			tree.setParentId(sysRes.getPareId());
			tree.setNodeValue(sysRes.getResName());
			if(listCid.contains(sysRes.getCid())){
				tree.setChecked(true);
			}
			trees.add(tree);
		}
		return trees;
	}

}
