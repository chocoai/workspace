package com.yhcrt.healthcloud.organization.mapper;

import com.yhcrt.healthcloud.organization.entity.Employee;
import com.yhcrt.healthcloud.organization.entity.EmployeeExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface EmployeeMapper {
    long countByExample(EmployeeExample example);

    int deleteByExample(EmployeeExample example);

    int deleteByPrimaryKey(Integer empId);

    int insert(Employee record);

    int insertSelective(Employee record);

    List<Employee> selectByExample(EmployeeExample example);

    Employee selectByPrimaryKey(Integer empId);

    int updateByExampleSelective(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByExample(@Param("record") Employee record, @Param("example") EmployeeExample example);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);

    List<Employee> search(Map<String, Object> map);

    //根据排除empId判断identityCard在表中是否存在
	int countExtEmpId(Employee employee);

	//导入批量新增
	void batchAdd(List<Employee> list);

	//根据电话号码查询
	List<Employee> selectByPhoneNo(String phoneNo);
	
}