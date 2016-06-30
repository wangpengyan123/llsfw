/**
 * ApiController.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.webcore.controller.permissions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.llsfw.core.common.Constants;
import com.llsfw.core.common.JsonResult;
import com.llsfw.core.controller.base.BaseController;
import com.llsfw.webcore.service.permissions.UserService;
import com.llsfw.webgen.model.standard.permissions.TsApplicationUser;

/**
 * <p>
 * ClassName: ApiController
 * </p>
 * <p>
 * Description: api登陆控制器
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月24日
 * </p>
 */
@RestController
public class ApiController extends BaseController {

    /**
     * <p>
     * Field us: 用户服务
     * </p>
     */
    @Autowired
    private UserService us;

    /**
     * <p>
     * Description: 登陆
     * </p>
     * 
     * @return 结果
     */
    @RequestMapping(value = "${security.api.loginUrl}", method = RequestMethod.POST)
    public JsonResult<Map<String, String>> login() {
        String loginName = this.getLoginName();
        TsApplicationUser user = this.us.loadUser(loginName);
        Map<String, String> data = new HashMap<String, String>();
        data.put("clientIdentity", user.getUserId());
        data.put("clientDigest", user.getLoginPassword());
        return new JsonResult<Map<String, String>>(Constants.SUCCESS, data);
    }
}
