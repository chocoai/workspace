package com.bigdata.assis.manage.model;

import java.io.Serializable;
import java.util.Date;

public class UserOperate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1597224312835902424L;

	private String id;
	private String USER_ID;
	private String USER_NAME;
	private String USER_ACCOUNT;
	private Integer ALL_LOGIN_NUM; // 全量登录数量，累计授课次数
	private Date LAST_LOGIN_TIME; // 最后登陆时间
	private Integer ALL_LOGIN_USE_TIME;// 全量使用时长（秒）
	private Integer ALL_LOGIN_VALID_NUM;// 全量有效登录次数数量（未去重），累计有效登录数量
	private Integer ALL_LOGIN_VALID_TIME;// 全量有效使用时长（秒）
	private Integer ALL_JXZS_BS_NUM; // 全量板书数量
	private Integer ALL_JXZS_KTSL_NUM; // 全量课堂实录数量
	private Integer ALL_TERMINAL_DZSB_NUM;// 全量电子书包使用数量
	private Integer ALL_TERMINAL_ZZHB_NUM;// 全量掌中黑板使用数量
	private Integer ALL_TERMINAL_XSZNB_NUM;// 全量学生智能笔使用数量
	private Integer ALL_TERMINAL_DTQ_NUM;// 全量答题器使用数量
	private Integer ALL_TERMINAL_YDJT_NUM;// 全量移动讲台使用数量

	private Integer GENDER; // 性别
	private String PHONE_NUMBER;
	private String EMAIL; // 性别
	private String CITY_NAME; // 所属区域
	private String ORGA_NAME; // 所属学校
	private String EVENT_NAME; // 使用功能名称
	private Integer ALL_EVENT_USE_NUM; // 功能使用次数

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUSER_ID() {
		return USER_ID;
	}

	public void setUSER_ID(String uSER_ID) {
		USER_ID = uSER_ID;
	}

	public String getUSER_NAME() {
		return USER_NAME;
	}

	public void setUSER_NAME(String uSER_NAME) {
		USER_NAME = uSER_NAME;
	}

	public String getUSER_ACCOUNT() {
		return USER_ACCOUNT;
	}

	public void setUSER_ACCOUNT(String uSER_ACCOUNT) {
		USER_ACCOUNT = uSER_ACCOUNT;
	}

	public Integer getALL_LOGIN_NUM() {
		return ALL_LOGIN_NUM;
	}

	public void setALL_LOGIN_NUM(Integer aLL_LOGIN_NUM) {
		ALL_LOGIN_NUM = aLL_LOGIN_NUM;
	}

	public Date getLAST_LOGIN_TIME() {
		return LAST_LOGIN_TIME;
	}

	public void setLAST_LOGIN_TIME(Date lAST_LOGIN_TIME) {
		LAST_LOGIN_TIME = lAST_LOGIN_TIME;
	}

	public Integer getALL_LOGIN_USE_TIME() {
		return ALL_LOGIN_USE_TIME;
	}

	public void setALL_LOGIN_USE_TIME(Integer aLL_LOGIN_USE_TIME) {
		ALL_LOGIN_USE_TIME = aLL_LOGIN_USE_TIME;
	}

	public Integer getALL_LOGIN_VALID_NUM() {
		return ALL_LOGIN_VALID_NUM;
	}

	public void setALL_LOGIN_VALID_NUM(Integer aLL_LOGIN_VALID_NUM) {
		ALL_LOGIN_VALID_NUM = aLL_LOGIN_VALID_NUM;
	}

	public Integer getALL_LOGIN_VALID_TIME() {
		return ALL_LOGIN_VALID_TIME;
	}

	public void setALL_LOGIN_VALID_TIME(Integer aLL_LOGIN_VALID_TIME) {
		ALL_LOGIN_VALID_TIME = aLL_LOGIN_VALID_TIME;
	}

	public Integer getALL_JXZS_BS_NUM() {
		return ALL_JXZS_BS_NUM;
	}

	public void setALL_JXZS_BS_NUM(Integer aLL_JXZS_BS_NUM) {
		ALL_JXZS_BS_NUM = aLL_JXZS_BS_NUM;
	}

	public Integer getALL_JXZS_KTSL_NUM() {
		return ALL_JXZS_KTSL_NUM;
	}

	public void setALL_JXZS_KTSL_NUM(Integer aLL_JXZS_KTSL_NUM) {
		ALL_JXZS_KTSL_NUM = aLL_JXZS_KTSL_NUM;
	}

	public Integer getALL_TERMINAL_DZSB_NUM() {
		return ALL_TERMINAL_DZSB_NUM;
	}

	public void setALL_TERMINAL_DZSB_NUM(Integer aLL_TERMINAL_DZSB_NUM) {
		ALL_TERMINAL_DZSB_NUM = aLL_TERMINAL_DZSB_NUM;
	}

	public Integer getALL_TERMINAL_ZZHB_NUM() {
		return ALL_TERMINAL_ZZHB_NUM;
	}

	public void setALL_TERMINAL_ZZHB_NUM(Integer aLL_TERMINAL_ZZHB_NUM) {
		ALL_TERMINAL_ZZHB_NUM = aLL_TERMINAL_ZZHB_NUM;
	}

	public Integer getALL_TERMINAL_XSZNB_NUM() {
		return ALL_TERMINAL_XSZNB_NUM;
	}

	public void setALL_TERMINAL_XSZNB_NUM(Integer aLL_TERMINAL_XSZNB_NUM) {
		ALL_TERMINAL_XSZNB_NUM = aLL_TERMINAL_XSZNB_NUM;
	}

	public Integer getALL_TERMINAL_DTQ_NUM() {
		return ALL_TERMINAL_DTQ_NUM;
	}

	public void setALL_TERMINAL_DTQ_NUM(Integer aLL_TERMINAL_DTQ_NUM) {
		ALL_TERMINAL_DTQ_NUM = aLL_TERMINAL_DTQ_NUM;
	}

	public Integer getALL_TERMINAL_YDJT_NUM() {
		return ALL_TERMINAL_YDJT_NUM;
	}

	public void setALL_TERMINAL_YDJT_NUM(Integer aLL_TERMINAL_YDJT_NUM) {
		ALL_TERMINAL_YDJT_NUM = aLL_TERMINAL_YDJT_NUM;
	}

	public Integer getGENDER() {
		return GENDER;
	}

	public void setGENDER(Integer gENDER) {
		GENDER = gENDER;
	}

	public String getPHONE_NUMBER() {
		return PHONE_NUMBER;
	}

	public void setPHONE_NUMBER(String pHONE_NUMBER) {
		PHONE_NUMBER = pHONE_NUMBER;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}

	public String getCITY_NAME() {
		return CITY_NAME;
	}

	public void setCITY_NAME(String cITY_NAME) {
		CITY_NAME = cITY_NAME;
	}

	public String getORGA_NAME() {
		return ORGA_NAME;
	}

	public void setORGA_NAME(String oRGA_NAME) {
		ORGA_NAME = oRGA_NAME;
	}

	public String getEVENT_NAME() {
		return EVENT_NAME;
	}

	public void setEVENT_NAME(String eVENT_NAME) {
		EVENT_NAME = eVENT_NAME;
	}

	public Integer getALL_EVENT_USE_NUM() {
		return ALL_EVENT_USE_NUM;
	}

	public void setALL_EVENT_USE_NUM(Integer aLL_EVENT_USE_NUM) {
		ALL_EVENT_USE_NUM = aLL_EVENT_USE_NUM;
	}

}
