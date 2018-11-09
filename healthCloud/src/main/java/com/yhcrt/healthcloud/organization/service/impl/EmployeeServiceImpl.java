package com.yhcrt.healthcloud.organization.service.impl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhcrt.healthcloud.organization.entity.Employee;
import com.yhcrt.healthcloud.organization.entity.EmployeeExample;
import com.yhcrt.healthcloud.organization.mapper.EmployeeMapper;
import com.yhcrt.healthcloud.organization.service.EmployeeService;
import com.yhcrt.healthcloud.system.entity.SysUser;
import com.yhcrt.healthcloud.system.entity.UserRole;
import com.yhcrt.healthcloud.system.mapper.SysUserMapper;
import com.yhcrt.healthcloud.system.mapper.UserRoleMapper;

/* @Description: 员工SERVICE层实现类
 * @version 1.0     2017年5月9日
 * @author jimmy
*/
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService{
    
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    /* 添加员工
     * @see com.yhcrt.healthcloud.dao.EmployeeDao#insert(com.yhcrt.healthcloud.entity.Employee)
     */
    @Override
    public Integer insert(Employee emp,SysUser sysUser) {
        int i = 0;
        try{
            //这里开始捕获异常，因为要让添加方法回滚，所以要写在try里面
            i = sysUserMapper.insert(sysUser);
            //这里写的判定条件是故意让其抛出异常，检验事务回滚功能的
            if(i<1){
                throw new Exception();
            }else{
                int e = employeeMapper.insert(emp);
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
        
    /* 删除员工(禁用员工)
     * @see com.yhcrt.healthcloud.dao.EmployeeDao#delete(java.lang.String)
     */
    @Override
    public Integer delete(Employee emp,SysUser user) {
        int i = 0;
        try{
            //这里开始捕获异常，因为要让upd方法回滚，所以要写在try里面
            i = employeeMapper.updateByPrimaryKey(emp);
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
    
    /* 恢复员工(取消禁用)
     * @see com.yhcrt.healthcloud.dao.EmployeeDao#recover(com.yhcrt.healthcloud.entity.Employee)
     */
    @Override
    public Integer recover(Employee emp,SysUser user) {
        int i = 0;
        try{
            //这里开始捕获异常，因为要让upd方法回滚，所以要写在try里面
            i = employeeMapper.updateByPrimaryKey(emp);
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

    /* 更新员工信息
     * @see com.yhcrt.healthcloud.dao.EmployeeDao#update(com.yhcrt.healthcloud.entity.Employee)
     */
    @Override
    public Integer update(Employee emp) {
        return employeeMapper.updateByPrimaryKey(emp);
    }

    /* 查询单个员工
     * @see com.yhcrt.healthcloud.dao.EmployeeDao#select(java.lang.String)
     */
    @Override
    public Employee select(Integer empId) {
        return employeeMapper.selectByPrimaryKey(empId);
    }

    /* 根据登录用户ID查询出对应的员工
     * @see com.yhcrt.healthcloud.organization.service.EmployeeService#selectByUserId(java.lang.Integer)
     */
    @Override
    public List<Employee> selectByUserId(Integer userId) {
        EmployeeExample example = new EmployeeExample();
        example.createCriteria().andUserIdEqualTo(userId);
        return employeeMapper.selectByExample(example);
    }

	@Override
	public Integer updateEmp(Employee emp) {
		return employeeMapper.updateByPrimaryKeySelective(emp);
	}

    /* 根据身份证号码查出对应的员工
     * @see com.yhcrt.healthcloud.organization.service.EmployeeService#selectByIdCard(java.lang.String)
     */
    @Override
    public List<Employee> selectByIdCard(String idCard) {
        EmployeeExample example = new EmployeeExample();
        example.createCriteria().andIdentityCardEqualTo(idCard);
        return employeeMapper.selectByExample(example);
    }

	@Override
	public List<Employee> queryList(Map<String, Object> map) {
        return employeeMapper.search(map);
	}

	//根据排除empId判断identityCard在表中是否存在
	@Override
	public int countExtEmpId(Employee employee) {
		return employeeMapper.countExtEmpId(employee);
	}

	//导入批量新增
	@Override
	public void batchAdd(List<SysUser> userList, List<Employee> empList, List<UserRole> roleList) {
		employeeMapper.batchAdd(empList);
	    sysUserMapper.batchAdd(userList);
	    userRoleMapper.batchAdd(roleList);
	}

}
