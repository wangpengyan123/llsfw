package com.llsfw.webgen.mapper.standard.permissions;

import com.llsfw.webgen.model.standard.permissions.TsUserJob;
import com.llsfw.webgen.model.standard.permissions.TsUserJobCriteria;
import com.llsfw.webgen.model.standard.permissions.TsUserJobKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TsUserJobMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TS_USER_JOB
     *
     * @mbggenerated Tue Mar 08 12:54:05 CST 2016
     */
    int countByExample(TsUserJobCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TS_USER_JOB
     *
     * @mbggenerated Tue Mar 08 12:54:05 CST 2016
     */
    int deleteByExample(TsUserJobCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TS_USER_JOB
     *
     * @mbggenerated Tue Mar 08 12:54:05 CST 2016
     */
    int deleteByPrimaryKey(TsUserJobKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TS_USER_JOB
     *
     * @mbggenerated Tue Mar 08 12:54:05 CST 2016
     */
    int insert(TsUserJob record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TS_USER_JOB
     *
     * @mbggenerated Tue Mar 08 12:54:05 CST 2016
     */
    int insertSelective(TsUserJob record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TS_USER_JOB
     *
     * @mbggenerated Tue Mar 08 12:54:05 CST 2016
     */
    List<TsUserJob> selectByExample(TsUserJobCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TS_USER_JOB
     *
     * @mbggenerated Tue Mar 08 12:54:05 CST 2016
     */
    TsUserJob selectByPrimaryKey(TsUserJobKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TS_USER_JOB
     *
     * @mbggenerated Tue Mar 08 12:54:05 CST 2016
     */
    int updateByExampleSelective(@Param("record") TsUserJob record, @Param("example") TsUserJobCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TS_USER_JOB
     *
     * @mbggenerated Tue Mar 08 12:54:05 CST 2016
     */
    int updateByExample(@Param("record") TsUserJob record, @Param("example") TsUserJobCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TS_USER_JOB
     *
     * @mbggenerated Tue Mar 08 12:54:05 CST 2016
     */
    int updateByPrimaryKeySelective(TsUserJob record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TS_USER_JOB
     *
     * @mbggenerated Tue Mar 08 12:54:05 CST 2016
     */
    int updateByPrimaryKey(TsUserJob record);
}