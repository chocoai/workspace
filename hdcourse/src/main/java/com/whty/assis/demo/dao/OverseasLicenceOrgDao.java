/**
 * 
 */
package com.whty.assis.demo.dao;

import java.util.List;
import java.util.Map;

import com.whty.assis.base.dao.IBaseDao;
import com.whty.assis.demo.model.Overseas_Ebp_licence_org;

/**
 * @author zhangzheng
 * @date 2018年5月17日
 */
public interface OverseasLicenceOrgDao extends IBaseDao<Overseas_Ebp_licence_org> {
	/* 新增授权机构信息 */
	public void saveLicenceOrg(Overseas_Ebp_licence_org licence_org);

	/* 删除授权机构信息 */
	public void deleteLicenceOrg(List list);

	/* 修改授权机构信息 */
	public void updateLicenceOrg(Overseas_Ebp_licence_org licence_org);

	/* 查询授权机构 */
	public List<Overseas_Ebp_licence_org> queryLicenceOrg(Map<String, Object> paramMap);

	public void countLicenceCode(Map<String, Object> param);

	public List<Map> getLicenceCount(Map<String, Object> countParam);
}
