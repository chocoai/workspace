package com.yhcrt.iHealthCloud.entity;

import java.util.ArrayList;
import java.util.List;

public class MedicalExaminationReportExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MedicalExaminationReportExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

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

        public Criteria andMerIdIsNull() {
            addCriterion("mer_id is null");
            return (Criteria) this;
        }

        public Criteria andMerIdIsNotNull() {
            addCriterion("mer_id is not null");
            return (Criteria) this;
        }

        public Criteria andMerIdEqualTo(Integer value) {
            addCriterion("mer_id =", value, "merId");
            return (Criteria) this;
        }

        public Criteria andMerIdNotEqualTo(Integer value) {
            addCriterion("mer_id <>", value, "merId");
            return (Criteria) this;
        }

        public Criteria andMerIdGreaterThan(Integer value) {
            addCriterion("mer_id >", value, "merId");
            return (Criteria) this;
        }

        public Criteria andMerIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("mer_id >=", value, "merId");
            return (Criteria) this;
        }

        public Criteria andMerIdLessThan(Integer value) {
            addCriterion("mer_id <", value, "merId");
            return (Criteria) this;
        }

        public Criteria andMerIdLessThanOrEqualTo(Integer value) {
            addCriterion("mer_id <=", value, "merId");
            return (Criteria) this;
        }

        public Criteria andMerIdIn(List<Integer> values) {
            addCriterion("mer_id in", values, "merId");
            return (Criteria) this;
        }

        public Criteria andMerIdNotIn(List<Integer> values) {
            addCriterion("mer_id not in", values, "merId");
            return (Criteria) this;
        }

        public Criteria andMerIdBetween(Integer value1, Integer value2) {
            addCriterion("mer_id between", value1, value2, "merId");
            return (Criteria) this;
        }

        public Criteria andMerIdNotBetween(Integer value1, Integer value2) {
            addCriterion("mer_id not between", value1, value2, "merId");
            return (Criteria) this;
        }

        public Criteria andMemberIdIsNull() {
            addCriterion("member_id is null");
            return (Criteria) this;
        }

        public Criteria andMemberIdIsNotNull() {
            addCriterion("member_id is not null");
            return (Criteria) this;
        }

        public Criteria andMemberIdEqualTo(Integer value) {
            addCriterion("member_id =", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotEqualTo(Integer value) {
            addCriterion("member_id <>", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdGreaterThan(Integer value) {
            addCriterion("member_id >", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("member_id >=", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLessThan(Integer value) {
            addCriterion("member_id <", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLessThanOrEqualTo(Integer value) {
            addCriterion("member_id <=", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdIn(List<Integer> values) {
            addCriterion("member_id in", values, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotIn(List<Integer> values) {
            addCriterion("member_id not in", values, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdBetween(Integer value1, Integer value2) {
            addCriterion("member_id between", value1, value2, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotBetween(Integer value1, Integer value2) {
            addCriterion("member_id not between", value1, value2, "memberId");
            return (Criteria) this;
        }

        public Criteria andMerTimeIsNull() {
            addCriterion("mer_time is null");
            return (Criteria) this;
        }

        public Criteria andMerTimeIsNotNull() {
            addCriterion("mer_time is not null");
            return (Criteria) this;
        }

        public Criteria andMerTimeEqualTo(String value) {
            addCriterion("mer_time =", value, "merTime");
            return (Criteria) this;
        }

        public Criteria andMerTimeNotEqualTo(String value) {
            addCriterion("mer_time <>", value, "merTime");
            return (Criteria) this;
        }

        public Criteria andMerTimeGreaterThan(String value) {
            addCriterion("mer_time >", value, "merTime");
            return (Criteria) this;
        }

        public Criteria andMerTimeGreaterThanOrEqualTo(String value) {
            addCriterion("mer_time >=", value, "merTime");
            return (Criteria) this;
        }

        public Criteria andMerTimeLessThan(String value) {
            addCriterion("mer_time <", value, "merTime");
            return (Criteria) this;
        }

        public Criteria andMerTimeLessThanOrEqualTo(String value) {
            addCriterion("mer_time <=", value, "merTime");
            return (Criteria) this;
        }

        public Criteria andMerTimeLike(String value) {
            addCriterion("mer_time like", value, "merTime");
            return (Criteria) this;
        }

        public Criteria andMerTimeNotLike(String value) {
            addCriterion("mer_time not like", value, "merTime");
            return (Criteria) this;
        }

        public Criteria andMerTimeIn(List<String> values) {
            addCriterion("mer_time in", values, "merTime");
            return (Criteria) this;
        }

        public Criteria andMerTimeNotIn(List<String> values) {
            addCriterion("mer_time not in", values, "merTime");
            return (Criteria) this;
        }

        public Criteria andMerTimeBetween(String value1, String value2) {
            addCriterion("mer_time between", value1, value2, "merTime");
            return (Criteria) this;
        }

        public Criteria andMerTimeNotBetween(String value1, String value2) {
            addCriterion("mer_time not between", value1, value2, "merTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

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