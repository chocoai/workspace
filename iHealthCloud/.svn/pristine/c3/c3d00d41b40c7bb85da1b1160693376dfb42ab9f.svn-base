package com.yhcrt.iHealthCloud.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class GoodsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GoodsExample() {
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

        public Criteria andGoodsIdIsNull() {
            addCriterion("goods_id is null");
            return (Criteria) this;
        }

        public Criteria andGoodsIdIsNotNull() {
            addCriterion("goods_id is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsIdEqualTo(Integer value) {
            addCriterion("goods_id =", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdNotEqualTo(Integer value) {
            addCriterion("goods_id <>", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdGreaterThan(Integer value) {
            addCriterion("goods_id >", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("goods_id >=", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdLessThan(Integer value) {
            addCriterion("goods_id <", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdLessThanOrEqualTo(Integer value) {
            addCriterion("goods_id <=", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdIn(List<Integer> values) {
            addCriterion("goods_id in", values, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdNotIn(List<Integer> values) {
            addCriterion("goods_id not in", values, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdBetween(Integer value1, Integer value2) {
            addCriterion("goods_id between", value1, value2, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdNotBetween(Integer value1, Integer value2) {
            addCriterion("goods_id not between", value1, value2, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsCategoryIsNull() {
            addCriterion("goods_category is null");
            return (Criteria) this;
        }

        public Criteria andGoodsCategoryIsNotNull() {
            addCriterion("goods_category is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsCategoryEqualTo(String value) {
            addCriterion("goods_category =", value, "goodsCategory");
            return (Criteria) this;
        }

        public Criteria andGoodsCategoryNotEqualTo(String value) {
            addCriterion("goods_category <>", value, "goodsCategory");
            return (Criteria) this;
        }

        public Criteria andGoodsCategoryGreaterThan(String value) {
            addCriterion("goods_category >", value, "goodsCategory");
            return (Criteria) this;
        }

        public Criteria andGoodsCategoryGreaterThanOrEqualTo(String value) {
            addCriterion("goods_category >=", value, "goodsCategory");
            return (Criteria) this;
        }

        public Criteria andGoodsCategoryLessThan(String value) {
            addCriterion("goods_category <", value, "goodsCategory");
            return (Criteria) this;
        }

        public Criteria andGoodsCategoryLessThanOrEqualTo(String value) {
            addCriterion("goods_category <=", value, "goodsCategory");
            return (Criteria) this;
        }

        public Criteria andGoodsCategoryLike(String value) {
            addCriterion("goods_category like", value, "goodsCategory");
            return (Criteria) this;
        }

        public Criteria andGoodsCategoryNotLike(String value) {
            addCriterion("goods_category not like", value, "goodsCategory");
            return (Criteria) this;
        }

        public Criteria andGoodsCategoryIn(List<String> values) {
            addCriterion("goods_category in", values, "goodsCategory");
            return (Criteria) this;
        }

        public Criteria andGoodsCategoryNotIn(List<String> values) {
            addCriterion("goods_category not in", values, "goodsCategory");
            return (Criteria) this;
        }

        public Criteria andGoodsCategoryBetween(String value1, String value2) {
            addCriterion("goods_category between", value1, value2, "goodsCategory");
            return (Criteria) this;
        }

        public Criteria andGoodsCategoryNotBetween(String value1, String value2) {
            addCriterion("goods_category not between", value1, value2, "goodsCategory");
            return (Criteria) this;
        }
        
        public Criteria andGoodsTypeEqualTo(String value) {
            addCriterion("goods_type =", value, "goodsType");
            return (Criteria) this;
        }
        
        public Criteria andGoodsNameIsNull() {
            addCriterion("goods_name is null");
            return (Criteria) this;
        }

        public Criteria andGoodsNameIsNotNull() {
            addCriterion("goods_name is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsNameEqualTo(String value) {
            addCriterion("goods_name =", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameNotEqualTo(String value) {
            addCriterion("goods_name <>", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameGreaterThan(String value) {
            addCriterion("goods_name >", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameGreaterThanOrEqualTo(String value) {
            addCriterion("goods_name >=", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameLessThan(String value) {
            addCriterion("goods_name <", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameLessThanOrEqualTo(String value) {
            addCriterion("goods_name <=", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameLike(String value) {
            addCriterion("goods_name like", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameNotLike(String value) {
            addCriterion("goods_name not like", value, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameIn(List<String> values) {
            addCriterion("goods_name in", values, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameNotIn(List<String> values) {
            addCriterion("goods_name not in", values, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameBetween(String value1, String value2) {
            addCriterion("goods_name between", value1, value2, "goodsName");
            return (Criteria) this;
        }

        public Criteria andGoodsNameNotBetween(String value1, String value2) {
            addCriterion("goods_name not between", value1, value2, "goodsName");
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

        public Criteria andGoodsIntroIsNull() {
            addCriterion("goods_intro is null");
            return (Criteria) this;
        }

        public Criteria andGoodsIntroIsNotNull() {
            addCriterion("goods_intro is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsIntroEqualTo(String value) {
            addCriterion("goods_intro =", value, "goodsIntro");
            return (Criteria) this;
        }

        public Criteria andGoodsIntroNotEqualTo(String value) {
            addCriterion("goods_intro <>", value, "goodsIntro");
            return (Criteria) this;
        }

        public Criteria andGoodsIntroGreaterThan(String value) {
            addCriterion("goods_intro >", value, "goodsIntro");
            return (Criteria) this;
        }

        public Criteria andGoodsIntroGreaterThanOrEqualTo(String value) {
            addCriterion("goods_intro >=", value, "goodsIntro");
            return (Criteria) this;
        }

        public Criteria andGoodsIntroLessThan(String value) {
            addCriterion("goods_intro <", value, "goodsIntro");
            return (Criteria) this;
        }

        public Criteria andGoodsIntroLessThanOrEqualTo(String value) {
            addCriterion("goods_intro <=", value, "goodsIntro");
            return (Criteria) this;
        }

        public Criteria andGoodsIntroLike(String value) {
            addCriterion("goods_intro like", value, "goodsIntro");
            return (Criteria) this;
        }

        public Criteria andGoodsIntroNotLike(String value) {
            addCriterion("goods_intro not like", value, "goodsIntro");
            return (Criteria) this;
        }

        public Criteria andGoodsIntroIn(List<String> values) {
            addCriterion("goods_intro in", values, "goodsIntro");
            return (Criteria) this;
        }

        public Criteria andGoodsIntroNotIn(List<String> values) {
            addCriterion("goods_intro not in", values, "goodsIntro");
            return (Criteria) this;
        }

        public Criteria andGoodsIntroBetween(String value1, String value2) {
            addCriterion("goods_intro between", value1, value2, "goodsIntro");
            return (Criteria) this;
        }

        public Criteria andGoodsIntroNotBetween(String value1, String value2) {
            addCriterion("goods_intro not between", value1, value2, "goodsIntro");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceIsNull() {
            addCriterion("original_price is null");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceIsNotNull() {
            addCriterion("original_price is not null");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceEqualTo(Double value) {
            addCriterion("original_price =", value, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceNotEqualTo(Double value) {
            addCriterion("original_price <>", value, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceGreaterThan(Double value) {
            addCriterion("original_price >", value, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceGreaterThanOrEqualTo(Double value) {
            addCriterion("original_price >=", value, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceLessThan(Double value) {
            addCriterion("original_price <", value, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceLessThanOrEqualTo(Double value) {
            addCriterion("original_price <=", value, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceIn(List<Double> values) {
            addCriterion("original_price in", values, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceNotIn(List<Double> values) {
            addCriterion("original_price not in", values, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceBetween(Double value1, Double value2) {
            addCriterion("original_price between", value1, value2, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceNotBetween(Double value1, Double value2) {
            addCriterion("original_price not between", value1, value2, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceIsNull() {
            addCriterion("unit_price is null");
            return (Criteria) this;
        }

        public Criteria andUnitPriceIsNotNull() {
            addCriterion("unit_price is not null");
            return (Criteria) this;
        }

        public Criteria andUnitPriceEqualTo(Double value) {
            addCriterion("unit_price =", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceNotEqualTo(Double value) {
            addCriterion("unit_price <>", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceGreaterThan(Double value) {
            addCriterion("unit_price >", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceGreaterThanOrEqualTo(Double value) {
            addCriterion("unit_price >=", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceLessThan(Double value) {
            addCriterion("unit_price <", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceLessThanOrEqualTo(Double value) {
            addCriterion("unit_price <=", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceIn(List<Double> values) {
            addCriterion("unit_price in", values, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceNotIn(List<Double> values) {
            addCriterion("unit_price not in", values, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceBetween(Double value1, Double value2) {
            addCriterion("unit_price between", value1, value2, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceNotBetween(Double value1, Double value2) {
            addCriterion("unit_price not between", value1, value2, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andDiscountIsNull() {
            addCriterion("discount is null");
            return (Criteria) this;
        }

        public Criteria andDiscountIsNotNull() {
            addCriterion("discount is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountEqualTo(Double value) {
            addCriterion("discount =", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountNotEqualTo(Double value) {
            addCriterion("discount <>", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountGreaterThan(Double value) {
            addCriterion("discount >", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountGreaterThanOrEqualTo(Double value) {
            addCriterion("discount >=", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountLessThan(Double value) {
            addCriterion("discount <", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountLessThanOrEqualTo(Double value) {
            addCriterion("discount <=", value, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountIn(List<Double> values) {
            addCriterion("discount in", values, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountNotIn(List<Double> values) {
            addCriterion("discount not in", values, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountBetween(Double value1, Double value2) {
            addCriterion("discount between", value1, value2, "discount");
            return (Criteria) this;
        }

        public Criteria andDiscountNotBetween(Double value1, Double value2) {
            addCriterion("discount not between", value1, value2, "discount");
            return (Criteria) this;
        }

        public Criteria andUnitIsNull() {
            addCriterion("unit is null");
            return (Criteria) this;
        }

        public Criteria andUnitIsNotNull() {
            addCriterion("unit is not null");
            return (Criteria) this;
        }

        public Criteria andUnitEqualTo(String value) {
            addCriterion("unit =", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotEqualTo(String value) {
            addCriterion("unit <>", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitGreaterThan(String value) {
            addCriterion("unit >", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitGreaterThanOrEqualTo(String value) {
            addCriterion("unit >=", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLessThan(String value) {
            addCriterion("unit <", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLessThanOrEqualTo(String value) {
            addCriterion("unit <=", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLike(String value) {
            addCriterion("unit like", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotLike(String value) {
            addCriterion("unit not like", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitIn(List<String> values) {
            addCriterion("unit in", values, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotIn(List<String> values) {
            addCriterion("unit not in", values, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitBetween(String value1, String value2) {
            addCriterion("unit between", value1, value2, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotBetween(String value1, String value2) {
            addCriterion("unit not between", value1, value2, "unit");
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

        public Criteria andStockAmountIsNull() {
            addCriterion("stock_amount is null");
            return (Criteria) this;
        }

        public Criteria andStockAmountIsNotNull() {
            addCriterion("stock_amount is not null");
            return (Criteria) this;
        }

        public Criteria andStockAmountEqualTo(Integer value) {
            addCriterion("stock_amount =", value, "stockAmount");
            return (Criteria) this;
        }

        public Criteria andStockAmountNotEqualTo(Integer value) {
            addCriterion("stock_amount <>", value, "stockAmount");
            return (Criteria) this;
        }

        public Criteria andStockAmountGreaterThan(Integer value) {
            addCriterion("stock_amount >", value, "stockAmount");
            return (Criteria) this;
        }

        public Criteria andStockAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("stock_amount >=", value, "stockAmount");
            return (Criteria) this;
        }

        public Criteria andStockAmountLessThan(Integer value) {
            addCriterion("stock_amount <", value, "stockAmount");
            return (Criteria) this;
        }

        public Criteria andStockAmountLessThanOrEqualTo(Integer value) {
            addCriterion("stock_amount <=", value, "stockAmount");
            return (Criteria) this;
        }

        public Criteria andStockAmountIn(List<Integer> values) {
            addCriterion("stock_amount in", values, "stockAmount");
            return (Criteria) this;
        }

        public Criteria andStockAmountNotIn(List<Integer> values) {
            addCriterion("stock_amount not in", values, "stockAmount");
            return (Criteria) this;
        }

        public Criteria andStockAmountBetween(Integer value1, Integer value2) {
            addCriterion("stock_amount between", value1, value2, "stockAmount");
            return (Criteria) this;
        }

        public Criteria andStockAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("stock_amount not between", value1, value2, "stockAmount");
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

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(String value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(String value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(String value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(String value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(String value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(String value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLike(String value) {
            addCriterion("update_time like", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotLike(String value) {
            addCriterion("update_time not like", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<String> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<String> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(String value1, String value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(String value1, String value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
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