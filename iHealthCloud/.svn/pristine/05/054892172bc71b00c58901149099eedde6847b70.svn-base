package com.yhcrt.iHealthCloud.mapper;

import com.yhcrt.iHealthCloud.entity.MedicalExaminationReport;
import com.yhcrt.iHealthCloud.entity.MedicalExaminationReportExample;
import com.yhcrt.iHealthCloud.entity.MedicalExaminationReportUnion;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MedicalExaminationReportMapper {
	
    long countByExample(MedicalExaminationReportExample example);

    int deleteByExample(MedicalExaminationReportExample example);

    int deleteByPrimaryKey(Integer merId);

    int insert(MedicalExaminationReport record);

    int insertSelective(MedicalExaminationReport record);

    List<MedicalExaminationReport> selectByExample(MedicalExaminationReportExample example);

    MedicalExaminationReportUnion getMedicalExaminationReportUnionById(Integer merId);
    
    MedicalExaminationReport selectByPrimaryKey(Integer merId);

    int updateByExampleSelective(@Param("record") MedicalExaminationReport record, @Param("example") MedicalExaminationReportExample example);

    int updateByExample(@Param("record") MedicalExaminationReport record, @Param("example") MedicalExaminationReportExample example);

    int updateByPrimaryKeySelective(MedicalExaminationReport record);

    int updateByPrimaryKey(MedicalExaminationReport record);
}