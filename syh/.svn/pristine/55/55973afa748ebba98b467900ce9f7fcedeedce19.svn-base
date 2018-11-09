package com.yhcrt.service.project;
import static com.yhcrt.utils.Constants.SCORE_RECORD;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yhcrt.controller.BaseController;
import com.yhcrt.dao.DaoSupport;
import com.yhcrt.entity.project.ScoreRecord;
import com.yhcrt.shiro.TokenManager.TokenManager;
import com.yhcrt.utils.DateUtil;
import com.yhcrt.utils.GetSequence;

/**
 * 计分计牌方式的service
 * @author kejunzhong
 * 2017年5月11日
 * 版权所有：武汉炎黄创新服务有限公司
 */
@Service("scoreRecordService")
public class ScoreRecordService {
	@Resource
	private DaoSupport dao;
	
	/**
	 * 查询计分记牌方式列表
	 * @param scoreRecord
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ScoreRecord> queryList(String findContent) throws Exception {
		ScoreRecord scoreRecord = new ScoreRecord();
		scoreRecord.setRuleName(findContent);
		scoreRecord.setErsionNum(TokenManager.getErsionNum());
		return (List<ScoreRecord>) dao.findForList("ScoreRecordMapper.selectByExample", scoreRecord);
	}
	/**
	 * 添加计分记牌方式
	 * @param jsonString
	 * @return
	 * @throws Exception
	 */
	public String insertScoreRecord(ScoreRecord scoreRecord) {
		String result="";
			try {
				if(scoreRecord.getSeventhScore() == null || "".equals(scoreRecord.getSeventhScore())){
					scoreRecord.setSeventhScore(0);
				}else if(scoreRecord.getEightScore() == null || "".equals(scoreRecord.getEightScore())){
					scoreRecord.setEightScore(0);
				}
				scoreRecord.setCid(GetSequence.getSequenceByName(dao, SCORE_RECORD));
				scoreRecord.setCreaRen(BaseController.getUserCode());
				scoreRecord.setCreaTime(DateUtil.getTime());
				scoreRecord.setErsionNum(TokenManager.getErsionNum());
				dao.save("ScoreRecordMapper.insert", scoreRecord);
				result="success";
			} catch (Exception e) {
				e.printStackTrace();
				result="failed";
			}
		return result;
	}
	/**
	 * 通过CID获取计分记牌信息
	 * @return
	 * @throws Exception 
	 */
	public ScoreRecord searchObjectByCid(Integer cid) {
		try {
			ScoreRecord scoreRecord = (ScoreRecord)dao.findForObject("ScoreRecordMapper.selectByCid", cid);
			return scoreRecord;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 修改计分记牌方式列表
	 * @param jsonString
	 * @return
	 * @throws Exception
	 */
	public String updateScoreRecord(ScoreRecord scoreRecord) {
		String result="";
			try {
				scoreRecord.setErsionNum(TokenManager.getErsionNum());
				dao.save("ScoreRecordMapper.update", scoreRecord);
				result="success";
			} catch (Exception e) {
				e.printStackTrace();
				result="failed";
			}
		return result;
	}
	/**
	 * 删除计分记牌方式列表
	 * @param cid
	 * @return 
	 * @throws Exception
	 */
	public HashMap<String,Object> deleteScoreRecord(Integer cid) throws Exception {
		HashMap<String,Object> map=new HashMap<String,Object>();
		Integer countScore = (Integer) dao.findForObject("ProjectManagerMapper.countByScoreCid", cid);
		if(countScore >0){
			map.put("status", "noscore");
		}else{
			dao.delete("ScoreRecordMapper.delete", cid);
			map.put("status", "success");
		}
		return map;
	}
	/**
	 * 判断方式名称是否重复
	 * @param cid
	 * @return 
	 * @throws Exception
	 */
	public HashMap<String,Object> isRuleName(String ruleName) {
		HashMap<String,Object> map=new HashMap<String,Object>();
		try {
			ScoreRecord scoreRecord =new ScoreRecord();
			scoreRecord.setRuleName(ruleName);
			scoreRecord.setErsionNum(TokenManager.getErsionNum());
			List<ScoreRecord> list = (List<ScoreRecord>) dao.findForList("ScoreRecordMapper.isRuleName", scoreRecord);
			if(list.size() >0){
				map.put("status", "true");
			}else{
				map.put("status", "success");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("status", "failed");
		}
		return map;
	}
}
