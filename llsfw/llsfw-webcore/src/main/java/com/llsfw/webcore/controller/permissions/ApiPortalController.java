/**
 * ApiPortalController.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.webcore.controller.permissions;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.llsfw.core.common.Constants;
import com.llsfw.core.common.JsonResult;
import com.llsfw.core.controller.base.BaseController;
import com.llsfw.webcore.service.permissions.PortalService;

/**
 * <p>
 * ClassName: ApiPortalController
 * </p>
 * <p>
 * Description: 面板API控制器
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月24日
 * </p>
 */
@RestController
@RequestMapping("${security.api.path}/ApiPortalController")
public class ApiPortalController extends BaseController {

    /**
     * <p>
     * Field ps: 面板服务
     * </p>
     */
    @Autowired
    private PortalService ps;

    /**
     * <p>
     * Description: 返回在线session列表
     * </p>
     * 
     * @return 结果集
     */
    @RequiresPermissions("portalController:view")
    @RequestMapping(value = "loadOnlineSecctionData", method = RequestMethod.GET)
    public JsonResult<List<Map<String, Object>>> loadOnlineSecctionData() {
        return new JsonResult<List<Map<String, Object>>>(Constants.SUCCESS, this.ps.loadOnlineSecctionData());
    }
}
