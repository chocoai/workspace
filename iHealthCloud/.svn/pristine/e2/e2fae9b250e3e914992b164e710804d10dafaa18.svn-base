package com.yhcrt.iHealthCloud.mapper;

import com.yhcrt.iHealthCloud.entity.ThirdLogin;
import com.yhcrt.iHealthCloud.entity.ThirdLoginExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ThirdLoginMapper {
    long countByExample(ThirdLoginExample example);

    int deleteByExample(ThirdLoginExample example);

    int deleteByPrimaryKey(String cid);

    int insert(ThirdLogin record);

    int insertSelective(ThirdLogin record);

    List<ThirdLogin> selectByExample(ThirdLoginExample example);

    ThirdLogin selectByPrimaryKey(String cid);
    
    ThirdLogin selectByUid(String uid);

    int updateByExampleSelective(@Param("record") ThirdLogin record, @Param("example") ThirdLoginExample example);

    int updateByExample(@Param("record") ThirdLogin record, @Param("example") ThirdLoginExample example);

    int updateByPrimaryKeySelective(ThirdLogin record);

    int updateByPrimaryKey(ThirdLogin record);
}