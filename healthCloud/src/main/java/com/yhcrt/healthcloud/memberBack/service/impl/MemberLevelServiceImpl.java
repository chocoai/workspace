package com.yhcrt.healthcloud.memberBack.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yhcrt.healthcloud.memberBack.entity.MemberLevel;
import com.yhcrt.healthcloud.memberBack.mapper.MemberLevelMapper;
import com.yhcrt.healthcloud.memberBack.service.MemberLevelService;

/* @Description: 
 * @version	1.0		2017年8月22日
 * @author jimmy
*/
public class MemberLevelServiceImpl implements MemberLevelService {

    @Autowired
    private MemberLevelMapper memberLevelMapper;

    /* (non-Javadoc)
     * @see com.yhcrt.healthcloud.mall.service.MemberLevelService#selectAll()
     */
    @Override
    public List<MemberLevel> selectAll() {
        return memberLevelMapper.selectByExample(null);
    }

    /* (non-Javadoc)
     * @see com.yhcrt.healthcloud.mall.service.MemberLevelService#getMemberLevel(java.lang.String)
     */
    @Override
    public MemberLevel getMemberLevel(Integer cid) {
        return memberLevelMapper.selectByPrimaryKey(cid);
    }

    /* (non-Javadoc)
     * @see com.yhcrt.healthcloud.mall.service.MemberLevelService#addMemberLevel(com.yhcrt.healthcloud.mall.entity.MemberLevel)
     */
    @Override
    public Integer addMemberLevel(MemberLevel memberLevel) {
        return memberLevelMapper.insert(memberLevel);
    }

    /* (non-Javadoc)
     * @see com.yhcrt.healthcloud.mall.service.MemberLevelService#delMemberLevel(java.lang.String)
     */
    @Override
    public Integer delMemberLevel(Integer cid) {
        return memberLevelMapper.deleteByPrimaryKey(cid);
    }

    /* (non-Javadoc)
     * @see com.yhcrt.healthcloud.mall.service.MemberLevelService#updMemberLevel(com.yhcrt.healthcloud.mall.entity.MemberLevel)
     */
    @Override
    public Integer updMemberLevel(MemberLevel memberLevel) {
        return memberLevelMapper.updateByPrimaryKey(memberLevel);
    }

}
