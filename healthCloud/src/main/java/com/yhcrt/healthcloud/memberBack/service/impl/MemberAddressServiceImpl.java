package com.yhcrt.healthcloud.memberBack.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yhcrt.healthcloud.memberBack.entity.MemberAddress;
import com.yhcrt.healthcloud.memberBack.mapper.MemberAddressMapper;
import com.yhcrt.healthcloud.memberBack.service.MemberAddressService;

/* @Description: 
 * @version	1.0		2017年8月22日
 * @author jimmy
*/
public class MemberAddressServiceImpl implements MemberAddressService{

    @Autowired
    private MemberAddressMapper memberAddressMapper;

    /* (non-Javadoc)
     * @see com.yhcrt.healthcloud.mall.service.MemberAddressService#selectAll()
     */
    @Override
    public List<MemberAddress> selectAll() {
        return memberAddressMapper.selectByExample(null);
    }

    /* (non-Javadoc)
     * @see com.yhcrt.healthcloud.mall.service.MemberAddressService#getMemberAddress(java.lang.String)
     */
    @Override
    public MemberAddress getMemberAddress(Integer cid) {
        return memberAddressMapper.selectByPrimaryKey(cid);
    }

    /* (non-Javadoc)
     * @see com.yhcrt.healthcloud.mall.service.MemberAddressService#addMemberAddress(com.yhcrt.healthcloud.mall.entity.MemberAddress)
     */
    @Override
    public Integer addMemberAddress(MemberAddress memberAddress) {
        return memberAddressMapper.insert(memberAddress);
    }

    /* (non-Javadoc)
     * @see com.yhcrt.healthcloud.mall.service.MemberAddressService#delMemberAddress(java.lang.String)
     */
    @Override
    public Integer delMemberAddress(Integer cid) {
        return memberAddressMapper.deleteByPrimaryKey(cid);
    }

    /* (non-Javadoc)
     * @see com.yhcrt.healthcloud.mall.service.MemberAddressService#updMemberAddress(com.yhcrt.healthcloud.mall.entity.MemberAddress)
     */
    @Override
    public Integer updMemberAddress(MemberAddress memberAddress) {
        return memberAddressMapper.updateByPrimaryKey(memberAddress);
    }

}
