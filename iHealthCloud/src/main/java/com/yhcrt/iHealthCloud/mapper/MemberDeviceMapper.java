package com.yhcrt.iHealthCloud.mapper;

import com.yhcrt.iHealthCloud.entity.MemberDevice;
import com.yhcrt.iHealthCloud.entity.MemberDeviceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MemberDeviceMapper {
    long countByExample(MemberDeviceExample example);

    int deleteByExample(MemberDeviceExample example);

    int deleteByPrimaryKey(Integer cid);

    int insert(MemberDevice record);

    int insertSelective(MemberDevice record);

    List<MemberDevice> selectByExample(MemberDeviceExample example);

    MemberDevice selectByPrimaryKey(Integer cid);
    
    MemberDevice selectByImei(@Param("imei")String imei, @Param("status")Integer status);
    
    /**
     * 获取会员的默认终端设备
     * @param memberId
     * @return
     */
    MemberDevice getDefaultDevice(String memberId);

    int updateByExampleSelective(@Param("record") MemberDevice record, @Param("example") MemberDeviceExample example);

    int updateByExample(@Param("record") MemberDevice record, @Param("example") MemberDeviceExample example);

    int updateByPrimaryKeySelective(MemberDevice record);

    int updateByPrimaryKey(MemberDevice record);
}