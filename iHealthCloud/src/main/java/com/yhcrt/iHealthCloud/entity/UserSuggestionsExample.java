package com.yhcrt.iHealthCloud.entity;

import java.util.ArrayList;
import java.util.List;

public class UserSuggestionsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserSuggestionsExample() {
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

        public Criteria andCidIsNull() {
            addCriterion("cid is null");
            return (Criteria) this;
        }

        public Criteria andCidIsNotNull() {
            addCriterion("cid is not null");
            return (Criteria) this;
        }

        public Criteria andCidEqualTo(String value) {
            addCriterion("cid =", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidNotEqualTo(String value) {
            addCriterion("cid <>", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidGreaterThan(String value) {
            addCriterion("cid >", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidGreaterThanOrEqualTo(String value) {
            addCriterion("cid >=", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidLessThan(String value) {
            addCriterion("cid <", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidLessThanOrEqualTo(String value) {
            addCriterion("cid <=", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidLike(String value) {
            addCriterion("cid like", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidNotLike(String value) {
            addCriterion("cid not like", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidIn(List<String> values) {
            addCriterion("cid in", values, "cid");
            return (Criteria) this;
        }

        public Criteria andCidNotIn(List<String> values) {
            addCriterion("cid not in", values, "cid");
            return (Criteria) this;
        }

        public Criteria andCidBetween(String value1, String value2) {
            addCriterion("cid between", value1, value2, "cid");
            return (Criteria) this;
        }

        public Criteria andCidNotBetween(String value1, String value2) {
            addCriterion("cid not between", value1, value2, "cid");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("user_id like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("user_id not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andSuggestionContentIsNull() {
            addCriterion("suggestion_content is null");
            return (Criteria) this;
        }

        public Criteria andSuggestionContentIsNotNull() {
            addCriterion("suggestion_content is not null");
            return (Criteria) this;
        }

        public Criteria andSuggestionContentEqualTo(String value) {
            addCriterion("suggestion_content =", value, "suggestionContent");
            return (Criteria) this;
        }

        public Criteria andSuggestionContentNotEqualTo(String value) {
            addCriterion("suggestion_content <>", value, "suggestionContent");
            return (Criteria) this;
        }

        public Criteria andSuggestionContentGreaterThan(String value) {
            addCriterion("suggestion_content >", value, "suggestionContent");
            return (Criteria) this;
        }

        public Criteria andSuggestionContentGreaterThanOrEqualTo(String value) {
            addCriterion("suggestion_content >=", value, "suggestionContent");
            return (Criteria) this;
        }

        public Criteria andSuggestionContentLessThan(String value) {
            addCriterion("suggestion_content <", value, "suggestionContent");
            return (Criteria) this;
        }

        public Criteria andSuggestionContentLessThanOrEqualTo(String value) {
            addCriterion("suggestion_content <=", value, "suggestionContent");
            return (Criteria) this;
        }

        public Criteria andSuggestionContentLike(String value) {
            addCriterion("suggestion_content like", value, "suggestionContent");
            return (Criteria) this;
        }

        public Criteria andSuggestionContentNotLike(String value) {
            addCriterion("suggestion_content not like", value, "suggestionContent");
            return (Criteria) this;
        }

        public Criteria andSuggestionContentIn(List<String> values) {
            addCriterion("suggestion_content in", values, "suggestionContent");
            return (Criteria) this;
        }

        public Criteria andSuggestionContentNotIn(List<String> values) {
            addCriterion("suggestion_content not in", values, "suggestionContent");
            return (Criteria) this;
        }

        public Criteria andSuggestionContentBetween(String value1, String value2) {
            addCriterion("suggestion_content between", value1, value2, "suggestionContent");
            return (Criteria) this;
        }

        public Criteria andSuggestionContentNotBetween(String value1, String value2) {
            addCriterion("suggestion_content not between", value1, value2, "suggestionContent");
            return (Criteria) this;
        }

        public Criteria andSuggestionTimeIsNull() {
            addCriterion("suggestion_time is null");
            return (Criteria) this;
        }

        public Criteria andSuggestionTimeIsNotNull() {
            addCriterion("suggestion_time is not null");
            return (Criteria) this;
        }

        public Criteria andSuggestionTimeEqualTo(String value) {
            addCriterion("suggestion_time =", value, "suggestionTime");
            return (Criteria) this;
        }

        public Criteria andSuggestionTimeNotEqualTo(String value) {
            addCriterion("suggestion_time <>", value, "suggestionTime");
            return (Criteria) this;
        }

        public Criteria andSuggestionTimeGreaterThan(String value) {
            addCriterion("suggestion_time >", value, "suggestionTime");
            return (Criteria) this;
        }

        public Criteria andSuggestionTimeGreaterThanOrEqualTo(String value) {
            addCriterion("suggestion_time >=", value, "suggestionTime");
            return (Criteria) this;
        }

        public Criteria andSuggestionTimeLessThan(String value) {
            addCriterion("suggestion_time <", value, "suggestionTime");
            return (Criteria) this;
        }

        public Criteria andSuggestionTimeLessThanOrEqualTo(String value) {
            addCriterion("suggestion_time <=", value, "suggestionTime");
            return (Criteria) this;
        }

        public Criteria andSuggestionTimeLike(String value) {
            addCriterion("suggestion_time like", value, "suggestionTime");
            return (Criteria) this;
        }

        public Criteria andSuggestionTimeNotLike(String value) {
            addCriterion("suggestion_time not like", value, "suggestionTime");
            return (Criteria) this;
        }

        public Criteria andSuggestionTimeIn(List<String> values) {
            addCriterion("suggestion_time in", values, "suggestionTime");
            return (Criteria) this;
        }

        public Criteria andSuggestionTimeNotIn(List<String> values) {
            addCriterion("suggestion_time not in", values, "suggestionTime");
            return (Criteria) this;
        }

        public Criteria andSuggestionTimeBetween(String value1, String value2) {
            addCriterion("suggestion_time between", value1, value2, "suggestionTime");
            return (Criteria) this;
        }

        public Criteria andSuggestionTimeNotBetween(String value1, String value2) {
            addCriterion("suggestion_time not between", value1, value2, "suggestionTime");
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