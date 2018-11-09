package com.whty.wfd.page.service.impl;

import com.whty.wfd.page.dao.TPostViewLogMapper;
import com.whty.wfd.page.model.TPostViewLog;
import com.whty.wfd.page.service.PostViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * \* User: zjd \* Date: 2018/8/25 \* Time: 9:04 \* Description: \
 */
@Service
public class PostViewServiceImpl implements PostViewService {

	@Autowired
	private TPostViewLogMapper tPostViewLogMapper;

	@Override
	public int addPostView(TPostViewLog tPostViewLog) {
		return tPostViewLogMapper.insert(tPostViewLog);
	}
}