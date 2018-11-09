package com.yhcrt.healthcloud.memberBack.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.yhcrt.healthcloud.memberBack.entity.MemberRecharge;
import com.yhcrt.healthcloud.memberBack.entity.MemberRechargeExample;

public interface MemberRechargeMapper {
    long countByExample(MemberRechargeExample example);

    int deleteByExample(MemberRechargeExample example);

    int insert(MemberRecharge record);

    int insertSelective(MemberRecharge record);

    List<MemberRecharge> selectByExample(MemberRechargeExample example);

    int updateByExampleSelective(@Param("record") MemberRecharge record, @Param("example") MemberRechargeExample example);

    int updateByExample(@Param("record") MemberRecharge record, @Param("example") MemberRechargeExample example);
}