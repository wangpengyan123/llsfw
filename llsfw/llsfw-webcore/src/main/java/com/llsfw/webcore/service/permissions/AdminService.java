/**
 * CsvFileUtil.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.webcore.service.permissions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.llsfw.core.common.Constants;
import com.llsfw.core.common.JsonResult;
import com.llsfw.core.service.base.BaseService;
import com.llsfw.webcore.mapper.expand.permissions.IPermissionsMapper;

/**
 * <p>
 * ClassName: AdminService
 * </p>
 * <p>
 * Description: 控制台服务
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月24日
 * </p>
 */
@Service
public class AdminService extends BaseService {

    /**
     * <p>
     * Field FUNCTION_NAME: FUNCTION_NAME常量
     * </p>
     */
    private static final String FUNCTION_NAME = "FUNCTION_NAME";
    /**
     * <p>
     * Field FUNCTION_CODE: FUNCTION_CODE常量
     * </p>
     */
    private static final String FUNCTION_CODE = "FUNCTION_CODE";
    /**
     * <p>
     * Field REQUEST_URL: REQUEST_URL常量
     * </p>
     */
    private static final String REQUEST_URL = "REQUEST_URL";

    /**
     * <p>
     * Field hashAlgorithmName: 加密算法
     * </p>
     */
    @Value("#{configProperties['security.hashAlgorithmName']}")
    private String hashAlgorithmName;

    /**
     * <p>
     * Field hashIterations: 迭代次数
     * </p>
     */
    @Value("#{configProperties['security.hashIterations']}")
    private int hashIterations;

    /**
     * <p>
     * Field pm: 权限服务
     * </p>
     */
    @Autowired
    private IPermissionsMapper pm;

    /**
     * <p>
     * Field iuos: 用户操作接口
     * </p>
     */
    @Autowired
    @Qualifier("userOp")
    private IUserOp iuos;

    /**
     * 构建菜单(APP)
     * 
     * @param loginName 用户名
     * @param functionAccessChannel 功能渠道
     * @return 结果
     */
    public String buildApp(String loginName, String functionAccessChannel) {
        // 获得当前用户已经有的FUNCTION
        List<String> appList = null;
        List<String> menuList = null;
        List<String> functionList = this.findUserFunctions(loginName, functionAccessChannel);
        if (!CollectionUtils.isEmpty(functionList)) {
            // 获得当前用户已经有的MENU
            menuList = this.pm.getParentFunctionListCode(functionList);
            if (!CollectionUtils.isEmpty(menuList)) {
                // 获得当前用户已经有的APP
                appList = this.pm.getParentFunctionListCode(menuList);
            }
        }
        // 构建APP
        StringBuilder script = new StringBuilder();
        if (!CollectionUtils.isEmpty(appList)) {
            // 获得APP明细
            List<Map<String, Object>> appDetailList = this.pm.getFunctionList(appList, "0");
            // 开始标签
            script.append(" <div id='menu_tree' class='easyui-accordion' data-options='fit:false,border:false'> ");
            for (Map<String, Object> app : appDetailList) {
                String appName = app.get(FUNCTION_NAME).toString();
                String appCode = app.get(FUNCTION_CODE).toString();
                script.append(" <div title='" + appName
                        + "' data-options='fit:false,border:false,bodyCls:\"overflowIeHide\"'> ");
                // 构建MENU
                script.append(this.buildMenu(menuList, functionList, appCode));
                script.append(" </div> ");
            }
            // 结束标签
            script.append(" </div> ");
        }

        return script.toString();
    }

    /**
     * 构建菜单(MENU)
     * 
     * @param menuList 菜单列表
     * @param functionList 功能列表
     * @param parentFunctionCode 上级功能代码
     * @return 结果
     */
    private String buildMenu(List<String> menuList, List<String> functionList, String parentFunctionCode) {
        // 构建MENU
        StringBuilder script = new StringBuilder();
        if (!CollectionUtils.isEmpty(menuList)) {
            // 获得MENU
            List<Map<String, Object>> menuDetailList = this.pm.getFunctionList(menuList, parentFunctionCode);
            // 开始标签
            script.append(
                    " <ul class='easyui-tree' data-options='fit:false,border:false,lines:true,onClick:openThisNoed'> ");
            for (Map<String, Object> menu : menuDetailList) {
                String menuName = menu.get(FUNCTION_NAME).toString();
                String menuCode = menu.get(FUNCTION_CODE).toString();
                script.append(" <li><span>" + menuName + "</span> ");
                // 构建FUNCTION
                script.append(this.buildFunction(functionList, menuCode));
                script.append(" </li> ");
            }
            // 结束标签
            script.append(" </ul> ");
        }

        return script.toString();
    }

    /**
     * 构建菜单(FUNCTION)
     * 
     * @param functionList 功能清单
     * @param parentFunctionCode 上级功能代码
     * @return 结果
     */
    private String buildFunction(List<String> functionList, String parentFunctionCode) {
        // 构建FUNCTION
        StringBuilder script = new StringBuilder();
        if (!CollectionUtils.isEmpty(functionList)) {
            // 获得FUNCTION
            List<Map<String, Object>> functionDetailList = this.pm.getFunctionList(functionList, parentFunctionCode);
            // 开始标签
            script.append(" <ul> ");
            for (Map<String, Object> function : functionDetailList) {
                String functionName = function.get(FUNCTION_NAME).toString();
                String functionCode = function.get(FUNCTION_CODE).toString();
                String reqeustUrl = function.get(REQUEST_URL).toString();
                script.append(" <li><span> ");
                script.append(" <div openFunction=\"addTab('" + functionCode + "','" + functionName + "','" + reqeustUrl
                        + "');\">" + functionName + "</div> ");
                script.append(" </span></li> ");
            }
            // 结束标签
            script.append(" </ul> ");
        }

        return script.toString();
    }

    /**
     * 查找用户所有的功能
     * 
     * @param loginName 登录名
     * @param functionAccessChannel 访问渠道
     * @return 结果
     */
    private List<String> findUserFunctions(String loginName, String functionAccessChannel) {
        List<String> functionList = new ArrayList<String>();
        List<String> roleList = this.findUserRoles(loginName);
        if (!CollectionUtils.isEmpty(roleList)) {
            List<String> roleFunctions = this.pm.findRoleFunctions(roleList, functionAccessChannel);
            functionList.addAll(roleFunctions);
        }
        List<String> userFunctions = this.pm.findUserFunctions(loginName, functionAccessChannel);
        functionList.addAll(userFunctions);
        return functionList;
    }

    /**
     * 查找用户所有的角色
     * 
     * @param loginName 登录名
     * @return 访问渠道
     */
    public List<String> findUserRoles(String loginName) {
        return this.pm.findUserRoles(loginName);
    }

    /**
     * <p>
     * Description: 返回用户所拥有的所有权限(角色权限,直接权限)
     * </p>
     * 
     * @param loginName 用户名
     * @param roleList 角色清单
     * @return 权限列表
     */
    public Set<String> findUserPermissions(String loginName, List<String> roleList) {
        Set<String> permissions = new HashSet<String>();
        if (!CollectionUtils.isEmpty(roleList)) {
            List<String> rolePermissions = getPermisions(this.pm.findRolePermissions(roleList));
            permissions.addAll(rolePermissions);
        }
        List<String> userPermissions = getPermisions(this.pm.findUserPermissions(loginName));
        permissions.addAll(userPermissions);
        return permissions;
    }

    /**
     * <p>
     * Description: 将查询出来的权限数据拼接成权限字符串
     * </p>
     * 
     * @param permisions 权限数据
     * @return 权限字符串
     */
    private static List<String> getPermisions(List<Map<String, Object>> permisions) {
        List<String> rv = new ArrayList<String>();
        if (!CollectionUtils.isEmpty(permisions)) {
            for (Map<String, Object> item : permisions) {
                String p = item.get("FUNCTION_CODE") + ":" + item.get("FUNCTION_PURVIEW");
                rv.add(p);
            }
        }
        return rv;
    }

    /**
     * <p>
     * Description: 修改密码
     * </p>
     * 
     * @param loginName 用户名
     * @param oldPswd 老密码
     * @param newPswd 新密码
     * @return 操作结果
     */
    public JsonResult<String> changePswd(String loginName, String oldPswd, String newPswd) {
        // 校验旧密码
        if (!this.pswdCheck(loginName, oldPswd)) {
            // 返回
            return new JsonResult<String>(Constants.FAIL, "The original password verification failed");
        }

        // 修改密码
        this.iuos.changePswd(this.hashAlgorithmName, this.hashIterations, loginName, newPswd);

        // 返回
        return new JsonResult<String>(Constants.SUCCESS, null);
    }

    /**
     * <p>
     * Description: 校验密码是否一致
     * </p>
     * 
     * @param loginName 登录名
     * @param oldPswd 登陆密码
     * @return ture:正确,false:错误
     */
    public boolean pswdCheck(String loginName, String oldPswd) {
        return this.iuos.pswdCheck(this.hashAlgorithmName, this.hashIterations, loginName, oldPswd);
    }

    /**
     * 返回用户名
     * 
     * @param loginName 登录名
     * @return 结果
     */
    public String getUserName(String loginName) {
        return this.iuos.getUserName(loginName);
    }

}
