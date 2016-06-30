package com.llsfw.webgen.mapper.standard.permissions;

import com.llsfw.webgen.model.standard.permissions.TsOrganization;
import com.llsfw.webgen.model.standard.permissions.TsOrganizationCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TsOrganizationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TS_ORGANIZATION
     *
     * @mbggenerated Tue Mar 08 12:54:05 CST 2016
     */
    int countByExample(TsOrganizationCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TS_ORGANIZATION
     *
     * @mbggenerated Tue Mar 08 12:54:05 CST 2016
     */
    int deleteByExample(TsOrganizationCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TS_ORGANIZATION
     *
     * @mbggenerated Tue Mar 08 12:54:05 CST 2016
     */
    int deleteByPrimaryKey(String orgCode);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TS_ORGANIZATION
     *
     * @mbggenerated Tue Mar 08 12:54:05 CST 2016
     */
    int insert(TsOrganization record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TS_ORGANIZATION
     *
     * @mbggenerated Tue Mar 08 12:54:05 CST 2016
     */
    int insertSelective(TsOrganization record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TS_ORGANIZATION
     *
     * @mbggenerated Tue Mar 08 12:54:05 CST 2016
     */
    List<TsOrganization> selectByExample(TsOrganizationCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TS_ORGANIZATION
     *
     * @mbggenerated Tue Mar 08 12:54:05 CST 2016
     */
    TsOrganization selectByPrimaryKey(String orgCode);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TS_ORGANIZATION
     *
     * @mbggenerated Tue Mar 08 12:54:05 CST 2016
     */
    int updateByExampleSelective(@Param("record") TsOrganization record, @Param("example") TsOrganizationCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TS_ORGANIZATION
     *
     * @mbggenerated Tue Mar 08 12:54:05 CST 2016
     */
    int updateByExample(@Param("record") TsOrganization record, @Param("example") TsOrganizationCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TS_ORGANIZATION
     *
     * @mbggenerated Tue Mar 08 12:54:05 CST 2016
     */
    int updateByPrimaryKeySelective(TsOrganization record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TS_ORGANIZATION
     *
     * @mbggenerated Tue Mar 08 12:54:05 CST 2016
     */
    int updateByPrimaryKey(TsOrganization record);
}