package com.yhcrt.healthcloud.system.mapper;

import com.yhcrt.healthcloud.system.entity.UserRole;
import com.yhcrt.healthcloud.system.entity.UserRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserRoleMapper {
    long countByExample(UserRoleExample example);

    int deleteByExample(UserRoleExample example);

    int deleteByPrimaryKey(Integer cid);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    List<UserRole> selectByExample(UserRoleExample example);

    UserRole selectByPrimaryKey(Integer cid);

    int updateByExampleSelective(@Param("record") UserRole record, @Param("example") UserRoleExample example);

    int updateByExample(@Param("record") UserRole record, @Param("example") UserRoleExample example);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);

    //根据userId查询roleId集合
	List<Integer> queryByUid(Integer userId);

	//查询是否存在记录
	int countByUid(Integer userId);

	//根据userId修改roleId
	void updateByUid(UserRole userRole);

	//导入批量新增
	void batchAdd(List<UserRole> list);
	
}