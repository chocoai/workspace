package com.yhcrt.healthcloud.memberBack.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class MemberRechargeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MemberRechargeExample() {
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

        public Criteria andMemberIdIsNull() {
            addCriterion("member_id is null");
            return (Criteria) this;
        }

        public Criteria andMemberIdIsNotNull() {
            addCriterion("member_id is not null");
            return (Criteria) this;
        }

        public Criteria andMemberIdEqualTo(String value) {
            addCriterion("member_id =", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotEqualTo(String value) {
            addCriterion("member_id <>", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdGreaterThan(String value) {
            addCriterion("member_id >", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdGreaterThanOrEqualTo(String value) {
            addCriterion("member_id >=", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLessThan(String value) {
            addCriterion("member_id <", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLessThanOrEqualTo(String value) {
            addCriterion("member_id <=", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLike(String value) {
            addCriterion("member_id like", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotLike(String value) {
            addCriterion("member_id not like", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdIn(List<String> values) {
            addCriterion("member_id in", values, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotIn(List<String> values) {
            addCriterion("member_id not in", values, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdBetween(String value1, String value2) {
            addCriterion("member_id between", value1, value2, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotBetween(String value1, String value2) {
            addCriterion("member_id not between", value1, value2, "memberId");
            return (Criteria) this;
        }

        public Criteria andKangbiIsNull() {
            addCriterion("kangbi is null");
            return (Criteria) this;
        }

        public Criteria andKangbiIsNotNull() {
            addCriterion("kangbi is not null");
            return (Criteria) this;
        }

        public Criteria andKangbiEqualTo(Integer value) {
            addCriterion("kangbi =", value, "kangbi");
            return (Criteria) this;
        }

        public Criteria andKangbiNotEqualTo(Integer value) {
            addCriterion("kangbi <>", value, "kangbi");
            return (Criteria) this;
        }

        public Criteria andKangbiGreaterThan(Integer value) {
            addCriterion("kangbi >", value, "kangbi");
            return (Criteria) this;
        }

        public Criteria andKangbiGreaterThanOrEqualTo(Integer value) {
            addCriterion("kangbi >=", value, "kangbi");
            return (Criteria) this;
        }

        public Criteria andKangbiLessThan(Integer value) {
            addCriterion("kangbi <", value, "kangbi");
            return (Criteria) this;
        }

        public Criteria andKangbiLessThanOrEqualTo(Integer value) {
            addCriterion("kangbi <=", value, "kangbi");
            return (Criteria) this;
        }

        public Criteria andKangbiIn(List<Integer> values) {
            addCriterion("kangbi in", values, "kangbi");
            return (Criteria) this;
        }

        public Criteria andKangbiNotIn(List<Integer> values) {
            addCriterion("kangbi not in", values, "kangbi");
            return (Criteria) this;
        }

        public Criteria andKangbiBetween(Integer value1, Integer value2) {
            addCriterion("kangbi between", value1, value2, "kangbi");
            return (Criteria) this;
        }

        public Criteria andKangbiNotBetween(Integer value1, Integer value2) {
            addCriterion("kangbi not between", value1, value2, "kangbi");
            return (Criteria) this;
        }

        public Criteria andScoreIsNull() {
            addCriterion("score is null");
            return (Criteria) this;
        }

        public Criteria andScoreIsNotNull() {
            addCriterion("score is not null");
            return (Criteria) this;
        }

        public Criteria andScoreEqualTo(Integer value) {
            addCriterion("score =", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotEqualTo(Integer value) {
            addCriterion("score <>", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThan(Integer value) {
            addCriterion("score >", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("score >=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThan(Integer value) {
            addCriterion("score <", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThanOrEqualTo(Integer value) {
            addCriterion("score <=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreIn(List<Integer> values) {
            addCriterion("score in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotIn(List<Integer> values) {
            addCriterion("score not in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreBetween(Integer value1, Integer value2) {
            addCriterion("score between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("score not between", value1, value2, "score");
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

        public Criteria andCreateUserIsNull() {
            addCriterion("create_user is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNotNull() {
            addCriterion("create_user is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserEqualTo(String value) {
            addCriterion("create_user =", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotEqualTo(String value) {
            addCriterion("create_user <>", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThan(String value) {
            addCriterion("create_user >", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThanOrEqualTo(String value) {
            addCriterion("create_user >=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThan(String value) {
            addCriterion("create_user <", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThanOrEqualTo(String value) {
            addCriterion("create_user <=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLike(String value) {
            addCriterion("create_user like", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotLike(String value) {
            addCriterion("create_user not like", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserIn(List<String> values) {
            addCriterion("create_user in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotIn(List<String> values) {
            addCriterion("create_user not in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserBetween(String value1, String value2) {
            addCriterion("create_user between", value1, value2, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotBetween(String value1, String value2) {
            addCriterion("create_user not between", value1, value2, "createUser");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
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