package com.yhcrt.entity.system;

public class SysConfig  implements java.io.Serializable{
    private Integer cid;

    private String siteName;

    private String sitePame;

    private String siteUrl;

    private String boottomText;

    private String logoUrl;

    private String dbIp;

    private String dbPath;

    private String dbUserName;

    private String dbPassword;

    private String dbName;

    private String dbFilePath;
    
    /**
     * 【描述】：届数
     */
    private Integer ersionNum;

    private String exp1;

    private String exp2;

    private String exp3;

    private String exp4;

    private String exp5;

    private String exp6;
    
    

    @Override
	public String toString() {
		return "SysConfig [cid=" + cid + ", siteName=" + siteName + ", sitePame=" + sitePame + ", siteUrl=" + siteUrl
				+ ", boottomText=" + boottomText + ", logoUrl=" + logoUrl + ", dbIp=" + dbIp + ", dbPath=" + dbPath
				+ ", dbUserName=" + dbUserName + ", dbPassword=" + dbPassword + ", dbName=" + dbName + ", dbFilePath="
				+ dbFilePath + ", exp1=" + exp1 + ", exp2=" + exp2 + ", exp3=" + exp3 + ", exp4=" + exp4 + ", exp5="
				+ exp5 + ", exp6=" + exp6 + "]";
	}

	public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName == null ? null : siteName.trim();
    }

    public String getSitePame() {
        return sitePame;
    }

    public void setSitePame(String sitePame) {
        this.sitePame = sitePame == null ? null : sitePame.trim();
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl == null ? null : siteUrl.trim();
    }

    public String getBoottomText() {
        return boottomText;
    }

    public void setBoottomText(String boottomText) {
        this.boottomText = boottomText == null ? null : boottomText.trim();
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl == null ? null : logoUrl.trim();
    }

    public String getDbIp() {
        return dbIp;
    }

    public void setDbIp(String dbIp) {
        this.dbIp = dbIp == null ? null : dbIp.trim();
    }

    public String getDbPath() {
        return dbPath;
    }

    public void setDbPath(String dbPath) {
        this.dbPath = dbPath == null ? null : dbPath.trim();
    }

    public String getDbUserName() {
        return dbUserName;
    }

    public void setDbUserName(String dbUserName) {
        this.dbUserName = dbUserName == null ? null : dbUserName.trim();
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword == null ? null : dbPassword.trim();
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName == null ? null : dbName.trim();
    }

    public String getDbFilePath() {
        return dbFilePath;
    }

    public void setDbFilePath(String dbFilePath) {
        this.dbFilePath = dbFilePath == null ? null : dbFilePath.trim();
    }

    public String getExp1() {
        return exp1;
    }

    public void setExp1(String exp1) {
        this.exp1 = exp1 == null ? null : exp1.trim();
    }

    public String getExp2() {
        return exp2;
    }

    public void setExp2(String exp2) {
        this.exp2 = exp2 == null ? null : exp2.trim();
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

	public Integer getErsionNum() {
		return ersionNum;
	}

	public void setErsionNum(Integer ersionNum) {
		this.ersionNum = ersionNum;
	}
    
    
}