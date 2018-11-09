package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.smart.model.DesignStage;
import com.smart.dao.DesignStageDao;

/**
 * DesignStageService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class DesignStageService {

	@Autowired
	private DesignStageDao designStageDao;

	// ====================== 基本 C R U D 方法 ===========================
	public DesignStage get(int id) {
		return designStageDao.get(id);
	}

	public void save(DesignStage entity) {
		designStageDao.save(entity);
	}

	public void update(DesignStage entity) {
		designStageDao.update(entity);
	}

	public void delete(int id) {
		designStageDao.delete(id);
	}

	public List<DesignStage> getAll() {
		return designStageDao.getAll();
	}

}
