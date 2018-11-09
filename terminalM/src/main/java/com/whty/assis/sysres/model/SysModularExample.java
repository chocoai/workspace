package com.whty.assis.sysres.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SysModularExample implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * sys_modular
     */
    protected String orderByClause;

    /**
     * sys_modular
     */
    protected boolean distinct;

    /**
     * sys_modular
     */
    protected List<Criteria> oredCriteria;

    /**
     *
     * @mbg.generated 2018-06-04
     */
    public SysModularExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     *
     * @mbg.generated 2018-06-04
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     *
     * @mbg.generated 2018-06-04
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     *
     * @mbg.generated 2018-06-04
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     *
     * @mbg.generated 2018-06-04
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     *
     * @mbg.generated 2018-06-04
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     *
     * @mbg.generated 2018-06-04
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     *
     * @mbg.generated 2018-06-04
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     *
     * @mbg.generated 2018-06-04
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     *
     * @mbg.generated 2018-06-04
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     *
     * @mbg.generated 2018-06-04
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * sys_modular 2018-06-04
     */
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andModularNameIsNull() {
            addCriterion("modular_name is null");
            return (Criteria) this;
        }

        public Criteria andModularNameIsNotNull() {
            addCriterion("modular_name is not null");
            return (Criteria) this;
        }

        public Criteria andModularNameEqualTo(String value) {
            addCriterion("modular_name =", value, "modularName");
            return (Criteria) this;
        }

        public Criteria andModularNameNotEqualTo(String value) {
            addCriterion("modular_name <>", value, "modularName");
            return (Criteria) this;
        }

        public Criteria andModularNameGreaterThan(String value) {
            addCriterion("modular_name >", value, "modularName");
            return (Criteria) this;
        }

        public Criteria andModularNameGreaterThanOrEqualTo(String value) {
            addCriterion("modular_name >=", value, "modularName");
            return (Criteria) this;
        }

        public Criteria andModularNameLessThan(String value) {
            addCriterion("modular_name <", value, "modularName");
            return (Criteria) this;
        }

        public Criteria andModularNameLessThanOrEqualTo(String value) {
            addCriterion("modular_name <=", value, "modularName");
            return (Criteria) this;
        }

        public Criteria andModularNameLike(String value) {
            addCriterion("modular_name like", value, "modularName");
            return (Criteria) this;
        }

        public Criteria andModularNameNotLike(String value) {
            addCriterion("modular_name not like", value, "modularName");
            return (Criteria) this;
        }

        public Criteria andModularNameIn(List<String> values) {
            addCriterion("modular_name in", values, "modularName");
            return (Criteria) this;
        }

        public Criteria andModularNameNotIn(List<String> values) {
            addCriterion("modular_name not in", values, "modularName");
            return (Criteria) this;
        }

        public Criteria andModularNameBetween(String value1, String value2) {
            addCriterion("modular_name between", value1, value2, "modularName");
            return (Criteria) this;
        }

        public Criteria andModularNameNotBetween(String value1, String value2) {
            addCriterion("modular_name not between", value1, value2, "modularName");
            return (Criteria) this;
        }

        public Criteria andModularPathIsNull() {
            addCriterion("modular_path is null");
            return (Criteria) this;
        }

        public Criteria andModularPathIsNotNull() {
            addCriterion("modular_path is not null");
            return (Criteria) this;
        }

        public Criteria andModularPathEqualTo(String value) {
            addCriterion("modular_path =", value, "modularPath");
            return (Criteria) this;
        }

        public Criteria andModularPathNotEqualTo(String value) {
            addCriterion("modular_path <>", value, "modularPath");
            return (Criteria) this;
        }

        public Criteria andModularPathGreaterThan(String value) {
            addCriterion("modular_path >", value, "modularPath");
            return (Criteria) this;
        }

        public Criteria andModularPathGreaterThanOrEqualTo(String value) {
            addCriterion("modular_path >=", value, "modularPath");
            return (Criteria) this;
        }

        public Criteria andModularPathLessThan(String value) {
            addCriterion("modular_path <", value, "modularPath");
            return (Criteria) this;
        }

        public Criteria andModularPathLessThanOrEqualTo(String value) {
            addCriterion("modular_path <=", value, "modularPath");
            return (Criteria) this;
        }

        public Criteria andModularPathLike(String value) {
            addCriterion("modular_path like", value, "modularPath");
            return (Criteria) this;
        }

        public Criteria andModularPathNotLike(String value) {
            addCriterion("modular_path not like", value, "modularPath");
            return (Criteria) this;
        }

        public Criteria andModularPathIn(List<String> values) {
            addCriterion("modular_path in", values, "modularPath");
            return (Criteria) this;
        }

        public Criteria andModularPathNotIn(List<String> values) {
            addCriterion("modular_path not in", values, "modularPath");
            return (Criteria) this;
        }

        public Criteria andModularPathBetween(String value1, String value2) {
            addCriterion("modular_path between", value1, value2, "modularPath");
            return (Criteria) this;
        }

        public Criteria andModularPathNotBetween(String value1, String value2) {
            addCriterion("modular_path not between", value1, value2, "modularPath");
            return (Criteria) this;
        }

        public Criteria andModularSortIsNull() {
            addCriterion("modular_sort is null");
            return (Criteria) this;
        }

        public Criteria andModularSortIsNotNull() {
            addCriterion("modular_sort is not null");
            return (Criteria) this;
        }

        public Criteria andModularSortEqualTo(Integer value) {
            addCriterion("modular_sort =", value, "modularSort");
            return (Criteria) this;
        }

        public Criteria andModularSortNotEqualTo(Integer value) {
            addCriterion("modular_sort <>", value, "modularSort");
            return (Criteria) this;
        }

        public Criteria andModularSortGreaterThan(Integer value) {
            addCriterion("modular_sort >", value, "modularSort");
            return (Criteria) this;
        }

        public Criteria andModularSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("modular_sort >=", value, "modularSort");
            return (Criteria) this;
        }

        public Criteria andModularSortLessThan(Integer value) {
            addCriterion("modular_sort <", value, "modularSort");
            return (Criteria) this;
        }

        public Criteria andModularSortLessThanOrEqualTo(Integer value) {
            addCriterion("modular_sort <=", value, "modularSort");
            return (Criteria) this;
        }

        public Criteria andModularSortIn(List<Integer> values) {
            addCriterion("modular_sort in", values, "modularSort");
            return (Criteria) this;
        }

        public Criteria andModularSortNotIn(List<Integer> values) {
            addCriterion("modular_sort not in", values, "modularSort");
            return (Criteria) this;
        }

        public Criteria andModularSortBetween(Integer value1, Integer value2) {
            addCriterion("modular_sort between", value1, value2, "modularSort");
            return (Criteria) this;
        }

        public Criteria andModularSortNotBetween(Integer value1, Integer value2) {
            addCriterion("modular_sort not between", value1, value2, "modularSort");
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

        public Criteria andIsParentIsNull() {
            addCriterion("is_parent is null");
            return (Criteria) this;
        }

        public Criteria andIsParentIsNotNull() {
            addCriterion("is_parent is not null");
            return (Criteria) this;
        }

        public Criteria andIsParentEqualTo(Integer value) {
            addCriterion("is_parent =", value, "isParent");
            return (Criteria) this;
        }

        public Criteria andIsParentNotEqualTo(Integer value) {
            addCriterion("is_parent <>", value, "isParent");
            return (Criteria) this;
        }

        public Criteria andIsParentGreaterThan(Integer value) {
            addCriterion("is_parent >", value, "isParent");
            return (Criteria) this;
        }

        public Criteria andIsParentGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_parent >=", value, "isParent");
            return (Criteria) this;
        }

        public Criteria andIsParentLessThan(Integer value) {
            addCriterion("is_parent <", value, "isParent");
            return (Criteria) this;
        }

        public Criteria andIsParentLessThanOrEqualTo(Integer value) {
            addCriterion("is_parent <=", value, "isParent");
            return (Criteria) this;
        }

        public Criteria andIsParentIn(List<Integer> values) {
            addCriterion("is_parent in", values, "isParent");
            return (Criteria) this;
        }

        public Criteria andIsParentNotIn(List<Integer> values) {
            addCriterion("is_parent not in", values, "isParent");
            return (Criteria) this;
        }

        public Criteria andIsParentBetween(Integer value1, Integer value2) {
            addCriterion("is_parent between", value1, value2, "isParent");
            return (Criteria) this;
        }

        public Criteria andIsParentNotBetween(Integer value1, Integer value2) {
            addCriterion("is_parent not between", value1, value2, "isParent");
            return (Criteria) this;
        }

        public Criteria andButtonsIsNull() {
            addCriterion("buttons is null");
            return (Criteria) this;
        }

        public Criteria andButtonsIsNotNull() {
            addCriterion("buttons is not null");
            return (Criteria) this;
        }

        public Criteria andButtonsEqualTo(String value) {
            addCriterion("buttons =", value, "buttons");
            return (Criteria) this;
        }

        public Criteria andButtonsNotEqualTo(String value) {
            addCriterion("buttons <>", value, "buttons");
            return (Criteria) this;
        }

        public Criteria andButtonsGreaterThan(String value) {
            addCriterion("buttons >", value, "buttons");
            return (Criteria) this;
        }

        public Criteria andButtonsGreaterThanOrEqualTo(String value) {
            addCriterion("buttons >=", value, "buttons");
            return (Criteria) this;
        }

        public Criteria andButtonsLessThan(String value) {
            addCriterion("buttons <", value, "buttons");
            return (Criteria) this;
        }

        public Criteria andButtonsLessThanOrEqualTo(String value) {
            addCriterion("buttons <=", value, "buttons");
            return (Criteria) this;
        }

        public Criteria andButtonsLike(String value) {
            addCriterion("buttons like", value, "buttons");
            return (Criteria) this;
        }

        public Criteria andButtonsNotLike(String value) {
            addCriterion("buttons not like", value, "buttons");
            return (Criteria) this;
        }

        public Criteria andButtonsIn(List<String> values) {
            addCriterion("buttons in", values, "buttons");
            return (Criteria) this;
        }

        public Criteria andButtonsNotIn(List<String> values) {
            addCriterion("buttons not in", values, "buttons");
            return (Criteria) this;
        }

        public Criteria andButtonsBetween(String value1, String value2) {
            addCriterion("buttons between", value1, value2, "buttons");
            return (Criteria) this;
        }

        public Criteria andButtonsNotBetween(String value1, String value2) {
            addCriterion("buttons not between", value1, value2, "buttons");
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

        public Criteria andImgcssIsNull() {
            addCriterion("imgcss is null");
            return (Criteria) this;
        }

        public Criteria andImgcssIsNotNull() {
            addCriterion("imgcss is not null");
            return (Criteria) this;
        }

        public Criteria andImgcssEqualTo(String value) {
            addCriterion("imgcss =", value, "imgcss");
            return (Criteria) this;
        }

        public Criteria andImgcssNotEqualTo(String value) {
            addCriterion("imgcss <>", value, "imgcss");
            return (Criteria) this;
        }

        public Criteria andImgcssGreaterThan(String value) {
            addCriterion("imgcss >", value, "imgcss");
            return (Criteria) this;
        }

        public Criteria andImgcssGreaterThanOrEqualTo(String value) {
            addCriterion("imgcss >=", value, "imgcss");
            return (Criteria) this;
        }

        public Criteria andImgcssLessThan(String value) {
            addCriterion("imgcss <", value, "imgcss");
            return (Criteria) this;
        }

        public Criteria andImgcssLessThanOrEqualTo(String value) {
            addCriterion("imgcss <=", value, "imgcss");
            return (Criteria) this;
        }

        public Criteria andImgcssLike(String value) {
            addCriterion("imgcss like", value, "imgcss");
            return (Criteria) this;
        }

        public Criteria andImgcssNotLike(String value) {
            addCriterion("imgcss not like", value, "imgcss");
            return (Criteria) this;
        }

        public Criteria andImgcssIn(List<String> values) {
            addCriterion("imgcss in", values, "imgcss");
            return (Criteria) this;
        }

        public Criteria andImgcssNotIn(List<String> values) {
            addCriterion("imgcss not in", values, "imgcss");
            return (Criteria) this;
        }

        public Criteria andImgcssBetween(String value1, String value2) {
            addCriterion("imgcss between", value1, value2, "imgcss");
            return (Criteria) this;
        }

        public Criteria andImgcssNotBetween(String value1, String value2) {
            addCriterion("imgcss not between", value1, value2, "imgcss");
            return (Criteria) this;
        }
    }

    /**
     * sys_modular
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * sys_modular 2018-06-04
     */
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