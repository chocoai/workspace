package com.yhcrt.entity.project;

import com.yhcrt.utils.Constants;

/**
 * 比赛项目的实体类
 * @author heyun
 * 2017年5月11日
 * 版权所有：武汉炎黄创新科技服务有限公司
 */
public class ProjectManager {
	/**
	 * 【描述】：比赛项目CID
	 */
    private Integer cid;

    /**
	 * 【描述】：比赛项目名称
	 */
    private String projectName;

    /**
	 * 【描述】：比赛项目简称
	 */
    private String abbreviation;
    
    /**
	 * 【描述】：比赛项目简称
	 */
    private String alias;

    /**
	 * 【描述】：是否团体
	 */
    private Integer isTeam;
    
    private String gameType;
    /**
	 * 【描述】：分类CID
	 */
    private Integer pCid;
    
    private String pName;//上级分类名称

    /**
	 * 【描述】：计分记牌方式
	 */
    private Integer scoreCard;

    /**
	 * 【描述】： 成绩类型
	 */
    private Integer resultType;
    
    private String resultTypeName;

    /**
	 * 【描述】：成绩单位
	 */
    private String resultUnit;

    /**
	 * 【描述】：晋级决赛人数
	 */
    private Integer finalistsNum;

    /**
	 * 【描述】：决赛录取人数
	 */
    private Integer finalAdmission;

    /**
	 * 【描述】：性别组别
	 */
    private Integer gender;

    /**
	 * 【描述】：健将级别
	 */
    private String mastersLevel;

    /**
	 * 【描述】：一级
	 */
    private String firstLevel;

    /**
	 * 【描述】：二级
	 */
    private String secondLevel;

    /**
	 * 【描述】：三级
	 */
    private String threeLevel;
    
    private Integer projectType;
    
    //创建人
    private String creaRen;
    //创建时间
    private String creaTime;
    /**
	 * 【描述】：排序号
	 */
    private Integer orderNum;

    /**
	 * 【描述】：备注
	 */
    private String backup;

    /**
	 * 【描述】：备用字段1
	 */
    private Integer exp1;

    /**
	 * 【描述】：备用字段2
	 */
    private Integer exp2;

    /**
	 * 【描述】：备用字段3
	 */
    private String exp3;

    /**
	 * 【描述】：备用字段4
	 */
    private String exp4;

    /**
	 * 【描述】：备用字段5
	 */
    private String exp5;

    /**
	 * 【描述】：备用字段6
	 */
    private String exp6;
    
    private ScoreRecord scoreRecord;
    
    private ClassManager classManager;
    
    private String sex;//自定义根据gender取值判断是男是女
   
    public ProjectManager(){
    	this.orderNum = Constants.Middle;
    	this.isTeam =  Constants.Middle;
    	this.gender =  Constants.Middle;
    }
    
    
    public String getCreaRen() {
		return creaRen;
	}

	public void setCreaRen(String creaRen) {
		this.creaRen = creaRen;
	}

	public String getCreaTime() {
		return creaTime;
	}

	public void setCreaTime(String creaTime) {
		this.creaTime = creaTime;
	}

	public String getGameType() {
		return gameType;
	}

	public void setGameType(String gameType) {
		this.gameType = gameType;
	}

	public String getResultTypeName() {
		return resultTypeName;
	}

	public void setResultTypeName(String resultTypeName) {
		this.resultTypeName = resultTypeName;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public ScoreRecord getScoreRecord() {
		return scoreRecord;
	}

	public void setScoreRecord(ScoreRecord scoreRecord) {
		this.scoreRecord = scoreRecord;
	}

	public ClassManager getClassManager() {
		return classManager;
	}

	public void setClassManager(ClassManager classManager) {
		this.classManager = classManager;
	}
	
    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation == null ? null : abbreviation.trim();
    }

    public Integer getIsTeam() {
    	return isTeam;
    }

    public void setIsTeam(Integer isTeam) {
        this.isTeam = isTeam;
    }

    public Integer getpCid() {
        return pCid;
    }

    public void setpCid(Integer pCid) {
        this.pCid = pCid;
    }

    public Integer getScoreCard() {
        return scoreCard;
    }

    public void setScoreCard(Integer scoreCard) {
        this.scoreCard = scoreCard;
    }

    public Integer getResultType() {
        return resultType;
    }

    public void setResultType(Integer resultType) {
        this.resultType = resultType;
    }

    public String getResultUnit() {
        return resultUnit;
    }

    public void setResultUnit(String resultUnit) {
        this.resultUnit = resultUnit == null ? null : resultUnit.trim();
    }

    public Integer getFinalistsNum() {
        return finalistsNum;
    }

    public void setFinalistsNum(Integer finalistsNum) {
        this.finalistsNum = finalistsNum;
    }

    public Integer getFinalAdmission() {
        return finalAdmission;
    }

    public void setFinalAdmission(Integer finalAdmission) {
        this.finalAdmission = finalAdmission;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getMastersLevel() {
        return mastersLevel;
    }

    public void setMastersLevel(String mastersLevel) {
        this.mastersLevel = mastersLevel == null ? null : mastersLevel.trim();
    }

    public String getFirstLevel() {
        return firstLevel;
    }

    public void setFirstLevel(String firstLevel) {
        this.firstLevel = firstLevel == null ? null : firstLevel.trim();
    }

    public String getSecondLevel() {
        return secondLevel;
    }

    public void setSecondLevel(String secondLevel) {
        this.secondLevel = secondLevel == null ? null : secondLevel.trim();
    }

    public String getThreeLevel() {
        return threeLevel;
    }

    public void setThreeLevel(String threeLevel) {
        this.threeLevel = threeLevel == null ? null : threeLevel.trim();
    }

    public Integer getProjectType() {
		return projectType;
	}


	public void setProjectType(Integer projectType) {
		this.projectType = projectType;
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


	@Override
	public String toString() {
		return "ProjectManager [cid=" + cid + ", projectName=" + projectName + ", abbreviation=" + abbreviation
				+ ", alias=" + alias + ", isTeam=" + isTeam + ", gameType=" + gameType + ", pCid=" + pCid + ", pName="
				+ pName + ", scoreCard=" + scoreCard + ", resultType=" + resultType + ", resultTypeName="
				+ resultTypeName + ", resultUnit=" + resultUnit + ", finalistsNum=" + finalistsNum + ", finalAdmission="
				+ finalAdmission + ", gender=" + gender + ", mastersLevel=" + mastersLevel + ", firstLevel="
				+ firstLevel + ", secondLevel=" + secondLevel + ", threeLevel=" + threeLevel + ", projectType="
				+ projectType + ", creaRen=" + creaRen + ", creaTime=" + creaTime + ", orderNum=" + orderNum
				+ ", backup=" + backup + ", exp1=" + exp1 + ", exp2=" + exp2 + ", exp3=" + exp3 + ", exp4=" + exp4
				+ ", exp5=" + exp5 + ", exp6=" + exp6 + ", scoreRecord=" + scoreRecord + ", classManager="
				+ classManager + ", sex=" + sex + "]";
	}
    
	
}