package com.yhcrt.healthcloud.organization.service;

import java.util.List;
import java.util.Map;

import com.yhcrt.healthcloud.organization.entity.Doctor;
import com.yhcrt.healthcloud.system.entity.SysUser;
import com.yhcrt.healthcloud.system.entity.UserRole;

/* @Description: 医师SERVICE层接口类
 * @version	1.0		2017年5月9日
 * @author jimmy
*/
public interface DoctorService {

	Integer insert(Doctor doc, SysUser user);

	Integer delete(Doctor doc, SysUser user);

	Integer recover(Doctor doc, SysUser user);

	Integer update(Doctor doc);

	Doctor select(Integer docId);

	List<Doctor> search(List<Integer> orgId_list, String userCode, String realName, String specialty, Integer status);

	List<Doctor> selectByUserId(Integer userId);

	Integer updateDoc(Doctor doc);

	// 根据排除docId判断identityCard在表中是否存在
	int countExtDocId(Doctor doc);

	// 查询当前登录员角色分类下管理的医师
	List<Doctor> queryList(Map<String, Object> map);

	//导入批量新增
	void batchAdd(List<SysUser> userList, List<Doctor> docList, List<UserRole> roleList);
	
}
