/**
 * OrgService.java
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
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.llsfw.core.common.AutoLoadBean;
import com.llsfw.core.common.Constants;
import com.llsfw.core.common.JsonResult;
import com.llsfw.core.service.base.BaseService;
import com.llsfw.webcore.mapper.expand.permissions.IPermissionsMapper;
import com.llsfw.webgen.mapper.standard.permissions.TsJobMapper;
import com.llsfw.webgen.mapper.standard.permissions.TsJobRoleMapper;
import com.llsfw.webgen.mapper.standard.permissions.TsOrganizationMapper;
import com.llsfw.webgen.mapper.standard.permissions.TsUserJobMapper;
import com.llsfw.webgen.model.standard.permissions.TsJob;
import com.llsfw.webgen.model.standard.permissions.TsJobCriteria;
import com.llsfw.webgen.model.standard.permissions.TsJobRole;
import com.llsfw.webgen.model.standard.permissions.TsJobRoleCriteria;
import com.llsfw.webgen.model.standard.permissions.TsJobRoleKey;
import com.llsfw.webgen.model.standard.permissions.TsOrganization;
import com.llsfw.webgen.model.standard.permissions.TsOrganizationCriteria;
import com.llsfw.webgen.model.standard.permissions.TsUserJobCriteria;

/**
 * <p>
 * ClassName: OrgService
 * </p>
 * <p>
 * Description: 组织服务
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月24日
 * </p>
 */
@Service
public class OrgService extends BaseService {

    /**
     * <p>
     * Field CHILDREN: 常量
     * </p>
     */
    private static final String CHILDREN = "children";

    /**
     * <p>
     * Field pm: 权限mapper
     * </p>
     */
    @Autowired
    private IPermissionsMapper pm;

    /**
     * <p>
     * Field tujm: 岗位用户dao
     * </p>
     */
    @Autowired
    private TsUserJobMapper tujm;

    /**
     * <p>
     * Field tjrm: 岗位角色dao
     * </p>
     */
    @Autowired
    private TsJobRoleMapper tjrm;

    /**
     * <p>
     * Field tjm: 岗位dao
     * </p>
     */
    @Autowired
    private TsJobMapper tjm;

    /**
     * <p>
     * Field tom: 组织dao
     * </p>
     */
    @Autowired
    private TsOrganizationMapper tom;

    /**
     * <p>
     * Description: 删除角色与岗位的关联
     * </p>
     * 
     * @param jobCode 岗位代码
     * @param roleCode 角色代码
     * @return 操作结果
     */
    public JsonResult<String> deleteRole(String jobCode, String roleCode) {
        TsJobRoleKey tjrk = new TsJobRoleKey();
        tjrk.setJobCode(jobCode);
        tjrk.setRoleCode(roleCode);
        this.tjrm.deleteByPrimaryKey(tjrk);
        return new JsonResult<String>(Constants.SUCCESS, null);
    }

    /**
     * <p>
     * Description: 添加岗位角色关联
     * </p>
     * 
     * @param loginName 用户名
     * @param jobCode 岗位代码
     * @param roleCodeList 角色列表
     * @return 操作结果
     */
    public JsonResult<String> addRole(String loginName, String jobCode, String roleCodeList) {
        String[] roleCodes = roleCodeList.split(",");
        if (ArrayUtils.isNotEmpty(roleCodes)) {
            for (String roleCode : roleCodes) {
                TsJobRole tjr = new TsJobRole();
                tjr.setCreateBy(loginName);
                tjr.setCreateDate(new Date());
                tjr.setJobCode(jobCode);
                tjr.setRoleCode(roleCode);
                this.tjrm.insert(tjr);
            }
        }
        return new JsonResult<String>(Constants.SUCCESS, null);
    }

    /**
     * <p>
     * Description: 根据岗位返回不存在角色列表
     * </p>
     * 
     * @param jobCode 岗位代码
     * @return 角色列表
     */
    public List<Map<String, Object>> getRoleList(String jobCode) {
        return this.pm.getNotExistsRoleByJobCode(jobCode);
    }

    /**
     * <p>
     * Description: 删除岗位
     * </p>
     * 
     * @param jobCode 岗位代码
     * @return 操作结果
     */
    public JsonResult<String> deleteJob(String jobCode) {
        TsJobRoleCriteria tjrc = new TsJobRoleCriteria();
        tjrc.createCriteria().andJobCodeEqualTo(jobCode);
        this.tjrm.deleteByExample(tjrc);

        TsUserJobCriteria tujc = new TsUserJobCriteria();
        tujc.createCriteria().andJobCodeEqualTo(jobCode);
        this.tujm.deleteByExample(tujc);

        this.tjm.deleteByPrimaryKey(jobCode);

        return new JsonResult<String>(Constants.SUCCESS, null);
    }

    /**
     * <p>
     * Description: 更新岗位
     * </p>
     * 
     * @param valueMap 提交的更新值
     * @param jobCode 岗位代码
     * @param loginName 操作人
     * @return 操作结果
     * @throws NoSuchMethodException 异常
     * @throws SecurityException 异常
     */
    public JsonResult<String> editJob(Map<String, String[]> valueMap, String jobCode, String loginName)
            throws SecurityException, NoSuchMethodException {
        TsJob tj = this.tjm.selectByPrimaryKey(jobCode);
        tj = AutoLoadBean.load(tj, valueMap);
        tj.setUpdateBy(loginName);
        tj.setUpdateDate(new Date());
        this.tjm.updateByPrimaryKey(tj);
        return new JsonResult<String>(Constants.SUCCESS, null);
    }

    /**
     * <p>
     * Description: 加载岗位对象
     * </p>
     * 
     * @param jobCode 岗位代码
     * @return 岗位对象
     */
    public TsJob loadJob(String jobCode) {
        return this.tjm.selectByPrimaryKey(jobCode);
    }

    /**
     * <p>
     * Description: 添加岗位
     * </p>
     * 
     * @param loginName 登陆人
     * @param tj 岗位对象
     * @return 操作结果
     */
    public JsonResult<String> addJob(String loginName, TsJob tj) {
        tj.setCreateBy(loginName);
        tj.setCreateDate(new Date());
        this.tjm.insert(tj);
        return new JsonResult<String>(Constants.SUCCESS, null);
    }

    /**
     * <p>
     * Description: 判断岗位代码是否存在
     * </p>
     * 
     * @param jobCode 岗位代码
     * @return 判断结果
     */
    public boolean jobCodeUniqueCheck(String jobCode) {
        return this.tjm.selectByPrimaryKey(jobCode) == null ? true : false;
    }

    /**
     * <p>
     * Description: 根据组织代码反馈岗位列表
     * </p>
     * 
     * @param orgCode 组织代码
     * @return 岗位列表
     */
    public List<Map<String, Object>> getJob(String orgCode) {
        return this.pm.getJobByOrgCode(orgCode);
    }

    /**
     * <p>
     * Description: 删除组织
     * </p>
     * 
     * @param orgCode 组织代码
     * @param parentOrgCode 上级组织代码
     * @return 操作结果
     */
    public JsonResult<String> deleteOrg(String orgCode, String parentOrgCode) {
        TsOrganizationCriteria toc = new TsOrganizationCriteria();
        if (!StringUtils.isEmpty(orgCode)) { // 删除本级
            toc.createCriteria().andOrgCodeEqualTo(orgCode);
        } else if (!StringUtils.isEmpty(parentOrgCode)) { // 删除下级
            toc.createCriteria().andParentOrgCodeEqualTo(parentOrgCode);
        }
        List<TsOrganization> list = this.tom.selectByExample(toc);
        if (!CollectionUtils.isEmpty(list)) {
            for (TsOrganization item : list) {
                // 删除岗位和角色,岗位和用户的关联
                TsJobCriteria tjc = new TsJobCriteria();
                tjc.createCriteria().andOrgCodeEqualTo(item.getOrgCode());
                List<TsJob> jobList = this.tjm.selectByExample(tjc);
                if (!CollectionUtils.isEmpty(jobList)) {
                    for (TsJob jobItem : jobList) {
                        TsJobRoleCriteria tjrc = new TsJobRoleCriteria();
                        tjrc.createCriteria().andJobCodeEqualTo(jobItem.getJobCode());
                        this.tjrm.deleteByExample(tjrc);

                        TsUserJobCriteria tujc = new TsUserJobCriteria();
                        tujc.createCriteria().andJobCodeEqualTo(jobItem.getJobCode());
                        this.tujm.deleteByExample(tujc);
                    }
                }
                // 删除岗位
                this.tjm.deleteByExample(tjc);

                // 删除组织
                this.tom.deleteByPrimaryKey(item.getOrgCode());

                // 递归向下删除
                this.deleteOrg(null, item.getOrgCode());
            }

        }
        return new JsonResult<String>(Constants.SUCCESS, null);
    }

    /**
     * <p>
     * Description: 更新组织
     * </p>
     * 
     * @param valueMap 提交的更新值
     * @param orgCode 组织代码
     * @param loginName 操作人
     * @return 操作结果
     * @throws NoSuchMethodException 异常
     * @throws SecurityException 异常
     */
    public JsonResult<String> editOrg(Map<String, String[]> valueMap, String orgCode, String loginName)
            throws SecurityException, NoSuchMethodException {
        TsOrganization to = this.tom.selectByPrimaryKey(orgCode);
        to = AutoLoadBean.load(to, valueMap);
        to.setUpdateBy(loginName);
        to.setUpdateDate(new Date());
        this.tom.updateByPrimaryKey(to);
        return new JsonResult<String>(Constants.SUCCESS, null);
    }

    /**
     * <p>
     * Description: 将组织结构转换为树形
     * </p>
     * 
     * @param nodes 组织结构
     * @return 树形组织结构
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> getOrgNodeTree(List<Map<String, Object>> nodes) {
        if (!CollectionUtils.isEmpty(nodes)) {
            List<Map<String, Object>> rv = new ArrayList<Map<String, Object>>();
            for (Map<String, Object> item : nodes) {
                Map<String, Object> rvItem = new HashMap<String, Object>();
                rvItem.put("id", item.get("ORG_CODE"));
                rvItem.put("text", item.get("ORG_NAME"));
                if (item.get(CHILDREN) != null) {
                    rvItem.put(CHILDREN, getOrgNodeTree((List<Map<String, Object>>) item.get(CHILDREN)));
                }
                rv.add(rvItem);
            }
            return rv;
        }
        return new ArrayList<Map<String, Object>>();
    }

    /**
     * <p>
     * Description: 加载组织对象
     * </p>
     * 
     * @param orgCode 组织代码
     * @return 组织对象
     */
    public TsOrganization loadOrg(String orgCode) {
        return this.tom.selectByPrimaryKey(orgCode);
    }

    /**
     * <p>
     * Description: 添加组织
     * </p>
     * 
     * @param loginName 登陆人
     * @param to 组织对象
     * @return 操作结果
     */
    public JsonResult<String> addOrg(String loginName, TsOrganization to) {
        to.setCreateBy(loginName);
        to.setCreateDate(new Date());
        this.tom.insert(to);
        return new JsonResult<String>(Constants.SUCCESS, null);
    }

    /**
     * <p>
     * Description: 判断组织代码是否存在
     * </p>
     * 
     * @param orgCode 组织代码
     * @return 判断结果
     */
    public boolean orgCodeUniqueCheck(String orgCode) {
        return this.tom.selectByPrimaryKey(orgCode) == null ? true : false;
    }

    /**
     * <p>
     * Description: 根据岗位返回角色列表
     * </p>
     * 
     * @param jobCode 岗位代码
     * @return 角色列表
     */
    public List<Map<String, Object>> getRole(String jobCode) {
        return this.pm.getRoleByJobCode(jobCode);
    }

    /**
     * <p>
     * Description: 获得组织树
     * </p>
     * 
     * @param parentOrgCode 上级组织代码
     * @return 组织树
     */
    public List<Map<String, Object>> getOrgNode(String parentOrgCode) {
        List<Map<String, Object>> node;
        if (StringUtils.isEmpty(parentOrgCode)) {
            node = this.pm.getRootOrg("-1");
        } else {
            node = this.pm.getChildrenOrg(parentOrgCode);
        }
        if (!CollectionUtils.isEmpty(node)) {
            for (int i = 0; i < node.size(); i++) {
                List<Map<String, Object>> children = this.getOrgNode(node.get(i).get("ORG_CODE").toString());
                if (!CollectionUtils.isEmpty(children)) {
                    node.get(i).put(CHILDREN, children);
                }
            }
        }
        return node;
    }

}
