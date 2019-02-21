package com.chatRobot.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class GoodsModelExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GoodsModelExample() {
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

        public Criteria andModelIdIsNull() {
            addCriterion("model_id is null");
            return (Criteria) this;
        }

        public Criteria andModelIdIsNotNull() {
            addCriterion("model_id is not null");
            return (Criteria) this;
        }

        public Criteria andModelIdEqualTo(Integer value) {
            addCriterion("model_id =", value, "modelId");
            return (Criteria) this;
        }

        public Criteria andModelIdNotEqualTo(Integer value) {
            addCriterion("model_id <>", value, "modelId");
            return (Criteria) this;
        }

        public Criteria andModelIdGreaterThan(Integer value) {
            addCriterion("model_id >", value, "modelId");
            return (Criteria) this;
        }

        public Criteria andModelIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("model_id >=", value, "modelId");
            return (Criteria) this;
        }

        public Criteria andModelIdLessThan(Integer value) {
            addCriterion("model_id <", value, "modelId");
            return (Criteria) this;
        }

        public Criteria andModelIdLessThanOrEqualTo(Integer value) {
            addCriterion("model_id <=", value, "modelId");
            return (Criteria) this;
        }

        public Criteria andModelIdIn(List<Integer> values) {
            addCriterion("model_id in", values, "modelId");
            return (Criteria) this;
        }

        public Criteria andModelIdNotIn(List<Integer> values) {
            addCriterion("model_id not in", values, "modelId");
            return (Criteria) this;
        }

        public Criteria andModelIdBetween(Integer value1, Integer value2) {
            addCriterion("model_id between", value1, value2, "modelId");
            return (Criteria) this;
        }

        public Criteria andModelIdNotBetween(Integer value1, Integer value2) {
            addCriterion("model_id not between", value1, value2, "modelId");
            return (Criteria) this;
        }

        public Criteria andModelNameIsNull() {
            addCriterion("model_name is null");
            return (Criteria) this;
        }

        public Criteria andModelNameIsNotNull() {
            addCriterion("model_name is not null");
            return (Criteria) this;
        }

        public Criteria andModelNameEqualTo(String value) {
            addCriterion("model_name =", value, "modelName");
            return (Criteria) this;
        }

        public Criteria andModelNameNotEqualTo(String value) {
            addCriterion("model_name <>", value, "modelName");
            return (Criteria) this;
        }

        public Criteria andModelNameGreaterThan(String value) {
            addCriterion("model_name >", value, "modelName");
            return (Criteria) this;
        }

        public Criteria andModelNameGreaterThanOrEqualTo(String value) {
            addCriterion("model_name >=", value, "modelName");
            return (Criteria) this;
        }

        public Criteria andModelNameLessThan(String value) {
            addCriterion("model_name <", value, "modelName");
            return (Criteria) this;
        }

        public Criteria andModelNameLessThanOrEqualTo(String value) {
            addCriterion("model_name <=", value, "modelName");
            return (Criteria) this;
        }

        public Criteria andModelNameLike(String value) {
            addCriterion("model_name like", value, "modelName");
            return (Criteria) this;
        }

        public Criteria andModelNameNotLike(String value) {
            addCriterion("model_name not like", value, "modelName");
            return (Criteria) this;
        }

        public Criteria andModelNameIn(List<String> values) {
            addCriterion("model_name in", values, "modelName");
            return (Criteria) this;
        }

        public Criteria andModelNameNotIn(List<String> values) {
            addCriterion("model_name not in", values, "modelName");
            return (Criteria) this;
        }

        public Criteria andModelNameBetween(String value1, String value2) {
            addCriterion("model_name between", value1, value2, "modelName");
            return (Criteria) this;
        }

        public Criteria andModelNameNotBetween(String value1, String value2) {
            addCriterion("model_name not between", value1, value2, "modelName");
            return (Criteria) this;
        }

        public Criteria andModelFileIsNull() {
            addCriterion("model_file is null");
            return (Criteria) this;
        }

        public Criteria andModelFileIsNotNull() {
            addCriterion("model_file is not null");
            return (Criteria) this;
        }

        public Criteria andModelFileEqualTo(String value) {
            addCriterion("model_file =", value, "modelFile");
            return (Criteria) this;
        }

        public Criteria andModelFileNotEqualTo(String value) {
            addCriterion("model_file <>", value, "modelFile");
            return (Criteria) this;
        }

        public Criteria andModelFileGreaterThan(String value) {
            addCriterion("model_file >", value, "modelFile");
            return (Criteria) this;
        }

        public Criteria andModelFileGreaterThanOrEqualTo(String value) {
            addCriterion("model_file >=", value, "modelFile");
            return (Criteria) this;
        }

        public Criteria andModelFileLessThan(String value) {
            addCriterion("model_file <", value, "modelFile");
            return (Criteria) this;
        }

        public Criteria andModelFileLessThanOrEqualTo(String value) {
            addCriterion("model_file <=", value, "modelFile");
            return (Criteria) this;
        }

        public Criteria andModelFileLike(String value) {
            addCriterion("model_file like", value, "modelFile");
            return (Criteria) this;
        }

        public Criteria andModelFileNotLike(String value) {
            addCriterion("model_file not like", value, "modelFile");
            return (Criteria) this;
        }

        public Criteria andModelFileIn(List<String> values) {
            addCriterion("model_file in", values, "modelFile");
            return (Criteria) this;
        }

        public Criteria andModelFileNotIn(List<String> values) {
            addCriterion("model_file not in", values, "modelFile");
            return (Criteria) this;
        }

        public Criteria andModelFileBetween(String value1, String value2) {
            addCriterion("model_file between", value1, value2, "modelFile");
            return (Criteria) this;
        }

        public Criteria andModelFileNotBetween(String value1, String value2) {
            addCriterion("model_file not between", value1, value2, "modelFile");
            return (Criteria) this;
        }

        public Criteria andModelTypeIsNull() {
            addCriterion("model_type is null");
            return (Criteria) this;
        }

        public Criteria andModelTypeIsNotNull() {
            addCriterion("model_type is not null");
            return (Criteria) this;
        }

        public Criteria andModelTypeEqualTo(String value) {
            addCriterion("model_type =", value, "modelType");
            return (Criteria) this;
        }

        public Criteria andModelTypeNotEqualTo(String value) {
            addCriterion("model_type <>", value, "modelType");
            return (Criteria) this;
        }

        public Criteria andModelTypeGreaterThan(String value) {
            addCriterion("model_type >", value, "modelType");
            return (Criteria) this;
        }

        public Criteria andModelTypeGreaterThanOrEqualTo(String value) {
            addCriterion("model_type >=", value, "modelType");
            return (Criteria) this;
        }

        public Criteria andModelTypeLessThan(String value) {
            addCriterion("model_type <", value, "modelType");
            return (Criteria) this;
        }

        public Criteria andModelTypeLessThanOrEqualTo(String value) {
            addCriterion("model_type <=", value, "modelType");
            return (Criteria) this;
        }

        public Criteria andModelTypeLike(String value) {
            addCriterion("model_type like", value, "modelType");
            return (Criteria) this;
        }

        public Criteria andModelTypeNotLike(String value) {
            addCriterion("model_type not like", value, "modelType");
            return (Criteria) this;
        }

        public Criteria andModelTypeIn(List<String> values) {
            addCriterion("model_type in", values, "modelType");
            return (Criteria) this;
        }

        public Criteria andModelTypeNotIn(List<String> values) {
            addCriterion("model_type not in", values, "modelType");
            return (Criteria) this;
        }

        public Criteria andModelTypeBetween(String value1, String value2) {
            addCriterion("model_type between", value1, value2, "modelType");
            return (Criteria) this;
        }

        public Criteria andModelTypeNotBetween(String value1, String value2) {
            addCriterion("model_type not between", value1, value2, "modelType");
            return (Criteria) this;
        }

        public Criteria andModelCreateTimeIsNull() {
            addCriterion("model_create_time is null");
            return (Criteria) this;
        }

        public Criteria andModelCreateTimeIsNotNull() {
            addCriterion("model_create_time is not null");
            return (Criteria) this;
        }

        public Criteria andModelCreateTimeEqualTo(Date value) {
            addCriterionForJDBCDate("model_create_time =", value, "modelCreateTime");
            return (Criteria) this;
        }

        public Criteria andModelCreateTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("model_create_time <>", value, "modelCreateTime");
            return (Criteria) this;
        }

        public Criteria andModelCreateTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("model_create_time >", value, "modelCreateTime");
            return (Criteria) this;
        }

        public Criteria andModelCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("model_create_time >=", value, "modelCreateTime");
            return (Criteria) this;
        }

        public Criteria andModelCreateTimeLessThan(Date value) {
            addCriterionForJDBCDate("model_create_time <", value, "modelCreateTime");
            return (Criteria) this;
        }

        public Criteria andModelCreateTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("model_create_time <=", value, "modelCreateTime");
            return (Criteria) this;
        }

        public Criteria andModelCreateTimeIn(List<Date> values) {
            addCriterionForJDBCDate("model_create_time in", values, "modelCreateTime");
            return (Criteria) this;
        }

        public Criteria andModelCreateTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("model_create_time not in", values, "modelCreateTime");
            return (Criteria) this;
        }

        public Criteria andModelCreateTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("model_create_time between", value1, value2, "modelCreateTime");
            return (Criteria) this;
        }

        public Criteria andModelCreateTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("model_create_time not between", value1, value2, "modelCreateTime");
            return (Criteria) this;
        }

        public Criteria andModelUpdateTimeIsNull() {
            addCriterion("model_update_time is null");
            return (Criteria) this;
        }

        public Criteria andModelUpdateTimeIsNotNull() {
            addCriterion("model_update_time is not null");
            return (Criteria) this;
        }

        public Criteria andModelUpdateTimeEqualTo(Date value) {
            addCriterionForJDBCDate("model_update_time =", value, "modelUpdateTime");
            return (Criteria) this;
        }

        public Criteria andModelUpdateTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("model_update_time <>", value, "modelUpdateTime");
            return (Criteria) this;
        }

        public Criteria andModelUpdateTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("model_update_time >", value, "modelUpdateTime");
            return (Criteria) this;
        }

        public Criteria andModelUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("model_update_time >=", value, "modelUpdateTime");
            return (Criteria) this;
        }

        public Criteria andModelUpdateTimeLessThan(Date value) {
            addCriterionForJDBCDate("model_update_time <", value, "modelUpdateTime");
            return (Criteria) this;
        }

        public Criteria andModelUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("model_update_time <=", value, "modelUpdateTime");
            return (Criteria) this;
        }

        public Criteria andModelUpdateTimeIn(List<Date> values) {
            addCriterionForJDBCDate("model_update_time in", values, "modelUpdateTime");
            return (Criteria) this;
        }

        public Criteria andModelUpdateTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("model_update_time not in", values, "modelUpdateTime");
            return (Criteria) this;
        }

        public Criteria andModelUpdateTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("model_update_time between", value1, value2, "modelUpdateTime");
            return (Criteria) this;
        }

        public Criteria andModelUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("model_update_time not between", value1, value2, "modelUpdateTime");
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