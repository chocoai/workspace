package com.yhcrt.healthcloud.health.mapper;

import com.yhcrt.healthcloud.health.entity.HdStep;
import com.yhcrt.healthcloud.health.entity.HdStepExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HdStepMapper {
    long countByExample(HdStepExample example);

    int deleteByExample(HdStepExample example);

    int deleteByPrimaryKey(Integer cid);

    int insert(HdStep record);

    int insertSelective(HdStep record);

    List<HdStep> selectByExample(HdStepExample example);

    HdStep selectByPrimaryKey(Integer cid);

    int updateByExampleSelective(@Param("record") HdStep record, @Param("example") HdStepExample example);

    int updateByExample(@Param("record") HdStep record, @Param("example") HdStepExample example);

    int updateByPrimaryKeySelective(HdStep record);

    int updateByPrimaryKey(HdStep record);
    
    HdStep selectLatestByMemberId(int memberId);
    
    List<HdStep> selectByMon(Integer memberId);//最近一个月的数据

	List<HdStep> selectCountStep(HdStepExample example);
	
}