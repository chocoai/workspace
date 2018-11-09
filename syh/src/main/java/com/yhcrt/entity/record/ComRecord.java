package com.yhcrt.entity.record;

import com.yhcrt.entity.project.ProjectManager;
/**
 * 竞赛记录的实体类
 * @author heyun
 * 2017年5月11日
 * 版权所有：武汉炎黄创新科技服务有限公司
 */
public class ComRecord {
	
	/**
   	 * 【描述】：竞赛记录CID
   	 */
    private Integer cid;

    /**
   	 * 【描述】：比赛项目CID
   	 */
    private Integer projectCid;

    /**
   	 * 【描述】：最高纪录保持者
   	 */
    private String recordHolder;
    
    /**
   	 * 【描述】：最高纪录成绩
   	 */
    private String recordScore;

    /**
   	 * 【描述】：最高纪录创造时间
   	 */
    private String recordCreatetime;

    /**
   	 * 【描述】：排序号
   	 */
    private Integer orderNum;

    /**
   	 * 【描述】：备注
   	 */
    private String backup;
    
    /**
   	 * 【描述】：版本号
   	 */
    private Integer versionNum;
    
    /**
     * 【描述】：操作人
     */
    private String creaRen;
    
    /**
     * 【描述】：操作时间
     */
    private String creaTime;

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
    
    private ProjectManager projectManager;  //项目信息
    
    private Integer preCid;//被刷新记录
    
    public Integer getPreCid() {
		return preCid;
	}

	public void setPreCid(Integer preCid) {
		this.preCid = preCid;
	}

	public ProjectManager getProjectManager() {
		return projectManager;
	}

	public String getRecordScore() {
		return recordScore;
	}

	public void setRecordScore(String recordScore) {
		this.recordScore = recordScore;
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

	public void setProjectManager(ProjectManager projectManager) {
		this.projectManager = projectManager;
	}

	public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

	public Integer getProjectCid() {
        return projectCid;
    }

    public void setProjectCid(Integer projectCid) {
        this.projectCid = projectCid;
    }

    public String getRecordHolder() {
        return recordHolder;
    }

    public void setRecordHolder(String recordHolder) {
        this.recordHolder = recordHolder == null ? null : recordHolder.trim();
    }

    public String getRecordCreatetime() {
        return recordCreatetime;
    }

    public void setRecordCreatetime(String recordCreatetime) {
        this.recordCreatetime = recordCreatetime == null ? null : recordCreatetime.trim();
    }

    public Integer getVersionNum() {
        return versionNum;
    }

    public void setVersionNum(Integer versionNum) {
        this.versionNum = versionNum;
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
        this.backup = backup;
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
		return "ComRecord [cid=" + cid + ", projectCid=" + projectCid + ", recordHolder=" + recordHolder
				+ ", recordCreatetime=" + recordCreatetime + ", orderNum=" + orderNum + ", backup=" + backup
				+ ", versionNum=" + versionNum + ", exp1=" + exp1 + ", exp2=" + exp2 + ", exp3=" + exp3 + ", exp4="
				+ exp4 + ", exp5=" + exp5 + ", exp6=" + exp6 + ", projectManager=" + projectManager + "]";
	}
}