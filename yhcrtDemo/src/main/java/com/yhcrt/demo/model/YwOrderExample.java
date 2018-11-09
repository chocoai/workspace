package com.yhcrt.demo.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class YwOrderExample {
    /**
     * yw_order
     */
    protected String orderByClause;

    /**
     * yw_order
     */
    protected boolean distinct;

    /**
     * yw_order
     */
    protected List<Criteria> oredCriteria;

    /**
     *
     * @mbg.generated 2018-03-22
     */
    public YwOrderExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     *
     * @mbg.generated 2018-03-22
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     *
     * @mbg.generated 2018-03-22
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     *
     * @mbg.generated 2018-03-22
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     *
     * @mbg.generated 2018-03-22
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     *
     * @mbg.generated 2018-03-22
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     *
     * @mbg.generated 2018-03-22
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     *
     * @mbg.generated 2018-03-22
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     *
     * @mbg.generated 2018-03-22
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
     * @mbg.generated 2018-03-22
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     *
     * @mbg.generated 2018-03-22
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * yw_order 2018-03-22
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

        public Criteria andOrderIdIsNull() {
            addCriterion("order_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("order_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(Integer value) {
            addCriterion("order_id =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(Integer value) {
            addCriterion("order_id <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(Integer value) {
            addCriterion("order_id >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_id >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(Integer value) {
            addCriterion("order_id <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(Integer value) {
            addCriterion("order_id <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<Integer> values) {
            addCriterion("order_id in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<Integer> values) {
            addCriterion("order_id not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(Integer value1, Integer value2) {
            addCriterion("order_id between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(Integer value1, Integer value2) {
            addCriterion("order_id not between", value1, value2, "orderId");
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

        public Criteria andOrderAmountIsNull() {
            addCriterion("order_amount is null");
            return (Criteria) this;
        }

        public Criteria andOrderAmountIsNotNull() {
            addCriterion("order_amount is not null");
            return (Criteria) this;
        }

        public Criteria andOrderAmountEqualTo(Double value) {
            addCriterion("order_amount =", value, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountNotEqualTo(Double value) {
            addCriterion("order_amount <>", value, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountGreaterThan(Double value) {
            addCriterion("order_amount >", value, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountGreaterThanOrEqualTo(Double value) {
            addCriterion("order_amount >=", value, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountLessThan(Double value) {
            addCriterion("order_amount <", value, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountLessThanOrEqualTo(Double value) {
            addCriterion("order_amount <=", value, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountIn(List<Double> values) {
            addCriterion("order_amount in", values, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountNotIn(List<Double> values) {
            addCriterion("order_amount not in", values, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountBetween(Double value1, Double value2) {
            addCriterion("order_amount between", value1, value2, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountNotBetween(Double value1, Double value2) {
            addCriterion("order_amount not between", value1, value2, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andFreightIsNull() {
            addCriterion("freight is null");
            return (Criteria) this;
        }

        public Criteria andFreightIsNotNull() {
            addCriterion("freight is not null");
            return (Criteria) this;
        }

        public Criteria andFreightEqualTo(Double value) {
            addCriterion("freight =", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightNotEqualTo(Double value) {
            addCriterion("freight <>", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightGreaterThan(Double value) {
            addCriterion("freight >", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightGreaterThanOrEqualTo(Double value) {
            addCriterion("freight >=", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightLessThan(Double value) {
            addCriterion("freight <", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightLessThanOrEqualTo(Double value) {
            addCriterion("freight <=", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightIn(List<Double> values) {
            addCriterion("freight in", values, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightNotIn(List<Double> values) {
            addCriterion("freight not in", values, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightBetween(Double value1, Double value2) {
            addCriterion("freight between", value1, value2, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightNotBetween(Double value1, Double value2) {
            addCriterion("freight not between", value1, value2, "freight");
            return (Criteria) this;
        }

        public Criteria andDiscountsIsNull() {
            addCriterion("discounts is null");
            return (Criteria) this;
        }

        public Criteria andDiscountsIsNotNull() {
            addCriterion("discounts is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountsEqualTo(Double value) {
            addCriterion("discounts =", value, "discounts");
            return (Criteria) this;
        }

        public Criteria andDiscountsNotEqualTo(Double value) {
            addCriterion("discounts <>", value, "discounts");
            return (Criteria) this;
        }

        public Criteria andDiscountsGreaterThan(Double value) {
            addCriterion("discounts >", value, "discounts");
            return (Criteria) this;
        }

        public Criteria andDiscountsGreaterThanOrEqualTo(Double value) {
            addCriterion("discounts >=", value, "discounts");
            return (Criteria) this;
        }

        public Criteria andDiscountsLessThan(Double value) {
            addCriterion("discounts <", value, "discounts");
            return (Criteria) this;
        }

        public Criteria andDiscountsLessThanOrEqualTo(Double value) {
            addCriterion("discounts <=", value, "discounts");
            return (Criteria) this;
        }

        public Criteria andDiscountsIn(List<Double> values) {
            addCriterion("discounts in", values, "discounts");
            return (Criteria) this;
        }

        public Criteria andDiscountsNotIn(List<Double> values) {
            addCriterion("discounts not in", values, "discounts");
            return (Criteria) this;
        }

        public Criteria andDiscountsBetween(Double value1, Double value2) {
            addCriterion("discounts between", value1, value2, "discounts");
            return (Criteria) this;
        }

        public Criteria andDiscountsNotBetween(Double value1, Double value2) {
            addCriterion("discounts not between", value1, value2, "discounts");
            return (Criteria) this;
        }

        public Criteria andActualPaymentIsNull() {
            addCriterion("actual_payment is null");
            return (Criteria) this;
        }

        public Criteria andActualPaymentIsNotNull() {
            addCriterion("actual_payment is not null");
            return (Criteria) this;
        }

        public Criteria andActualPaymentEqualTo(Double value) {
            addCriterion("actual_payment =", value, "actualPayment");
            return (Criteria) this;
        }

        public Criteria andActualPaymentNotEqualTo(Double value) {
            addCriterion("actual_payment <>", value, "actualPayment");
            return (Criteria) this;
        }

        public Criteria andActualPaymentGreaterThan(Double value) {
            addCriterion("actual_payment >", value, "actualPayment");
            return (Criteria) this;
        }

        public Criteria andActualPaymentGreaterThanOrEqualTo(Double value) {
            addCriterion("actual_payment >=", value, "actualPayment");
            return (Criteria) this;
        }

        public Criteria andActualPaymentLessThan(Double value) {
            addCriterion("actual_payment <", value, "actualPayment");
            return (Criteria) this;
        }

        public Criteria andActualPaymentLessThanOrEqualTo(Double value) {
            addCriterion("actual_payment <=", value, "actualPayment");
            return (Criteria) this;
        }

        public Criteria andActualPaymentIn(List<Double> values) {
            addCriterion("actual_payment in", values, "actualPayment");
            return (Criteria) this;
        }

        public Criteria andActualPaymentNotIn(List<Double> values) {
            addCriterion("actual_payment not in", values, "actualPayment");
            return (Criteria) this;
        }

        public Criteria andActualPaymentBetween(Double value1, Double value2) {
            addCriterion("actual_payment between", value1, value2, "actualPayment");
            return (Criteria) this;
        }

        public Criteria andActualPaymentNotBetween(Double value1, Double value2) {
            addCriterion("actual_payment not between", value1, value2, "actualPayment");
            return (Criteria) this;
        }

        public Criteria andRecipientIsNull() {
            addCriterion("recipient is null");
            return (Criteria) this;
        }

        public Criteria andRecipientIsNotNull() {
            addCriterion("recipient is not null");
            return (Criteria) this;
        }

        public Criteria andRecipientEqualTo(String value) {
            addCriterion("recipient =", value, "recipient");
            return (Criteria) this;
        }

        public Criteria andRecipientNotEqualTo(String value) {
            addCriterion("recipient <>", value, "recipient");
            return (Criteria) this;
        }

        public Criteria andRecipientGreaterThan(String value) {
            addCriterion("recipient >", value, "recipient");
            return (Criteria) this;
        }

        public Criteria andRecipientGreaterThanOrEqualTo(String value) {
            addCriterion("recipient >=", value, "recipient");
            return (Criteria) this;
        }

        public Criteria andRecipientLessThan(String value) {
            addCriterion("recipient <", value, "recipient");
            return (Criteria) this;
        }

        public Criteria andRecipientLessThanOrEqualTo(String value) {
            addCriterion("recipient <=", value, "recipient");
            return (Criteria) this;
        }

        public Criteria andRecipientLike(String value) {
            addCriterion("recipient like", value, "recipient");
            return (Criteria) this;
        }

        public Criteria andRecipientNotLike(String value) {
            addCriterion("recipient not like", value, "recipient");
            return (Criteria) this;
        }

        public Criteria andRecipientIn(List<String> values) {
            addCriterion("recipient in", values, "recipient");
            return (Criteria) this;
        }

        public Criteria andRecipientNotIn(List<String> values) {
            addCriterion("recipient not in", values, "recipient");
            return (Criteria) this;
        }

        public Criteria andRecipientBetween(String value1, String value2) {
            addCriterion("recipient between", value1, value2, "recipient");
            return (Criteria) this;
        }

        public Criteria andRecipientNotBetween(String value1, String value2) {
            addCriterion("recipient not between", value1, value2, "recipient");
            return (Criteria) this;
        }

        public Criteria andPhoneNumIsNull() {
            addCriterion("phone_num is null");
            return (Criteria) this;
        }

        public Criteria andPhoneNumIsNotNull() {
            addCriterion("phone_num is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneNumEqualTo(String value) {
            addCriterion("phone_num =", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumNotEqualTo(String value) {
            addCriterion("phone_num <>", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumGreaterThan(String value) {
            addCriterion("phone_num >", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumGreaterThanOrEqualTo(String value) {
            addCriterion("phone_num >=", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumLessThan(String value) {
            addCriterion("phone_num <", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumLessThanOrEqualTo(String value) {
            addCriterion("phone_num <=", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumLike(String value) {
            addCriterion("phone_num like", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumNotLike(String value) {
            addCriterion("phone_num not like", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumIn(List<String> values) {
            addCriterion("phone_num in", values, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumNotIn(List<String> values) {
            addCriterion("phone_num not in", values, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumBetween(String value1, String value2) {
            addCriterion("phone_num between", value1, value2, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumNotBetween(String value1, String value2) {
            addCriterion("phone_num not between", value1, value2, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIdIsNull() {
            addCriterion("address_id is null");
            return (Criteria) this;
        }

        public Criteria andAddressIdIsNotNull() {
            addCriterion("address_id is not null");
            return (Criteria) this;
        }

        public Criteria andAddressIdEqualTo(Integer value) {
            addCriterion("address_id =", value, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdNotEqualTo(Integer value) {
            addCriterion("address_id <>", value, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdGreaterThan(Integer value) {
            addCriterion("address_id >", value, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("address_id >=", value, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdLessThan(Integer value) {
            addCriterion("address_id <", value, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdLessThanOrEqualTo(Integer value) {
            addCriterion("address_id <=", value, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdIn(List<Integer> values) {
            addCriterion("address_id in", values, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdNotIn(List<Integer> values) {
            addCriterion("address_id not in", values, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdBetween(Integer value1, Integer value2) {
            addCriterion("address_id between", value1, value2, "addressId");
            return (Criteria) this;
        }

        public Criteria andAddressIdNotBetween(Integer value1, Integer value2) {
            addCriterion("address_id not between", value1, value2, "addressId");
            return (Criteria) this;
        }

        public Criteria andOrderStatusIsNull() {
            addCriterion("order_status is null");
            return (Criteria) this;
        }

        public Criteria andOrderStatusIsNotNull() {
            addCriterion("order_status is not null");
            return (Criteria) this;
        }

        public Criteria andOrderStatusEqualTo(Integer value) {
            addCriterion("order_status =", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotEqualTo(Integer value) {
            addCriterion("order_status <>", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusGreaterThan(Integer value) {
            addCriterion("order_status >", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_status >=", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusLessThan(Integer value) {
            addCriterion("order_status <", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusLessThanOrEqualTo(Integer value) {
            addCriterion("order_status <=", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusIn(List<Integer> values) {
            addCriterion("order_status in", values, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotIn(List<Integer> values) {
            addCriterion("order_status not in", values, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusBetween(Integer value1, Integer value2) {
            addCriterion("order_status between", value1, value2, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("order_status not between", value1, value2, "orderStatus");
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

        public Criteria andPayTypeIsNull() {
            addCriterion("pay_type is null");
            return (Criteria) this;
        }

        public Criteria andPayTypeIsNotNull() {
            addCriterion("pay_type is not null");
            return (Criteria) this;
        }

        public Criteria andPayTypeEqualTo(String value) {
            addCriterion("pay_type =", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotEqualTo(String value) {
            addCriterion("pay_type <>", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeGreaterThan(String value) {
            addCriterion("pay_type >", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeGreaterThanOrEqualTo(String value) {
            addCriterion("pay_type >=", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLessThan(String value) {
            addCriterion("pay_type <", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLessThanOrEqualTo(String value) {
            addCriterion("pay_type <=", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLike(String value) {
            addCriterion("pay_type like", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotLike(String value) {
            addCriterion("pay_type not like", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeIn(List<String> values) {
            addCriterion("pay_type in", values, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotIn(List<String> values) {
            addCriterion("pay_type not in", values, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeBetween(String value1, String value2) {
            addCriterion("pay_type between", value1, value2, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotBetween(String value1, String value2) {
            addCriterion("pay_type not between", value1, value2, "payType");
            return (Criteria) this;
        }

        public Criteria andPayAccountIsNull() {
            addCriterion("pay_account is null");
            return (Criteria) this;
        }

        public Criteria andPayAccountIsNotNull() {
            addCriterion("pay_account is not null");
            return (Criteria) this;
        }

        public Criteria andPayAccountEqualTo(String value) {
            addCriterion("pay_account =", value, "payAccount");
            return (Criteria) this;
        }

        public Criteria andPayAccountNotEqualTo(String value) {
            addCriterion("pay_account <>", value, "payAccount");
            return (Criteria) this;
        }

        public Criteria andPayAccountGreaterThan(String value) {
            addCriterion("pay_account >", value, "payAccount");
            return (Criteria) this;
        }

        public Criteria andPayAccountGreaterThanOrEqualTo(String value) {
            addCriterion("pay_account >=", value, "payAccount");
            return (Criteria) this;
        }

        public Criteria andPayAccountLessThan(String value) {
            addCriterion("pay_account <", value, "payAccount");
            return (Criteria) this;
        }

        public Criteria andPayAccountLessThanOrEqualTo(String value) {
            addCriterion("pay_account <=", value, "payAccount");
            return (Criteria) this;
        }

        public Criteria andPayAccountLike(String value) {
            addCriterion("pay_account like", value, "payAccount");
            return (Criteria) this;
        }

        public Criteria andPayAccountNotLike(String value) {
            addCriterion("pay_account not like", value, "payAccount");
            return (Criteria) this;
        }

        public Criteria andPayAccountIn(List<String> values) {
            addCriterion("pay_account in", values, "payAccount");
            return (Criteria) this;
        }

        public Criteria andPayAccountNotIn(List<String> values) {
            addCriterion("pay_account not in", values, "payAccount");
            return (Criteria) this;
        }

        public Criteria andPayAccountBetween(String value1, String value2) {
            addCriterion("pay_account between", value1, value2, "payAccount");
            return (Criteria) this;
        }

        public Criteria andPayAccountNotBetween(String value1, String value2) {
            addCriterion("pay_account not between", value1, value2, "payAccount");
            return (Criteria) this;
        }

        public Criteria andPayValueIsNull() {
            addCriterion("pay_value is null");
            return (Criteria) this;
        }

        public Criteria andPayValueIsNotNull() {
            addCriterion("pay_value is not null");
            return (Criteria) this;
        }

        public Criteria andPayValueEqualTo(Double value) {
            addCriterion("pay_value =", value, "payValue");
            return (Criteria) this;
        }

        public Criteria andPayValueNotEqualTo(Double value) {
            addCriterion("pay_value <>", value, "payValue");
            return (Criteria) this;
        }

        public Criteria andPayValueGreaterThan(Double value) {
            addCriterion("pay_value >", value, "payValue");
            return (Criteria) this;
        }

        public Criteria andPayValueGreaterThanOrEqualTo(Double value) {
            addCriterion("pay_value >=", value, "payValue");
            return (Criteria) this;
        }

        public Criteria andPayValueLessThan(Double value) {
            addCriterion("pay_value <", value, "payValue");
            return (Criteria) this;
        }

        public Criteria andPayValueLessThanOrEqualTo(Double value) {
            addCriterion("pay_value <=", value, "payValue");
            return (Criteria) this;
        }

        public Criteria andPayValueIn(List<Double> values) {
            addCriterion("pay_value in", values, "payValue");
            return (Criteria) this;
        }

        public Criteria andPayValueNotIn(List<Double> values) {
            addCriterion("pay_value not in", values, "payValue");
            return (Criteria) this;
        }

        public Criteria andPayValueBetween(Double value1, Double value2) {
            addCriterion("pay_value between", value1, value2, "payValue");
            return (Criteria) this;
        }

        public Criteria andPayValueNotBetween(Double value1, Double value2) {
            addCriterion("pay_value not between", value1, value2, "payValue");
            return (Criteria) this;
        }

        public Criteria andPayTimeIsNull() {
            addCriterion("pay_time is null");
            return (Criteria) this;
        }

        public Criteria andPayTimeIsNotNull() {
            addCriterion("pay_time is not null");
            return (Criteria) this;
        }

        public Criteria andPayTimeEqualTo(String value) {
            addCriterion("pay_time =", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeNotEqualTo(String value) {
            addCriterion("pay_time <>", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeGreaterThan(String value) {
            addCriterion("pay_time >", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeGreaterThanOrEqualTo(String value) {
            addCriterion("pay_time >=", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeLessThan(String value) {
            addCriterion("pay_time <", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeLessThanOrEqualTo(String value) {
            addCriterion("pay_time <=", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeLike(String value) {
            addCriterion("pay_time like", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeNotLike(String value) {
            addCriterion("pay_time not like", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeIn(List<String> values) {
            addCriterion("pay_time in", values, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeNotIn(List<String> values) {
            addCriterion("pay_time not in", values, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeBetween(String value1, String value2) {
            addCriterion("pay_time between", value1, value2, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeNotBetween(String value1, String value2) {
            addCriterion("pay_time not between", value1, value2, "payTime");
            return (Criteria) this;
        }

        public Criteria andLogisticsIsNull() {
            addCriterion("logistics is null");
            return (Criteria) this;
        }

        public Criteria andLogisticsIsNotNull() {
            addCriterion("logistics is not null");
            return (Criteria) this;
        }

        public Criteria andLogisticsEqualTo(String value) {
            addCriterion("logistics =", value, "logistics");
            return (Criteria) this;
        }

        public Criteria andLogisticsNotEqualTo(String value) {
            addCriterion("logistics <>", value, "logistics");
            return (Criteria) this;
        }

        public Criteria andLogisticsGreaterThan(String value) {
            addCriterion("logistics >", value, "logistics");
            return (Criteria) this;
        }

        public Criteria andLogisticsGreaterThanOrEqualTo(String value) {
            addCriterion("logistics >=", value, "logistics");
            return (Criteria) this;
        }

        public Criteria andLogisticsLessThan(String value) {
            addCriterion("logistics <", value, "logistics");
            return (Criteria) this;
        }

        public Criteria andLogisticsLessThanOrEqualTo(String value) {
            addCriterion("logistics <=", value, "logistics");
            return (Criteria) this;
        }

        public Criteria andLogisticsLike(String value) {
            addCriterion("logistics like", value, "logistics");
            return (Criteria) this;
        }

        public Criteria andLogisticsNotLike(String value) {
            addCriterion("logistics not like", value, "logistics");
            return (Criteria) this;
        }

        public Criteria andLogisticsIn(List<String> values) {
            addCriterion("logistics in", values, "logistics");
            return (Criteria) this;
        }

        public Criteria andLogisticsNotIn(List<String> values) {
            addCriterion("logistics not in", values, "logistics");
            return (Criteria) this;
        }

        public Criteria andLogisticsBetween(String value1, String value2) {
            addCriterion("logistics between", value1, value2, "logistics");
            return (Criteria) this;
        }

        public Criteria andLogisticsNotBetween(String value1, String value2) {
            addCriterion("logistics not between", value1, value2, "logistics");
            return (Criteria) this;
        }

        public Criteria andWaybillIsNull() {
            addCriterion("waybill is null");
            return (Criteria) this;
        }

        public Criteria andWaybillIsNotNull() {
            addCriterion("waybill is not null");
            return (Criteria) this;
        }

        public Criteria andWaybillEqualTo(String value) {
            addCriterion("waybill =", value, "waybill");
            return (Criteria) this;
        }

        public Criteria andWaybillNotEqualTo(String value) {
            addCriterion("waybill <>", value, "waybill");
            return (Criteria) this;
        }

        public Criteria andWaybillGreaterThan(String value) {
            addCriterion("waybill >", value, "waybill");
            return (Criteria) this;
        }

        public Criteria andWaybillGreaterThanOrEqualTo(String value) {
            addCriterion("waybill >=", value, "waybill");
            return (Criteria) this;
        }

        public Criteria andWaybillLessThan(String value) {
            addCriterion("waybill <", value, "waybill");
            return (Criteria) this;
        }

        public Criteria andWaybillLessThanOrEqualTo(String value) {
            addCriterion("waybill <=", value, "waybill");
            return (Criteria) this;
        }

        public Criteria andWaybillLike(String value) {
            addCriterion("waybill like", value, "waybill");
            return (Criteria) this;
        }

        public Criteria andWaybillNotLike(String value) {
            addCriterion("waybill not like", value, "waybill");
            return (Criteria) this;
        }

        public Criteria andWaybillIn(List<String> values) {
            addCriterion("waybill in", values, "waybill");
            return (Criteria) this;
        }

        public Criteria andWaybillNotIn(List<String> values) {
            addCriterion("waybill not in", values, "waybill");
            return (Criteria) this;
        }

        public Criteria andWaybillBetween(String value1, String value2) {
            addCriterion("waybill between", value1, value2, "waybill");
            return (Criteria) this;
        }

        public Criteria andWaybillNotBetween(String value1, String value2) {
            addCriterion("waybill not between", value1, value2, "waybill");
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

    /**
     * yw_order
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * yw_order 2018-03-22
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