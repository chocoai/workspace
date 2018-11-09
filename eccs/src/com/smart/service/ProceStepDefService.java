/**
 * 
 */
package com.smart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.dao.ProceStepDefDao;
import com.smart.model.ProceStepDef;

/**
 * @Description:
 * @author raopanfeng
 * @date 2017年1月20日 下午4:45:38
 */
@Service
public class ProceStepDefService {

	@Autowired
	private ProceStepDefDao proceStepDefDao;

	public void save(ProceStepDef entity) {
		proceStepDefDao.save(entity);
	}

	public void delete(int id) {
		proceStepDefDao.delete(id);
	}

	public void update(ProceStepDef entity) {
		proceStepDefDao.update(entity);
	}

	public ProceStepDef get(int id) {
		return proceStepDefDao.get(id);
	}

	public List<ProceStepDef> getAll() {
		return proceStepDefDao.getAll();
	}

	public ProceStepDef getStepByStepCode(String stepCode) {
		return proceStepDefDao.getStepByStepCode(stepCode);
	}

	/**
	 * 返回满足要求的步骤集合
	 * 
	 * @param isRemove
	 *            是否剔除[项目管理-人员分配]或[投标管理-人员分配]
	 * @return: List<ProceStepDef>
	 */
	public List<ProceStepDef> getProjectByStep(String type, boolean isRemove) {
		List<ProceStepDef> psdList = proceStepDefDao.getProjectByStep(type);
		if (isRemove) {
			psdList.remove(0);// 剔除[项目管理-人员分配]或[投标管理-人员分配]这一步骤
		}
		return psdList;
	}

}
