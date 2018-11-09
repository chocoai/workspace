package com.whty.wfd.page.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TPlatePostEditExample {
	/**
	 * t_plate_post_edit
	 */
	protected String orderByClause;

	/**
	 * t_plate_post_edit
	 */
	protected boolean distinct;

	/**
	 * t_plate_post_edit
	 */
	protected List<Criteria> oredCriteria;

	/**
	 *
	 * @mbg.generated 2018-10-10
	 */
	public TPlatePostEditExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 *
	 * @mbg.generated 2018-10-10
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 *
	 * @mbg.generated 2018-10-10
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 *
	 * @mbg.generated 2018-10-10
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 *
	 * @mbg.generated 2018-10-10
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 *
	 * @mbg.generated 2018-10-10
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 *
	 * @mbg.generated 2018-10-10
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 *
	 * @mbg.generated 2018-10-10
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 *
	 * @mbg.generated 2018-10-10
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
	 * @mbg.generated 2018-10-10
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 *
	 * @mbg.generated 2018-10-10
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * t_plate_post_edit 2018-10-10
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

		public Criteria andIdEqualTo(String value) {
			addCriterion("id =", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotEqualTo(String value) {
			addCriterion("id <>", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThan(String value) {
			addCriterion("id >", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdGreaterThanOrEqualTo(String value) {
			addCriterion("id >=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThan(String value) {
			addCriterion("id <", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLessThanOrEqualTo(String value) {
			addCriterion("id <=", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdLike(String value) {
			addCriterion("id like", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotLike(String value) {
			addCriterion("id not like", value, "id");
			return (Criteria) this;
		}

		public Criteria andIdIn(List<String> values) {
			addCriterion("id in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotIn(List<String> values) {
			addCriterion("id not in", values, "id");
			return (Criteria) this;
		}

		public Criteria andIdBetween(String value1, String value2) {
			addCriterion("id between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andIdNotBetween(String value1, String value2) {
			addCriterion("id not between", value1, value2, "id");
			return (Criteria) this;
		}

		public Criteria andPlateIdIsNull() {
			addCriterion("plate_id is null");
			return (Criteria) this;
		}

		public Criteria andPlateIdIsNotNull() {
			addCriterion("plate_id is not null");
			return (Criteria) this;
		}

		public Criteria andPlateIdEqualTo(String value) {
			addCriterion("plate_id =", value, "plateId");
			return (Criteria) this;
		}

		public Criteria andPlateIdNotEqualTo(String value) {
			addCriterion("plate_id <>", value, "plateId");
			return (Criteria) this;
		}

		public Criteria andPlateIdGreaterThan(String value) {
			addCriterion("plate_id >", value, "plateId");
			return (Criteria) this;
		}

		public Criteria andPlateIdGreaterThanOrEqualTo(String value) {
			addCriterion("plate_id >=", value, "plateId");
			return (Criteria) this;
		}

		public Criteria andPlateIdLessThan(String value) {
			addCriterion("plate_id <", value, "plateId");
			return (Criteria) this;
		}

		public Criteria andPlateIdLessThanOrEqualTo(String value) {
			addCriterion("plate_id <=", value, "plateId");
			return (Criteria) this;
		}

		public Criteria andPlateIdLike(String value) {
			addCriterion("plate_id like", value, "plateId");
			return (Criteria) this;
		}

		public Criteria andPlateIdNotLike(String value) {
			addCriterion("plate_id not like", value, "plateId");
			return (Criteria) this;
		}

		public Criteria andPlateIdIn(List<String> values) {
			addCriterion("plate_id in", values, "plateId");
			return (Criteria) this;
		}

		public Criteria andPlateIdNotIn(List<String> values) {
			addCriterion("plate_id not in", values, "plateId");
			return (Criteria) this;
		}

		public Criteria andPlateIdBetween(String value1, String value2) {
			addCriterion("plate_id between", value1, value2, "plateId");
			return (Criteria) this;
		}

		public Criteria andPlateIdNotBetween(String value1, String value2) {
			addCriterion("plate_id not between", value1, value2, "plateId");
			return (Criteria) this;
		}

		public Criteria andCreatorIsNull() {
			addCriterion("creator is null");
			return (Criteria) this;
		}

		public Criteria andCreatorIsNotNull() {
			addCriterion("creator is not null");
			return (Criteria) this;
		}

		public Criteria andCreatorEqualTo(Integer value) {
			addCriterion("creator =", value, "creator");
			return (Criteria) this;
		}

		public Criteria andCreatorNotEqualTo(Integer value) {
			addCriterion("creator <>", value, "creator");
			return (Criteria) this;
		}

		public Criteria andCreatorGreaterThan(Integer value) {
			addCriterion("creator >", value, "creator");
			return (Criteria) this;
		}

		public Criteria andCreatorGreaterThanOrEqualTo(Integer value) {
			addCriterion("creator >=", value, "creator");
			return (Criteria) this;
		}

		public Criteria andCreatorLessThan(Integer value) {
			addCriterion("creator <", value, "creator");
			return (Criteria) this;
		}

		public Criteria andCreatorLessThanOrEqualTo(Integer value) {
			addCriterion("creator <=", value, "creator");
			return (Criteria) this;
		}

		public Criteria andCreatorIn(List<Integer> values) {
			addCriterion("creator in", values, "creator");
			return (Criteria) this;
		}

		public Criteria andCreatorNotIn(List<Integer> values) {
			addCriterion("creator not in", values, "creator");
			return (Criteria) this;
		}

		public Criteria andCreatorBetween(Integer value1, Integer value2) {
			addCriterion("creator between", value1, value2, "creator");
			return (Criteria) this;
		}

		public Criteria andCreatorNotBetween(Integer value1, Integer value2) {
			addCriterion("creator not between", value1, value2, "creator");
			return (Criteria) this;
		}

		public Criteria andAtUserIdIsNull() {
			addCriterion("at_user_id is null");
			return (Criteria) this;
		}

		public Criteria andAtUserIdIsNotNull() {
			addCriterion("at_user_id is not null");
			return (Criteria) this;
		}

		public Criteria andAtUserIdEqualTo(String value) {
			addCriterion("at_user_id =", value, "atUserId");
			return (Criteria) this;
		}

		public Criteria andAtUserIdNotEqualTo(String value) {
			addCriterion("at_user_id <>", value, "atUserId");
			return (Criteria) this;
		}

		public Criteria andAtUserIdGreaterThan(String value) {
			addCriterion("at_user_id >", value, "atUserId");
			return (Criteria) this;
		}

		public Criteria andAtUserIdGreaterThanOrEqualTo(String value) {
			addCriterion("at_user_id >=", value, "atUserId");
			return (Criteria) this;
		}

		public Criteria andAtUserIdLessThan(String value) {
			addCriterion("at_user_id <", value, "atUserId");
			return (Criteria) this;
		}

		public Criteria andAtUserIdLessThanOrEqualTo(String value) {
			addCriterion("at_user_id <=", value, "atUserId");
			return (Criteria) this;
		}

		public Criteria andAtUserIdLike(String value) {
			addCriterion("at_user_id like", value, "atUserId");
			return (Criteria) this;
		}

		public Criteria andAtUserIdNotLike(String value) {
			addCriterion("at_user_id not like", value, "atUserId");
			return (Criteria) this;
		}

		public Criteria andAtUserIdIn(List<String> values) {
			addCriterion("at_user_id in", values, "atUserId");
			return (Criteria) this;
		}

		public Criteria andAtUserIdNotIn(List<String> values) {
			addCriterion("at_user_id not in", values, "atUserId");
			return (Criteria) this;
		}

		public Criteria andAtUserIdBetween(String value1, String value2) {
			addCriterion("at_user_id between", value1, value2, "atUserId");
			return (Criteria) this;
		}

		public Criteria andAtUserIdNotBetween(String value1, String value2) {
			addCriterion("at_user_id not between", value1, value2, "atUserId");
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
	}

	/**
	 * t_plate_post_edit
	 */
	public static class Criteria extends GeneratedCriteria {

		protected Criteria() {
			super();
		}
	}

	/**
	 * t_plate_post_edit 2018-10-10
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