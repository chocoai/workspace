package com.yhcrt.iHealthCloud.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yhcrt.iHealthCloud.entity.UserComment;
import com.yhcrt.iHealthCloud.entity.UserCommentExample;

public interface UserCommentMapper {
    long countByExample(UserCommentExample example);

    int deleteByExample(UserCommentExample example);

    int deleteByPrimaryKey(Integer commentId);

    int insert(UserComment record);

    int insertSelective(UserComment record);

    List<UserComment> selectByExample(UserCommentExample example);

    UserComment selectByPrimaryKey(Integer commentId);

    int updateByExampleSelective(@Param("record") UserComment record, @Param("example") UserCommentExample example);

    int updateByExample(@Param("record") UserComment record, @Param("example") UserCommentExample example);

    int updateByPrimaryKeySelective(UserComment record);

    int updateByPrimaryKey(UserComment record);
    
    List<UserComment> selectAll(@Param("comment") UserComment comment,@Param("limit") Integer limit);
    
    Long countAll(@Param("comment") UserComment comment);
}