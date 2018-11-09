package com.yhcrt.healthcloud.mall.service.impl;	
	
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhcrt.healthcloud.mall.entity.YwImage;
import com.yhcrt.healthcloud.mall.entity.YwImageExample;
import com.yhcrt.healthcloud.mall.mapper.YwImageMapper;
import com.yhcrt.healthcloud.mall.service.YwImageService;
	
/* @Description: 	
 * @version 1.0     2017年9月7日	
 * @author jimmy	
*/	
@Service	
public class YwImageServiceImpl implements YwImageService{	
	
    @Autowired	
    private YwImageMapper ywImageMapper;	
    	
    /* (non-Javadoc)	
     * @see com.yhcrt.healthcloud.mall.service.YwImageService#add(com.yhcrt.healthcloud.mall.entity.YwImage)	
     */	
    @Override	
    public Integer add(YwImage ywImage) {	
        return ywImageMapper.insert(ywImage);	
    }	
	
    /* (non-Javadoc)	
     * @see com.yhcrt.healthcloud.mall.service.YwImageService#del(java.lang.String)	
     */	
    @Override	
    public Integer del(Integer cid) {	
        return ywImageMapper.deleteByPrimaryKey(cid);	
    }	
	
    /* (non-Javadoc)	
     * @see com.yhcrt.healthcloud.mall.service.YwImageService#get(java.lang.String)	
     */	
    @Override	
    public YwImage get(Integer cid) {	
        return ywImageMapper.selectByPrimaryKey(cid);	
    }	
	
    /* (non-Javadoc)	
     * @see com.yhcrt.healthcloud.mall.service.YwImageService#getAll()	
     */	
    @Override	
    public List<YwImage> getAll() {	
        return ywImageMapper.selectByExample(null);	
    }

    //根据条件查询出日志关联的图片
    @Override
    public List<YwImage> getByRefId(Integer cid,String module_code) {
        YwImageExample example = new YwImageExample();
        example.createCriteria().andRefIdEqualTo(cid).andModuleCodeEqualTo(module_code);
        return ywImageMapper.selectByExample(example);
    }	
	
}	
