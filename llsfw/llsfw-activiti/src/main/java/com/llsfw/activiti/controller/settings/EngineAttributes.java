/**
 * EngineAttributes.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.activiti.controller.settings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.ManagementService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.llsfw.core.common.DataGridJsonResult;
import com.llsfw.core.controller.base.BaseController;

/**
 * <p>
 * ClassName: EngineAttributes
 * </p>
 * <p>
 * Description: 引擎参数
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月24日
 * </p>
 */
@Controller
@RequestMapping(value = "activiti/settings/engineAttributes")
public class EngineAttributes extends BaseController {

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
    @RequiresPermissions("engineAttributes:view")
    @RequestMapping("init")
    public String init(HttpServletRequest req) {
        // 将国际化信息转换成脚本放入作用域
        this.getLocalStript(req, "activiti.settings.engineAttributes.");
        return "llsfw/activiti/settings/engineAttributes/engineAttributesMain";
    }

    /**
     * <p>
     * Description: 加载引擎参数
     * </p>
     * 
     * @return 结果
     */
    @RequiresPermissions("engineAttributes:view")
    @RequestMapping("loadEngineAttributesData")
    @ResponseBody
    public DataGridJsonResult<Map<String, String>> loadEngineAttributesData() {
        List<Map<String, String>> data = new ArrayList<Map<String, String>>();
        // 引擎配置
        Map<String, String> engineProperties = this.managementService.getProperties();
        Set<String> enginePropertiesKeys = engineProperties.keySet();
        for (String key : enginePropertiesKeys) {
            Map<String, String> item = new HashMap<String, String>();
            item.put("name", key);
            item.put("value", engineProperties.get(key));
            item.put("group", "引擎配置");
            data.add(item);
        }
        // 返回
        return new DataGridJsonResult<Map<String, String>>(data.size(), data);
    }
}
