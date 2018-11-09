package com.whty.wfd.page.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TPostMessageAudioExample {
	/**
	 * t_post_message_audio
	 */
	protected String orderByClause;

	/**
	 * t_post_message_audio
	 */
	protected boolean distinct;

	/**
	 * t_post_message_audio
	 */
	protected List<Criteria> oredCriteria;

	/**
	 *
	 * @mbg.generated 2018-09-18
	 */
	public TPostMessageAudioExample() {
		oredCriteria = new ArrayList<Criteria>();
	}

	/**
	 *
	 * @mbg.generated 2018-09-18
	 */
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}

	/**
	 *
	 * @mbg.generated 2018-09-18
	 */
	public String getOrderByClause() {
		return orderByClause;
	}

	/**
	 *
	 * @mbg.generated 2018-09-18
	 */
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}

	/**
	 *
	 * @mbg.generated 2018-09-18
	 */
	public boolean isDistinct() {
		return distinct;
	}

	/**
	 *
	 * @mbg.generated 2018-09-18
	 */
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}

	/**
	 *
	 * @mbg.generated 2018-09-18
	 */
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}

	/**
	 *
	 * @mbg.generated 2018-09-18
	 */
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}

	/**
	 *
	 * @mbg.generated 2018-09-18
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
	 * @mbg.generated 2018-09-18
	 */
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 *
	 * @mbg.generated 2018-09-18
	 */
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}

	/**
	 * t_post_message_audio 2018-09-18
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

		public Criteria andPostMessageIdIsNull() {
			addCriterion("post_message_id is null");
			return (Criteria) this;
		}

		public Criteria andPostMessageIdIsNotNull() {
			addCriterion("post_message_id is not null");
			return (Criteria) this;
		}

		public Criteria andPostMessageIdEqualTo(String value) {
			addCriterion("post_message_id =", value, "postMessageId");
			return (Criteria) this;
		}

		public Criteria andPostMessageIdNotEqualTo(String value) {
			addCriterion("post_message_id <>", value, "postMessageId");
			return (Criteria) this;
		}

		public Criteria andPostMessageIdGreaterThan(String value) {
			addCriterion("post_message_id >", value, "postMessageId");
			return (Criteria) this;
		}

		public Criteria andPostMessageIdGreaterThanOrEqualTo(String value) {
			addCriterion("post_message_id >=", value, "postMessageId");
			return (Criteria) this;
		}

		public Criteria andPostMessageIdLessThan(String value) {
			addCriterion("post_message_id <", value, "postMessageId");
			return (Criteria) this;
		}

		public Criteria andPostMessageIdLessThanOrEqualTo(String value) {
			addCriterion("post_message_id <=", value, "postMessageId");
			return (Criteria) this;
		}

		public Criteria andPostMessageIdLike(String value) {
			addCriterion("post_message_id like", value, "postMessageId");
			return (Criteria) this;
		}

		public Criteria andPostMessageIdNotLike(String value) {
			addCriterion("post_message_id not like", value, "postMessageId");
			return (Criteria) this;
		}

		public Criteria andPostMessageIdIn(List<String> values) {
			addCriterion("post_message_id in", values, "postMessageId");
			return (Criteria) this;
		}

		public Criteria andPostMessageIdNotIn(List<String> values) {
			addCriterion("post_message_id not in", values, "postMessageId");
			return (Criteria) this;
		}

		public Criteria andPostMessageIdBetween(String value1, String value2) {
			addCriterion("post_message_id between", value1, value2, "postMessageId");
			return (Criteria) this;
		}

		public Criteria andPostMessageIdNotBetween(String value1, String value2) {
			addCriterion("post_message_id not between", value1, value2, "postMessageId");
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
	 * t_post_message_audio
	 */
	public static class Criteria extends GeneratedCriteria {

		protected Criteria() {
			super();
		}
	}

	/**
	 * t_post_message_audio 2018-09-18
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