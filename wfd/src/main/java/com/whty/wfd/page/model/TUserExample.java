package com.whty.wfd.page.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TUserExample {
	/**
	 * t_user
	 */
	protected String orderByClause;

	/**
	 * t_user
	 */
	protected boolean distinct;

	/**
	 * t_user
	 */
	protected List<Criteria> oredCriteria;

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	public TUserExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * t_user 2018-08-16
	 */
	protected abstract static class GeneratedCriteria {
		protected List<Criterion> criteria;

		protected GeneratedCriteria() {
			super();
			criteria = new ArrayList<Criterion>();
		}

		public boolean isValid() {
			return criteria.size() > 0;
		}

		public List<Criterion> getAllCriteria() {
			return criteria;
		}

		public List<Criterion> getCriteria() {
			return criteria;
		}

		protected void addCriterion(String condition) {
			if (condition == null) {
				throw new RuntimeException("Value for condition cannot be null");
			}
			criteria.add(new Criterion(condition));
		}

		protected void addCriterion(String condition, Object value, String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value));
		}

		protected void addCriterion(String condition, Object value1, Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value1, value2));
		}

		public Criteria andIdIsNull() {
			addCriterion("id is null");
			return (Criteria) this;
		}

		public Criteria andIdIsNotNull() {
			addCriterion("id is not null");
			return (Criteria) this;
		}

		public Criteria andIdEqualTo(Integer value) {
			addCriterion("id =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(Integer value) {
			addCriterion("id <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(Integer value) {
			addCriterion("id >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("id >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(Integer value) {
			addCriterion("id <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(Integer value) {
			addCriterion("id <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<Integer> values) {
			addCriterion("id in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<Integer> values) {
			addCriterion("id not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(Integer value1, Integer value2) {
			addCriterion("id between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(Integer value1, Integer value2) {
			addCriterion("id not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andNameIsNull() {
			addCriterion("name is null");
			return (Criteria) this;
		}

		public Criteria andNameIsNotNull() {
			addCriterion("name is not null");
			return (Criteria) this;
		}

		public Criteria andNameEqualTo(String value) {
			addCriterion("name =", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotEqualTo(String value) {
			addCriterion("name <>", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameGreaterThan(String value) {
			addCriterion("name >", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameGreaterThanOrEqualTo(String value) {
			addCriterion("name >=", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLessThan(String value) {
			addCriterion("name <", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLessThanOrEqualTo(String value) {
			addCriterion("name <=", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameLike(String value) {
			addCriterion("name like", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotLike(String value) {
			addCriterion("name not like", value, "name");
			return (Criteria) this;
		}

		public Criteria andNameIn(List<String> values) {
			addCriterion("name in", values, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotIn(List<String> values) {
			addCriterion("name not in", values, "name");
			return (Criteria) this;
		}

		public Criteria andNameBetween(String value1, String value2) {
			addCriterion("name between", value1, value2, "name");
			return (Criteria) this;
		}

		public Criteria andNameNotBetween(String value1, String value2) {
			addCriterion("name not between", value1, value2, "name");
			return (Criteria) this;
		}

		public Criteria andAccountIsNull() {
			addCriterion("account is null");
			return (Criteria) this;
		}

		public Criteria andAccountIsNotNull() {
			addCriterion("account is not null");
			return (Criteria) this;
		}

		public Criteria andAccountEqualTo(String value) {
			addCriterion("account =", value, "account");
			return (Criteria) this;
		}

		public Criteria andAccountNotEqualTo(String value) {
			addCriterion("account <>", value, "account");
			return (Criteria) this;
		}

		public Criteria andAccountGreaterThan(String value) {
			addCriterion("account >", value, "account");
			return (Criteria) this;
		}

		public Criteria andAccountGreaterThanOrEqualTo(String value) {
			addCriterion("account >=", value, "account");
			return (Criteria) this;
		}

		public Criteria andAccountLessThan(String value) {
			addCriterion("account <", value, "account");
			return (Criteria) this;
		}

		public Criteria andAccountLessThanOrEqualTo(String value) {
			addCriterion("account <=", value, "account");
			return (Criteria) this;
		}

		public Criteria andAccountLike(String value) {
			addCriterion("account like", value, "account");
			return (Criteria) this;
		}

		public Criteria andAccountNotLike(String value) {
			addCriterion("account not like", value, "account");
			return (Criteria) this;
		}

		public Criteria andAccountIn(List<String> values) {
			addCriterion("account in", values, "account");
			return (Criteria) this;
		}

		public Criteria andAccountNotIn(List<String> values) {
			addCriterion("account not in", values, "account");
			return (Criteria) this;
		}

		public Criteria andAccountBetween(String value1, String value2) {
			addCriterion("account between", value1, value2, "account");
			return (Criteria) this;
		}

		public Criteria andAccountNotBetween(String value1, String value2) {
			addCriterion("account not between", value1, value2, "account");
			return (Criteria) this;
		}

		public Criteria andCreateTimeIsNull() {
			addCriterion("create_time is null");
			return (Criteria) this;
		}

		public Criteria andCreateTimeIsNotNull() {
			addCriterion("create_time is not null");
			return (Criteria) this;
		}

		public Criteria andCreateTimeEqualTo(Date value) {
			addCriterion("create_time =", value, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeNotEqualTo(Date value) {
			addCriterion("create_time <>", value, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeGreaterThan(Date value) {
			addCriterion("create_time >", value, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
			addCriterion("create_time >=", value, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeLessThan(Date value) {
			addCriterion("create_time <", value, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
			addCriterion("create_time <=", value, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeIn(List<Date> values) {
			addCriterion("create_time in", values, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeNotIn(List<Date> values) {
			addCriterion("create_time not in", values, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeBetween(Date value1, Date value2) {
			addCriterion("create_time between", value1, value2, "createTime");
			return (Criteria) this;
		}

		public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
			addCriterion("create_time not between", value1, value2, "createTime");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeIsNull() {
			addCriterion("update_time is null");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeIsNotNull() {
			addCriterion("update_time is not null");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeEqualTo(Date value) {
			addCriterion("update_time =", value, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeNotEqualTo(Date value) {
			addCriterion("update_time <>", value, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeGreaterThan(Date value) {
			addCriterion("update_time >", value, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
			addCriterion("update_time >=", value, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeLessThan(Date value) {
			addCriterion("update_time <", value, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
			addCriterion("update_time <=", value, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeIn(List<Date> values) {
			addCriterion("update_time in", values, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeNotIn(List<Date> values) {
			addCriterion("update_time not in", values, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeBetween(Date value1, Date value2) {
			addCriterion("update_time between", value1, value2, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
			addCriterion("update_time not between", value1, value2, "updateTime");
			return (Criteria) this;
		}

		public Criteria andUserTypeIsNull() {
			addCriterion("user_type is null");
			return (Criteria) this;
		}

		public Criteria andUserTypeIsNotNull() {
			addCriterion("user_type is not null");
			return (Criteria) this;
		}

		public Criteria andUserTypeEqualTo(String value) {
			addCriterion("user_type =", value, "userType");
			return (Criteria) this;
		}

		public Criteria andUserTypeNotEqualTo(String value) {
			addCriterion("user_type <>", value, "userType");
			return (Criteria) this;
		}

		public Criteria andUserTypeGreaterThan(String value) {
			addCriterion("user_type >", value, "userType");
			return (Criteria) this;
		}

		public Criteria andUserTypeGreaterThanOrEqualTo(String value) {
			addCriterion("user_type >=", value, "userType");
			return (Criteria) this;
		}

		public Criteria andUserTypeLessThan(String value) {
			addCriterion("user_type <", value, "userType");
			return (Criteria) this;
		}

		public Criteria andUserTypeLessThanOrEqualTo(String value) {
			addCriterion("user_type <=", value, "userType");
			return (Criteria) this;
		}

		public Criteria andUserTypeLike(String value) {
			addCriterion("user_type like", value, "userType");
			return (Criteria) this;
		}

		public Criteria andUserTypeNotLike(String value) {
			addCriterion("user_type not like", value, "userType");
			return (Criteria) this;
		}

		public Criteria andUserTypeIn(List<String> values) {
			addCriterion("user_type in", values, "userType");
			return (Criteria) this;
		}

		public Criteria andUserTypeNotIn(List<String> values) {
			addCriterion("user_type not in", values, "userType");
			return (Criteria) this;
		}

		public Criteria andUserTypeBetween(String value1, String value2) {
			addCriterion("user_type between", value1, value2, "userType");
			return (Criteria) this;
		}

		public Criteria andUserTypeNotBetween(String value1, String value2) {
			addCriterion("user_type not between", value1, value2, "userType");
			return (Criteria) this;
		}

		public Criteria andPersonIdIsNull() {
			addCriterion("person_id is null");
			return (Criteria) this;
		}

		public Criteria andPersonIdIsNotNull() {
			addCriterion("person_id is not null");
			return (Criteria) this;
		}

		public Criteria andPersonIdEqualTo(String value) {
			addCriterion("person_id =", value, "personId");
			return (Criteria) this;
		}

		public Criteria andPersonIdNotEqualTo(String value) {
			addCriterion("person_id <>", value, "personId");
			return (Criteria) this;
		}

		public Criteria andPersonIdGreaterThan(String value) {
			addCriterion("person_id >", value, "personId");
			return (Criteria) this;
		}

		public Criteria andPersonIdGreaterThanOrEqualTo(String value) {
			addCriterion("person_id >=", value, "personId");
			return (Criteria) this;
		}

		public Criteria andPersonIdLessThan(String value) {
			addCriterion("person_id <", value, "personId");
			return (Criteria) this;
		}

		public Criteria andPersonIdLessThanOrEqualTo(String value) {
			addCriterion("person_id <=", value, "personId");
			return (Criteria) this;
		}

		public Criteria andPersonIdLike(String value) {
			addCriterion("person_id like", value, "personId");
			return (Criteria) this;
		}

		public Criteria andPersonIdNotLike(String value) {
			addCriterion("person_id not like", value, "personId");
			return (Criteria) this;
		}

		public Criteria andPersonIdIn(List<String> values) {
			addCriterion("person_id in", values, "personId");
			return (Criteria) this;
		}

		public Criteria andPersonIdNotIn(List<String> values) {
			addCriterion("person_id not in", values, "personId");
			return (Criteria) this;
		}

		public Criteria andPersonIdBetween(String value1, String value2) {
			addCriterion("person_id between", value1, value2, "personId");
			return (Criteria) this;
		}

		public Criteria andPersonIdNotBetween(String value1, String value2) {
			addCriterion("person_id not between", value1, value2, "personId");
			return (Criteria) this;
		}

		public Criteria andPlatformCodeIsNull() {
			addCriterion("platform_code is null");
			return (Criteria) this;
		}

		public Criteria andPlatformCodeIsNotNull() {
			addCriterion("platform_code is not null");
			return (Criteria) this;
		}

		public Criteria andPlatformCodeEqualTo(String value) {
			addCriterion("platform_code =", value, "platformCode");
			return (Criteria) this;
		}

		public Criteria andPlatformCodeNotEqualTo(String value) {
			addCriterion("platform_code <>", value, "platformCode");
			return (Criteria) this;
		}

		public Criteria andPlatformCodeGreaterThan(String value) {
			addCriterion("platform_code >", value, "platformCode");
			return (Criteria) this;
		}

		public Criteria andPlatformCodeGreaterThanOrEqualTo(String value) {
			addCriterion("platform_code >=", value, "platformCode");
			return (Criteria) this;
		}

		public Criteria andPlatformCodeLessThan(String value) {
			addCriterion("platform_code <", value, "platformCode");
			return (Criteria) this;
		}

		public Criteria andPlatformCodeLessThanOrEqualTo(String value) {
			addCriterion("platform_code <=", value, "platformCode");
			return (Criteria) this;
		}

		public Criteria andPlatformCodeLike(String value) {
			addCriterion("platform_code like", value, "platformCode");
			return (Criteria) this;
		}

		public Criteria andPlatformCodeNotLike(String value) {
			addCriterion("platform_code not like", value, "platformCode");
			return (Criteria) this;
		}

		public Criteria andPlatformCodeIn(List<String> values) {
			addCriterion("platform_code in", values, "platformCode");
			return (Criteria) this;
		}

		public Criteria andPlatformCodeNotIn(List<String> values) {
			addCriterion("platform_code not in", values, "platformCode");
			return (Criteria) this;
		}

		public Criteria andPlatformCodeBetween(String value1, String value2) {
			addCriterion("platform_code between", value1, value2, "platformCode");
			return (Criteria) this;
		}

		public Criteria andPlatformCodeNotBetween(String value1, String value2) {
			addCriterion("platform_code not between", value1, value2, "platformCode");
			return (Criteria) this;
		}

		public Criteria andLoginPlatformCodeIsNull() {
			addCriterion("login_platform_code is null");
			return (Criteria) this;
		}

		public Criteria andLoginPlatformCodeIsNotNull() {
			addCriterion("login_platform_code is not null");
			return (Criteria) this;
		}

		public Criteria andLoginPlatformCodeEqualTo(String value) {
			addCriterion("login_platform_code =", value, "loginPlatformCode");
			return (Criteria) this;
		}

		public Criteria andLoginPlatformCodeNotEqualTo(String value) {
			addCriterion("login_platform_code <>", value, "loginPlatformCode");
			return (Criteria) this;
		}

		public Criteria andLoginPlatformCodeGreaterThan(String value) {
			addCriterion("login_platform_code >", value, "loginPlatformCode");
			return (Criteria) this;
		}

		public Criteria andLoginPlatformCodeGreaterThanOrEqualTo(String value) {
			addCriterion("login_platform_code >=", value, "loginPlatformCode");
			return (Criteria) this;
		}

		public Criteria andLoginPlatformCodeLessThan(String value) {
			addCriterion("login_platform_code <", value, "loginPlatformCode");
			return (Criteria) this;
		}

		public Criteria andLoginPlatformCodeLessThanOrEqualTo(String value) {
			addCriterion("login_platform_code <=", value, "loginPlatformCode");
			return (Criteria) this;
		}

		public Criteria andLoginPlatformCodeLike(String value) {
			addCriterion("login_platform_code like", value, "loginPlatformCode");
			return (Criteria) this;
		}

		public Criteria andLoginPlatformCodeNotLike(String value) {
			addCriterion("login_platform_code not like", value, "loginPlatformCode");
			return (Criteria) this;
		}

		public Criteria andLoginPlatformCodeIn(List<String> values) {
			addCriterion("login_platform_code in", values, "loginPlatformCode");
			return (Criteria) this;
		}

		public Criteria andLoginPlatformCodeNotIn(List<String> values) {
			addCriterion("login_platform_code not in", values, "loginPlatformCode");
			return (Criteria) this;
		}

		public Criteria andLoginPlatformCodeBetween(String value1, String value2) {
			addCriterion("login_platform_code between", value1, value2, "loginPlatformCode");
			return (Criteria) this;
		}

		public Criteria andLoginPlatformCodeNotBetween(String value1, String value2) {
			addCriterion("login_platform_code not between", value1, value2, "loginPlatformCode");
			return (Criteria) this;
		}

		public Criteria andSchoolIdIsNull() {
			addCriterion("school_id is null");
			return (Criteria) this;
		}

		public Criteria andSchoolIdIsNotNull() {
			addCriterion("school_id is not null");
			return (Criteria) this;
		}

		public Criteria andSchoolIdEqualTo(Integer value) {
			addCriterion("school_id =", value, "schoolId");
			return (Criteria) this;
		}

		public Criteria andSchoolIdNotEqualTo(Integer value) {
			addCriterion("school_id <>", value, "schoolId");
			return (Criteria) this;
		}

		public Criteria andSchoolIdGreaterThan(Integer value) {
			addCriterion("school_id >", value, "schoolId");
			return (Criteria) this;
		}

		public Criteria andSchoolIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("school_id >=", value, "schoolId");
			return (Criteria) this;
		}

		public Criteria andSchoolIdLessThan(Integer value) {
			addCriterion("school_id <", value, "schoolId");
			return (Criteria) this;
		}

		public Criteria andSchoolIdLessThanOrEqualTo(Integer value) {
			addCriterion("school_id <=", value, "schoolId");
			return (Criteria) this;
		}

		public Criteria andSchoolIdIn(List<Integer> values) {
			addCriterion("school_id in", values, "schoolId");
			return (Criteria) this;
		}

		public Criteria andSchoolIdNotIn(List<Integer> values) {
			addCriterion("school_id not in", values, "schoolId");
			return (Criteria) this;
		}

		public Criteria andSchoolIdBetween(Integer value1, Integer value2) {
			addCriterion("school_id between", value1, value2, "schoolId");
			return (Criteria) this;
		}

		public Criteria andSchoolIdNotBetween(Integer value1, Integer value2) {
			addCriterion("school_id not between", value1, value2, "schoolId");
			return (Criteria) this;
		}
	}

	/**
	 * t_user
	 */
	public static class Criteria extends GeneratedCriteria {

		protected Criteria() {
			super();
		}
	}

	/**
	 * t_user 2018-08-16
	 */
	public static class Criterion {
		private String condition;

		private Object value;

		private Object secondValue;

		private boolean noValue;

		private boolean singleValue;

		private boolean betweenValue;

		private boolean listValue;

		private String typeHandler;

		public String getCondition() {
			return condition;
		}

		public Object getValue() {
			return value;
		}

		public Object getSecondValue() {
			return secondValue;
		}

		public boolean isNoValue() {
			return noValue;
		}

		public boolean isSingleValue() {
			return singleValue;
		}

		public boolean isBetweenValue() {
			return betweenValue;
		}

		public boolean isListValue() {
			return listValue;
		}

		public String getTypeHandler() {
			return typeHandler;
		}

		protected Criterion(String condition) {
			super();
			this.condition = condition;
			this.typeHandler = null;
			this.noValue = true;
		}

		protected Criterion(String condition, Object value, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.typeHandler = typeHandler;
			if (value instanceof List<?>) {
				this.listValue = true;
			} else {
				this.singleValue = true;
			}
		}

		protected Criterion(String condition, Object value) {
			this(condition, value, null);
		}

		protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.secondValue = secondValue;
			this.typeHandler = typeHandler;
			this.betweenValue = true;
		}

		protected Criterion(String condition, Object value, Object secondValue) {
			this(condition, value, secondValue, null);
		}
	}
}