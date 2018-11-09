package com.yhcrt.iHealthCloud.mapper;

import com.yhcrt.iHealthCloud.entity.DoctorProposal;
import com.yhcrt.iHealthCloud.entity.DoctorProposalExample;
import com.yhcrt.iHealthCloud.pojo.DoctorProposalItem;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DoctorProposalMapper {
    long countByExample(DoctorProposalExample example);

    int deleteByExample(DoctorProposalExample example);

    int deleteByPrimaryKey(Integer cid);

    int insert(DoctorProposal record);

    int insertSelective(DoctorProposal record);

    List<DoctorProposal> selectByExample(DoctorProposalExample example);
    
    /**
     * 获取某会员的所有医师建议
     * @param memberId
     * @return
     */
    List<DoctorProposalItem> listDoctorProposal(String memberId);
    
    DoctorProposalItem getDoctorProposalById(Integer Id);

    DoctorProposal selectByPrimaryKey(Integer cid);

    int updateByExampleSelective(@Param("record") DoctorProposal record, @Param("example") DoctorProposalExample example);

    int updateByExample(@Param("record") DoctorProposal record, @Param("example") DoctorProposalExample example);

    int updateByPrimaryKeySelective(DoctorProposal record);

    int updateByPrimaryKey(DoctorProposal record);
}