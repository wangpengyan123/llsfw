package com.llsfw.webgen.model.standard.permissions;

import java.io.Serializable;

public class TsUserFunctionKey implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TS_USER_FUNCTION.LOGIN_NAME
     *
     * @mbggenerated Tue Mar 08 12:54:05 CST 2016
     */
    private String loginName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TS_USER_FUNCTION.FUNCTION_CODE
     *
     * @mbggenerated Tue Mar 08 12:54:05 CST 2016
     */
    private String functionCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TS_USER_FUNCTION.PURVIEW_CODE
     *
     * @mbggenerated Tue Mar 08 12:54:05 CST 2016
     */
    private String purviewCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table TS_USER_FUNCTION
     *
     * @mbggenerated Tue Mar 08 12:54:05 CST 2016
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TS_USER_FUNCTION.LOGIN_NAME
     *
     * @return the value of TS_USER_FUNCTION.LOGIN_NAME
     *
     * @mbggenerated Tue Mar 08 12:54:05 CST 2016
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TS_USER_FUNCTION.LOGIN_NAME
     *
     * @param loginName the value for TS_USER_FUNCTION.LOGIN_NAME
     *
     * @mbggenerated Tue Mar 08 12:54:05 CST 2016
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TS_USER_FUNCTION.FUNCTION_CODE
     *
     * @return the value of TS_USER_FUNCTION.FUNCTION_CODE
     *
     * @mbggenerated Tue Mar 08 12:54:05 CST 2016
     */
    public String getFunctionCode() {
        return functionCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TS_USER_FUNCTION.FUNCTION_CODE
     *
     * @param functionCode the value for TS_USER_FUNCTION.FUNCTION_CODE
     *
     * @mbggenerated Tue Mar 08 12:54:05 CST 2016
     */
    public void setFunctionCode(String functionCode) {
        this.functionCode = functionCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TS_USER_FUNCTION.PURVIEW_CODE
     *
     * @return the value of TS_USER_FUNCTION.PURVIEW_CODE
     *
     * @mbggenerated Tue Mar 08 12:54:05 CST 2016
     */
    public String getPurviewCode() {
        return purviewCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TS_USER_FUNCTION.PURVIEW_CODE
     *
     * @param purviewCode the value for TS_USER_FUNCTION.PURVIEW_CODE
     *
     * @mbggenerated Tue Mar 08 12:54:05 CST 2016
     */
    public void setPurviewCode(String purviewCode) {
        this.purviewCode = purviewCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TS_USER_FUNCTION
     *
     * @mbggenerated Tue Mar 08 12:54:05 CST 2016
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        TsUserFunctionKey other = (TsUserFunctionKey) that;
        return (this.getLoginName() == null ? other.getLoginName() == null : this.getLoginName().equals(other.getLoginName()))
            && (this.getFunctionCode() == null ? other.getFunctionCode() == null : this.getFunctionCode().equals(other.getFunctionCode()))
            && (this.getPurviewCode() == null ? other.getPurviewCode() == null : this.getPurviewCode().equals(other.getPurviewCode()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TS_USER_FUNCTION
     *
     * @mbggenerated Tue Mar 08 12:54:05 CST 2016
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getLoginName() == null) ? 0 : getLoginName().hashCode());
        result = prime * result + ((getFunctionCode() == null) ? 0 : getFunctionCode().hashCode());
        result = prime * result + ((getPurviewCode() == null) ? 0 : getPurviewCode().hashCode());
        return result;
    }
}