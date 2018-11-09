package com.yhcrt.entity.Template;

/**
 * 
 * 运动员导入模板
 * @author 陈伟
 * 2017年8月1日 下午2:48:12
 * 版权所有：武汉炎黄创新服务有限公司
 */
public class AthleteTemplate {
	
    /**
   	 * 【描述】：运动员姓名
   	 */
    private String athleteName;

    /**
   	 * 【描述】：运动员别名
   	 */
    private String alias;

    /**
   	 * 【描述】：性别（0：男；1：女）
   	 */
    private String sex;

    /**
   	 * 【描述】：民族
   	 */
    private String nation;

    /**
   	 * 【描述】：出生日期
   	 */
    private String birthday;

    /**
   	 * 【描述】：证件号
   	 */
    private String idCard;

    /**
   	 * 【描述】：是否参加反兴奋剂培训
   	 */
    private String isExamine;
    /**
     * 【描述】：运动员类型  （0：青少年组  1：地方组  2：企业事业组）
     */
    private String athletesType;
    
    
	public String getAthleteName() {
		return athleteName;
	}
	public void setAthleteName(String athleteName) {
		this.athleteName = athleteName;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getIsExamine() {
		return isExamine;
	}
	public void setIsExamine(String isExamine) {
		this.isExamine = isExamine;
	}
	public String getAthletesType() {
		return athletesType;
	}
	public void setAthletesType(String athletesType) {
		this.athletesType = athletesType;
	}

    
    
}