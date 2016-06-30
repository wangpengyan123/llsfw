/**
 * EngineDataBase.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.activiti.controller.settings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.ManagementService;
import org.activiti.engine.management.TableMetaData;
import org.activiti.engine.management.TablePage;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.llsfw.core.common.Constants;
import com.llsfw.core.common.DataGridJsonResult;
import com.llsfw.core.controller.base.BaseController;
import com.llsfw.core.pagequery.PageResult;

/**
 * <p>
 * ClassName: EngineDataBase
 * </p>
 * <p>
 * Description: 引擎数据库
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月24日
 * </p>
 */
@Controller
@RequestMapping(value = "activiti/settings/engineDataBase")
public class EngineDataBase extends BaseController {

    /**
     * <p>
     * Field managementService: 管理服务
     * </p>
     */
    @Autowired
    ManagementService managementService;

    /**
     * <p>
     * Description: 初始化
     * </p>
     * 
     * @param req 请求对象
     * @return 页面
     */
    @RequiresPermissions("engineDataBase:view")
    @RequestMapping("init")
    public String init(HttpServletRequest req) {
        // 将国际化信息转换成脚本放入作用域
        this.getLocalStript(req, "activiti.settings.engineDataBase.");
        return "llsfw/activiti/settings/engineDataBase/engineDataBaseMain";
    }

    /**
     * <p>
     * Description: 加载引擎数据库
     * </p>
     * 
     * @return 结果
     */
    @RequiresPermissions("engineDataBase:view")
    @RequestMapping("loadEngineDataBaseTablesName")
    @ResponseBody
    public DataGridJsonResult<Map<String, Object>> loadEngineDataBaseTablesName() {
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        // 读取表
        Map<String, Long> tableCount = this.managementService.getTableCount();
        List<String> keys = new ArrayList<String>();
        keys.addAll(tableCount.keySet());
        for (String key : keys) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("TABLE_NAME", key);
            item.put("TABLE_COUNT", tableCount.get(key));
            data.add(item);
        }
        // 返回
        return new DataGridJsonResult<Map<String, Object>>(data.size(), data);
    }

    /**
     * <p>
     * Description: 加载引擎表数据
     * </p>
     * 
     * @param page 页数
     * @param rows 行数
     * @param tableName 表名
     * @return 结果
     */
    @RequiresPermissions("engineDataBase:view")
    @RequestMapping("loadEngineDataBaseTableData")
    @ResponseBody
    public DataGridJsonResult<Map<String, Object>> loadEngineDataBaseTableData(int page, int rows, String tableName) {
        // 获得总数
        Long tableCount = this.managementService.getTableCount().get(tableName);

        // 获得分页数据
        TablePage tablePages = this.managementService.createTablePageQuery().tableName(tableName)
                .listPage(PageResult.getFistResult(page, rows), rows);

        // 结果集处理
        for (int i = 0; i < tablePages.getRows().size(); i++) {
            Map<String, Object> item = tablePages.getRows().get(i);
            Iterator<String> iterator = item.keySet().iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                if (item.get(key) != null) {
                    tablePages.getRows().get(i).put(key, item.get(key).toString());
                }
            }
        }

        // 返回
        return new DataGridJsonResult<Map<String, Object>>(tableCount.intValue(), tablePages.getRows());
    }

    /**
     * <p>
     * Description: 加载表行数
     * </p>
     * 
     * @param req 请求对象
     * @param tableName 表名
     * @return 结果
     * @throws JsonProcessingException 异常
     */
    @RequiresPermissions("engineDataBase:view")
    @RequestMapping("toloadEngineDataBaseTablesRows")
    public String toloadEngineDataBaseTablesRows(HttpServletRequest req, String tableName)
            throws JsonProcessingException {
        TableMetaData tableMetaData = this.managementService.getTableMetaData(tableName);
        List<String> columnNames = tableMetaData.getColumnNames();
        List<Map<String, Object>> columns = new ArrayList<Map<String, Object>>();
        for (String col : columnNames) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("title", col);
            item.put("field", col);
            item.put("align", "left");
            item.put("width", Constants.NUMBER_200);
            columns.add(item);
        }
        req.setAttribute("columns", new ObjectMapper().writeValueAsString(columns));
        req.setAttribute("tableName", tableName);
        return "llsfw/activiti/settings/engineDataBase/engineDataBaseTableRows";
    }

}
