package com.yhcrt.iHealthCloud.mapper;

import com.yhcrt.iHealthCloud.entity.Doctor;
import com.yhcrt.iHealthCloud.entity.DoctorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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
    
    Doctor selectByUserId(Integer userId);
    
    int setProfilePhotoByUserId(@Param("userId") String userId, @Param("profilePhotoUrl") String profilePhotoUrl);
}