package com.fxzhj.mapper;

import java.util.List;

import com.fxzhj.model.Elevator;

public interface ElevatorMapper {

	//根据条件查询小区电梯
	List<Elevator> queryElevator(Elevator elevator);

	//新增小区电梯
	void add(Elevator elevator);

	//根据id修改电梯
	void update(Elevator elevator);

	//批量删除
	void batchDelete(int[] ids);

	//根据id查询二维码
	Elevator queryById(Long id);

	//批量查询电梯信息
	List<Elevator> queryByArrIds(int[] ids);

	//根据名称和二维码判断是否重复
	int countByNameOrCode(Elevator elevator);

	//根据名称和二维码并根据id排除本条数据 进行重复判断
	int countExId(Elevator elevator);

}