package com.yhcrt.healthcloud.device.entity;

import java.util.ArrayList;
import java.util.List;

public class HeartRateSettingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public HeartRateSettingExample() {
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

        public Criteria andHighHeartRateIsNull() {
            addCriterion("high_heart_rate is null");
            return (Criteria) this;
        }

        public Criteria andHighHeartRateIsNotNull() {
            addCriterion("high_heart_rate is not null");
            return (Criteria) this;
        }

        public Criteria andHighHeartRateEqualTo(Integer value) {
            addCriterion("high_heart_rate =", value, "highHeartRate");
            return (Criteria) this;
        }

        public Criteria andHighHeartRateNotEqualTo(Integer value) {
            addCriterion("high_heart_rate <>", value, "highHeartRate");
            return (Criteria) this;
        }

        public Criteria andHighHeartRateGreaterThan(Integer value) {
            addCriterion("high_heart_rate >", value, "highHeartRate");
            return (Criteria) this;
        }

        public Criteria andHighHeartRateGreaterThanOrEqualTo(Integer value) {
            addCriterion("high_heart_rate >=", value, "highHeartRate");
            return (Criteria) this;
        }

        public Criteria andHighHeartRateLessThan(Integer value) {
            addCriterion("high_heart_rate <", value, "highHeartRate");
            return (Criteria) this;
        }

        public Criteria andHighHeartRateLessThanOrEqualTo(Integer value) {
            addCriterion("high_heart_rate <=", value, "highHeartRate");
            return (Criteria) this;
        }

        public Criteria andHighHeartRateIn(List<Integer> values) {
            addCriterion("high_heart_rate in", values, "highHeartRate");
            return (Criteria) this;
        }

        public Criteria andHighHeartRateNotIn(List<Integer> values) {
            addCriterion("high_heart_rate not in", values, "highHeartRate");
            return (Criteria) this;
        }

        public Criteria andHighHeartRateBetween(Integer value1, Integer value2) {
            addCriterion("high_heart_rate between", value1, value2, "highHeartRate");
            return (Criteria) this;
        }

        public Criteria andHighHeartRateNotBetween(Integer value1, Integer value2) {
            addCriterion("high_heart_rate not between", value1, value2, "highHeartRate");
            return (Criteria) this;
        }

        public Criteria andLowHeartRateIsNull() {
            addCriterion("low_heart_rate is null");
            return (Criteria) this;
        }

        public Criteria andLowHeartRateIsNotNull() {
            addCriterion("low_heart_rate is not null");
            return (Criteria) this;
        }

        public Criteria andLowHeartRateEqualTo(Integer value) {
            addCriterion("low_heart_rate =", value, "lowHeartRate");
            return (Criteria) this;
        }

        public Criteria andLowHeartRateNotEqualTo(Integer value) {
            addCriterion("low_heart_rate <>", value, "lowHeartRate");
            return (Criteria) this;
        }

        public Criteria andLowHeartRateGreaterThan(Integer value) {
            addCriterion("low_heart_rate >", value, "lowHeartRate");
            return (Criteria) this;
        }

        public Criteria andLowHeartRateGreaterThanOrEqualTo(Integer value) {
            addCriterion("low_heart_rate >=", value, "lowHeartRate");
            return (Criteria) this;
        }

        public Criteria andLowHeartRateLessThan(Integer value) {
            addCriterion("low_heart_rate <", value, "lowHeartRate");
            return (Criteria) this;
        }

        public Criteria andLowHeartRateLessThanOrEqualTo(Integer value) {
            addCriterion("low_heart_rate <=", value, "lowHeartRate");
            return (Criteria) this;
        }

        public Criteria andLowHeartRateIn(List<Integer> values) {
            addCriterion("low_heart_rate in", values, "lowHeartRate");
            return (Criteria) this;
        }

        public Criteria andLowHeartRateNotIn(List<Integer> values) {
            addCriterion("low_heart_rate not in", values, "lowHeartRate");
            return (Criteria) this;
        }

        public Criteria andLowHeartRateBetween(Integer value1, Integer value2) {
            addCriterion("low_heart_rate between", value1, value2, "lowHeartRate");
            return (Criteria) this;
        }

        public Criteria andLowHeartRateNotBetween(Integer value1, Integer value2) {
            addCriterion("low_heart_rate not between", value1, value2, "lowHeartRate");
            return (Criteria) this;
        }

        public Criteria andAlarmSwitchIsNull() {
            addCriterion("alarm_switch is null");
            return (Criteria) this;
        }

        public Criteria andAlarmSwitchIsNotNull() {
            addCriterion("alarm_switch is not null");
            return (Criteria) this;
        }

        public Criteria andAlarmSwitchEqualTo(Integer value) {
            addCriterion("alarm_switch =", value, "alarmSwitch");
            return (Criteria) this;
        }

        public Criteria andAlarmSwitchNotEqualTo(Integer value) {
            addCriterion("alarm_switch <>", value, "alarmSwitch");
            return (Criteria) this;
        }

        public Criteria andAlarmSwitchGreaterThan(Integer value) {
            addCriterion("alarm_switch >", value, "alarmSwitch");
            return (Criteria) this;
        }

        public Criteria andAlarmSwitchGreaterThanOrEqualTo(Integer value) {
            addCriterion("alarm_switch >=", value, "alarmSwitch");
            return (Criteria) this;
        }

        public Criteria andAlarmSwitchLessThan(Integer value) {
            addCriterion("alarm_switch <", value, "alarmSwitch");
            return (Criteria) this;
        }

        public Criteria andAlarmSwitchLessThanOrEqualTo(Integer value) {
            addCriterion("alarm_switch <=", value, "alarmSwitch");
            return (Criteria) this;
        }

        public Criteria andAlarmSwitchIn(List<Integer> values) {
            addCriterion("alarm_switch in", values, "alarmSwitch");
            return (Criteria) this;
        }

        public Criteria andAlarmSwitchNotIn(List<Integer> values) {
            addCriterion("alarm_switch not in", values, "alarmSwitch");
            return (Criteria) this;
        }

        public Criteria andAlarmSwitchBetween(Integer value1, Integer value2) {
            addCriterion("alarm_switch between", value1, value2, "alarmSwitch");
            return (Criteria) this;
        }

        public Criteria andAlarmSwitchNotBetween(Integer value1, Integer value2) {
            addCriterion("alarm_switch not between", value1, value2, "alarmSwitch");
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