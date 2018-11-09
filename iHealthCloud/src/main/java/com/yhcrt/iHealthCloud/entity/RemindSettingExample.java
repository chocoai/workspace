package com.yhcrt.iHealthCloud.entity;

import java.util.ArrayList;
import java.util.List;

public class RemindSettingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RemindSettingExample() {
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

        public Criteria andCidEqualTo(Integer value) {
            addCriterion("cid =", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidNotEqualTo(Integer value) {
            addCriterion("cid <>", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidGreaterThan(Integer value) {
            addCriterion("cid >", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidGreaterThanOrEqualTo(Integer value) {
            addCriterion("cid >=", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidLessThan(Integer value) {
            addCriterion("cid <", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidLessThanOrEqualTo(Integer value) {
            addCriterion("cid <=", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidIn(List<Integer> values) {
            addCriterion("cid in", values, "cid");
            return (Criteria) this;
        }

        public Criteria andCidNotIn(List<Integer> values) {
            addCriterion("cid not in", values, "cid");
            return (Criteria) this;
        }

        public Criteria andCidBetween(Integer value1, Integer value2) {
            addCriterion("cid between", value1, value2, "cid");
            return (Criteria) this;
        }

        public Criteria andCidNotBetween(Integer value1, Integer value2) {
            addCriterion("cid not between", value1, value2, "cid");
            return (Criteria) this;
        }

        public Criteria andImeiIsNull() {
            addCriterion("imei is null");
            return (Criteria) this;
        }

        public Criteria andImeiIsNotNull() {
            addCriterion("imei is not null");
            return (Criteria) this;
        }

        public Criteria andImeiEqualTo(String value) {
            addCriterion("imei =", value, "imei");
            return (Criteria) this;
        }

        public Criteria andImeiNotEqualTo(String value) {
            addCriterion("imei <>", value, "imei");
            return (Criteria) this;
        }

        public Criteria andImeiGreaterThan(String value) {
            addCriterion("imei >", value, "imei");
            return (Criteria) this;
        }

        public Criteria andImeiGreaterThanOrEqualTo(String value) {
            addCriterion("imei >=", value, "imei");
            return (Criteria) this;
        }

        public Criteria andImeiLessThan(String value) {
            addCriterion("imei <", value, "imei");
            return (Criteria) this;
        }

        public Criteria andImeiLessThanOrEqualTo(String value) {
            addCriterion("imei <=", value, "imei");
            return (Criteria) this;
        }

        public Criteria andImeiLike(String value) {
            addCriterion("imei like", value, "imei");
            return (Criteria) this;
        }

        public Criteria andImeiNotLike(String value) {
            addCriterion("imei not like", value, "imei");
            return (Criteria) this;
        }

        public Criteria andImeiIn(List<String> values) {
            addCriterion("imei in", values, "imei");
            return (Criteria) this;
        }

        public Criteria andImeiNotIn(List<String> values) {
            addCriterion("imei not in", values, "imei");
            return (Criteria) this;
        }

        public Criteria andImeiBetween(String value1, String value2) {
            addCriterion("imei between", value1, value2, "imei");
            return (Criteria) this;
        }

        public Criteria andImeiNotBetween(String value1, String value2) {
            addCriterion("imei not between", value1, value2, "imei");
            return (Criteria) this;
        }

        public Criteria andRemindTypeIsNull() {
            addCriterion("remind_type is null");
            return (Criteria) this;
        }

        public Criteria andRemindTypeIsNotNull() {
            addCriterion("remind_type is not null");
            return (Criteria) this;
        }

        public Criteria andRemindTypeEqualTo(Integer value) {
            addCriterion("remind_type =", value, "remindType");
            return (Criteria) this;
        }

        public Criteria andRemindTypeNotEqualTo(Integer value) {
            addCriterion("remind_type <>", value, "remindType");
            return (Criteria) this;
        }

        public Criteria andRemindTypeGreaterThan(Integer value) {
            addCriterion("remind_type >", value, "remindType");
            return (Criteria) this;
        }

        public Criteria andRemindTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("remind_type >=", value, "remindType");
            return (Criteria) this;
        }

        public Criteria andRemindTypeLessThan(Integer value) {
            addCriterion("remind_type <", value, "remindType");
            return (Criteria) this;
        }

        public Criteria andRemindTypeLessThanOrEqualTo(Integer value) {
            addCriterion("remind_type <=", value, "remindType");
            return (Criteria) this;
        }

        public Criteria andRemindTypeIn(List<Integer> values) {
            addCriterion("remind_type in", values, "remindType");
            return (Criteria) this;
        }

        public Criteria andRemindTypeNotIn(List<Integer> values) {
            addCriterion("remind_type not in", values, "remindType");
            return (Criteria) this;
        }

        public Criteria andRemindTypeBetween(Integer value1, Integer value2) {
            addCriterion("remind_type between", value1, value2, "remindType");
            return (Criteria) this;
        }

        public Criteria andRemindTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("remind_type not between", value1, value2, "remindType");
            return (Criteria) this;
        }

        public Criteria andRemindTimeIsNull() {
            addCriterion("remind_time is null");
            return (Criteria) this;
        }

        public Criteria andRemindTimeIsNotNull() {
            addCriterion("remind_time is not null");
            return (Criteria) this;
        }

        public Criteria andRemindTimeEqualTo(String value) {
            addCriterion("remind_time =", value, "remindTime");
            return (Criteria) this;
        }

        public Criteria andRemindTimeNotEqualTo(String value) {
            addCriterion("remind_time <>", value, "remindTime");
            return (Criteria) this;
        }

        public Criteria andRemindTimeGreaterThan(String value) {
            addCriterion("remind_time >", value, "remindTime");
            return (Criteria) this;
        }

        public Criteria andRemindTimeGreaterThanOrEqualTo(String value) {
            addCriterion("remind_time >=", value, "remindTime");
            return (Criteria) this;
        }

        public Criteria andRemindTimeLessThan(String value) {
            addCriterion("remind_time <", value, "remindTime");
            return (Criteria) this;
        }

        public Criteria andRemindTimeLessThanOrEqualTo(String value) {
            addCriterion("remind_time <=", value, "remindTime");
            return (Criteria) this;
        }

        public Criteria andRemindTimeLike(String value) {
            addCriterion("remind_time like", value, "remindTime");
            return (Criteria) this;
        }

        public Criteria andRemindTimeNotLike(String value) {
            addCriterion("remind_time not like", value, "remindTime");
            return (Criteria) this;
        }

        public Criteria andRemindTimeIn(List<String> values) {
            addCriterion("remind_time in", values, "remindTime");
            return (Criteria) this;
        }

        public Criteria andRemindTimeNotIn(List<String> values) {
            addCriterion("remind_time not in", values, "remindTime");
            return (Criteria) this;
        }

        public Criteria andRemindTimeBetween(String value1, String value2) {
            addCriterion("remind_time between", value1, value2, "remindTime");
            return (Criteria) this;
        }

        public Criteria andRemindTimeNotBetween(String value1, String value2) {
            addCriterion("remind_time not between", value1, value2, "remindTime");
            return (Criteria) this;
        }

        public Criteria andRemindSwitchIsNull() {
            addCriterion("remind_switch is null");
            return (Criteria) this;
        }

        public Criteria andRemindSwitchIsNotNull() {
            addCriterion("remind_switch is not null");
            return (Criteria) this;
        }

        public Criteria andRemindSwitchEqualTo(Integer value) {
            addCriterion("remind_switch =", value, "remindSwitch");
            return (Criteria) this;
        }

        public Criteria andRemindSwitchNotEqualTo(Integer value) {
            addCriterion("remind_switch <>", value, "remindSwitch");
            return (Criteria) this;
        }

        public Criteria andRemindSwitchGreaterThan(Integer value) {
            addCriterion("remind_switch >", value, "remindSwitch");
            return (Criteria) this;
        }

        public Criteria andRemindSwitchGreaterThanOrEqualTo(Integer value) {
            addCriterion("remind_switch >=", value, "remindSwitch");
            return (Criteria) this;
        }

        public Criteria andRemindSwitchLessThan(Integer value) {
            addCriterion("remind_switch <", value, "remindSwitch");
            return (Criteria) this;
        }

        public Criteria andRemindSwitchLessThanOrEqualTo(Integer value) {
            addCriterion("remind_switch <=", value, "remindSwitch");
            return (Criteria) this;
        }

        public Criteria andRemindSwitchIn(List<Integer> values) {
            addCriterion("remind_switch in", values, "remindSwitch");
            return (Criteria) this;
        }

        public Criteria andRemindSwitchNotIn(List<Integer> values) {
            addCriterion("remind_switch not in", values, "remindSwitch");
            return (Criteria) this;
        }

        public Criteria andRemindSwitchBetween(Integer value1, Integer value2) {
            addCriterion("remind_switch between", value1, value2, "remindSwitch");
            return (Criteria) this;
        }

        public Criteria andRemindSwitchNotBetween(Integer value1, Integer value2) {
            addCriterion("remind_switch not between", value1, value2, "remindSwitch");
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