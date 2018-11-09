package com.yhcrt.healthcloud.mall.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class UserCollectExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserCollectExample() {
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

        public Criteria andRefIdIsNull() {
            addCriterion("ref_id is null");
            return (Criteria) this;
        }

        public Criteria andRefIdIsNotNull() {
            addCriterion("ref_id is not null");
            return (Criteria) this;
        }

        public Criteria andRefIdEqualTo(Integer value) {
            addCriterion("ref_id =", value, "refId");
            return (Criteria) this;
        }

        public Criteria andRefIdNotEqualTo(Integer value) {
            addCriterion("ref_id <>", value, "refId");
            return (Criteria) this;
        }

        public Criteria andRefIdGreaterThan(Integer value) {
            addCriterion("ref_id >", value, "refId");
            return (Criteria) this;
        }

        public Criteria andRefIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ref_id >=", value, "refId");
            return (Criteria) this;
        }

        public Criteria andRefIdLessThan(Integer value) {
            addCriterion("ref_id <", value, "refId");
            return (Criteria) this;
        }

        public Criteria andRefIdLessThanOrEqualTo(Integer value) {
            addCriterion("ref_id <=", value, "refId");
            return (Criteria) this;
        }

        public Criteria andRefIdIn(List<Integer> values) {
            addCriterion("ref_id in", values, "refId");
            return (Criteria) this;
        }

        public Criteria andRefIdNotIn(List<Integer> values) {
            addCriterion("ref_id not in", values, "refId");
            return (Criteria) this;
        }

        public Criteria andRefIdBetween(Integer value1, Integer value2) {
            addCriterion("ref_id between", value1, value2, "refId");
            return (Criteria) this;
        }

        public Criteria andRefIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ref_id not between", value1, value2, "refId");
            return (Criteria) this;
        }

        public Criteria andCollectTypeIsNull() {
            addCriterion("collect_type is null");
            return (Criteria) this;
        }

        public Criteria andCollectTypeIsNotNull() {
            addCriterion("collect_type is not null");
            return (Criteria) this;
        }

        public Criteria andCollectTypeEqualTo(String value) {
            addCriterion("collect_type =", value, "collectType");
            return (Criteria) this;
        }

        public Criteria andCollectTypeNotEqualTo(String value) {
            addCriterion("collect_type <>", value, "collectType");
            return (Criteria) this;
        }

        public Criteria andCollectTypeGreaterThan(String value) {
            addCriterion("collect_type >", value, "collectType");
            return (Criteria) this;
        }

        public Criteria andCollectTypeGreaterThanOrEqualTo(String value) {
            addCriterion("collect_type >=", value, "collectType");
            return (Criteria) this;
        }

        public Criteria andCollectTypeLessThan(String value) {
            addCriterion("collect_type <", value, "collectType");
            return (Criteria) this;
        }

        public Criteria andCollectTypeLessThanOrEqualTo(String value) {
            addCriterion("collect_type <=", value, "collectType");
            return (Criteria) this;
        }

        public Criteria andCollectTypeLike(String value) {
            addCriterion("collect_type like", value, "collectType");
            return (Criteria) this;
        }

        public Criteria andCollectTypeNotLike(String value) {
            addCriterion("collect_type not like", value, "collectType");
            return (Criteria) this;
        }

        public Criteria andCollectTypeIn(List<String> values) {
            addCriterion("collect_type in", values, "collectType");
            return (Criteria) this;
        }

        public Criteria andCollectTypeNotIn(List<String> values) {
            addCriterion("collect_type not in", values, "collectType");
            return (Criteria) this;
        }

        public Criteria andCollectTypeBetween(String value1, String value2) {
            addCriterion("collect_type between", value1, value2, "collectType");
            return (Criteria) this;
        }

        public Criteria andCollectTypeNotBetween(String value1, String value2) {
            addCriterion("collect_type not between", value1, value2, "collectType");
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

        public Criteria andCreateTimeEqualTo(String value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(String value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(String value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(String value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(String value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(String value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLike(String value) {
            addCriterion("create_time like", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotLike(String value) {
            addCriterion("create_time not like", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<String> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<String> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(String value1, String value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(String value1, String value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
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