package com.yhcrt.healthcloud.system.service;

import com.yhcrt.healthcloud.system.entity.SysUser;

/* @Description: 登录信息SERVICE层接口类
 * @version 1.0     2017年5月9日
 * @author jimmy
*/
public interface SysUserService {
    
            Integer update(SysUser user);
            SysUser selUser(Integer userId);
            
     /**
      * 根据userCode查询 SysUser对象数据      
      * @param userCode
      * @return
      */
	 public SysUser selectByUserCode(String userCode);
	 
	 /**
	  * 插入SysUser对象数据
	  * @param record
	  * @return
	  */
	 public int insert(SysUser record);
	 
	 /**
	  * 根据userCode更新SysUser对象数据
	  * @param record
	  * @return
	  */
	 public int updateByUserCode(SysUser record);
	 
}
