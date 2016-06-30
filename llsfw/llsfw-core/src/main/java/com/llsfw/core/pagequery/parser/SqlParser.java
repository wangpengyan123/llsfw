/**
 * SqlParser.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.core.pagequery.parser;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.llsfw.core.exception.SystemException;
import com.llsfw.core.exception.SystemRuntimeException;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.FromItem;
import net.sf.jsqlparser.statement.select.Join;
import net.sf.jsqlparser.statement.select.LateralSubSelect;
import net.sf.jsqlparser.statement.select.OrderByElement;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectBody;
import net.sf.jsqlparser.statement.select.SelectExpressionItem;
import net.sf.jsqlparser.statement.select.SelectItem;
import net.sf.jsqlparser.statement.select.SetOperationList;
import net.sf.jsqlparser.statement.select.SubJoin;
import net.sf.jsqlparser.statement.select.SubSelect;
import net.sf.jsqlparser.statement.select.ValuesList;
import net.sf.jsqlparser.statement.select.WithItem;

/**
 * 分页查询,去除order by处理,参考开源项目Mybatis_PageHelper
 * 
 * @author Administrator
 *
 */
public class SqlParser {

    /**
     * <p>
     * Field COUNT_ITEM: COUNT_ITEM
     * </p>
     */
    private static final List<SelectItem> COUNT_ITEM;
    /**
     * <p>
     * Field TABLE_ALIAS: TABLE_ALIAS
     * </p>
     */
    private static final Alias TABLE_ALIAS;

    /**
     * <p>
     * Field log: 日志
     * </p>
     */
    private Logger log = LoggerFactory.getLogger(this.getClass());

    static {
        COUNT_ITEM = new ArrayList<SelectItem>();
        COUNT_ITEM.add(new SelectExpressionItem(new Column("count(*)")));

        TABLE_ALIAS = new Alias("table_count");
        TABLE_ALIAS.setUseAs(false);
    }

    /**
     * 获取智能的countSql
     *
     * @param sql sql
     * @return 优化后的sql
     * @throws SystemException 异常
     */
    public String getSmartCountSql(String sql) {
        // 校验是否支持该sql
        isSupportedSql(sql);
        // 解析SQL
        Statement stmt = null;
        try {
            stmt = CCJSqlParserUtil.parse(sql);
        } catch (JSQLParserException e) {
            // 无法解析的用一般方法返回count语句
            this.log.info("无法解析的用一般方法返回count语句:", e);
            return getSimpleCountSql(sql);
        }
        Select select = (Select) stmt;
        SelectBody selectBody = select.getSelectBody();
        // 处理body-去order by
        processSelectBody(selectBody);
        // 处理with-去order by
        processWithItemsList(select.getWithItemsList());
        // 处理为count查询
        sqlToCount(select);
        // 返回
        return select.toString();
    }

    /**
     * 获取普通的Count-sql
     *
     * @param sql 原查询sql
     * @return 返回count查询sql
     * @throws SystemException
     */
    private String getSimpleCountSql(String sql) {
        isSupportedSql(sql);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select count(*) from (");
        stringBuilder.append(sql);
        stringBuilder.append(") tmp_count");
        return stringBuilder.toString();
    }

    /**
     * 将sql转换为count查询
     *
     * @param select 查询对象
     */
    private void sqlToCount(Select select) {
        SelectBody selectBody = select.getSelectBody();
        // 是否能简化count查询
        if (selectBody instanceof PlainSelect && isSimpleCount((PlainSelect) selectBody)) {
            ((PlainSelect) selectBody).setSelectItems(COUNT_ITEM);
        } else {
            PlainSelect plainSelect = new PlainSelect();
            SubSelect subSelect = new SubSelect();
            subSelect.setSelectBody(selectBody);
            subSelect.setAlias(TABLE_ALIAS);
            plainSelect.setFromItem(subSelect);
            plainSelect.setSelectItems(COUNT_ITEM);
            select.setSelectBody(plainSelect);
        }
    }

    /**
     * 是否可以用简单的count查询方式
     *
     * @param select 查询对象
     * @return 结果
     */
    private boolean isSimpleCount(PlainSelect select) {
        // 包含group by的时候不可以
        if (select.getGroupByColumnReferences() != null) {
            return false;
        }
        // 包含distinct的时候不可以
        if (select.getDistinct() != null) {
            return false;
        }
        for (SelectItem item : select.getSelectItems()) {
            // select列中包含参数的时候不可以，否则会引起参数个数错误
            if (item.toString().contains("?")) {
                return false;
            }
            // 如果查询列中包含函数，也不可以，函数可能会聚合列
            if (item instanceof SelectExpressionItem && checkSelectItem(item)) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>
     * Description: 检查查询项
     * </p>
     * 
     * @param item 查询项
     * @return 结果
     */
    private static boolean checkSelectItem(SelectItem item) {
        return ((SelectExpressionItem) item).getExpression() instanceof Function;
    }

    /**
     * 处理WithItem
     *
     * @param withItemsList withItemsList
     */
    private void processWithItemsList(List<WithItem> withItemsList) {
        if (withItemsList != null && !withItemsList.isEmpty()) {
            for (WithItem item : withItemsList) {
                processSelectBody(item.getSelectBody());
            }
        }
    }

    /**
     * 处理selectBody去除Order by
     *
     * @param selectBody 查询主体
     */
    private void processSelectBody(SelectBody selectBody) {
        if (selectBody instanceof PlainSelect) {
            processPlainSelect((PlainSelect) selectBody);
        } else if (selectBody instanceof WithItem) {
            WithItem withItem = (WithItem) selectBody;
            if (withItem.getSelectBody() != null) {
                processSelectBody(withItem.getSelectBody());
            }
        } else {
            SetOperationList operationList = (SetOperationList) selectBody;
            if (operationList.getSelects() != null && !operationList.getSelects().isEmpty()) {
                List<SelectBody> plainSelects = operationList.getSelects();
                for (SelectBody plainSelect : plainSelects) {
                    processSelectBody(plainSelect);
                }
            }
            if (!orderByHashParameters(operationList.getOrderByElements())) {
                operationList.setOrderByElements(null);
            }
        }
    }

    /**
     * 处理PlainSelect类型的selectBody
     *
     * @param plainSelect plainSelect
     */
    private void processPlainSelect(PlainSelect plainSelect) {
        if (!orderByHashParameters(plainSelect.getOrderByElements())) {
            plainSelect.setOrderByElements(null);
        }
        if (plainSelect.getFromItem() != null) {
            processFromItem(plainSelect.getFromItem());
        }
        if (plainSelect.getJoins() != null && !plainSelect.getJoins().isEmpty()) {
            List<Join> joins = plainSelect.getJoins();
            for (Join join : joins) {
                if (join.getRightItem() != null) {
                    processFromItem(join.getRightItem());
                }
            }
        }
    }

    /**
     * 处理子查询
     *
     * @param fromItem 子查询
     */
    private void processFromItem(FromItem fromItem) {
        if (fromItem instanceof SubJoin) {
            SubJoin subJoin = (SubJoin) fromItem;
            if (subJoin.getJoin() != null && subJoin.getJoin().getRightItem() != null) {
                processFromItem(subJoin.getJoin().getRightItem());
            }
            if (subJoin.getLeft() != null) {
                processFromItem(subJoin.getLeft());
            }
        } else if (fromItem instanceof SubSelect) {
            SubSelect subSelect = (SubSelect) fromItem;
            if (subSelect.getSelectBody() != null) {
                processSelectBody(subSelect.getSelectBody());
            }
        } else if (fromItem instanceof ValuesList) {
            // 不用做处理
        } else if (fromItem instanceof LateralSubSelect) {
            LateralSubSelect lateralSubSelect = (LateralSubSelect) fromItem;
            if (lateralSubSelect.getSubSelect() != null) {
                SubSelect subSelect = lateralSubSelect.getSubSelect();
                if (subSelect.getSelectBody() != null) {
                    processSelectBody(subSelect.getSelectBody());
                }
            }
        }
        // Table时不用处理
    }

    /**
     * 判断Orderby是否包含参数，有参数的不能去
     *
     * @param orderByElements orderby 项目
     * @return 结果
     */
    private boolean orderByHashParameters(List<OrderByElement> orderByElements) {
        if (orderByElements == null) {
            return false;
        }
        for (OrderByElement orderByElement : orderByElements) {
            if (orderByElement.toString().contains("?")) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否支持分页插件
     * 
     * @param sql 语句
     * @throws SystemException 异常
     */
    private void isSupportedSql(String sql) {
        if (sql.trim().toUpperCase().endsWith("FOR UPDATE")) {
            throw new SystemRuntimeException("分页插件不支持包含for update的sql");
        }
    }
}
