package com.yhcrt.healthcloud.memberBack.mapper;

import com.yhcrt.healthcloud.memberBack.entity.MemberDeviceBack;
import com.yhcrt.healthcloud.memberBack.entity.MemberDeviceExampleBack;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MemberDeviceBackMapper {
	
    long countByExample(MemberDeviceExampleBack example);

    int deleteByExample(MemberDeviceExampleBack example);

    int deleteByPrimaryKey(Integer cid);

    int insert(MemberDeviceBack record);

    int insertSelective(MemberDeviceBack record);

    List<MemberDeviceBack> selectByExample(MemberDeviceExampleBack example);

    MemberDeviceBack selectByPrimaryKey(Integer cid);
    
    MemberDeviceBack selectByImei(String imei);

    int updateByExampleSelective(@Param("record") MemberDeviceBack record, @Param("example") MemberDeviceExampleBack example);

    int updateByExample(@Param("record") MemberDeviceBack record, @Param("example") MemberDeviceExampleBack example);

    int updateByPrimaryKeySelective(MemberDeviceBack record);

    int updateByPrimaryKey(MemberDeviceBack record);
}