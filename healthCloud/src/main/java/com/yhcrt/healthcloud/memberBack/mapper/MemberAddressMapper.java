package com.yhcrt.healthcloud.memberBack.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.yhcrt.healthcloud.memberBack.entity.MemberAddress;
import com.yhcrt.healthcloud.memberBack.entity.MemberAddressExample;

public interface MemberAddressMapper {
    long countByExample(MemberAddressExample example);

    int deleteByExample(MemberAddressExample example);

    int deleteByPrimaryKey(Integer cid);

    int insert(MemberAddress record);

    int insertSelective(MemberAddress record);

    List<MemberAddress> selectByExample(MemberAddressExample example);

    MemberAddress selectByPrimaryKey(Integer cid);

    int updateByExampleSelective(@Param("record") MemberAddress record, @Param("example") MemberAddressExample example);

    int updateByExample(@Param("record") MemberAddress record, @Param("example") MemberAddressExample example);

    int updateByPrimaryKeySelective(MemberAddress record);

    int updateByPrimaryKey(MemberAddress record);
}