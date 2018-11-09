/**
 * 
 */
package com.whty.assis.sysres.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whty.assis.sysres.dao.SysModularMapper;
import com.whty.assis.sysres.model.SysModular;
import com.whty.assis.sysres.model.SysModularExample;
import com.whty.assis.sysres.service.SysModularService;

/** 
 * @ClassName: SysModularServiceImpl 
 * @author: zjd
 * @date: 2018年6月2日 下午5:28:07  
 */
@Service
public class SysModularServiceImpl implements SysModularService{

	@Autowired
	private SysModularMapper sysModularMapper;
	
	/* 
	 * @Title: selectByExample
	 * @param sysModular
	 * @return 
	 */ 
	@Override
	public List<SysModular> selectByExample(SysModular record) {
		return sysModularMapper.getChildrenModular(record);
	}

	/* 
	 * @Title: insert
	 * @param record
	 * @return 
	 */ 
	@Override
	public int insert(SysModular record) {
		return sysModularMapper.insert(record);
	}

	/* 
	 * @Title: deleteByPrimaryKey
	 * @param resId
	 * @return 
	 */ 
	@Override
	public int deleteByPrimaryKey(Integer resId) {
		SysModularExample example = new SysModularExample();
		example.createCriteria().andIdEqualTo(resId);
		return sysModularMapper.deleteByExample(example);
	}

	/* 
	 * @Title: updateByPrimaryKey
	 * @param record
	 * @return 
	 */ 
	@Override
	public int updateByPrimaryKey(SysModular record) {
		SysModularExample example = new SysModularExample();
		example.createCriteria().andIdEqualTo(record.getId());
		return sysModularMapper.updateByExample(record, example);
	}

	/* 
	 * @Title: getChildModularByParentId
	 * @param parentId
	 * @return 
	 */ 
	@Override
	public List<SysModular> getChildModularByParentId(Integer parentId) {
		SysModularExample example = new SysModularExample();
		example.createCriteria().andParentIdEqualTo(parentId.toString());
		return sysModularMapper.selectByExample(example);
	}

	/* 
	 * @Title: getModularId
	 * @param Id
	 * @return 
	 */ 
	@Override
	public SysModular getModularId(Integer Id) {
		return sysModularMapper.selectByPrimaryKey(Id);
	}

	/* 
	 * @Title: getModularNode
	 * @return 
	 */ 
	@Override
	public SysModular getModularNode() {
		//  Auto-generated method stub
		return null;
	}

	/* 
	 * @Title: list树图，方便jsp接收
	 * @return 
	 */ 
	@Override
	public List<SysModular> listAllSysModular(Integer userId) {
		List<SysModular> modularList = sysModularMapper.listAllSysModular(userId);
		System.out.println("查询前集合大小:"+modularList.size());  
		List<SysModular> resultList = new ArrayList<>();  
		for (SysModular sysModular : modularList) {  
            if (sysModular.getParentId().equals("1")) {//父级菜单开始添加  
                if (ifChilds(modularList, sysModular.getId())) {//存在子集  
                    List<SysModular> childs = new ArrayList<>();  
                    childs = getChildList(modularList,sysModular.getId(),childs);  
                    sysModular.setSysModular(childs);
                }
                resultList.add(sysModular);  
            }  
        } 
		return resultList;
	}

	/* 
	 * @Title: allModular
	 * @return 
	 */ 
	@Override
	public List<SysModular> allModular() {
		SysModularExample example = new SysModularExample();
		example.createCriteria().andStatusEqualTo(0);
		example.setOrderByClause("modular_sort");
		List<SysModular> modularList = sysModularMapper.selectByExample(example);
		List<SysModular> resultList = new ArrayList<>();  
		for (SysModular sysModular : modularList) {  
            if (sysModular.getParentId().equals("1")) {//父级菜单开始添加  
                if (ifChilds(modularList, sysModular.getId())) {//存在子集  
                    List<SysModular> childs = new ArrayList<>();  
                    childs = getChildList(modularList,sysModular.getId(),childs);  
                    sysModular.setSysModular(childs);
                }
                resultList.add(sysModular);  
            }  
        } 
		return resultList;
	}

	/* 
	 * @Title: selectByPrimaryKey
	 * @param id
	 * @return 
	 */ 
	@Override
	public SysModular selectByPrimaryKey(Integer id) {
		return sysModularMapper.selectByPrimaryKey(id);
	}
	
	//获取父id下的子集合  
    private static List<SysModular> getChildList(List<SysModular> list,Integer pId,List<SysModular> reList) {  
        for (SysModular testEntity : list) {  
            if (testEntity.getParentId().equals(pId.toString())) {//查询下级菜单  
                if (ifChilds(list, testEntity.getId())) {
                	List<SysModular> childss = new ArrayList<>();  
                    childss = getChildList(list, testEntity.getId(), childss);  
                    testEntity.setSysModular(childss);
                }  
                reList.add(testEntity);  
            }  
        }  
        return reList;  
    }  
	
	//判断是否存在子集  
    private static boolean ifChilds(List<SysModular> list,Integer id) {  
        boolean flag = false;  
        for (SysModular sysmodular : list) {  
            if (sysmodular.getParentId().equals(id.toString())) {  
                flag=true;  
                break;  
            }  
        }  
        return flag;  
    }

	/* 
	 * @Title: allModulars
	 * @return 
	 */ 
	@Override
	public List<SysModular> allModulars() {
		SysModularExample example = new SysModularExample();
		example.createCriteria().andStatusEqualTo(0);
		example.setOrderByClause("modular_sort");
		return sysModularMapper.selectByExample(example);
	}

	/* 
	 * @Title: 查询当前用户的所有权限资源
	 * @param userId
	 * @return 
	 */ 
	@Override
	public List<SysModular> listAllUserModular(Integer userId) {
		return sysModularMapper.listAllSysModular(userId);
	} 

}
