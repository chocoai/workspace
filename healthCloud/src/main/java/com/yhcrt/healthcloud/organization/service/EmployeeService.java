package com.yhcrt.healthcloud.organization.service;

import java.util.List;
import java.util.Map;

import com.yhcrt.healthcloud.organization.entity.Employee;
import com.yhcrt.healthcloud.system.entity.SysUser;
import com.yhcrt.healthcloud.system.entity.UserRole;

/* @Description: 员工SERVICE层接口类
 * @version	1.0		2017年5月9日
 * @author jimmy
 */
public interface EmployeeService {

	Integer insert(Employee emp, SysUser user);

	Integer delete(Employee emp, SysUser user);

	Integer recover(Employee emp, SysUser user);

	Integer update(Employee emp);

	Employee select(Integer empId);

	List<Employee> selectByUserId(Integer userId);

	Integer updateEmp(Employee emp);

	List<Employee> selectByIdCard(String idCard);

	// 根据排除empId判断identityCard在表中是否存在
	int countExtEmpId(Employee employee);

	// 导入批量新增
	void batchAdd(List<SysUser> userList, List<Employee> empList, List<UserRole> roleList);

	// 分页查询
	List<Employee> queryList(Map<String, Object> map);

}
