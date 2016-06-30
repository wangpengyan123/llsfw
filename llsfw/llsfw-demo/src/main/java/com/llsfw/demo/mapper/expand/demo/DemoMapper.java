/**
 * PageResultMapperOracle.java
 * Created at 2013-12-02
 * Created by wangkang
 * Copyright (C) llsfw.
 */
package com.llsfw.demo.mapper.expand.demo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * ClassName: IPageResultMapperOracle
 * </p>
 * <p>
 * Description: 分页查询
 * </p>
 * <p>
 * Author: wangkang
 * </p>
 * <p>
 * Date: 2013年12月2日
 * </p>
 */
public interface DemoMapper {

    /**
     * 测试列为空值的时候,map映射的请情况
     * 
     * @return
     */
    List<Map<String, Object>> testNullCol();

    /**
     * 测试分页SQL
     * 
     * @param sql
     * @param pageInfo
     * @return
     */
    List<Map<String, Object>> ttDbsDemoPageQuery(@Param("demoCol") String demoCol);

}
