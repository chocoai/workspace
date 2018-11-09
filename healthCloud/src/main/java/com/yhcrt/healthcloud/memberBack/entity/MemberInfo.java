package com.yhcrt.healthcloud.memberBack.entity;

import java.util.Date;

public class MemberInfo {
	
    private Integer cid;	//主键id

    private Integer memberId;	//会员ID

    private String nation;	//民族

    private String marriageStatus;	//婚姻状况(1.未婚 2.有配偶 3.丧偶 4.离婚)

    private String memberType;	//老人类别(1.普通老人  2.退休工人  3.退休干部 4.退休军人 5.其他)

    private Integer age;	//年龄

    private String birthday;	//出生日期

    private String address;	//详细地址

    private String liveStatus;	//居住状况(1.与子女合住 2.独居 3.养老院 4.其他)

    private String moneySource;	//经济来源(1.退休工资 2.养老基金 3.子女供养 4.其他)

    private String salary;	//月收入

    private String securityNum;	//社保号

    private String guarantee;	//医疗保障

    private String disease;	//慢性病

    private String disability;	//失能情况

    private String deformity;	//残疾情况

    private String degree;	//文化程度(1.大专及大专以下 2.本科 3.硕士 4.博士)

    private String company;	//原单位

    private String bloodType;	//血型(1.A型 2.B型 3.O型 4.AB型 5.其他)

    private String cext1;

    private String cext2;

    private String cext3;

    private Integer iext1;

    private Integer iext2;

    private Date dext1;

    private Date dext2;

    private String nativePlace;	//籍贯

    private String district;	//所在地区

    private String politicsStatus;	//政治面貌(1.群众 2.团员 3.党员)

    private String religion;	//宗教信仰

    private String professionalTitle;	//职称

    private String serviceType;	//服务类型(1.无偿服务 2.低偿服务 3.有偿服务 4.社会力量认购服务 5.义工服务 6.残联购买 7.政府购买)

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation == null ? null : nation.trim();
    }

    public String getMarriageStatus() {
        return marriageStatus;
    }

    public void setMarriageStatus(String marriageStatus) {
        this.marriageStatus = marriageStatus == null ? null : marriageStatus.trim();
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType == null ? null : memberType.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday == null ? null : birthday.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getLiveStatus() {
        return liveStatus;
    }

    public void setLiveStatus(String liveStatus) {
        this.liveStatus = liveStatus == null ? null : liveStatus.trim();
    }

    public String getMoneySource() {
        return moneySource;
    }

    public void setMoneySource(String moneySource) {
        this.moneySource = moneySource == null ? null : moneySource.trim();
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary == null ? null : salary.trim();
    }

    public String getSecurityNum() {
        return securityNum;
    }

    public void setSecurityNum(String securityNum) {
        this.securityNum = securityNum == null ? null : securityNum.trim();
    }

    public String getGuarantee() {
        return guarantee;
    }

    public void setGuarantee(String guarantee) {
        this.guarantee = guarantee == null ? null : guarantee.trim();
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease == null ? null : disease.trim();
    }

    public String getDisability() {
        return disability;
    }

    public void setDisability(String disability) {
        this.disability = disability == null ? null : disability.trim();
    }

    public String getDeformity() {
        return deformity;
    }

    public void setDeformity(String deformity) {
        this.deformity = deformity == null ? null : deformity.trim();
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree == null ? null : degree.trim();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType == null ? null : bloodType.trim();
    }

    public String getCext1() {
        return cext1;
    }

    public void setCext1(String cext1) {
        this.cext1 = cext1 == null ? null : cext1.trim();
    }

    public String getCext2() {
        return cext2;
    }

    public void setCext2(String cext2) {
        this.cext2 = cext2 == null ? null : cext2.trim();
    }

    public String getCext3() {
        return cext3;
    }

    public void setCext3(String cext3) {
        this.cext3 = cext3 == null ? null : cext3.trim();
    }

    public Integer getIext1() {
        return iext1;
    }

    public void setIext1(Integer iext1) {
        this.iext1 = iext1;
    }

    public Integer getIext2() {
        return iext2;
    }

    public void setIext2(Integer iext2) {
        this.iext2 = iext2;
    }

    public Date getDext1() {
        return dext1;
    }

    public void setDext1(Date dext1) {
        this.dext1 = dext1;
    }

    public Date getDext2() {
        return dext2;
    }

    public void setDext2(Date dext2) {
        this.dext2 = dext2;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace == null ? null : nativePlace.trim();
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    public String getPoliticsStatus() {
        return politicsStatus;
    }

    public void setPoliticsStatus(String politicsStatus) {
        this.politicsStatus = politicsStatus == null ? null : politicsStatus.trim();
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion == null ? null : religion.trim();
    }

    public String getProfessionalTitle() {
        return professionalTitle;
    }

    public void setProfessionalTitle(String professionalTitle) {
        this.professionalTitle = professionalTitle == null ? null : professionalTitle.trim();
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType == null ? null : serviceType.trim();
    }
}