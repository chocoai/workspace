package com.yhcrt.healthcloud.organization.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhcrt.healthcloud.organization.entity.Doctor;
import com.yhcrt.healthcloud.organization.entity.DoctorExample;
import com.yhcrt.healthcloud.organization.mapper.DoctorMapper;
import com.yhcrt.healthcloud.organization.service.DoctorService;
import com.yhcrt.healthcloud.system.entity.SysUser;
import com.yhcrt.healthcloud.system.entity.UserRole;
import com.yhcrt.healthcloud.system.mapper.SysUserMapper;
import com.yhcrt.healthcloud.system.mapper.UserRoleMapper;

/* @Description: 医师SERVICE层实现类
 * @version	1.0		2017年5月9日
 * @author jimmy
*/
@Service
@Transactional
public class DoctorServiceImpl implements DoctorService{
    
    @Autowired
    private DoctorMapper doctorMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    /* 添加医师
     * @see com.yhcrt.healthcloud.dao.DoctorDao#insert(com.yhcrt.healthcloud.entity.Doctor)
     */
    @Override
    public Integer insert(Doctor doc,SysUser sysUser) {
        int i = 0;
        try{
            //这里开始捕获异常，因为要让upd方法回滚，所以要写在try里面
            i = sysUserMapper.insert(sysUser);
            //这里写的判定条件是故意让其抛出异常，检验事务回滚功能的
            if(i<1){
                throw new Exception();
            }else{
                int e = doctorMapper.insert(doc);
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
        
    /* 删除医师(禁用医师)
     * @see com.yhcrt.healthcloud.dao.DoctorDao#delete(java.lang.String)
     */
    @Override
    public Integer delete(Doctor doc,SysUser user) {
        int i = 0;
        try{
            //这里开始捕获异常，因为要让upd方法回滚，所以要写在try里面
            i = doctorMapper.updateByPrimaryKey(doc);
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
    
    /* 恢复医师(取消禁用)
     * @see com.yhcrt.healthcloud.dao.DoctorDao#recover(com.yhcrt.healthcloud.entity.Doctor)
     */
    @Override
    public Integer recover(Doctor doc,SysUser user) {
        int i = 0;
        try{
            //这里开始捕获异常，因为要让upd方法回滚，所以要写在try里面
            i = doctorMapper.updateByPrimaryKey(doc);
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

    /* 更新医师信息
     * @see com.yhcrt.healthcloud.dao.DoctorDao#update(com.yhcrt.healthcloud.entity.Doctor)
     */
    @Override
    public Integer update(Doctor doc) {
        return doctorMapper.updateByPrimaryKey(doc);
    }

    /* 查询单个医师
     * @see com.yhcrt.healthcloud.dao.DoctorDao#select(java.lang.String)
     */
    @Override
    public Doctor select(Integer docId) {
        return doctorMapper.selectByPrimaryKey(docId);
    }

    /* 按条件查询医师
     * @see com.yhcrt.healthcloud.organization.service.DoctorService#search(java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, int)
     */
    @Override
    public List<Doctor> search(List<Integer> orgId_list, String userCode, String realName, String specialty, Integer status) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("orgId_list", orgId_list);
        map.put("userCode", userCode);
        map.put("realName", realName);
        map.put("specialty", specialty);
        map.put("status", status);
        return doctorMapper.search(map);
    }

    /* 根据登录用户ID查询医师
     * @see com.yhcrt.healthcloud.organization.service.DoctorService#selectByUserId(java.lang.Integer)
     */
    @Override
    public List<Doctor> selectByUserId(Integer userId) {
        DoctorExample example = new DoctorExample();
        example.createCriteria().andUserIdEqualTo(userId);
        return doctorMapper.selectByExample(example);
    }

	@Override
	public Integer updateDoc(Doctor doc) {
		return doctorMapper.updateByPrimaryKeySelective(doc);
	}

	//根据排除docId判断identityCard在表中是否存在
	@Override
	public int countExtDocId(Doctor doc) {
		return doctorMapper.countExtDocId(doc);
	}

	// 查询当前登录员角色分类下管理的医师
	@Override
	public List<Doctor> queryList(Map<String, Object> map) {
        return doctorMapper.search(map);
	}

	//导入批量新增
	@Override
	public void batchAdd(List<SysUser> userList, List<Doctor> docList, List<UserRole> roleList) {
		sysUserMapper.batchAdd(userList);
		userRoleMapper.batchAdd(roleList);
		doctorMapper.batchAdd(docList);
	}

}
