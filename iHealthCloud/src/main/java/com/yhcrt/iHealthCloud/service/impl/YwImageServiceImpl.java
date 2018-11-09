package com.yhcrt.iHealthCloud.service.impl;	
	
import java.util.List;	

	





import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.stereotype.Service;

import com.yhcrt.iHealthCloud.common.Constants;
import com.yhcrt.iHealthCloud.entity.YwImage;
import com.yhcrt.iHealthCloud.entity.YwImageExample;
import com.yhcrt.iHealthCloud.mapper.YwImageMapper;
import com.yhcrt.iHealthCloud.service.YwImageService;
	
	
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
     * @see com.yhcrt.healthcloud.mall.service.YwImageService#upd(com.yhcrt.healthcloud.mall.entity.YwImage)	
     */	
    @Override	
    public Integer upd(YwImage ywImage) {	
        return ywImageMapper.updateByPrimaryKey(ywImage);	
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

    /* (non-Javadoc)
     * @see com.yhcrt.healthcloud.mall.service.YwImageService#getByRefId(java.lang.Integer)
     */
    @Override
    public List<YwImage> getByRefId(Integer cid,String module_code) {
        YwImageExample example = new YwImageExample();
        example.createCriteria().andRefIdEqualTo(cid)
            .andModuleCodeEqualTo(module_code)
            .andStatusEqualTo(Constants.STATUS_NORMAL);
        return ywImageMapper.selectByExample(example);
    }	
	
}	
