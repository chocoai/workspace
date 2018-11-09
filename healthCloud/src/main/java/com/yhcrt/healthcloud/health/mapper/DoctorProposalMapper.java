package com.yhcrt.healthcloud.health.mapper;

import com.yhcrt.healthcloud.health.entity.DoctorProposal;
import com.yhcrt.healthcloud.health.entity.DoctorProposalExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DoctorProposalMapper {
    long countByExample(DoctorProposalExample example);

    int deleteByExample(DoctorProposalExample example);

    int deleteByPrimaryKey(Integer cid);

    int insert(DoctorProposal record);

    int insertSelective(DoctorProposal record);

    List<DoctorProposal> selectByExample(DoctorProposalExample example);

    DoctorProposal selectByPrimaryKey(Integer cid);

    int updateByExampleSelective(@Param("record") DoctorProposal record, @Param("example") DoctorProposalExample example);

    int updateByExample(@Param("record") DoctorProposal record, @Param("example") DoctorProposalExample example);

    int updateByPrimaryKeySelective(DoctorProposal record);

    int updateByPrimaryKey(DoctorProposal record);
    
    List<DoctorProposal> selectAdvice(Integer memberId);//根据memberid查所有次ID的医师建议
    
}