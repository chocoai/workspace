package com.yhcrt.healthcloud.health.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class MerBloodPressureExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MerBloodPressureExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andDataDateIsNull() {
            addCriterion("data_date is null");
            return (Criteria) this;
        }

        public Criteria andDataDateIsNotNull() {
            addCriterion("data_date is not null");
            return (Criteria) this;
        }

        public Criteria andDataDateEqualTo(Date value) {
            addCriterionForJDBCDate("data_date =", value, "dataDate");
            return (Criteria) this;
        }

        public Criteria andDataDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("data_date <>", value, "dataDate");
            return (Criteria) this;
        }

        public Criteria andDataDateGreaterThan(Date value) {
            addCriterionForJDBCDate("data_date >", value, "dataDate");
            return (Criteria) this;
        }

        public Criteria andDataDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("data_date >=", value, "dataDate");
            return (Criteria) this;
        }

        public Criteria andDataDateLessThan(Date value) {
            addCriterionForJDBCDate("data_date <", value, "dataDate");
            return (Criteria) this;
        }

        public Criteria andDataDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("data_date <=", value, "dataDate");
            return (Criteria) this;
        }

        public Criteria andDataDateIn(List<Date> values) {
            addCriterionForJDBCDate("data_date in", values, "dataDate");
            return (Criteria) this;
        }

        public Criteria andDataDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("data_date not in", values, "dataDate");
            return (Criteria) this;
        }

        public Criteria andDataDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("data_date between", value1, value2, "dataDate");
            return (Criteria) this;
        }

        public Criteria andDataDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("data_date not between", value1, value2, "dataDate");
            return (Criteria) this;
        }

        public Criteria andUploadTimeIsNull() {
            addCriterion("upload_time is null");
            return (Criteria) this;
        }

        public Criteria andUploadTimeIsNotNull() {
            addCriterion("upload_time is not null");
            return (Criteria) this;
        }

        public Criteria andUploadTimeEqualTo(String value) {
            addCriterion("upload_time =", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeNotEqualTo(String value) {
            addCriterion("upload_time <>", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeGreaterThan(String value) {
            addCriterion("upload_time >", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeGreaterThanOrEqualTo(String value) {
            addCriterion("upload_time >=", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeLessThan(String value) {
            addCriterion("upload_time <", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeLessThanOrEqualTo(String value) {
            addCriterion("upload_time <=", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeLike(String value) {
            addCriterion("upload_time like", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeNotLike(String value) {
            addCriterion("upload_time not like", value, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeIn(List<String> values) {
            addCriterion("upload_time in", values, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeNotIn(List<String> values) {
            addCriterion("upload_time not in", values, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeBetween(String value1, String value2) {
            addCriterion("upload_time between", value1, value2, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andUploadTimeNotBetween(String value1, String value2) {
            addCriterion("upload_time not between", value1, value2, "uploadTime");
            return (Criteria) this;
        }

        public Criteria andDbpIsNull() {
            addCriterion("dbp is null");
            return (Criteria) this;
        }

        public Criteria andDbpIsNotNull() {
            addCriterion("dbp is not null");
            return (Criteria) this;
        }

        public Criteria andDbpEqualTo(Integer value) {
            addCriterion("dbp =", value, "dbp");
            return (Criteria) this;
        }

        public Criteria andDbpNotEqualTo(Integer value) {
            addCriterion("dbp <>", value, "dbp");
            return (Criteria) this;
        }

        public Criteria andDbpGreaterThan(Integer value) {
            addCriterion("dbp >", value, "dbp");
            return (Criteria) this;
        }

        public Criteria andDbpGreaterThanOrEqualTo(Integer value) {
            addCriterion("dbp >=", value, "dbp");
            return (Criteria) this;
        }

        public Criteria andDbpLessThan(Integer value) {
            addCriterion("dbp <", value, "dbp");
            return (Criteria) this;
        }

        public Criteria andDbpLessThanOrEqualTo(Integer value) {
            addCriterion("dbp <=", value, "dbp");
            return (Criteria) this;
        }

        public Criteria andDbpIn(List<Integer> values) {
            addCriterion("dbp in", values, "dbp");
            return (Criteria) this;
        }

        public Criteria andDbpNotIn(List<Integer> values) {
            addCriterion("dbp not in", values, "dbp");
            return (Criteria) this;
        }

        public Criteria andDbpBetween(Integer value1, Integer value2) {
            addCriterion("dbp between", value1, value2, "dbp");
            return (Criteria) this;
        }

        public Criteria andDbpNotBetween(Integer value1, Integer value2) {
            addCriterion("dbp not between", value1, value2, "dbp");
            return (Criteria) this;
        }

        public Criteria andSbpIsNull() {
            addCriterion("sbp is null");
            return (Criteria) this;
        }

        public Criteria andSbpIsNotNull() {
            addCriterion("sbp is not null");
            return (Criteria) this;
        }

        public Criteria andSbpEqualTo(Integer value) {
            addCriterion("sbp =", value, "sbp");
            return (Criteria) this;
        }

        public Criteria andSbpNotEqualTo(Integer value) {
            addCriterion("sbp <>", value, "sbp");
            return (Criteria) this;
        }

        public Criteria andSbpGreaterThan(Integer value) {
            addCriterion("sbp >", value, "sbp");
            return (Criteria) this;
        }

        public Criteria andSbpGreaterThanOrEqualTo(Integer value) {
            addCriterion("sbp >=", value, "sbp");
            return (Criteria) this;
        }

        public Criteria andSbpLessThan(Integer value) {
            addCriterion("sbp <", value, "sbp");
            return (Criteria) this;
        }

        public Criteria andSbpLessThanOrEqualTo(Integer value) {
            addCriterion("sbp <=", value, "sbp");
            return (Criteria) this;
        }

        public Criteria andSbpIn(List<Integer> values) {
            addCriterion("sbp in", values, "sbp");
            return (Criteria) this;
        }

        public Criteria andSbpNotIn(List<Integer> values) {
            addCriterion("sbp not in", values, "sbp");
            return (Criteria) this;
        }

        public Criteria andSbpBetween(Integer value1, Integer value2) {
            addCriterion("sbp between", value1, value2, "sbp");
            return (Criteria) this;
        }

        public Criteria andSbpNotBetween(Integer value1, Integer value2) {
            addCriterion("sbp not between", value1, value2, "sbp");
            return (Criteria) this;
        }

        public Criteria andSphygmusIsNull() {
            addCriterion("sphygmus is null");
            return (Criteria) this;
        }

        public Criteria andSphygmusIsNotNull() {
            addCriterion("sphygmus is not null");
            return (Criteria) this;
        }

        public Criteria andSphygmusEqualTo(Integer value) {
            addCriterion("sphygmus =", value, "sphygmus");
            return (Criteria) this;
        }

        public Criteria andSphygmusNotEqualTo(Integer value) {
            addCriterion("sphygmus <>", value, "sphygmus");
            return (Criteria) this;
        }

        public Criteria andSphygmusGreaterThan(Integer value) {
            addCriterion("sphygmus >", value, "sphygmus");
            return (Criteria) this;
        }

        public Criteria andSphygmusGreaterThanOrEqualTo(Integer value) {
            addCriterion("sphygmus >=", value, "sphygmus");
            return (Criteria) this;
        }

        public Criteria andSphygmusLessThan(Integer value) {
            addCriterion("sphygmus <", value, "sphygmus");
            return (Criteria) this;
        }

        public Criteria andSphygmusLessThanOrEqualTo(Integer value) {
            addCriterion("sphygmus <=", value, "sphygmus");
            return (Criteria) this;
        }

        public Criteria andSphygmusIn(List<Integer> values) {
            addCriterion("sphygmus in", values, "sphygmus");
            return (Criteria) this;
        }

        public Criteria andSphygmusNotIn(List<Integer> values) {
            addCriterion("sphygmus not in", values, "sphygmus");
            return (Criteria) this;
        }

        public Criteria andSphygmusBetween(Integer value1, Integer value2) {
            addCriterion("sphygmus between", value1, value2, "sphygmus");
            return (Criteria) this;
        }

        public Criteria andSphygmusNotBetween(Integer value1, Integer value2) {
            addCriterion("sphygmus not between", value1, value2, "sphygmus");
            return (Criteria) this;
        }

        public Criteria andConclusionIsNull() {
            addCriterion("conclusion is null");
            return (Criteria) this;
        }

        public Criteria andConclusionIsNotNull() {
            addCriterion("conclusion is not null");
            return (Criteria) this;
        }

        public Criteria andConclusionEqualTo(String value) {
            addCriterion("conclusion =", value, "conclusion");
            return (Criteria) this;
        }

        public Criteria andConclusionNotEqualTo(String value) {
            addCriterion("conclusion <>", value, "conclusion");
            return (Criteria) this;
        }

        public Criteria andConclusionGreaterThan(String value) {
            addCriterion("conclusion >", value, "conclusion");
            return (Criteria) this;
        }

        public Criteria andConclusionGreaterThanOrEqualTo(String value) {
            addCriterion("conclusion >=", value, "conclusion");
            return (Criteria) this;
        }

        public Criteria andConclusionLessThan(String value) {
            addCriterion("conclusion <", value, "conclusion");
            return (Criteria) this;
        }

        public Criteria andConclusionLessThanOrEqualTo(String value) {
            addCriterion("conclusion <=", value, "conclusion");
            return (Criteria) this;
        }

        public Criteria andConclusionLike(String value) {
            addCriterion("conclusion like", value, "conclusion");
            return (Criteria) this;
        }

        public Criteria andConclusionNotLike(String value) {
            addCriterion("conclusion not like", value, "conclusion");
            return (Criteria) this;
        }

        public Criteria andConclusionIn(List<String> values) {
            addCriterion("conclusion in", values, "conclusion");
            return (Criteria) this;
        }

        public Criteria andConclusionNotIn(List<String> values) {
            addCriterion("conclusion not in", values, "conclusion");
            return (Criteria) this;
        }

        public Criteria andConclusionBetween(String value1, String value2) {
            addCriterion("conclusion between", value1, value2, "conclusion");
            return (Criteria) this;
        }

        public Criteria andConclusionNotBetween(String value1, String value2) {
            addCriterion("conclusion not between", value1, value2, "conclusion");
            return (Criteria) this;
        }

        public Criteria andAdviceIsNull() {
            addCriterion("advice is null");
            return (Criteria) this;
        }

        public Criteria andAdviceIsNotNull() {
            addCriterion("advice is not null");
            return (Criteria) this;
        }

        public Criteria andAdviceEqualTo(String value) {
            addCriterion("advice =", value, "advice");
            return (Criteria) this;
        }

        public Criteria andAdviceNotEqualTo(String value) {
            addCriterion("advice <>", value, "advice");
            return (Criteria) this;
        }

        public Criteria andAdviceGreaterThan(String value) {
            addCriterion("advice >", value, "advice");
            return (Criteria) this;
        }

        public Criteria andAdviceGreaterThanOrEqualTo(String value) {
            addCriterion("advice >=", value, "advice");
            return (Criteria) this;
        }

        public Criteria andAdviceLessThan(String value) {
            addCriterion("advice <", value, "advice");
            return (Criteria) this;
        }

        public Criteria andAdviceLessThanOrEqualTo(String value) {
            addCriterion("advice <=", value, "advice");
            return (Criteria) this;
        }

        public Criteria andAdviceLike(String value) {
            addCriterion("advice like", value, "advice");
            return (Criteria) this;
        }

        public Criteria andAdviceNotLike(String value) {
            addCriterion("advice not like", value, "advice");
            return (Criteria) this;
        }

        public Criteria andAdviceIn(List<String> values) {
            addCriterion("advice in", values, "advice");
            return (Criteria) this;
        }

        public Criteria andAdviceNotIn(List<String> values) {
            addCriterion("advice not in", values, "advice");
            return (Criteria) this;
        }

        public Criteria andAdviceBetween(String value1, String value2) {
            addCriterion("advice between", value1, value2, "advice");
            return (Criteria) this;
        }

        public Criteria andAdviceNotBetween(String value1, String value2) {
            addCriterion("advice not between", value1, value2, "advice");
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