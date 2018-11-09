package com.yhcrt.iHealthCloud.service.impl;

import java.util.List;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhcrt.iHealthCloud.entity.MemberAddress;
import com.yhcrt.iHealthCloud.entity.MemberAddressExample;
import com.yhcrt.iHealthCloud.mapper.MemberAddressMapper;
import com.yhcrt.iHealthCloud.service.MemberAddressService;


/* @Description: 
 * @version	1.0		2017年8月22日
 * @author jimmy
*/
@Service	
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

    @Override
	public List<MemberAddress> selectByExample(Integer memberId) {
		MemberAddressExample example = new MemberAddressExample();
		example.createCriteria().andMemberIdEqualTo(memberId).andStatusEqualTo(0);
		return memberAddressMapper.selectByExample(example);
	}

	@Override
	public MemberAddress selectByIsDefault(Integer memberId) {
		MemberAddressExample example = new MemberAddressExample();
		example.createCriteria().andMemberIdEqualTo(memberId).andIsDefaultEqualTo(1).andStatusEqualTo(0);
		return memberAddressMapper.selectByIsDefault(example);
	}

	@Override
	public Integer updateByIsDefault(MemberAddress record) {
		return memberAddressMapper.updateByIsDefault(record);
	}
}
