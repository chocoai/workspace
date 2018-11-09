package com.yhcrt.healthcloud.mall.service;

import java.util.List;

import com.yhcrt.healthcloud.mall.entity.YwImage;

/**
 * 显示图片service层
 * @author PC
 *
 */
public interface YwImageService {

	Integer add(YwImage ywImage);

	Integer del(Integer cid);

	YwImage get(Integer cid);

	List<YwImage> getAll();

	// 根据条件查询出日志关联的图片
	List<YwImage> getByRefId(Integer cid, String module_code);

}
