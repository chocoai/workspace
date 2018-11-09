package com.yhcrt.healthcloud.memberBack.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhcrt.healthcloud.memberBack.entity.MemberBack;
import com.yhcrt.healthcloud.memberBack.entity.MemberContactExample;
import com.yhcrt.healthcloud.memberBack.entity.MemberDeviceBack;
import com.yhcrt.healthcloud.memberBack.entity.MemberDeviceExampleBack;
import com.yhcrt.healthcloud.memberBack.entity.MemberExampleBack;
import com.yhcrt.healthcloud.memberBack.entity.MemberExampleBack.Criteria;
import com.yhcrt.healthcloud.memberBack.entity.MemberInfo;
import com.yhcrt.healthcloud.memberBack.entity.MemberRelationshipBack;
import com.yhcrt.healthcloud.memberBack.entity.MemberRelationshipExampleBack;
import com.yhcrt.healthcloud.memberBack.mapper.MemberBackMapper;
import com.yhcrt.healthcloud.memberBack.mapper.MemberContactMapper;
import com.yhcrt.healthcloud.memberBack.mapper.MemberDeviceBackMapper;
import com.yhcrt.healthcloud.memberBack.mapper.MemberInfoMapper;
import com.yhcrt.healthcloud.memberBack.mapper.MemberRelationshipBackMapper;
import com.yhcrt.healthcloud.memberBack.service.MemberBackService;
import com.yhcrt.healthcloud.system.entity.SysUser;
import com.yhcrt.healthcloud.system.mapper.SysUserMapper;
import com.yhcrt.healthcloud.util.IDCardUtil;
import com.yhcrt.healthcloud.util.StringUtil;

/* @Description: 会员管理SERVICE层实现类
 * @version	1.0		2017年5月22日
 * @author jimmy
*/
@Service
@Transactional
public class MemberBackServiceImpl implements MemberBackService{
    
    @Autowired
    private MemberBackMapper memberBackMapper;
    @Autowired
    private MemberDeviceBackMapper memberDeviceBackMapper;
    @Autowired
    private MemberRelationshipBackMapper memberRelationshipBackMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private MemberInfoMapper memberInfoMapper;
    @Autowired
    private MemberContactMapper memberContactMapper;

    /* 查询机构下所有会员
     * @see com.yhcrt.healthcloud.memberBack.service.MemberService#selAll(java.util.List, int)
     */
    @Override
    public List<MemberBack> selAll(List<Integer> orgId_list, Integer status) {
        MemberExampleBack example = new MemberExampleBack();
        Criteria criteria = example.createCriteria();
        if(status!=null){
            criteria.andStatusEqualTo(status);
        }
        if(orgId_list!=null){
            criteria.andOrgIdIn(orgId_list);
        }
        example.setOrderByClause("update_time desc,member_id desc");
        return memberBackMapper.selectByExample(example);
    }

    /* 根据条件搜索会员
     * @see com.yhcrt.healthcloud.memberBack.service.MemberService#search(java.util.List, java.lang.String, java.lang.String, java.lang.Object, int)
     */
    @Override
    public List<MemberBack> search(List<Integer> orgId_list, String realName, String imei, String identity_card, Integer status) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("orgId_list", orgId_list);
        map.put("realName", realName);
        map.put("imei", imei);
        map.put("identity_card", identity_card);
        map.put("status", status==null?"0":status);
        return memberBackMapper.search(map);
    }

    /* 根据主键查询会员
     * @see com.yhcrt.healthcloud.memberBack.service.MemberService#select(java.lang.Integer)
     */
    @Override
    public MemberBack select(Integer memberId) {
        return memberBackMapper.selectByPrimaryKey(memberId);
    }

    /* 删除会员(禁用)
     * @see com.yhcrt.healthcloud.memberBack.service.MemberService#delete(com.yhcrt.healthcloud.memberBack.entity.Member, com.yhcrt.healthcloud.system.entity.SysUser)
     */
    @Override
    public Integer delete(MemberBack member, SysUser user) {
        member.setStatus(1);
        user.setStatus(1);
        int i = 0;
        try{
            //这里开始捕获异常，因为要让upd方法回滚，所以要写在try里面
            i = memberBackMapper.updateByPrimaryKey(member);
            //这里写的判定条件是故意让其抛出异常，检验事务回滚功能的
            if(i<1){
                throw new Exception();
            }else{
                int e = sysUserMapper.updateByPrimaryKeySelective(user);
                if(e<1){
                    i = 0;
                    throw new Exception();
                }
            }
        }catch (Exception e){
            //捕捉到上面抛出的异常后这里抛出一个RuntimeException异常，这个异常会被spring的aop捕获，然后发生回滚。
            throw new RuntimeException();
        }
        return i;
    }

    /* 恢复会员(取消禁用)
     * @see com.yhcrt.healthcloud.memberBack.service.MemberService#recover(com.yhcrt.healthcloud.memberBack.entity.Member, com.yhcrt.healthcloud.system.entity.SysUser)
     */
    @Override
    public Integer recover(MemberBack member, SysUser user) {
        member.setStatus(0);
        user.setStatus(0);
        int i = 0;
        try{
            //这里开始捕获异常，因为要让upd方法回滚，所以要写在try里面
            i = memberBackMapper.updateByPrimaryKey(member);
            //这里写的判定条件是故意让其抛出异常，检验事务回滚功能的
            if(i<1){
                throw new Exception();
            }else{
                int e = sysUserMapper.updateByPrimaryKeySelective(user);
                if(e<1){
                    i = 0;
                    throw new Exception();
                }
            }
        }catch (Exception e){
            //捕捉到上面抛出的异常后这里抛出一个RuntimeException异常，这个异常会被spring的aop捕获，然后发生回滚。
            throw new RuntimeException();
        }
        return i;
    }

    /* 查询会员的终端设备
     * @see com.yhcrt.healthcloud.memberBack.service.MemberBackService#deviceList(java.lang.Integer)
     */
    @Override
    public List<MemberDeviceBack> selectDeviceByMemberId(Integer memberId) {
        MemberDeviceExampleBack example = new MemberDeviceExampleBack();
        example.createCriteria().andMemberIdEqualTo(memberId);
        return memberDeviceBackMapper.selectByExample(example);
    }

    /* 查询该会员关注的会员
     * @see com.yhcrt.healthcloud.memberBack.service.MemberBackService#selectMemberFollow(java.lang.Integer)
     */
    @Override
    public List<MemberRelationshipBack> selectMemberFollow(Integer memberId) {
        MemberRelationshipExampleBack example = new MemberRelationshipExampleBack();
        if(memberId!=null){
            example.createCriteria().andFollowerIdEqualTo(memberId);
        }
        return memberRelationshipBackMapper.selectByExample(example);
    }

    /* 查询关注该会员的会员
     * @see com.yhcrt.healthcloud.memberBack.service.MemberBackService#selectFollowmember(java.lang.Integer)
     */
    @Override
    public List<MemberRelationshipBack> selectFollowMember(Integer memberId) {
        MemberRelationshipExampleBack example = new MemberRelationshipExampleBack();
        if(memberId!=null){
            example.createCriteria().andMemberIdEqualTo(memberId);
        }
        return memberRelationshipBackMapper.selectByExample(example);
    }

    /* 根据员工ID查询会员(即查询该员工服务的会员)
     * @see com.yhcrt.healthcloud.memberBack.service.MemberBackService#selectByEmpId(java.lang.Integer)
     */
    @Override
    public List<MemberBack> selectByEmpId(Integer empId,Integer status) {
        MemberExampleBack example = new MemberExampleBack();
        Criteria criteria = example.createCriteria();
        criteria.andEmpIdEqualTo(empId);
        criteria.andStatusEqualTo(status);
        return memberBackMapper.selectByExample(example);
    }
    
    /* 根据医师ID查询会员(即查询该医师服务的会员)
     * @see com.yhcrt.healthcloud.memberBack.service.MemberBackService#selectByEmpId(java.lang.Integer)
     */
    @Override
    public List<MemberBack> selectByDocId(Integer DocId,Integer status) {
        MemberExampleBack example = new MemberExampleBack();
        Criteria criteria = example.createCriteria();
        criteria.andDocIdEqualTo(DocId);
        criteria.andStatusEqualTo(status);
        return memberBackMapper.selectByExample(example);
    }

    /* 查询待分配的会员
     * @see com.yhcrt.healthcloud.memberBack.service.MemberBackService#selectByEmpIdEqualToNull(java.lang.Integer)
     */
    @Override
    public List<MemberBack> selectByDocIdEqualToNull(Integer orgId,String realName,String phoneNo,Integer status) {
        MemberExampleBack example = new MemberExampleBack();
        Criteria criteria = example.createCriteria();
        criteria.andOrgIdEqualTo(orgId);
        criteria.andDocIdIsNull();
        criteria.andStatusEqualTo(status);
        if(!StringUtil.isEmpty(realName)){
            criteria.andRealNameLike("%"+realName+"%");
        }
        if(!StringUtil.isEmpty(phoneNo)){
            criteria.andPhoneNoLike("%"+phoneNo+"%");
        }
        return memberBackMapper.selectByExample(example);
    }
    
    // 更新会员信息
    @Override
    public Integer update(MemberBack member) {
        return memberBackMapper.updateByPrimaryKeySelective(member);
    }
    
    //修改会员信息和详细信息
    @Override
    public void updateByMemberAndInfo(MemberBack member) {
    	//先根据memberId删除数据
    	MemberContactExample example = new MemberContactExample();
    	example.createCriteria().andMemberIdEqualTo(member.getMemberId());
    	memberContactMapper.deleteByExample(example);
    	//然后批量新增
    	if(member.getContactList() != null && member.getContactList().size() > 0){
    		memberContactMapper.addBatch(member.getContactList());
    	}
    	memberBackMapper.updateByPrimaryKeySelective(member);
    	MemberInfo info = member.getMemberInfo();
    	info.setMemberId(member.getMemberId());
    	info.setAge(IDCardUtil.IdNOToAge(member.getIdentityCard()));
    	info.setBirthday(IDCardUtil.getBirthdayFromPersonIDCode(member.getIdentityCard()));
    	if(info.getCid() != null){
        	memberInfoMapper.updateByMemberInfo(info);
    	}else{
    		memberInfoMapper.insert(info);
    	}
    	
    }

    //查询未绑定机构的会员
	@Override
	public List<MemberBack> queryByNameAndCard(Map<String, String> map) {
		return memberBackMapper.queryByNameAndCard(map);
	}

	//查询该员工所属机构及旗下机构中可分配的会员
	@Override
	public List<MemberBack> queryByEid(Map<String, Object> map) {
		return memberBackMapper.queryByEid(map);
	}

	//查询该医师所属机构及旗下机构中可分配的或已分配的会员
	@Override
	public List<MemberBack> selectByDid(Map<String, Object> map) {
		return memberBackMapper.queryByDid(map);
	}

	//员工批量分配或移除会员
	@Override
	public Integer updateBatchEid(Map<String, Object> map) {
		return memberBackMapper.updateBatchEid(map);
	}

	//医师批量分配或移除会员
	@Override
	public Integer updateBatchDid(Map<String, Object> map) {
		return memberBackMapper.updateBatchDid(map);
	}

	//根据memberId查询会员个人信息
	@Override
	public MemberBack queryByMemberId(Integer memberId) {
		return memberBackMapper.queryByMemberId(memberId);
	}
	
	//根据手机号查询会员个人信息
	@Override
	public MemberBack queryByTel(String tel) {
		return memberBackMapper.queryByTel(tel);
	}

	//为用户服务的人员
	@Override
	public List<MemberBack> getByMemberId(Integer memberId) {
		return memberBackMapper.getByMemberId(memberId);
	}

	//移除分配的会员
	@Override
	public Integer updateByMember(MemberBack member) {
		return memberBackMapper.updateByPrimaryKey(member);
	}

	//不管是分配还是移除 首先将会员表中empId为空
	@Override
	public Integer updateByEmpId(Map<String, Object> map) {
		return memberBackMapper.updateByEmpId(map);
	}

	//不管是分配还是移除 首先将会员表中docId清空 
	@Override
	public Integer updateByDocId(Map<String, Object> map) {
		return memberBackMapper.updateByDocId(map);
	}

}
