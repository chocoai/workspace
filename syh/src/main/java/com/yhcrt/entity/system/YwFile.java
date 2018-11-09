package com.yhcrt.entity.system;

public class YwFile  implements java.io.Serializable{
	private Integer cid;

    private Integer refId;

    private String moduleCode;
    
    private String fileType;

    private String fileName;

    private String filePath;

    private String creaTime;

    private String creaRen;

    private Integer orderNum;

    private String backup;

    private Integer exp1;

    private Integer exp2;

    private String exp3;

    private String exp4;

    private String exp5;

    private String exp6;

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType == null ? null : fileType.trim();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
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
        this.creaRen = creaRen == null ? null : creaRen.trim();
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
    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getRefId() {
        return refId;
    }

    public void setRefId(Integer refId) {
        this.refId = refId;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode == null ? null : moduleCode.trim();
    }

	@Override
	public String toString() {
		return "YwFile [cid=" + cid + ", refId=" + refId + ", moduleCode=" + moduleCode + ", fileType=" + fileType
				+ ", fileName=" + fileName + ", filePath=" + filePath + ", creaTime=" + creaTime + ", creaRen="
				+ creaRen + ", orderNum=" + orderNum + ", backup=" + backup + ", exp1=" + exp1 + ", exp2=" + exp2
				+ ", exp3=" + exp3 + ", exp4=" + exp4 + ", exp5=" + exp5 + ", exp6=" + exp6 + "]";
	}
}