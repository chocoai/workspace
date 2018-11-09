
package com.yhcrt.entity.queryStatistical;

import com.yhcrt.utils.Constants;

/**
 * 成绩查询
 * @author 陈伟
 * 2017年7月20日 上午11:32:35
 * 版权所有：武汉炎黄创新服务有限公司
 */
public class ScoreInfo extends ScoresBase{

	/**
   	 * 【描述】：参赛CID
   	 */
	private Integer cid;
	/**
   	 * 【描述】：项目ID
   	 */
    private Integer pid;
    
	/**
   	 * 【描述】：等级标准（0：健将；1：一级；2：二级；3：三级）
   	 */
    private Integer judgeLevel;
    
	/**
   	 * 【描述】：是否破纪录(0:否；1：是)
   	 */
    private Integer judgeRecord;
	/**
   	 * 【描述】：是否代表单位参赛
   	 */
    private Integer isperUnit;
    /**
     * 【描述】：参赛单位个数
     */
    private Integer unitNum;
    /**
     * 【描述】：参赛运动员个数
     */
    private Integer athleteNum;
    
    public ScoresBase getScoresBase(){
    	ScoresBase bean = new ScoresBase();
    	bean.setRanking(getRanking());
    	bean.setAthleteName(getAthleteName());
    	bean.setScores(getScores());
    	bean.setIntrgral(getIntrgral());
    	bean.setBackUp(getBackUp());
    	if(getIsperUnit()!=null&&getIsperUnit().equals(Constants.Middle)){
    		bean.setUnitName("个人");
    	}else{
    		bean.setUnitName(getUnitName());
    	}
    	
    	return bean;
    }
    
    
	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}


	public Integer getUnitNum() {
    	return unitNum;
		
	}

	public void setUnitNum(Integer unitNum) {
		this.unitNum = unitNum;
	}

	public Integer getAthleteNum() {
		return athleteNum;
	}

	public void setAthleteNum(Integer athleteNum) {
		this.athleteNum = athleteNum;
	}

	public Integer getJudgeLevel() {
		return judgeLevel;
	}

	public void setJudgeLevel(Integer judgeLevel) {
		this.judgeLevel = judgeLevel;
	}

	public Integer getJudgeRecord() {
		return judgeRecord;
	}

	public void setJudgeRecord(Integer judgeRecord) {
		this.judgeRecord = judgeRecord;
	}


	public Integer getIsperUnit() {
		return isperUnit;
	}


	public void setIsperUnit(Integer isperUnit) {
		this.isperUnit = isperUnit;
	}
	
}
