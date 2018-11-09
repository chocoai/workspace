package com.yhcrt.healthcloud.organization.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yhcrt.healthcloud.organization.entity.Doctor;
import com.yhcrt.healthcloud.organization.entity.DoctorExample;

public interface DoctorMapper {
	
    long countByExample(DoctorExample example);

    int deleteByExample(DoctorExample example);

    int deleteByPrimaryKey(Integer docId);

    int insert(Doctor record);

    int insertSelective(Doctor record);

    List<Doctor> selectByExample(DoctorExample example);

    Doctor selectByPrimaryKey(Integer docId);

    int updateByExampleSelective(@Param("record") Doctor record, @Param("example") DoctorExample example);

    int updateByExample(@Param("record") Doctor record, @Param("example") DoctorExample example);

    int updateByPrimaryKeySelective(Doctor record);

    int updateByPrimaryKey(Doctor record);

    List<Doctor> search(Map<String, Object> map);

    //根据排除docId判断identityCard在表中是否存在
	int countExtDocId(Doctor doc);

	//导入批量新增
	void batchAdd(List<Doctor> list);

	//根据电话号码查询
	List<Doctor> selectByPhoneNo(String phoneNo);
	
}