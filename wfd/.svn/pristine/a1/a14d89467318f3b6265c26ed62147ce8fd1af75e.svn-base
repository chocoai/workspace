package com.whty.wfd.page.service.impl;

import com.whty.wfd.page.dao.TClassMapper;
import com.whty.wfd.page.dao.TClassPlateMapper;
import com.whty.wfd.page.dao.TClassUserMapper;
import com.whty.wfd.page.model.*;
import com.whty.wfd.page.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.List;

/**
 * \* User: zjd \* Date: 2018/8/16 \* Time: 11:42 \* Description: \
 */
@Service
public class ClassSeviceImpl implements ClassService {

	@Autowired
	private TClassMapper tClassMapper;
	@Autowired
	private TClassUserMapper tClassUserMapper;
	@Autowired
	private TClassPlateMapper tClassPlateMapper;

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public void createClass(TClass tClass, TClassUser tClassUser, TClassPlate tClassPlate) {
		try {
			tClassMapper.insert(tClass);
			tClassUserMapper.insert(tClassUser);
			tClassPlateMapper.insert(tClassPlate);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
	}

	@Override
	public void insertClassUser(Integer ClassId, Integer[] userId) {
		tClassUserMapper.insertMany(ClassId, userId, new Date());
	}

	@Override
	@Transactional(rollbackFor = { Exception.class })
	public void deleteClass(String ClassId) {
		try {
			tClassMapper.deleteByPrimaryKey(ClassId);
			TClassUserExample example = new TClassUserExample();
			example.createCriteria().andClassIdEqualTo(ClassId);
			tClassUserMapper.deleteByExample(example);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
	}

	@Override
	public void cancel(String ClassId, String PlateId) {
		TClassPlateExample example = new TClassPlateExample();
		example.createCriteria().andClassIdEqualTo(ClassId).andPlateIdEqualTo(PlateId);
		tClassPlateMapper.deleteByExample(example);
	}

	@Override
	public void relative(String ClassId, String PlateId) {
		TClassPlate tClassPlate = new TClassPlate();
		tClassPlate.setCreateTime(new Date());
		tClassPlate.setUpdateTime(new Date());
		// tClassPlate.setClassId(ClassId);
		// tClassPlate.setPlateId(PlateId);
		tClassPlateMapper.insert(tClassPlate);
	}

	@Override
	public List<TClass> getClassBy() {
		TClassExample example = new TClassExample();
		return tClassMapper.selectByExample(example);
	}
}