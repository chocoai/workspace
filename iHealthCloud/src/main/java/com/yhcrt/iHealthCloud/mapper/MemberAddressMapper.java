package com.yhcrt.iHealthCloud.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yhcrt.iHealthCloud.entity.MemberAddress;
import com.yhcrt.iHealthCloud.entity.MemberAddressExample;


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
    
    MemberAddress getDefaultAddress(Integer memberId);
    
    MemberAddress selectByIsDefault(MemberAddressExample example);
    
    int updateByIsDefault(MemberAddress record);
    
}