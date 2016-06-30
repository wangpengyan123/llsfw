/**
 * DynamicDataSource.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.core.datasource;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.llsfw.generator.model.standard.system.TtDynamicDataSource;

/**
 * <p>
 * ClassName: DynamicDataSource
 * </p>
 * <p>
 * Description: 动态数据源
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月23日
 * </p>
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    /**
     * 配置的目标数据库
     */
    private static Map<Object, Object> dyanmictargetDataSources;

    /**
     * 当前所使用的数据源
     */
    private static final ThreadLocal<DataSource> CURRENTDATASOURCE = new ThreadLocal<DataSource>();

    /**
     * <p>
     * Field log: 日志
     * </p>
     */
    private Logger log = LoggerFactory.getLogger(this.getClass()); 

    /**
     * 主数据源名字
     */
    private String defaultDataSourceName;

    /**
     * maxActive : 连接池中可同时连接的最大的连接数(默认值为8,高峰单机器在8并发左右,自己根据应用场景定)
     */
    private int maxActive;

    /**
     * maxIdle :
     * 连接池中最大的空闲的连接数,超过的空闲连接将被释放,如果设置为负数表示不限制(默认为8个,maxIdle不能设置太小,因为假如在高负载的情况下,
     * 连接的打开时间比关闭的时间快,会引起连接池中idle的个数 上升超过maxIdle,而造成频繁的连接销毁和创建,类似于jvm参数中的Xmx设置)
     */
    private int maxIdle;

    /**
     * minIdle :
     * 连接池中最小的空闲的连接数,低于这个数量会被创建新的连接(默认为0,该参数越接近maxIdle,性能越好,因为连接的创建和销毁,都是需要消耗资源的
     * ;但是不能太大,因为在机器很空闲的时候,也会创建低于minidle个数的连接,类似于jvm参数中的Xmn设置)
     */
    private int minIdle;

    /**
     * maxWait :
     * 最大等待时间,当没有可用连接时,连接池等待连接释放的最大时间,超过该时间限制会抛出异常,如果设置-1表示无限等待(默认为无限,避免因线程池不够用,
     * 而导致请求被无限制挂起)
     */
    private int maxWait;

    /**
     * minEvictableIdleTimeMillis : 连接池中连接,在时间段内一直空闲,被逐出连接池的时间
     * ,(默认为30分钟,可以适当做调整,需要和后端服务端的策略配置相关)
     */
    private long minEvictableIdleTimeMillis;

    /**
     * removeAbandonedTimeout : 超过时间限制,回收没有用(废弃)的连接(默认为 300秒)
     */
    private int removeAbandonedTimeout;

    /**
     * removeAbandoned : 超过removeAbandonedTimeout时间后,是否进 行没用连接(废弃)的回收(默认为false)
     */
    private boolean removeAbandoned;

    /**
     * testOnBorrow : 进行borrowObject处理时,对拿到的connection进行validateObject校验
     */
    private boolean testOnBorrow;

    /**
     * testOnReturn :
     * 进行returnObject处理时,对返回的connection进行validateObject校验，个人觉得对数据库连接池的管理意义不大
     */
    private boolean testOnReturn;

    /**
     * testWhileIdle :
     * 关注的重点,GenericObjectPool中针对pool管理,起了一个Evict的TimerTask定时线程进行控制(
     * 可通过设置参数timeBetweenEvictionRunsMillis>0),定时对线程池中的链接进行validateObject校验,
     * 对无效的链接进行关闭后,会调用ensureMinIdle,适当建立链接保证最小的minIdle连接数.
     */
    private boolean testWhileIdle;

    /**
     * timeBetweenEvictionRunsMillis,设置的Evict线程的时间,单位ms,大于0才会开启evict检查线程
     */
    private long timeBetweenEvictionRunsMillis;

    /**
     * numTestsPerEvictionRun : 代表每次检查链接的数量,建议设置和maxActive一样大,这样每次可以有效检查所有的链接.
     */
    private int numTestsPerEvictionRun;

    /**
     * 当前所使用的数据源(如果是默认数据库,则设置为空)
     * 
     * @return 数据源
     */
    public static DataSource getCurrentDataSource() {
        return CURRENTDATASOURCE.get();
    }

    /**
     * 设置当前所使用的数据源(如果是默认数据库,则设置为空)
     * 
     * @param dataSourceName 数据源名称
     */
    private static void setCurrentDataSource(String dataSourceName) {
        Object dataSource = dyanmictargetDataSources.get(dataSourceName);
        if (dataSource != null) {
            CURRENTDATASOURCE.set((DataSource) dataSource);
        } else {
            CURRENTDATASOURCE.set(null);
        }
    }

    static {
        if (null == DynamicDataSource.dyanmictargetDataSources) {
            DynamicDataSource.dyanmictargetDataSources = new HashMap<Object, Object>();
        }
    }

    public void setDefaultDataSourceName(String defaultDataSourceName) {
        this.defaultDataSourceName = defaultDataSourceName;
    }

    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public void setMaxWait(int maxWait) {
        this.maxWait = maxWait;
    }

    public void setMinEvictableIdleTimeMillis(long minEvictableIdleTimeMillis) {
        this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
    }

    public void setRemoveAbandonedTimeout(int removeAbandonedTimeout) {
        this.removeAbandonedTimeout = removeAbandonedTimeout;
    }

    public void setRemoveAbandoned(boolean removeAbandoned) {
        this.removeAbandoned = removeAbandoned;
    }

    public void setTestOnBorrow(boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public void setTestOnReturn(boolean testOnReturn) {
        this.testOnReturn = testOnReturn;
    }

    public void setTestWhileIdle(boolean testWhileIdle) {
        this.testWhileIdle = testWhileIdle;
    }

    public void setTimeBetweenEvictionRunsMillis(long timeBetweenEvictionRunsMillis) {
        this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
    }

    public void setNumTestsPerEvictionRun(int numTestsPerEvictionRun) {
        this.numTestsPerEvictionRun = numTestsPerEvictionRun;
    }

    @Override
    protected Object determineCurrentLookupKey() {
        TtDynamicDataSource dds = DbContextHolder.getDbType();
        String dataSourceName = this.defaultDataSourceName;
        if (dds != null) {
            this.log.debug("Start switching data source : " + dds.getDbsName());
            this.selectDataSource(dds);
            dataSourceName = dds.getDbsName();
        } else {
            this.log.debug("Incoming dataSourceName empty, switching to the default database : " + dataSourceName);
        }
        this.log.debug("Switch to the Data Source : " + dataSourceName);
        setCurrentDataSource(dataSourceName);
        this.log.debug("Sets the current data source : " + dataSourceName);
        DbContextHolder.clearDbType();
        this.log.debug("Cleanup DBContextHolder of ThreadLocal");
        return dataSourceName;
    }

    /**
     * <p>
     * Description: 数据源存在时不做处理，不存在时创建新的数据源链接，并将新数据链接添加至缓存
     * </p>
     * 
     * @param dds 数据源
     */
    private void selectDataSource(TtDynamicDataSource dds) {

        Object obj = DynamicDataSource.dyanmictargetDataSources.get(dds.getDbsName());
        if (obj != null) {
            this.log.debug(dds.getDbsName() + " Already in the cache can be used directly");
            return;
        } else {
            this.log.debug(dds.getDbsName() + " Not in the cache, ready to create a data source object");
            DataSource dataSource = this.getDataSource(dds);
            if (null != dataSource) {
                this.setDataSource(dds, dataSource);
            }
            this.log.debug(dds.getDbsName() + " Data Source has been created and the cache");
        }
    }

    /**
     * 根据数据源信息,创建数据源
     * 
     * @param dds 数据源信息
     * @return 数据源
     */
    private DataSource getDataSource(TtDynamicDataSource dds) {

        // 连接配置
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(dds.getDbsUrl());
        dataSource.setUsername(dds.getDbsUserName());
        dataSource.setPassword(dds.getDbsPassword());
        dataSource.setDriverClassName(dds.getDbsDriverClass());

        // 连接池配置
        dataSource.setMaxActive(this.maxActive);
        dataSource.setMaxIdle(this.maxIdle);
        dataSource.setMinIdle(this.minIdle);
        dataSource.setMaxWait(this.maxWait);
        dataSource.setMinEvictableIdleTimeMillis(this.minEvictableIdleTimeMillis);
        dataSource.setRemoveAbandonedTimeout(this.removeAbandonedTimeout);
        dataSource.setRemoveAbandoned(this.removeAbandoned);

        // 连接重试
        dataSource.setTestOnBorrow(this.testOnBorrow);
        dataSource.setTestOnReturn(this.testOnReturn);
        dataSource.setTestWhileIdle(this.testWhileIdle);
        dataSource.setTimeBetweenEvictionRunsMillis(this.timeBetweenEvictionRunsMillis);
        dataSource.setNumTestsPerEvictionRun(this.numTestsPerEvictionRun);

        this.log.debug("DataSource : " + dds.getDbsName() + " Created");
        return dataSource;

    }

    /**
     * 设置数据源
     * 
     * @param dds 数据源信息
     * @param dataSource 数据源
     */
    private void setDataSource(TtDynamicDataSource dds, DataSource dataSource) {
        DynamicDataSource.dyanmictargetDataSources.put(dds.getDbsName(), dataSource);
        super.setTargetDataSources(DynamicDataSource.dyanmictargetDataSources);
        super.afterPropertiesSet();
        DbContextHolder.setDbType(dds);
    }
}
