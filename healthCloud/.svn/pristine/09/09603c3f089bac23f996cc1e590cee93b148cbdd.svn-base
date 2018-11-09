package com.yhcrt.healthcloud.cms.mapper;

import com.yhcrt.healthcloud.cms.entity.CmsContent;
import com.yhcrt.healthcloud.cms.entity.CmsContentExample;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsContentMapper {
    long countByExample(CmsContentExample example);

    int deleteByExample(CmsContentExample example);

    int deleteByPrimaryKey(Integer contentId);

    int insert(CmsContent record);

    int insertSelective(CmsContent record);

    List<CmsContent> selectByExampleWithBLOBs(CmsContentExample example);

    List<CmsContent> selectByExample(CmsContentExample example);

    CmsContent selectByPrimaryKey(Integer contentId);

    int updateByExampleSelective(@Param("record") CmsContent record, @Param("example") CmsContentExample example);

    int updateByExampleWithBLOBs(@Param("record") CmsContent record, @Param("example") CmsContentExample example);

    int updateByExample(@Param("record") CmsContent record, @Param("example") CmsContentExample example);

    int updateByPrimaryKeySelective(CmsContent record);

    int updateByPrimaryKeyWithBLOBs(CmsContent record);

    int updateByPrimaryKey(CmsContent record);
    
    int batchArchive(HashMap<?, ?> params);
    
    int batchDelete(HashMap<?, ?> params);
    
    int batchCancleArchive(HashMap<?, ?> params);
}