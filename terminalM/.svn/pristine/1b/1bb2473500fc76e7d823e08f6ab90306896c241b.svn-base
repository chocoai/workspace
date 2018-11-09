/**
 * 
 */
package com.whty.assis.sysres.service;

import java.util.List;

import com.whty.assis.sysres.model.SysModular;

/** 
 * @ClassName: SysModularService 
 * @author: zjd
 * @date: 2018年6月2日 下午5:27:06  
 */
public interface SysModularService {
	
	/**
	 * 新增资源
	 * @param record
	 * @return
	 */
	int insert(SysModular record);
	
    
    /**
     * 根据资源ID删除权限资源
     * @param resId
     * @return
     */
    int deleteByPrimaryKey(Integer resId);

    /**
     * 根据权限资源ID更新权限资源
     * @param record
     * @return
     */
    int updateByPrimaryKey(SysModular record);
    
    /**
	 * 根据父级资源查询子级资源
	 * @param parentId
	 * @return
	 */
    List<SysModular> getChildModularByParentId(Integer parentId);
    
    /**
	 * 根据父级资源查询所有子级资源
	 * @param sysModular
	 * @return
	 */
    List<SysModular> selectByExample(SysModular record);
	
	/**
	 * 根据权限资源Id查询权限资源
	 * @param resId
	 * @return
	 */
	SysModular getModularId(Integer Id);
	
	/**
	 * 查询权限资源根节点
	 * @return
	 */
	SysModular getModularNode();
    
    
    /**
     * 查询当前用户的所有权限资源并构建树图
     * @return
     */
    List<SysModular> listAllSysModular(Integer userId);
    
    /**
     * 查询当前用户的所有权限资源
     * @return
     */
    List<SysModular> listAllUserModular(Integer userId);
    
    /**
     * 查询所有权限资源
     * @return
     */
    List<SysModular> allModular();
    
    /**
     * 查询所有权限资源
     * @return
     */
    List<SysModular> allModulars();
    
    SysModular selectByPrimaryKey(Integer id);

}
