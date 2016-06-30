/**
 * RoleService.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.webcore.service.permissions;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.llsfw.core.common.AutoLoadBean;
import com.llsfw.core.common.Constants;
import com.llsfw.core.common.JsonResult;
import com.llsfw.core.service.base.BaseService;
import com.llsfw.webcore.mapper.expand.permissions.IPermissionsMapper;
import com.llsfw.webgen.mapper.standard.permissions.TsFunctionMapper;
import com.llsfw.webgen.mapper.standard.permissions.TsJobRoleMapper;
import com.llsfw.webgen.mapper.standard.permissions.TsRoleFunctionMapper;
import com.llsfw.webgen.mapper.standard.permissions.TsRoleMapper;
import com.llsfw.webgen.model.standard.permissions.TsFunction;
import com.llsfw.webgen.model.standard.permissions.TsFunctionCriteria;
import com.llsfw.webgen.model.standard.permissions.TsJobRoleCriteria;
import com.llsfw.webgen.model.standard.permissions.TsRole;
import com.llsfw.webgen.model.standard.permissions.TsRoleCriteria;
import com.llsfw.webgen.model.standard.permissions.TsRoleFunction;
import com.llsfw.webgen.model.standard.permissions.TsRoleFunctionCriteria;
import com.llsfw.webgen.model.standard.permissions.TsRoleFunctionKey;

/**
 * <p>
 * ClassName: RoleService
 * </p>
 * <p>
 * Description: 角色服务
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月24日
 * </p>
 */
@Service
public class RoleService extends BaseService {

    /**
     * <p>
     * Field pm: 权限mapper
     * </p>
     */
    @Autowired
    private IPermissionsMapper pm;

    /**
     * <p>
     * Field fs: 功能服务
     * </p>
     */
    @Autowired
    private FunctionService fs;

    /**
     * <p>
     * Field tfm: 功能mapper
     * </p>
     */
    @Autowired
    private TsFunctionMapper tfm;

    /**
     * <p>
     * Field trfm: 角色功能mapper
     * </p>
     */
    @Autowired
    private TsRoleFunctionMapper trfm;

    /**
     * <p>
     * Field trm: 角色mapper
     * </p>
     */
    @Autowired
    private TsRoleMapper trm;

    /**
     * <p>
     * Field tjrm: 岗位角色mapper
     * </p>
     */
    @Autowired
    private TsJobRoleMapper tjrm;

    /**
     * <p>
     * Description: 删除角色
     * </p>
     * 
     * @param roleCode 角色代码
     * @return 操作结果
     */
    public JsonResult<String> deleteRole(String roleCode) {
        // 删除功能关联
        TsRoleFunctionCriteria trfc = new TsRoleFunctionCriteria();
        trfc.createCriteria().andRoleCodeEqualTo(roleCode);
        this.trfm.deleteByExample(trfc);

        // 删除岗位关联
        TsJobRoleCriteria tjrc = new TsJobRoleCriteria();
        tjrc.createCriteria().andRoleCodeEqualTo(roleCode);
        this.tjrm.deleteByExample(tjrc);

        // 删除角色
        this.trm.deleteByPrimaryKey(roleCode);

        // 设定返回值
        return new JsonResult<String>(Constants.SUCCESS, null);
    }

    /**
     * <p>
     * Description: 更新角色
     * </p>
     * 
     * @param v 更新数据
     * @param roleCode 角色代码
     * @param loginName 登录名
     * @return 操作结果
     * @throws NoSuchMethodException 异常
     * @throws SecurityException 异常
     */
    public JsonResult<String> editRole(Map<String, String[]> v, String roleCode, String loginName)
            throws SecurityException, NoSuchMethodException {
        TsRole tr = this.trm.selectByPrimaryKey(roleCode);
        tr = AutoLoadBean.load(tr, v);
        tr.setUpdateBy(loginName);
        tr.setUpdateDate(new Date());
        this.trm.updateByPrimaryKey(tr);
        return new JsonResult<String>(Constants.SUCCESS, null);

    }

    /**
     * <p>
     * Description: 加载单个角色
     * </p>
     * 
     * @param roleCode 角色代码
     * @return 角色
     */
    public TsRole loadRole(String roleCode) {
        return this.trm.selectByPrimaryKey(roleCode);
    }

    /**
     * <p>
     * Description: 创建角色
     * </p>
     * 
     * @param tr 角色
     * @param loginName 登陆名
     * @return 操作结果
     */
    public JsonResult<String> addRole(TsRole tr, String loginName) {
        tr.setCreateBy(loginName);
        tr.setCreateDate(new Date());
        this.trm.insertSelective(tr);
        return new JsonResult<String>(Constants.SUCCESS, null);
    }

    /**
     * <p>
     * Description: 校验角色是否唯一
     * </p>
     * 
     * @param roleCode 角色代码
     * @return true:通过,false:不通过
     */
    public boolean roleCodeUniqueCheck(String roleCode) {
        TsRoleCriteria trc = new TsRoleCriteria();
        trc.createCriteria().andRoleCodeEqualTo(roleCode);
        return this.trm.countByExample(trc) > 0 ? false : true;
    }

    /**
     * <p>
     * Description: 为角色添加功能和操作权限
     * </p>
     * 
     * @param loginName 用户名
     * @param roleCode 角色名
     * @param functionCode 功能代码
     * @param purviewCodes 权限代码(清单)
     * @return 操作结果
     */
    public JsonResult<String> addRoleFunction(String loginName, String roleCode, String functionCode,
            String purviewCodes) {
        // 分解操作权限数组
        String[] purviewCodeArray = purviewCodes.split(",");
        if (ArrayUtils.isNotEmpty(purviewCodeArray)) {
            for (String purviewCode : purviewCodeArray) {
                // 删除
                TsRoleFunctionCriteria trfc = new TsRoleFunctionCriteria();
                trfc.createCriteria().andRoleCodeEqualTo(roleCode).andFunctionCodeEqualTo(functionCode)
                        .andPurviewCodeEqualTo(purviewCode);
                this.trfm.deleteByExample(trfc);
                // 添加
                TsRoleFunction trf = new TsRoleFunction();
                trf.setRoleCode(roleCode);
                trf.setFunctionCode(functionCode);
                trf.setPurviewCode(purviewCode);
                trf.setCreateBy(loginName);
                trf.setCreateDate(new Date());
                this.trfm.insert(trf);
            }
        }
        return new JsonResult<String>(Constants.SUCCESS, null);
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
    public List<Map<String, Object>> getFunctionPurviewList(String roleCode, String functionCode) {
        return this.pm.findNotIncludePurviewByRoleFunction(roleCode, functionCode);
    }

    /**
     * <p>
     * Description: 返回功能列表
     * </p>
     * 
     * @param roleCode 角色代码
     * @return 功能列表
     */
    public List<Map<String, Object>> getFunctionList(String roleCode) {
        // 返回角色没有的功能清单
        List<Map<String, Object>> notIncludeFunctionPurviewList = this.pm.findNotIncludeFunctionPurviewByRole(roleCode);
        // 获得角色没有的完整菜单
        return this.fs.getFunctionPurviewTreeNode(notIncludeFunctionPurviewList, false);
    }

    /**
     * <p>
     * Description: 删除角色所关联的功能权限
     * </p>
     * 
     * @param roleCode 角色代码
     * @param functionCode 功能代码
     * @param purviewCode 权限代码
     * @return 结果
     */
    public JsonResult<String> deleteRoleFunction(String roleCode, String functionCode, String purviewCode) {
        // 如果purviewCode不为空,则代表是只删除操作权限
        if (!StringUtils.isEmpty(purviewCode)) {
            TsRoleFunctionKey trfk = new TsRoleFunctionKey();
            trfk.setFunctionCode(functionCode);
            trfk.setPurviewCode(purviewCode);
            trfk.setRoleCode(roleCode);
            this.trfm.deleteByPrimaryKey(trfk);
        } else {
            // 删除
            TsRoleFunctionCriteria trfc = new TsRoleFunctionCriteria();
            trfc.createCriteria().andRoleCodeEqualTo(roleCode).andFunctionCodeEqualTo(functionCode);
            this.trfm.deleteByExample(trfc);
            // 获得下级
            TsFunctionCriteria tfc = new TsFunctionCriteria();
            tfc.createCriteria().andParentFunctionCodeEqualTo(functionCode);
            List<TsFunction> fList = this.tfm.selectByExample(tfc);
            if (!CollectionUtils.isEmpty(fList)) {
                for (TsFunction item : fList) {
                    deleteRoleFunction(roleCode, item.getFunctionCode(), null);
                }
            }
        }
        return new JsonResult<String>(Constants.SUCCESS, null);
    }

    /**
     * <p>
     * Description: 根据角色代码返回所关联的功能列表
     * </p>
     * 
     * @param roleCode 角色代码
     * @return 功能列表
     */
    public List<Map<String, Object>> getRoleFunctionList(String roleCode) {
        // 获得角色已有的功能清单
        List<Map<String, Object>> functionPurviewList = this.pm.findFunctionPurviewByRole(roleCode);
        // 获得角色已有的完整菜单
        return this.fs.getFunctionPurviewTreeNode(functionPurviewList, true);
    }

    /**
     * <p>
     * Description: 返回角色列表
     * </p>
     * 
     * @return 角色列表
     */
    public List<Map<String, Object>> getRoleList() {
        return this.pm.getRoleList();
    }
}
