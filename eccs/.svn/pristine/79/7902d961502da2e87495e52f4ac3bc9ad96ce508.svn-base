package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.smart.model.StepTemplete;
import com.smart.util.StringUtil;
import com.smart.dao.StepTempleteDao;

/**
 * StepTempleteService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class StepTempleteService {

	@Autowired
	private StepTempleteDao stepTempleteDao;

	/**
	 * 获取下一步
	 * 
	 * @param id
	 *            编审模板id
	 * @param current
	 *            当前步骤
	 * @return 返回下一个步骤编号，如果返回100，表示当前已经是最后一步
	 */
	public Integer getNext(int id, int current) {
		StepTemplete st = get(id);
		if (st == null) {
			return null;
		}

		String items = st.getStepItems();
		String[] itemArr = items.split(",");
		for (int i = 0; i < itemArr.length; i++) {
			String s = itemArr[i];
			if (StringUtil.isBlank(s)) {
				continue;
			}
			Integer count = Integer.parseInt(s);
			if (count == current) {
				if (i == itemArr.length - 1) {
					return 100;
				} else {
					return Integer.parseInt(itemArr[i + 1]);
				}
			}
		}
		return null;
	}

	/**
	 * 获取上一步
	 * 
	 * @param id
	 *            编审模板id
	 * @param current
	 *            当前步骤
	 * @return 返回下一个步骤编号，如果返回0，表示当前是第一步
	 */
	public Integer getBefore(int id, int current) {
		StepTemplete st = get(id);
		if (st == null) {
			return null;
		}

		String items = st.getStepItems();
		String[] itemArr = items.split(",");
		for (int i = 0; i < itemArr.length; i++) {
			String s = itemArr[i];
			if (StringUtil.isBlank(s)) {
				continue;
			}
			Integer count = Integer.parseInt(s);
			if (count == current) {
				if (i == 0) {
					return 0;
				} else {
					return Integer.parseInt(itemArr[i - 1]);
				}
			}
		}
		return null;
	}

	// ====================== 基本 C R U D 方法 ===========================
	public StepTemplete get(int id) {
		return stepTempleteDao.get(id);
	}

	public void save(StepTemplete entity) {
		stepTempleteDao.save(entity);
	}

	public void update(StepTemplete entity) {
		stepTempleteDao.update(entity);
	}

	public void delete(int id) {
		stepTempleteDao.delete(id);
	}

	public List<StepTemplete> getAll() {
		return stepTempleteDao.getAll();
	}

}
