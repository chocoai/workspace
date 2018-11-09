package com.whty.wfd.page.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TPlatePostImgExample {
	/**
	 * t_plate_post_img
	 */
	protected String orderByClause;

	/**
	 * t_plate_post_img
	 */
	protected boolean distinct;

	/**
	 * t_plate_post_img
	 */
	protected List<Criteria> oredCriteria;

	/**
	 *
	 * @mbg.generated 2018-08-16
	 */
	public TPlatePostImgExample() {
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
	 * t_plate_post_img 2018-08-16
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

		public Criteria andDownUrlIsNull() {
			addCriterion("down_url is null");
			return (Criteria) this;
		}

		public Criteria andDownUrlIsNotNull() {
			addCriterion("down_url is not null");
			return (Criteria) this;
		}

		public Criteria andDownUrlEqualTo(String value) {
			addCriterion("down_url =", value, "downUrl");
			return (Criteria) this;
		}

		public Criteria andDownUrlNotEqualTo(String value) {
			addCriterion("down_url <>", value, "downUrl");
			return (Criteria) this;
		}

		public Criteria andDownUrlGreaterThan(String value) {
			addCriterion("down_url >", value, "downUrl");
			return (Criteria) this;
		}

		public Criteria andDownUrlGreaterThanOrEqualTo(String value) {
			addCriterion("down_url >=", value, "downUrl");
			return (Criteria) this;
		}

		public Criteria andDownUrlLessThan(String value) {
			addCriterion("down_url <", value, "downUrl");
			return (Criteria) this;
		}

		public Criteria andDownUrlLessThanOrEqualTo(String value) {
			addCriterion("down_url <=", value, "downUrl");
			return (Criteria) this;
		}

		public Criteria andDownUrlLike(String value) {
			addCriterion("down_url like", value, "downUrl");
			return (Criteria) this;
		}

		public Criteria andDownUrlNotLike(String value) {
			addCriterion("down_url not like", value, "downUrl");
			return (Criteria) this;
		}

		public Criteria andDownUrlIn(List<String> values) {
			addCriterion("down_url in", values, "downUrl");
			return (Criteria) this;
		}

		public Criteria andDownUrlNotIn(List<String> values) {
			addCriterion("down_url not in", values, "downUrl");
			return (Criteria) this;
		}

		public Criteria andDownUrlBetween(String value1, String value2) {
			addCriterion("down_url between", value1, value2, "downUrl");
			return (Criteria) this;
		}

		public Criteria andDownUrlNotBetween(String value1, String value2) {
			addCriterion("down_url not between", value1, value2, "downUrl");
			return (Criteria) this;
		}

		public Criteria andMd5IsNull() {
			addCriterion("md5 is null");
			return (Criteria) this;
		}

		public Criteria andMd5IsNotNull() {
			addCriterion("md5 is not null");
			return (Criteria) this;
		}

		public Criteria andMd5EqualTo(String value) {
			addCriterion("md5 =", value, "md5");
			return (Criteria) this;
		}

		public Criteria andMd5NotEqualTo(String value) {
			addCriterion("md5 <>", value, "md5");
			return (Criteria) this;
		}

		public Criteria andMd5GreaterThan(String value) {
			addCriterion("md5 >", value, "md5");
			return (Criteria) this;
		}

		public Criteria andMd5GreaterThanOrEqualTo(String value) {
			addCriterion("md5 >=", value, "md5");
			return (Criteria) this;
		}

		public Criteria andMd5LessThan(String value) {
			addCriterion("md5 <", value, "md5");
			return (Criteria) this;
		}

		public Criteria andMd5LessThanOrEqualTo(String value) {
			addCriterion("md5 <=", value, "md5");
			return (Criteria) this;
		}

		public Criteria andMd5Like(String value) {
			addCriterion("md5 like", value, "md5");
			return (Criteria) this;
		}

		public Criteria andMd5NotLike(String value) {
			addCriterion("md5 not like", value, "md5");
			return (Criteria) this;
		}

		public Criteria andMd5In(List<String> values) {
			addCriterion("md5 in", values, "md5");
			return (Criteria) this;
		}

		public Criteria andMd5NotIn(List<String> values) {
			addCriterion("md5 not in", values, "md5");
			return (Criteria) this;
		}

		public Criteria andMd5Between(String value1, String value2) {
			addCriterion("md5 between", value1, value2, "md5");
			return (Criteria) this;
		}

		public Criteria andMd5NotBetween(String value1, String value2) {
			addCriterion("md5 not between", value1, value2, "md5");
			return (Criteria) this;
		}

		public Criteria andPlatePostIdIsNull() {
			addCriterion("plate_post_id is null");
			return (Criteria) this;
		}

		public Criteria andPlatePostIdIsNotNull() {
			addCriterion("plate_post_id is not null");
			return (Criteria) this;
		}

		public Criteria andPlatePostIdEqualTo(Integer value) {
			addCriterion("plate_post_id =", value, "platePostId");
			return (Criteria) this;
		}

		public Criteria andPlatePostIdNotEqualTo(Integer value) {
			addCriterion("plate_post_id <>", value, "platePostId");
			return (Criteria) this;
		}

		public Criteria andPlatePostIdGreaterThan(Integer value) {
			addCriterion("plate_post_id >", value, "platePostId");
			return (Criteria) this;
		}

		public Criteria andPlatePostIdGreaterThanOrEqualTo(Integer value) {
			addCriterion("plate_post_id >=", value, "platePostId");
			return (Criteria) this;
		}

		public Criteria andPlatePostIdLessThan(Integer value) {
			addCriterion("plate_post_id <", value, "platePostId");
			return (Criteria) this;
		}

		public Criteria andPlatePostIdLessThanOrEqualTo(Integer value) {
			addCriterion("plate_post_id <=", value, "platePostId");
			return (Criteria) this;
		}

		public Criteria andPlatePostIdIn(List<Integer> values) {
			addCriterion("plate_post_id in", values, "platePostId");
			return (Criteria) this;
		}

		public Criteria andPlatePostIdNotIn(List<Integer> values) {
			addCriterion("plate_post_id not in", values, "platePostId");
			return (Criteria) this;
		}

		public Criteria andPlatePostIdBetween(Integer value1, Integer value2) {
			addCriterion("plate_post_id between", value1, value2, "platePostId");
			return (Criteria) this;
		}

		public Criteria andPlatePostIdNotBetween(Integer value1, Integer value2) {
			addCriterion("plate_post_id not between", value1, value2, "platePostId");
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
	}

	/**
	 * t_plate_post_img
	 */
	public static class Criteria extends GeneratedCriteria {

		protected Criteria() {
			super();
		}
	}

	/**
	 * t_plate_post_img 2018-08-16
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