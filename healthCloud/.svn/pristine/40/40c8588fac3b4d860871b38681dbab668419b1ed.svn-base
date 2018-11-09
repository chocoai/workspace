package com.yhcrt.healthcloud.mall.mapper;

import com.yhcrt.healthcloud.mall.entity.UserComment;
import com.yhcrt.healthcloud.mall.entity.UserCommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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
}