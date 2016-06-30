/**
 * UserService.java
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
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.llsfw.core.common.AutoLoadBean;
import com.llsfw.core.common.Constants;
import com.llsfw.core.common.JsonResult;
import com.llsfw.core.common.Uuid;
import com.llsfw.core.pagequery.PageInterceptor;
import com.llsfw.core.pagequery.PageResult;
import com.llsfw.core.service.base.BaseService;
import com.llsfw.webcore.mapper.expand.permissions.IPermissionsMapper;
import com.llsfw.webgen.mapper.standard.permissions.TsApplicationUserMapper;
import com.llsfw.webgen.mapper.standard.permissions.TsFunctionMapper;
import com.llsfw.webgen.mapper.standard.permissions.TsOrganizationMapper;
import com.llsfw.webgen.mapper.standard.permissions.TsPurviewMapper;
import com.llsfw.webgen.mapper.standard.permissions.TsUserFunctionMapper;
import com.llsfw.webgen.mapper.standard.permissions.TsUserJobMapper;
import com.llsfw.webgen.mapper.standard.permissions.TsUserPortalMapper;
import com.llsfw.webgen.model.standard.permissions.TsApplicationUser;
import com.llsfw.webgen.model.standard.permissions.TsApplicationUserCriteria;
import com.llsfw.webgen.model.standard.permissions.TsFunction;
import com.llsfw.webgen.model.standard.permissions.TsOrganization;
import com.llsfw.webgen.model.standard.permissions.TsOrganizationCriteria;
import com.llsfw.webgen.model.standard.permissions.TsPurview;
import com.llsfw.webgen.model.standard.permissions.TsPurviewKey;
import com.llsfw.webgen.model.standard.permissions.TsUserFunction;
import com.llsfw.webgen.model.standard.permissions.TsUserFunctionCriteria;
import com.llsfw.webgen.model.standard.permissions.TsUserJob;
import com.llsfw.webgen.model.standard.permissions.TsUserJobCriteria;
import com.llsfw.webgen.model.standard.permissions.TsUserPortalCriteria;

/**
 * <p>
 * ClassName: UserService
 * </p>
 * <p>
 * Description: 用户服务
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月24日
 * </p>
 */
@Service
public class UserService extends BaseService {

    /**
     * <p>
     * Field FUNCTION_CODE: 常量
     * </p>
     */
    private static final String FUNCTION_CODE = "FUNCTION_CODE";

    /**
     * <p>
     * Field FUNCTION_NAME: 常量
     * </p>
     */
    private static final String FUNCTION_NAME = "FUNCTION_NAME";

    /**
     * <p>
     * Field PARENT_FUNCTION_CODE: 常量
     * </p>
     */
    private static final String PARENT_FUNCTION_CODE = "PARENT_FUNCTION_CODE";

    /**
     * <p>
     * Field PURVIEW_CODE: 常量
     * </p>
     */
    private static final String PURVIEW_CODE = "PURVIEW_CODE";

    /**
     * <p>
     * Field ORG_CODE: 常量
     * </p>
     */
    private static final String ORG_CODE = "ORG_CODE";

    /**
     * <p>
     * Field ORG_NAME: 常量
     * </p>
     */
    private static final String ORG_NAME = "ORG_NAME";

    /**
     * <p>
     * Field PARENT_ORG_CODE: 常量
     * </p>
     */
    private static final String PARENT_ORG_CODE = "PARENT_ORG_CODE";

    /**
     * <p>
     * Field MAIN_ORG: 常量
     * </p>
     */
    private static final String MAIN_ORG = "MAIN_ORG";

    /**
     * <p>
     * Field CHAR_1: 字符常量
     * </p>
     */
    private static final String CHAR_1 = "_";

    /**
     * <p>
     * Field pm: 权限mapper
     * </p>
     */
    @Autowired
    private IPermissionsMapper pm;

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
     * Field tujm: 岗位用户dao
     * </p>
     */
    @Autowired
    private TsUserJobMapper tujm;

    /**
     * <p>
     * Field taum: 用户
     * </p>
     */
    @Autowired
    private TsApplicationUserMapper taum;

    /**
     * <p>
     * Field tom: 组织机构dao
     * </p>
     */
    @Autowired
    private TsOrganizationMapper tom;

    /**
     * <p>
     * Field tfm: 功能dao
     * </p>
     */
    @Autowired
    private TsFunctionMapper tfm;

    /**
     * 面板关联操作
     */
    @Autowired
    private TsUserPortalMapper tupm;

    /**
     * <p>
     * Field tpm: 权限dao
     * </p>
     */
    @Autowired
    private TsPurviewMapper tpm;

    /**
     * <p>
     * Field tufm: 用户功能mapper
     * </p>
     */
    @Autowired
    private TsUserFunctionMapper tufm;

    /**
     * <p>
     * Description: 取消直接授权
     * </p>
     * 
     * @param userName 用户名
     * @param functionCode 功能代码
     * @param purviewCode 权限代码
     * @return 操作结果
     */
    public JsonResult<String> deleteUserFunction(String userName, String functionCode, String purviewCode) {
        // 删除
        TsUserFunctionCriteria tufc = new TsUserFunctionCriteria();
        tufc.createCriteria().andLoginNameEqualTo(userName).andFunctionCodeEqualTo(functionCode)
                .andPurviewCodeEqualTo(purviewCode);
        this.tufm.deleteByExample(tufc);
        return new JsonResult<String>(Constants.SUCCESS, null);
    }

    /**
     * <p>
     * Description: 添加直接权限
     * </p>
     * 
     * @param loginName 登陆人
     * @param userName 用户名
     * @param functionCode 功能代码
     * @param purviewCode 权限代码
     * @return 操作结果
     */
    public JsonResult<String> addUserFunction(String loginName, String userName, String functionCode,
            String purviewCode) {
        // 删除
        TsUserFunctionCriteria tufc = new TsUserFunctionCriteria();
        tufc.createCriteria().andLoginNameEqualTo(userName).andFunctionCodeEqualTo(functionCode)
                .andPurviewCodeEqualTo(purviewCode);
        this.tufm.deleteByExample(tufc);
        // 添加
        TsUserFunction tuf = new TsUserFunction();
        tuf.setCreateBy(loginName);
        tuf.setCreateDate(new Date());
        tuf.setLoginName(userName);
        tuf.setFunctionCode(functionCode);
        tuf.setPurviewCode(purviewCode);
        this.tufm.insertSelective(tuf);
        return new JsonResult<String>(Constants.SUCCESS, null);
    }

    /**
     * <p>
     * Description: 加载完整的功能权限树
     * </p>
     * 
     * @param userName 用户名
     * @return 功能权限树
     */
    public List<Map<String, Object>> loadUserFunctionTree(String userName) {
        // 获得功能的数据
        List<Map<String, Object>> rv = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> functionList = this.pm.getFunctionAllList(userName);

        // 如有数据,在继续操作
        List<String> eqrv = new ArrayList<String>();
        if (!CollectionUtils.isEmpty(functionList)) {
            for (Map<String, Object> item : functionList) {
                TsFunction f = this.tfm.selectByPrimaryKey(item.get(FUNCTION_CODE).toString());
                if (!eqrv.contains(f.getFunctionCode())) {
                    Map<String, Object> functionItem = new HashMap<String, Object>();
                    functionItem.put(FUNCTION_CODE, f.getFunctionCode());
                    functionItem.put(FUNCTION_NAME, f.getFunctionName());
                    functionItem.put(PARENT_FUNCTION_CODE, f.getParentFunctionCode());
                    rv.add(functionItem);
                    eqrv.add(f.getFunctionCode());
                    rv = this.getParentFunction(f.getParentFunctionCode(), rv, eqrv);
                }

                TsPurviewKey pk = new TsPurviewKey();
                pk.setFunctionCode(item.get(FUNCTION_CODE).toString());
                pk.setPurviewCode(item.get(PURVIEW_CODE).toString());
                TsPurview purview = this.tpm.selectByPrimaryKey(pk);
                if (!eqrv.contains(purview.getFunctionCode() + CHAR_1 + purview.getPurviewCode())) {
                    Map<String, Object> purviewItem = new HashMap<String, Object>();
                    purviewItem.put(FUNCTION_CODE, purview.getFunctionCode() + CHAR_1 + purview.getPurviewCode());
                    purviewItem.put(FUNCTION_NAME, purview.getPurviewName());
                    purviewItem.put(PARENT_FUNCTION_CODE, purview.getFunctionCode());
                    purviewItem.put("F_CODE", purview.getFunctionCode());
                    purviewItem.put("P_CODE", purview.getPurviewCode());
                    // 判断是否已经勾选
                    TsUserFunctionCriteria tufc = new TsUserFunctionCriteria();
                    tufc.createCriteria().andLoginNameEqualTo(userName)
                            .andFunctionCodeEqualTo(purview.getFunctionCode())
                            .andPurviewCodeEqualTo(purview.getPurviewCode());
                    if (this.tufm.countByExample(tufc) > 0) {
                        purviewItem.put("checked", true);
                    } else {
                        purviewItem.put("checked", false);
                    }
                    // 添加结果集
                    rv.add(purviewItem);
                    eqrv.add(purview.getFunctionCode() + CHAR_1 + purview.getPurviewCode());
                }

            }
        }
        return rv;
    }

    /**
     * <p>
     * Description: 删除用户岗位关联
     * </p>
     * 
     * @param loginName 用户
     * @param jobCode 岗位
     * @return 操作结果
     */
    public JsonResult<String> deleteUserJob(String loginName, String jobCode) {
        TsUserJobCriteria turc = new TsUserJobCriteria();
        turc.createCriteria().andLoginNameEqualTo(loginName).andJobCodeEqualTo(jobCode);
        this.tujm.deleteByExample(turc);
        return new JsonResult<String>(Constants.SUCCESS, null);
    }

    /**
     * <p>
     * Description: 添加用户岗位关联
     * </p>
     * 
     * @param loginName 登录名
     * @param userName 用户名
     * @param jobCode 岗位 代码
     * @return 操作结果
     */
    public JsonResult<String> addUserJob(String loginName, String userName, String jobCode) {
        // 删除
        TsUserJobCriteria turc = new TsUserJobCriteria();
        turc.createCriteria().andLoginNameEqualTo(userName).andJobCodeEqualTo(jobCode);
        this.tujm.deleteByExample(turc);
        // 添加
        TsUserJob tur = new TsUserJob();
        tur.setCreateBy(loginName);
        tur.setCreateDate(new Date());
        tur.setLoginName(userName);
        tur.setJobCode(jobCode);
        this.tujm.insert(tur);
        return new JsonResult<String>(Constants.SUCCESS, null);
    }

    /**
     * <p>
     * Description: 加载岗位清单
     * </p>
     * 
     * @param loginName 用户名
     * @param orgCode 组织
     * @return 结果集
     */
    public List<Map<String, Object>> loadJobList(String loginName, String orgCode) {
        return this.pm.loadJobList(loginName, orgCode);
    }

    /**
     * <p>
     * Description: 加载组织机构树
     * </p>
     * 
     * @param userName 用户名
     * @return 结果集
     */
    public List<Map<String, Object>> loadAllOrgTree(String userName) {
        List<Map<String, Object>> rv = new ArrayList<Map<String, Object>>();
        TsOrganizationCriteria toc = new TsOrganizationCriteria();
        toc.createCriteria().andParentOrgCodeEqualTo("-1");
        toc.setOrderByClause(" ORG_SORT ASC");
        List<TsOrganization> orgList = this.tom.selectByExample(toc);
        if (!CollectionUtils.isEmpty(orgList)) {
            for (TsOrganization org : orgList) {
                Map<String, Object> item = new HashMap<String, Object>();
                item.put("id", org.getOrgName());
                item.put(ORG_CODE, org.getOrgCode());
                item.put(ORG_NAME, org.getOrgName());
                item.put(PARENT_ORG_CODE, org.getParentOrgCode());
                if (this.pm.countOrg(org.getOrgCode(), userName) > 0) {
                    item.put(MAIN_ORG, "1");
                } else {
                    item.put(MAIN_ORG, "0");
                }
                rv.add(item);
                rv = loadAllOrgTree(org.getOrgCode(), userName, rv);
            }
        }
        return rv;
    }

    /**
     * <p>
     * Description: 递归加载下级组织
     * </p>
     * 
     * @param orgCode 组织代码
     * @param userName 用户名
     * @param rv 结果集
     * @return 结果集
     */
    private List<Map<String, Object>> loadAllOrgTree(String orgCode, String userName, List<Map<String, Object>> rv) {
        if (!StringUtils.isEmpty(orgCode)) {
            // 获得下级组织
            TsOrganizationCriteria toc = new TsOrganizationCriteria();
            toc.createCriteria().andParentOrgCodeEqualTo(orgCode);
            toc.setOrderByClause("ORG_SORT ASC");
            List<TsOrganization> orgList = this.tom.selectByExample(toc);
            // 递归处理
            if (!CollectionUtils.isEmpty(orgList)) {
                for (TsOrganization org : orgList) {
                    Map<String, Object> item = new HashMap<String, Object>();
                    item.put("id", org.getOrgName());
                    item.put(ORG_CODE, org.getOrgCode());
                    item.put(ORG_NAME, org.getOrgName());
                    item.put(PARENT_ORG_CODE, org.getParentOrgCode());
                    if (this.pm.countOrg(org.getOrgCode(), userName) > 0) {
                        item.put(MAIN_ORG, "1");
                    } else {
                        item.put(MAIN_ORG, "0");
                    }
                    rv.add(item);
                    rv = loadAllOrgTree(org.getOrgCode(), userName, rv);
                }
            }
        }
        return rv;
    }

    /**
     * <p>
     * Description: 加载用户指定岗位的功能树
     * </p>
     * 
     * @param jobCode 岗位代码
     * @return 结果
     */
    public List<Map<String, Object>> loadAddUserJobFunctionTree(String jobCode) {
        // 获得功能的数据
        List<Map<String, Object>> rv = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> functionList = this.pm.getFunctionData(jobCode);

        // 如有数据,在继续操作
        List<String> eqrv = new ArrayList<String>();
        if (!CollectionUtils.isEmpty(functionList)) {
            for (Map<String, Object> item : functionList) {
                TsFunction f = this.tfm.selectByPrimaryKey(item.get(FUNCTION_CODE).toString());
                if (!eqrv.contains(f.getFunctionCode())) {
                    Map<String, Object> functionItem = new HashMap<String, Object>();
                    functionItem.put(FUNCTION_CODE, f.getFunctionCode());
                    functionItem.put(FUNCTION_NAME, f.getFunctionName());
                    functionItem.put(PARENT_FUNCTION_CODE, f.getParentFunctionCode());
                    rv.add(functionItem);
                    eqrv.add(f.getFunctionCode());
                    rv = this.getParentFunction(f.getParentFunctionCode(), rv, eqrv);
                }

                TsPurviewKey pk = new TsPurviewKey();
                pk.setFunctionCode(item.get(FUNCTION_CODE).toString());
                pk.setPurviewCode(item.get(PURVIEW_CODE).toString());
                TsPurview purview = this.tpm.selectByPrimaryKey(pk);
                if (!eqrv.contains(purview.getFunctionCode() + CHAR_1 + purview.getPurviewCode())) {
                    Map<String, Object> purviewItem = new HashMap<String, Object>();
                    purviewItem.put(FUNCTION_CODE, purview.getFunctionCode() + CHAR_1 + purview.getPurviewCode());
                    purviewItem.put(FUNCTION_NAME, purview.getPurviewName());
                    purviewItem.put(PARENT_FUNCTION_CODE, purview.getFunctionCode());
                    rv.add(purviewItem);
                    eqrv.add(purview.getFunctionCode() + CHAR_1 + purview.getPurviewCode());
                }

            }
        }
        return rv;
    }

    /**
     * <p>
     * Description: 初始化密码
     * </p>
     * 
     * @param loginName 用户名
     * @param ln 操作人
     * @return 操作结果
     */
    public JsonResult<String> saveDefPswd(String loginName, String ln) {
        TsApplicationUser tau = new TsApplicationUser();
        tau.setLoginName(loginName);
        tau.setUpdateBy(ln);
        tau.setUpdateDate(new Date());

        // 设置初始化密码(MD5加密)
        String defPswd = this.getPs().getServerParamter("SYSTEM_DEF_PSWD");
        String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
        SimpleHash hash = null;
        hash = new SimpleHash(this.hashAlgorithmName, defPswd, ByteSource.Util.bytes(salt), this.hashIterations);
        String encodedPassword = hash.toHex();
        tau.setLoginPassword(encodedPassword);
        tau.setSalt(salt);

        // 更新
        this.taum.updateByPrimaryKeySelective(tau);

        // 设定返回值
        return new JsonResult<String>(Constants.SUCCESS, null);
    }

    /**
     * <p>
     * Description: 删除用户
     * </p>
     * 
     * @param loginName 用户名
     * @return 操作结果
     */
    public JsonResult<String> userDelete(String loginName) {
        TsUserJobCriteria tujc = new TsUserJobCriteria();
        tujc.createCriteria().andLoginNameEqualTo(loginName);
        TsUserFunctionCriteria tuf = new TsUserFunctionCriteria();
        tuf.createCriteria().andLoginNameEqualTo(loginName);
        TsUserPortalCriteria tupc = new TsUserPortalCriteria();
        tupc.createCriteria().andLoginNameEqualTo(loginName);
        this.tujm.deleteByExample(tujc);
        this.tufm.deleteByExample(tuf);
        this.tupm.deleteByExample(tupc);
        this.taum.deleteByPrimaryKey(loginName);
        return new JsonResult<String>(Constants.SUCCESS, null);
    }

    /**
     * <p>
     * Description: 修改用户
     * </p>
     * 
     * @param v 修改值
     * @param loginName 用户名
     * @param ln 操作名
     * @return 操作结果
     * @throws NoSuchMethodException 异常
     * @throws SecurityException 异常
     */
    public JsonResult<String> editUser(Map<String, String[]> v, String loginName, String ln)
            throws SecurityException, NoSuchMethodException {
        TsApplicationUser tau = this.taum.selectByPrimaryKey(loginName);
        tau = AutoLoadBean.load(tau, v);
        tau.setUpdateBy(ln);
        tau.setUpdateDate(new Date());
        this.taum.updateByPrimaryKey(tau);
        return new JsonResult<String>(Constants.SUCCESS, null);
    }

    /**
     * <p>
     * Description: 加载用户数据
     * </p>
     * 
     * @param loginName 登陆名字
     * @return 用户
     */
    public TsApplicationUser loadUser(String loginName) {
        return this.taum.selectByPrimaryKey(loginName);
    }

    /**
     * <p>
     * Description: 保存用户(密码初始化)
     * </p>
     * 
     * @param tau 用户对象
     * @param loginName 登录名
     * @return 操作结果
     */
    public JsonResult<String> addUser(TsApplicationUser tau, String loginName) {
        // 获得默认密码
        String defPswd = this.getPs().getServerParamter("SYSTEM_DEF_PSWD");

        // 设置密码
        String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
        SimpleHash hash = new SimpleHash(this.hashAlgorithmName, defPswd, ByteSource.Util.bytes(salt),
                this.hashIterations);
        String encodedPassword = hash.toHex();

        // 填充值,并且保存
        tau.setLoginPassword(encodedPassword);
        tau.setSalt(salt);
        tau.setCreateBy(loginName);
        tau.setCreateDate(new Date());
        tau.setUserId(Uuid.getUuid(false));
        this.taum.insertSelective(tau);

        return new JsonResult<String>(Constants.SUCCESS, "保存成功");
    }

    /**
     * <p>
     * Description: 校验登录名是否维护
     * </p>
     * 
     * @param loginName 登录名
     * @return true:通过,false:不通过
     */
    public boolean loginNameUniqueCheck(String loginName) {
        TsApplicationUserCriteria tauc = new TsApplicationUserCriteria();
        tauc.createCriteria().andLoginNameEqualTo(loginName);
        return this.taum.countByExample(tauc) > 0 ? false : true;
    }

    /**
     * <p>
     * Description: 获得job关联的组织机构
     * </p>
     * 
     * @param loginName 用户名
     * @param jobCodes 岗位代码
     * @param loadOrgType 加载组织类别(空为全部组织,1为上级组织,2为下级组织)
     * @return 组织机构树
     */
    public List<Map<String, Object>> getUserJobOrgTree(String loginName, String[] jobCodes, String loadOrgType) {
        // 获得所关联的组织数据
        if (!StringUtils.isEmpty(loginName)) {
            List<Map<String, Object>> orgList = this.pm.getOrgList(loginName, jobCodes);
            // 如果有数据,则进行后续的操作
            if (!CollectionUtils.isEmpty(orgList)) {
                // 返回值
                List<Map<String, Object>> rv = new ArrayList<Map<String, Object>>();
                List<String> eqrv = new ArrayList<String>();
                // 获得上级组织和下级组织
                for (Map<String, Object> item : orgList) {
                    Map<String, Object> orgItem = new HashMap<String, Object>();
                    orgItem.put(ORG_CODE, item.get(ORG_CODE));
                    orgItem.put(ORG_NAME, item.get(ORG_NAME));
                    orgItem.put(PARENT_ORG_CODE, item.get(PARENT_ORG_CODE));
                    orgItem.put(MAIN_ORG, "1");
                    rv.add(orgItem);
                    eqrv.add(item.get(ORG_CODE).toString());

                    // 判断加载类别
                    if (StringUtils.isEmpty(loadOrgType)) {
                        rv = getParentOrg(
                                item.get(PARENT_ORG_CODE) == null ? null : item.get(PARENT_ORG_CODE).toString(), rv,
                                eqrv);
                        rv = getOrg(item.get(ORG_CODE) == null ? null : item.get(ORG_CODE).toString(), rv, eqrv);
                    } else if ("1".equals(loadOrgType)) {
                        rv = getParentOrg(
                                item.get(PARENT_ORG_CODE) == null ? null : item.get(PARENT_ORG_CODE).toString(), rv,
                                eqrv);
                    } else if ("2".equals(loadOrgType)) {
                        rv = getOrg(item.get(ORG_CODE) == null ? null : item.get(ORG_CODE).toString(), rv, eqrv);
                    } else {
                        this.getLog().error("未知组织加载类别");
                    }
                }
                return rv;
            } else {
                return new ArrayList<Map<String, Object>>();
            }
        } else {
            return new ArrayList<Map<String, Object>>();
        }
    }

    /**
     * <p>
     * Description: 获得下级组织
     * </p>
     * 
     * @param orgCode 组织代码
     * @param rv 结果集
     * @param eqrv 比对结果集
     * @return 结果集
     */
    private List<Map<String, Object>> getOrg(String orgCode, List<Map<String, Object>> rv, List<String> eqrv) {
        if (!StringUtils.isEmpty(orgCode)) {
            // 获得下级组织
            TsOrganizationCriteria toc = new TsOrganizationCriteria();
            toc.createCriteria().andParentOrgCodeEqualTo(orgCode);
            toc.setOrderByClause("ORG_SORT ASC");
            List<TsOrganization> orgList = this.tom.selectByExample(toc);
            // 递归处理
            if (!CollectionUtils.isEmpty(orgList)) {
                for (TsOrganization org : orgList) {
                    Map<String, Object> orgItem = new HashMap<String, Object>();
                    orgItem.put(ORG_CODE, org.getOrgCode());
                    orgItem.put(ORG_NAME, org.getOrgName());
                    orgItem.put(PARENT_ORG_CODE, org.getParentOrgCode());
                    orgItem.put(MAIN_ORG, "0");
                    if (!eqrv.contains(org.getOrgCode())) {
                        rv.add(orgItem);
                        eqrv.add(org.getOrgCode());
                        rv = getOrg(org.getOrgCode(), rv, eqrv);
                    }
                }
            }
        }
        return rv;
    }

    /**
     * <p>
     * Description: 获得上级组织
     * </p>
     * 
     * @param parentOrgCode 上级组织代码
     * @param rv 结果集
     * @param eqrv 比对结果集
     * @return 结果集
     */
    private List<Map<String, Object>> getParentOrg(String parentOrgCode, List<Map<String, Object>> rv,
            List<String> eqrv) {
        if (!StringUtils.isEmpty(parentOrgCode)) {
            // 获得上级组织
            TsOrganization org = this.tom.selectByPrimaryKey(parentOrgCode);
            // 递归处理
            if (org != null) {
                Map<String, Object> orgItem = new HashMap<String, Object>();
                orgItem.put(ORG_CODE, org.getOrgCode());
                orgItem.put(ORG_NAME, org.getOrgName());
                orgItem.put(PARENT_ORG_CODE, org.getParentOrgCode());
                orgItem.put(MAIN_ORG, "0");
                if (!eqrv.contains(org.getOrgCode())) {
                    rv.add(orgItem);
                    eqrv.add(org.getOrgCode());
                    rv = getParentOrg(org.getParentOrgCode(), rv, eqrv);
                }
            }
        }
        return rv;
    }

    /**
     * <p>
     * Description: 获得功能树
     * </p>
     * 
     * @param loginName 用户名
     * @param jobCodes 岗位代码
     * @param roleCodes 角色代码
     * @param loadFunctionType 加载类别
     * @return 功能树
     */
    public List<Map<String, Object>> getUserJobRoleFunctionTree(String loginName, String[] jobCodes, String[] roleCodes,
            String loadFunctionType) {
        // 获得功能的数据
        List<Map<String, Object>> rv = new ArrayList<Map<String, Object>>();
        if (!StringUtils.isEmpty(loginName)) {
            // 判断加载类别
            List<Map<String, Object>> functionList = new ArrayList<Map<String, Object>>();
            if (StringUtils.isEmpty(loadFunctionType)) {
                List<Map<String, Object>> functionLista = this.pm.getJobFunction(loginName, jobCodes, roleCodes);
                List<Map<String, Object>> functionListb = this.pm.getUserFunction(loginName);
                functionList.addAll(functionLista);
                functionList.addAll(functionListb);
            } else if ("1".equals(loadFunctionType)) {
                List<Map<String, Object>> functionLista = this.pm.getJobFunction(loginName, jobCodes, roleCodes);
                functionList.addAll(functionLista);
            } else if ("2".equals(loadFunctionType)) {
                List<Map<String, Object>> functionListb = this.pm.getUserFunction(loginName);
                functionList.addAll(functionListb);
            } else {
                this.getLog().error("未知功能加载类别");
            }

            // 如有数据,在继续操作
            List<String> eqrv = new ArrayList<String>();
            if (!CollectionUtils.isEmpty(functionList)) {
                for (Map<String, Object> item : functionList) {
                    TsFunction f = this.tfm.selectByPrimaryKey(item.get(FUNCTION_CODE).toString());
                    if (!eqrv.contains(f.getFunctionCode())) {
                        Map<String, Object> functionItem = new HashMap<String, Object>();
                        functionItem.put(FUNCTION_CODE, f.getFunctionCode());
                        functionItem.put(FUNCTION_NAME, f.getFunctionName());
                        functionItem.put(PARENT_FUNCTION_CODE, f.getParentFunctionCode());
                        rv.add(functionItem);
                        eqrv.add(f.getFunctionCode());
                        rv = this.getParentFunction(f.getParentFunctionCode(), rv, eqrv);
                    }

                    TsPurviewKey pk = new TsPurviewKey();
                    pk.setFunctionCode(item.get(FUNCTION_CODE).toString());
                    pk.setPurviewCode(item.get(PURVIEW_CODE).toString());
                    TsPurview purview = this.tpm.selectByPrimaryKey(pk);
                    if (!eqrv.contains(purview.getFunctionCode() + CHAR_1 + purview.getPurviewCode())) {
                        Map<String, Object> purviewItem = new HashMap<String, Object>();
                        purviewItem.put(FUNCTION_CODE, purview.getFunctionCode() + CHAR_1 + purview.getPurviewCode());
                        purviewItem.put(FUNCTION_NAME, purview.getPurviewName());
                        purviewItem.put(PARENT_FUNCTION_CODE, purview.getFunctionCode());
                        rv.add(purviewItem);
                        eqrv.add(purview.getFunctionCode() + CHAR_1 + purview.getPurviewCode());
                    }

                }
            }
        }
        return rv;
    }

    /**
     * <p>
     * Description: 获得上级菜单
     * </p>
     * 
     * @param parentFunctionCode 上级菜单代码
     * @param rv 结果集
     * @param eqrv 比较结果集
     * @return 结果集
     */
    private List<Map<String, Object>> getParentFunction(String parentFunctionCode, List<Map<String, Object>> rv,
            List<String> eqrv) {
        if (!StringUtils.isEmpty(parentFunctionCode)) {
            // 获得上级组织
            TsFunction function = this.tfm.selectByPrimaryKey(parentFunctionCode);
            // 递归处理
            if (function != null) {
                Map<String, Object> functionItem = new HashMap<String, Object>();
                functionItem.put(FUNCTION_CODE, function.getFunctionCode());
                functionItem.put(FUNCTION_NAME, function.getFunctionName());
                functionItem.put(PARENT_FUNCTION_CODE, function.getParentFunctionCode());
                if (!eqrv.contains(function.getFunctionCode())) {
                    rv.add(functionItem);
                    eqrv.add(function.getFunctionCode());
                    rv = getParentFunction(function.getParentFunctionCode(), rv, eqrv);
                }
            }
        }
        return rv;
    }

    /**
     * <p>
     * Description: 返回角色清单
     * </p>
     * 
     * @param loginName 用户名
     * @param jobCodes 岗位代码
     * @return 角色列表
     */
    public List<Map<String, Object>> getUserJobRoleList(String loginName, String[] jobCodes) {
        if (!StringUtils.isEmpty(loginName)) {
            return this.pm.getUserJobRoleList(loginName, jobCodes);
        } else {
            return new ArrayList<Map<String, Object>>();
        }
    }

    /**
     * <p>
     * Description: 返回用户岗位列表
     * </p>
     * 
     * @param loginName 当前用户名
     * @return 岗位列表
     */
    public List<Map<String, Object>> getUserJobList(String loginName) {
        if (!StringUtils.isEmpty(loginName)) {
            return this.pm.getUserJobList(loginName);
        } else {
            return new ArrayList<Map<String, Object>>();
        }
    }

    /**
     * <p>
     * Description: 返回用户列表
     * </p>
     * 
     * @param page 当前页
     * @param rows 每页行数
     * @param loginName 登录名
     * @param userName 用户名
     * 
     * @return 用户列表
     */
    public Map<String, Object> getUserList(int page, int rows, String loginName, String userName) {
        // 格式化参数(like查询)
        String loginNameParam = loginName;
        if (!StringUtils.isEmpty(loginName)) {
            loginNameParam = "%" + loginName + "%";
        }
        String userNameParam = userName;
        if (!StringUtils.isEmpty(userName)) {
            userNameParam = "%" + userName + "%";
        }
        // 开始查询
        PageInterceptor.startPage(rows, page);
        PageResult<Map<String, Object>> pr = PageInterceptor
                .getPageResult(this.pm.getUserListPageQuery(loginNameParam, userNameParam));
        Map<String, Object> rv = new HashMap<String, Object>();
        rv.put("total", pr.getTotalRecords());
        rv.put("rows", pr.toList());
        return rv;
    }

}
