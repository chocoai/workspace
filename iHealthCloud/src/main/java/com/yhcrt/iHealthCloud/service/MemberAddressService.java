package com.yhcrt.iHealthCloud.service;

import java.util.List;

import com.yhcrt.iHealthCloud.entity.MemberAddress;



/* @Description: 
 * @version	1.0		2017年8月21日
 * @author jimmy
*/
public interface MemberAddressService {

    List<MemberAddress> selectAll();
    MemberAddress getMemberAddress(Integer cid);
    Integer addMemberAddress(MemberAddress memberAddress);
    Integer delMemberAddress(Integer cid);
    Integer updMemberAddress(MemberAddress memberAddress);
    List<MemberAddress> selectByExample(Integer memberId);
    MemberAddress selectByIsDefault(Integer memberId);
    Integer updateByIsDefault(MemberAddress record);
}
