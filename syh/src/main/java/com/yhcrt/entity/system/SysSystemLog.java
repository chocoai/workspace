package com.yhcrt.entity.system;

public class SysSystemLog implements java.io.Serializable {
    private Integer cid;

    private String opeartionContent;

    private String opeartionDetail;

    private String opeartionTime;

    private Integer opeartionState;

    private String opeartionUser;

    private String opeartionIp;
    
    private Integer opeartionType;
    
    /**
     * 【描述】：届数
     */
    private Integer ersionNum;
    
    
    //----------------------------------------------------------------------//
    
    private String timeStart;//开始时间
    private String timeEnd;//结束时间
    

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getOpeartionContent() {
        return opeartionContent;
    }

    public void setOpeartionContent(String opeartionContent) {
        this.opeartionContent = opeartionContent == null ? null : opeartionContent.trim();
    }

    public String getOpeartionDetail() {
        return opeartionDetail;
    }

    public void setOpeartionDetail(String opeartionDetail) {
        this.opeartionDetail = opeartionDetail == null ? null : opeartionDetail.trim();
    }

    public String getOpeartionTime() {
        return opeartionTime;
    }

    public void setOpeartionTime(String opeartionTime) {
        this.opeartionTime = opeartionTime == null ? null : opeartionTime.trim();
    }

    public Integer getOpeartionState() {
        return opeartionState;
    }

    public void setOpeartionState(Integer opeartionState) {
        this.opeartionState = opeartionState;
    }

    public String getOpeartionUser() {
        return opeartionUser;
    }

    public void setOpeartionUser(String opeartionUser) {
        this.opeartionUser = opeartionUser == null ? null : opeartionUser.trim();
    }

    public String getOpeartionIp() {
        return opeartionIp;
    }

    public void setOpeartionIp(String opeartionIp) {
        this.opeartionIp = opeartionIp == null ? null : opeartionIp.trim();
    }

	public String getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}

	public String getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}

	public Integer getOpeartionType() {
		return opeartionType;
	}

	public void setOpeartionType(Integer opeartionType) {
		this.opeartionType = opeartionType;
	}

	public Integer getErsionNum() {
		return ersionNum;
	}

	public void setErsionNum(Integer ersionNum) {
		this.ersionNum = ersionNum;
	}

	@Override
	public String toString() {
		return "UserOperation [cid=" + cid + ", opeartionContent=" + opeartionContent + ", opeartionDetail="
				+ opeartionDetail + ", opeartionTime=" + opeartionTime + ", opeartionState=" + opeartionState
				+ ", opeartionUser=" + opeartionUser + ", opeartionIp=" + opeartionIp + "]";
	}
}