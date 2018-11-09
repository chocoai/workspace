package com.yhcrt.iHealthCloud.mapper;

import com.yhcrt.iHealthCloud.entity.SysInterfaceLog;
import com.yhcrt.iHealthCloud.entity.SysInterfaceLogExample;
import com.yhcrt.iHealthCloud.entity.SysInterfaceLogWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysInterfaceLogMapper {
    long countByExample(SysInterfaceLogExample example);

    int deleteByExample(SysInterfaceLogExample example);

    int deleteByPrimaryKey(String cid);

    int insert(SysInterfaceLogWithBLOBs record);

    int insertSelective(SysInterfaceLogWithBLOBs record);

    List<SysInterfaceLogWithBLOBs> selectByExampleWithBLOBs(SysInterfaceLogExample example);

    List<SysInterfaceLog> selectByExample(SysInterfaceLogExample example);

    SysInterfaceLogWithBLOBs selectByPrimaryKey(String cid);

    int updateByExampleSelective(@Param("record") SysInterfaceLogWithBLOBs record, @Param("example") SysInterfaceLogExample example);

    int updateByExampleWithBLOBs(@Param("record") SysInterfaceLogWithBLOBs record, @Param("example") SysInterfaceLogExample example);

    int updateByExample(@Param("record") SysInterfaceLog record, @Param("example") SysInterfaceLogExample example);

    int updateByPrimaryKeySelective(SysInterfaceLogWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(SysInterfaceLogWithBLOBs record);

    int updateByPrimaryKey(SysInterfaceLog record);
}