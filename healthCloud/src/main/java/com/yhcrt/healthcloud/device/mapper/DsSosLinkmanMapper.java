package com.yhcrt.healthcloud.device.mapper;

import com.yhcrt.healthcloud.device.entity.DsSosLinkman;
import com.yhcrt.healthcloud.device.entity.DsSosLinkmanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DsSosLinkmanMapper {
    long countByExample(DsSosLinkmanExample example);

    int deleteByExample(DsSosLinkmanExample example);

    int deleteByPrimaryKey(Integer cid);

    int insert(DsSosLinkman record);

    int insertSelective(DsSosLinkman record);

    List<DsSosLinkman> selectByExample(DsSosLinkmanExample example);

    DsSosLinkman selectByPrimaryKey(Integer cid);

    int updateByExampleSelective(@Param("record") DsSosLinkman record, @Param("example") DsSosLinkmanExample example);

    int updateByExample(@Param("record") DsSosLinkman record, @Param("example") DsSosLinkmanExample example);

    int updateByPrimaryKeySelective(DsSosLinkman record);

    int updateByPrimaryKey(DsSosLinkman record);
}