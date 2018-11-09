package com.whty.assis.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;

import com.whty.assis.demo.model.Ebp_licence_org;
import com.whty.page.util.HandlerResult;

/**
 * 授权机构信息service
 * 
 * @author Administrator
 *
 */
public interface LicenceOrgService {
	/* 新增授权机构信息 */
	public void saveLicenceOrg(Ebp_licence_org licence_org);

	/* 删除授权机构信息 */
	public void deleteLicenceOrg(List list);

	/* 修改授权机构信息 */
	public void updateLicenceOrg(Ebp_licence_org licence_org);

	/* 查询授权机构 */
	public List<Ebp_licence_org> queryLicenceOrg(Map<String, Object> paramMap);

	/* 查询授权机构（带分页） */
	public HandlerResult queryLicenceOrgPage(Map<String, Object> paramMap);

	public void countLicenceCode(Map<String, Object> param);

	public List<Map> getLicenceCount(Map<String, Object> countParam);

	public void setCountDataTOModel(Model model);
}
