package com.yhcrt.iHealthCloud.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class HdBloodPressureExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public HdBloodPressureExample() {
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

        public Criteria andDataDateIsNull() {
            addCriterion("data_date is null");
            return (Criteria) this;
        }

        public Criteria andDataDateIsNotNull() {
            addCriterion("data_date is not null");
            return (Criteria) this;
        }

        public Criteria andDataDateEqualTo(String value) {
            addCriterion("data_date =", value, "dataDate");
            return (Criteria) this;
        }

        public Criteria andDataDateNotEqualTo(String value) {
            addCriterion("data_date <>", value, "dataDate");
            return (Criteria) this;
        }

        public Criteria andDataDateGreaterThan(String value) {
            addCriterion("data_date >", value, "dataDate");
            return (Criteria) this;
        }

        public Criteria andDataDateGreaterThanOrEqualTo(String value) {
            addCriterion("data_date >=", value, "dataDate");
            return (Criteria) this;
        }

        public Criteria andDataDateLessThan(String value) {
            addCriterion("data_date <", value, "dataDate");
            return (Criteria) this;
        }

        public Criteria andDataDateLessThanOrEqualTo(String value) {
            addCriterion("data_date <=", value, "dataDate");
            return (Criteria) this;
        }

        public Criteria andDataDateLike(String value) {
            addCriterion("data_date like", value, "dataDate");
            return (Criteria) this;
        }

        public Criteria andDataDateNotLike(String value) {
            addCriterion("data_date not like", value, "dataDate");
            return (Criteria) this;
        }

        public Criteria andDataDateIn(List<String> values) {
            addCriterion("data_date in", values, "dataDate");
            return (Criteria) this;
        }

        public Criteria andDataDateNotIn(List<String> values) {
            addCriterion("data_date not in", values, "dataDate");
            return (Criteria) this;
        }

        public Criteria andDataDateBetween(String value1, String value2) {
            addCriterion("data_date between", value1, value2, "dataDate");
            return (Criteria) this;
        }

        public Criteria andDataDateNotBetween(String value1, String value2) {
            addCriterion("data_date not between", value1, value2, "dataDate");
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

        public Criteria andCext1IsNull() {
            addCriterion("cext1 is null");
            return (Criteria) this;
        }

        public Criteria andCext1IsNotNull() {
            addCriterion("cext1 is not null");
            return (Criteria) this;
        }

        public Criteria andCext1EqualTo(String value) {
            addCriterion("cext1 =", value, "cext1");
            return (Criteria) this;
        }

        public Criteria andCext1NotEqualTo(String value) {
            addCriterion("cext1 <>", value, "cext1");
            return (Criteria) this;
        }

        public Criteria andCext1GreaterThan(String value) {
            addCriterion("cext1 >", value, "cext1");
            return (Criteria) this;
        }

        public Criteria andCext1GreaterThanOrEqualTo(String value) {
            addCriterion("cext1 >=", value, "cext1");
            return (Criteria) this;
        }

        public Criteria andCext1LessThan(String value) {
            addCriterion("cext1 <", value, "cext1");
            return (Criteria) this;
        }

        public Criteria andCext1LessThanOrEqualTo(String value) {
            addCriterion("cext1 <=", value, "cext1");
            return (Criteria) this;
        }

        public Criteria andCext1Like(String value) {
            addCriterion("cext1 like", value, "cext1");
            return (Criteria) this;
        }

        public Criteria andCext1NotLike(String value) {
            addCriterion("cext1 not like", value, "cext1");
            return (Criteria) this;
        }

        public Criteria andCext1In(List<String> values) {
            addCriterion("cext1 in", values, "cext1");
            return (Criteria) this;
        }

        public Criteria andCext1NotIn(List<String> values) {
            addCriterion("cext1 not in", values, "cext1");
            return (Criteria) this;
        }

        public Criteria andCext1Between(String value1, String value2) {
            addCriterion("cext1 between", value1, value2, "cext1");
            return (Criteria) this;
        }

        public Criteria andCext1NotBetween(String value1, String value2) {
            addCriterion("cext1 not between", value1, value2, "cext1");
            return (Criteria) this;
        }

        public Criteria andCext2IsNull() {
            addCriterion("cext2 is null");
            return (Criteria) this;
        }

        public Criteria andCext2IsNotNull() {
            addCriterion("cext2 is not null");
            return (Criteria) this;
        }

        public Criteria andCext2EqualTo(String value) {
            addCriterion("cext2 =", value, "cext2");
            return (Criteria) this;
        }

        public Criteria andCext2NotEqualTo(String value) {
            addCriterion("cext2 <>", value, "cext2");
            return (Criteria) this;
        }

        public Criteria andCext2GreaterThan(String value) {
            addCriterion("cext2 >", value, "cext2");
            return (Criteria) this;
        }

        public Criteria andCext2GreaterThanOrEqualTo(String value) {
            addCriterion("cext2 >=", value, "cext2");
            return (Criteria) this;
        }

        public Criteria andCext2LessThan(String value) {
            addCriterion("cext2 <", value, "cext2");
            return (Criteria) this;
        }

        public Criteria andCext2LessThanOrEqualTo(String value) {
            addCriterion("cext2 <=", value, "cext2");
            return (Criteria) this;
        }

        public Criteria andCext2Like(String value) {
            addCriterion("cext2 like", value, "cext2");
            return (Criteria) this;
        }

        public Criteria andCext2NotLike(String value) {
            addCriterion("cext2 not like", value, "cext2");
            return (Criteria) this;
        }

        public Criteria andCext2In(List<String> values) {
            addCriterion("cext2 in", values, "cext2");
            return (Criteria) this;
        }

        public Criteria andCext2NotIn(List<String> values) {
            addCriterion("cext2 not in", values, "cext2");
            return (Criteria) this;
        }

        public Criteria andCext2Between(String value1, String value2) {
            addCriterion("cext2 between", value1, value2, "cext2");
            return (Criteria) this;
        }

        public Criteria andCext2NotBetween(String value1, String value2) {
            addCriterion("cext2 not between", value1, value2, "cext2");
            return (Criteria) this;
        }

        public Criteria andCext3IsNull() {
            addCriterion("cext3 is null");
            return (Criteria) this;
        }

        public Criteria andCext3IsNotNull() {
            addCriterion("cext3 is not null");
            return (Criteria) this;
        }

        public Criteria andCext3EqualTo(String value) {
            addCriterion("cext3 =", value, "cext3");
            return (Criteria) this;
        }

        public Criteria andCext3NotEqualTo(String value) {
            addCriterion("cext3 <>", value, "cext3");
            return (Criteria) this;
        }

        public Criteria andCext3GreaterThan(String value) {
            addCriterion("cext3 >", value, "cext3");
            return (Criteria) this;
        }

        public Criteria andCext3GreaterThanOrEqualTo(String value) {
            addCriterion("cext3 >=", value, "cext3");
            return (Criteria) this;
        }

        public Criteria andCext3LessThan(String value) {
            addCriterion("cext3 <", value, "cext3");
            return (Criteria) this;
        }

        public Criteria andCext3LessThanOrEqualTo(String value) {
            addCriterion("cext3 <=", value, "cext3");
            return (Criteria) this;
        }

        public Criteria andCext3Like(String value) {
            addCriterion("cext3 like", value, "cext3");
            return (Criteria) this;
        }

        public Criteria andCext3NotLike(String value) {
            addCriterion("cext3 not like", value, "cext3");
            return (Criteria) this;
        }

        public Criteria andCext3In(List<String> values) {
            addCriterion("cext3 in", values, "cext3");
            return (Criteria) this;
        }

        public Criteria andCext3NotIn(List<String> values) {
            addCriterion("cext3 not in", values, "cext3");
            return (Criteria) this;
        }

        public Criteria andCext3Between(String value1, String value2) {
            addCriterion("cext3 between", value1, value2, "cext3");
            return (Criteria) this;
        }

        public Criteria andCext3NotBetween(String value1, String value2) {
            addCriterion("cext3 not between", value1, value2, "cext3");
            return (Criteria) this;
        }

        public Criteria andIext1IsNull() {
            addCriterion("iext1 is null");
            return (Criteria) this;
        }

        public Criteria andIext1IsNotNull() {
            addCriterion("iext1 is not null");
            return (Criteria) this;
        }

        public Criteria andIext1EqualTo(Integer value) {
            addCriterion("iext1 =", value, "iext1");
            return (Criteria) this;
        }

        public Criteria andIext1NotEqualTo(Integer value) {
            addCriterion("iext1 <>", value, "iext1");
            return (Criteria) this;
        }

        public Criteria andIext1GreaterThan(Integer value) {
            addCriterion("iext1 >", value, "iext1");
            return (Criteria) this;
        }

        public Criteria andIext1GreaterThanOrEqualTo(Integer value) {
            addCriterion("iext1 >=", value, "iext1");
            return (Criteria) this;
        }

        public Criteria andIext1LessThan(Integer value) {
            addCriterion("iext1 <", value, "iext1");
            return (Criteria) this;
        }

        public Criteria andIext1LessThanOrEqualTo(Integer value) {
            addCriterion("iext1 <=", value, "iext1");
            return (Criteria) this;
        }

        public Criteria andIext1In(List<Integer> values) {
            addCriterion("iext1 in", values, "iext1");
            return (Criteria) this;
        }

        public Criteria andIext1NotIn(List<Integer> values) {
            addCriterion("iext1 not in", values, "iext1");
            return (Criteria) this;
        }

        public Criteria andIext1Between(Integer value1, Integer value2) {
            addCriterion("iext1 between", value1, value2, "iext1");
            return (Criteria) this;
        }

        public Criteria andIext1NotBetween(Integer value1, Integer value2) {
            addCriterion("iext1 not between", value1, value2, "iext1");
            return (Criteria) this;
        }

        public Criteria andIext2IsNull() {
            addCriterion("iext2 is null");
            return (Criteria) this;
        }

        public Criteria andIext2IsNotNull() {
            addCriterion("iext2 is not null");
            return (Criteria) this;
        }

        public Criteria andIext2EqualTo(Integer value) {
            addCriterion("iext2 =", value, "iext2");
            return (Criteria) this;
        }

        public Criteria andIext2NotEqualTo(Integer value) {
            addCriterion("iext2 <>", value, "iext2");
            return (Criteria) this;
        }

        public Criteria andIext2GreaterThan(Integer value) {
            addCriterion("iext2 >", value, "iext2");
            return (Criteria) this;
        }

        public Criteria andIext2GreaterThanOrEqualTo(Integer value) {
            addCriterion("iext2 >=", value, "iext2");
            return (Criteria) this;
        }

        public Criteria andIext2LessThan(Integer value) {
            addCriterion("iext2 <", value, "iext2");
            return (Criteria) this;
        }

        public Criteria andIext2LessThanOrEqualTo(Integer value) {
            addCriterion("iext2 <=", value, "iext2");
            return (Criteria) this;
        }

        public Criteria andIext2In(List<Integer> values) {
            addCriterion("iext2 in", values, "iext2");
            return (Criteria) this;
        }

        public Criteria andIext2NotIn(List<Integer> values) {
            addCriterion("iext2 not in", values, "iext2");
            return (Criteria) this;
        }

        public Criteria andIext2Between(Integer value1, Integer value2) {
            addCriterion("iext2 between", value1, value2, "iext2");
            return (Criteria) this;
        }

        public Criteria andIext2NotBetween(Integer value1, Integer value2) {
            addCriterion("iext2 not between", value1, value2, "iext2");
            return (Criteria) this;
        }

        public Criteria andDext1IsNull() {
            addCriterion("dext1 is null");
            return (Criteria) this;
        }

        public Criteria andDext1IsNotNull() {
            addCriterion("dext1 is not null");
            return (Criteria) this;
        }

        public Criteria andDext1EqualTo(Date value) {
            addCriterionForJDBCDate("dext1 =", value, "dext1");
            return (Criteria) this;
        }

        public Criteria andDext1NotEqualTo(Date value) {
            addCriterionForJDBCDate("dext1 <>", value, "dext1");
            return (Criteria) this;
        }

        public Criteria andDext1GreaterThan(Date value) {
            addCriterionForJDBCDate("dext1 >", value, "dext1");
            return (Criteria) this;
        }

        public Criteria andDext1GreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("dext1 >=", value, "dext1");
            return (Criteria) this;
        }

        public Criteria andDext1LessThan(Date value) {
            addCriterionForJDBCDate("dext1 <", value, "dext1");
            return (Criteria) this;
        }

        public Criteria andDext1LessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("dext1 <=", value, "dext1");
            return (Criteria) this;
        }

        public Criteria andDext1In(List<Date> values) {
            addCriterionForJDBCDate("dext1 in", values, "dext1");
            return (Criteria) this;
        }

        public Criteria andDext1NotIn(List<Date> values) {
            addCriterionForJDBCDate("dext1 not in", values, "dext1");
            return (Criteria) this;
        }

        public Criteria andDext1Between(Date value1, Date value2) {
            addCriterionForJDBCDate("dext1 between", value1, value2, "dext1");
            return (Criteria) this;
        }

        public Criteria andDext1NotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("dext1 not between", value1, value2, "dext1");
            return (Criteria) this;
        }

        public Criteria andDext2IsNull() {
            addCriterion("dext2 is null");
            return (Criteria) this;
        }

        public Criteria andDext2IsNotNull() {
            addCriterion("dext2 is not null");
            return (Criteria) this;
        }

        public Criteria andDext2EqualTo(Date value) {
            addCriterionForJDBCDate("dext2 =", value, "dext2");
            return (Criteria) this;
        }

        public Criteria andDext2NotEqualTo(Date value) {
            addCriterionForJDBCDate("dext2 <>", value, "dext2");
            return (Criteria) this;
        }

        public Criteria andDext2GreaterThan(Date value) {
            addCriterionForJDBCDate("dext2 >", value, "dext2");
            return (Criteria) this;
        }

        public Criteria andDext2GreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("dext2 >=", value, "dext2");
            return (Criteria) this;
        }

        public Criteria andDext2LessThan(Date value) {
            addCriterionForJDBCDate("dext2 <", value, "dext2");
            return (Criteria) this;
        }

        public Criteria andDext2LessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("dext2 <=", value, "dext2");
            return (Criteria) this;
        }

        public Criteria andDext2In(List<Date> values) {
            addCriterionForJDBCDate("dext2 in", values, "dext2");
            return (Criteria) this;
        }

        public Criteria andDext2NotIn(List<Date> values) {
            addCriterionForJDBCDate("dext2 not in", values, "dext2");
            return (Criteria) this;
        }

        public Criteria andDext2Between(Date value1, Date value2) {
            addCriterionForJDBCDate("dext2 between", value1, value2, "dext2");
            return (Criteria) this;
        }

        public Criteria andDext2NotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("dext2 not between", value1, value2, "dext2");
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