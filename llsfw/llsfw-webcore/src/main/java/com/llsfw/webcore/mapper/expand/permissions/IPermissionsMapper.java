/**
 * IpermissionsMapper.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.webcore.mapper.expand.permissions;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * ClassName: IPermissionsMapper
 * </p>
 * <p>
 * Description: 权限操作结构
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月24日
 * </p>
 */
public interface IPermissionsMapper {

    /**
     * 统计异常率
     * 
     * @return 结果 结果
     */
    List<Map<String, Object>> reportExceptionCount();

    /**
     * 统计session个数
     * 
     * @return 结果 结果
     */
    List<Map<String, Object>> reportSessionCount();

    /**
     * 统计请求次数
     * 
     * @return 结果
     */
    List<Map<String, Object>> reportRequestCount();

    /**
     * 加载在线session数据
     * 
     * @return 结果
     */
    List<Map<String, Object>> loadOnlineSecctionData();

    /**
     * 获得面板列表
     * 
     * @param loginName 登录名
     * @param functionCodeList 功能代码清单
     * @return 结果
     */
    List<Map<String, Object>> getPortalList(@Param("loginName") String loginName,
            @Param("functionCodeList") List<String> functionCodeList);

    /**
     * 获得用户面板列表
     * 
     * @param loginName 登录名
     * @return 结果
     */
    List<Map<String, Object>> getUserPortal(@Param("loginName") String loginName);

    /**
     * 获得全部的功能清单
     * 
     * @param userName 用户名
     * @return 结果
     */
    public List<Map<String, Object>> getFunctionAllList(@Param("userName") String userName);

    /**
     * <p>
     * Description: 加载岗位清单
     * </p>
     * 
     * @param loginName 用户名
     * @param orgCode 组织代码
     * @return 结果 结果集
     */
    public List<Map<String, Object>> loadJobList(@Param("loginName") String loginName,
            @Param("orgCode") String orgCode);

    /**
     * 判断组织是否存在
     * 
     * @param orgCode 组织代码
     * @param loginName 登录名
     * @return 结果
     */
    public int countOrg(@Param("orgCode") String orgCode, @Param("loginName") String loginName);

    /**
     * 获得功能数据
     * 
     * @param jobCode 岗位代码
     * @return 结果
     */
    public List<Map<String, Object>> getFunctionData(@Param("jobCode") String jobCode);

    /**
     * 获得组织列表
     * 
     * @param loginName 登录名
     * @param jobCodes 岗位代码
     * @return 结果
     */
    public List<Map<String, Object>> getOrgList(@Param("loginName") String loginName,
            @Param("jobCodes") String[] jobCodes);

    /**
     * 返回用户权限
     * 
     * @param loginName 登录名
     * @return 结果
     */
    public List<Map<String, Object>> getUserFunction(@Param("loginName") String loginName);

    /**
     * 返回岗位权限
     * 
     * @param loginName 登录名
     * @param jobCodes 岗位代码
     * @param roleCodes 角色代码
     * @return 结果
     */
    public List<Map<String, Object>> getJobFunction(@Param("loginName") String loginName,
            @Param("jobCodes") String[] jobCodes, @Param("roleCodes") String[] roleCodes);

    /**
     * <p>
     * Description: 返回角色清单
     * </p>
     * 
     * @param loginName 用户名
     * @param jobCodes 岗位代码
     * @return 结果
     */
    public List<Map<String, Object>> getUserJobRoleList(@Param("loginName") String loginName,
            @Param("jobCodes") String[] jobCodes);

    /**
     * <p>
     * Description: 返回用户岗位列表
     * </p>
     * 
     * @param loginName 当前用户名
     * @return 结果
     */
    public List<Map<String, Object>> getUserJobList(@Param("loginName") String loginName);

    /**
     * 返回用户列表-分页
     * 
     * @param loginNameParam 登录名
     * @param userNameParam 用户名
     * @return 结果
     */
    public List<Map<String, Object>> getUserListPageQuery(@Param("loginNameParam") String loginNameParam,
            @Param("userNameParam") String userNameParam);

    /**
     * <p>
     * Description: 返回job不存在的role
     * </p>
     * 
     * @param jobCode 岗位代码
     * @return 结果
     */
    public List<Map<String, Object>> getNotExistsRoleByJobCode(@Param("jobCode") String jobCode);

    /**
     * <p>
     * Description: 根据组织返回岗位列表
     * </p>
     * 
     * @param orgCode 组织代码
     * @return 结果
     */
    public List<Map<String, Object>> getJobByOrgCode(@Param("orgCode") String orgCode);

    /**
     * <p>
     * Description: 根据岗位返回角色列表
     * </p>
     * 
     * @param jobCode 岗位代码
     * @return 结果
     */
    public List<Map<String, Object>> getRoleByJobCode(@Param("jobCode") String jobCode);

    /**
     * <p>
     * Description: 返回所有的子节点
     * </p>
     * 
     * @param parentOrgCode 父节点代码
     * @return 结果
     */
    public List<Map<String, Object>> getChildrenOrg(@Param("parentOrgCode") String parentOrgCode);

    /**
     * <p>
     * Description: 返回所有的根节点
     * </p>
     * 
     * @param rootCode 根节点代码
     * 
     * @return 结果
     */
    public List<Map<String, Object>> getRootOrg(@Param("rootCode") String rootCode);

    /**
     * <p>
     * Description: 返回角色功能不包含的权限列表
     * </p>
     * 
     * @param roleCode 角色代码
     * @param functionCode 功能代码
     * @return 结果
     */
    List<Map<String, Object>> findNotIncludePurviewByRoleFunction(@Param("roleCode") String roleCode,
            @Param("functionCode") String functionCode);

    /**
     * <p>
     * Description: 返回角色不包含的功能权限列表
     * </p>
     * 
     * @param roleCode 角色代码
     * @return 结果
     */
    List<Map<String, Object>> findNotIncludeFunctionPurviewByRole(@Param("roleCode") String roleCode);

    /**
     * <p>
     * Description: 返回角色所包含的功能权限
     * </p>
     * 
     * @param roleCode 角色代码
     * @return 结果
     */
    List<Map<String, Object>> findFunctionPurviewByRole(@Param("roleCode") String roleCode);

    /**
     * <p>
     * Description: 返回角色列表
     * </p>
     * 
     * @return 结果 角色列表
     */
    public List<Map<String, Object>> getRoleList();

    /**
     * 获得系统节点
     * 
     * @param levelNo 节点级别
     * @param parentFunctionCode 父功能代码
     * @return 结果
     */
    List<Map<String, Object>> appNode(@Param("levelNo") String levelNo,
            @Param("parentFunctionCode") String parentFunctionCode);

    /**
     * <p>
     * Description: 返回用户直接权限
     * </p>
     * 
     * @param loginName 用户名
     * @return 结果 权限列表
     */
    List<Map<String, Object>> findUserPermissions(@Param("loginName") String loginName);

    /**
     * <p>
     * Description: 返回角色权限
     * </p>
     * 
     * @param roleList 角色列表
     * @return 结果 权限列表
     */
    List<Map<String, Object>> findRolePermissions(@Param("roleList") List<String> roleList);

    /**
     * <p>
     * Description: 返回功能代码清单
     * </p>
     * 
     * @param functionCodeList 功能代码列表
     * @param parentFunctionCode 上级功能代码
     * @return 结果 结果集
     */
    List<Map<String, Object>> getFunctionList(@Param("functionCodeList") List<String> functionCodeList,
            @Param("parentFunctionCode") String parentFunctionCode);

    /**
     * <p>
     * Description: 返回上级功能代码列表
     * </p>
     * 
     * @param functionCodeList 功能代码列表
     * @return 结果 结果集
     */
    List<String> getParentFunctionListCode(@Param("functionCodeList") List<String> functionCodeList);

    /**
     * <p>
     * Description: 返回用户直接功能
     * </p>
     * 
     * @param loginName 用户名
     * @param functionAccessChannel 功能访问渠道
     * @return 结果 功能列表
     */
    List<String> findUserFunctions(@Param("loginName") String loginName,
            @Param("functionAccessChannel") String functionAccessChannel);

    /**
     * <p>
     * Description: 返回角色功能
     * </p>
     * 
     * @param roleList 角色列表
     * @param functionAccessChannel 功能访问渠道
     * @return 结果 功能列表
     */
    List<String> findRoleFunctions(@Param("roleList") List<String> roleList,
            @Param("functionAccessChannel") String functionAccessChannel);

    /**
     * <p>
     * Description: 返回用户所关联的角色
     * </p>
     * 
     * @param loginName 用户名
     * @return 结果 角色列表
     */
    List<String> findUserRoles(@Param("loginName") String loginName);

}
