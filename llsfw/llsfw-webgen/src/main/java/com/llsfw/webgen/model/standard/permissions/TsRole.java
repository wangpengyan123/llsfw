package com.llsfw.webgen.model.standard.permissions;

import java.io.Serializable;
import java.util.Date;

public class TsRole implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TS_ROLE.ROLE_CODE
     *
     * @mbggenerated Tue Mar 08 12:54:05 CST 2016
     */
    private String roleCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TS_ROLE.ROLE_NAME
     *
     * @mbggenerated Tue Mar 08 12:54:05 CST 2016
     */
    private String roleName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TS_ROLE.CREATE_BY
     *
     * @mbggenerated Tue Mar 08 12:54:05 CST 2016
     */
    private String createBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TS_ROLE.CREATE_DATE
     *
     * @mbggenerated Tue Mar 08 12:54:05 CST 2016
     */
    private Date createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TS_ROLE.UPDATE_BY
     *
     * @mbggenerated Tue Mar 08 12:54:05 CST 2016
     */
    private String updateBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column TS_ROLE.UPDATE_DATE
     *
     * @mbggenerated Tue Mar 08 12:54:05 CST 2016
     */
    private Date updateDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table TS_ROLE
     *
     * @mbggenerated Tue Mar 08 12:54:05 CST 2016
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TS_ROLE.ROLE_CODE
     *
     * @return the value of TS_ROLE.ROLE_CODE
     *
     * @mbggenerated Tue Mar 08 12:54:05 CST 2016
     */
    public String getRoleCode() {
        return roleCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TS_ROLE.ROLE_CODE
     *
     * @param roleCode the value for TS_ROLE.ROLE_CODE
     *
     * @mbggenerated Tue Mar 08 12:54:05 CST 2016
     */
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TS_ROLE.ROLE_NAME
     *
     * @return the value of TS_ROLE.ROLE_NAME
     *
     * @mbggenerated Tue Mar 08 12:54:05 CST 2016
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TS_ROLE.ROLE_NAME
     *
     * @param roleName the value for TS_ROLE.ROLE_NAME
     *
     * @mbggenerated Tue Mar 08 12:54:05 CST 2016
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TS_ROLE.CREATE_BY
     *
     * @return the value of TS_ROLE.CREATE_BY
     *
     * @mbggenerated Tue Mar 08 12:54:05 CST 2016
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TS_ROLE.CREATE_BY
     *
     * @param createBy the value for TS_ROLE.CREATE_BY
     *
     * @mbggenerated Tue Mar 08 12:54:05 CST 2016
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TS_ROLE.CREATE_DATE
     *
     * @return the value of TS_ROLE.CREATE_DATE
     *
     * @mbggenerated Tue Mar 08 12:54:05 CST 2016
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TS_ROLE.CREATE_DATE
     *
     * @param createDate the value for TS_ROLE.CREATE_DATE
     *
     * @mbggenerated Tue Mar 08 12:54:05 CST 2016
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TS_ROLE.UPDATE_BY
     *
     * @return the value of TS_ROLE.UPDATE_BY
     *
     * @mbggenerated Tue Mar 08 12:54:05 CST 2016
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TS_ROLE.UPDATE_BY
     *
     * @param updateBy the value for TS_ROLE.UPDATE_BY
     *
     * @mbggenerated Tue Mar 08 12:54:05 CST 2016
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column TS_ROLE.UPDATE_DATE
     *
     * @return the value of TS_ROLE.UPDATE_DATE
     *
     * @mbggenerated Tue Mar 08 12:54:05 CST 2016
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column TS_ROLE.UPDATE_DATE
     *
     * @param updateDate the value for TS_ROLE.UPDATE_DATE
     *
     * @mbggenerated Tue Mar 08 12:54:05 CST 2016
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TS_ROLE
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
        TsRole other = (TsRole) that;
        return (this.getRoleCode() == null ? other.getRoleCode() == null : this.getRoleCode().equals(other.getRoleCode()))
            && (this.getRoleName() == null ? other.getRoleName() == null : this.getRoleName().equals(other.getRoleName()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
            && (this.getUpdateDate() == null ? other.getUpdateDate() == null : this.getUpdateDate().equals(other.getUpdateDate()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TS_ROLE
     *
     * @mbggenerated Tue Mar 08 12:54:05 CST 2016
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRoleCode() == null) ? 0 : getRoleCode().hashCode());
        result = prime * result + ((getRoleName() == null) ? 0 : getRoleName().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getUpdateDate() == null) ? 0 : getUpdateDate().hashCode());
        return result;
    }
}