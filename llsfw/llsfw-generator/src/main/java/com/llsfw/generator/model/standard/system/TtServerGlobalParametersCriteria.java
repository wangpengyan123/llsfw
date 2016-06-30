package com.llsfw.generator.model.standard.system;

import java.util.ArrayList;
import java.util.List;

public class TtServerGlobalParametersCriteria {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table TT_SERVER_GLOBAL_PARAMETERS
     *
     * @mbggenerated Mon Dec 07 22:45:14 CST 2015
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table TT_SERVER_GLOBAL_PARAMETERS
     *
     * @mbggenerated Mon Dec 07 22:45:14 CST 2015
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table TT_SERVER_GLOBAL_PARAMETERS
     *
     * @mbggenerated Mon Dec 07 22:45:14 CST 2015
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_SERVER_GLOBAL_PARAMETERS
     *
     * @mbggenerated Mon Dec 07 22:45:14 CST 2015
     */
    public TtServerGlobalParametersCriteria() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_SERVER_GLOBAL_PARAMETERS
     *
     * @mbggenerated Mon Dec 07 22:45:14 CST 2015
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_SERVER_GLOBAL_PARAMETERS
     *
     * @mbggenerated Mon Dec 07 22:45:14 CST 2015
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_SERVER_GLOBAL_PARAMETERS
     *
     * @mbggenerated Mon Dec 07 22:45:14 CST 2015
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_SERVER_GLOBAL_PARAMETERS
     *
     * @mbggenerated Mon Dec 07 22:45:14 CST 2015
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_SERVER_GLOBAL_PARAMETERS
     *
     * @mbggenerated Mon Dec 07 22:45:14 CST 2015
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_SERVER_GLOBAL_PARAMETERS
     *
     * @mbggenerated Mon Dec 07 22:45:14 CST 2015
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_SERVER_GLOBAL_PARAMETERS
     *
     * @mbggenerated Mon Dec 07 22:45:14 CST 2015
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_SERVER_GLOBAL_PARAMETERS
     *
     * @mbggenerated Mon Dec 07 22:45:14 CST 2015
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
     * This method corresponds to the database table TT_SERVER_GLOBAL_PARAMETERS
     *
     * @mbggenerated Mon Dec 07 22:45:14 CST 2015
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_SERVER_GLOBAL_PARAMETERS
     *
     * @mbggenerated Mon Dec 07 22:45:14 CST 2015
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table TT_SERVER_GLOBAL_PARAMETERS
     *
     * @mbggenerated Mon Dec 07 22:45:14 CST 2015
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

        public Criteria andParametersCodeIsNull() {
            addCriterion("PARAMETERS_CODE is null");
            return (Criteria) this;
        }

        public Criteria andParametersCodeIsNotNull() {
            addCriterion("PARAMETERS_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andParametersCodeEqualTo(String value) {
            addCriterion("PARAMETERS_CODE =", value, "parametersCode");
            return (Criteria) this;
        }

        public Criteria andParametersCodeNotEqualTo(String value) {
            addCriterion("PARAMETERS_CODE <>", value, "parametersCode");
            return (Criteria) this;
        }

        public Criteria andParametersCodeGreaterThan(String value) {
            addCriterion("PARAMETERS_CODE >", value, "parametersCode");
            return (Criteria) this;
        }

        public Criteria andParametersCodeGreaterThanOrEqualTo(String value) {
            addCriterion("PARAMETERS_CODE >=", value, "parametersCode");
            return (Criteria) this;
        }

        public Criteria andParametersCodeLessThan(String value) {
            addCriterion("PARAMETERS_CODE <", value, "parametersCode");
            return (Criteria) this;
        }

        public Criteria andParametersCodeLessThanOrEqualTo(String value) {
            addCriterion("PARAMETERS_CODE <=", value, "parametersCode");
            return (Criteria) this;
        }

        public Criteria andParametersCodeLike(String value) {
            addCriterion("PARAMETERS_CODE like", value, "parametersCode");
            return (Criteria) this;
        }

        public Criteria andParametersCodeNotLike(String value) {
            addCriterion("PARAMETERS_CODE not like", value, "parametersCode");
            return (Criteria) this;
        }

        public Criteria andParametersCodeIn(List<String> values) {
            addCriterion("PARAMETERS_CODE in", values, "parametersCode");
            return (Criteria) this;
        }

        public Criteria andParametersCodeNotIn(List<String> values) {
            addCriterion("PARAMETERS_CODE not in", values, "parametersCode");
            return (Criteria) this;
        }

        public Criteria andParametersCodeBetween(String value1, String value2) {
            addCriterion("PARAMETERS_CODE between", value1, value2, "parametersCode");
            return (Criteria) this;
        }

        public Criteria andParametersCodeNotBetween(String value1, String value2) {
            addCriterion("PARAMETERS_CODE not between", value1, value2, "parametersCode");
            return (Criteria) this;
        }

        public Criteria andParametersValueIsNull() {
            addCriterion("PARAMETERS_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andParametersValueIsNotNull() {
            addCriterion("PARAMETERS_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andParametersValueEqualTo(String value) {
            addCriterion("PARAMETERS_VALUE =", value, "parametersValue");
            return (Criteria) this;
        }

        public Criteria andParametersValueNotEqualTo(String value) {
            addCriterion("PARAMETERS_VALUE <>", value, "parametersValue");
            return (Criteria) this;
        }

        public Criteria andParametersValueGreaterThan(String value) {
            addCriterion("PARAMETERS_VALUE >", value, "parametersValue");
            return (Criteria) this;
        }

        public Criteria andParametersValueGreaterThanOrEqualTo(String value) {
            addCriterion("PARAMETERS_VALUE >=", value, "parametersValue");
            return (Criteria) this;
        }

        public Criteria andParametersValueLessThan(String value) {
            addCriterion("PARAMETERS_VALUE <", value, "parametersValue");
            return (Criteria) this;
        }

        public Criteria andParametersValueLessThanOrEqualTo(String value) {
            addCriterion("PARAMETERS_VALUE <=", value, "parametersValue");
            return (Criteria) this;
        }

        public Criteria andParametersValueLike(String value) {
            addCriterion("PARAMETERS_VALUE like", value, "parametersValue");
            return (Criteria) this;
        }

        public Criteria andParametersValueNotLike(String value) {
            addCriterion("PARAMETERS_VALUE not like", value, "parametersValue");
            return (Criteria) this;
        }

        public Criteria andParametersValueIn(List<String> values) {
            addCriterion("PARAMETERS_VALUE in", values, "parametersValue");
            return (Criteria) this;
        }

        public Criteria andParametersValueNotIn(List<String> values) {
            addCriterion("PARAMETERS_VALUE not in", values, "parametersValue");
            return (Criteria) this;
        }

        public Criteria andParametersValueBetween(String value1, String value2) {
            addCriterion("PARAMETERS_VALUE between", value1, value2, "parametersValue");
            return (Criteria) this;
        }

        public Criteria andParametersValueNotBetween(String value1, String value2) {
            addCriterion("PARAMETERS_VALUE not between", value1, value2, "parametersValue");
            return (Criteria) this;
        }

        public Criteria andParametersDescIsNull() {
            addCriterion("PARAMETERS_DESC is null");
            return (Criteria) this;
        }

        public Criteria andParametersDescIsNotNull() {
            addCriterion("PARAMETERS_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andParametersDescEqualTo(String value) {
            addCriterion("PARAMETERS_DESC =", value, "parametersDesc");
            return (Criteria) this;
        }

        public Criteria andParametersDescNotEqualTo(String value) {
            addCriterion("PARAMETERS_DESC <>", value, "parametersDesc");
            return (Criteria) this;
        }

        public Criteria andParametersDescGreaterThan(String value) {
            addCriterion("PARAMETERS_DESC >", value, "parametersDesc");
            return (Criteria) this;
        }

        public Criteria andParametersDescGreaterThanOrEqualTo(String value) {
            addCriterion("PARAMETERS_DESC >=", value, "parametersDesc");
            return (Criteria) this;
        }

        public Criteria andParametersDescLessThan(String value) {
            addCriterion("PARAMETERS_DESC <", value, "parametersDesc");
            return (Criteria) this;
        }

        public Criteria andParametersDescLessThanOrEqualTo(String value) {
            addCriterion("PARAMETERS_DESC <=", value, "parametersDesc");
            return (Criteria) this;
        }

        public Criteria andParametersDescLike(String value) {
            addCriterion("PARAMETERS_DESC like", value, "parametersDesc");
            return (Criteria) this;
        }

        public Criteria andParametersDescNotLike(String value) {
            addCriterion("PARAMETERS_DESC not like", value, "parametersDesc");
            return (Criteria) this;
        }

        public Criteria andParametersDescIn(List<String> values) {
            addCriterion("PARAMETERS_DESC in", values, "parametersDesc");
            return (Criteria) this;
        }

        public Criteria andParametersDescNotIn(List<String> values) {
            addCriterion("PARAMETERS_DESC not in", values, "parametersDesc");
            return (Criteria) this;
        }

        public Criteria andParametersDescBetween(String value1, String value2) {
            addCriterion("PARAMETERS_DESC between", value1, value2, "parametersDesc");
            return (Criteria) this;
        }

        public Criteria andParametersDescNotBetween(String value1, String value2) {
            addCriterion("PARAMETERS_DESC not between", value1, value2, "parametersDesc");
            return (Criteria) this;
        }

        public Criteria andParametersCodeLikeInsensitive(String value) {
            addCriterion("upper(PARAMETERS_CODE) like", value.toUpperCase(), "parametersCode");
            return (Criteria) this;
        }

        public Criteria andParametersValueLikeInsensitive(String value) {
            addCriterion("upper(PARAMETERS_VALUE) like", value.toUpperCase(), "parametersValue");
            return (Criteria) this;
        }

        public Criteria andParametersDescLikeInsensitive(String value) {
            addCriterion("upper(PARAMETERS_DESC) like", value.toUpperCase(), "parametersDesc");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table TT_SERVER_GLOBAL_PARAMETERS
     *
     * @mbggenerated do_not_delete_during_merge Mon Dec 07 22:45:14 CST 2015
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table TT_SERVER_GLOBAL_PARAMETERS
     *
     * @mbggenerated Mon Dec 07 22:45:14 CST 2015
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