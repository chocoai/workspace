
package com.yhcrt.service.stsyem;

import static com.yhcrt.utils.Constants.SYS_SYSTEM_LOG;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yhcrt.controller.BaseController;
import com.yhcrt.dao.DaoSupport;
import com.yhcrt.entity.system.SysSystemLog;
import com.yhcrt.shiro.TokenManager.TokenManager;
import com.yhcrt.utils.ConstantsLog;
import com.yhcrt.utils.DateUtil;
import com.yhcrt.utils.GetSequence;
import com.yhcrt.utils.PublicUtil;

/**
 * 系统用户的service
 * @author 陈伟
 * 2017年5月23日 下午1:11:02
 * 版权所有：武汉炎黄创新服务有限公司
 */
@Service("sysStsyemLogService")
public class SysStsyemLogService {
	@Resource
	private DaoSupport dao;
	/* 
	 * @Title: saveSelective
	 * @Description: 新增操作日志信息
	 */
	public void saveSelective(String content,String detail,Integer state) throws Exception {
		SysSystemLog log = new SysSystemLog();
		log.setCid(GetSequence.getSequenceByName(dao, SYS_SYSTEM_LOG));
		log .setOpeartionContent(content);
		log.setOpeartionDetail(detail);
		log.setOpeartionIp(PublicUtil.getIp());
		log.setOpeartionState(state);
		log.setOpeartionTime(DateUtil.getTime());
		log.setOpeartionUser(BaseController.getUserCode()+"/"+BaseController.getSessionName());
		log.setOpeartionType(ConstantsLog.LOG_1);//操作日志
		log.setErsionNum(TokenManager.getErsionNum());
		dao.save("SysSystemLogMapper.saveSelective", log);
	}
	/* 
	 * @Title: saveLoginSelective
	 * @Description: 新增操作日志信息
	 */
	public void saveLoginSelective(String content,String detail,Integer state,String userName){
		SysSystemLog log = new SysSystemLog();
		try {
			log.setCid(GetSequence.getSequenceByName(dao, SYS_SYSTEM_LOG));
			log .setOpeartionContent(content);
			log.setOpeartionDetail(detail);
			log.setOpeartionIp(PublicUtil.getIp());
			log.setOpeartionState(state);
			log.setOpeartionTime(DateUtil.getTime());
			log.setOpeartionUser(userName);
			log.setOpeartionType(ConstantsLog.LOG_0);//操作日志
			log.setErsionNum(TokenManager.getErsionNum());
			dao.save("SysSystemLogMapper.saveSelective", log);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @Title: quertByParam
	 * @Description: 多条件查询 所有
	 * @return: List<SysSystemLog>
	 */
	@SuppressWarnings("unchecked")
	public List<SysSystemLog> quertByParam(SysSystemLog bean) throws Exception {
		bean.setErsionNum(TokenManager.getErsionNum());
		return (List<SysSystemLog>) dao.findForList("SysSystemLogMapper.quertByParam",bean);
	}
	/**
	 * 
	 * @Title: removeByCids
	 * @Description: 根据ID批量删除
	 */
	public void removeByCids(Integer cids[]) throws Exception {
		dao.delete("SysSystemLogMapper.removeByCids",cids);
	}
	/**
	 * 
	 * @Title: removeByParam
	 * @Description: 根据条件批量删除
	 */
	public void removeByParam(SysSystemLog bean) throws Exception {
		bean.setErsionNum(TokenManager.getErsionNum());
		dao.delete("SysSystemLogMapper.removeByParam",bean);
	}
	
}
