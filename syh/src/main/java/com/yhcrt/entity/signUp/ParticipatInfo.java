package com.yhcrt.entity.signUp;

import java.util.List;
import java.util.Map;

import com.yhcrt.entity.project.ProjectManager;
import com.yhcrt.utils.Constants;

/**
 * 
 * 参赛信息表(存储代表团队/个人的参赛信息)
 * @author 陈伟
 * 2017年7月10日 上午10:46:57
 * 版权所有：武汉炎黄创新服务有限公司
 */
public class ParticipatInfo {
	/**
   	 * 【描述】：主键CID
   	 */
	private Integer cid;
	/**
   	 * 【描述】：项目ID
   	 */
    private Integer pid;
	/**
   	 * 【描述】：是否代表单位参赛
   	 */
    private Integer isperUnit;
	/**
   	 * 【描述】：是否组合队
   	 */
    private Integer iscombinationTeam;
	/**
   	 * 【描述】：是否个人赛
   	 */
    private Integer isindividual;
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
    private ProjectManager projectManager;
    
    private List<ParticipatDetail> participatDetails;
    
    private String participatNames;//运动员名字的字符串集合
    
    private String participatCids;//运动员ID的字符串集合
    
    private String unitNames;//单位名字的字符串集合
    
    private String unitCids;//单位ID的字符串集合
    
    private String projectNames;//完整的项目名
    
    private Map<String,String> scoreInfo;//成绩信息
    
    
    
    
    public Map<String, String> getScoreInfo() {

		return scoreInfo;
	}


	public void setScoreInfo(Map<String, String> scoreInfo) {

		this.scoreInfo = scoreInfo;
	}


	public ParticipatInfo() {
		this.orderNum = Constants.Middle;
		this.isperUnit = Constants.Middle_1;
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

    public Integer getIsperUnit() {
        return isperUnit;
    }

    public void setIsperUnit(Integer isperUnit) {
        this.isperUnit = isperUnit;
    }

    public Integer getIscombinationTeam() {
        return iscombinationTeam;
    }

    public void setIscombinationTeam(Integer iscombinationTeam) {
        this.iscombinationTeam = iscombinationTeam;
    }

    public Integer getIsindividual() {
        return isindividual;
    }
    public String getIsindividualStr() {
    	if(getProjectManager()!=null && getProjectManager().getIsTeam()==Constants.Middle){
    		return "个人赛";
    	}else{
    		return "团体赛";
    	}
    }
    
    public void setIsindividual(Integer isindividual) {
        this.isindividual = isindividual;
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


	public ProjectManager getProjectManager() {
		return projectManager;
	}


	public void setProjectManager(ProjectManager projectManager) {
		this.projectManager = projectManager;
	}


	public List<ParticipatDetail> getParticipatDetails() {
		return participatDetails;
	}


	public void setParticipatDetails(List<ParticipatDetail> participatDetails) {
		this.participatDetails = participatDetails;
	}


	public String getParticipatNames() {
		return participatNames;
	}


	public void setParticipatNames(String participatNames) {
		this.participatNames = participatNames;
	}


	public String getUnitNames() {
		return unitNames;
	}


	public void setUnitNames(String unitNames) {
		this.unitNames = unitNames;
	}


	public String getProjectNames() {
		return projectNames;
	}


	public void setProjectNames(String projectNames) {
		this.projectNames = projectNames;
	}


	public String getUnitCids() {
		return unitCids;
	}


	public void setUnitCids(String unitCids) {
		this.unitCids = unitCids;
	}


	public String getParticipatCids() {
		return participatCids;
	}


	public void setParticipatCids(String participatCids) {
		this.participatCids = participatCids;
	}
    
}