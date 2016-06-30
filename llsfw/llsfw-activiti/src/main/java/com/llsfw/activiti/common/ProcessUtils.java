/**
 * ProcessUtils.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.activiti.common;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.activiti.engine.form.FormProperty;
import org.apache.commons.collections4.CollectionUtils;

/**
 * <p>
 * ClassName: ProcessUtils
 * </p>
 * <p>
 * Description: 工作流工具类
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月24日
 * </p>
 */
public class ProcessUtils {

    /**
     * <p>
     * Field LABLE_START_TD: 常量
     * </p>
     */
    private static final String LABLE_START_TD = "<td align=\"right\"><label style=\"font-weight: bold\">";

    /**
     * <p>
     * Field START_TR: 常量
     * </p>
     */
    private static final String START_TR = "<tr>";

    /**
     * <p>
     * Field START_TD: 常量
     * </p>
     */
    private static final String START_TD = "<td>";

    /**
     * <p>
     * Field END_TD: 常量
     * </p>
     */
    private static final String END_TD = "</td>";

    /**
     * <p>
     * Field C_1: 常量
     * </p>
     */
    private static final String C_1 = "'";

    /**
     * <p>
     * Description: 构造函数
     * </p>
     */
    private ProcessUtils() {
    }

    /**
     * <p>
     * Description: 格式化表单
     * </p>
     * 
     * @param formProperties 表单属性
     * @return 结果
     */
    public static String formatForm(List<FormProperty> formProperties) {
        StringBuilder sb = new StringBuilder();
        sb.append("<table>");
        if (!CollectionUtils.isEmpty(formProperties)) {
            for (FormProperty formProperty : formProperties) {
                // 判断字段类型
                if ("string".equals(formProperty.getType().getName())) {
                    sb.append(formatStringInput(formProperty));
                } else if ("long".equals(formProperty.getType().getName())) {
                    sb.append(formatLongInput(formProperty));
                } else if ("boolean".equals(formProperty.getType().getName())) {
                    sb.append(formatBooleanInput(formProperty));
                } else if ("date".equals(formProperty.getType().getName())) {
                    sb.append(formatDateInput(formProperty));
                } else if ("enum".equals(formProperty.getType().getName())) {
                    sb.append(formatEnmuInput(formProperty));
                } else {
                    sb.append(formatDefaultInput(formProperty));
                }
            }
        }
        sb.append("</table>");
        return sb.toString();
    }

    /**
     * <p>
     * Description: 格式化默认input
     * </p>
     * 
     * @param formProperty 表单参数
     * @return 结果
     */
    private static String formatDefaultInput(FormProperty formProperty) {
        // 构造字段
        StringBuilder sb = new StringBuilder();
        // 判断字段是否可读
        if (formProperty.isReadable()) {
            sb.append(START_TR);
            sb.append("<td align=\"right\">" + formProperty.getName() + "(未知字段类型" + formProperty.getType().getName()
                    + "):" + END_TD);
            sb.append(START_TD);
            sb.append(formProperty.getValue() == null ? "" : formProperty.getValue());
            sb.append(END_TD);
            sb.append("</tr>");
        }
        return sb.toString();
    }

    /**
     * <p>
     * Description: 格式化string
     * </p>
     * 
     * @param formProperty 表单参数
     * @return 结果
     */
    private static String formatStringInput(FormProperty formProperty) {
        // 构造字段
        StringBuilder sb = new StringBuilder();
        // 判断字段是否可读
        if (formProperty.isReadable()) {
            sb.append(START_TR);
            sb.append(LABLE_START_TD);
            sb.append(formProperty.getName());
            sb.append(":" + "</label></td>");
            sb.append(START_TD);
            sb.append(MessageFormat.format(
                    "<input id=\"{0}\" name=\"{1}\" value=\"{2}\" class=\"easyui-textbox\" style=\"width:200px\" "
                            + "data-options=\"prompt:{3},required:{4},editable:{5},disabled:{6},readonly:{7}\" />",
                    formProperty.getId(), formProperty.getId(),
                    formProperty.getValue() == null ? "" : formProperty.getValue(), C_1 + formProperty.getName() + C_1,
                    formProperty.isRequired(), formProperty.isWritable(), !formProperty.isWritable(),
                    !formProperty.isWritable()));
            sb.append(END_TD);
            sb.append("</tr>");
        }
        return sb.toString();
    }

    /**
     * <p>
     * Description: 格式化Long字段
     * </p>
     * 
     * @param formProperty 表单参数
     * @return 结果
     */
    private static String formatLongInput(FormProperty formProperty) {
        // 构造字段
        StringBuilder sb = new StringBuilder();
        // 判断字段是否可读
        if (formProperty.isReadable()) {
            sb.append(START_TR);
            sb.append(LABLE_START_TD);
            sb.append(formProperty.getName());
            sb.append(":" + "</label></td>");
            sb.append(START_TD);
            sb.append(MessageFormat.format(
                    "<input id=\"{0}\" name=\"{1}\" value=\"{2}\" class=\"easyui-numberbox\" "
                            + "style=\"width:200px\" data-options=\"prompt:{3},required:{4},"
                            + "editable:{5},disabled:{6},readonly:{7}\" />",
                    formProperty.getId(), formProperty.getId(),
                    formProperty.getValue() == null ? "" : formProperty.getValue(), C_1 + formProperty.getName() + C_1,
                    formProperty.isRequired(), formProperty.isWritable(), !formProperty.isWritable(),
                    !formProperty.isWritable()));
            sb.append(END_TD);
            sb.append(START_TR);
        }
        return sb.toString();
    }

    /**
     * <p>
     * Description: 格式化boolean参数
     * </p>
     * 
     * @param formProperty 表单参数
     * @return 结果
     */
    private static String formatBooleanInput(FormProperty formProperty) {
        // 构造字段
        StringBuilder sb = new StringBuilder();
        // 判断字段是否可读
        if (formProperty.isReadable()) {
            sb.append(START_TR);
            sb.append(LABLE_START_TD);
            sb.append(formProperty.getName());
            sb.append(":" + "</label></td>");
            sb.append(START_TD);
            String value = formProperty.getValue() == null ? ""
                    : Boolean.parseBoolean(formProperty.getValue()) ? "checked=\"checked\"" : "";
            String disabled = formProperty.isWritable() ? "" : "disabled=\"disabled\" readonly=\"readonly\"";
            sb.append(MessageFormat.format("<input id=\"{0}\" name=\"{1}\" type=\"checkbox\" {2} {3} />",
                    formProperty.getId(), formProperty.getId(), value, disabled));
            sb.append(END_TD);
            sb.append("</tr>");
        }
        return sb.toString();
    }

    /**
     * <p>
     * Description: 格式化date
     * </p>
     * 
     * @param formProperty 表单参数
     * @return 结果
     */
    private static String formatDateInput(FormProperty formProperty) {
        // 构造字段
        StringBuilder sb = new StringBuilder();
        // 判断字段是否可读
        if (formProperty.isReadable()) {
            sb.append(START_TR);
            sb.append(LABLE_START_TD);
            sb.append(formProperty.getName());
            sb.append(":" + "</label></td>");
            sb.append(START_TD);
            sb.append(MessageFormat.format(
                    "<input id=\"{0}\" name=\"{1}\" value=\"{2}\" class=\"easyui-datebox\""
                            + " style=\"width:200px\" data-options=\"editable:false,prompt:{3},"
                            + "required:{4},editable:{5},disabled:{6},readonly:{7}\" />",
                    formProperty.getId(), formProperty.getId(),
                    formProperty.getValue() == null ? "" : formProperty.getValue(), C_1 + formProperty.getName() + C_1,
                    formProperty.isRequired(), formProperty.isWritable(), !formProperty.isWritable(),
                    !formProperty.isWritable()));
            sb.append(END_TD);
            sb.append("</tr>");
        }
        return sb.toString();
    }

    /**
     * <p>
     * Description: 格式化枚举
     * </p>
     * 
     * @param formProperty 表单参数
     * @return 结果
     */
    @SuppressWarnings("unchecked")
    private static String formatEnmuInput(FormProperty formProperty) {
        // 构造字段
        StringBuilder sb = new StringBuilder();
        // 判断字段是否可读
        if (formProperty.isReadable()) {
            sb.append(START_TR);
            sb.append(LABLE_START_TD);
            sb.append(formProperty.getName());
            sb.append(":" + "</label></td>");
            sb.append(START_TD);
            sb.append(MessageFormat.format(
                    "<select id=\"{0}\" name=\"{1}\" value=\"{2}\" class=\"easyui-combobox\" style=\"width:200px\" "
                            + "data-options=\"editable:false,prompt:{3},required:{4},editable:{5},"
                            + "disabled:{6},readonly:{7}\" >",
                    formProperty.getId(), formProperty.getId(),
                    formProperty.getValue() == null ? "" : formProperty.getValue(), C_1 + formProperty.getName() + C_1,
                    formProperty.isRequired(), formProperty.isWritable(), !formProperty.isWritable(),
                    !formProperty.isWritable()));
            Map<String, String> values = (Map<String, String>) formProperty.getType().getInformation("values");
            if (values != null) {
                for (Entry<String, String> enumEntry : values.entrySet()) {
                    sb.append(MessageFormat.format("<option value=\"{0}\">{1}</option>", enumEntry.getKey(),
                            enumEntry.getValue()));
                }
            }
            sb.append("</select>");
            sb.append(END_TD);
            sb.append("</tr>");
        }
        return sb.toString();
    }

}
