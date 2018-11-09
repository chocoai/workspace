package com.yhcrt.iHealthCloud.entity;

import java.util.ArrayList;
import java.util.List;

public class SysDictionaryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysDictionaryExample() {
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

        public Criteria andDictIdIsNull() {
            addCriterion("dict_id is null");
            return (Criteria) this;
        }

        public Criteria andDictIdIsNotNull() {
            addCriterion("dict_id is not null");
            return (Criteria) this;
        }

        public Criteria andDictIdEqualTo(Integer value) {
            addCriterion("dict_id =", value, "dictId");
            return (Criteria) this;
        }

        public Criteria andDictIdNotEqualTo(Integer value) {
            addCriterion("dict_id <>", value, "dictId");
            return (Criteria) this;
        }

        public Criteria andDictIdGreaterThan(Integer value) {
            addCriterion("dict_id >", value, "dictId");
            return (Criteria) this;
        }

        public Criteria andDictIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("dict_id >=", value, "dictId");
            return (Criteria) this;
        }

        public Criteria andDictIdLessThan(Integer value) {
            addCriterion("dict_id <", value, "dictId");
            return (Criteria) this;
        }

        public Criteria andDictIdLessThanOrEqualTo(Integer value) {
            addCriterion("dict_id <=", value, "dictId");
            return (Criteria) this;
        }

        public Criteria andDictIdIn(List<Integer> values) {
            addCriterion("dict_id in", values, "dictId");
            return (Criteria) this;
        }

        public Criteria andDictIdNotIn(List<Integer> values) {
            addCriterion("dict_id not in", values, "dictId");
            return (Criteria) this;
        }

        public Criteria andDictIdBetween(Integer value1, Integer value2) {
            addCriterion("dict_id between", value1, value2, "dictId");
            return (Criteria) this;
        }

        public Criteria andDictIdNotBetween(Integer value1, Integer value2) {
            addCriterion("dict_id not between", value1, value2, "dictId");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNull() {
            addCriterion("parent_id is null");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNotNull() {
            addCriterion("parent_id is not null");
            return (Criteria) this;
        }

        public Criteria andParentIdEqualTo(String value) {
            addCriterion("parent_id =", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotEqualTo(String value) {
            addCriterion("parent_id <>", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThan(String value) {
            addCriterion("parent_id >", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThanOrEqualTo(String value) {
            addCriterion("parent_id >=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThan(String value) {
            addCriterion("parent_id <", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThanOrEqualTo(String value) {
            addCriterion("parent_id <=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLike(String value) {
            addCriterion("parent_id like", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotLike(String value) {
            addCriterion("parent_id not like", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdIn(List<String> values) {
            addCriterion("parent_id in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotIn(List<String> values) {
            addCriterion("parent_id not in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdBetween(String value1, String value2) {
            addCriterion("parent_id between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotBetween(String value1, String value2) {
            addCriterion("parent_id not between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andDictZhNameIsNull() {
            addCriterion("dict_zh_name is null");
            return (Criteria) this;
        }

        public Criteria andDictZhNameIsNotNull() {
            addCriterion("dict_zh_name is not null");
            return (Criteria) this;
        }

        public Criteria andDictZhNameEqualTo(String value) {
            addCriterion("dict_zh_name =", value, "dictZhName");
            return (Criteria) this;
        }

        public Criteria andDictZhNameNotEqualTo(String value) {
            addCriterion("dict_zh_name <>", value, "dictZhName");
            return (Criteria) this;
        }

        public Criteria andDictZhNameGreaterThan(String value) {
            addCriterion("dict_zh_name >", value, "dictZhName");
            return (Criteria) this;
        }

        public Criteria andDictZhNameGreaterThanOrEqualTo(String value) {
            addCriterion("dict_zh_name >=", value, "dictZhName");
            return (Criteria) this;
        }

        public Criteria andDictZhNameLessThan(String value) {
            addCriterion("dict_zh_name <", value, "dictZhName");
            return (Criteria) this;
        }

        public Criteria andDictZhNameLessThanOrEqualTo(String value) {
            addCriterion("dict_zh_name <=", value, "dictZhName");
            return (Criteria) this;
        }

        public Criteria andDictZhNameLike(String value) {
            addCriterion("dict_zh_name like", value, "dictZhName");
            return (Criteria) this;
        }

        public Criteria andDictZhNameNotLike(String value) {
            addCriterion("dict_zh_name not like", value, "dictZhName");
            return (Criteria) this;
        }

        public Criteria andDictZhNameIn(List<String> values) {
            addCriterion("dict_zh_name in", values, "dictZhName");
            return (Criteria) this;
        }

        public Criteria andDictZhNameNotIn(List<String> values) {
            addCriterion("dict_zh_name not in", values, "dictZhName");
            return (Criteria) this;
        }

        public Criteria andDictZhNameBetween(String value1, String value2) {
            addCriterion("dict_zh_name between", value1, value2, "dictZhName");
            return (Criteria) this;
        }

        public Criteria andDictZhNameNotBetween(String value1, String value2) {
            addCriterion("dict_zh_name not between", value1, value2, "dictZhName");
            return (Criteria) this;
        }

        public Criteria andDictEnNameIsNull() {
            addCriterion("dict_en_name is null");
            return (Criteria) this;
        }

        public Criteria andDictEnNameIsNotNull() {
            addCriterion("dict_en_name is not null");
            return (Criteria) this;
        }

        public Criteria andDictEnNameEqualTo(String value) {
            addCriterion("dict_en_name =", value, "dictEnName");
            return (Criteria) this;
        }

        public Criteria andDictEnNameNotEqualTo(String value) {
            addCriterion("dict_en_name <>", value, "dictEnName");
            return (Criteria) this;
        }

        public Criteria andDictEnNameGreaterThan(String value) {
            addCriterion("dict_en_name >", value, "dictEnName");
            return (Criteria) this;
        }

        public Criteria andDictEnNameGreaterThanOrEqualTo(String value) {
            addCriterion("dict_en_name >=", value, "dictEnName");
            return (Criteria) this;
        }

        public Criteria andDictEnNameLessThan(String value) {
            addCriterion("dict_en_name <", value, "dictEnName");
            return (Criteria) this;
        }

        public Criteria andDictEnNameLessThanOrEqualTo(String value) {
            addCriterion("dict_en_name <=", value, "dictEnName");
            return (Criteria) this;
        }

        public Criteria andDictEnNameLike(String value) {
            addCriterion("dict_en_name like", value, "dictEnName");
            return (Criteria) this;
        }

        public Criteria andDictEnNameNotLike(String value) {
            addCriterion("dict_en_name not like", value, "dictEnName");
            return (Criteria) this;
        }

        public Criteria andDictEnNameIn(List<String> values) {
            addCriterion("dict_en_name in", values, "dictEnName");
            return (Criteria) this;
        }

        public Criteria andDictEnNameNotIn(List<String> values) {
            addCriterion("dict_en_name not in", values, "dictEnName");
            return (Criteria) this;
        }

        public Criteria andDictEnNameBetween(String value1, String value2) {
            addCriterion("dict_en_name between", value1, value2, "dictEnName");
            return (Criteria) this;
        }

        public Criteria andDictEnNameNotBetween(String value1, String value2) {
            addCriterion("dict_en_name not between", value1, value2, "dictEnName");
            return (Criteria) this;
        }

        public Criteria andDictKeyIsNull() {
            addCriterion("dict_key is null");
            return (Criteria) this;
        }

        public Criteria andDictKeyIsNotNull() {
            addCriterion("dict_key is not null");
            return (Criteria) this;
        }

        public Criteria andDictKeyEqualTo(String value) {
            addCriterion("dict_key =", value, "dictKey");
            return (Criteria) this;
        }

        public Criteria andDictKeyNotEqualTo(String value) {
            addCriterion("dict_key <>", value, "dictKey");
            return (Criteria) this;
        }

        public Criteria andDictKeyGreaterThan(String value) {
            addCriterion("dict_key >", value, "dictKey");
            return (Criteria) this;
        }

        public Criteria andDictKeyGreaterThanOrEqualTo(String value) {
            addCriterion("dict_key >=", value, "dictKey");
            return (Criteria) this;
        }

        public Criteria andDictKeyLessThan(String value) {
            addCriterion("dict_key <", value, "dictKey");
            return (Criteria) this;
        }

        public Criteria andDictKeyLessThanOrEqualTo(String value) {
            addCriterion("dict_key <=", value, "dictKey");
            return (Criteria) this;
        }

        public Criteria andDictKeyLike(String value) {
            addCriterion("dict_key like", value, "dictKey");
            return (Criteria) this;
        }

        public Criteria andDictKeyNotLike(String value) {
            addCriterion("dict_key not like", value, "dictKey");
            return (Criteria) this;
        }

        public Criteria andDictKeyIn(List<String> values) {
            addCriterion("dict_key in", values, "dictKey");
            return (Criteria) this;
        }

        public Criteria andDictKeyNotIn(List<String> values) {
            addCriterion("dict_key not in", values, "dictKey");
            return (Criteria) this;
        }

        public Criteria andDictKeyBetween(String value1, String value2) {
            addCriterion("dict_key between", value1, value2, "dictKey");
            return (Criteria) this;
        }

        public Criteria andDictKeyNotBetween(String value1, String value2) {
            addCriterion("dict_key not between", value1, value2, "dictKey");
            return (Criteria) this;
        }

        public Criteria andDictValueIsNull() {
            addCriterion("dict_value is null");
            return (Criteria) this;
        }

        public Criteria andDictValueIsNotNull() {
            addCriterion("dict_value is not null");
            return (Criteria) this;
        }

        public Criteria andDictValueEqualTo(String value) {
            addCriterion("dict_value =", value, "dictValue");
            return (Criteria) this;
        }

        public Criteria andDictValueNotEqualTo(String value) {
            addCriterion("dict_value <>", value, "dictValue");
            return (Criteria) this;
        }

        public Criteria andDictValueGreaterThan(String value) {
            addCriterion("dict_value >", value, "dictValue");
            return (Criteria) this;
        }

        public Criteria andDictValueGreaterThanOrEqualTo(String value) {
            addCriterion("dict_value >=", value, "dictValue");
            return (Criteria) this;
        }

        public Criteria andDictValueLessThan(String value) {
            addCriterion("dict_value <", value, "dictValue");
            return (Criteria) this;
        }

        public Criteria andDictValueLessThanOrEqualTo(String value) {
            addCriterion("dict_value <=", value, "dictValue");
            return (Criteria) this;
        }

        public Criteria andDictValueLike(String value) {
            addCriterion("dict_value like", value, "dictValue");
            return (Criteria) this;
        }

        public Criteria andDictValueNotLike(String value) {
            addCriterion("dict_value not like", value, "dictValue");
            return (Criteria) this;
        }

        public Criteria andDictValueIn(List<String> values) {
            addCriterion("dict_value in", values, "dictValue");
            return (Criteria) this;
        }

        public Criteria andDictValueNotIn(List<String> values) {
            addCriterion("dict_value not in", values, "dictValue");
            return (Criteria) this;
        }

        public Criteria andDictValueBetween(String value1, String value2) {
            addCriterion("dict_value between", value1, value2, "dictValue");
            return (Criteria) this;
        }

        public Criteria andDictValueNotBetween(String value1, String value2) {
            addCriterion("dict_value not between", value1, value2, "dictValue");
            return (Criteria) this;
        }

        public Criteria andDictDescIsNull() {
            addCriterion("dict_desc is null");
            return (Criteria) this;
        }

        public Criteria andDictDescIsNotNull() {
            addCriterion("dict_desc is not null");
            return (Criteria) this;
        }

        public Criteria andDictDescEqualTo(String value) {
            addCriterion("dict_desc =", value, "dictDesc");
            return (Criteria) this;
        }

        public Criteria andDictDescNotEqualTo(String value) {
            addCriterion("dict_desc <>", value, "dictDesc");
            return (Criteria) this;
        }

        public Criteria andDictDescGreaterThan(String value) {
            addCriterion("dict_desc >", value, "dictDesc");
            return (Criteria) this;
        }

        public Criteria andDictDescGreaterThanOrEqualTo(String value) {
            addCriterion("dict_desc >=", value, "dictDesc");
            return (Criteria) this;
        }

        public Criteria andDictDescLessThan(String value) {
            addCriterion("dict_desc <", value, "dictDesc");
            return (Criteria) this;
        }

        public Criteria andDictDescLessThanOrEqualTo(String value) {
            addCriterion("dict_desc <=", value, "dictDesc");
            return (Criteria) this;
        }

        public Criteria andDictDescLike(String value) {
            addCriterion("dict_desc like", value, "dictDesc");
            return (Criteria) this;
        }

        public Criteria andDictDescNotLike(String value) {
            addCriterion("dict_desc not like", value, "dictDesc");
            return (Criteria) this;
        }

        public Criteria andDictDescIn(List<String> values) {
            addCriterion("dict_desc in", values, "dictDesc");
            return (Criteria) this;
        }

        public Criteria andDictDescNotIn(List<String> values) {
            addCriterion("dict_desc not in", values, "dictDesc");
            return (Criteria) this;
        }

        public Criteria andDictDescBetween(String value1, String value2) {
            addCriterion("dict_desc between", value1, value2, "dictDesc");
            return (Criteria) this;
        }

        public Criteria andDictDescNotBetween(String value1, String value2) {
            addCriterion("dict_desc not between", value1, value2, "dictDesc");
            return (Criteria) this;
        }

        public Criteria andDictIconIsNull() {
            addCriterion("dict_icon is null");
            return (Criteria) this;
        }

        public Criteria andDictIconIsNotNull() {
            addCriterion("dict_icon is not null");
            return (Criteria) this;
        }

        public Criteria andDictIconEqualTo(String value) {
            addCriterion("dict_icon =", value, "dictIcon");
            return (Criteria) this;
        }

        public Criteria andDictIconNotEqualTo(String value) {
            addCriterion("dict_icon <>", value, "dictIcon");
            return (Criteria) this;
        }

        public Criteria andDictIconGreaterThan(String value) {
            addCriterion("dict_icon >", value, "dictIcon");
            return (Criteria) this;
        }

        public Criteria andDictIconGreaterThanOrEqualTo(String value) {
            addCriterion("dict_icon >=", value, "dictIcon");
            return (Criteria) this;
        }

        public Criteria andDictIconLessThan(String value) {
            addCriterion("dict_icon <", value, "dictIcon");
            return (Criteria) this;
        }

        public Criteria andDictIconLessThanOrEqualTo(String value) {
            addCriterion("dict_icon <=", value, "dictIcon");
            return (Criteria) this;
        }

        public Criteria andDictIconLike(String value) {
            addCriterion("dict_icon like", value, "dictIcon");
            return (Criteria) this;
        }

        public Criteria andDictIconNotLike(String value) {
            addCriterion("dict_icon not like", value, "dictIcon");
            return (Criteria) this;
        }

        public Criteria andDictIconIn(List<String> values) {
            addCriterion("dict_icon in", values, "dictIcon");
            return (Criteria) this;
        }

        public Criteria andDictIconNotIn(List<String> values) {
            addCriterion("dict_icon not in", values, "dictIcon");
            return (Criteria) this;
        }

        public Criteria andDictIconBetween(String value1, String value2) {
            addCriterion("dict_icon between", value1, value2, "dictIcon");
            return (Criteria) this;
        }

        public Criteria andDictIconNotBetween(String value1, String value2) {
            addCriterion("dict_icon not between", value1, value2, "dictIcon");
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

        public Criteria andOrderNumIsNull() {
            addCriterion("order_num is null");
            return (Criteria) this;
        }

        public Criteria andOrderNumIsNotNull() {
            addCriterion("order_num is not null");
            return (Criteria) this;
        }

        public Criteria andOrderNumEqualTo(Integer value) {
            addCriterion("order_num =", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumNotEqualTo(Integer value) {
            addCriterion("order_num <>", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumGreaterThan(Integer value) {
            addCriterion("order_num >", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_num >=", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumLessThan(Integer value) {
            addCriterion("order_num <", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumLessThanOrEqualTo(Integer value) {
            addCriterion("order_num <=", value, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumIn(List<Integer> values) {
            addCriterion("order_num in", values, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumNotIn(List<Integer> values) {
            addCriterion("order_num not in", values, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumBetween(Integer value1, Integer value2) {
            addCriterion("order_num between", value1, value2, "orderNum");
            return (Criteria) this;
        }

        public Criteria andOrderNumNotBetween(Integer value1, Integer value2) {
            addCriterion("order_num not between", value1, value2, "orderNum");
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