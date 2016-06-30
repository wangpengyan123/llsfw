/**
 * ReflectHelper.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.core.pagequery;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.llsfw.core.exception.SystemRuntimeException;

/**
 * <p>
 * ClassName: ReflectHelper
 * </p>
 * <p>
 * Description: 反射工具类
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月23日
 * </p>
 */
public class ReflectHelper {

    /**
     * <p>
     * Field log: 日志
     * </p>
     */
    private static final Logger LOG = LoggerFactory.getLogger(ReflectHelper.class);

    /**
     * <p>
     * Description: 构造函数
     * </p>
     */
    private ReflectHelper() {

    }

    /**
     * <p>
     * Description: 根据字段名获得字段
     * </p>
     * 
     * @param obj 对象
     * @param fieldName 字段名
     * @return 字段
     */
    public static Field getFieldByFieldName(Object obj, String fieldName) {
        for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass
                .getSuperclass()) {
            try {
                return superClass.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                LOG.trace("ReflectHelper.getFieldByFieldName", e);
            }
        }
        return null;
    }

    /**
     * <p>
     * Description: 根据字段名获得值
     * </p>
     * 
     * @param obj 对象
     * @param fieldName 字段名
     * @return 值
     * @throws IllegalAccessException 异常
     * @throws IllegalArgumentException 异常
     */
    public static Object getValueByFieldName(Object obj, String fieldName)
            throws IllegalArgumentException, IllegalAccessException {
        Field field = getFieldByFieldName(obj, fieldName);
        Object value = null;
        if (field != null) {
            if (field.isAccessible()) {
                value = field.get(obj);
            } else {
                field.setAccessible(true);
                value = field.get(obj);
                field.setAccessible(false);
            }
        }
        return value;
    }

    /**
     * <p>
     * Description: 根据字段名设置字段值
     * </p>
     * 
     * @param obj 对象
     * @param fieldName 字段名
     * @param value 值
     */
    public static void setValueByFieldName(Object obj, String fieldName, Object value) {
        try {
            Field field = obj.getClass().getDeclaredField(fieldName);
            if (field.isAccessible()) {
                field.set(obj, value);
            } else {
                field.setAccessible(true);
                field.set(obj, value);
                field.setAccessible(false);
            }
        } catch (SecurityException e) {
            throw new SystemRuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new SystemRuntimeException(e);
        } catch (IllegalArgumentException e) {
            throw new SystemRuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new SystemRuntimeException(e);
        }
    }

}
