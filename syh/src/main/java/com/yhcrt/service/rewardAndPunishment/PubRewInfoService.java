package com.yhcrt.service.rewardAndPunishment;
import static com.yhcrt.utils.Constants.PUB_REW_INFO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yhcrt.dao.DaoSupport;
import com.yhcrt.entity.rewardAndPunishment.PubRewInfo;
import com.yhcrt.utils.GetSequence;

/**
 * 奖惩信息的servie
 * @author kejunzhong
 * 2017年5月11日
 * 版权所有：武汉炎黄创新服务有限公司
 */
@Service("pubRewInfoService")
public class PubRewInfoService {
	@Resource
	private DaoSupport dao;
	
	/**
	 * 条件查询奖惩信息
	 * @param pubRewInfo
	 * @return
	 * @throws Exception
	 */
	public List<PubRewInfo> queryByProperties(Map<String, Object> paramMap) throws Exception{
		return (List<PubRewInfo>) dao.findForList("PubRewInfoMapper.queryByProperties", paramMap);
	}
	
	/**
	 * 新增奖励信息
	 * @param pubRewInfo
	 * @throws Exception
	 */
	public void insertRewInfo(PubRewInfo pubRewInfo) throws Exception {
		pubRewInfo.setCid(GetSequence.getSequenceByName(dao, PUB_REW_INFO));
		dao.save("PubRewInfoMapper.insertRewInfo", pubRewInfo);
	}
	
	/**
	 * 新增惩罚信息
	 * @param pubRewInfo
	 * @throws Exception
	 */
	public void insertPunishInfo(PubRewInfo pubRewInfo) throws Exception {
		pubRewInfo.setCid(GetSequence.getSequenceByName(dao, "pub_rew_info"));
		dao.save("PubRewInfoMapper.insertPunishInfo", pubRewInfo);
	}
	
	/**
	 * 删除奖惩信息
	 * @param cid
	 * @throws Exception
	 */
	public void deleteInfoByCid(Integer[] cids) throws Exception {
		for(Integer cid : cids){
			PubRewInfo pubRewInfo = (PubRewInfo) dao.findForObject("PubRewInfoMapper.getById", cid);
			if(pubRewInfo.getInfoType()==1){  //惩罚
				Map<String, Object> param1 = new HashMap<String, Object>();
				Map<String, Object> param2 = new HashMap<String, Object>();
				param1.put("participatId", pubRewInfo.getParticipatId());
				param1.put("unitId", pubRewInfo.getCompanyCid());
				param1.put("medal", pubRewInfo.getMedalType());
				param1.put("medalNum", pubRewInfo.getMedalCount());
				param1.put("scores", pubRewInfo.getScoresCount());
				param1.put("ranking", pubRewInfo.getRankingCount());
				param1.put("intrgral", pubRewInfo.getIntrgralCount());
				param2.put("isIntrgral", pubRewInfo.getIntrgralCount()>0?true:false);
				param2.put("isRanking", pubRewInfo.getRankingCount()>0?true:false);
				param2.put("isMedal", pubRewInfo.getMedalCount()>0?true:false);
				param2.put("projectId", pubRewInfo.getProjectCid());
				param2.put("ranking", pubRewInfo.getRankingCount());
				dao.update("ParticipatDetailMapper.deletePunishForOther", param2); //先操作
				dao.findForObject("ParticipatDetailMapper.rollbackDetail", param1);	 //后操作
			}
			dao.delete("PubRewInfoMapper.deleteByCid", cid);
		}
	}
	
	
	/**
	 * 通过项目id和单位id查询参赛信息
	 * @param  projectCid
	 *  @param unitCid
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>>  getParticipatInfo(Map<String, Integer> param) throws Exception{
		return (List<Map<String, Object>>) dao.findForList("ParticipatInfoMapper.getForSelect", param);
	}
	
}
