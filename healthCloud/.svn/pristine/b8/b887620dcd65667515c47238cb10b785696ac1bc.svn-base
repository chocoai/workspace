package com.yhcrt.healthcloud.system.mapper;

import com.yhcrt.healthcloud.system.entity.MessageCode;
import com.yhcrt.healthcloud.system.entity.MessageCodeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MessageCodeMapper {
    long countByExample(MessageCodeExample example);

    int deleteByExample(MessageCodeExample example);

    int deleteByPrimaryKey(String msgId);

    int insert(MessageCode record);

    int insertSelective(MessageCode record);

    List<MessageCode> selectByExample(MessageCodeExample example);

    MessageCode selectByPrimaryKey(String msgId);
    
    MessageCode selectByKey(String phoneNum);//按手机号码查询验证码最新的一条

    int updateByExampleSelective(@Param("record") MessageCode record, @Param("example") MessageCodeExample example);

    int updateByExample(@Param("record") MessageCode record, @Param("example") MessageCodeExample example);

    int updateByPrimaryKeySelective(MessageCode record);

    int updateByPrimaryKey(MessageCode record);
}