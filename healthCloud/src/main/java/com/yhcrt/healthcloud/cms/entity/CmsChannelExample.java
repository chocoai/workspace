package com.yhcrt.healthcloud.cms.entity;

import java.util.ArrayList;
import java.util.List;

public class CmsChannelExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CmsChannelExample() {
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

        public Criteria andChannelIdIsNull() {
            addCriterion("channel_id is null");
            return (Criteria) this;
        }

        public Criteria andChannelIdIsNotNull() {
            addCriterion("channel_id is not null");
            return (Criteria) this;
        }

        public Criteria andChannelIdEqualTo(Integer value) {
            addCriterion("channel_id =", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdNotEqualTo(Integer value) {
            addCriterion("channel_id <>", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdGreaterThan(Integer value) {
            addCriterion("channel_id >", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("channel_id >=", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdLessThan(Integer value) {
            addCriterion("channel_id <", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdLessThanOrEqualTo(Integer value) {
            addCriterion("channel_id <=", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdIn(List<Integer> values) {
            addCriterion("channel_id in", values, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdNotIn(List<Integer> values) {
            addCriterion("channel_id not in", values, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdBetween(Integer value1, Integer value2) {
            addCriterion("channel_id between", value1, value2, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdNotBetween(Integer value1, Integer value2) {
            addCriterion("channel_id not between", value1, value2, "channelId");
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

        public Criteria andParentIdEqualTo(Integer value) {
            addCriterion("parent_id =", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotEqualTo(Integer value) {
            addCriterion("parent_id <>", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThan(Integer value) {
            addCriterion("parent_id >", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("parent_id >=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThan(Integer value) {
            addCriterion("parent_id <", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThanOrEqualTo(Integer value) {
            addCriterion("parent_id <=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdIn(List<Integer> values) {
            addCriterion("parent_id in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotIn(List<Integer> values) {
            addCriterion("parent_id not in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdBetween(Integer value1, Integer value2) {
            addCriterion("parent_id between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("parent_id not between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andChannelNameIsNull() {
            addCriterion("channel_name is null");
            return (Criteria) this;
        }

        public Criteria andChannelNameIsNotNull() {
            addCriterion("channel_name is not null");
            return (Criteria) this;
        }

        public Criteria andChannelNameEqualTo(String value) {
            addCriterion("channel_name =", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameNotEqualTo(String value) {
            addCriterion("channel_name <>", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameGreaterThan(String value) {
            addCriterion("channel_name >", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameGreaterThanOrEqualTo(String value) {
            addCriterion("channel_name >=", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameLessThan(String value) {
            addCriterion("channel_name <", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameLessThanOrEqualTo(String value) {
            addCriterion("channel_name <=", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameLike(String value) {
            addCriterion("channel_name like", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameNotLike(String value) {
            addCriterion("channel_name not like", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameIn(List<String> values) {
            addCriterion("channel_name in", values, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameNotIn(List<String> values) {
            addCriterion("channel_name not in", values, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameBetween(String value1, String value2) {
            addCriterion("channel_name between", value1, value2, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameNotBetween(String value1, String value2) {
            addCriterion("channel_name not between", value1, value2, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelDescIsNull() {
            addCriterion("channel_desc is null");
            return (Criteria) this;
        }

        public Criteria andChannelDescIsNotNull() {
            addCriterion("channel_desc is not null");
            return (Criteria) this;
        }

        public Criteria andChannelDescEqualTo(String value) {
            addCriterion("channel_desc =", value, "channelDesc");
            return (Criteria) this;
        }

        public Criteria andChannelDescNotEqualTo(String value) {
            addCriterion("channel_desc <>", value, "channelDesc");
            return (Criteria) this;
        }

        public Criteria andChannelDescGreaterThan(String value) {
            addCriterion("channel_desc >", value, "channelDesc");
            return (Criteria) this;
        }

        public Criteria andChannelDescGreaterThanOrEqualTo(String value) {
            addCriterion("channel_desc >=", value, "channelDesc");
            return (Criteria) this;
        }

        public Criteria andChannelDescLessThan(String value) {
            addCriterion("channel_desc <", value, "channelDesc");
            return (Criteria) this;
        }

        public Criteria andChannelDescLessThanOrEqualTo(String value) {
            addCriterion("channel_desc <=", value, "channelDesc");
            return (Criteria) this;
        }

        public Criteria andChannelDescLike(String value) {
            addCriterion("channel_desc like", value, "channelDesc");
            return (Criteria) this;
        }

        public Criteria andChannelDescNotLike(String value) {
            addCriterion("channel_desc not like", value, "channelDesc");
            return (Criteria) this;
        }

        public Criteria andChannelDescIn(List<String> values) {
            addCriterion("channel_desc in", values, "channelDesc");
            return (Criteria) this;
        }

        public Criteria andChannelDescNotIn(List<String> values) {
            addCriterion("channel_desc not in", values, "channelDesc");
            return (Criteria) this;
        }

        public Criteria andChannelDescBetween(String value1, String value2) {
            addCriterion("channel_desc between", value1, value2, "channelDesc");
            return (Criteria) this;
        }

        public Criteria andChannelDescNotBetween(String value1, String value2) {
            addCriterion("channel_desc not between", value1, value2, "channelDesc");
            return (Criteria) this;
        }

        public Criteria andChannelUrlIsNull() {
            addCriterion("channel_url is null");
            return (Criteria) this;
        }

        public Criteria andChannelUrlIsNotNull() {
            addCriterion("channel_url is not null");
            return (Criteria) this;
        }

        public Criteria andChannelUrlEqualTo(String value) {
            addCriterion("channel_url =", value, "channelUrl");
            return (Criteria) this;
        }

        public Criteria andChannelUrlNotEqualTo(String value) {
            addCriterion("channel_url <>", value, "channelUrl");
            return (Criteria) this;
        }

        public Criteria andChannelUrlGreaterThan(String value) {
            addCriterion("channel_url >", value, "channelUrl");
            return (Criteria) this;
        }

        public Criteria andChannelUrlGreaterThanOrEqualTo(String value) {
            addCriterion("channel_url >=", value, "channelUrl");
            return (Criteria) this;
        }

        public Criteria andChannelUrlLessThan(String value) {
            addCriterion("channel_url <", value, "channelUrl");
            return (Criteria) this;
        }

        public Criteria andChannelUrlLessThanOrEqualTo(String value) {
            addCriterion("channel_url <=", value, "channelUrl");
            return (Criteria) this;
        }

        public Criteria andChannelUrlLike(String value) {
            addCriterion("channel_url like", value, "channelUrl");
            return (Criteria) this;
        }

        public Criteria andChannelUrlNotLike(String value) {
            addCriterion("channel_url not like", value, "channelUrl");
            return (Criteria) this;
        }

        public Criteria andChannelUrlIn(List<String> values) {
            addCriterion("channel_url in", values, "channelUrl");
            return (Criteria) this;
        }

        public Criteria andChannelUrlNotIn(List<String> values) {
            addCriterion("channel_url not in", values, "channelUrl");
            return (Criteria) this;
        }

        public Criteria andChannelUrlBetween(String value1, String value2) {
            addCriterion("channel_url between", value1, value2, "channelUrl");
            return (Criteria) this;
        }

        public Criteria andChannelUrlNotBetween(String value1, String value2) {
            addCriterion("channel_url not between", value1, value2, "channelUrl");
            return (Criteria) this;
        }

        public Criteria andOpenTypeIsNull() {
            addCriterion("open_type is null");
            return (Criteria) this;
        }

        public Criteria andOpenTypeIsNotNull() {
            addCriterion("open_type is not null");
            return (Criteria) this;
        }

        public Criteria andOpenTypeEqualTo(String value) {
            addCriterion("open_type =", value, "openType");
            return (Criteria) this;
        }

        public Criteria andOpenTypeNotEqualTo(String value) {
            addCriterion("open_type <>", value, "openType");
            return (Criteria) this;
        }

        public Criteria andOpenTypeGreaterThan(String value) {
            addCriterion("open_type >", value, "openType");
            return (Criteria) this;
        }

        public Criteria andOpenTypeGreaterThanOrEqualTo(String value) {
            addCriterion("open_type >=", value, "openType");
            return (Criteria) this;
        }

        public Criteria andOpenTypeLessThan(String value) {
            addCriterion("open_type <", value, "openType");
            return (Criteria) this;
        }

        public Criteria andOpenTypeLessThanOrEqualTo(String value) {
            addCriterion("open_type <=", value, "openType");
            return (Criteria) this;
        }

        public Criteria andOpenTypeLike(String value) {
            addCriterion("open_type like", value, "openType");
            return (Criteria) this;
        }

        public Criteria andOpenTypeNotLike(String value) {
            addCriterion("open_type not like", value, "openType");
            return (Criteria) this;
        }

        public Criteria andOpenTypeIn(List<String> values) {
            addCriterion("open_type in", values, "openType");
            return (Criteria) this;
        }

        public Criteria andOpenTypeNotIn(List<String> values) {
            addCriterion("open_type not in", values, "openType");
            return (Criteria) this;
        }

        public Criteria andOpenTypeBetween(String value1, String value2) {
            addCriterion("open_type between", value1, value2, "openType");
            return (Criteria) this;
        }

        public Criteria andOpenTypeNotBetween(String value1, String value2) {
            addCriterion("open_type not between", value1, value2, "openType");
            return (Criteria) this;
        }

        public Criteria andTitleImgIsNull() {
            addCriterion("title_img is null");
            return (Criteria) this;
        }

        public Criteria andTitleImgIsNotNull() {
            addCriterion("title_img is not null");
            return (Criteria) this;
        }

        public Criteria andTitleImgEqualTo(String value) {
            addCriterion("title_img =", value, "titleImg");
            return (Criteria) this;
        }

        public Criteria andTitleImgNotEqualTo(String value) {
            addCriterion("title_img <>", value, "titleImg");
            return (Criteria) this;
        }

        public Criteria andTitleImgGreaterThan(String value) {
            addCriterion("title_img >", value, "titleImg");
            return (Criteria) this;
        }

        public Criteria andTitleImgGreaterThanOrEqualTo(String value) {
            addCriterion("title_img >=", value, "titleImg");
            return (Criteria) this;
        }

        public Criteria andTitleImgLessThan(String value) {
            addCriterion("title_img <", value, "titleImg");
            return (Criteria) this;
        }

        public Criteria andTitleImgLessThanOrEqualTo(String value) {
            addCriterion("title_img <=", value, "titleImg");
            return (Criteria) this;
        }

        public Criteria andTitleImgLike(String value) {
            addCriterion("title_img like", value, "titleImg");
            return (Criteria) this;
        }

        public Criteria andTitleImgNotLike(String value) {
            addCriterion("title_img not like", value, "titleImg");
            return (Criteria) this;
        }

        public Criteria andTitleImgIn(List<String> values) {
            addCriterion("title_img in", values, "titleImg");
            return (Criteria) this;
        }

        public Criteria andTitleImgNotIn(List<String> values) {
            addCriterion("title_img not in", values, "titleImg");
            return (Criteria) this;
        }

        public Criteria andTitleImgBetween(String value1, String value2) {
            addCriterion("title_img between", value1, value2, "titleImg");
            return (Criteria) this;
        }

        public Criteria andTitleImgNotBetween(String value1, String value2) {
            addCriterion("title_img not between", value1, value2, "titleImg");
            return (Criteria) this;
        }

        public Criteria andChannelTplIsNull() {
            addCriterion("channel_tpl is null");
            return (Criteria) this;
        }

        public Criteria andChannelTplIsNotNull() {
            addCriterion("channel_tpl is not null");
            return (Criteria) this;
        }

        public Criteria andChannelTplEqualTo(String value) {
            addCriterion("channel_tpl =", value, "channelTpl");
            return (Criteria) this;
        }

        public Criteria andChannelTplNotEqualTo(String value) {
            addCriterion("channel_tpl <>", value, "channelTpl");
            return (Criteria) this;
        }

        public Criteria andChannelTplGreaterThan(String value) {
            addCriterion("channel_tpl >", value, "channelTpl");
            return (Criteria) this;
        }

        public Criteria andChannelTplGreaterThanOrEqualTo(String value) {
            addCriterion("channel_tpl >=", value, "channelTpl");
            return (Criteria) this;
        }

        public Criteria andChannelTplLessThan(String value) {
            addCriterion("channel_tpl <", value, "channelTpl");
            return (Criteria) this;
        }

        public Criteria andChannelTplLessThanOrEqualTo(String value) {
            addCriterion("channel_tpl <=", value, "channelTpl");
            return (Criteria) this;
        }

        public Criteria andChannelTplLike(String value) {
            addCriterion("channel_tpl like", value, "channelTpl");
            return (Criteria) this;
        }

        public Criteria andChannelTplNotLike(String value) {
            addCriterion("channel_tpl not like", value, "channelTpl");
            return (Criteria) this;
        }

        public Criteria andChannelTplIn(List<String> values) {
            addCriterion("channel_tpl in", values, "channelTpl");
            return (Criteria) this;
        }

        public Criteria andChannelTplNotIn(List<String> values) {
            addCriterion("channel_tpl not in", values, "channelTpl");
            return (Criteria) this;
        }

        public Criteria andChannelTplBetween(String value1, String value2) {
            addCriterion("channel_tpl between", value1, value2, "channelTpl");
            return (Criteria) this;
        }

        public Criteria andChannelTplNotBetween(String value1, String value2) {
            addCriterion("channel_tpl not between", value1, value2, "channelTpl");
            return (Criteria) this;
        }

        public Criteria andChannelMobileTplIsNull() {
            addCriterion("channel_mobile_tpl is null");
            return (Criteria) this;
        }

        public Criteria andChannelMobileTplIsNotNull() {
            addCriterion("channel_mobile_tpl is not null");
            return (Criteria) this;
        }

        public Criteria andChannelMobileTplEqualTo(String value) {
            addCriterion("channel_mobile_tpl =", value, "channelMobileTpl");
            return (Criteria) this;
        }

        public Criteria andChannelMobileTplNotEqualTo(String value) {
            addCriterion("channel_mobile_tpl <>", value, "channelMobileTpl");
            return (Criteria) this;
        }

        public Criteria andChannelMobileTplGreaterThan(String value) {
            addCriterion("channel_mobile_tpl >", value, "channelMobileTpl");
            return (Criteria) this;
        }

        public Criteria andChannelMobileTplGreaterThanOrEqualTo(String value) {
            addCriterion("channel_mobile_tpl >=", value, "channelMobileTpl");
            return (Criteria) this;
        }

        public Criteria andChannelMobileTplLessThan(String value) {
            addCriterion("channel_mobile_tpl <", value, "channelMobileTpl");
            return (Criteria) this;
        }

        public Criteria andChannelMobileTplLessThanOrEqualTo(String value) {
            addCriterion("channel_mobile_tpl <=", value, "channelMobileTpl");
            return (Criteria) this;
        }

        public Criteria andChannelMobileTplLike(String value) {
            addCriterion("channel_mobile_tpl like", value, "channelMobileTpl");
            return (Criteria) this;
        }

        public Criteria andChannelMobileTplNotLike(String value) {
            addCriterion("channel_mobile_tpl not like", value, "channelMobileTpl");
            return (Criteria) this;
        }

        public Criteria andChannelMobileTplIn(List<String> values) {
            addCriterion("channel_mobile_tpl in", values, "channelMobileTpl");
            return (Criteria) this;
        }

        public Criteria andChannelMobileTplNotIn(List<String> values) {
            addCriterion("channel_mobile_tpl not in", values, "channelMobileTpl");
            return (Criteria) this;
        }

        public Criteria andChannelMobileTplBetween(String value1, String value2) {
            addCriterion("channel_mobile_tpl between", value1, value2, "channelMobileTpl");
            return (Criteria) this;
        }

        public Criteria andChannelMobileTplNotBetween(String value1, String value2) {
            addCriterion("channel_mobile_tpl not between", value1, value2, "channelMobileTpl");
            return (Criteria) this;
        }

        public Criteria andContentTplIsNull() {
            addCriterion("content_tpl is null");
            return (Criteria) this;
        }

        public Criteria andContentTplIsNotNull() {
            addCriterion("content_tpl is not null");
            return (Criteria) this;
        }

        public Criteria andContentTplEqualTo(String value) {
            addCriterion("content_tpl =", value, "contentTpl");
            return (Criteria) this;
        }

        public Criteria andContentTplNotEqualTo(String value) {
            addCriterion("content_tpl <>", value, "contentTpl");
            return (Criteria) this;
        }

        public Criteria andContentTplGreaterThan(String value) {
            addCriterion("content_tpl >", value, "contentTpl");
            return (Criteria) this;
        }

        public Criteria andContentTplGreaterThanOrEqualTo(String value) {
            addCriterion("content_tpl >=", value, "contentTpl");
            return (Criteria) this;
        }

        public Criteria andContentTplLessThan(String value) {
            addCriterion("content_tpl <", value, "contentTpl");
            return (Criteria) this;
        }

        public Criteria andContentTplLessThanOrEqualTo(String value) {
            addCriterion("content_tpl <=", value, "contentTpl");
            return (Criteria) this;
        }

        public Criteria andContentTplLike(String value) {
            addCriterion("content_tpl like", value, "contentTpl");
            return (Criteria) this;
        }

        public Criteria andContentTplNotLike(String value) {
            addCriterion("content_tpl not like", value, "contentTpl");
            return (Criteria) this;
        }

        public Criteria andContentTplIn(List<String> values) {
            addCriterion("content_tpl in", values, "contentTpl");
            return (Criteria) this;
        }

        public Criteria andContentTplNotIn(List<String> values) {
            addCriterion("content_tpl not in", values, "contentTpl");
            return (Criteria) this;
        }

        public Criteria andContentTplBetween(String value1, String value2) {
            addCriterion("content_tpl between", value1, value2, "contentTpl");
            return (Criteria) this;
        }

        public Criteria andContentTplNotBetween(String value1, String value2) {
            addCriterion("content_tpl not between", value1, value2, "contentTpl");
            return (Criteria) this;
        }

        public Criteria andContentMobileTplIsNull() {
            addCriterion("content_mobile_tpl is null");
            return (Criteria) this;
        }

        public Criteria andContentMobileTplIsNotNull() {
            addCriterion("content_mobile_tpl is not null");
            return (Criteria) this;
        }

        public Criteria andContentMobileTplEqualTo(String value) {
            addCriterion("content_mobile_tpl =", value, "contentMobileTpl");
            return (Criteria) this;
        }

        public Criteria andContentMobileTplNotEqualTo(String value) {
            addCriterion("content_mobile_tpl <>", value, "contentMobileTpl");
            return (Criteria) this;
        }

        public Criteria andContentMobileTplGreaterThan(String value) {
            addCriterion("content_mobile_tpl >", value, "contentMobileTpl");
            return (Criteria) this;
        }

        public Criteria andContentMobileTplGreaterThanOrEqualTo(String value) {
            addCriterion("content_mobile_tpl >=", value, "contentMobileTpl");
            return (Criteria) this;
        }

        public Criteria andContentMobileTplLessThan(String value) {
            addCriterion("content_mobile_tpl <", value, "contentMobileTpl");
            return (Criteria) this;
        }

        public Criteria andContentMobileTplLessThanOrEqualTo(String value) {
            addCriterion("content_mobile_tpl <=", value, "contentMobileTpl");
            return (Criteria) this;
        }

        public Criteria andContentMobileTplLike(String value) {
            addCriterion("content_mobile_tpl like", value, "contentMobileTpl");
            return (Criteria) this;
        }

        public Criteria andContentMobileTplNotLike(String value) {
            addCriterion("content_mobile_tpl not like", value, "contentMobileTpl");
            return (Criteria) this;
        }

        public Criteria andContentMobileTplIn(List<String> values) {
            addCriterion("content_mobile_tpl in", values, "contentMobileTpl");
            return (Criteria) this;
        }

        public Criteria andContentMobileTplNotIn(List<String> values) {
            addCriterion("content_mobile_tpl not in", values, "contentMobileTpl");
            return (Criteria) this;
        }

        public Criteria andContentMobileTplBetween(String value1, String value2) {
            addCriterion("content_mobile_tpl between", value1, value2, "contentMobileTpl");
            return (Criteria) this;
        }

        public Criteria andContentMobileTplNotBetween(String value1, String value2) {
            addCriterion("content_mobile_tpl not between", value1, value2, "contentMobileTpl");
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

        public Criteria andAllowCommentIsNull() {
            addCriterion("allow_comment is null");
            return (Criteria) this;
        }

        public Criteria andAllowCommentIsNotNull() {
            addCriterion("allow_comment is not null");
            return (Criteria) this;
        }

        public Criteria andAllowCommentEqualTo(String value) {
            addCriterion("allow_comment =", value, "allowComment");
            return (Criteria) this;
        }

        public Criteria andAllowCommentNotEqualTo(String value) {
            addCriterion("allow_comment <>", value, "allowComment");
            return (Criteria) this;
        }

        public Criteria andAllowCommentGreaterThan(String value) {
            addCriterion("allow_comment >", value, "allowComment");
            return (Criteria) this;
        }

        public Criteria andAllowCommentGreaterThanOrEqualTo(String value) {
            addCriterion("allow_comment >=", value, "allowComment");
            return (Criteria) this;
        }

        public Criteria andAllowCommentLessThan(String value) {
            addCriterion("allow_comment <", value, "allowComment");
            return (Criteria) this;
        }

        public Criteria andAllowCommentLessThanOrEqualTo(String value) {
            addCriterion("allow_comment <=", value, "allowComment");
            return (Criteria) this;
        }

        public Criteria andAllowCommentLike(String value) {
            addCriterion("allow_comment like", value, "allowComment");
            return (Criteria) this;
        }

        public Criteria andAllowCommentNotLike(String value) {
            addCriterion("allow_comment not like", value, "allowComment");
            return (Criteria) this;
        }

        public Criteria andAllowCommentIn(List<String> values) {
            addCriterion("allow_comment in", values, "allowComment");
            return (Criteria) this;
        }

        public Criteria andAllowCommentNotIn(List<String> values) {
            addCriterion("allow_comment not in", values, "allowComment");
            return (Criteria) this;
        }

        public Criteria andAllowCommentBetween(String value1, String value2) {
            addCriterion("allow_comment between", value1, value2, "allowComment");
            return (Criteria) this;
        }

        public Criteria andAllowCommentNotBetween(String value1, String value2) {
            addCriterion("allow_comment not between", value1, value2, "allowComment");
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