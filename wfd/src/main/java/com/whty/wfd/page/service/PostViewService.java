package com.whty.wfd.page.service;

import com.whty.wfd.page.model.TPostViewLog;

/**
 * \* User: zjd \* Date: 2018/8/25 \* Time: 9:04 \* Description: \
 */
public interface PostViewService {

	// 添加帖子访问记录
	int addPostView(TPostViewLog tPostViewLog);
}