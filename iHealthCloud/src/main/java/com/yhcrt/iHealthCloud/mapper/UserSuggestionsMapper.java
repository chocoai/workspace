package com.yhcrt.iHealthCloud.mapper;

import com.yhcrt.iHealthCloud.entity.UserSuggestions;
import com.yhcrt.iHealthCloud.entity.UserSuggestionsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserSuggestionsMapper {
    long countByExample(UserSuggestionsExample example);

    int deleteByExample(UserSuggestionsExample example);

    int deleteByPrimaryKey(String cid);

    int insert(UserSuggestions record);

    int insertSelective(UserSuggestions record);

    List<UserSuggestions> selectByExample(UserSuggestionsExample example);

    UserSuggestions selectByPrimaryKey(String cid);

    int updateByExampleSelective(@Param("record") UserSuggestions record, @Param("example") UserSuggestionsExample example);

    int updateByExample(@Param("record") UserSuggestions record, @Param("example") UserSuggestionsExample example);

    int updateByPrimaryKeySelective(UserSuggestions record);

    int updateByPrimaryKey(UserSuggestions record);
}