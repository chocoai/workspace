package com.whty.wfd.page.dao;

import com.whty.wfd.page.vo.Collection;
import com.whty.wfd.page.vo.Like;

import java.util.List;

/**
 * \* User: zjd \* Date: 2018/8/17 \* Time: 16:01 \* Description: \
 */
public interface CollectionMapper {

	// 查询该用户帖子、评论点赞
	public List<Collection> getCollections(Integer userId);
}