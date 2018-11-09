package com.yhcrt.iHealthCloud.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yhcrt.iHealthCloud.entity.Employee;
import com.yhcrt.iHealthCloud.entity.EmployeeExample;
import com.yhcrt.iHealthCloud.pojo.OrgServicer;

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

    int updateByPrimaryKeySelective(Employee emp);

    int updateByPrimaryKey(Employee emp);

    List<Employee> search(Map<String, Object> map);
    
    Employee selectByUserId(Integer empId);

    int setProfilePhotoByUserId(@Param("userId") String userId, @Param("profilePhotoUrl") String profilePhotoUrl);
    
    List<OrgServicer> getOrgServicersByExample(EmployeeExample example);
}