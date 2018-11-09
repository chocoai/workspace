package com.whty.assis.demo.dao;

import java.util.List;
import java.util.Map;

import com.whty.assis.base.dao.IBaseDao;
import com.whty.assis.demo.model.Ebp_licence_org;

/**
 * 电子书包授权机构信息dao
 * 
 * @author Administrator
 *
 */
public interface LicenceOrgDao extends IBaseDao<Ebp_licence_org> {

	/* 新增授权机构信息 */
	public void saveLicenceOrg(Ebp_licence_org licence_org);

	/* 删除授权机构信息 */
	public void deleteLicenceOrg(List list);

	/* 修改授权机构信息 */
	public void updateLicenceOrg(Ebp_licence_org licence_org);

	/* 查询授权机构 */
	public List<Ebp_licence_org> queryLicenceOrg(Map<String, Object> paramMap);

	public void countLicenceCode(Map<String, Object> param);

	public List<Map> getLicenceCount(Map<String, Object> countParam);
}
