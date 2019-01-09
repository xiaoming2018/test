package com.chatRobot.model;

import java.math.BigDecimal;
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

        public Criteria andGoodsPriceIsNull() {
            addCriterion("goods_price is null");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceIsNotNull() {
            addCriterion("goods_price is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceEqualTo(BigDecimal value) {
            addCriterion("goods_price =", value, "goodsPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceNotEqualTo(BigDecimal value) {
            addCriterion("goods_price <>", value, "goodsPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceGreaterThan(BigDecimal value) {
            addCriterion("goods_price >", value, "goodsPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("goods_price >=", value, "goodsPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceLessThan(BigDecimal value) {
            addCriterion("goods_price <", value, "goodsPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("goods_price <=", value, "goodsPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceIn(List<BigDecimal> values) {
            addCriterion("goods_price in", values, "goodsPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceNotIn(List<BigDecimal> values) {
            addCriterion("goods_price not in", values, "goodsPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("goods_price between", value1, value2, "goodsPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("goods_price not between", value1, value2, "goodsPrice");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeIdIsNull() {
            addCriterion("goods_type_id is null");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeIdIsNotNull() {
            addCriterion("goods_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeIdEqualTo(Integer value) {
            addCriterion("goods_type_id =", value, "goodsTypeId");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeIdNotEqualTo(Integer value) {
            addCriterion("goods_type_id <>", value, "goodsTypeId");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeIdGreaterThan(Integer value) {
            addCriterion("goods_type_id >", value, "goodsTypeId");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("goods_type_id >=", value, "goodsTypeId");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeIdLessThan(Integer value) {
            addCriterion("goods_type_id <", value, "goodsTypeId");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeIdLessThanOrEqualTo(Integer value) {
            addCriterion("goods_type_id <=", value, "goodsTypeId");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeIdIn(List<Integer> values) {
            addCriterion("goods_type_id in", values, "goodsTypeId");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeIdNotIn(List<Integer> values) {
            addCriterion("goods_type_id not in", values, "goodsTypeId");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeIdBetween(Integer value1, Integer value2) {
            addCriterion("goods_type_id between", value1, value2, "goodsTypeId");
            return (Criteria) this;
        }

        public Criteria andGoodsTypeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("goods_type_id not between", value1, value2, "goodsTypeId");
            return (Criteria) this;
        }

        public Criteria andGoodsModelIdIsNull() {
            addCriterion("goods_model_id is null");
            return (Criteria) this;
        }

        public Criteria andGoodsModelIdIsNotNull() {
            addCriterion("goods_model_id is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsModelIdEqualTo(Integer value) {
            addCriterion("goods_model_id =", value, "goodsModelId");
            return (Criteria) this;
        }

        public Criteria andGoodsModelIdNotEqualTo(Integer value) {
            addCriterion("goods_model_id <>", value, "goodsModelId");
            return (Criteria) this;
        }

        public Criteria andGoodsModelIdGreaterThan(Integer value) {
            addCriterion("goods_model_id >", value, "goodsModelId");
            return (Criteria) this;
        }

        public Criteria andGoodsModelIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("goods_model_id >=", value, "goodsModelId");
            return (Criteria) this;
        }

        public Criteria andGoodsModelIdLessThan(Integer value) {
            addCriterion("goods_model_id <", value, "goodsModelId");
            return (Criteria) this;
        }

        public Criteria andGoodsModelIdLessThanOrEqualTo(Integer value) {
            addCriterion("goods_model_id <=", value, "goodsModelId");
            return (Criteria) this;
        }

        public Criteria andGoodsModelIdIn(List<Integer> values) {
            addCriterion("goods_model_id in", values, "goodsModelId");
            return (Criteria) this;
        }

        public Criteria andGoodsModelIdNotIn(List<Integer> values) {
            addCriterion("goods_model_id not in", values, "goodsModelId");
            return (Criteria) this;
        }

        public Criteria andGoodsModelIdBetween(Integer value1, Integer value2) {
            addCriterion("goods_model_id between", value1, value2, "goodsModelId");
            return (Criteria) this;
        }

        public Criteria andGoodsModelIdNotBetween(Integer value1, Integer value2) {
            addCriterion("goods_model_id not between", value1, value2, "goodsModelId");
            return (Criteria) this;
        }

        public Criteria andGoodsDescIsNull() {
            addCriterion("goods_desc is null");
            return (Criteria) this;
        }

        public Criteria andGoodsDescIsNotNull() {
            addCriterion("goods_desc is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsDescEqualTo(String value) {
            addCriterion("goods_desc =", value, "goodsDesc");
            return (Criteria) this;
        }

        public Criteria andGoodsDescNotEqualTo(String value) {
            addCriterion("goods_desc <>", value, "goodsDesc");
            return (Criteria) this;
        }

        public Criteria andGoodsDescGreaterThan(String value) {
            addCriterion("goods_desc >", value, "goodsDesc");
            return (Criteria) this;
        }

        public Criteria andGoodsDescGreaterThanOrEqualTo(String value) {
            addCriterion("goods_desc >=", value, "goodsDesc");
            return (Criteria) this;
        }

        public Criteria andGoodsDescLessThan(String value) {
            addCriterion("goods_desc <", value, "goodsDesc");
            return (Criteria) this;
        }

        public Criteria andGoodsDescLessThanOrEqualTo(String value) {
            addCriterion("goods_desc <=", value, "goodsDesc");
            return (Criteria) this;
        }

        public Criteria andGoodsDescLike(String value) {
            addCriterion("goods_desc like", value, "goodsDesc");
            return (Criteria) this;
        }

        public Criteria andGoodsDescNotLike(String value) {
            addCriterion("goods_desc not like", value, "goodsDesc");
            return (Criteria) this;
        }

        public Criteria andGoodsDescIn(List<String> values) {
            addCriterion("goods_desc in", values, "goodsDesc");
            return (Criteria) this;
        }

        public Criteria andGoodsDescNotIn(List<String> values) {
            addCriterion("goods_desc not in", values, "goodsDesc");
            return (Criteria) this;
        }

        public Criteria andGoodsDescBetween(String value1, String value2) {
            addCriterion("goods_desc between", value1, value2, "goodsDesc");
            return (Criteria) this;
        }

        public Criteria andGoodsDescNotBetween(String value1, String value2) {
            addCriterion("goods_desc not between", value1, value2, "goodsDesc");
            return (Criteria) this;
        }

        public Criteria andGoodsDiscountIsNull() {
            addCriterion("goods_discount is null");
            return (Criteria) this;
        }

        public Criteria andGoodsDiscountIsNotNull() {
            addCriterion("goods_discount is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsDiscountEqualTo(Float value) {
            addCriterion("goods_discount =", value, "goodsDiscount");
            return (Criteria) this;
        }

        public Criteria andGoodsDiscountNotEqualTo(Float value) {
            addCriterion("goods_discount <>", value, "goodsDiscount");
            return (Criteria) this;
        }

        public Criteria andGoodsDiscountGreaterThan(Float value) {
            addCriterion("goods_discount >", value, "goodsDiscount");
            return (Criteria) this;
        }

        public Criteria andGoodsDiscountGreaterThanOrEqualTo(Float value) {
            addCriterion("goods_discount >=", value, "goodsDiscount");
            return (Criteria) this;
        }

        public Criteria andGoodsDiscountLessThan(Float value) {
            addCriterion("goods_discount <", value, "goodsDiscount");
            return (Criteria) this;
        }

        public Criteria andGoodsDiscountLessThanOrEqualTo(Float value) {
            addCriterion("goods_discount <=", value, "goodsDiscount");
            return (Criteria) this;
        }

        public Criteria andGoodsDiscountIn(List<Float> values) {
            addCriterion("goods_discount in", values, "goodsDiscount");
            return (Criteria) this;
        }

        public Criteria andGoodsDiscountNotIn(List<Float> values) {
            addCriterion("goods_discount not in", values, "goodsDiscount");
            return (Criteria) this;
        }

        public Criteria andGoodsDiscountBetween(Float value1, Float value2) {
            addCriterion("goods_discount between", value1, value2, "goodsDiscount");
            return (Criteria) this;
        }

        public Criteria andGoodsDiscountNotBetween(Float value1, Float value2) {
            addCriterion("goods_discount not between", value1, value2, "goodsDiscount");
            return (Criteria) this;
        }

        public Criteria andGoodsPictureIsNull() {
            addCriterion("goods_picture is null");
            return (Criteria) this;
        }

        public Criteria andGoodsPictureIsNotNull() {
            addCriterion("goods_picture is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsPictureEqualTo(String value) {
            addCriterion("goods_picture =", value, "goodsPicture");
            return (Criteria) this;
        }

        public Criteria andGoodsPictureNotEqualTo(String value) {
            addCriterion("goods_picture <>", value, "goodsPicture");
            return (Criteria) this;
        }

        public Criteria andGoodsPictureGreaterThan(String value) {
            addCriterion("goods_picture >", value, "goodsPicture");
            return (Criteria) this;
        }

        public Criteria andGoodsPictureGreaterThanOrEqualTo(String value) {
            addCriterion("goods_picture >=", value, "goodsPicture");
            return (Criteria) this;
        }

        public Criteria andGoodsPictureLessThan(String value) {
            addCriterion("goods_picture <", value, "goodsPicture");
            return (Criteria) this;
        }

        public Criteria andGoodsPictureLessThanOrEqualTo(String value) {
            addCriterion("goods_picture <=", value, "goodsPicture");
            return (Criteria) this;
        }

        public Criteria andGoodsPictureLike(String value) {
            addCriterion("goods_picture like", value, "goodsPicture");
            return (Criteria) this;
        }

        public Criteria andGoodsPictureNotLike(String value) {
            addCriterion("goods_picture not like", value, "goodsPicture");
            return (Criteria) this;
        }

        public Criteria andGoodsPictureIn(List<String> values) {
            addCriterion("goods_picture in", values, "goodsPicture");
            return (Criteria) this;
        }

        public Criteria andGoodsPictureNotIn(List<String> values) {
            addCriterion("goods_picture not in", values, "goodsPicture");
            return (Criteria) this;
        }

        public Criteria andGoodsPictureBetween(String value1, String value2) {
            addCriterion("goods_picture between", value1, value2, "goodsPicture");
            return (Criteria) this;
        }

        public Criteria andGoodsPictureNotBetween(String value1, String value2) {
            addCriterion("goods_picture not between", value1, value2, "goodsPicture");
            return (Criteria) this;
        }

        public Criteria andGoodsIsnewIsNull() {
            addCriterion("goods_isNew is null");
            return (Criteria) this;
        }

        public Criteria andGoodsIsnewIsNotNull() {
            addCriterion("goods_isNew is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsIsnewEqualTo(Boolean value) {
            addCriterion("goods_isNew =", value, "goodsIsnew");
            return (Criteria) this;
        }

        public Criteria andGoodsIsnewNotEqualTo(Boolean value) {
            addCriterion("goods_isNew <>", value, "goodsIsnew");
            return (Criteria) this;
        }

        public Criteria andGoodsIsnewGreaterThan(Boolean value) {
            addCriterion("goods_isNew >", value, "goodsIsnew");
            return (Criteria) this;
        }

        public Criteria andGoodsIsnewGreaterThanOrEqualTo(Boolean value) {
            addCriterion("goods_isNew >=", value, "goodsIsnew");
            return (Criteria) this;
        }

        public Criteria andGoodsIsnewLessThan(Boolean value) {
            addCriterion("goods_isNew <", value, "goodsIsnew");
            return (Criteria) this;
        }

        public Criteria andGoodsIsnewLessThanOrEqualTo(Boolean value) {
            addCriterion("goods_isNew <=", value, "goodsIsnew");
            return (Criteria) this;
        }

        public Criteria andGoodsIsnewIn(List<Boolean> values) {
            addCriterion("goods_isNew in", values, "goodsIsnew");
            return (Criteria) this;
        }

        public Criteria andGoodsIsnewNotIn(List<Boolean> values) {
            addCriterion("goods_isNew not in", values, "goodsIsnew");
            return (Criteria) this;
        }

        public Criteria andGoodsIsnewBetween(Boolean value1, Boolean value2) {
            addCriterion("goods_isNew between", value1, value2, "goodsIsnew");
            return (Criteria) this;
        }

        public Criteria andGoodsIsnewNotBetween(Boolean value1, Boolean value2) {
            addCriterion("goods_isNew not between", value1, value2, "goodsIsnew");
            return (Criteria) this;
        }

        public Criteria andGoodsIsrecommendIsNull() {
            addCriterion("goods_isRecommend is null");
            return (Criteria) this;
        }

        public Criteria andGoodsIsrecommendIsNotNull() {
            addCriterion("goods_isRecommend is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsIsrecommendEqualTo(Boolean value) {
            addCriterion("goods_isRecommend =", value, "goodsIsrecommend");
            return (Criteria) this;
        }

        public Criteria andGoodsIsrecommendNotEqualTo(Boolean value) {
            addCriterion("goods_isRecommend <>", value, "goodsIsrecommend");
            return (Criteria) this;
        }

        public Criteria andGoodsIsrecommendGreaterThan(Boolean value) {
            addCriterion("goods_isRecommend >", value, "goodsIsrecommend");
            return (Criteria) this;
        }

        public Criteria andGoodsIsrecommendGreaterThanOrEqualTo(Boolean value) {
            addCriterion("goods_isRecommend >=", value, "goodsIsrecommend");
            return (Criteria) this;
        }

        public Criteria andGoodsIsrecommendLessThan(Boolean value) {
            addCriterion("goods_isRecommend <", value, "goodsIsrecommend");
            return (Criteria) this;
        }

        public Criteria andGoodsIsrecommendLessThanOrEqualTo(Boolean value) {
            addCriterion("goods_isRecommend <=", value, "goodsIsrecommend");
            return (Criteria) this;
        }

        public Criteria andGoodsIsrecommendIn(List<Boolean> values) {
            addCriterion("goods_isRecommend in", values, "goodsIsrecommend");
            return (Criteria) this;
        }

        public Criteria andGoodsIsrecommendNotIn(List<Boolean> values) {
            addCriterion("goods_isRecommend not in", values, "goodsIsrecommend");
            return (Criteria) this;
        }

        public Criteria andGoodsIsrecommendBetween(Boolean value1, Boolean value2) {
            addCriterion("goods_isRecommend between", value1, value2, "goodsIsrecommend");
            return (Criteria) this;
        }

        public Criteria andGoodsIsrecommendNotBetween(Boolean value1, Boolean value2) {
            addCriterion("goods_isRecommend not between", value1, value2, "goodsIsrecommend");
            return (Criteria) this;
        }

        public Criteria andGoodsStatusIsNull() {
            addCriterion("goods_status is null");
            return (Criteria) this;
        }

        public Criteria andGoodsStatusIsNotNull() {
            addCriterion("goods_status is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsStatusEqualTo(String value) {
            addCriterion("goods_status =", value, "goodsStatus");
            return (Criteria) this;
        }

        public Criteria andGoodsStatusNotEqualTo(String value) {
            addCriterion("goods_status <>", value, "goodsStatus");
            return (Criteria) this;
        }

        public Criteria andGoodsStatusGreaterThan(String value) {
            addCriterion("goods_status >", value, "goodsStatus");
            return (Criteria) this;
        }

        public Criteria andGoodsStatusGreaterThanOrEqualTo(String value) {
            addCriterion("goods_status >=", value, "goodsStatus");
            return (Criteria) this;
        }

        public Criteria andGoodsStatusLessThan(String value) {
            addCriterion("goods_status <", value, "goodsStatus");
            return (Criteria) this;
        }

        public Criteria andGoodsStatusLessThanOrEqualTo(String value) {
            addCriterion("goods_status <=", value, "goodsStatus");
            return (Criteria) this;
        }

        public Criteria andGoodsStatusLike(String value) {
            addCriterion("goods_status like", value, "goodsStatus");
            return (Criteria) this;
        }

        public Criteria andGoodsStatusNotLike(String value) {
            addCriterion("goods_status not like", value, "goodsStatus");
            return (Criteria) this;
        }

        public Criteria andGoodsStatusIn(List<String> values) {
            addCriterion("goods_status in", values, "goodsStatus");
            return (Criteria) this;
        }

        public Criteria andGoodsStatusNotIn(List<String> values) {
            addCriterion("goods_status not in", values, "goodsStatus");
            return (Criteria) this;
        }

        public Criteria andGoodsStatusBetween(String value1, String value2) {
            addCriterion("goods_status between", value1, value2, "goodsStatus");
            return (Criteria) this;
        }

        public Criteria andGoodsStatusNotBetween(String value1, String value2) {
            addCriterion("goods_status not between", value1, value2, "goodsStatus");
            return (Criteria) this;
        }

        public Criteria andGoodsRemarkIsNull() {
            addCriterion("goods_remark is null");
            return (Criteria) this;
        }

        public Criteria andGoodsRemarkIsNotNull() {
            addCriterion("goods_remark is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsRemarkEqualTo(String value) {
            addCriterion("goods_remark =", value, "goodsRemark");
            return (Criteria) this;
        }

        public Criteria andGoodsRemarkNotEqualTo(String value) {
            addCriterion("goods_remark <>", value, "goodsRemark");
            return (Criteria) this;
        }

        public Criteria andGoodsRemarkGreaterThan(String value) {
            addCriterion("goods_remark >", value, "goodsRemark");
            return (Criteria) this;
        }

        public Criteria andGoodsRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("goods_remark >=", value, "goodsRemark");
            return (Criteria) this;
        }

        public Criteria andGoodsRemarkLessThan(String value) {
            addCriterion("goods_remark <", value, "goodsRemark");
            return (Criteria) this;
        }

        public Criteria andGoodsRemarkLessThanOrEqualTo(String value) {
            addCriterion("goods_remark <=", value, "goodsRemark");
            return (Criteria) this;
        }

        public Criteria andGoodsRemarkLike(String value) {
            addCriterion("goods_remark like", value, "goodsRemark");
            return (Criteria) this;
        }

        public Criteria andGoodsRemarkNotLike(String value) {
            addCriterion("goods_remark not like", value, "goodsRemark");
            return (Criteria) this;
        }

        public Criteria andGoodsRemarkIn(List<String> values) {
            addCriterion("goods_remark in", values, "goodsRemark");
            return (Criteria) this;
        }

        public Criteria andGoodsRemarkNotIn(List<String> values) {
            addCriterion("goods_remark not in", values, "goodsRemark");
            return (Criteria) this;
        }

        public Criteria andGoodsRemarkBetween(String value1, String value2) {
            addCriterion("goods_remark between", value1, value2, "goodsRemark");
            return (Criteria) this;
        }

        public Criteria andGoodsRemarkNotBetween(String value1, String value2) {
            addCriterion("goods_remark not between", value1, value2, "goodsRemark");
            return (Criteria) this;
        }

        public Criteria andGoodsAmountIsNull() {
            addCriterion("goods_amount is null");
            return (Criteria) this;
        }

        public Criteria andGoodsAmountIsNotNull() {
            addCriterion("goods_amount is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsAmountEqualTo(Integer value) {
            addCriterion("goods_amount =", value, "goodsAmount");
            return (Criteria) this;
        }

        public Criteria andGoodsAmountNotEqualTo(Integer value) {
            addCriterion("goods_amount <>", value, "goodsAmount");
            return (Criteria) this;
        }

        public Criteria andGoodsAmountGreaterThan(Integer value) {
            addCriterion("goods_amount >", value, "goodsAmount");
            return (Criteria) this;
        }

        public Criteria andGoodsAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("goods_amount >=", value, "goodsAmount");
            return (Criteria) this;
        }

        public Criteria andGoodsAmountLessThan(Integer value) {
            addCriterion("goods_amount <", value, "goodsAmount");
            return (Criteria) this;
        }

        public Criteria andGoodsAmountLessThanOrEqualTo(Integer value) {
            addCriterion("goods_amount <=", value, "goodsAmount");
            return (Criteria) this;
        }

        public Criteria andGoodsAmountIn(List<Integer> values) {
            addCriterion("goods_amount in", values, "goodsAmount");
            return (Criteria) this;
        }

        public Criteria andGoodsAmountNotIn(List<Integer> values) {
            addCriterion("goods_amount not in", values, "goodsAmount");
            return (Criteria) this;
        }

        public Criteria andGoodsAmountBetween(Integer value1, Integer value2) {
            addCriterion("goods_amount between", value1, value2, "goodsAmount");
            return (Criteria) this;
        }

        public Criteria andGoodsAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("goods_amount not between", value1, value2, "goodsAmount");
            return (Criteria) this;
        }

        public Criteria andGoodsSellAmountIsNull() {
            addCriterion("goods_sell_amount is null");
            return (Criteria) this;
        }

        public Criteria andGoodsSellAmountIsNotNull() {
            addCriterion("goods_sell_amount is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsSellAmountEqualTo(Integer value) {
            addCriterion("goods_sell_amount =", value, "goodsSellAmount");
            return (Criteria) this;
        }

        public Criteria andGoodsSellAmountNotEqualTo(Integer value) {
            addCriterion("goods_sell_amount <>", value, "goodsSellAmount");
            return (Criteria) this;
        }

        public Criteria andGoodsSellAmountGreaterThan(Integer value) {
            addCriterion("goods_sell_amount >", value, "goodsSellAmount");
            return (Criteria) this;
        }

        public Criteria andGoodsSellAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("goods_sell_amount >=", value, "goodsSellAmount");
            return (Criteria) this;
        }

        public Criteria andGoodsSellAmountLessThan(Integer value) {
            addCriterion("goods_sell_amount <", value, "goodsSellAmount");
            return (Criteria) this;
        }

        public Criteria andGoodsSellAmountLessThanOrEqualTo(Integer value) {
            addCriterion("goods_sell_amount <=", value, "goodsSellAmount");
            return (Criteria) this;
        }

        public Criteria andGoodsSellAmountIn(List<Integer> values) {
            addCriterion("goods_sell_amount in", values, "goodsSellAmount");
            return (Criteria) this;
        }

        public Criteria andGoodsSellAmountNotIn(List<Integer> values) {
            addCriterion("goods_sell_amount not in", values, "goodsSellAmount");
            return (Criteria) this;
        }

        public Criteria andGoodsSellAmountBetween(Integer value1, Integer value2) {
            addCriterion("goods_sell_amount between", value1, value2, "goodsSellAmount");
            return (Criteria) this;
        }

        public Criteria andGoodsSellAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("goods_sell_amount not between", value1, value2, "goodsSellAmount");
            return (Criteria) this;
        }

        public Criteria andGoodsCreateTimeIsNull() {
            addCriterion("goods_create_time is null");
            return (Criteria) this;
        }

        public Criteria andGoodsCreateTimeIsNotNull() {
            addCriterion("goods_create_time is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsCreateTimeEqualTo(Date value) {
            addCriterionForJDBCDate("goods_create_time =", value, "goodsCreateTime");
            return (Criteria) this;
        }

        public Criteria andGoodsCreateTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("goods_create_time <>", value, "goodsCreateTime");
            return (Criteria) this;
        }

        public Criteria andGoodsCreateTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("goods_create_time >", value, "goodsCreateTime");
            return (Criteria) this;
        }

        public Criteria andGoodsCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("goods_create_time >=", value, "goodsCreateTime");
            return (Criteria) this;
        }

        public Criteria andGoodsCreateTimeLessThan(Date value) {
            addCriterionForJDBCDate("goods_create_time <", value, "goodsCreateTime");
            return (Criteria) this;
        }

        public Criteria andGoodsCreateTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("goods_create_time <=", value, "goodsCreateTime");
            return (Criteria) this;
        }

        public Criteria andGoodsCreateTimeIn(List<Date> values) {
            addCriterionForJDBCDate("goods_create_time in", values, "goodsCreateTime");
            return (Criteria) this;
        }

        public Criteria andGoodsCreateTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("goods_create_time not in", values, "goodsCreateTime");
            return (Criteria) this;
        }

        public Criteria andGoodsCreateTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("goods_create_time between", value1, value2, "goodsCreateTime");
            return (Criteria) this;
        }

        public Criteria andGoodsCreateTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("goods_create_time not between", value1, value2, "goodsCreateTime");
            return (Criteria) this;
        }

        public Criteria andGoodsUpdateTimeIsNull() {
            addCriterion("goods_update_time is null");
            return (Criteria) this;
        }

        public Criteria andGoodsUpdateTimeIsNotNull() {
            addCriterion("goods_update_time is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsUpdateTimeEqualTo(Date value) {
            addCriterionForJDBCDate("goods_update_time =", value, "goodsUpdateTime");
            return (Criteria) this;
        }

        public Criteria andGoodsUpdateTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("goods_update_time <>", value, "goodsUpdateTime");
            return (Criteria) this;
        }

        public Criteria andGoodsUpdateTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("goods_update_time >", value, "goodsUpdateTime");
            return (Criteria) this;
        }

        public Criteria andGoodsUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("goods_update_time >=", value, "goodsUpdateTime");
            return (Criteria) this;
        }

        public Criteria andGoodsUpdateTimeLessThan(Date value) {
            addCriterionForJDBCDate("goods_update_time <", value, "goodsUpdateTime");
            return (Criteria) this;
        }

        public Criteria andGoodsUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("goods_update_time <=", value, "goodsUpdateTime");
            return (Criteria) this;
        }

        public Criteria andGoodsUpdateTimeIn(List<Date> values) {
            addCriterionForJDBCDate("goods_update_time in", values, "goodsUpdateTime");
            return (Criteria) this;
        }

        public Criteria andGoodsUpdateTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("goods_update_time not in", values, "goodsUpdateTime");
            return (Criteria) this;
        }

        public Criteria andGoodsUpdateTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("goods_update_time between", value1, value2, "goodsUpdateTime");
            return (Criteria) this;
        }

        public Criteria andGoodsUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("goods_update_time not between", value1, value2, "goodsUpdateTime");
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