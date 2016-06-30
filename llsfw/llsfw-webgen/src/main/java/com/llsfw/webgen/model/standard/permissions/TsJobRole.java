package com.llsfw.webgen.model.standard.permissions;

import java.io.Serializable;
import java.util.Date;

public class TsJobRole extends TsJobRoleKey implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TS_JOB_ROLE.CREATE_BY
     *
     * @mbggenerated Tue Mar 08 12:54:05 CST 2016
     */
    private String createBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TS_JOB_ROLE.CREATE_DATE
     *
     * @mbggenerated Tue Mar 08 12:54:05 CST 2016
     */
    private Date createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table TS_JOB_ROLE
     *
     * @mbggenerated Tue Mar 08 12:54:05 CST 2016
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TS_JOB_ROLE.CREATE_BY
     *
     * @return the value of TS_JOB_ROLE.CREATE_BY
     *
     * @mbggenerated Tue Mar 08 12:54:05 CST 2016
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TS_JOB_ROLE.CREATE_BY
     *
     * @param createBy the value for TS_JOB_ROLE.CREATE_BY
     *
     * @mbggenerated Tue Mar 08 12:54:05 CST 2016
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TS_JOB_ROLE.CREATE_DATE
     *
     * @return the value of TS_JOB_ROLE.CREATE_DATE
     *
     * @mbggenerated Tue Mar 08 12:54:05 CST 2016
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TS_JOB_ROLE.CREATE_DATE
     *
     * @param createDate the value for TS_JOB_ROLE.CREATE_DATE
     *
     * @mbggenerated Tue Mar 08 12:54:05 CST 2016
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TS_JOB_ROLE
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
        TsJobRole other = (TsJobRole) that;
        return (this.getRoleCode() == null ? other.getRoleCode() == null : this.getRoleCode().equals(other.getRoleCode()))
            && (this.getJobCode() == null ? other.getJobCode() == null : this.getJobCode().equals(other.getJobCode()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TS_JOB_ROLE
     *
     * @mbggenerated Tue Mar 08 12:54:05 CST 2016
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRoleCode() == null) ? 0 : getRoleCode().hashCode());
        result = prime * result + ((getJobCode() == null) ? 0 : getJobCode().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        return result;
    }
}