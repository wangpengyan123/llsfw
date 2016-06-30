package com.llsfw.generator.model.standard.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TtOnlineSessionHisCriteria {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table TT_ONLINE_SESSION_HIS
     *
     * @mbggenerated Fri Jan 15 16:04:44 CST 2016
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table TT_ONLINE_SESSION_HIS
     *
     * @mbggenerated Fri Jan 15 16:04:44 CST 2016
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table TT_ONLINE_SESSION_HIS
     *
     * @mbggenerated Fri Jan 15 16:04:44 CST 2016
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_ONLINE_SESSION_HIS
     *
     * @mbggenerated Fri Jan 15 16:04:44 CST 2016
     */
    public TtOnlineSessionHisCriteria() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_ONLINE_SESSION_HIS
     *
     * @mbggenerated Fri Jan 15 16:04:44 CST 2016
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_ONLINE_SESSION_HIS
     *
     * @mbggenerated Fri Jan 15 16:04:44 CST 2016
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_ONLINE_SESSION_HIS
     *
     * @mbggenerated Fri Jan 15 16:04:44 CST 2016
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_ONLINE_SESSION_HIS
     *
     * @mbggenerated Fri Jan 15 16:04:44 CST 2016
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_ONLINE_SESSION_HIS
     *
     * @mbggenerated Fri Jan 15 16:04:44 CST 2016
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_ONLINE_SESSION_HIS
     *
     * @mbggenerated Fri Jan 15 16:04:44 CST 2016
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_ONLINE_SESSION_HIS
     *
     * @mbggenerated Fri Jan 15 16:04:44 CST 2016
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_ONLINE_SESSION_HIS
     *
     * @mbggenerated Fri Jan 15 16:04:44 CST 2016
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_ONLINE_SESSION_HIS
     *
     * @mbggenerated Fri Jan 15 16:04:44 CST 2016
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_ONLINE_SESSION_HIS
     *
     * @mbggenerated Fri Jan 15 16:04:44 CST 2016
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table TT_ONLINE_SESSION_HIS
     *
     * @mbggenerated Fri Jan 15 16:04:44 CST 2016
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

        public Criteria andSessionIdIsNull() {
            addCriterion("SESSION_ID is null");
            return (Criteria) this;
        }

        public Criteria andSessionIdIsNotNull() {
            addCriterion("SESSION_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSessionIdEqualTo(String value) {
            addCriterion("SESSION_ID =", value, "sessionId");
            return (Criteria) this;
        }

        public Criteria andSessionIdNotEqualTo(String value) {
            addCriterion("SESSION_ID <>", value, "sessionId");
            return (Criteria) this;
        }

        public Criteria andSessionIdGreaterThan(String value) {
            addCriterion("SESSION_ID >", value, "sessionId");
            return (Criteria) this;
        }

        public Criteria andSessionIdGreaterThanOrEqualTo(String value) {
            addCriterion("SESSION_ID >=", value, "sessionId");
            return (Criteria) this;
        }

        public Criteria andSessionIdLessThan(String value) {
            addCriterion("SESSION_ID <", value, "sessionId");
            return (Criteria) this;
        }

        public Criteria andSessionIdLessThanOrEqualTo(String value) {
            addCriterion("SESSION_ID <=", value, "sessionId");
            return (Criteria) this;
        }

        public Criteria andSessionIdLike(String value) {
            addCriterion("SESSION_ID like", value, "sessionId");
            return (Criteria) this;
        }

        public Criteria andSessionIdNotLike(String value) {
            addCriterion("SESSION_ID not like", value, "sessionId");
            return (Criteria) this;
        }

        public Criteria andSessionIdIn(List<String> values) {
            addCriterion("SESSION_ID in", values, "sessionId");
            return (Criteria) this;
        }

        public Criteria andSessionIdNotIn(List<String> values) {
            addCriterion("SESSION_ID not in", values, "sessionId");
            return (Criteria) this;
        }

        public Criteria andSessionIdBetween(String value1, String value2) {
            addCriterion("SESSION_ID between", value1, value2, "sessionId");
            return (Criteria) this;
        }

        public Criteria andSessionIdNotBetween(String value1, String value2) {
            addCriterion("SESSION_ID not between", value1, value2, "sessionId");
            return (Criteria) this;
        }

        public Criteria andSessionCreateDateIsNull() {
            addCriterion("SESSION_CREATE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andSessionCreateDateIsNotNull() {
            addCriterion("SESSION_CREATE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andSessionCreateDateEqualTo(Long value) {
            addCriterion("SESSION_CREATE_DATE =", value, "sessionCreateDate");
            return (Criteria) this;
        }

        public Criteria andSessionCreateDateNotEqualTo(Long value) {
            addCriterion("SESSION_CREATE_DATE <>", value, "sessionCreateDate");
            return (Criteria) this;
        }

        public Criteria andSessionCreateDateGreaterThan(Long value) {
            addCriterion("SESSION_CREATE_DATE >", value, "sessionCreateDate");
            return (Criteria) this;
        }

        public Criteria andSessionCreateDateGreaterThanOrEqualTo(Long value) {
            addCriterion("SESSION_CREATE_DATE >=", value, "sessionCreateDate");
            return (Criteria) this;
        }

        public Criteria andSessionCreateDateLessThan(Long value) {
            addCriterion("SESSION_CREATE_DATE <", value, "sessionCreateDate");
            return (Criteria) this;
        }

        public Criteria andSessionCreateDateLessThanOrEqualTo(Long value) {
            addCriterion("SESSION_CREATE_DATE <=", value, "sessionCreateDate");
            return (Criteria) this;
        }

        public Criteria andSessionCreateDateIn(List<Long> values) {
            addCriterion("SESSION_CREATE_DATE in", values, "sessionCreateDate");
            return (Criteria) this;
        }

        public Criteria andSessionCreateDateNotIn(List<Long> values) {
            addCriterion("SESSION_CREATE_DATE not in", values, "sessionCreateDate");
            return (Criteria) this;
        }

        public Criteria andSessionCreateDateBetween(Long value1, Long value2) {
            addCriterion("SESSION_CREATE_DATE between", value1, value2, "sessionCreateDate");
            return (Criteria) this;
        }

        public Criteria andSessionCreateDateNotBetween(Long value1, Long value2) {
            addCriterion("SESSION_CREATE_DATE not between", value1, value2, "sessionCreateDate");
            return (Criteria) this;
        }

        public Criteria andSessionLastAccessedTimeIsNull() {
            addCriterion("SESSION_LAST_ACCESSED_TIME is null");
            return (Criteria) this;
        }

        public Criteria andSessionLastAccessedTimeIsNotNull() {
            addCriterion("SESSION_LAST_ACCESSED_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andSessionLastAccessedTimeEqualTo(Long value) {
            addCriterion("SESSION_LAST_ACCESSED_TIME =", value, "sessionLastAccessedTime");
            return (Criteria) this;
        }

        public Criteria andSessionLastAccessedTimeNotEqualTo(Long value) {
            addCriterion("SESSION_LAST_ACCESSED_TIME <>", value, "sessionLastAccessedTime");
            return (Criteria) this;
        }

        public Criteria andSessionLastAccessedTimeGreaterThan(Long value) {
            addCriterion("SESSION_LAST_ACCESSED_TIME >", value, "sessionLastAccessedTime");
            return (Criteria) this;
        }

        public Criteria andSessionLastAccessedTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("SESSION_LAST_ACCESSED_TIME >=", value, "sessionLastAccessedTime");
            return (Criteria) this;
        }

        public Criteria andSessionLastAccessedTimeLessThan(Long value) {
            addCriterion("SESSION_LAST_ACCESSED_TIME <", value, "sessionLastAccessedTime");
            return (Criteria) this;
        }

        public Criteria andSessionLastAccessedTimeLessThanOrEqualTo(Long value) {
            addCriterion("SESSION_LAST_ACCESSED_TIME <=", value, "sessionLastAccessedTime");
            return (Criteria) this;
        }

        public Criteria andSessionLastAccessedTimeIn(List<Long> values) {
            addCriterion("SESSION_LAST_ACCESSED_TIME in", values, "sessionLastAccessedTime");
            return (Criteria) this;
        }

        public Criteria andSessionLastAccessedTimeNotIn(List<Long> values) {
            addCriterion("SESSION_LAST_ACCESSED_TIME not in", values, "sessionLastAccessedTime");
            return (Criteria) this;
        }

        public Criteria andSessionLastAccessedTimeBetween(Long value1, Long value2) {
            addCriterion("SESSION_LAST_ACCESSED_TIME between", value1, value2, "sessionLastAccessedTime");
            return (Criteria) this;
        }

        public Criteria andSessionLastAccessedTimeNotBetween(Long value1, Long value2) {
            addCriterion("SESSION_LAST_ACCESSED_TIME not between", value1, value2, "sessionLastAccessedTime");
            return (Criteria) this;
        }

        public Criteria andSessionMaxInactiveIntervalIsNull() {
            addCriterion("SESSION_MAX_INACTIVE_INTERVAL is null");
            return (Criteria) this;
        }

        public Criteria andSessionMaxInactiveIntervalIsNotNull() {
            addCriterion("SESSION_MAX_INACTIVE_INTERVAL is not null");
            return (Criteria) this;
        }

        public Criteria andSessionMaxInactiveIntervalEqualTo(Long value) {
            addCriterion("SESSION_MAX_INACTIVE_INTERVAL =", value, "sessionMaxInactiveInterval");
            return (Criteria) this;
        }

        public Criteria andSessionMaxInactiveIntervalNotEqualTo(Long value) {
            addCriterion("SESSION_MAX_INACTIVE_INTERVAL <>", value, "sessionMaxInactiveInterval");
            return (Criteria) this;
        }

        public Criteria andSessionMaxInactiveIntervalGreaterThan(Long value) {
            addCriterion("SESSION_MAX_INACTIVE_INTERVAL >", value, "sessionMaxInactiveInterval");
            return (Criteria) this;
        }

        public Criteria andSessionMaxInactiveIntervalGreaterThanOrEqualTo(Long value) {
            addCriterion("SESSION_MAX_INACTIVE_INTERVAL >=", value, "sessionMaxInactiveInterval");
            return (Criteria) this;
        }

        public Criteria andSessionMaxInactiveIntervalLessThan(Long value) {
            addCriterion("SESSION_MAX_INACTIVE_INTERVAL <", value, "sessionMaxInactiveInterval");
            return (Criteria) this;
        }

        public Criteria andSessionMaxInactiveIntervalLessThanOrEqualTo(Long value) {
            addCriterion("SESSION_MAX_INACTIVE_INTERVAL <=", value, "sessionMaxInactiveInterval");
            return (Criteria) this;
        }

        public Criteria andSessionMaxInactiveIntervalIn(List<Long> values) {
            addCriterion("SESSION_MAX_INACTIVE_INTERVAL in", values, "sessionMaxInactiveInterval");
            return (Criteria) this;
        }

        public Criteria andSessionMaxInactiveIntervalNotIn(List<Long> values) {
            addCriterion("SESSION_MAX_INACTIVE_INTERVAL not in", values, "sessionMaxInactiveInterval");
            return (Criteria) this;
        }

        public Criteria andSessionMaxInactiveIntervalBetween(Long value1, Long value2) {
            addCriterion("SESSION_MAX_INACTIVE_INTERVAL between", value1, value2, "sessionMaxInactiveInterval");
            return (Criteria) this;
        }

        public Criteria andSessionMaxInactiveIntervalNotBetween(Long value1, Long value2) {
            addCriterion("SESSION_MAX_INACTIVE_INTERVAL not between", value1, value2, "sessionMaxInactiveInterval");
            return (Criteria) this;
        }

        public Criteria andSessionDestoryedTimeIsNull() {
            addCriterion("SESSION_DESTORYED_TIME is null");
            return (Criteria) this;
        }

        public Criteria andSessionDestoryedTimeIsNotNull() {
            addCriterion("SESSION_DESTORYED_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andSessionDestoryedTimeEqualTo(Date value) {
            addCriterion("SESSION_DESTORYED_TIME =", value, "sessionDestoryedTime");
            return (Criteria) this;
        }

        public Criteria andSessionDestoryedTimeNotEqualTo(Date value) {
            addCriterion("SESSION_DESTORYED_TIME <>", value, "sessionDestoryedTime");
            return (Criteria) this;
        }

        public Criteria andSessionDestoryedTimeGreaterThan(Date value) {
            addCriterion("SESSION_DESTORYED_TIME >", value, "sessionDestoryedTime");
            return (Criteria) this;
        }

        public Criteria andSessionDestoryedTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("SESSION_DESTORYED_TIME >=", value, "sessionDestoryedTime");
            return (Criteria) this;
        }

        public Criteria andSessionDestoryedTimeLessThan(Date value) {
            addCriterion("SESSION_DESTORYED_TIME <", value, "sessionDestoryedTime");
            return (Criteria) this;
        }

        public Criteria andSessionDestoryedTimeLessThanOrEqualTo(Date value) {
            addCriterion("SESSION_DESTORYED_TIME <=", value, "sessionDestoryedTime");
            return (Criteria) this;
        }

        public Criteria andSessionDestoryedTimeIn(List<Date> values) {
            addCriterion("SESSION_DESTORYED_TIME in", values, "sessionDestoryedTime");
            return (Criteria) this;
        }

        public Criteria andSessionDestoryedTimeNotIn(List<Date> values) {
            addCriterion("SESSION_DESTORYED_TIME not in", values, "sessionDestoryedTime");
            return (Criteria) this;
        }

        public Criteria andSessionDestoryedTimeBetween(Date value1, Date value2) {
            addCriterion("SESSION_DESTORYED_TIME between", value1, value2, "sessionDestoryedTime");
            return (Criteria) this;
        }

        public Criteria andSessionDestoryedTimeNotBetween(Date value1, Date value2) {
            addCriterion("SESSION_DESTORYED_TIME not between", value1, value2, "sessionDestoryedTime");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("CREATE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("CREATE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("CREATE_DATE =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("CREATE_DATE <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("CREATE_DATE >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_DATE >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("CREATE_DATE <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_DATE <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("CREATE_DATE in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("CREATE_DATE not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("CREATE_DATE between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_DATE not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andSessionIdLikeInsensitive(String value) {
            addCriterion("upper(SESSION_ID) like", value.toUpperCase(), "sessionId");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table TT_ONLINE_SESSION_HIS
     *
     * @mbggenerated do_not_delete_during_merge Fri Jan 15 16:04:44 CST 2016
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table TT_ONLINE_SESSION_HIS
     *
     * @mbggenerated Fri Jan 15 16:04:44 CST 2016
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