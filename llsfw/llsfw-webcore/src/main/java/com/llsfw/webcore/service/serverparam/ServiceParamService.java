/**
 * ServiceParamService.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.webcore.service.serverparam;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.llsfw.core.common.AutoLoadBean;
import com.llsfw.core.common.Constants;
import com.llsfw.core.common.JsonResult;
import com.llsfw.core.service.base.BaseService;
import com.llsfw.generator.mapper.standard.system.TtServerGlobalParametersMapper;
import com.llsfw.generator.model.standard.system.TtServerGlobalParameters;
import com.llsfw.generator.model.standard.system.TtServerGlobalParametersCriteria;
import com.llsfw.webcore.mapper.expand.serverparam.IServerParametersMapper;

/**
 * <p>
 * ClassName: ServiceParamService
 * </p>
 * <p>
 * Description: 参数服务
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月24日
 * </p>
 */
@Service
public class ServiceParamService extends BaseService {
    /**
     * <p>
     * Field tsgp: 参数dao
     * </p>
     */
    @Autowired
    private TtServerGlobalParametersMapper tsgpm;

    /**
     * <p>
     * Field spm: 参数mapper
     * </p>
     */
    @Autowired
    private IServerParametersMapper spm;

    /**
     * <p>
     * Description: 修改参数
     * </p>
     * 
     * @param valueMap 请求参数
     * @param parametersCode 主键
     * @return 修改结果
     * @throws NoSuchMethodException 异常
     * @throws SecurityException 异常
     */
    public JsonResult<String> editParameters(Map<String, String[]> valueMap, String parametersCode)
            throws SecurityException, NoSuchMethodException {
        TtServerGlobalParameters tsgp = this.tsgpm.selectByPrimaryKey(parametersCode);
        tsgp = AutoLoadBean.load(tsgp, valueMap);
        this.tsgpm.updateByPrimaryKey(tsgp);
        return new JsonResult<String>(Constants.SUCCESS, null);
    }

    /**
     * <p>
     * Description:加载参数对象
     * </p>
     * 
     * @param parametersCode 参数代码
     * @return 参数对象
     */
    public TtServerGlobalParameters loadParam(String parametersCode) {
        return this.tsgpm.selectByPrimaryKey(parametersCode);
    }

    /**
     * <p>
     * Description: 删除参数
     * </p>
     * 
     * @param parametersCode 参数代码
     * @return 操作结果
     */
    public JsonResult<String> deleteParam(String parametersCode) {
        this.tsgpm.deleteByPrimaryKey(parametersCode);
        return new JsonResult<String>(Constants.SUCCESS, null);
    }

    /**
     * <p>
     * Description: 保存参数
     * </p>
     * 
     * @param tsgp 需保存参数的对象
     * @return 返回保存结果
     */
    public JsonResult<String> addParameters(TtServerGlobalParameters tsgp) {
        this.tsgpm.insert(tsgp);
        return new JsonResult<String>(Constants.SUCCESS, null);
    }

    /**
     * <p>
     * Description: 校验参数代码是否重复
     * </p>
     * 
     * @param parametersCode 参数代码
     * @return 是否重复 true:通过,false:不通过
     */
    public boolean parametersCodeUniqueCheck(String parametersCode) {
        TtServerGlobalParametersCriteria tsgpc = new TtServerGlobalParametersCriteria();
        tsgpc.createCriteria().andParametersCodeEqualTo(parametersCode);
        return this.tsgpm.countByExample(tsgpc) > 0 ? false : true;
    }

    /**
     * <p>
     * Description: 返回参数列表
     * </p>
     * 
     * @param parametersCode 参数代码
     * @param parametersDesc 参数描述
     * @return 参数列表
     */
    public List<Map<String, Object>> getParamsList(String parametersCode, String parametersDesc) {
        // 格式化参数(LIKE查询)
        String parametersCodeParam = parametersCode;
        if (!StringUtils.isEmpty(parametersCode)) {
            parametersCodeParam = "%" + parametersCode + "%";
        }
        String parametersDescParam = parametersDesc;
        if (!StringUtils.isEmpty(parametersDesc)) {
            parametersDescParam = "%" + parametersDesc + "%";
        }
        return this.spm.getParamsList(parametersCodeParam, parametersDescParam);
    }
}
