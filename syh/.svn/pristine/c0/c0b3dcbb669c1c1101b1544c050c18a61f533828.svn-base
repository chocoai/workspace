package com.yhcrt.entity.project;

import com.yhcrt.utils.Constants;

/**
 * 项目分类的实体类
 * @author heyun
 * 2017年5月11日
 * 版权所有：武汉炎黄创新科技服务有限公司
 */
public class ClassManager {
	/**
	 * 【描述】：项目分类主键
	 */
    private Integer cid;
    
    /**
	 * 【描述】：项目分类名称
	 */
    private String classifyName;

    /**
	 * 【描述】：项目分类简称
	 */
    private String abbreviation;

    /**
	 * 【描述】：项目分类别名
	 */
    private String alias;

    /**
	 * 【描述】：上级分类主键
	 */
    private Integer classCid;
    /**
     * 【描述】：分类类型
     */
    private Integer classType;

    /**
	 * 【描述】：单位参赛运动员总数
	 */
    private Integer athletesNum;

    /**
	 * 【描述】：男运动员总数
	 */
    private Integer manNum;

    /**
	 * 【描述】：女运动员总数
	 */
    private Integer womanNum;

    /**
	 * 【描述】：参赛单位队数
	 */
    private Integer unitTeam;
    //个人参赛总数
    private Integer personTotnum;
    //个人单项参赛数
    private Integer personSinglenum;
    //个人团体参赛数
    private Integer personTeamnum;
    //单位参赛总数
    private Integer unitTotnum;
    //单位单项参赛数
    private Integer unitSinglenum;
    //单位团体参赛数
    private Integer unitTeamnum;

    /**
     * 【描述】：是否可以跨组参赛(默认0：是 1：否)
     */
    private Integer isCrossGroup;
    /**
	 * 【描述】：组内男子年龄上限
	 */
    private String groupManAgeStart;

    /**
	 * 【描述】：组内男子年龄下限
	 */
    private String groupManAgeEnd;
    /**
     * 【描述】：组内女子年龄上限
     */
    private String groupWomanAgeStart;
    
    /**
     * 【描述】：组内女子年龄下限
     */
    private String groupWomanAgeEnd;
    
    /**
	 * 【描述】：年龄相加限制
	 */
    private Integer ageSum;
    //创建人
    private String creaRen;
    //创建时间
    private String creaTime;
    /**
     * 【描述】：届数
     */
    private Integer ersionNum;
    /**
	 * 【描述】：排序号
	 */
    private Integer orderNum;
    /**
     * 【描述】：团体项目人数
     */
    private Integer teamSum;
    /**
     * 【描述】：团体项目男运动员人数(至少)
     */
    private Integer teamManSum;
    /**
     * 【描述】：团体项目女运动员人数(至少)
     */
    private Integer teamWomanSum;
    /**
     * 【描述】：团体项目男性年龄上限
     */
    private String teamManAgeStart;
    /**
     * 【描述】：团体项目男性年龄下限
     */
    private String teamManAgeEnd;
    /**
     * 【描述】：团体项目女性年龄上限
     */
    private String teamWomanAgeStart;
    /**
     * 【描述】：团体项目女性年龄下限
     */
    private String teamWomanAgeEnd;
    
    /**
	 * 【描述】：备注
	 */
    private String backup;

    /**
	 * 【描述】：备用字段1  -- 比赛人群分类（0：青少年组  1：地方组  2：企业事业组）
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
    
    /********************************临时属性***********************************/
    private ClassManager manager;//上级分级分类
    
    public ClassManager(){
    	this.orderNum = Constants.Middle;
    	this.isCrossGroup = Constants.Middle;
    }
    
    public Integer getPersonTotnum() {
		return personTotnum;
	}

	public void setPersonTotnum(Integer personTotnum) {
		this.personTotnum = personTotnum;
	}

	public Integer getPersonSinglenum() {
		return personSinglenum;
	}

	public void setPersonSinglenum(Integer personSinglenum) {
		this.personSinglenum = personSinglenum;
	}

	public Integer getPersonTeamnum() {
		return personTeamnum;
	}

	public void setPersonTeamnum(Integer personTeamnum) {
		this.personTeamnum = personTeamnum;
	}

	public Integer getUnitTotnum() {
		return unitTotnum;
	}

	public void setUnitTotnum(Integer unitTotnum) {
		this.unitTotnum = unitTotnum;
	}

	public Integer getUnitSinglenum() {
		return unitSinglenum;
	}

	public void setUnitSinglenum(Integer unitSinglenum) {
		this.unitSinglenum = unitSinglenum;
	}

	public Integer getUnitTeamnum() {
		return unitTeamnum;
	}

	public void setUnitTeamnum(Integer unitTeamnum) {
		this.unitTeamnum = unitTeamnum;
	}

	public ClassManager getManager() {
		return manager;
	}

	public void setManager(ClassManager manager) {
		this.manager = manager;
	}

	public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName == null ? null : classifyName.trim();
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation == null ? null : abbreviation.trim();
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias == null ? null : alias.trim();
    }

    public Integer getClassCid() {
        return classCid;
    }

    public void setClassCid(Integer classCid) {
        this.classCid = classCid;
    }

    public Integer getAthletesNum() {
        return athletesNum;
    }

    public void setAthletesNum(Integer athletesNum) {
        this.athletesNum = athletesNum;
    }

    public Integer getManNum() {
        return manNum;
    }

    public void setManNum(Integer manNum) {
        this.manNum = manNum;
    }

    public Integer getWomanNum() {
        return womanNum;
    }

    public void setWomanNum(Integer womanNum) {
        this.womanNum = womanNum;
    }

    public Integer getUnitTeam() {
        return unitTeam;
    }

    public void setUnitTeam(Integer unitTeam) {
        this.unitTeam = unitTeam;
    }


    public Integer getIsCrossGroup() {
		return isCrossGroup;
	}

	public void setIsCrossGroup(Integer isCrossGroup) {
		this.isCrossGroup = isCrossGroup;
	}

	public String getGroupManAgeStart() {
		return groupManAgeStart;
	}

	public void setGroupManAgeStart(String groupManAgeStart) {
		this.groupManAgeStart = groupManAgeStart;
	}

	public String getGroupManAgeEnd() {
		return groupManAgeEnd;
	}

	public void setGroupManAgeEnd(String groupManAgeEnd) {
		this.groupManAgeEnd = groupManAgeEnd;
	}

	public String getGroupWomanAgeStart() {
		return groupWomanAgeStart;
	}

	public void setGroupWomanAgeStart(String groupWomanAgeStart) {
		this.groupWomanAgeStart = groupWomanAgeStart;
	}

	public String getGroupWomanAgeEnd() {
		return groupWomanAgeEnd;
	}

	public void setGroupWomanAgeEnd(String groupWomanAgeEnd) {
		this.groupWomanAgeEnd = groupWomanAgeEnd;
	}

	public Integer getAgeSum() {
		return ageSum;
	}

	public void setAgeSum(Integer ageSum) {
		this.ageSum = ageSum;
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

	public Integer getClassType() {
		return classType;
	}

	public void setClassType(Integer classType) {
		this.classType = classType;
	}

	public Integer getErsionNum() {
		return ersionNum;
	}

	public void setErsionNum(Integer ersionNum) {
		this.ersionNum = ersionNum;
	}

	public Integer getTeamSum() {
		return teamSum;
	}

	public void setTeamSum(Integer teamSum) {
		this.teamSum = teamSum;
	}

	public Integer getTeamManSum() {
		return teamManSum;
	}

	public void setTeamManSum(Integer teamManSum) {
		this.teamManSum = teamManSum;
	}

	public Integer getTeamWomanSum() {
		return teamWomanSum;
	}

	public void setTeamWomanSum(Integer teamWomanSum) {
		this.teamWomanSum = teamWomanSum;
	}

	public String getTeamManAgeStart() {
		return teamManAgeStart;
	}

	public void setTeamManAgeStart(String teamManAgeStart) {
		this.teamManAgeStart = teamManAgeStart;
	}

	public String getTeamManAgeEnd() {
		return teamManAgeEnd;
	}

	public void setTeamManAgeEnd(String teamManAgeEnd) {
		this.teamManAgeEnd = teamManAgeEnd;
	}

	public String getTeamWomanAgeStart() {
		return teamWomanAgeStart;
	}

	public void setTeamWomanAgeStart(String teamWomanAgeStart) {
		this.teamWomanAgeStart = teamWomanAgeStart;
	}

	public String getTeamWomanAgeEnd() {
		return teamWomanAgeEnd;
	}

	public void setTeamWomanAgeEnd(String teamWomanAgeEnd) {
		this.teamWomanAgeEnd = teamWomanAgeEnd;
	}

	@Override
	public String toString() {
		return "ClassManager [cid=" + cid + ", classifyName=" + classifyName + ", abbreviation=" + abbreviation
				+ ", alias=" + alias + ", classCid=" + classCid + ", classType=" + classType + ", athletesNum="
				+ athletesNum + ", manNum=" + manNum + ", womanNum=" + womanNum + ", unitTeam=" + unitTeam
				+ ", personTotnum=" + personTotnum + ", personSinglenum=" + personSinglenum + ", personTeamnum="
				+ personTeamnum + ", unitTotnum=" + unitTotnum + ", unitSinglenum=" + unitSinglenum + ", unitTeamnum="
				+ unitTeamnum + ", isCrossGroup=" + isCrossGroup + ", groupManAgeStart=" + groupManAgeStart
				+ ", groupManAgeEnd=" + groupManAgeEnd + ", groupWomanAgeStart=" + groupWomanAgeStart
				+ ", groupWomanAgeEnd=" + groupWomanAgeEnd + ", ageSum=" + ageSum + ", creaRen=" + creaRen
				+ ", creaTime=" + creaTime + ", ersionNum=" + ersionNum + ", orderNum=" + orderNum + ", teamSum="
				+ teamSum + ", teamManSum=" + teamManSum + ", teamWomanSum=" + teamWomanSum + ", teamManAgeStart="
				+ teamManAgeStart + ", teamManAgeEnd=" + teamManAgeEnd + ", teamWomanAgeStart=" + teamWomanAgeStart
				+ ", teamWomanAgeEnd=" + teamWomanAgeEnd + ", backup=" + backup + ", exp1=" + exp1 + ", exp2=" + exp2
				+ ", exp3=" + exp3 + ", exp4=" + exp4 + ", exp5=" + exp5 + ", exp6=" + exp6 + ", manager=" + manager
				+ "]";
	}
}