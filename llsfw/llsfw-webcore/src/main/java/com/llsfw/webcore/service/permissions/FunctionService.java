/**
 * FunctionService.java
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
import org.springframework.web.servlet.support.RequestContext;

import com.llsfw.core.common.AutoLoadBean;
import com.llsfw.core.common.Constants;
import com.llsfw.core.common.JsonResult;
import com.llsfw.core.common.Uuid;
import com.llsfw.core.service.base.BaseService;
import com.llsfw.webcore.mapper.expand.permissions.IPermissionsMapper;
import com.llsfw.webgen.mapper.standard.permissions.TsFunctionMapper;
import com.llsfw.webgen.mapper.standard.permissions.TsPortalMapper;
import com.llsfw.webgen.mapper.standard.permissions.TsPurviewMapper;
import com.llsfw.webgen.mapper.standard.permissions.TsRoleFunctionMapper;
import com.llsfw.webgen.mapper.standard.permissions.TsUserFunctionMapper;
import com.llsfw.webgen.mapper.standard.permissions.TsUserPortalMapper;
import com.llsfw.webgen.model.standard.permissions.TsFunction;
import com.llsfw.webgen.model.standard.permissions.TsFunctionCriteria;
import com.llsfw.webgen.model.standard.permissions.TsPortal;
import com.llsfw.webgen.model.standard.permissions.TsPortalCriteria;
import com.llsfw.webgen.model.standard.permissions.TsPortalKey;
import com.llsfw.webgen.model.standard.permissions.TsPurview;
import com.llsfw.webgen.model.standard.permissions.TsPurviewCriteria;
import com.llsfw.webgen.model.standard.permissions.TsPurviewKey;
import com.llsfw.webgen.model.standard.permissions.TsRoleFunctionCriteria;
import com.llsfw.webgen.model.standard.permissions.TsUserFunctionCriteria;
import com.llsfw.webgen.model.standard.permissions.TsUserPortalCriteria;

/**
 * <p>
 * ClassName: FunctionService
 * </p>
 * <p>
 * Description: 功能服务
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月24日
 * </p>
 */
@Service
public class FunctionService extends BaseService {

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
     * Field LEVEL_NO: 常量
     * </p>
     */
    private static final String LEVEL_NO = "LEVEL_NO";

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
     * Field tpm: 权限dao
     * </p>
     */
    @Autowired
    private TsPurviewMapper tpm;

    /**
     * <p>
     * Field tufm: 用户权限dao
     * </p>
     */
    @Autowired
    private TsUserFunctionMapper tufm;

    /**
     * <p>
     * Field tfm: 服务
     * </p>
     */
    @Autowired
    private TsFunctionMapper tfm;

    /**
     * <p>
     * Field trfm: 服务
     * </p>
     */
    @Autowired
    private TsRoleFunctionMapper trfm;

    /**
     * <p>
     * Field tportalm: 面板mapper
     * </p>
     */
    @Autowired
    private TsPortalMapper tportalm;

    /**
     * <p>
     * Field tupm: 面板用户mapper
     * </p>
     */
    @Autowired
    private TsUserPortalMapper tupm;

    /**
     * 删除面板
     * 
     * @param functionCode 功能代码
     * @param portalCode 面板代码
     * @return 结果
     */
    public JsonResult<String> portalDelete(String functionCode, String portalCode) {
        // 删除用户面板关联表
        TsUserPortalCriteria tupc = new TsUserPortalCriteria();
        tupc.createCriteria().andPortalCodeEqualTo(portalCode);
        this.tupm.deleteByExample(tupc);
        // 删除面板
        TsPortalKey tpk = new TsPortalKey();
        tpk.setFunctionCode(functionCode);
        tpk.setPortalCode(portalCode);
        this.tportalm.deleteByPrimaryKey(tpk);
        // 返回
        return new JsonResult<String>(Constants.SUCCESS, null);
    }

    /**
     * 编辑面板
     * 
     * @param loginName 用户名
     * @param portal 面板数据
     * @return 结果
     */
    public JsonResult<String> portalAdd(String loginName, TsPortal portal) {
        // 生成面板代码
        if (StringUtils.isEmpty(portal.getPortalCode())) {
            portal.setPortalCode(Uuid.getUuid(false));
        }
        // 删除原有数据
        TsPortalKey tpk = new TsPortalKey();
        tpk.setFunctionCode(portal.getFunctionCode());
        tpk.setPortalCode(portal.getPortalCode());
        this.tportalm.deleteByPrimaryKey(tpk);
        // 新增数据
        portal.setCreateBy(loginName);
        portal.setCreateDate(new Date());
        this.tportalm.insert(portal);
        // 返回
        return new JsonResult<String>(Constants.SUCCESS, null);
    }

    /**
     * 加载面板明细
     * 
     * @param functionCode 功能代码
     * @param portalCode 面板代码
     * @return 结果
     */
    public TsPortal loadPortal(String functionCode, String portalCode) {
        if (!StringUtils.isEmpty(functionCode) && !StringUtils.isEmpty(portalCode)) {
            TsPortalKey tpk = new TsPortalKey();
            tpk.setFunctionCode(functionCode);
            tpk.setPortalCode(portalCode);
            return this.tportalm.selectByPrimaryKey(tpk);
        }
        return null;
    }

    /**
     * 返回面板列表
     * 
     * @param functionCode 功能代码
     * @return 结果
     */
    public List<TsPortal> getPortalList(String functionCode) {
        TsPortalCriteria tpc = new TsPortalCriteria();
        tpc.createCriteria().andFunctionCodeEqualTo(functionCode);
        tpc.setOrderByClause("PORTAL_CODE ASC");
        return this.tportalm.selectByExample(tpc);
    }

    /**
     * <p>
     * Description: 返回完整的功能菜单树形结构<br />
     * 反馈的节点字段有,FUNCTION_CODE,FUNCTION_NAME,LEVEL_NO,children<br />
     * 最后一层为操作权限层,会多出两个字段,FUNCTION_CODE_DISPLY,PURVIEW_CODE,主要为展示层使用,无特殊意义,
     * FUNCTION_CODE本身应该拼接格式为F:P,但是貌似easyui在treegrid中不支持这样的数据,所以采用下划线拼接F_P
     * </p>
     * 
     * @param functionPurviewList
     *            [已有]或者[没有]的功能权限列表,包含的key必须有FUNCTION_CODE,PURVIEW_CODE
     * @param appendPurviewChild 是否包含操作权限节点
     * @return 完整的功能菜单树形结构
     */
    public List<Map<String, Object>> getFunctionPurviewTreeNode(List<Map<String, Object>> functionPurviewList,
            boolean appendPurviewChild) {
        if (!CollectionUtils.isEmpty(functionPurviewList)) {

            // 获得已有的功能
            List<String> functionList = new ArrayList<String>();
            for (Map<String, Object> functionPurviewItem : functionPurviewList) {
                functionList.add(functionPurviewItem.get(FUNCTION_CODE).toString());
            }

            // 获得已有的菜单
            List<String> menuList = this.pm.getParentFunctionListCode(functionList);

            // 获得已有应用
            List<String> appList = this.pm.getParentFunctionListCode(menuList);

            // 获得应用明细
            List<Map<String, Object>> appNode = this.pm.getFunctionList(appList, null);
            List<Map<String, Object>> rvAppList = new ArrayList<Map<String, Object>>();
            for (Map<String, Object> appNodeItem : appNode) {
                Map<String, Object> rvAppItem = new HashMap<String, Object>();
                String appCode = appNodeItem.get(FUNCTION_CODE).toString();
                String appName = appNodeItem.get(FUNCTION_NAME).toString();
                String appLevel = appNodeItem.get(LEVEL_NO).toString();
                rvAppItem.put(FUNCTION_CODE, appCode);
                rvAppItem.put(FUNCTION_NAME, appName);
                rvAppItem.put(LEVEL_NO, appLevel);

                // 获得菜单明细
                List<Map<String, Object>> menuNode = this.pm.getFunctionList(menuList, appCode);
                List<Map<String, Object>> rvMenuList = new ArrayList<Map<String, Object>>();
                for (Map<String, Object> menuNodeItem : menuNode) {
                    Map<String, Object> rvMenuItem = new HashMap<String, Object>();
                    String menuCode = menuNodeItem.get(FUNCTION_CODE).toString();
                    String menuName = menuNodeItem.get(FUNCTION_NAME).toString();
                    String menuLevel = menuNodeItem.get(LEVEL_NO).toString();
                    rvMenuItem.put(FUNCTION_CODE, menuCode);
                    rvMenuItem.put(FUNCTION_NAME, menuName);
                    rvMenuItem.put(LEVEL_NO, menuLevel);

                    // 获得功能明细
                    List<Map<String, Object>> functionNode = this.pm.getFunctionList(functionList, menuCode);
                    List<Map<String, Object>> rvFunctionList = new ArrayList<Map<String, Object>>();
                    for (Map<String, Object> functionNodeItem : functionNode) {
                        Map<String, Object> rvFunctionItem = new HashMap<String, Object>();
                        String functionCode = functionNodeItem.get(FUNCTION_CODE).toString();
                        String functionName = functionNodeItem.get(FUNCTION_NAME).toString();
                        String functionLevel = functionNodeItem.get(LEVEL_NO).toString();
                        rvFunctionItem.put(FUNCTION_CODE, functionCode);
                        rvFunctionItem.put(FUNCTION_NAME, functionName);
                        rvFunctionItem.put(LEVEL_NO, functionLevel);

                        // 获得权限明细
                        if (appendPurviewChild) {
                            List<Map<String, Object>> rvPurviewList = new ArrayList<Map<String, Object>>();
                            for (Map<String, Object> functionPurviewItem : functionPurviewList) {
                                String purviewfunctionCode = functionPurviewItem.get(FUNCTION_CODE).toString();
                                String purviewCode = functionPurviewItem.get("PURVIEW_CODE").toString();
                                TsPurviewKey tpk = new TsPurviewKey();
                                tpk.setFunctionCode(purviewfunctionCode);
                                tpk.setPurviewCode(purviewCode);
                                TsPurview purview = this.tpm.selectByPrimaryKey(tpk);
                                if (purview != null && functionCode.equals(purviewfunctionCode)) {
                                    Map<String, Object> rvPurviewItem = new HashMap<String, Object>();
                                    rvPurviewItem.put(FUNCTION_CODE,
                                            purview.getFunctionCode() + "_" + purview.getPurviewCode());
                                    rvPurviewItem.put("FUNCTION_CODE_DISPLY", purview.getFunctionCode()); // 比其他节点多一个字段
                                    rvPurviewItem.put("PURVIEW_CODE", purview.getPurviewCode()); // 比其他节点多一个字段
                                    rvPurviewItem.put(FUNCTION_NAME, purview.getPurviewName());
                                    rvPurviewItem.put(LEVEL_NO, "PURVIEW");
                                    rvPurviewList.add(rvPurviewItem);
                                }
                            }
                            rvFunctionItem.put(CHILDREN, rvPurviewList);
                        }
                        rvFunctionList.add(rvFunctionItem);
                    }
                    rvMenuItem.put(CHILDREN, rvFunctionList);
                    rvMenuList.add(rvMenuItem);
                }
                rvAppItem.put(CHILDREN, rvMenuList);
                rvAppList.add(rvAppItem);
            }
            return rvAppList;
        }
        return new ArrayList<Map<String, Object>>();
    }

    /**
     * <p>
     * Description: 修改操作权限
     * </p>
     * 
     * @param valueMap 修改值
     * @param functionCode 功能代码
     * @param purviewCode 权限代码
     * @param loginName 登陆人
     * @return 操作结果
     * @throws NoSuchMethodException 异常
     * @throws SecurityException 异常
     */
    public JsonResult<String> purviewEdit(Map<String, String[]> valueMap, String functionCode, String purviewCode,
            String loginName) throws SecurityException, NoSuchMethodException {
        TsPurviewKey key = new TsPurviewKey();
        key.setFunctionCode(functionCode);
        key.setPurviewCode(purviewCode);
        TsPurview tp = this.tpm.selectByPrimaryKey(key);
        tp = AutoLoadBean.load(tp, valueMap);
        tp.setCreateBy(loginName);
        tp.setCreateDate(new Date());
        this.tpm.updateByPrimaryKey(tp);
        return new JsonResult<String>(Constants.SUCCESS, null);

    }

    /**
     * <p>
     * Description: 返回操作权限
     * </p>
     * 
     * @param functionCode 功能代码
     * @param purviewCode 权限代码
     * @return 操作权限
     */
    public TsPurview getPurview(String functionCode, String purviewCode) {
        TsPurviewKey key = new TsPurviewKey();
        key.setFunctionCode(functionCode);
        key.setPurviewCode(purviewCode);
        return this.tpm.selectByPrimaryKey(key);
    }

    /**
     * <p>
     * Description: 添加操作权限
     * </p>
     * 
     * @param loginName 登陆名称
     * @param purview 权限
     * @param requestContext 请求上下文
     * @return 操作结果
     */
    public JsonResult<String> purviewAdd(String loginName, TsPurview purview, RequestContext requestContext) {
        TsPurviewKey tpk = new TsPurviewKey();
        tpk.setFunctionCode(purview.getFunctionCode());
        tpk.setPurviewCode(purview.getPurviewCode());
        if (this.tpm.selectByPrimaryKey(tpk) == null) {
            purview.setCreateBy(loginName);
            purview.setCreateDate(new Date());
            this.tpm.insert(purview);
            return new JsonResult<String>(Constants.SUCCESS, null);
        } else {
            return new JsonResult<String>(Constants.FAIL, requestContext.getMessage("function.puw.add.error.3"));
        }
    }

    /**
     * <p>
     * Description: 权限删除
     * </p>
     * 
     * @param functionCode 功能代码
     * @param purviewCode 权限代码
     * @return 操作结果
     */
    public JsonResult<String> purviewDelete(String functionCode, String purviewCode) {
        // 删除角色权限表
        TsRoleFunctionCriteria trfc = new TsRoleFunctionCriteria();
        trfc.createCriteria().andFunctionCodeEqualTo(functionCode).andPurviewCodeEqualTo(purviewCode);
        this.trfm.deleteByExample(trfc);
        // 删除用户权限表
        TsUserFunctionCriteria tufc = new TsUserFunctionCriteria();
        tufc.createCriteria().andFunctionCodeEqualTo(functionCode).andPurviewCodeEqualTo(purviewCode);
        this.tufm.deleteByExample(tufc);
        // 删除权限表
        TsPurviewKey tpk = new TsPurviewKey();
        tpk.setFunctionCode(functionCode);
        tpk.setPurviewCode(purviewCode);
        this.tpm.deleteByPrimaryKey(tpk);
        // 返回
        return new JsonResult<String>(Constants.SUCCESS, null);
    }

    /**
     * <p>
     * Description: 返回当前功能的上级列表
     * </p>
     * 
     * @param levelNo 级别编号
     * @return 当前功能的上级列表
     */
    public List<TsFunction> loadParentFunction(Integer levelNo) {
        TsFunctionCriteria tfc = new TsFunctionCriteria();
        tfc.createCriteria().andLevelNoEqualTo(levelNo - 1);
        return this.tfm.selectByExample(tfc);
    }

    /**
     * <p>
     * Description: 更新功能
     * </p>
     * 
     * @param valueMap 提交的更新值
     * @param functionCode 功能代码
     * @param loginName 操作人
     * @return 操作结果
     * @throws NoSuchMethodException 异常
     * @throws SecurityException 异常
     */
    public JsonResult<String> editFunction(Map<String, String[]> valueMap, String functionCode, String loginName)
            throws SecurityException, NoSuchMethodException {
        // 更新当前功能
        TsFunction tf = this.tfm.selectByPrimaryKey(functionCode);
        tf = AutoLoadBean.load(tf, valueMap);
        tf.setUpdateBy(loginName);
        tf.setUpdateDate(new Date());
        this.tfm.updateByPrimaryKey(tf);
        // 更新子节点
        this.enableOrDisableLogic(tf, loginName);
        // 返回
        return new JsonResult<String>(Constants.SUCCESS, null);
    }

    /**
     * <p>
     * Description: 启用停用逻辑
     * </p>
     * 
     * @param f 功能代码
     * @param l 操作人
     */
    private void enableOrDisableLogic(TsFunction f, String l) {
        String disablevalue = "0"; // 停用
        String enablevalue = "1"; // 启用
        if (disablevalue.equals(f.getStatus())) { // 如果节点为停用,则子节点通通停用
            // 获得所有子节点
            List<Map<String, Object>> nodeList = getAllChildNode(f.getFunctionCode(), null);

            // 依次更新子节点,更新为停用
            if (!CollectionUtils.isEmpty(nodeList)) {
                for (int i = 0; i < nodeList.size(); i++) {
                    TsFunction tf = new TsFunction();
                    tf.setFunctionCode(nodeList.get(i).get(FUNCTION_CODE).toString());
                    tf.setStatus(disablevalue);
                    tf.setUpdateBy(l);
                    tf.setUpdateDate(new Date());
                    this.tfm.updateByPrimaryKeySelective(tf);
                }
            }
        } else if (enablevalue.equals(f.getStatus())) { // 启用,如果启用,则依次启用所有关联的父节点
            // 获得所有父节点
            List<Map<String, Object>> nodeList = getAllParentNode(f.getFunctionCode(), null);

            // 依次更新父节点,更新为启用
            if (!CollectionUtils.isEmpty(nodeList)) {
                for (int i = 0; i < nodeList.size(); i++) {
                    TsFunction tf = new TsFunction();
                    tf.setFunctionCode(nodeList.get(i).get(FUNCTION_CODE).toString());
                    tf.setStatus(enablevalue);
                    tf.setUpdateBy(l);
                    tf.setUpdateDate(new Date());
                    this.tfm.updateByPrimaryKeySelective(tf);
                }
            }
        }
    }

    /**
     * <p>
     * Description: 返回所有的父节点
     * </p>
     * 
     * @param functionCode 当前节点
     * @param parentFunctionCode 父节点
     * @return 结果集
     */
    private List<Map<String, Object>> getAllParentNode(String functionCode, String parentFunctionCode) {
        // 返回值
        List<Map<String, Object>> rv = new ArrayList<Map<String, Object>>();
        // 获得所有关联的节点
        TsFunctionCriteria tfc = new TsFunctionCriteria();
        if (!StringUtils.isEmpty(functionCode)) { // 本级
            tfc.createCriteria().andFunctionCodeEqualTo(functionCode);
        } else if (!StringUtils.isEmpty(parentFunctionCode)) { // 上级
            tfc.createCriteria().andFunctionCodeEqualTo(parentFunctionCode);
        } else {
            return rv;
        }
        List<TsFunction> functionList = this.tfm.selectByExample(tfc);
        if (!CollectionUtils.isEmpty(functionList)) {
            for (int i = 0; i < functionList.size(); i++) {
                Map<String, Object> item = new HashMap<String, Object>();
                item.put(FUNCTION_CODE, functionList.get(i).getFunctionCode());
                item.put("STATUS", functionList.get(i).getStatus());
                rv.add(item);

                // 循环添加下级
                rv.addAll(getAllParentNode(null, functionList.get(i).getParentFunctionCode()));
            }
        }
        return rv;
    }

    /**
     * <p>
     * Description: 获得所有子节点
     * </p>
     * 
     * @param functionCode 当前节点
     * @param parentFunctionCode 父节点
     * @return 结果集
     */
    private List<Map<String, Object>> getAllChildNode(String functionCode, String parentFunctionCode) {
        // 返回值
        List<Map<String, Object>> rv = new ArrayList<Map<String, Object>>();
        // 获得所有关联的节点
        TsFunctionCriteria tfc = new TsFunctionCriteria();
        if (!StringUtils.isEmpty(functionCode)) { // 本级
            tfc.createCriteria().andFunctionCodeEqualTo(functionCode);
        } else if (!StringUtils.isEmpty(parentFunctionCode)) { // 下级
            tfc.createCriteria().andParentFunctionCodeEqualTo(parentFunctionCode);
        } else {
            return rv;
        }
        List<TsFunction> functionList = this.tfm.selectByExample(tfc);
        if (!CollectionUtils.isEmpty(functionList)) {
            for (int i = 0; i < functionList.size(); i++) {
                Map<String, Object> item = new HashMap<String, Object>();
                item.put(FUNCTION_CODE, functionList.get(i).getFunctionCode());
                item.put("STATUS", functionList.get(i).getStatus());
                rv.add(item);

                // 循环添加下级
                rv.addAll(getAllChildNode(null, functionList.get(i).getFunctionCode()));
            }
        }
        return rv;
    }

    /**
     * <p>
     * Description: 获得功能对象
     * </p>
     * 
     * @param functionCode 功能代码
     * @return 功能对象
     */
    public TsFunction loadFunction(String functionCode) {
        return this.tfm.selectByPrimaryKey(functionCode);
    }

    /**
     * <p>
     * Description: 保存功能
     * </p>
     * 
     * @param tf 功能对象
     * @param loginName 登录名
     * @return 操作结果
     */
    public JsonResult<String> addFunction(TsFunction tf, String loginName) {
        // 如果当前级别为1,则默认添加将上级代码设置为0
        if (Constants.APP_LEVEL == tf.getLevelNo()) {
            tf.setParentFunctionCode("0");
        }

        // 保存
        tf.setCreateBy(loginName);
        tf.setCreateDate(new Date());
        this.tfm.insert(tf);

        // 如果当前级别为3,则默认添加*和view两个权限
        if (Constants.FUNCTION_LEVEL == tf.getLevelNo()) {
            TsPurview purview = new TsPurview();
            purview.setFunctionCode(tf.getFunctionCode());
            purview.setPurviewCode("*");
            purview.setPurviewName("所有权限");
            purview.setCreateBy(loginName);
            purview.setCreateDate(new Date());
            this.tpm.insert(purview);

            purview.setPurviewCode("view");
            purview.setPurviewName("查看权限");
            this.tpm.insert(purview);
        }

        // 设定返回值
        return new JsonResult<String>(Constants.SUCCESS, null);
    }

    /**
     * <p>
     * Description: 校验功能代码是否存在
     * </p>
     * 
     * @param functionCode 功能代码
     * @return false:不通过,true:通过
     */
    public boolean functionCodeUniqueCheck(String functionCode) {
        TsFunctionCriteria tfc = new TsFunctionCriteria();
        tfc.createCriteria().andFunctionCodeEqualTo(functionCode);
        return this.tfm.countByExample(tfc) > 0 ? false : true;
    }

    /**
     * <p>
     * Description: 删除功能
     * </p>
     * 
     * @param functionCode 功能代码
     * @return 操作结果
     */
    public JsonResult<String> deleteFunction(String functionCode) {
        this.deleteLogic(functionCode, null);
        return new JsonResult<String>(Constants.SUCCESS, null);
    }

    /**
     * <p>
     * Description: 删除节点逻辑
     * </p>
     * 
     * @param functionCode 功能代码
     * @param parentFunctionCode 上级功能代码
     */
    private void deleteLogic(String functionCode, String parentFunctionCode) {
        // 获得所有关联的节点
        TsFunctionCriteria tfc = new TsFunctionCriteria();
        if (!StringUtils.isEmpty(functionCode)) { // 删除本级
            tfc.createCriteria().andFunctionCodeEqualTo(functionCode);
        } else if (!StringUtils.isEmpty(parentFunctionCode)) { // 删除下级
            tfc.createCriteria().andParentFunctionCodeEqualTo(parentFunctionCode);
        }
        List<TsFunction> functionList = this.tfm.selectByExample(tfc);
        if (!CollectionUtils.isEmpty(functionList)) {
            for (int i = 0; i < functionList.size(); i++) {
                // 删除用户功能权限关联表
                TsUserFunctionCriteria tufc = new TsUserFunctionCriteria();
                tufc.createCriteria().andFunctionCodeEqualTo(functionList.get(i).getFunctionCode());
                this.tufm.deleteByExample(tufc);

                // 删除角色功能关联表
                TsRoleFunctionCriteria trfc = new TsRoleFunctionCriteria();
                trfc.createCriteria().andFunctionCodeEqualTo(functionList.get(i).getFunctionCode());
                this.trfm.deleteByExample(trfc);

                // 删除权限
                TsPurviewCriteria tpc = new TsPurviewCriteria();
                tpc.createCriteria().andFunctionCodeEqualTo(functionList.get(i).getFunctionCode());
                this.tpm.deleteByExample(tpc);

                // 删除功能
                this.tfm.deleteByPrimaryKey(functionList.get(i).getFunctionCode());

                // 递归向下删除
                deleteLogic(null, functionList.get(i).getFunctionCode());
            }
        }
    }

    /**
     * <p>
     * Description: 获得权限列表
     * </p>
     * 
     * @param functionCode 功能代码
     * @return 权限列表
     */
    public List<TsPurview> getPurviewList(String functionCode) {
        TsPurviewCriteria tpc = new TsPurviewCriteria();
        tpc.createCriteria().andFunctionCodeEqualTo(functionCode);
        tpc.setOrderByClause("PURVIEW_CODE ASC");
        return this.tpm.selectByExample(tpc);
    }

    /**
     * <p>
     * Description: 返回系统节点
     * </p>
     * 
     * @param hasChildren 是否包含子节点 true:包含,false:不包含
     * @return 系统节点
     */
    public List<Map<String, Object>> getAppNode(boolean hasChildren) {
        List<Map<String, Object>> appNode = this.pm.appNode("1", "0");
        // 加载菜单节点(MENU层)
        if (hasChildren && !CollectionUtils.isEmpty(appNode)) {
            for (int i = 0; i < appNode.size(); i++) {
                List<Map<String, Object>> menuNode = this.getMenuNode(appNode.get(i).get(FUNCTION_CODE).toString(),
                        true);

                // 如果有子节点,则添加子节点
                if (!CollectionUtils.isEmpty(menuNode)) {
                    appNode.get(i).put(CHILDREN, menuNode);
                }
            }
        }
        return appNode;
    }

    /**
     * <p>
     * Description: 获得菜单清单
     * </p>
     * 
     * @param parentFunctionCode 上级应用代码
     * @param hasChildren 是否包含子集
     * @return 菜单清单
     */
    private List<Map<String, Object>> getMenuNode(String parentFunctionCode, boolean hasChildren) {
        List<Map<String, Object>> menuNode = this.pm.appNode("2", parentFunctionCode);
        // 加载功能节点(function层)
        if (hasChildren && !CollectionUtils.isEmpty(menuNode)) {
            for (int i = 0; i < menuNode.size(); i++) {
                Map<String, Object> item = menuNode.get(i);
                List<Map<String, Object>> functionNode = this.getFunctionNode(item.get(FUNCTION_CODE).toString());

                // 如果有子节点,则添加子节点
                if (!CollectionUtils.isEmpty(functionNode)) {
                    menuNode.get(i).put(CHILDREN, functionNode);
                }
            }
        }
        return menuNode;
    }

    /**
     * <p>
     * Description: 获得功能清单
     * </p>
     * 
     * @param parentFunctionCode 上级菜单代码
     * @return 功能清单
     */
    private List<Map<String, Object>> getFunctionNode(String parentFunctionCode) {
        return this.pm.appNode("3", parentFunctionCode);
    }
}
