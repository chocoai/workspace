package com.yhcrt.iHealthCloud.entity;

import java.util.ArrayList;
import java.util.List;

public class BloodPressureSettingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BloodPressureSettingExample() {
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

        public Criteria andDbpMinIsNull() {
            addCriterion("dbp_min is null");
            return (Criteria) this;
        }

        public Criteria andDbpMinIsNotNull() {
            addCriterion("dbp_min is not null");
            return (Criteria) this;
        }

        public Criteria andDbpMinEqualTo(Integer value) {
            addCriterion("dbp_min =", value, "dbpMin");
            return (Criteria) this;
        }

        public Criteria andDbpMinNotEqualTo(Integer value) {
            addCriterion("dbp_min <>", value, "dbpMin");
            return (Criteria) this;
        }

        public Criteria andDbpMinGreaterThan(Integer value) {
            addCriterion("dbp_min >", value, "dbpMin");
            return (Criteria) this;
        }

        public Criteria andDbpMinGreaterThanOrEqualTo(Integer value) {
            addCriterion("dbp_min >=", value, "dbpMin");
            return (Criteria) this;
        }

        public Criteria andDbpMinLessThan(Integer value) {
            addCriterion("dbp_min <", value, "dbpMin");
            return (Criteria) this;
        }

        public Criteria andDbpMinLessThanOrEqualTo(Integer value) {
            addCriterion("dbp_min <=", value, "dbpMin");
            return (Criteria) this;
        }

        public Criteria andDbpMinIn(List<Integer> values) {
            addCriterion("dbp_min in", values, "dbpMin");
            return (Criteria) this;
        }

        public Criteria andDbpMinNotIn(List<Integer> values) {
            addCriterion("dbp_min not in", values, "dbpMin");
            return (Criteria) this;
        }

        public Criteria andDbpMinBetween(Integer value1, Integer value2) {
            addCriterion("dbp_min between", value1, value2, "dbpMin");
            return (Criteria) this;
        }

        public Criteria andDbpMinNotBetween(Integer value1, Integer value2) {
            addCriterion("dbp_min not between", value1, value2, "dbpMin");
            return (Criteria) this;
        }

        public Criteria andDbpMaxIsNull() {
            addCriterion("dbp_max is null");
            return (Criteria) this;
        }

        public Criteria andDbpMaxIsNotNull() {
            addCriterion("dbp_max is not null");
            return (Criteria) this;
        }

        public Criteria andDbpMaxEqualTo(Integer value) {
            addCriterion("dbp_max =", value, "dbpMax");
            return (Criteria) this;
        }

        public Criteria andDbpMaxNotEqualTo(Integer value) {
            addCriterion("dbp_max <>", value, "dbpMax");
            return (Criteria) this;
        }

        public Criteria andDbpMaxGreaterThan(Integer value) {
            addCriterion("dbp_max >", value, "dbpMax");
            return (Criteria) this;
        }

        public Criteria andDbpMaxGreaterThanOrEqualTo(Integer value) {
            addCriterion("dbp_max >=", value, "dbpMax");
            return (Criteria) this;
        }

        public Criteria andDbpMaxLessThan(Integer value) {
            addCriterion("dbp_max <", value, "dbpMax");
            return (Criteria) this;
        }

        public Criteria andDbpMaxLessThanOrEqualTo(Integer value) {
            addCriterion("dbp_max <=", value, "dbpMax");
            return (Criteria) this;
        }

        public Criteria andDbpMaxIn(List<Integer> values) {
            addCriterion("dbp_max in", values, "dbpMax");
            return (Criteria) this;
        }

        public Criteria andDbpMaxNotIn(List<Integer> values) {
            addCriterion("dbp_max not in", values, "dbpMax");
            return (Criteria) this;
        }

        public Criteria andDbpMaxBetween(Integer value1, Integer value2) {
            addCriterion("dbp_max between", value1, value2, "dbpMax");
            return (Criteria) this;
        }

        public Criteria andDbpMaxNotBetween(Integer value1, Integer value2) {
            addCriterion("dbp_max not between", value1, value2, "dbpMax");
            return (Criteria) this;
        }

        public Criteria andSbpMinIsNull() {
            addCriterion("sbp_min is null");
            return (Criteria) this;
        }

        public Criteria andSbpMinIsNotNull() {
            addCriterion("sbp_min is not null");
            return (Criteria) this;
        }

        public Criteria andSbpMinEqualTo(Integer value) {
            addCriterion("sbp_min =", value, "sbpMin");
            return (Criteria) this;
        }

        public Criteria andSbpMinNotEqualTo(Integer value) {
            addCriterion("sbp_min <>", value, "sbpMin");
            return (Criteria) this;
        }

        public Criteria andSbpMinGreaterThan(Integer value) {
            addCriterion("sbp_min >", value, "sbpMin");
            return (Criteria) this;
        }

        public Criteria andSbpMinGreaterThanOrEqualTo(Integer value) {
            addCriterion("sbp_min >=", value, "sbpMin");
            return (Criteria) this;
        }

        public Criteria andSbpMinLessThan(Integer value) {
            addCriterion("sbp_min <", value, "sbpMin");
            return (Criteria) this;
        }

        public Criteria andSbpMinLessThanOrEqualTo(Integer value) {
            addCriterion("sbp_min <=", value, "sbpMin");
            return (Criteria) this;
        }

        public Criteria andSbpMinIn(List<Integer> values) {
            addCriterion("sbp_min in", values, "sbpMin");
            return (Criteria) this;
        }

        public Criteria andSbpMinNotIn(List<Integer> values) {
            addCriterion("sbp_min not in", values, "sbpMin");
            return (Criteria) this;
        }

        public Criteria andSbpMinBetween(Integer value1, Integer value2) {
            addCriterion("sbp_min between", value1, value2, "sbpMin");
            return (Criteria) this;
        }

        public Criteria andSbpMinNotBetween(Integer value1, Integer value2) {
            addCriterion("sbp_min not between", value1, value2, "sbpMin");
            return (Criteria) this;
        }

        public Criteria andSbpMaxIsNull() {
            addCriterion("sbp_max is null");
            return (Criteria) this;
        }

        public Criteria andSbpMaxIsNotNull() {
            addCriterion("sbp_max is not null");
            return (Criteria) this;
        }

        public Criteria andSbpMaxEqualTo(Integer value) {
            addCriterion("sbp_max =", value, "sbpMax");
            return (Criteria) this;
        }

        public Criteria andSbpMaxNotEqualTo(Integer value) {
            addCriterion("sbp_max <>", value, "sbpMax");
            return (Criteria) this;
        }

        public Criteria andSbpMaxGreaterThan(Integer value) {
            addCriterion("sbp_max >", value, "sbpMax");
            return (Criteria) this;
        }

        public Criteria andSbpMaxGreaterThanOrEqualTo(Integer value) {
            addCriterion("sbp_max >=", value, "sbpMax");
            return (Criteria) this;
        }

        public Criteria andSbpMaxLessThan(Integer value) {
            addCriterion("sbp_max <", value, "sbpMax");
            return (Criteria) this;
        }

        public Criteria andSbpMaxLessThanOrEqualTo(Integer value) {
            addCriterion("sbp_max <=", value, "sbpMax");
            return (Criteria) this;
        }

        public Criteria andSbpMaxIn(List<Integer> values) {
            addCriterion("sbp_max in", values, "sbpMax");
            return (Criteria) this;
        }

        public Criteria andSbpMaxNotIn(List<Integer> values) {
            addCriterion("sbp_max not in", values, "sbpMax");
            return (Criteria) this;
        }

        public Criteria andSbpMaxBetween(Integer value1, Integer value2) {
            addCriterion("sbp_max between", value1, value2, "sbpMax");
            return (Criteria) this;
        }

        public Criteria andSbpMaxNotBetween(Integer value1, Integer value2) {
            addCriterion("sbp_max not between", value1, value2, "sbpMax");
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