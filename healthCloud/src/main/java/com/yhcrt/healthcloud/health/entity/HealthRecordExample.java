package com.yhcrt.healthcloud.health.entity;

import java.util.ArrayList;
import java.util.List;

public class HealthRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public HealthRecordExample() {
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

        public Criteria andRecordIdIsNull() {
            addCriterion("record_id is null");
            return (Criteria) this;
        }

        public Criteria andRecordIdIsNotNull() {
            addCriterion("record_id is not null");
            return (Criteria) this;
        }

        public Criteria andRecordIdEqualTo(Integer value) {
            addCriterion("record_id =", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdNotEqualTo(Integer value) {
            addCriterion("record_id <>", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdGreaterThan(Integer value) {
            addCriterion("record_id >", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("record_id >=", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdLessThan(Integer value) {
            addCriterion("record_id <", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdLessThanOrEqualTo(Integer value) {
            addCriterion("record_id <=", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdIn(List<Integer> values) {
            addCriterion("record_id in", values, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdNotIn(List<Integer> values) {
            addCriterion("record_id not in", values, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdBetween(Integer value1, Integer value2) {
            addCriterion("record_id between", value1, value2, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdNotBetween(Integer value1, Integer value2) {
            addCriterion("record_id not between", value1, value2, "recordId");
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

        public Criteria andAllergicHistoryIsNull() {
            addCriterion("allergic_history is null");
            return (Criteria) this;
        }

        public Criteria andAllergicHistoryIsNotNull() {
            addCriterion("allergic_history is not null");
            return (Criteria) this;
        }

        public Criteria andAllergicHistoryEqualTo(String value) {
            addCriterion("allergic_history =", value, "allergicHistory");
            return (Criteria) this;
        }

        public Criteria andAllergicHistoryNotEqualTo(String value) {
            addCriterion("allergic_history <>", value, "allergicHistory");
            return (Criteria) this;
        }

        public Criteria andAllergicHistoryGreaterThan(String value) {
            addCriterion("allergic_history >", value, "allergicHistory");
            return (Criteria) this;
        }

        public Criteria andAllergicHistoryGreaterThanOrEqualTo(String value) {
            addCriterion("allergic_history >=", value, "allergicHistory");
            return (Criteria) this;
        }

        public Criteria andAllergicHistoryLessThan(String value) {
            addCriterion("allergic_history <", value, "allergicHistory");
            return (Criteria) this;
        }

        public Criteria andAllergicHistoryLessThanOrEqualTo(String value) {
            addCriterion("allergic_history <=", value, "allergicHistory");
            return (Criteria) this;
        }

        public Criteria andAllergicHistoryLike(String value) {
            addCriterion("allergic_history like", value, "allergicHistory");
            return (Criteria) this;
        }

        public Criteria andAllergicHistoryNotLike(String value) {
            addCriterion("allergic_history not like", value, "allergicHistory");
            return (Criteria) this;
        }

        public Criteria andAllergicHistoryIn(List<String> values) {
            addCriterion("allergic_history in", values, "allergicHistory");
            return (Criteria) this;
        }

        public Criteria andAllergicHistoryNotIn(List<String> values) {
            addCriterion("allergic_history not in", values, "allergicHistory");
            return (Criteria) this;
        }

        public Criteria andAllergicHistoryBetween(String value1, String value2) {
            addCriterion("allergic_history between", value1, value2, "allergicHistory");
            return (Criteria) this;
        }

        public Criteria andAllergicHistoryNotBetween(String value1, String value2) {
            addCriterion("allergic_history not between", value1, value2, "allergicHistory");
            return (Criteria) this;
        }

        public Criteria andCommunicableDiseasesIsNull() {
            addCriterion("communicable_diseases is null");
            return (Criteria) this;
        }

        public Criteria andCommunicableDiseasesIsNotNull() {
            addCriterion("communicable_diseases is not null");
            return (Criteria) this;
        }

        public Criteria andCommunicableDiseasesEqualTo(String value) {
            addCriterion("communicable_diseases =", value, "communicableDiseases");
            return (Criteria) this;
        }

        public Criteria andCommunicableDiseasesNotEqualTo(String value) {
            addCriterion("communicable_diseases <>", value, "communicableDiseases");
            return (Criteria) this;
        }

        public Criteria andCommunicableDiseasesGreaterThan(String value) {
            addCriterion("communicable_diseases >", value, "communicableDiseases");
            return (Criteria) this;
        }

        public Criteria andCommunicableDiseasesGreaterThanOrEqualTo(String value) {
            addCriterion("communicable_diseases >=", value, "communicableDiseases");
            return (Criteria) this;
        }

        public Criteria andCommunicableDiseasesLessThan(String value) {
            addCriterion("communicable_diseases <", value, "communicableDiseases");
            return (Criteria) this;
        }

        public Criteria andCommunicableDiseasesLessThanOrEqualTo(String value) {
            addCriterion("communicable_diseases <=", value, "communicableDiseases");
            return (Criteria) this;
        }

        public Criteria andCommunicableDiseasesLike(String value) {
            addCriterion("communicable_diseases like", value, "communicableDiseases");
            return (Criteria) this;
        }

        public Criteria andCommunicableDiseasesNotLike(String value) {
            addCriterion("communicable_diseases not like", value, "communicableDiseases");
            return (Criteria) this;
        }

        public Criteria andCommunicableDiseasesIn(List<String> values) {
            addCriterion("communicable_diseases in", values, "communicableDiseases");
            return (Criteria) this;
        }

        public Criteria andCommunicableDiseasesNotIn(List<String> values) {
            addCriterion("communicable_diseases not in", values, "communicableDiseases");
            return (Criteria) this;
        }

        public Criteria andCommunicableDiseasesBetween(String value1, String value2) {
            addCriterion("communicable_diseases between", value1, value2, "communicableDiseases");
            return (Criteria) this;
        }

        public Criteria andCommunicableDiseasesNotBetween(String value1, String value2) {
            addCriterion("communicable_diseases not between", value1, value2, "communicableDiseases");
            return (Criteria) this;
        }

        public Criteria andInheritedDiseasesIsNull() {
            addCriterion("inherited_diseases is null");
            return (Criteria) this;
        }

        public Criteria andInheritedDiseasesIsNotNull() {
            addCriterion("inherited_diseases is not null");
            return (Criteria) this;
        }

        public Criteria andInheritedDiseasesEqualTo(String value) {
            addCriterion("inherited_diseases =", value, "inheritedDiseases");
            return (Criteria) this;
        }

        public Criteria andInheritedDiseasesNotEqualTo(String value) {
            addCriterion("inherited_diseases <>", value, "inheritedDiseases");
            return (Criteria) this;
        }

        public Criteria andInheritedDiseasesGreaterThan(String value) {
            addCriterion("inherited_diseases >", value, "inheritedDiseases");
            return (Criteria) this;
        }

        public Criteria andInheritedDiseasesGreaterThanOrEqualTo(String value) {
            addCriterion("inherited_diseases >=", value, "inheritedDiseases");
            return (Criteria) this;
        }

        public Criteria andInheritedDiseasesLessThan(String value) {
            addCriterion("inherited_diseases <", value, "inheritedDiseases");
            return (Criteria) this;
        }

        public Criteria andInheritedDiseasesLessThanOrEqualTo(String value) {
            addCriterion("inherited_diseases <=", value, "inheritedDiseases");
            return (Criteria) this;
        }

        public Criteria andInheritedDiseasesLike(String value) {
            addCriterion("inherited_diseases like", value, "inheritedDiseases");
            return (Criteria) this;
        }

        public Criteria andInheritedDiseasesNotLike(String value) {
            addCriterion("inherited_diseases not like", value, "inheritedDiseases");
            return (Criteria) this;
        }

        public Criteria andInheritedDiseasesIn(List<String> values) {
            addCriterion("inherited_diseases in", values, "inheritedDiseases");
            return (Criteria) this;
        }

        public Criteria andInheritedDiseasesNotIn(List<String> values) {
            addCriterion("inherited_diseases not in", values, "inheritedDiseases");
            return (Criteria) this;
        }

        public Criteria andInheritedDiseasesBetween(String value1, String value2) {
            addCriterion("inherited_diseases between", value1, value2, "inheritedDiseases");
            return (Criteria) this;
        }

        public Criteria andInheritedDiseasesNotBetween(String value1, String value2) {
            addCriterion("inherited_diseases not between", value1, value2, "inheritedDiseases");
            return (Criteria) this;
        }

        public Criteria andDisabilityIsNull() {
            addCriterion("disability is null");
            return (Criteria) this;
        }

        public Criteria andDisabilityIsNotNull() {
            addCriterion("disability is not null");
            return (Criteria) this;
        }

        public Criteria andDisabilityEqualTo(String value) {
            addCriterion("disability =", value, "disability");
            return (Criteria) this;
        }

        public Criteria andDisabilityNotEqualTo(String value) {
            addCriterion("disability <>", value, "disability");
            return (Criteria) this;
        }

        public Criteria andDisabilityGreaterThan(String value) {
            addCriterion("disability >", value, "disability");
            return (Criteria) this;
        }

        public Criteria andDisabilityGreaterThanOrEqualTo(String value) {
            addCriterion("disability >=", value, "disability");
            return (Criteria) this;
        }

        public Criteria andDisabilityLessThan(String value) {
            addCriterion("disability <", value, "disability");
            return (Criteria) this;
        }

        public Criteria andDisabilityLessThanOrEqualTo(String value) {
            addCriterion("disability <=", value, "disability");
            return (Criteria) this;
        }

        public Criteria andDisabilityLike(String value) {
            addCriterion("disability like", value, "disability");
            return (Criteria) this;
        }

        public Criteria andDisabilityNotLike(String value) {
            addCriterion("disability not like", value, "disability");
            return (Criteria) this;
        }

        public Criteria andDisabilityIn(List<String> values) {
            addCriterion("disability in", values, "disability");
            return (Criteria) this;
        }

        public Criteria andDisabilityNotIn(List<String> values) {
            addCriterion("disability not in", values, "disability");
            return (Criteria) this;
        }

        public Criteria andDisabilityBetween(String value1, String value2) {
            addCriterion("disability between", value1, value2, "disability");
            return (Criteria) this;
        }

        public Criteria andDisabilityNotBetween(String value1, String value2) {
            addCriterion("disability not between", value1, value2, "disability");
            return (Criteria) this;
        }

        public Criteria andIsHypertensionIsNull() {
            addCriterion("is_hypertension is null");
            return (Criteria) this;
        }

        public Criteria andIsHypertensionIsNotNull() {
            addCriterion("is_hypertension is not null");
            return (Criteria) this;
        }

        public Criteria andIsHypertensionEqualTo(String value) {
            addCriterion("is_hypertension =", value, "isHypertension");
            return (Criteria) this;
        }

        public Criteria andIsHypertensionNotEqualTo(String value) {
            addCriterion("is_hypertension <>", value, "isHypertension");
            return (Criteria) this;
        }

        public Criteria andIsHypertensionGreaterThan(String value) {
            addCriterion("is_hypertension >", value, "isHypertension");
            return (Criteria) this;
        }

        public Criteria andIsHypertensionGreaterThanOrEqualTo(String value) {
            addCriterion("is_hypertension >=", value, "isHypertension");
            return (Criteria) this;
        }

        public Criteria andIsHypertensionLessThan(String value) {
            addCriterion("is_hypertension <", value, "isHypertension");
            return (Criteria) this;
        }

        public Criteria andIsHypertensionLessThanOrEqualTo(String value) {
            addCriterion("is_hypertension <=", value, "isHypertension");
            return (Criteria) this;
        }

        public Criteria andIsHypertensionLike(String value) {
            addCriterion("is_hypertension like", value, "isHypertension");
            return (Criteria) this;
        }

        public Criteria andIsHypertensionNotLike(String value) {
            addCriterion("is_hypertension not like", value, "isHypertension");
            return (Criteria) this;
        }

        public Criteria andIsHypertensionIn(List<String> values) {
            addCriterion("is_hypertension in", values, "isHypertension");
            return (Criteria) this;
        }

        public Criteria andIsHypertensionNotIn(List<String> values) {
            addCriterion("is_hypertension not in", values, "isHypertension");
            return (Criteria) this;
        }

        public Criteria andIsHypertensionBetween(String value1, String value2) {
            addCriterion("is_hypertension between", value1, value2, "isHypertension");
            return (Criteria) this;
        }

        public Criteria andIsHypertensionNotBetween(String value1, String value2) {
            addCriterion("is_hypertension not between", value1, value2, "isHypertension");
            return (Criteria) this;
        }

        public Criteria andIsDmIsNull() {
            addCriterion("is_dm is null");
            return (Criteria) this;
        }

        public Criteria andIsDmIsNotNull() {
            addCriterion("is_dm is not null");
            return (Criteria) this;
        }

        public Criteria andIsDmEqualTo(String value) {
            addCriterion("is_dm =", value, "isDm");
            return (Criteria) this;
        }

        public Criteria andIsDmNotEqualTo(String value) {
            addCriterion("is_dm <>", value, "isDm");
            return (Criteria) this;
        }

        public Criteria andIsDmGreaterThan(String value) {
            addCriterion("is_dm >", value, "isDm");
            return (Criteria) this;
        }

        public Criteria andIsDmGreaterThanOrEqualTo(String value) {
            addCriterion("is_dm >=", value, "isDm");
            return (Criteria) this;
        }

        public Criteria andIsDmLessThan(String value) {
            addCriterion("is_dm <", value, "isDm");
            return (Criteria) this;
        }

        public Criteria andIsDmLessThanOrEqualTo(String value) {
            addCriterion("is_dm <=", value, "isDm");
            return (Criteria) this;
        }

        public Criteria andIsDmLike(String value) {
            addCriterion("is_dm like", value, "isDm");
            return (Criteria) this;
        }

        public Criteria andIsDmNotLike(String value) {
            addCriterion("is_dm not like", value, "isDm");
            return (Criteria) this;
        }

        public Criteria andIsDmIn(List<String> values) {
            addCriterion("is_dm in", values, "isDm");
            return (Criteria) this;
        }

        public Criteria andIsDmNotIn(List<String> values) {
            addCriterion("is_dm not in", values, "isDm");
            return (Criteria) this;
        }

        public Criteria andIsDmBetween(String value1, String value2) {
            addCriterion("is_dm between", value1, value2, "isDm");
            return (Criteria) this;
        }

        public Criteria andIsDmNotBetween(String value1, String value2) {
            addCriterion("is_dm not between", value1, value2, "isDm");
            return (Criteria) this;
        }

        public Criteria andIsCahdIsNull() {
            addCriterion("is_cahd is null");
            return (Criteria) this;
        }

        public Criteria andIsCahdIsNotNull() {
            addCriterion("is_cahd is not null");
            return (Criteria) this;
        }

        public Criteria andIsCahdEqualTo(String value) {
            addCriterion("is_cahd =", value, "isCahd");
            return (Criteria) this;
        }

        public Criteria andIsCahdNotEqualTo(String value) {
            addCriterion("is_cahd <>", value, "isCahd");
            return (Criteria) this;
        }

        public Criteria andIsCahdGreaterThan(String value) {
            addCriterion("is_cahd >", value, "isCahd");
            return (Criteria) this;
        }

        public Criteria andIsCahdGreaterThanOrEqualTo(String value) {
            addCriterion("is_cahd >=", value, "isCahd");
            return (Criteria) this;
        }

        public Criteria andIsCahdLessThan(String value) {
            addCriterion("is_cahd <", value, "isCahd");
            return (Criteria) this;
        }

        public Criteria andIsCahdLessThanOrEqualTo(String value) {
            addCriterion("is_cahd <=", value, "isCahd");
            return (Criteria) this;
        }

        public Criteria andIsCahdLike(String value) {
            addCriterion("is_cahd like", value, "isCahd");
            return (Criteria) this;
        }

        public Criteria andIsCahdNotLike(String value) {
            addCriterion("is_cahd not like", value, "isCahd");
            return (Criteria) this;
        }

        public Criteria andIsCahdIn(List<String> values) {
            addCriterion("is_cahd in", values, "isCahd");
            return (Criteria) this;
        }

        public Criteria andIsCahdNotIn(List<String> values) {
            addCriterion("is_cahd not in", values, "isCahd");
            return (Criteria) this;
        }

        public Criteria andIsCahdBetween(String value1, String value2) {
            addCriterion("is_cahd between", value1, value2, "isCahd");
            return (Criteria) this;
        }

        public Criteria andIsCahdNotBetween(String value1, String value2) {
            addCriterion("is_cahd not between", value1, value2, "isCahd");
            return (Criteria) this;
        }

        public Criteria andIsCancerIsNull() {
            addCriterion("is_cancer is null");
            return (Criteria) this;
        }

        public Criteria andIsCancerIsNotNull() {
            addCriterion("is_cancer is not null");
            return (Criteria) this;
        }

        public Criteria andIsCancerEqualTo(String value) {
            addCriterion("is_cancer =", value, "isCancer");
            return (Criteria) this;
        }

        public Criteria andIsCancerNotEqualTo(String value) {
            addCriterion("is_cancer <>", value, "isCancer");
            return (Criteria) this;
        }

        public Criteria andIsCancerGreaterThan(String value) {
            addCriterion("is_cancer >", value, "isCancer");
            return (Criteria) this;
        }

        public Criteria andIsCancerGreaterThanOrEqualTo(String value) {
            addCriterion("is_cancer >=", value, "isCancer");
            return (Criteria) this;
        }

        public Criteria andIsCancerLessThan(String value) {
            addCriterion("is_cancer <", value, "isCancer");
            return (Criteria) this;
        }

        public Criteria andIsCancerLessThanOrEqualTo(String value) {
            addCriterion("is_cancer <=", value, "isCancer");
            return (Criteria) this;
        }

        public Criteria andIsCancerLike(String value) {
            addCriterion("is_cancer like", value, "isCancer");
            return (Criteria) this;
        }

        public Criteria andIsCancerNotLike(String value) {
            addCriterion("is_cancer not like", value, "isCancer");
            return (Criteria) this;
        }

        public Criteria andIsCancerIn(List<String> values) {
            addCriterion("is_cancer in", values, "isCancer");
            return (Criteria) this;
        }

        public Criteria andIsCancerNotIn(List<String> values) {
            addCriterion("is_cancer not in", values, "isCancer");
            return (Criteria) this;
        }

        public Criteria andIsCancerBetween(String value1, String value2) {
            addCriterion("is_cancer between", value1, value2, "isCancer");
            return (Criteria) this;
        }

        public Criteria andIsCancerNotBetween(String value1, String value2) {
            addCriterion("is_cancer not between", value1, value2, "isCancer");
            return (Criteria) this;
        }

        public Criteria andIsCerebralApoplexyIsNull() {
            addCriterion("is_cerebral_apoplexy is null");
            return (Criteria) this;
        }

        public Criteria andIsCerebralApoplexyIsNotNull() {
            addCriterion("is_cerebral_apoplexy is not null");
            return (Criteria) this;
        }

        public Criteria andIsCerebralApoplexyEqualTo(String value) {
            addCriterion("is_cerebral_apoplexy =", value, "isCerebralApoplexy");
            return (Criteria) this;
        }

        public Criteria andIsCerebralApoplexyNotEqualTo(String value) {
            addCriterion("is_cerebral_apoplexy <>", value, "isCerebralApoplexy");
            return (Criteria) this;
        }

        public Criteria andIsCerebralApoplexyGreaterThan(String value) {
            addCriterion("is_cerebral_apoplexy >", value, "isCerebralApoplexy");
            return (Criteria) this;
        }

        public Criteria andIsCerebralApoplexyGreaterThanOrEqualTo(String value) {
            addCriterion("is_cerebral_apoplexy >=", value, "isCerebralApoplexy");
            return (Criteria) this;
        }

        public Criteria andIsCerebralApoplexyLessThan(String value) {
            addCriterion("is_cerebral_apoplexy <", value, "isCerebralApoplexy");
            return (Criteria) this;
        }

        public Criteria andIsCerebralApoplexyLessThanOrEqualTo(String value) {
            addCriterion("is_cerebral_apoplexy <=", value, "isCerebralApoplexy");
            return (Criteria) this;
        }

        public Criteria andIsCerebralApoplexyLike(String value) {
            addCriterion("is_cerebral_apoplexy like", value, "isCerebralApoplexy");
            return (Criteria) this;
        }

        public Criteria andIsCerebralApoplexyNotLike(String value) {
            addCriterion("is_cerebral_apoplexy not like", value, "isCerebralApoplexy");
            return (Criteria) this;
        }

        public Criteria andIsCerebralApoplexyIn(List<String> values) {
            addCriterion("is_cerebral_apoplexy in", values, "isCerebralApoplexy");
            return (Criteria) this;
        }

        public Criteria andIsCerebralApoplexyNotIn(List<String> values) {
            addCriterion("is_cerebral_apoplexy not in", values, "isCerebralApoplexy");
            return (Criteria) this;
        }

        public Criteria andIsCerebralApoplexyBetween(String value1, String value2) {
            addCriterion("is_cerebral_apoplexy between", value1, value2, "isCerebralApoplexy");
            return (Criteria) this;
        }

        public Criteria andIsCerebralApoplexyNotBetween(String value1, String value2) {
            addCriterion("is_cerebral_apoplexy not between", value1, value2, "isCerebralApoplexy");
            return (Criteria) this;
        }

        public Criteria andIsCopdIsNull() {
            addCriterion("is_copd is null");
            return (Criteria) this;
        }

        public Criteria andIsCopdIsNotNull() {
            addCriterion("is_copd is not null");
            return (Criteria) this;
        }

        public Criteria andIsCopdEqualTo(String value) {
            addCriterion("is_copd =", value, "isCopd");
            return (Criteria) this;
        }

        public Criteria andIsCopdNotEqualTo(String value) {
            addCriterion("is_copd <>", value, "isCopd");
            return (Criteria) this;
        }

        public Criteria andIsCopdGreaterThan(String value) {
            addCriterion("is_copd >", value, "isCopd");
            return (Criteria) this;
        }

        public Criteria andIsCopdGreaterThanOrEqualTo(String value) {
            addCriterion("is_copd >=", value, "isCopd");
            return (Criteria) this;
        }

        public Criteria andIsCopdLessThan(String value) {
            addCriterion("is_copd <", value, "isCopd");
            return (Criteria) this;
        }

        public Criteria andIsCopdLessThanOrEqualTo(String value) {
            addCriterion("is_copd <=", value, "isCopd");
            return (Criteria) this;
        }

        public Criteria andIsCopdLike(String value) {
            addCriterion("is_copd like", value, "isCopd");
            return (Criteria) this;
        }

        public Criteria andIsCopdNotLike(String value) {
            addCriterion("is_copd not like", value, "isCopd");
            return (Criteria) this;
        }

        public Criteria andIsCopdIn(List<String> values) {
            addCriterion("is_copd in", values, "isCopd");
            return (Criteria) this;
        }

        public Criteria andIsCopdNotIn(List<String> values) {
            addCriterion("is_copd not in", values, "isCopd");
            return (Criteria) this;
        }

        public Criteria andIsCopdBetween(String value1, String value2) {
            addCriterion("is_copd between", value1, value2, "isCopd");
            return (Criteria) this;
        }

        public Criteria andIsCopdNotBetween(String value1, String value2) {
            addCriterion("is_copd not between", value1, value2, "isCopd");
            return (Criteria) this;
        }

        public Criteria andIsTuberculosisIsNull() {
            addCriterion("is_tuberculosis is null");
            return (Criteria) this;
        }

        public Criteria andIsTuberculosisIsNotNull() {
            addCriterion("is_tuberculosis is not null");
            return (Criteria) this;
        }

        public Criteria andIsTuberculosisEqualTo(String value) {
            addCriterion("is_tuberculosis =", value, "isTuberculosis");
            return (Criteria) this;
        }

        public Criteria andIsTuberculosisNotEqualTo(String value) {
            addCriterion("is_tuberculosis <>", value, "isTuberculosis");
            return (Criteria) this;
        }

        public Criteria andIsTuberculosisGreaterThan(String value) {
            addCriterion("is_tuberculosis >", value, "isTuberculosis");
            return (Criteria) this;
        }

        public Criteria andIsTuberculosisGreaterThanOrEqualTo(String value) {
            addCriterion("is_tuberculosis >=", value, "isTuberculosis");
            return (Criteria) this;
        }

        public Criteria andIsTuberculosisLessThan(String value) {
            addCriterion("is_tuberculosis <", value, "isTuberculosis");
            return (Criteria) this;
        }

        public Criteria andIsTuberculosisLessThanOrEqualTo(String value) {
            addCriterion("is_tuberculosis <=", value, "isTuberculosis");
            return (Criteria) this;
        }

        public Criteria andIsTuberculosisLike(String value) {
            addCriterion("is_tuberculosis like", value, "isTuberculosis");
            return (Criteria) this;
        }

        public Criteria andIsTuberculosisNotLike(String value) {
            addCriterion("is_tuberculosis not like", value, "isTuberculosis");
            return (Criteria) this;
        }

        public Criteria andIsTuberculosisIn(List<String> values) {
            addCriterion("is_tuberculosis in", values, "isTuberculosis");
            return (Criteria) this;
        }

        public Criteria andIsTuberculosisNotIn(List<String> values) {
            addCriterion("is_tuberculosis not in", values, "isTuberculosis");
            return (Criteria) this;
        }

        public Criteria andIsTuberculosisBetween(String value1, String value2) {
            addCriterion("is_tuberculosis between", value1, value2, "isTuberculosis");
            return (Criteria) this;
        }

        public Criteria andIsTuberculosisNotBetween(String value1, String value2) {
            addCriterion("is_tuberculosis not between", value1, value2, "isTuberculosis");
            return (Criteria) this;
        }

        public Criteria andIsSchizophreniaIsNull() {
            addCriterion("is_schizophrenia is null");
            return (Criteria) this;
        }

        public Criteria andIsSchizophreniaIsNotNull() {
            addCriterion("is_schizophrenia is not null");
            return (Criteria) this;
        }

        public Criteria andIsSchizophreniaEqualTo(String value) {
            addCriterion("is_schizophrenia =", value, "isSchizophrenia");
            return (Criteria) this;
        }

        public Criteria andIsSchizophreniaNotEqualTo(String value) {
            addCriterion("is_schizophrenia <>", value, "isSchizophrenia");
            return (Criteria) this;
        }

        public Criteria andIsSchizophreniaGreaterThan(String value) {
            addCriterion("is_schizophrenia >", value, "isSchizophrenia");
            return (Criteria) this;
        }

        public Criteria andIsSchizophreniaGreaterThanOrEqualTo(String value) {
            addCriterion("is_schizophrenia >=", value, "isSchizophrenia");
            return (Criteria) this;
        }

        public Criteria andIsSchizophreniaLessThan(String value) {
            addCriterion("is_schizophrenia <", value, "isSchizophrenia");
            return (Criteria) this;
        }

        public Criteria andIsSchizophreniaLessThanOrEqualTo(String value) {
            addCriterion("is_schizophrenia <=", value, "isSchizophrenia");
            return (Criteria) this;
        }

        public Criteria andIsSchizophreniaLike(String value) {
            addCriterion("is_schizophrenia like", value, "isSchizophrenia");
            return (Criteria) this;
        }

        public Criteria andIsSchizophreniaNotLike(String value) {
            addCriterion("is_schizophrenia not like", value, "isSchizophrenia");
            return (Criteria) this;
        }

        public Criteria andIsSchizophreniaIn(List<String> values) {
            addCriterion("is_schizophrenia in", values, "isSchizophrenia");
            return (Criteria) this;
        }

        public Criteria andIsSchizophreniaNotIn(List<String> values) {
            addCriterion("is_schizophrenia not in", values, "isSchizophrenia");
            return (Criteria) this;
        }

        public Criteria andIsSchizophreniaBetween(String value1, String value2) {
            addCriterion("is_schizophrenia between", value1, value2, "isSchizophrenia");
            return (Criteria) this;
        }

        public Criteria andIsSchizophreniaNotBetween(String value1, String value2) {
            addCriterion("is_schizophrenia not between", value1, value2, "isSchizophrenia");
            return (Criteria) this;
        }

        public Criteria andIsHepatitisIsNull() {
            addCriterion("is_hepatitis is null");
            return (Criteria) this;
        }

        public Criteria andIsHepatitisIsNotNull() {
            addCriterion("is_hepatitis is not null");
            return (Criteria) this;
        }

        public Criteria andIsHepatitisEqualTo(String value) {
            addCriterion("is_hepatitis =", value, "isHepatitis");
            return (Criteria) this;
        }

        public Criteria andIsHepatitisNotEqualTo(String value) {
            addCriterion("is_hepatitis <>", value, "isHepatitis");
            return (Criteria) this;
        }

        public Criteria andIsHepatitisGreaterThan(String value) {
            addCriterion("is_hepatitis >", value, "isHepatitis");
            return (Criteria) this;
        }

        public Criteria andIsHepatitisGreaterThanOrEqualTo(String value) {
            addCriterion("is_hepatitis >=", value, "isHepatitis");
            return (Criteria) this;
        }

        public Criteria andIsHepatitisLessThan(String value) {
            addCriterion("is_hepatitis <", value, "isHepatitis");
            return (Criteria) this;
        }

        public Criteria andIsHepatitisLessThanOrEqualTo(String value) {
            addCriterion("is_hepatitis <=", value, "isHepatitis");
            return (Criteria) this;
        }

        public Criteria andIsHepatitisLike(String value) {
            addCriterion("is_hepatitis like", value, "isHepatitis");
            return (Criteria) this;
        }

        public Criteria andIsHepatitisNotLike(String value) {
            addCriterion("is_hepatitis not like", value, "isHepatitis");
            return (Criteria) this;
        }

        public Criteria andIsHepatitisIn(List<String> values) {
            addCriterion("is_hepatitis in", values, "isHepatitis");
            return (Criteria) this;
        }

        public Criteria andIsHepatitisNotIn(List<String> values) {
            addCriterion("is_hepatitis not in", values, "isHepatitis");
            return (Criteria) this;
        }

        public Criteria andIsHepatitisBetween(String value1, String value2) {
            addCriterion("is_hepatitis between", value1, value2, "isHepatitis");
            return (Criteria) this;
        }

        public Criteria andIsHepatitisNotBetween(String value1, String value2) {
            addCriterion("is_hepatitis not between", value1, value2, "isHepatitis");
            return (Criteria) this;
        }

        public Criteria andIsCholesterolIsNull() {
            addCriterion("is_cholesterol is null");
            return (Criteria) this;
        }

        public Criteria andIsCholesterolIsNotNull() {
            addCriterion("is_cholesterol is not null");
            return (Criteria) this;
        }

        public Criteria andIsCholesterolEqualTo(String value) {
            addCriterion("is_cholesterol =", value, "isCholesterol");
            return (Criteria) this;
        }

        public Criteria andIsCholesterolNotEqualTo(String value) {
            addCriterion("is_cholesterol <>", value, "isCholesterol");
            return (Criteria) this;
        }

        public Criteria andIsCholesterolGreaterThan(String value) {
            addCriterion("is_cholesterol >", value, "isCholesterol");
            return (Criteria) this;
        }

        public Criteria andIsCholesterolGreaterThanOrEqualTo(String value) {
            addCriterion("is_cholesterol >=", value, "isCholesterol");
            return (Criteria) this;
        }

        public Criteria andIsCholesterolLessThan(String value) {
            addCriterion("is_cholesterol <", value, "isCholesterol");
            return (Criteria) this;
        }

        public Criteria andIsCholesterolLessThanOrEqualTo(String value) {
            addCriterion("is_cholesterol <=", value, "isCholesterol");
            return (Criteria) this;
        }

        public Criteria andIsCholesterolLike(String value) {
            addCriterion("is_cholesterol like", value, "isCholesterol");
            return (Criteria) this;
        }

        public Criteria andIsCholesterolNotLike(String value) {
            addCriterion("is_cholesterol not like", value, "isCholesterol");
            return (Criteria) this;
        }

        public Criteria andIsCholesterolIn(List<String> values) {
            addCriterion("is_cholesterol in", values, "isCholesterol");
            return (Criteria) this;
        }

        public Criteria andIsCholesterolNotIn(List<String> values) {
            addCriterion("is_cholesterol not in", values, "isCholesterol");
            return (Criteria) this;
        }

        public Criteria andIsCholesterolBetween(String value1, String value2) {
            addCriterion("is_cholesterol between", value1, value2, "isCholesterol");
            return (Criteria) this;
        }

        public Criteria andIsCholesterolNotBetween(String value1, String value2) {
            addCriterion("is_cholesterol not between", value1, value2, "isCholesterol");
            return (Criteria) this;
        }

        public Criteria andIsHyperlipoidemiaIsNull() {
            addCriterion("is_hyperlipoidemia is null");
            return (Criteria) this;
        }

        public Criteria andIsHyperlipoidemiaIsNotNull() {
            addCriterion("is_hyperlipoidemia is not null");
            return (Criteria) this;
        }

        public Criteria andIsHyperlipoidemiaEqualTo(String value) {
            addCriterion("is_hyperlipoidemia =", value, "isHyperlipoidemia");
            return (Criteria) this;
        }

        public Criteria andIsHyperlipoidemiaNotEqualTo(String value) {
            addCriterion("is_hyperlipoidemia <>", value, "isHyperlipoidemia");
            return (Criteria) this;
        }

        public Criteria andIsHyperlipoidemiaGreaterThan(String value) {
            addCriterion("is_hyperlipoidemia >", value, "isHyperlipoidemia");
            return (Criteria) this;
        }

        public Criteria andIsHyperlipoidemiaGreaterThanOrEqualTo(String value) {
            addCriterion("is_hyperlipoidemia >=", value, "isHyperlipoidemia");
            return (Criteria) this;
        }

        public Criteria andIsHyperlipoidemiaLessThan(String value) {
            addCriterion("is_hyperlipoidemia <", value, "isHyperlipoidemia");
            return (Criteria) this;
        }

        public Criteria andIsHyperlipoidemiaLessThanOrEqualTo(String value) {
            addCriterion("is_hyperlipoidemia <=", value, "isHyperlipoidemia");
            return (Criteria) this;
        }

        public Criteria andIsHyperlipoidemiaLike(String value) {
            addCriterion("is_hyperlipoidemia like", value, "isHyperlipoidemia");
            return (Criteria) this;
        }

        public Criteria andIsHyperlipoidemiaNotLike(String value) {
            addCriterion("is_hyperlipoidemia not like", value, "isHyperlipoidemia");
            return (Criteria) this;
        }

        public Criteria andIsHyperlipoidemiaIn(List<String> values) {
            addCriterion("is_hyperlipoidemia in", values, "isHyperlipoidemia");
            return (Criteria) this;
        }

        public Criteria andIsHyperlipoidemiaNotIn(List<String> values) {
            addCriterion("is_hyperlipoidemia not in", values, "isHyperlipoidemia");
            return (Criteria) this;
        }

        public Criteria andIsHyperlipoidemiaBetween(String value1, String value2) {
            addCriterion("is_hyperlipoidemia between", value1, value2, "isHyperlipoidemia");
            return (Criteria) this;
        }

        public Criteria andIsHyperlipoidemiaNotBetween(String value1, String value2) {
            addCriterion("is_hyperlipoidemia not between", value1, value2, "isHyperlipoidemia");
            return (Criteria) this;
        }

        public Criteria andIsDermatosisIsNull() {
            addCriterion("is_dermatosis is null");
            return (Criteria) this;
        }

        public Criteria andIsDermatosisIsNotNull() {
            addCriterion("is_dermatosis is not null");
            return (Criteria) this;
        }

        public Criteria andIsDermatosisEqualTo(String value) {
            addCriterion("is_dermatosis =", value, "isDermatosis");
            return (Criteria) this;
        }

        public Criteria andIsDermatosisNotEqualTo(String value) {
            addCriterion("is_dermatosis <>", value, "isDermatosis");
            return (Criteria) this;
        }

        public Criteria andIsDermatosisGreaterThan(String value) {
            addCriterion("is_dermatosis >", value, "isDermatosis");
            return (Criteria) this;
        }

        public Criteria andIsDermatosisGreaterThanOrEqualTo(String value) {
            addCriterion("is_dermatosis >=", value, "isDermatosis");
            return (Criteria) this;
        }

        public Criteria andIsDermatosisLessThan(String value) {
            addCriterion("is_dermatosis <", value, "isDermatosis");
            return (Criteria) this;
        }

        public Criteria andIsDermatosisLessThanOrEqualTo(String value) {
            addCriterion("is_dermatosis <=", value, "isDermatosis");
            return (Criteria) this;
        }

        public Criteria andIsDermatosisLike(String value) {
            addCriterion("is_dermatosis like", value, "isDermatosis");
            return (Criteria) this;
        }

        public Criteria andIsDermatosisNotLike(String value) {
            addCriterion("is_dermatosis not like", value, "isDermatosis");
            return (Criteria) this;
        }

        public Criteria andIsDermatosisIn(List<String> values) {
            addCriterion("is_dermatosis in", values, "isDermatosis");
            return (Criteria) this;
        }

        public Criteria andIsDermatosisNotIn(List<String> values) {
            addCriterion("is_dermatosis not in", values, "isDermatosis");
            return (Criteria) this;
        }

        public Criteria andIsDermatosisBetween(String value1, String value2) {
            addCriterion("is_dermatosis between", value1, value2, "isDermatosis");
            return (Criteria) this;
        }

        public Criteria andIsDermatosisNotBetween(String value1, String value2) {
            addCriterion("is_dermatosis not between", value1, value2, "isDermatosis");
            return (Criteria) this;
        }

        public Criteria andFatherDiseaseIsNull() {
            addCriterion("father_disease is null");
            return (Criteria) this;
        }

        public Criteria andFatherDiseaseIsNotNull() {
            addCriterion("father_disease is not null");
            return (Criteria) this;
        }

        public Criteria andFatherDiseaseEqualTo(String value) {
            addCriterion("father_disease =", value, "fatherDisease");
            return (Criteria) this;
        }

        public Criteria andFatherDiseaseNotEqualTo(String value) {
            addCriterion("father_disease <>", value, "fatherDisease");
            return (Criteria) this;
        }

        public Criteria andFatherDiseaseGreaterThan(String value) {
            addCriterion("father_disease >", value, "fatherDisease");
            return (Criteria) this;
        }

        public Criteria andFatherDiseaseGreaterThanOrEqualTo(String value) {
            addCriterion("father_disease >=", value, "fatherDisease");
            return (Criteria) this;
        }

        public Criteria andFatherDiseaseLessThan(String value) {
            addCriterion("father_disease <", value, "fatherDisease");
            return (Criteria) this;
        }

        public Criteria andFatherDiseaseLessThanOrEqualTo(String value) {
            addCriterion("father_disease <=", value, "fatherDisease");
            return (Criteria) this;
        }

        public Criteria andFatherDiseaseLike(String value) {
            addCriterion("father_disease like", value, "fatherDisease");
            return (Criteria) this;
        }

        public Criteria andFatherDiseaseNotLike(String value) {
            addCriterion("father_disease not like", value, "fatherDisease");
            return (Criteria) this;
        }

        public Criteria andFatherDiseaseIn(List<String> values) {
            addCriterion("father_disease in", values, "fatherDisease");
            return (Criteria) this;
        }

        public Criteria andFatherDiseaseNotIn(List<String> values) {
            addCriterion("father_disease not in", values, "fatherDisease");
            return (Criteria) this;
        }

        public Criteria andFatherDiseaseBetween(String value1, String value2) {
            addCriterion("father_disease between", value1, value2, "fatherDisease");
            return (Criteria) this;
        }

        public Criteria andFatherDiseaseNotBetween(String value1, String value2) {
            addCriterion("father_disease not between", value1, value2, "fatherDisease");
            return (Criteria) this;
        }

        public Criteria andMotherDiseaseIsNull() {
            addCriterion("mother_disease is null");
            return (Criteria) this;
        }

        public Criteria andMotherDiseaseIsNotNull() {
            addCriterion("mother_disease is not null");
            return (Criteria) this;
        }

        public Criteria andMotherDiseaseEqualTo(String value) {
            addCriterion("mother_disease =", value, "motherDisease");
            return (Criteria) this;
        }

        public Criteria andMotherDiseaseNotEqualTo(String value) {
            addCriterion("mother_disease <>", value, "motherDisease");
            return (Criteria) this;
        }

        public Criteria andMotherDiseaseGreaterThan(String value) {
            addCriterion("mother_disease >", value, "motherDisease");
            return (Criteria) this;
        }

        public Criteria andMotherDiseaseGreaterThanOrEqualTo(String value) {
            addCriterion("mother_disease >=", value, "motherDisease");
            return (Criteria) this;
        }

        public Criteria andMotherDiseaseLessThan(String value) {
            addCriterion("mother_disease <", value, "motherDisease");
            return (Criteria) this;
        }

        public Criteria andMotherDiseaseLessThanOrEqualTo(String value) {
            addCriterion("mother_disease <=", value, "motherDisease");
            return (Criteria) this;
        }

        public Criteria andMotherDiseaseLike(String value) {
            addCriterion("mother_disease like", value, "motherDisease");
            return (Criteria) this;
        }

        public Criteria andMotherDiseaseNotLike(String value) {
            addCriterion("mother_disease not like", value, "motherDisease");
            return (Criteria) this;
        }

        public Criteria andMotherDiseaseIn(List<String> values) {
            addCriterion("mother_disease in", values, "motherDisease");
            return (Criteria) this;
        }

        public Criteria andMotherDiseaseNotIn(List<String> values) {
            addCriterion("mother_disease not in", values, "motherDisease");
            return (Criteria) this;
        }

        public Criteria andMotherDiseaseBetween(String value1, String value2) {
            addCriterion("mother_disease between", value1, value2, "motherDisease");
            return (Criteria) this;
        }

        public Criteria andMotherDiseaseNotBetween(String value1, String value2) {
            addCriterion("mother_disease not between", value1, value2, "motherDisease");
            return (Criteria) this;
        }

        public Criteria andSiblingDiseaseIsNull() {
            addCriterion("sibling_disease is null");
            return (Criteria) this;
        }

        public Criteria andSiblingDiseaseIsNotNull() {
            addCriterion("sibling_disease is not null");
            return (Criteria) this;
        }

        public Criteria andSiblingDiseaseEqualTo(String value) {
            addCriterion("sibling_disease =", value, "siblingDisease");
            return (Criteria) this;
        }

        public Criteria andSiblingDiseaseNotEqualTo(String value) {
            addCriterion("sibling_disease <>", value, "siblingDisease");
            return (Criteria) this;
        }

        public Criteria andSiblingDiseaseGreaterThan(String value) {
            addCriterion("sibling_disease >", value, "siblingDisease");
            return (Criteria) this;
        }

        public Criteria andSiblingDiseaseGreaterThanOrEqualTo(String value) {
            addCriterion("sibling_disease >=", value, "siblingDisease");
            return (Criteria) this;
        }

        public Criteria andSiblingDiseaseLessThan(String value) {
            addCriterion("sibling_disease <", value, "siblingDisease");
            return (Criteria) this;
        }

        public Criteria andSiblingDiseaseLessThanOrEqualTo(String value) {
            addCriterion("sibling_disease <=", value, "siblingDisease");
            return (Criteria) this;
        }

        public Criteria andSiblingDiseaseLike(String value) {
            addCriterion("sibling_disease like", value, "siblingDisease");
            return (Criteria) this;
        }

        public Criteria andSiblingDiseaseNotLike(String value) {
            addCriterion("sibling_disease not like", value, "siblingDisease");
            return (Criteria) this;
        }

        public Criteria andSiblingDiseaseIn(List<String> values) {
            addCriterion("sibling_disease in", values, "siblingDisease");
            return (Criteria) this;
        }

        public Criteria andSiblingDiseaseNotIn(List<String> values) {
            addCriterion("sibling_disease not in", values, "siblingDisease");
            return (Criteria) this;
        }

        public Criteria andSiblingDiseaseBetween(String value1, String value2) {
            addCriterion("sibling_disease between", value1, value2, "siblingDisease");
            return (Criteria) this;
        }

        public Criteria andSiblingDiseaseNotBetween(String value1, String value2) {
            addCriterion("sibling_disease not between", value1, value2, "siblingDisease");
            return (Criteria) this;
        }

        public Criteria andChildrenDiseaseIsNull() {
            addCriterion("children_disease is null");
            return (Criteria) this;
        }

        public Criteria andChildrenDiseaseIsNotNull() {
            addCriterion("children_disease is not null");
            return (Criteria) this;
        }

        public Criteria andChildrenDiseaseEqualTo(String value) {
            addCriterion("children_disease =", value, "childrenDisease");
            return (Criteria) this;
        }

        public Criteria andChildrenDiseaseNotEqualTo(String value) {
            addCriterion("children_disease <>", value, "childrenDisease");
            return (Criteria) this;
        }

        public Criteria andChildrenDiseaseGreaterThan(String value) {
            addCriterion("children_disease >", value, "childrenDisease");
            return (Criteria) this;
        }

        public Criteria andChildrenDiseaseGreaterThanOrEqualTo(String value) {
            addCriterion("children_disease >=", value, "childrenDisease");
            return (Criteria) this;
        }

        public Criteria andChildrenDiseaseLessThan(String value) {
            addCriterion("children_disease <", value, "childrenDisease");
            return (Criteria) this;
        }

        public Criteria andChildrenDiseaseLessThanOrEqualTo(String value) {
            addCriterion("children_disease <=", value, "childrenDisease");
            return (Criteria) this;
        }

        public Criteria andChildrenDiseaseLike(String value) {
            addCriterion("children_disease like", value, "childrenDisease");
            return (Criteria) this;
        }

        public Criteria andChildrenDiseaseNotLike(String value) {
            addCriterion("children_disease not like", value, "childrenDisease");
            return (Criteria) this;
        }

        public Criteria andChildrenDiseaseIn(List<String> values) {
            addCriterion("children_disease in", values, "childrenDisease");
            return (Criteria) this;
        }

        public Criteria andChildrenDiseaseNotIn(List<String> values) {
            addCriterion("children_disease not in", values, "childrenDisease");
            return (Criteria) this;
        }

        public Criteria andChildrenDiseaseBetween(String value1, String value2) {
            addCriterion("children_disease between", value1, value2, "childrenDisease");
            return (Criteria) this;
        }

        public Criteria andChildrenDiseaseNotBetween(String value1, String value2) {
            addCriterion("children_disease not between", value1, value2, "childrenDisease");
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