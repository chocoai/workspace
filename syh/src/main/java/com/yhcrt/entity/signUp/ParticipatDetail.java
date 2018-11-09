package com.yhcrt.entity.signUp;

import com.yhcrt.utils.Constants;
/**
 * 
 * 存储参赛的明细
 * @author 陈伟
 * 2017年7月10日 上午11:33:09
 * 版权所有：武汉炎黄创新服务有限公司
 */
public class ParticipatDetail {
	/**
   	 * 【描述】：主键
   	 */
	private Integer cid;
	/**
   	 * 【描述】：参赛ID
   	 */
    private Integer participatId;
	/**
   	 * 【描述】：运动员ID
   	 */
    private Integer athleteId;
	/**
   	 * 【描述】：证书
   	 */
    private Integer certificate;
	/**
   	 * 【描述】：成绩
   	 */
    private String scores;
	/**
   	 * 【描述】：名次
   	 */
    private Integer ranking;
	/**
   	 * 【描述】：积分
   	 */
    private Double intrgral;
	/**
   	 * 【描述】：奖牌(0:金；1：银；2：铜)
   	 */
    private Integer medal;
	/**
   	 * 【描述】：奖牌数量
   	 */
    private Double medalNum;
	/**
   	 * 【描述】：等级标准（0：健将；1：一级；2：二级；3：三级）
   	 */
    private Integer judgeLevel;
	/**
   	 * 【描述】：是否破纪录(0:否；1：是)
   	 */
    private Integer judgeRecord;

	/**
   	 * 【描述】：创建时间
   	 */
    private String creaTime;
    /**
     * 【描述】：创建人
     */
    private String creaRen;
	/**
   	 * 【描述】：排序号
   	 */
    private Integer orderNum;
	/**
   	 * 【描述】：备注
   	 */
    private String backup;

    private Integer exp1;

    private Integer exp2;

    private String exp3;

    private String exp4;

    private String exp5;

    private String exp6;
    
    /*************************************临时属性************************************/

    private AthleteBaseInfo athleteBaseInfo;
    
    private ParticipatInfo participatInfo;
    
    private String teamUnitName;  //团队赛单位名称
	private String teamAthleteName;//团队赛运动员名称
    
    public String getTeamUnitName() {
		return teamUnitName;
	}

	public void setTeamUnitName(String teamUnitName) {
		this.teamUnitName = teamUnitName;
	}

	public String getTeamAthleteName() {
		return teamAthleteName;
	}

	public void setTeamAthleteName(String teamAthleteName) {
		this.teamAthleteName = teamAthleteName;
	}

	public ParticipatInfo getParticipatInfo() {
		return participatInfo;
	}

	public void setParticipatInfo(ParticipatInfo participatInfo) {
		this.participatInfo = participatInfo;
	}

	public ParticipatDetail() {
    	this.orderNum = Constants.Middle;
	}

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getParticipatId() {
        return participatId;
    }

    public void setParticipatId(Integer participatId) {
        this.participatId = participatId;
    }

    public Integer getAthleteId() {
        return athleteId;
    }

    public void setAthleteId(Integer athleteId) {
        this.athleteId = athleteId;
    }

    public Integer getCertificate() {
        return certificate;
    }

    public void setCertificate(Integer certificate) {
        this.certificate = certificate;
    }

	public String getScores() {
		return scores;
	}

	public void setScores(String scores) {
		this.scores = scores;
	}

	public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }


    public Double getIntrgral() {
		return intrgral;
	}

	public void setIntrgral(Double intrgral) {
		this.intrgral = intrgral;
	}

	public Integer getMedal() {
        return medal;
    }

    public void setMedal(Integer medal) {
        this.medal = medal;
    }

    public Double getMedalNum() {
		return medalNum;
	}

	public void setMedalNum(Double medalNum) {
		this.medalNum = medalNum;
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

    public String getCreaTime() {
        return creaTime;
    }

    public void setCreaTime(String creaTime) {
        this.creaTime = creaTime == null ? null : creaTime.trim();
    }

    public String getCreaRen() {
		return creaRen;
	}

	public void setCreaRen(String creaRen) {
		this.creaRen = creaRen;
	}

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getBackup() {
        return backup;
    }

    public void setBackup(String backup) {
        this.backup = backup == null ? null : backup.trim();
    }

    public Integer getExp1() {
        return exp1;
    }

    public void setExp1(Integer exp1) {
        this.exp1 = exp1;
    }

    public Integer getExp2() {
        return exp2;
    }

    public void setExp2(Integer exp2) {
        this.exp2 = exp2;
    }

    public String getExp3() {
        return exp3;
    }

    public void setExp3(String exp3) {
        this.exp3 = exp3 == null ? null : exp3.trim();
    }

    public String getExp4() {
        return exp4;
    }

    public void setExp4(String exp4) {
        this.exp4 = exp4 == null ? null : exp4.trim();
    }

    public String getExp5() {
        return exp5;
    }

    public void setExp5(String exp5) {
        this.exp5 = exp5 == null ? null : exp5.trim();
    }

    public String getExp6() {
        return exp6;
    }

    public void setExp6(String exp6) {
        this.exp6 = exp6 == null ? null : exp6.trim();
    }

	public AthleteBaseInfo getAthleteBaseInfo() {
		return athleteBaseInfo;
	}

	public void setAthleteBaseInfo(AthleteBaseInfo athleteBaseInfo) {
		this.athleteBaseInfo = athleteBaseInfo;
	}
    
}