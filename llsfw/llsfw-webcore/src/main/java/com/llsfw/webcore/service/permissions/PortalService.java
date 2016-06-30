/**
 * PortalService.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.webcore.service.permissions;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.llsfw.core.common.Constants;
import com.llsfw.core.common.JsonResult;
import com.llsfw.core.service.base.BaseService;
import com.llsfw.webcore.mapper.expand.permissions.IPermissionsMapper;
import com.llsfw.webgen.mapper.standard.permissions.TsUserPortalMapper;
import com.llsfw.webgen.model.standard.permissions.TsUserPortal;
import com.llsfw.webgen.model.standard.permissions.TsUserPortalKey;

/**
 * <p>
 * ClassName: PortalService
 * </p>
 * <p>
 * Description: 面板服务
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月24日
 * </p>
 */
@Service
public class PortalService extends BaseService {

    /**
     * <p>
     * Field CCOUNT: 常量
     * </p>
     */
    private static final String CCOUNT = "CCOUNT";

    /**
     * <p>
     * Field pm: 权限mapper
     * </p>
     */
    @Autowired
    private IPermissionsMapper pm;

    /**
     * <p>
     * Field tupm: 用户面板mapper
     * </p>
     */
    @Autowired
    private TsUserPortalMapper tupm;

    /**
     * 删除面板
     * 
     * @param loginName 登录名
     * @param portalCode 面板代码
     * @return 结果
     */
    public JsonResult<String> portalDelete(String loginName, String portalCode) {
        // 删除用户面板关联表
        TsUserPortalKey tupk = new TsUserPortalKey();
        tupk.setLoginName(loginName);
        tupk.setPortalCode(portalCode);
        this.tupm.deleteByPrimaryKey(tupk);
        // 返回
        return new JsonResult<String>(Constants.SUCCESS, null);
    }

    /**
     * 编辑面板关联
     * 
     * @param loginName 登录名
     * @param portal 面板
     * @return 结果
     */
    public JsonResult<String> portalAdd(String loginName, TsUserPortal portal) {
        // 删除原有数据
        TsUserPortalKey tupk = new TsUserPortalKey();
        tupk.setLoginName(loginName);
        tupk.setPortalCode(portal.getPortalCode());
        this.tupm.deleteByPrimaryKey(tupk);
        // 新增数据
        portal.setLoginName(loginName);
        portal.setCreateBy(loginName);
        portal.setCreateDate(new Date());
        this.tupm.insert(portal);
        // 返回
        return new JsonResult<String>(Constants.SUCCESS, null);
    }

    /**
     * 获得面板列表
     * 
     * @param loginName 登录名
     * @return 结果
     */
    public List<Map<String, Object>> loadPoartList(String loginName) {
        // 获得用户已有功能
        List<String> functionList = new ArrayList<String>();
        List<String> roleList = this.pm.findUserRoles(loginName);
        if (!CollectionUtils.isEmpty(roleList)) {
            List<String> roleFunctions = this.pm.findRoleFunctions(roleList, "0");
            functionList.addAll(roleFunctions);
        }
        List<String> userFunctions = this.pm.findUserFunctions(loginName, "0");
        functionList.addAll(userFunctions);
        // 返回
        return this.pm.getPortalList(loginName, functionList);
    }

    /**
     * 加载用户面板
     * 
     * @param loginName 登录名
     * @param portalCode 面板到代码
     * @return 结果
     */
    public TsUserPortal loadPortal(String loginName, String portalCode) {
        if (!StringUtils.isEmpty(loginName) && !StringUtils.isEmpty(portalCode)) {
            TsUserPortalKey tupk = new TsUserPortalKey();
            tupk.setLoginName(loginName);
            tupk.setPortalCode(portalCode);
            return this.tupm.selectByPrimaryKey(tupk);
        }
        return null;
    }

    /**
     * 获得用户面板列表
     * 
     * @param loginName 登录名
     * @return 结果
     */
    public List<Map<String, Object>> getUserPortal(String loginName) {
        return this.pm.getUserPortal(loginName);
    }

    /**
     * 加载在线session数据
     * 
     * @return 结果
     */
    public List<Map<String, Object>> loadOnlineSecctionData() {
        return this.pm.loadOnlineSecctionData();
    }

    /**
     * 统计请求次数报表
     * 
     * @return 结果
     */
    public Map<String, List<Object>> reportRequestCount() {
        Map<String, List<Object>> rv = new HashMap<String, List<Object>>();
        List<Object> categories = new ArrayList<Object>();
        List<Object> seriesData = new ArrayList<Object>();
        List<Map<String, Object>> data = this.pm.reportRequestCount();
        if (!CollectionUtils.isEmpty(data)) {
            for (Map<String, Object> item : data) {
                categories.add(item.get("DDATE").toString());
                seriesData.add(Long.parseLong(item.get(CCOUNT).toString()));
            }
        }
        rv.put("categories", categories);
        rv.put("seriesData", seriesData);
        return rv;
    }

    /**
     * 统计session个数
     * 
     * @return 结果
     */
    public Map<String, List<Object>> reportSessionCount() {
        Map<String, List<Object>> rv = new HashMap<String, List<Object>>();
        List<Object> categories = new ArrayList<Object>();
        List<Object> seriesData = new ArrayList<Object>();
        List<Map<String, Object>> data = this.pm.reportSessionCount();
        if (!CollectionUtils.isEmpty(data)) {
            for (Map<String, Object> item : data) {
                categories.add(item.get("DDATE").toString());
                seriesData.add(Long.parseLong(item.get(CCOUNT).toString()));
            }
        }
        rv.put("categories", categories);
        rv.put("seriesData", seriesData);
        return rv;
    }

    /**
     * 统计异常率
     * 
     * @return 结果
     */
    public List<Map<String, Object>> reportExceptionCount() {
        List<Map<String, Object>> rv = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> data = this.pm.reportExceptionCount();
        if (!CollectionUtils.isEmpty(data)) {
            for (Map<String, Object> source : data) {
                Map<String, Object> item = new HashMap<String, Object>();
                if (source.get("EXCEPTION_CLASS") != null) {
                    String excName = source.get("EXCEPTION_CLASS").toString();
                    excName = excName.substring(excName.lastIndexOf(".") + 1, excName.length());
                    item.put("name", excName);
                } else {
                    item.put("name", "正常");
                }
                item.put("y", source.get(CCOUNT));
                rv.add(item);
            }
        }
        return rv;
    }
}
