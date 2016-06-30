/**
 * PageInterceptor.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.core.pagequery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

//import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.Executor;
//import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.MappedStatement.Builder;
import org.apache.ibatis.mapping.ParameterMapping;
//import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
//import org.apache.ibatis.reflection.MetaObject;
//import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
//import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
//import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
//import org.apache.ibatis.type.TypeHandler;
//import org.apache.ibatis.type.TypeHandlerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.llsfw.core.common.Constants;
import com.llsfw.core.datasource.DynamicDataSource;
import com.llsfw.core.exception.SystemException;
import com.llsfw.core.exception.SystemRuntimeException;
import com.llsfw.core.pagequery.parser.SqlParser;

/**
 * <p>
 * ClassName: PageInterceptor
 * </p>
 * <p>
 * Description: 分页插件
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月23日
 * </p>
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@Intercepts(@Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,
        RowBounds.class, ResultHandler.class }))
public class PageInterceptor implements Interceptor {

    /**
     * 本地变量
     */
    private static final ThreadLocal<PageResult> LOCAL_PAGE = new ThreadLocal<PageResult>();

    /**
     * <p>
     * Field log: 日志
     * </p>
     */
    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 方言
     */
    private String dialect;

    public String getDialect() {
        return this.dialect;
    }

    public void setDialect(String dialect) {
        this.dialect = dialect;
    }

    /**
     * 获取Page参数
     *
     * @return 分页对象
     */
    private static PageResult getLocalPage() {
        return LOCAL_PAGE.get();
    }

    /**
     * 设置Page参数
     * 
     * @param page 分页结果
     */
    private static void setLocalPage(PageResult page) {
        LOCAL_PAGE.set(page);
    }

    /**
     * 移除本地变量
     */
    private static void clearLocalPage() {
        LOCAL_PAGE.remove();
    }

    /**
     * 开始分页
     *
     * @param pageSize 每页显示数量
     * @param curPage 页码
     * @throws SystemException
     */
    public static void startPage(int pageSize, int curPage) {

        // 规范参数
        if (pageSize <= 0) {
            throw new SystemRuntimeException("pageSize <= 0");
        }
        if (curPage <= 0) {
            throw new SystemRuntimeException("curpage <= 0");
        }

        // 实例化分页对象
        PageResult page = new PageResult();
        page.setPageSize(pageSize); // 设置每页条数
        page.setCurPage(curPage); // 设置当前页

        // 保存对象至本地变量中
        setLocalPage(page);

    }

    /**
     * 开始分页
     * 
     * @param pageSize 每页数量
     * @param curPage 当前页数
     * @param totalRecords 总记录数
     * @throws SystemException 异常
     */
    public static void startPage(int pageSize, int curPage, int totalRecords) {

        // 规范参数
        if (totalRecords < 0) {
            throw new SystemRuntimeException("totalRecords < 0");
        }

        // 设置总行数
        startPage(pageSize, curPage);
        PageResult page = getLocalPage();
        page.setTotalRecords(totalRecords);

        // 保存对象至本地变量中
        setLocalPage(page);

    }

    /**
     * 将结果集转换为PageResult
     * 
     * @param result 结果集
     * @param <E> 泛型
     * @return 结果集
     */
    public static <E> PageResult<E> getPageResult(List<E> result) {
        return (PageResult<E>) result;
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        try {
            // 获得分页对象
            PageResult pr = getLocalPage();

            // 获取必要参数
            MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
            String id = mappedStatement.getId();
            this.log.debug("MappedStatement id = " + id);
            // 判断是否需要分页
            if (pr != null) {
                Object parameter = invocation.getArgs()[1];
                BoundSql boundSql = mappedStatement.getBoundSql(parameter);
                String sql = boundSql.getSql();
                // 获得链接
                Connection connection = getConnection(mappedStatement);
                // 查询总行数,如果当前PageResult对象包含总页数,则这里不做查询
                if (!pr.isHasTotalRecords()) {
                    int count = this.countRecords(connection, sql, mappedStatement, boundSql);
                    pr.setTotalRecords(count);
                }
                // 计算页数
                if (pr.getTotalRecords() > 0) {
                    int totalPages = pr.getTotalRecords() / pr.getPageSize()
                            + ((pr.getTotalRecords() % pr.getPageSize() > 0) ? 1 : 0);
                    pr.setTotalPages(totalPages); // 设置总页数
                    if (pr.getCurPage() > pr.getTotalPages()) {
                        pr.setCurPage(pr.getTotalPages());
                    }
                } else {
                    pr.setCurPage(1); // 没有数据,默认当前为第一页
                    pr.setTotalPages(0); // 没有数据,总页数为0
                }
                // 生成分页SQL
                String pageSql = generatePageSql(sql, pr);
                invocation.getArgs()[Constants.NUMBER_2] = RowBounds.DEFAULT;
                MappedStatement newMappedStatement = copyFromNewSql(mappedStatement, boundSql, pageSql,
                        boundSql.getParameterMappings(), boundSql.getParameterObject());
                invocation.getArgs()[0] = newMappedStatement;
                // 执行分页查询
                Object result = invocation.proceed();
                // 得到处理结果
                pr.addAll((List) result);
                // 分页返回值
                return pr;
            }
            // 常规返回值
            return invocation.proceed();
        } finally {
            // 清理本地变量
            clearLocalPage();
        }
    }

    /**
     * 复制MappedStatement
     * 
     * @param ms MappedStatement
     * @param newSqlSource newSqlSource
     * @return 结果
     */
    private static MappedStatement copyFromMappedStatement(MappedStatement ms, SqlSource newSqlSource) {
        Builder builder = new Builder(ms.getConfiguration(), ms.getId(), newSqlSource, ms.getSqlCommandType());
        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        String[] keys = ms.getKeyProperties();
        if (keys != null) {
            String keysstr = Arrays.toString(keys);
            keysstr = keysstr.replace("[", "");
            keysstr = keysstr.replace("]", "");
            builder.keyProperty(keysstr);
        }
        builder.timeout(ms.getTimeout());
        builder.parameterMap(ms.getParameterMap());
        builder.resultMaps(ms.getResultMaps());
        builder.cache(ms.getCache());
        builder.databaseId(ms.getDatabaseId());
        builder.flushCacheRequired(ms.isFlushCacheRequired());
        String[] keyColumns = ms.getKeyColumns();
        if (keyColumns != null) {
            String keysstr = Arrays.toString(keyColumns);
            keysstr = keysstr.replace("[", "");
            keysstr = keysstr.replace("]", "");
            builder.keyColumn(keysstr);
        }
        builder.lang(ms.getLang());
        String[] resulSets = ms.getResulSets();
        if (resulSets != null) {
            String keysstr = Arrays.toString(resulSets);
            keysstr = keysstr.replace("[", "");
            keysstr = keysstr.replace("]", "");
            builder.resulSets(keysstr);
        }
        builder.resultSetType(ms.getResultSetType());
        builder.statementType(ms.getStatementType());
        builder.useCache(ms.isUseCache());
        return builder.build();
    }

    /**
     * 复制MappedStatement
     * 
     * @param mappedStatement mappedStatement
     * @param boundSql boundSql
     * @param sql sql
     * @param parameterMappings parameterMappings
     * @param parameter parameter
     * @return 结果
     */
    private static MappedStatement copyFromNewSql(MappedStatement mappedStatement, BoundSql boundSql, String sql,
            List<ParameterMapping> parameterMappings, Object parameter) {
        BoundSql newBoundSql = copyFromBoundSql(mappedStatement, boundSql, sql, parameterMappings, parameter);
        return copyFromMappedStatement(mappedStatement, new BoundSqlSqlSource(newBoundSql));
    }

    /**
     * 复制MappedStatement
     * 
     * @param mappedStatement mappedStatement
     * @param boundSql boundSql
     * @param sql sql
     * @param parameterMappings parameterMappings
     * @param parameter parameter
     * @return 结果
     */
    private static BoundSql copyFromBoundSql(MappedStatement mappedStatement, BoundSql boundSql, String sql,
            List<ParameterMapping> parameterMappings, Object parameter) {
        BoundSql newBoundSql = new BoundSql(mappedStatement.getConfiguration(), sql, parameterMappings, parameter);
        for (ParameterMapping mapping : boundSql.getParameterMappings()) {
            String prop = mapping.getProperty();
            if (boundSql.hasAdditionalParameter(prop)) {
                newBoundSql.setAdditionalParameter(prop, boundSql.getAdditionalParameter(prop));
            }
        }
        return newBoundSql;
    }

    /**
     * 获得数据库连接
     * 
     * @param mappedStatement mappedStatement
     * @return 数据库连接
     * @throws SQLException 异常
     */
    private static Connection getConnection(MappedStatement mappedStatement) throws SQLException {
        DataSource dataSource = DynamicDataSource.getCurrentDataSource();
        if (dataSource == null) {
            dataSource = mappedStatement.getConfiguration().getEnvironment().getDataSource();
        }
        return dataSource.getConnection();
    }

    /**
     * 生成分页SQL语句
     * 
     * @param sql sql语句
     * @param pr 分页信息
     * @return 分页sql语句
     */
    private String generatePageSql(String sql, PageResult pr) {
        if (pr != null && (this.dialect != null && !"".equals(this.dialect))) {
            StringBuilder pageSql = new StringBuilder();
            if ("mysql".equals(this.dialect)) {
                pageSql.append(sql);
                pageSql.append(" LIMIT " + (pr.getCurPage() - 1) * pr.getPageSize() + "," + pr.getPageSize());
            } else if ("oracle".equals(this.dialect)) {
                pageSql.append(" SELECT * FROM (SELECT TMP_TB.*,ROWNUM ROW_ID FROM ( ");
                pageSql.append(sql);
                pageSql.append(" )  TMP_TB WHERE ROWNUM <= ");
                pageSql.append(pr.getPageSize() + ((pr.getCurPage() - 1) * pr.getPageSize()));
                pageSql.append(" ) WHERE ROW_ID >= ");
                pageSql.append(1 + ((pr.getCurPage() - 1) * pr.getPageSize()));
            }
            return pageSql.toString();
        } else {
            return sql;
        }
    }

    /**
     * 获得总记录数
     * 
     * @param connection connection
     * @param originalSql originalSql
     * @param mappedStatement mappedStatement
     * @param boundSql boundSql
     * @return 总记录数
     * @throws SQLException 异常
     */
    private int countRecords(Connection connection, String originalSql, MappedStatement mappedStatement,
            BoundSql boundSql) throws SQLException {
        SqlParser sqlParser = null;
        PreparedStatement countStmt = null;
        ResultSet rs = null;
        try {
            int totalRecorld = 0;
            sqlParser = new SqlParser();
            Object paramObject = boundSql.getParameterObject();
            String countSql = sqlParser.getSmartCountSql(originalSql);

            //            BoundSql countBs = new BoundSql(mappedStatement.getConfiguration(), originalSql,
            //                    boundSql.getParameterMappings(), paramObject);
            ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, paramObject, boundSql);
            countStmt = connection.prepareStatement(countSql);
            parameterHandler.setParameters(countStmt);
            //            setParameters(countStmt, mappedStatement, countBs, paramObject);
            rs = countStmt.executeQuery();
            if (rs.next()) {
                totalRecorld = rs.getInt(1);
            }
            return totalRecorld;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                this.log.error("error:", e);
            }
            try {
                if (countStmt != null) {
                    countStmt.close();
                }
            } catch (SQLException e) {
                this.log.error("error:", e);
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                this.log.error("error:", e);
            }
        }
    }

    //    /**
    //     * 设置SQL参数
    //     * 
    //     * @param ps ps
    //     * @param mappedStatement mappedStatement
    //     * @param boundSql boundSql
    //     * @param parameterObject parameterObject
    //     * @throws SQLException SQLException
    //     */
    //    private void setParameters(PreparedStatement ps, MappedStatement mappedStatement, BoundSql boundSql,
    //            Object parameterObject) throws SQLException {
    //        ErrorContext.instance().activity("setting parameters").object(mappedStatement.getParameterMap().getId());
    //        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
    //        if (parameterMappings != null) {
    //            Configuration configuration = mappedStatement.getConfiguration();
    //            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
    //            MetaObject metaObject = parameterObject == null ? null : configuration.newMetaObject(parameterObject);
    //            for (int i = 0; i < parameterMappings.size(); i++) {
    //                ParameterMapping parameterMapping = parameterMappings.get(i);
    //                if (parameterMapping.getMode() != ParameterMode.OUT) {
    //                    Object value;
    //                    String propertyName = parameterMapping.getProperty();
    //                    PropertyTokenizer prop = new PropertyTokenizer(propertyName);
    //                    value = this.setParameters(parameterObject, typeHandlerRegistry, boundSql, propertyName,
    //                            configuration, prop, metaObject);
    //                    TypeHandler typeHandler = parameterMapping.getTypeHandler();
    //                    setParameters(typeHandler, mappedStatement, propertyName);
    //                    typeHandler.setParameter(ps, i + 1, value, parameterMapping.getJdbcType());
    //                }
    //            }
    //        }
    //    }

    //    /**
    //     * <p>
    //     * Description: 设置参数
    //     * </p>
    //     * 
    //     * @param typeHandler typeHandler
    //     * @param mappedStatement mappedStatement
    //     * @param propertyName propertyName
    //     */
    //    private static void setParameters(TypeHandler typeHandler, MappedStatement mappedStatement,
    //      String propertyName) {
    //        if (typeHandler == null) {
    //            throw new ExecutorException("There was no TypeHandler found for parameter " + propertyName
    //                    + " of statement " + mappedStatement.getId());
    //        }
    //    }

    //    /**
    //     * <p>
    //     * Description: 设置参数
    //     * </p>
    //     * 
    //     * @param parameterObject parameterObject
    //     * @param typeHandlerRegistry typeHandlerRegistry
    //     * @param boundSql boundSql
    //     * @param propertyName propertyName
    //     * @param configuration configuration
    //     * @param prop prop
    //     * @param metaObject metaObject
    //     * @return 结果
    //     */
    //    private Object setParameters(Object parameterObject, TypeHandlerRegistry typeHandlerRegistry,
    //      BoundSql boundSql,
    //            String propertyName, Configuration configuration, PropertyTokenizer prop, MetaObject metaObject) {
    //        Object value;
    //        if (parameterObject == null) {
    //            value = null;
    //        } else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
    //            value = parameterObject;
    //        } else if (boundSql.hasAdditionalParameter(propertyName)) {
    //            value = boundSql.getAdditionalParameter(propertyName);
    //        } else if (propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX)
    //                && boundSql.hasAdditionalParameter(prop.getName())) {
    //            value = boundSql.getAdditionalParameter(prop.getName());
    //            if (value != null) {
    //                value = configuration.newMetaObject(value).getValue(
    //      propertyName.substring(prop.getName().length()));
    //            }
    //        } else {
    //            value = metaObject == null ? null : metaObject.getValue(propertyName);
    //        }
    //        return value;
    //    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }

}
