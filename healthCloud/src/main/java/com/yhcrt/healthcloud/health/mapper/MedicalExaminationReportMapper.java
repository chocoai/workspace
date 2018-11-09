package com.yhcrt.healthcloud.health.mapper;

import com.yhcrt.healthcloud.health.entity.MedicalExaminationReport;
import com.yhcrt.healthcloud.health.entity.MedicalExaminationReportExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MedicalExaminationReportMapper {
    long countByExample(MedicalExaminationReportExample example);

    int deleteByExample(MedicalExaminationReportExample example);

    int deleteByPrimaryKey(Integer merId);

    int insert(MedicalExaminationReport record);

    int insertSelective(MedicalExaminationReport record);

    List<MedicalExaminationReport> selectByExample(MedicalExaminationReportExample example);

    MedicalExaminationReport selectByPrimaryKey(Integer merId);

    int updateByExampleSelective(@Param("record") MedicalExaminationReport record, @Param("example") MedicalExaminationReportExample example);

    int updateByExample(@Param("record") MedicalExaminationReport record, @Param("example") MedicalExaminationReportExample example);

    int updateByPrimaryKeySelective(MedicalExaminationReport record);

    int updateByPrimaryKey(MedicalExaminationReport record);
    
    List<MedicalExaminationReport> selectByMemberId(Integer memberId);
}