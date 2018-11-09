package com.yhcrt.healthcloud.cms.mapper;

import com.yhcrt.healthcloud.cms.entity.CmsChannel;
import com.yhcrt.healthcloud.cms.entity.CmsChannelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsChannelMapper {
    long countByExample(CmsChannelExample example);

    int deleteByExample(CmsChannelExample example);

    int deleteByPrimaryKey(Integer channelId);

    int insert(CmsChannel record);

    int insertSelective(CmsChannel record);

    List<CmsChannel> selectByExample(CmsChannelExample example);
    
    List<CmsChannel> getChildChannelByParentId(Integer parentId);

    CmsChannel selectByPrimaryKey(Integer channelId);
    
    CmsChannel getRootChannel();

    int updateByExampleSelective(@Param("record") CmsChannel record, @Param("example") CmsChannelExample example);

    int updateByExample(@Param("record") CmsChannel record, @Param("example") CmsChannelExample example);

    int updateByPrimaryKeySelective(CmsChannel record);

    int updateByPrimaryKey(CmsChannel record);
}