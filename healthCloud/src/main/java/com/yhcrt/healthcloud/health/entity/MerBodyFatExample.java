package com.yhcrt.healthcloud.health.entity;

import java.util.ArrayList;
import java.util.List;

public class MerBodyFatExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MerBodyFatExample() {
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

        public Criteria andMerIdIsNull() {
            addCriterion("mer_id is null");
            return (Criteria) this;
        }

        public Criteria andMerIdIsNotNull() {
            addCriterion("mer_id is not null");
            return (Criteria) this;
        }

        public Criteria andMerIdEqualTo(String value) {
            addCriterion("mer_id =", value, "merId");
            return (Criteria) this;
        }

        public Criteria andMerIdNotEqualTo(String value) {
            addCriterion("mer_id <>", value, "merId");
            return (Criteria) this;
        }

        public Criteria andMerIdGreaterThan(String value) {
            addCriterion("mer_id >", value, "merId");
            return (Criteria) this;
        }

        public Criteria andMerIdGreaterThanOrEqualTo(String value) {
            addCriterion("mer_id >=", value, "merId");
            return (Criteria) this;
        }

        public Criteria andMerIdLessThan(String value) {
            addCriterion("mer_id <", value, "merId");
            return (Criteria) this;
        }

        public Criteria andMerIdLessThanOrEqualTo(String value) {
            addCriterion("mer_id <=", value, "merId");
            return (Criteria) this;
        }

        public Criteria andMerIdLike(String value) {
            addCriterion("mer_id like", value, "merId");
            return (Criteria) this;
        }

        public Criteria andMerIdNotLike(String value) {
            addCriterion("mer_id not like", value, "merId");
            return (Criteria) this;
        }

        public Criteria andMerIdIn(List<String> values) {
            addCriterion("mer_id in", values, "merId");
            return (Criteria) this;
        }

        public Criteria andMerIdNotIn(List<String> values) {
            addCriterion("mer_id not in", values, "merId");
            return (Criteria) this;
        }

        public Criteria andMerIdBetween(String value1, String value2) {
            addCriterion("mer_id between", value1, value2, "merId");
            return (Criteria) this;
        }

        public Criteria andMerIdNotBetween(String value1, String value2) {
            addCriterion("mer_id not between", value1, value2, "merId");
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

        public Criteria andBfpIsNull() {
            addCriterion("bfp is null");
            return (Criteria) this;
        }

        public Criteria andBfpIsNotNull() {
            addCriterion("bfp is not null");
            return (Criteria) this;
        }

        public Criteria andBfpEqualTo(Double value) {
            addCriterion("bfp =", value, "bfp");
            return (Criteria) this;
        }

        public Criteria andBfpNotEqualTo(Double value) {
            addCriterion("bfp <>", value, "bfp");
            return (Criteria) this;
        }

        public Criteria andBfpGreaterThan(Double value) {
            addCriterion("bfp >", value, "bfp");
            return (Criteria) this;
        }

        public Criteria andBfpGreaterThanOrEqualTo(Double value) {
            addCriterion("bfp >=", value, "bfp");
            return (Criteria) this;
        }

        public Criteria andBfpLessThan(Double value) {
            addCriterion("bfp <", value, "bfp");
            return (Criteria) this;
        }

        public Criteria andBfpLessThanOrEqualTo(Double value) {
            addCriterion("bfp <=", value, "bfp");
            return (Criteria) this;
        }

        public Criteria andBfpIn(List<Double> values) {
            addCriterion("bfp in", values, "bfp");
            return (Criteria) this;
        }

        public Criteria andBfpNotIn(List<Double> values) {
            addCriterion("bfp not in", values, "bfp");
            return (Criteria) this;
        }

        public Criteria andBfpBetween(Double value1, Double value2) {
            addCriterion("bfp between", value1, value2, "bfp");
            return (Criteria) this;
        }

        public Criteria andBfpNotBetween(Double value1, Double value2) {
            addCriterion("bfp not between", value1, value2, "bfp");
            return (Criteria) this;
        }

        public Criteria andBfIsNull() {
            addCriterion("bf is null");
            return (Criteria) this;
        }

        public Criteria andBfIsNotNull() {
            addCriterion("bf is not null");
            return (Criteria) this;
        }

        public Criteria andBfEqualTo(Double value) {
            addCriterion("bf =", value, "bf");
            return (Criteria) this;
        }

        public Criteria andBfNotEqualTo(Double value) {
            addCriterion("bf <>", value, "bf");
            return (Criteria) this;
        }

        public Criteria andBfGreaterThan(Double value) {
            addCriterion("bf >", value, "bf");
            return (Criteria) this;
        }

        public Criteria andBfGreaterThanOrEqualTo(Double value) {
            addCriterion("bf >=", value, "bf");
            return (Criteria) this;
        }

        public Criteria andBfLessThan(Double value) {
            addCriterion("bf <", value, "bf");
            return (Criteria) this;
        }

        public Criteria andBfLessThanOrEqualTo(Double value) {
            addCriterion("bf <=", value, "bf");
            return (Criteria) this;
        }

        public Criteria andBfIn(List<Double> values) {
            addCriterion("bf in", values, "bf");
            return (Criteria) this;
        }

        public Criteria andBfNotIn(List<Double> values) {
            addCriterion("bf not in", values, "bf");
            return (Criteria) this;
        }

        public Criteria andBfBetween(Double value1, Double value2) {
            addCriterion("bf between", value1, value2, "bf");
            return (Criteria) this;
        }

        public Criteria andBfNotBetween(Double value1, Double value2) {
            addCriterion("bf not between", value1, value2, "bf");
            return (Criteria) this;
        }

        public Criteria andNonFatIsNull() {
            addCriterion("non_fat is null");
            return (Criteria) this;
        }

        public Criteria andNonFatIsNotNull() {
            addCriterion("non_fat is not null");
            return (Criteria) this;
        }

        public Criteria andNonFatEqualTo(Double value) {
            addCriterion("non_fat =", value, "nonFat");
            return (Criteria) this;
        }

        public Criteria andNonFatNotEqualTo(Double value) {
            addCriterion("non_fat <>", value, "nonFat");
            return (Criteria) this;
        }

        public Criteria andNonFatGreaterThan(Double value) {
            addCriterion("non_fat >", value, "nonFat");
            return (Criteria) this;
        }

        public Criteria andNonFatGreaterThanOrEqualTo(Double value) {
            addCriterion("non_fat >=", value, "nonFat");
            return (Criteria) this;
        }

        public Criteria andNonFatLessThan(Double value) {
            addCriterion("non_fat <", value, "nonFat");
            return (Criteria) this;
        }

        public Criteria andNonFatLessThanOrEqualTo(Double value) {
            addCriterion("non_fat <=", value, "nonFat");
            return (Criteria) this;
        }

        public Criteria andNonFatIn(List<Double> values) {
            addCriterion("non_fat in", values, "nonFat");
            return (Criteria) this;
        }

        public Criteria andNonFatNotIn(List<Double> values) {
            addCriterion("non_fat not in", values, "nonFat");
            return (Criteria) this;
        }

        public Criteria andNonFatBetween(Double value1, Double value2) {
            addCriterion("non_fat between", value1, value2, "nonFat");
            return (Criteria) this;
        }

        public Criteria andNonFatNotBetween(Double value1, Double value2) {
            addCriterion("non_fat not between", value1, value2, "nonFat");
            return (Criteria) this;
        }

        public Criteria andBwpIsNull() {
            addCriterion("bwp is null");
            return (Criteria) this;
        }

        public Criteria andBwpIsNotNull() {
            addCriterion("bwp is not null");
            return (Criteria) this;
        }

        public Criteria andBwpEqualTo(Double value) {
            addCriterion("bwp =", value, "bwp");
            return (Criteria) this;
        }

        public Criteria andBwpNotEqualTo(Double value) {
            addCriterion("bwp <>", value, "bwp");
            return (Criteria) this;
        }

        public Criteria andBwpGreaterThan(Double value) {
            addCriterion("bwp >", value, "bwp");
            return (Criteria) this;
        }

        public Criteria andBwpGreaterThanOrEqualTo(Double value) {
            addCriterion("bwp >=", value, "bwp");
            return (Criteria) this;
        }

        public Criteria andBwpLessThan(Double value) {
            addCriterion("bwp <", value, "bwp");
            return (Criteria) this;
        }

        public Criteria andBwpLessThanOrEqualTo(Double value) {
            addCriterion("bwp <=", value, "bwp");
            return (Criteria) this;
        }

        public Criteria andBwpIn(List<Double> values) {
            addCriterion("bwp in", values, "bwp");
            return (Criteria) this;
        }

        public Criteria andBwpNotIn(List<Double> values) {
            addCriterion("bwp not in", values, "bwp");
            return (Criteria) this;
        }

        public Criteria andBwpBetween(Double value1, Double value2) {
            addCriterion("bwp between", value1, value2, "bwp");
            return (Criteria) this;
        }

        public Criteria andBwpNotBetween(Double value1, Double value2) {
            addCriterion("bwp not between", value1, value2, "bwp");
            return (Criteria) this;
        }

        public Criteria andBwIsNull() {
            addCriterion("bw is null");
            return (Criteria) this;
        }

        public Criteria andBwIsNotNull() {
            addCriterion("bw is not null");
            return (Criteria) this;
        }

        public Criteria andBwEqualTo(Double value) {
            addCriterion("bw =", value, "bw");
            return (Criteria) this;
        }

        public Criteria andBwNotEqualTo(Double value) {
            addCriterion("bw <>", value, "bw");
            return (Criteria) this;
        }

        public Criteria andBwGreaterThan(Double value) {
            addCriterion("bw >", value, "bw");
            return (Criteria) this;
        }

        public Criteria andBwGreaterThanOrEqualTo(Double value) {
            addCriterion("bw >=", value, "bw");
            return (Criteria) this;
        }

        public Criteria andBwLessThan(Double value) {
            addCriterion("bw <", value, "bw");
            return (Criteria) this;
        }

        public Criteria andBwLessThanOrEqualTo(Double value) {
            addCriterion("bw <=", value, "bw");
            return (Criteria) this;
        }

        public Criteria andBwIn(List<Double> values) {
            addCriterion("bw in", values, "bw");
            return (Criteria) this;
        }

        public Criteria andBwNotIn(List<Double> values) {
            addCriterion("bw not in", values, "bw");
            return (Criteria) this;
        }

        public Criteria andBwBetween(Double value1, Double value2) {
            addCriterion("bw between", value1, value2, "bw");
            return (Criteria) this;
        }

        public Criteria andBwNotBetween(Double value1, Double value2) {
            addCriterion("bw not between", value1, value2, "bw");
            return (Criteria) this;
        }

        public Criteria andMineralIsNull() {
            addCriterion("mineral is null");
            return (Criteria) this;
        }

        public Criteria andMineralIsNotNull() {
            addCriterion("mineral is not null");
            return (Criteria) this;
        }

        public Criteria andMineralEqualTo(Double value) {
            addCriterion("mineral =", value, "mineral");
            return (Criteria) this;
        }

        public Criteria andMineralNotEqualTo(Double value) {
            addCriterion("mineral <>", value, "mineral");
            return (Criteria) this;
        }

        public Criteria andMineralGreaterThan(Double value) {
            addCriterion("mineral >", value, "mineral");
            return (Criteria) this;
        }

        public Criteria andMineralGreaterThanOrEqualTo(Double value) {
            addCriterion("mineral >=", value, "mineral");
            return (Criteria) this;
        }

        public Criteria andMineralLessThan(Double value) {
            addCriterion("mineral <", value, "mineral");
            return (Criteria) this;
        }

        public Criteria andMineralLessThanOrEqualTo(Double value) {
            addCriterion("mineral <=", value, "mineral");
            return (Criteria) this;
        }

        public Criteria andMineralIn(List<Double> values) {
            addCriterion("mineral in", values, "mineral");
            return (Criteria) this;
        }

        public Criteria andMineralNotIn(List<Double> values) {
            addCriterion("mineral not in", values, "mineral");
            return (Criteria) this;
        }

        public Criteria andMineralBetween(Double value1, Double value2) {
            addCriterion("mineral between", value1, value2, "mineral");
            return (Criteria) this;
        }

        public Criteria andMineralNotBetween(Double value1, Double value2) {
            addCriterion("mineral not between", value1, value2, "mineral");
            return (Criteria) this;
        }

        public Criteria andProteinIsNull() {
            addCriterion("protein is null");
            return (Criteria) this;
        }

        public Criteria andProteinIsNotNull() {
            addCriterion("protein is not null");
            return (Criteria) this;
        }

        public Criteria andProteinEqualTo(Double value) {
            addCriterion("protein =", value, "protein");
            return (Criteria) this;
        }

        public Criteria andProteinNotEqualTo(Double value) {
            addCriterion("protein <>", value, "protein");
            return (Criteria) this;
        }

        public Criteria andProteinGreaterThan(Double value) {
            addCriterion("protein >", value, "protein");
            return (Criteria) this;
        }

        public Criteria andProteinGreaterThanOrEqualTo(Double value) {
            addCriterion("protein >=", value, "protein");
            return (Criteria) this;
        }

        public Criteria andProteinLessThan(Double value) {
            addCriterion("protein <", value, "protein");
            return (Criteria) this;
        }

        public Criteria andProteinLessThanOrEqualTo(Double value) {
            addCriterion("protein <=", value, "protein");
            return (Criteria) this;
        }

        public Criteria andProteinIn(List<Double> values) {
            addCriterion("protein in", values, "protein");
            return (Criteria) this;
        }

        public Criteria andProteinNotIn(List<Double> values) {
            addCriterion("protein not in", values, "protein");
            return (Criteria) this;
        }

        public Criteria andProteinBetween(Double value1, Double value2) {
            addCriterion("protein between", value1, value2, "protein");
            return (Criteria) this;
        }

        public Criteria andProteinNotBetween(Double value1, Double value2) {
            addCriterion("protein not between", value1, value2, "protein");
            return (Criteria) this;
        }

        public Criteria andIcwIsNull() {
            addCriterion("icw is null");
            return (Criteria) this;
        }

        public Criteria andIcwIsNotNull() {
            addCriterion("icw is not null");
            return (Criteria) this;
        }

        public Criteria andIcwEqualTo(Double value) {
            addCriterion("icw =", value, "icw");
            return (Criteria) this;
        }

        public Criteria andIcwNotEqualTo(Double value) {
            addCriterion("icw <>", value, "icw");
            return (Criteria) this;
        }

        public Criteria andIcwGreaterThan(Double value) {
            addCriterion("icw >", value, "icw");
            return (Criteria) this;
        }

        public Criteria andIcwGreaterThanOrEqualTo(Double value) {
            addCriterion("icw >=", value, "icw");
            return (Criteria) this;
        }

        public Criteria andIcwLessThan(Double value) {
            addCriterion("icw <", value, "icw");
            return (Criteria) this;
        }

        public Criteria andIcwLessThanOrEqualTo(Double value) {
            addCriterion("icw <=", value, "icw");
            return (Criteria) this;
        }

        public Criteria andIcwIn(List<Double> values) {
            addCriterion("icw in", values, "icw");
            return (Criteria) this;
        }

        public Criteria andIcwNotIn(List<Double> values) {
            addCriterion("icw not in", values, "icw");
            return (Criteria) this;
        }

        public Criteria andIcwBetween(Double value1, Double value2) {
            addCriterion("icw between", value1, value2, "icw");
            return (Criteria) this;
        }

        public Criteria andIcwNotBetween(Double value1, Double value2) {
            addCriterion("icw not between", value1, value2, "icw");
            return (Criteria) this;
        }

        public Criteria andEcwIsNull() {
            addCriterion("ecw is null");
            return (Criteria) this;
        }

        public Criteria andEcwIsNotNull() {
            addCriterion("ecw is not null");
            return (Criteria) this;
        }

        public Criteria andEcwEqualTo(Double value) {
            addCriterion("ecw =", value, "ecw");
            return (Criteria) this;
        }

        public Criteria andEcwNotEqualTo(Double value) {
            addCriterion("ecw <>", value, "ecw");
            return (Criteria) this;
        }

        public Criteria andEcwGreaterThan(Double value) {
            addCriterion("ecw >", value, "ecw");
            return (Criteria) this;
        }

        public Criteria andEcwGreaterThanOrEqualTo(Double value) {
            addCriterion("ecw >=", value, "ecw");
            return (Criteria) this;
        }

        public Criteria andEcwLessThan(Double value) {
            addCriterion("ecw <", value, "ecw");
            return (Criteria) this;
        }

        public Criteria andEcwLessThanOrEqualTo(Double value) {
            addCriterion("ecw <=", value, "ecw");
            return (Criteria) this;
        }

        public Criteria andEcwIn(List<Double> values) {
            addCriterion("ecw in", values, "ecw");
            return (Criteria) this;
        }

        public Criteria andEcwNotIn(List<Double> values) {
            addCriterion("ecw not in", values, "ecw");
            return (Criteria) this;
        }

        public Criteria andEcwBetween(Double value1, Double value2) {
            addCriterion("ecw between", value1, value2, "ecw");
            return (Criteria) this;
        }

        public Criteria andEcwNotBetween(Double value1, Double value2) {
            addCriterion("ecw not between", value1, value2, "ecw");
            return (Criteria) this;
        }

        public Criteria andMuscleMassIsNull() {
            addCriterion("muscle_mass is null");
            return (Criteria) this;
        }

        public Criteria andMuscleMassIsNotNull() {
            addCriterion("muscle_mass is not null");
            return (Criteria) this;
        }

        public Criteria andMuscleMassEqualTo(Double value) {
            addCriterion("muscle_mass =", value, "muscleMass");
            return (Criteria) this;
        }

        public Criteria andMuscleMassNotEqualTo(Double value) {
            addCriterion("muscle_mass <>", value, "muscleMass");
            return (Criteria) this;
        }

        public Criteria andMuscleMassGreaterThan(Double value) {
            addCriterion("muscle_mass >", value, "muscleMass");
            return (Criteria) this;
        }

        public Criteria andMuscleMassGreaterThanOrEqualTo(Double value) {
            addCriterion("muscle_mass >=", value, "muscleMass");
            return (Criteria) this;
        }

        public Criteria andMuscleMassLessThan(Double value) {
            addCriterion("muscle_mass <", value, "muscleMass");
            return (Criteria) this;
        }

        public Criteria andMuscleMassLessThanOrEqualTo(Double value) {
            addCriterion("muscle_mass <=", value, "muscleMass");
            return (Criteria) this;
        }

        public Criteria andMuscleMassIn(List<Double> values) {
            addCriterion("muscle_mass in", values, "muscleMass");
            return (Criteria) this;
        }

        public Criteria andMuscleMassNotIn(List<Double> values) {
            addCriterion("muscle_mass not in", values, "muscleMass");
            return (Criteria) this;
        }

        public Criteria andMuscleMassBetween(Double value1, Double value2) {
            addCriterion("muscle_mass between", value1, value2, "muscleMass");
            return (Criteria) this;
        }

        public Criteria andMuscleMassNotBetween(Double value1, Double value2) {
            addCriterion("muscle_mass not between", value1, value2, "muscleMass");
            return (Criteria) this;
        }

        public Criteria andFatRegulationIsNull() {
            addCriterion("fat_regulation is null");
            return (Criteria) this;
        }

        public Criteria andFatRegulationIsNotNull() {
            addCriterion("fat_regulation is not null");
            return (Criteria) this;
        }

        public Criteria andFatRegulationEqualTo(Double value) {
            addCriterion("fat_regulation =", value, "fatRegulation");
            return (Criteria) this;
        }

        public Criteria andFatRegulationNotEqualTo(Double value) {
            addCriterion("fat_regulation <>", value, "fatRegulation");
            return (Criteria) this;
        }

        public Criteria andFatRegulationGreaterThan(Double value) {
            addCriterion("fat_regulation >", value, "fatRegulation");
            return (Criteria) this;
        }

        public Criteria andFatRegulationGreaterThanOrEqualTo(Double value) {
            addCriterion("fat_regulation >=", value, "fatRegulation");
            return (Criteria) this;
        }

        public Criteria andFatRegulationLessThan(Double value) {
            addCriterion("fat_regulation <", value, "fatRegulation");
            return (Criteria) this;
        }

        public Criteria andFatRegulationLessThanOrEqualTo(Double value) {
            addCriterion("fat_regulation <=", value, "fatRegulation");
            return (Criteria) this;
        }

        public Criteria andFatRegulationIn(List<Double> values) {
            addCriterion("fat_regulation in", values, "fatRegulation");
            return (Criteria) this;
        }

        public Criteria andFatRegulationNotIn(List<Double> values) {
            addCriterion("fat_regulation not in", values, "fatRegulation");
            return (Criteria) this;
        }

        public Criteria andFatRegulationBetween(Double value1, Double value2) {
            addCriterion("fat_regulation between", value1, value2, "fatRegulation");
            return (Criteria) this;
        }

        public Criteria andFatRegulationNotBetween(Double value1, Double value2) {
            addCriterion("fat_regulation not between", value1, value2, "fatRegulation");
            return (Criteria) this;
        }

        public Criteria andWeightRegulationIsNull() {
            addCriterion("weight_regulation is null");
            return (Criteria) this;
        }

        public Criteria andWeightRegulationIsNotNull() {
            addCriterion("weight_regulation is not null");
            return (Criteria) this;
        }

        public Criteria andWeightRegulationEqualTo(Double value) {
            addCriterion("weight_regulation =", value, "weightRegulation");
            return (Criteria) this;
        }

        public Criteria andWeightRegulationNotEqualTo(Double value) {
            addCriterion("weight_regulation <>", value, "weightRegulation");
            return (Criteria) this;
        }

        public Criteria andWeightRegulationGreaterThan(Double value) {
            addCriterion("weight_regulation >", value, "weightRegulation");
            return (Criteria) this;
        }

        public Criteria andWeightRegulationGreaterThanOrEqualTo(Double value) {
            addCriterion("weight_regulation >=", value, "weightRegulation");
            return (Criteria) this;
        }

        public Criteria andWeightRegulationLessThan(Double value) {
            addCriterion("weight_regulation <", value, "weightRegulation");
            return (Criteria) this;
        }

        public Criteria andWeightRegulationLessThanOrEqualTo(Double value) {
            addCriterion("weight_regulation <=", value, "weightRegulation");
            return (Criteria) this;
        }

        public Criteria andWeightRegulationIn(List<Double> values) {
            addCriterion("weight_regulation in", values, "weightRegulation");
            return (Criteria) this;
        }

        public Criteria andWeightRegulationNotIn(List<Double> values) {
            addCriterion("weight_regulation not in", values, "weightRegulation");
            return (Criteria) this;
        }

        public Criteria andWeightRegulationBetween(Double value1, Double value2) {
            addCriterion("weight_regulation between", value1, value2, "weightRegulation");
            return (Criteria) this;
        }

        public Criteria andWeightRegulationNotBetween(Double value1, Double value2) {
            addCriterion("weight_regulation not between", value1, value2, "weightRegulation");
            return (Criteria) this;
        }

        public Criteria andMuscleRegulationIsNull() {
            addCriterion("muscle_regulation is null");
            return (Criteria) this;
        }

        public Criteria andMuscleRegulationIsNotNull() {
            addCriterion("muscle_regulation is not null");
            return (Criteria) this;
        }

        public Criteria andMuscleRegulationEqualTo(Double value) {
            addCriterion("muscle_regulation =", value, "muscleRegulation");
            return (Criteria) this;
        }

        public Criteria andMuscleRegulationNotEqualTo(Double value) {
            addCriterion("muscle_regulation <>", value, "muscleRegulation");
            return (Criteria) this;
        }

        public Criteria andMuscleRegulationGreaterThan(Double value) {
            addCriterion("muscle_regulation >", value, "muscleRegulation");
            return (Criteria) this;
        }

        public Criteria andMuscleRegulationGreaterThanOrEqualTo(Double value) {
            addCriterion("muscle_regulation >=", value, "muscleRegulation");
            return (Criteria) this;
        }

        public Criteria andMuscleRegulationLessThan(Double value) {
            addCriterion("muscle_regulation <", value, "muscleRegulation");
            return (Criteria) this;
        }

        public Criteria andMuscleRegulationLessThanOrEqualTo(Double value) {
            addCriterion("muscle_regulation <=", value, "muscleRegulation");
            return (Criteria) this;
        }

        public Criteria andMuscleRegulationIn(List<Double> values) {
            addCriterion("muscle_regulation in", values, "muscleRegulation");
            return (Criteria) this;
        }

        public Criteria andMuscleRegulationNotIn(List<Double> values) {
            addCriterion("muscle_regulation not in", values, "muscleRegulation");
            return (Criteria) this;
        }

        public Criteria andMuscleRegulationBetween(Double value1, Double value2) {
            addCriterion("muscle_regulation between", value1, value2, "muscleRegulation");
            return (Criteria) this;
        }

        public Criteria andMuscleRegulationNotBetween(Double value1, Double value2) {
            addCriterion("muscle_regulation not between", value1, value2, "muscleRegulation");
            return (Criteria) this;
        }

        public Criteria andBmrIsNull() {
            addCriterion("bmr is null");
            return (Criteria) this;
        }

        public Criteria andBmrIsNotNull() {
            addCriterion("bmr is not null");
            return (Criteria) this;
        }

        public Criteria andBmrEqualTo(Double value) {
            addCriterion("bmr =", value, "bmr");
            return (Criteria) this;
        }

        public Criteria andBmrNotEqualTo(Double value) {
            addCriterion("bmr <>", value, "bmr");
            return (Criteria) this;
        }

        public Criteria andBmrGreaterThan(Double value) {
            addCriterion("bmr >", value, "bmr");
            return (Criteria) this;
        }

        public Criteria andBmrGreaterThanOrEqualTo(Double value) {
            addCriterion("bmr >=", value, "bmr");
            return (Criteria) this;
        }

        public Criteria andBmrLessThan(Double value) {
            addCriterion("bmr <", value, "bmr");
            return (Criteria) this;
        }

        public Criteria andBmrLessThanOrEqualTo(Double value) {
            addCriterion("bmr <=", value, "bmr");
            return (Criteria) this;
        }

        public Criteria andBmrIn(List<Double> values) {
            addCriterion("bmr in", values, "bmr");
            return (Criteria) this;
        }

        public Criteria andBmrNotIn(List<Double> values) {
            addCriterion("bmr not in", values, "bmr");
            return (Criteria) this;
        }

        public Criteria andBmrBetween(Double value1, Double value2) {
            addCriterion("bmr between", value1, value2, "bmr");
            return (Criteria) this;
        }

        public Criteria andBmrNotBetween(Double value1, Double value2) {
            addCriterion("bmr not between", value1, value2, "bmr");
            return (Criteria) this;
        }

        public Criteria andVisceralFatLevelIsNull() {
            addCriterion("visceral_fat_level is null");
            return (Criteria) this;
        }

        public Criteria andVisceralFatLevelIsNotNull() {
            addCriterion("visceral_fat_level is not null");
            return (Criteria) this;
        }

        public Criteria andVisceralFatLevelEqualTo(Double value) {
            addCriterion("visceral_fat_level =", value, "visceralFatLevel");
            return (Criteria) this;
        }

        public Criteria andVisceralFatLevelNotEqualTo(Double value) {
            addCriterion("visceral_fat_level <>", value, "visceralFatLevel");
            return (Criteria) this;
        }

        public Criteria andVisceralFatLevelGreaterThan(Double value) {
            addCriterion("visceral_fat_level >", value, "visceralFatLevel");
            return (Criteria) this;
        }

        public Criteria andVisceralFatLevelGreaterThanOrEqualTo(Double value) {
            addCriterion("visceral_fat_level >=", value, "visceralFatLevel");
            return (Criteria) this;
        }

        public Criteria andVisceralFatLevelLessThan(Double value) {
            addCriterion("visceral_fat_level <", value, "visceralFatLevel");
            return (Criteria) this;
        }

        public Criteria andVisceralFatLevelLessThanOrEqualTo(Double value) {
            addCriterion("visceral_fat_level <=", value, "visceralFatLevel");
            return (Criteria) this;
        }

        public Criteria andVisceralFatLevelIn(List<Double> values) {
            addCriterion("visceral_fat_level in", values, "visceralFatLevel");
            return (Criteria) this;
        }

        public Criteria andVisceralFatLevelNotIn(List<Double> values) {
            addCriterion("visceral_fat_level not in", values, "visceralFatLevel");
            return (Criteria) this;
        }

        public Criteria andVisceralFatLevelBetween(Double value1, Double value2) {
            addCriterion("visceral_fat_level between", value1, value2, "visceralFatLevel");
            return (Criteria) this;
        }

        public Criteria andVisceralFatLevelNotBetween(Double value1, Double value2) {
            addCriterion("visceral_fat_level not between", value1, value2, "visceralFatLevel");
            return (Criteria) this;
        }

        public Criteria andSkeletalMassIsNull() {
            addCriterion("skeletal_mass is null");
            return (Criteria) this;
        }

        public Criteria andSkeletalMassIsNotNull() {
            addCriterion("skeletal_mass is not null");
            return (Criteria) this;
        }

        public Criteria andSkeletalMassEqualTo(Double value) {
            addCriterion("skeletal_mass =", value, "skeletalMass");
            return (Criteria) this;
        }

        public Criteria andSkeletalMassNotEqualTo(Double value) {
            addCriterion("skeletal_mass <>", value, "skeletalMass");
            return (Criteria) this;
        }

        public Criteria andSkeletalMassGreaterThan(Double value) {
            addCriterion("skeletal_mass >", value, "skeletalMass");
            return (Criteria) this;
        }

        public Criteria andSkeletalMassGreaterThanOrEqualTo(Double value) {
            addCriterion("skeletal_mass >=", value, "skeletalMass");
            return (Criteria) this;
        }

        public Criteria andSkeletalMassLessThan(Double value) {
            addCriterion("skeletal_mass <", value, "skeletalMass");
            return (Criteria) this;
        }

        public Criteria andSkeletalMassLessThanOrEqualTo(Double value) {
            addCriterion("skeletal_mass <=", value, "skeletalMass");
            return (Criteria) this;
        }

        public Criteria andSkeletalMassIn(List<Double> values) {
            addCriterion("skeletal_mass in", values, "skeletalMass");
            return (Criteria) this;
        }

        public Criteria andSkeletalMassNotIn(List<Double> values) {
            addCriterion("skeletal_mass not in", values, "skeletalMass");
            return (Criteria) this;
        }

        public Criteria andSkeletalMassBetween(Double value1, Double value2) {
            addCriterion("skeletal_mass between", value1, value2, "skeletalMass");
            return (Criteria) this;
        }

        public Criteria andSkeletalMassNotBetween(Double value1, Double value2) {
            addCriterion("skeletal_mass not between", value1, value2, "skeletalMass");
            return (Criteria) this;
        }

        public Criteria andMusclePercentIsNull() {
            addCriterion("muscle_percent is null");
            return (Criteria) this;
        }

        public Criteria andMusclePercentIsNotNull() {
            addCriterion("muscle_percent is not null");
            return (Criteria) this;
        }

        public Criteria andMusclePercentEqualTo(Double value) {
            addCriterion("muscle_percent =", value, "musclePercent");
            return (Criteria) this;
        }

        public Criteria andMusclePercentNotEqualTo(Double value) {
            addCriterion("muscle_percent <>", value, "musclePercent");
            return (Criteria) this;
        }

        public Criteria andMusclePercentGreaterThan(Double value) {
            addCriterion("muscle_percent >", value, "musclePercent");
            return (Criteria) this;
        }

        public Criteria andMusclePercentGreaterThanOrEqualTo(Double value) {
            addCriterion("muscle_percent >=", value, "musclePercent");
            return (Criteria) this;
        }

        public Criteria andMusclePercentLessThan(Double value) {
            addCriterion("muscle_percent <", value, "musclePercent");
            return (Criteria) this;
        }

        public Criteria andMusclePercentLessThanOrEqualTo(Double value) {
            addCriterion("muscle_percent <=", value, "musclePercent");
            return (Criteria) this;
        }

        public Criteria andMusclePercentIn(List<Double> values) {
            addCriterion("muscle_percent in", values, "musclePercent");
            return (Criteria) this;
        }

        public Criteria andMusclePercentNotIn(List<Double> values) {
            addCriterion("muscle_percent not in", values, "musclePercent");
            return (Criteria) this;
        }

        public Criteria andMusclePercentBetween(Double value1, Double value2) {
            addCriterion("muscle_percent between", value1, value2, "musclePercent");
            return (Criteria) this;
        }

        public Criteria andMusclePercentNotBetween(Double value1, Double value2) {
            addCriterion("muscle_percent not between", value1, value2, "musclePercent");
            return (Criteria) this;
        }

        public Criteria andTrunkMuscleIsNull() {
            addCriterion("trunk_muscle is null");
            return (Criteria) this;
        }

        public Criteria andTrunkMuscleIsNotNull() {
            addCriterion("trunk_muscle is not null");
            return (Criteria) this;
        }

        public Criteria andTrunkMuscleEqualTo(Double value) {
            addCriterion("trunk_muscle =", value, "trunkMuscle");
            return (Criteria) this;
        }

        public Criteria andTrunkMuscleNotEqualTo(Double value) {
            addCriterion("trunk_muscle <>", value, "trunkMuscle");
            return (Criteria) this;
        }

        public Criteria andTrunkMuscleGreaterThan(Double value) {
            addCriterion("trunk_muscle >", value, "trunkMuscle");
            return (Criteria) this;
        }

        public Criteria andTrunkMuscleGreaterThanOrEqualTo(Double value) {
            addCriterion("trunk_muscle >=", value, "trunkMuscle");
            return (Criteria) this;
        }

        public Criteria andTrunkMuscleLessThan(Double value) {
            addCriterion("trunk_muscle <", value, "trunkMuscle");
            return (Criteria) this;
        }

        public Criteria andTrunkMuscleLessThanOrEqualTo(Double value) {
            addCriterion("trunk_muscle <=", value, "trunkMuscle");
            return (Criteria) this;
        }

        public Criteria andTrunkMuscleIn(List<Double> values) {
            addCriterion("trunk_muscle in", values, "trunkMuscle");
            return (Criteria) this;
        }

        public Criteria andTrunkMuscleNotIn(List<Double> values) {
            addCriterion("trunk_muscle not in", values, "trunkMuscle");
            return (Criteria) this;
        }

        public Criteria andTrunkMuscleBetween(Double value1, Double value2) {
            addCriterion("trunk_muscle between", value1, value2, "trunkMuscle");
            return (Criteria) this;
        }

        public Criteria andTrunkMuscleNotBetween(Double value1, Double value2) {
            addCriterion("trunk_muscle not between", value1, value2, "trunkMuscle");
            return (Criteria) this;
        }

        public Criteria andTrunkFatIsNull() {
            addCriterion("trunk_fat is null");
            return (Criteria) this;
        }

        public Criteria andTrunkFatIsNotNull() {
            addCriterion("trunk_fat is not null");
            return (Criteria) this;
        }

        public Criteria andTrunkFatEqualTo(Double value) {
            addCriterion("trunk_fat =", value, "trunkFat");
            return (Criteria) this;
        }

        public Criteria andTrunkFatNotEqualTo(Double value) {
            addCriterion("trunk_fat <>", value, "trunkFat");
            return (Criteria) this;
        }

        public Criteria andTrunkFatGreaterThan(Double value) {
            addCriterion("trunk_fat >", value, "trunkFat");
            return (Criteria) this;
        }

        public Criteria andTrunkFatGreaterThanOrEqualTo(Double value) {
            addCriterion("trunk_fat >=", value, "trunkFat");
            return (Criteria) this;
        }

        public Criteria andTrunkFatLessThan(Double value) {
            addCriterion("trunk_fat <", value, "trunkFat");
            return (Criteria) this;
        }

        public Criteria andTrunkFatLessThanOrEqualTo(Double value) {
            addCriterion("trunk_fat <=", value, "trunkFat");
            return (Criteria) this;
        }

        public Criteria andTrunkFatIn(List<Double> values) {
            addCriterion("trunk_fat in", values, "trunkFat");
            return (Criteria) this;
        }

        public Criteria andTrunkFatNotIn(List<Double> values) {
            addCriterion("trunk_fat not in", values, "trunkFat");
            return (Criteria) this;
        }

        public Criteria andTrunkFatBetween(Double value1, Double value2) {
            addCriterion("trunk_fat between", value1, value2, "trunkFat");
            return (Criteria) this;
        }

        public Criteria andTrunkFatNotBetween(Double value1, Double value2) {
            addCriterion("trunk_fat not between", value1, value2, "trunkFat");
            return (Criteria) this;
        }

        public Criteria andLeftArmMuscleIsNull() {
            addCriterion("left_arm_muscle is null");
            return (Criteria) this;
        }

        public Criteria andLeftArmMuscleIsNotNull() {
            addCriterion("left_arm_muscle is not null");
            return (Criteria) this;
        }

        public Criteria andLeftArmMuscleEqualTo(Double value) {
            addCriterion("left_arm_muscle =", value, "leftArmMuscle");
            return (Criteria) this;
        }

        public Criteria andLeftArmMuscleNotEqualTo(Double value) {
            addCriterion("left_arm_muscle <>", value, "leftArmMuscle");
            return (Criteria) this;
        }

        public Criteria andLeftArmMuscleGreaterThan(Double value) {
            addCriterion("left_arm_muscle >", value, "leftArmMuscle");
            return (Criteria) this;
        }

        public Criteria andLeftArmMuscleGreaterThanOrEqualTo(Double value) {
            addCriterion("left_arm_muscle >=", value, "leftArmMuscle");
            return (Criteria) this;
        }

        public Criteria andLeftArmMuscleLessThan(Double value) {
            addCriterion("left_arm_muscle <", value, "leftArmMuscle");
            return (Criteria) this;
        }

        public Criteria andLeftArmMuscleLessThanOrEqualTo(Double value) {
            addCriterion("left_arm_muscle <=", value, "leftArmMuscle");
            return (Criteria) this;
        }

        public Criteria andLeftArmMuscleIn(List<Double> values) {
            addCriterion("left_arm_muscle in", values, "leftArmMuscle");
            return (Criteria) this;
        }

        public Criteria andLeftArmMuscleNotIn(List<Double> values) {
            addCriterion("left_arm_muscle not in", values, "leftArmMuscle");
            return (Criteria) this;
        }

        public Criteria andLeftArmMuscleBetween(Double value1, Double value2) {
            addCriterion("left_arm_muscle between", value1, value2, "leftArmMuscle");
            return (Criteria) this;
        }

        public Criteria andLeftArmMuscleNotBetween(Double value1, Double value2) {
            addCriterion("left_arm_muscle not between", value1, value2, "leftArmMuscle");
            return (Criteria) this;
        }

        public Criteria andRightArmMuscleIsNull() {
            addCriterion("right_arm_muscle is null");
            return (Criteria) this;
        }

        public Criteria andRightArmMuscleIsNotNull() {
            addCriterion("right_arm_muscle is not null");
            return (Criteria) this;
        }

        public Criteria andRightArmMuscleEqualTo(Double value) {
            addCriterion("right_arm_muscle =", value, "rightArmMuscle");
            return (Criteria) this;
        }

        public Criteria andRightArmMuscleNotEqualTo(Double value) {
            addCriterion("right_arm_muscle <>", value, "rightArmMuscle");
            return (Criteria) this;
        }

        public Criteria andRightArmMuscleGreaterThan(Double value) {
            addCriterion("right_arm_muscle >", value, "rightArmMuscle");
            return (Criteria) this;
        }

        public Criteria andRightArmMuscleGreaterThanOrEqualTo(Double value) {
            addCriterion("right_arm_muscle >=", value, "rightArmMuscle");
            return (Criteria) this;
        }

        public Criteria andRightArmMuscleLessThan(Double value) {
            addCriterion("right_arm_muscle <", value, "rightArmMuscle");
            return (Criteria) this;
        }

        public Criteria andRightArmMuscleLessThanOrEqualTo(Double value) {
            addCriterion("right_arm_muscle <=", value, "rightArmMuscle");
            return (Criteria) this;
        }

        public Criteria andRightArmMuscleIn(List<Double> values) {
            addCriterion("right_arm_muscle in", values, "rightArmMuscle");
            return (Criteria) this;
        }

        public Criteria andRightArmMuscleNotIn(List<Double> values) {
            addCriterion("right_arm_muscle not in", values, "rightArmMuscle");
            return (Criteria) this;
        }

        public Criteria andRightArmMuscleBetween(Double value1, Double value2) {
            addCriterion("right_arm_muscle between", value1, value2, "rightArmMuscle");
            return (Criteria) this;
        }

        public Criteria andRightArmMuscleNotBetween(Double value1, Double value2) {
            addCriterion("right_arm_muscle not between", value1, value2, "rightArmMuscle");
            return (Criteria) this;
        }

        public Criteria andLeftLegMuscleIsNull() {
            addCriterion("left_leg_muscle is null");
            return (Criteria) this;
        }

        public Criteria andLeftLegMuscleIsNotNull() {
            addCriterion("left_leg_muscle is not null");
            return (Criteria) this;
        }

        public Criteria andLeftLegMuscleEqualTo(Double value) {
            addCriterion("left_leg_muscle =", value, "leftLegMuscle");
            return (Criteria) this;
        }

        public Criteria andLeftLegMuscleNotEqualTo(Double value) {
            addCriterion("left_leg_muscle <>", value, "leftLegMuscle");
            return (Criteria) this;
        }

        public Criteria andLeftLegMuscleGreaterThan(Double value) {
            addCriterion("left_leg_muscle >", value, "leftLegMuscle");
            return (Criteria) this;
        }

        public Criteria andLeftLegMuscleGreaterThanOrEqualTo(Double value) {
            addCriterion("left_leg_muscle >=", value, "leftLegMuscle");
            return (Criteria) this;
        }

        public Criteria andLeftLegMuscleLessThan(Double value) {
            addCriterion("left_leg_muscle <", value, "leftLegMuscle");
            return (Criteria) this;
        }

        public Criteria andLeftLegMuscleLessThanOrEqualTo(Double value) {
            addCriterion("left_leg_muscle <=", value, "leftLegMuscle");
            return (Criteria) this;
        }

        public Criteria andLeftLegMuscleIn(List<Double> values) {
            addCriterion("left_leg_muscle in", values, "leftLegMuscle");
            return (Criteria) this;
        }

        public Criteria andLeftLegMuscleNotIn(List<Double> values) {
            addCriterion("left_leg_muscle not in", values, "leftLegMuscle");
            return (Criteria) this;
        }

        public Criteria andLeftLegMuscleBetween(Double value1, Double value2) {
            addCriterion("left_leg_muscle between", value1, value2, "leftLegMuscle");
            return (Criteria) this;
        }

        public Criteria andLeftLegMuscleNotBetween(Double value1, Double value2) {
            addCriterion("left_leg_muscle not between", value1, value2, "leftLegMuscle");
            return (Criteria) this;
        }

        public Criteria andRightLegMuscleIsNull() {
            addCriterion("right_leg_muscle is null");
            return (Criteria) this;
        }

        public Criteria andRightLegMuscleIsNotNull() {
            addCriterion("right_leg_muscle is not null");
            return (Criteria) this;
        }

        public Criteria andRightLegMuscleEqualTo(Double value) {
            addCriterion("right_leg_muscle =", value, "rightLegMuscle");
            return (Criteria) this;
        }

        public Criteria andRightLegMuscleNotEqualTo(Double value) {
            addCriterion("right_leg_muscle <>", value, "rightLegMuscle");
            return (Criteria) this;
        }

        public Criteria andRightLegMuscleGreaterThan(Double value) {
            addCriterion("right_leg_muscle >", value, "rightLegMuscle");
            return (Criteria) this;
        }

        public Criteria andRightLegMuscleGreaterThanOrEqualTo(Double value) {
            addCriterion("right_leg_muscle >=", value, "rightLegMuscle");
            return (Criteria) this;
        }

        public Criteria andRightLegMuscleLessThan(Double value) {
            addCriterion("right_leg_muscle <", value, "rightLegMuscle");
            return (Criteria) this;
        }

        public Criteria andRightLegMuscleLessThanOrEqualTo(Double value) {
            addCriterion("right_leg_muscle <=", value, "rightLegMuscle");
            return (Criteria) this;
        }

        public Criteria andRightLegMuscleIn(List<Double> values) {
            addCriterion("right_leg_muscle in", values, "rightLegMuscle");
            return (Criteria) this;
        }

        public Criteria andRightLegMuscleNotIn(List<Double> values) {
            addCriterion("right_leg_muscle not in", values, "rightLegMuscle");
            return (Criteria) this;
        }

        public Criteria andRightLegMuscleBetween(Double value1, Double value2) {
            addCriterion("right_leg_muscle between", value1, value2, "rightLegMuscle");
            return (Criteria) this;
        }

        public Criteria andRightLegMuscleNotBetween(Double value1, Double value2) {
            addCriterion("right_leg_muscle not between", value1, value2, "rightLegMuscle");
            return (Criteria) this;
        }

        public Criteria andLeftArmFatIsNull() {
            addCriterion("left_arm_fat is null");
            return (Criteria) this;
        }

        public Criteria andLeftArmFatIsNotNull() {
            addCriterion("left_arm_fat is not null");
            return (Criteria) this;
        }

        public Criteria andLeftArmFatEqualTo(Double value) {
            addCriterion("left_arm_fat =", value, "leftArmFat");
            return (Criteria) this;
        }

        public Criteria andLeftArmFatNotEqualTo(Double value) {
            addCriterion("left_arm_fat <>", value, "leftArmFat");
            return (Criteria) this;
        }

        public Criteria andLeftArmFatGreaterThan(Double value) {
            addCriterion("left_arm_fat >", value, "leftArmFat");
            return (Criteria) this;
        }

        public Criteria andLeftArmFatGreaterThanOrEqualTo(Double value) {
            addCriterion("left_arm_fat >=", value, "leftArmFat");
            return (Criteria) this;
        }

        public Criteria andLeftArmFatLessThan(Double value) {
            addCriterion("left_arm_fat <", value, "leftArmFat");
            return (Criteria) this;
        }

        public Criteria andLeftArmFatLessThanOrEqualTo(Double value) {
            addCriterion("left_arm_fat <=", value, "leftArmFat");
            return (Criteria) this;
        }

        public Criteria andLeftArmFatIn(List<Double> values) {
            addCriterion("left_arm_fat in", values, "leftArmFat");
            return (Criteria) this;
        }

        public Criteria andLeftArmFatNotIn(List<Double> values) {
            addCriterion("left_arm_fat not in", values, "leftArmFat");
            return (Criteria) this;
        }

        public Criteria andLeftArmFatBetween(Double value1, Double value2) {
            addCriterion("left_arm_fat between", value1, value2, "leftArmFat");
            return (Criteria) this;
        }

        public Criteria andLeftArmFatNotBetween(Double value1, Double value2) {
            addCriterion("left_arm_fat not between", value1, value2, "leftArmFat");
            return (Criteria) this;
        }

        public Criteria andRightArmFatIsNull() {
            addCriterion("right_arm_fat is null");
            return (Criteria) this;
        }

        public Criteria andRightArmFatIsNotNull() {
            addCriterion("right_arm_fat is not null");
            return (Criteria) this;
        }

        public Criteria andRightArmFatEqualTo(Double value) {
            addCriterion("right_arm_fat =", value, "rightArmFat");
            return (Criteria) this;
        }

        public Criteria andRightArmFatNotEqualTo(Double value) {
            addCriterion("right_arm_fat <>", value, "rightArmFat");
            return (Criteria) this;
        }

        public Criteria andRightArmFatGreaterThan(Double value) {
            addCriterion("right_arm_fat >", value, "rightArmFat");
            return (Criteria) this;
        }

        public Criteria andRightArmFatGreaterThanOrEqualTo(Double value) {
            addCriterion("right_arm_fat >=", value, "rightArmFat");
            return (Criteria) this;
        }

        public Criteria andRightArmFatLessThan(Double value) {
            addCriterion("right_arm_fat <", value, "rightArmFat");
            return (Criteria) this;
        }

        public Criteria andRightArmFatLessThanOrEqualTo(Double value) {
            addCriterion("right_arm_fat <=", value, "rightArmFat");
            return (Criteria) this;
        }

        public Criteria andRightArmFatIn(List<Double> values) {
            addCriterion("right_arm_fat in", values, "rightArmFat");
            return (Criteria) this;
        }

        public Criteria andRightArmFatNotIn(List<Double> values) {
            addCriterion("right_arm_fat not in", values, "rightArmFat");
            return (Criteria) this;
        }

        public Criteria andRightArmFatBetween(Double value1, Double value2) {
            addCriterion("right_arm_fat between", value1, value2, "rightArmFat");
            return (Criteria) this;
        }

        public Criteria andRightArmFatNotBetween(Double value1, Double value2) {
            addCriterion("right_arm_fat not between", value1, value2, "rightArmFat");
            return (Criteria) this;
        }

        public Criteria andLeftLegFatIsNull() {
            addCriterion("left_leg_fat is null");
            return (Criteria) this;
        }

        public Criteria andLeftLegFatIsNotNull() {
            addCriterion("left_leg_fat is not null");
            return (Criteria) this;
        }

        public Criteria andLeftLegFatEqualTo(Double value) {
            addCriterion("left_leg_fat =", value, "leftLegFat");
            return (Criteria) this;
        }

        public Criteria andLeftLegFatNotEqualTo(Double value) {
            addCriterion("left_leg_fat <>", value, "leftLegFat");
            return (Criteria) this;
        }

        public Criteria andLeftLegFatGreaterThan(Double value) {
            addCriterion("left_leg_fat >", value, "leftLegFat");
            return (Criteria) this;
        }

        public Criteria andLeftLegFatGreaterThanOrEqualTo(Double value) {
            addCriterion("left_leg_fat >=", value, "leftLegFat");
            return (Criteria) this;
        }

        public Criteria andLeftLegFatLessThan(Double value) {
            addCriterion("left_leg_fat <", value, "leftLegFat");
            return (Criteria) this;
        }

        public Criteria andLeftLegFatLessThanOrEqualTo(Double value) {
            addCriterion("left_leg_fat <=", value, "leftLegFat");
            return (Criteria) this;
        }

        public Criteria andLeftLegFatIn(List<Double> values) {
            addCriterion("left_leg_fat in", values, "leftLegFat");
            return (Criteria) this;
        }

        public Criteria andLeftLegFatNotIn(List<Double> values) {
            addCriterion("left_leg_fat not in", values, "leftLegFat");
            return (Criteria) this;
        }

        public Criteria andLeftLegFatBetween(Double value1, Double value2) {
            addCriterion("left_leg_fat between", value1, value2, "leftLegFat");
            return (Criteria) this;
        }

        public Criteria andLeftLegFatNotBetween(Double value1, Double value2) {
            addCriterion("left_leg_fat not between", value1, value2, "leftLegFat");
            return (Criteria) this;
        }

        public Criteria andRightLegFatIsNull() {
            addCriterion("right_leg_fat is null");
            return (Criteria) this;
        }

        public Criteria andRightLegFatIsNotNull() {
            addCriterion("right_leg_fat is not null");
            return (Criteria) this;
        }

        public Criteria andRightLegFatEqualTo(Double value) {
            addCriterion("right_leg_fat =", value, "rightLegFat");
            return (Criteria) this;
        }

        public Criteria andRightLegFatNotEqualTo(Double value) {
            addCriterion("right_leg_fat <>", value, "rightLegFat");
            return (Criteria) this;
        }

        public Criteria andRightLegFatGreaterThan(Double value) {
            addCriterion("right_leg_fat >", value, "rightLegFat");
            return (Criteria) this;
        }

        public Criteria andRightLegFatGreaterThanOrEqualTo(Double value) {
            addCriterion("right_leg_fat >=", value, "rightLegFat");
            return (Criteria) this;
        }

        public Criteria andRightLegFatLessThan(Double value) {
            addCriterion("right_leg_fat <", value, "rightLegFat");
            return (Criteria) this;
        }

        public Criteria andRightLegFatLessThanOrEqualTo(Double value) {
            addCriterion("right_leg_fat <=", value, "rightLegFat");
            return (Criteria) this;
        }

        public Criteria andRightLegFatIn(List<Double> values) {
            addCriterion("right_leg_fat in", values, "rightLegFat");
            return (Criteria) this;
        }

        public Criteria andRightLegFatNotIn(List<Double> values) {
            addCriterion("right_leg_fat not in", values, "rightLegFat");
            return (Criteria) this;
        }

        public Criteria andRightLegFatBetween(Double value1, Double value2) {
            addCriterion("right_leg_fat between", value1, value2, "rightLegFat");
            return (Criteria) this;
        }

        public Criteria andRightLegFatNotBetween(Double value1, Double value2) {
            addCriterion("right_leg_fat not between", value1, value2, "rightLegFat");
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