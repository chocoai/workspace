
package com.yhcrt.service.stsyem;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yhcrt.dao.DaoSupport;
import com.yhcrt.entity.system.SysConfig;
import com.yhcrt.shiro.TokenManager.TokenManager;
import com.yhcrt.utils.DatebaseUtils;
import com.yhcrt.utils.SpringUtil;

/**
 * 系统配置的service
 * @author 陈伟
 * 2017年5月23日 下午1:10:51
 * 版权所有：武汉炎黄创新服务有限公司
 */
@Service("sysConfigService")
public class SysConfigService {
	
	@Resource
	private DaoSupport dao;
	
	/**
	 * 
	 * @Title: getByCid
	 * @Description: 根据ID查询
	 * @return: SysRole
	 */
	public SysConfig getByConfig() throws Exception{
		return (SysConfig) dao.findForObject("SysConfigMapper.getByCid", 45);
	}
	
	/**
	 * 
	 * @Title: saveSelective
	 * @Description: 只更新不为null的字段,不会影响有默认值的字段
	 * @param SysListData
	 * @throws Exception 
	 */
	public void updateByPrimaryKeySelective(SysConfig sysConfig) throws Exception{
		sysConfig.setErsionNum(TokenManager.getErsionNum());
		dao.update("SysConfigMapper.updateByPrimaryKeySelective",sysConfig);
	}
	/**
	 * 
	 * @Title: backupDatebase
	 * @Description: 备份数据库
	 * @return: void
	 * @throws Exception 
	 */
	public void backupDatebase() throws Exception{
		SysConfig sysConfig = (SysConfig) SpringUtil.getConfigObj("sysConfig");
		DatebaseUtils.backupDatebase(sysConfig.getDbIp(), sysConfig.getDbPath(),
				sysConfig.getDbUserName(), sysConfig.getDbPassword(), sysConfig.getDbName(), sysConfig.getDbFilePath());
	};
	
	/**
	 * 
	 * @Title: restoreDatebase
	 * @Description: 恢复数据库
	 * @param filePath 执行文件
	 * @return: void
	 * @throws Exception 
	 */
	public void restoreDatebase(String filePath) throws Exception{
		SysConfig sysConfig = (SysConfig) SpringUtil.getConfigObj("sysConfig");
		DatebaseUtils.backupDatebase(sysConfig.getDbIp(), sysConfig.getDbPath(),
				sysConfig.getDbUserName(), sysConfig.getDbPassword(), sysConfig.getDbName(),filePath);
	};

	
}
