package com.chatRobot.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ManagerExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ManagerExample() {
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

        public Criteria andManagerIdIsNull() {
            addCriterion("manager_id is null");
            return (Criteria) this;
        }

        public Criteria andManagerIdIsNotNull() {
            addCriterion("manager_id is not null");
            return (Criteria) this;
        }

        public Criteria andManagerIdEqualTo(Integer value) {
            addCriterion("manager_id =", value, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdNotEqualTo(Integer value) {
            addCriterion("manager_id <>", value, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdGreaterThan(Integer value) {
            addCriterion("manager_id >", value, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("manager_id >=", value, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdLessThan(Integer value) {
            addCriterion("manager_id <", value, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdLessThanOrEqualTo(Integer value) {
            addCriterion("manager_id <=", value, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdIn(List<Integer> values) {
            addCriterion("manager_id in", values, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdNotIn(List<Integer> values) {
            addCriterion("manager_id not in", values, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdBetween(Integer value1, Integer value2) {
            addCriterion("manager_id between", value1, value2, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerIdNotBetween(Integer value1, Integer value2) {
            addCriterion("manager_id not between", value1, value2, "managerId");
            return (Criteria) this;
        }

        public Criteria andManagerNameIsNull() {
            addCriterion("manager_name is null");
            return (Criteria) this;
        }

        public Criteria andManagerNameIsNotNull() {
            addCriterion("manager_name is not null");
            return (Criteria) this;
        }

        public Criteria andManagerNameEqualTo(String value) {
            addCriterion("manager_name =", value, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerNameNotEqualTo(String value) {
            addCriterion("manager_name <>", value, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerNameGreaterThan(String value) {
            addCriterion("manager_name >", value, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerNameGreaterThanOrEqualTo(String value) {
            addCriterion("manager_name >=", value, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerNameLessThan(String value) {
            addCriterion("manager_name <", value, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerNameLessThanOrEqualTo(String value) {
            addCriterion("manager_name <=", value, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerNameLike(String value) {
            addCriterion("manager_name like", value, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerNameNotLike(String value) {
            addCriterion("manager_name not like", value, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerNameIn(List<String> values) {
            addCriterion("manager_name in", values, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerNameNotIn(List<String> values) {
            addCriterion("manager_name not in", values, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerNameBetween(String value1, String value2) {
            addCriterion("manager_name between", value1, value2, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerNameNotBetween(String value1, String value2) {
            addCriterion("manager_name not between", value1, value2, "managerName");
            return (Criteria) this;
        }

        public Criteria andManagerAccountIsNull() {
            addCriterion("manager_account is null");
            return (Criteria) this;
        }

        public Criteria andManagerAccountIsNotNull() {
            addCriterion("manager_account is not null");
            return (Criteria) this;
        }

        public Criteria andManagerAccountEqualTo(String value) {
            addCriterion("manager_account =", value, "managerAccount");
            return (Criteria) this;
        }

        public Criteria andManagerAccountNotEqualTo(String value) {
            addCriterion("manager_account <>", value, "managerAccount");
            return (Criteria) this;
        }

        public Criteria andManagerAccountGreaterThan(String value) {
            addCriterion("manager_account >", value, "managerAccount");
            return (Criteria) this;
        }

        public Criteria andManagerAccountGreaterThanOrEqualTo(String value) {
            addCriterion("manager_account >=", value, "managerAccount");
            return (Criteria) this;
        }

        public Criteria andManagerAccountLessThan(String value) {
            addCriterion("manager_account <", value, "managerAccount");
            return (Criteria) this;
        }

        public Criteria andManagerAccountLessThanOrEqualTo(String value) {
            addCriterion("manager_account <=", value, "managerAccount");
            return (Criteria) this;
        }

        public Criteria andManagerAccountLike(String value) {
            addCriterion("manager_account like", value, "managerAccount");
            return (Criteria) this;
        }

        public Criteria andManagerAccountNotLike(String value) {
            addCriterion("manager_account not like", value, "managerAccount");
            return (Criteria) this;
        }

        public Criteria andManagerAccountIn(List<String> values) {
            addCriterion("manager_account in", values, "managerAccount");
            return (Criteria) this;
        }

        public Criteria andManagerAccountNotIn(List<String> values) {
            addCriterion("manager_account not in", values, "managerAccount");
            return (Criteria) this;
        }

        public Criteria andManagerAccountBetween(String value1, String value2) {
            addCriterion("manager_account between", value1, value2, "managerAccount");
            return (Criteria) this;
        }

        public Criteria andManagerAccountNotBetween(String value1, String value2) {
            addCriterion("manager_account not between", value1, value2, "managerAccount");
            return (Criteria) this;
        }

        public Criteria andManagerPasswordIsNull() {
            addCriterion("manager_password is null");
            return (Criteria) this;
        }

        public Criteria andManagerPasswordIsNotNull() {
            addCriterion("manager_password is not null");
            return (Criteria) this;
        }

        public Criteria andManagerPasswordEqualTo(String value) {
            addCriterion("manager_password =", value, "managerPassword");
            return (Criteria) this;
        }

        public Criteria andManagerPasswordNotEqualTo(String value) {
            addCriterion("manager_password <>", value, "managerPassword");
            return (Criteria) this;
        }

        public Criteria andManagerPasswordGreaterThan(String value) {
            addCriterion("manager_password >", value, "managerPassword");
            return (Criteria) this;
        }

        public Criteria andManagerPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("manager_password >=", value, "managerPassword");
            return (Criteria) this;
        }

        public Criteria andManagerPasswordLessThan(String value) {
            addCriterion("manager_password <", value, "managerPassword");
            return (Criteria) this;
        }

        public Criteria andManagerPasswordLessThanOrEqualTo(String value) {
            addCriterion("manager_password <=", value, "managerPassword");
            return (Criteria) this;
        }

        public Criteria andManagerPasswordLike(String value) {
            addCriterion("manager_password like", value, "managerPassword");
            return (Criteria) this;
        }

        public Criteria andManagerPasswordNotLike(String value) {
            addCriterion("manager_password not like", value, "managerPassword");
            return (Criteria) this;
        }

        public Criteria andManagerPasswordIn(List<String> values) {
            addCriterion("manager_password in", values, "managerPassword");
            return (Criteria) this;
        }

        public Criteria andManagerPasswordNotIn(List<String> values) {
            addCriterion("manager_password not in", values, "managerPassword");
            return (Criteria) this;
        }

        public Criteria andManagerPasswordBetween(String value1, String value2) {
            addCriterion("manager_password between", value1, value2, "managerPassword");
            return (Criteria) this;
        }

        public Criteria andManagerPasswordNotBetween(String value1, String value2) {
            addCriterion("manager_password not between", value1, value2, "managerPassword");
            return (Criteria) this;
        }

        public Criteria andManagerEmailIsNull() {
            addCriterion("manager_email is null");
            return (Criteria) this;
        }

        public Criteria andManagerEmailIsNotNull() {
            addCriterion("manager_email is not null");
            return (Criteria) this;
        }

        public Criteria andManagerEmailEqualTo(String value) {
            addCriterion("manager_email =", value, "managerEmail");
            return (Criteria) this;
        }

        public Criteria andManagerEmailNotEqualTo(String value) {
            addCriterion("manager_email <>", value, "managerEmail");
            return (Criteria) this;
        }

        public Criteria andManagerEmailGreaterThan(String value) {
            addCriterion("manager_email >", value, "managerEmail");
            return (Criteria) this;
        }

        public Criteria andManagerEmailGreaterThanOrEqualTo(String value) {
            addCriterion("manager_email >=", value, "managerEmail");
            return (Criteria) this;
        }

        public Criteria andManagerEmailLessThan(String value) {
            addCriterion("manager_email <", value, "managerEmail");
            return (Criteria) this;
        }

        public Criteria andManagerEmailLessThanOrEqualTo(String value) {
            addCriterion("manager_email <=", value, "managerEmail");
            return (Criteria) this;
        }

        public Criteria andManagerEmailLike(String value) {
            addCriterion("manager_email like", value, "managerEmail");
            return (Criteria) this;
        }

        public Criteria andManagerEmailNotLike(String value) {
            addCriterion("manager_email not like", value, "managerEmail");
            return (Criteria) this;
        }

        public Criteria andManagerEmailIn(List<String> values) {
            addCriterion("manager_email in", values, "managerEmail");
            return (Criteria) this;
        }

        public Criteria andManagerEmailNotIn(List<String> values) {
            addCriterion("manager_email not in", values, "managerEmail");
            return (Criteria) this;
        }

        public Criteria andManagerEmailBetween(String value1, String value2) {
            addCriterion("manager_email between", value1, value2, "managerEmail");
            return (Criteria) this;
        }

        public Criteria andManagerEmailNotBetween(String value1, String value2) {
            addCriterion("manager_email not between", value1, value2, "managerEmail");
            return (Criteria) this;
        }

        public Criteria andManagerPictureIsNull() {
            addCriterion("manager_picture is null");
            return (Criteria) this;
        }

        public Criteria andManagerPictureIsNotNull() {
            addCriterion("manager_picture is not null");
            return (Criteria) this;
        }

        public Criteria andManagerPictureEqualTo(String value) {
            addCriterion("manager_picture =", value, "managerPicture");
            return (Criteria) this;
        }

        public Criteria andManagerPictureNotEqualTo(String value) {
            addCriterion("manager_picture <>", value, "managerPicture");
            return (Criteria) this;
        }

        public Criteria andManagerPictureGreaterThan(String value) {
            addCriterion("manager_picture >", value, "managerPicture");
            return (Criteria) this;
        }

        public Criteria andManagerPictureGreaterThanOrEqualTo(String value) {
            addCriterion("manager_picture >=", value, "managerPicture");
            return (Criteria) this;
        }

        public Criteria andManagerPictureLessThan(String value) {
            addCriterion("manager_picture <", value, "managerPicture");
            return (Criteria) this;
        }

        public Criteria andManagerPictureLessThanOrEqualTo(String value) {
            addCriterion("manager_picture <=", value, "managerPicture");
            return (Criteria) this;
        }

        public Criteria andManagerPictureLike(String value) {
            addCriterion("manager_picture like", value, "managerPicture");
            return (Criteria) this;
        }

        public Criteria andManagerPictureNotLike(String value) {
            addCriterion("manager_picture not like", value, "managerPicture");
            return (Criteria) this;
        }

        public Criteria andManagerPictureIn(List<String> values) {
            addCriterion("manager_picture in", values, "managerPicture");
            return (Criteria) this;
        }

        public Criteria andManagerPictureNotIn(List<String> values) {
            addCriterion("manager_picture not in", values, "managerPicture");
            return (Criteria) this;
        }

        public Criteria andManagerPictureBetween(String value1, String value2) {
            addCriterion("manager_picture between", value1, value2, "managerPicture");
            return (Criteria) this;
        }

        public Criteria andManagerPictureNotBetween(String value1, String value2) {
            addCriterion("manager_picture not between", value1, value2, "managerPicture");
            return (Criteria) this;
        }

        public Criteria andManagerPhoneIsNull() {
            addCriterion("manager_phone is null");
            return (Criteria) this;
        }

        public Criteria andManagerPhoneIsNotNull() {
            addCriterion("manager_phone is not null");
            return (Criteria) this;
        }

        public Criteria andManagerPhoneEqualTo(String value) {
            addCriterion("manager_phone =", value, "managerPhone");
            return (Criteria) this;
        }

        public Criteria andManagerPhoneNotEqualTo(String value) {
            addCriterion("manager_phone <>", value, "managerPhone");
            return (Criteria) this;
        }

        public Criteria andManagerPhoneGreaterThan(String value) {
            addCriterion("manager_phone >", value, "managerPhone");
            return (Criteria) this;
        }

        public Criteria andManagerPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("manager_phone >=", value, "managerPhone");
            return (Criteria) this;
        }

        public Criteria andManagerPhoneLessThan(String value) {
            addCriterion("manager_phone <", value, "managerPhone");
            return (Criteria) this;
        }

        public Criteria andManagerPhoneLessThanOrEqualTo(String value) {
            addCriterion("manager_phone <=", value, "managerPhone");
            return (Criteria) this;
        }

        public Criteria andManagerPhoneLike(String value) {
            addCriterion("manager_phone like", value, "managerPhone");
            return (Criteria) this;
        }

        public Criteria andManagerPhoneNotLike(String value) {
            addCriterion("manager_phone not like", value, "managerPhone");
            return (Criteria) this;
        }

        public Criteria andManagerPhoneIn(List<String> values) {
            addCriterion("manager_phone in", values, "managerPhone");
            return (Criteria) this;
        }

        public Criteria andManagerPhoneNotIn(List<String> values) {
            addCriterion("manager_phone not in", values, "managerPhone");
            return (Criteria) this;
        }

        public Criteria andManagerPhoneBetween(String value1, String value2) {
            addCriterion("manager_phone between", value1, value2, "managerPhone");
            return (Criteria) this;
        }

        public Criteria andManagerPhoneNotBetween(String value1, String value2) {
            addCriterion("manager_phone not between", value1, value2, "managerPhone");
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
            addCriterionForJDBCDate("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterionForJDBCDate("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterionForJDBCDate("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("create_time not between", value1, value2, "createTime");
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

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterionForJDBCDate("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterionForJDBCDate("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterionForJDBCDate("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("update_time not between", value1, value2, "updateTime");
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