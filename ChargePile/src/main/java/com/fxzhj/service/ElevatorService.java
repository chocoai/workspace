package com.fxzhj.service;

import java.util.List;

import com.fxzhj.model.Elevator;

public interface ElevatorService {

	//根据条件查询小区电梯
	List<Elevator> queryElevator(Elevator elevator);

	//新增小区电梯
	void add(Elevator elevator);

	//根据id修改电梯
	void update(Elevator elevator);

	//批量删除
	void batchDelete(int[] idArr);

	//根据名称和二维码判断是否重复
	int countByNameOrCode(Elevator elevator);

	//根据名称和二维码并根据id排除本条数据 进行重复判断
	int countExId(Elevator elevator);

}
