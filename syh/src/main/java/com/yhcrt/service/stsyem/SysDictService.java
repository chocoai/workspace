
package com.yhcrt.service.stsyem;

import static com.yhcrt.utils.Constants.SYS_LIST_DAYTA;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yhcrt.dao.DaoSupport;
import com.yhcrt.entity.system.SysListData;
import com.yhcrt.utils.GetSequence;

/**
 * 系统用户的service
 * @author 陈伟
 * 2017年5月23日 下午1:11:02
 * 版权所有：武汉炎黄创新服务有限公司
 */
@Service("sysDictService")
public class SysDictService {
	@Resource
	private DaoSupport dao;
	
	
	/**
	 * @Title: saveSelective
	 * @Description: 插入信息
	 * @return: void
	 */
	public void saveSelective(SysListData sysListData) throws Exception{
		int id = GetSequence.getSequenceByName(dao, SYS_LIST_DAYTA);
		sysListData.setCid(id);
		dao.update("SysListDataMapper.saveSelective", sysListData);
	}
	/**
	 * @Title: updateByPrimaryKeySelective
	 * @Description: 更新信息
	 * @return: void
	 */
	public void updateByPrimaryKeySelective(SysListData sysListData) throws Exception{

		SysListData bean = (SysListData) dao.findForObject("SysListDataMapper.getByCid", sysListData.getCid());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("itemType1", bean.getItemValue());
		map.put("itemType2", sysListData.getItemValue());
		dao.update("SysListDataMapper.updateType", map);
		dao.update("SysListDataMapper.updateByPrimaryKeySelective", sysListData);
	}
	/**
	 * 
	 * @Title: getByUsreCid
	 * @Description: 根据ID查询
	 * @return: SysListData
	 */
	public SysListData getByCid(Integer cid) throws Exception {
		return (SysListData) dao.findForObject("SysListDataMapper.getByCid", cid);
	}
	/**
	 * 
	 * @Title: queryParam
	 * @Description: 根据字典类型查询
	 * @return: SysListData
	 */
	public List<SysListData> selectByItemType(String itemType) throws Exception {
		SysListData sysListData = new SysListData();
		sysListData.setItemType(itemType);
		return (List<SysListData>) dao.findForList("SysListDataMapper.selectByItemType", sysListData);
	}
	/**
	 * 
	 * @Title: queryParam
	 * @Description: 根据字典类型查询返回显示值
	 * @return: SysListData
	 */
	public List<String> selectByItemKey(String itemType) throws Exception {
		return (List<String> ) dao.findForList("SysListDataMapper.selectByItemKey", itemType);
	}
	
	/**
	 * 
	 * @Title: removeByCid
	 * @Description: 根据ID删除
	 * @return: SysListData
	 */
	public SysListData removeByCid(Integer cid) throws Exception {
		return (SysListData) dao.findForObject("SysListDataMapper.removeByCid", cid);
	}
	/**
	 * @Title: deleteAll
	 * @Description: 批量删除
	 * @return: void
	 * @throws Exception 
	 */
	public void deleteAll(Integer[] cids) throws Exception {
		dao.findForObject("SysListDataMapper.removeByCids", cids);
	}
	/**
	 * @Title: queryParam
	 * @Description: 判断字典的字典值是否重复录入
	 * @return: void
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public boolean queryParam(Integer cid,String itemType,String itemValue) throws Exception {
		boolean isSuccess = false;
		SysListData info = new SysListData(itemType,itemValue);
		List<SysListData> list = (List<SysListData>) dao.findForList("SysListDataMapper.quertByParam",info);
		if(cid == null){
			if(list.size()==0){
				isSuccess = true;
			}
		}else{
			if(list.size()==0 || (list.size()==1 && list.get(0).getCid().equals(cid))){
				isSuccess = true;
			}
		}
		return isSuccess;
	}
}
