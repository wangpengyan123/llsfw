package com.llsfw.generator.mapper.standard.system;

import com.llsfw.generator.model.standard.system.TtScheduledLog;
import com.llsfw.generator.model.standard.system.TtScheduledLogCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TtScheduledLogMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_SCHEDULED_LOG
     *
     * @mbggenerated Mon Dec 07 22:45:14 CST 2015
     */
    int countByExample(TtScheduledLogCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_SCHEDULED_LOG
     *
     * @mbggenerated Mon Dec 07 22:45:14 CST 2015
     */
    int deleteByExample(TtScheduledLogCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_SCHEDULED_LOG
     *
     * @mbggenerated Mon Dec 07 22:45:14 CST 2015
     */
    int deleteByPrimaryKey(String logid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_SCHEDULED_LOG
     *
     * @mbggenerated Mon Dec 07 22:45:14 CST 2015
     */
    int insert(TtScheduledLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_SCHEDULED_LOG
     *
     * @mbggenerated Mon Dec 07 22:45:14 CST 2015
     */
    int insertSelective(TtScheduledLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_SCHEDULED_LOG
     *
     * @mbggenerated Mon Dec 07 22:45:14 CST 2015
     */
    List<TtScheduledLog> selectByExample(TtScheduledLogCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_SCHEDULED_LOG
     *
     * @mbggenerated Mon Dec 07 22:45:14 CST 2015
     */
    TtScheduledLog selectByPrimaryKey(String logid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_SCHEDULED_LOG
     *
     * @mbggenerated Mon Dec 07 22:45:14 CST 2015
     */
    int updateByExampleSelective(@Param("record") TtScheduledLog record, @Param("example") TtScheduledLogCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_SCHEDULED_LOG
     *
     * @mbggenerated Mon Dec 07 22:45:14 CST 2015
     */
    int updateByExample(@Param("record") TtScheduledLog record, @Param("example") TtScheduledLogCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_SCHEDULED_LOG
     *
     * @mbggenerated Mon Dec 07 22:45:14 CST 2015
     */
    int updateByPrimaryKeySelective(TtScheduledLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table TT_SCHEDULED_LOG
     *
     * @mbggenerated Mon Dec 07 22:45:14 CST 2015
     */
    int updateByPrimaryKey(TtScheduledLog record);
}