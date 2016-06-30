/**
 * RoleController.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.webcore.controller.permissions;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.llsfw.core.common.JsonResult;
import com.llsfw.core.controller.base.BaseController;
import com.llsfw.webcore.service.permissions.RoleService;
import com.llsfw.webgen.model.standard.permissions.TsRole;

/**
 * <p>
 * ClassName: RoleController
 * </p>
 * <p>
 * Description: 角色控制器
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月24日
 * </p>
 */
@Controller
@RequestMapping("roleController")
public class RoleController extends BaseController {

    /**
     * <p>
     * Field rs: 角色服务
     * </p>
     */
    @Autowired
    private RoleService rs;

    /**
     * <p>
     * Description: 删除角色
     * </p>
     * 
     * @param roleCode 角色代码
     * @return 操作结果
     */
    @RequiresPermissions("roleController:edit")
    @RequestMapping("deleteRole")
    @ResponseBody
    public JsonResult<String> deleteRole(String roleCode) {
        return this.rs.deleteRole(roleCode);
    }

    /**
     * <p>
     * Description: 更新角色
     * </p>
     * 
     * @param r 请求对象
     * @param roleCode 角色代码
     * @return 操作结果
     * @throws NoSuchMethodException 异常
     * @throws SecurityException 异常
     */
    @RequiresPermissions("roleController:edit")
    @RequestMapping("editRole")
    @ResponseBody
    public JsonResult<String> editRole(HttpServletRequest r, String roleCode)
            throws SecurityException, NoSuchMethodException {
        return this.rs.editRole(r.getParameterMap(), roleCode, this.getLoginName());
    }

    /**
     * <p>
     * Description: 加载单个角色
     * </p>
     * 
     * @param roleCode 角色代码
     * @return 角色
     */
    @RequiresPermissions("roleController:view")
    @RequestMapping("loadRole")
    @ResponseBody
    public TsRole loadRole(String roleCode) {
        return this.rs.loadRole(roleCode);
    }

    /**
     * <p>
     * Description: 跳转到角色修改界面
     * </p>
     * 
     * @param roleCode 角色代码
     * @param request 请求
     * @return 角色修改界面
     */
    @RequiresPermissions("roleController:view")
    @RequestMapping("toRoleEdit")
    public String toRoleEdit(String roleCode, HttpServletRequest request) {
        request.setAttribute(roleCode, roleCode);
        return "llsfw/role/roleEdit";
    }

    /**
     * <p>
     * Description: 创建角色
     * </p>
     * 
     * @param tr 角色对象
     * @return 操作结果
     */
    @RequiresPermissions("roleController:edit")
    @RequestMapping("addRole")
    @ResponseBody
    public JsonResult<String> addRole(TsRole tr) {
        return this.rs.addRole(tr, this.getLoginName());
    }

    /**
     * <p>
     * Description: 校验角色是否唯一
     * </p>
     * 
     * @param roleCode 角色代码
     * @return true:通过,false:不通过
     */
    @RequiresPermissions("roleController:view")
    @RequestMapping("roleCodeUniqueCheck")
    @ResponseBody
    public boolean roleCodeUniqueCheck(String roleCode) {
        return this.rs.roleCodeUniqueCheck(roleCode);
    }

    /**
     * <p>
     * Description: 跳转到角色添加界面
     * </p>
     * 
     * @return 角色添加
     */
    @RequiresPermissions("roleController:view")
    @RequestMapping("toRoleAdd")
    public String toRoleAdd() {
        return "llsfw/role/roleAdd";
    }

    /**
     * <p>
     * Description: 添加角色的功能权限
     * </p>
     * 
     * @param roleCode 角色代码
     * @param functionCode 功能代码
     * @param purviewCodes 权限代码
     * @return 操作结果
     */
    @RequiresPermissions("roleController:edit")
    @RequestMapping("addRoleFunction")
    @ResponseBody
    public JsonResult<String> addRoleFunction(String roleCode, String functionCode, String purviewCodes) {
        return this.rs.addRoleFunction(this.getLoginName(), roleCode, functionCode, purviewCodes);
    }

    /**
     * <p>
     * Description: 返回角色功能不包含的权限列表
     * </p>
     * 
     * @param roleCode 角色代码
     * @param functionCode 功能代码
     * @return 角色功能不包含的权限列表
     */
    @RequiresPermissions("roleController:view")
    @RequestMapping("getFunctionPurviewList")
    @ResponseBody
    public List<Map<String, Object>> getFunctionPurviewList(String roleCode, String functionCode) {
        return this.rs.getFunctionPurviewList(roleCode, functionCode);
    }

    /**
     * <p>
     * Description: 返回功能列表
     * </p>
     * 
     * @param roleCode 角色代码
     * @return 功能列表
     */
    @RequiresPermissions("roleController:view")
    @RequestMapping("getFunctionList")
    @ResponseBody
    public List<Map<String, Object>> getFunctionList(String roleCode) {
        return this.rs.getFunctionList(roleCode);
    }

    /**
     * <p>
     * Description: 跳转到角色功能关联界面
     * </p>
     * 
     * @param roleCode 角色代码
     * @param request 请求
     * @return 角色功能关联
     */
    @RequiresPermissions("roleController:view")
    @RequestMapping("toAddRoleFunction")
    public String toAddRoleFunction(String roleCode, HttpServletRequest request) {
        request.setAttribute(roleCode, roleCode);
        return "llsfw/role/addRoleFunction";
    }

    /**
     * <p>
     * Description: 删除角色关联的功能权限
     * </p>
     * 
     * @param roleCode 角色代码
     * @param functionCode 功能代码
     * @param purviewCode 权限代码
     * @return 操作结果
     */
    @RequiresPermissions("roleController:edit")
    @RequestMapping("deleteRoleFunction")
    @ResponseBody
    public JsonResult<String> deleteRoleFunction(String roleCode, String functionCode, String purviewCode) {
        return this.rs.deleteRoleFunction(roleCode, functionCode, purviewCode);
    }

    /**
     * <p>
     * Description: 根据角色代码返回所关联的功能列表
     * </p>
     * 
     * @param roleCode 角色代码
     * @return 功能列表
     */
    @RequiresPermissions("roleController:view")
    @RequestMapping("getRoleFunctionList")
    @ResponseBody
    public List<Map<String, Object>> getRoleFunctionList(String roleCode) {
        return this.rs.getRoleFunctionList(roleCode);
    }

    /**
     * <p>
     * Description: 返回角色列表
     * </p>
     * 
     * @return 角色列表
     */
    @RequiresPermissions("roleController:view")
    @RequestMapping("getRoleList")
    @ResponseBody
    public List<Map<String, Object>> getRoleList() {
        return this.rs.getRoleList();
    }

    /**
     * <p>
     * Description: 初始化方法
     * </p>
     * 
     * @param req 请求对象
     * @return 页面
     */
    @RequiresPermissions("roleController:view")
    @RequestMapping("init")
    public String init(HttpServletRequest req) {
        this.getLocalStript(req, "role.");
        return "llsfw/role/roleMain";
    }

}
