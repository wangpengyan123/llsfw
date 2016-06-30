/**
 * SerializeUtils.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.core.security.session;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.llsfw.core.common.Constants;

/**
 * <p>
 * ClassName: SerializeUtils
 * </p>
 * <p>
 * Description: 序列化工具类SerializeUtils
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月23日
 * </p>
 */
public class SerializeUtils {

    /**
     * <p>
     * Field log: 日志
     * </p>
     */
    private static final Logger LOG = LoggerFactory.getLogger(SerializeUtils.class);

    /**
     * <p>
     * Description: 构造函数
     * </p>
     */
    private SerializeUtils() {

    }

    /**
     * 反序列化
     * 
     * @param bytes 字节
     * @return 结果
     */
    public static Object deserialize(byte[] bytes) {
        if (isEmpty(bytes)) {
            return null;
        }
        try {
            ByteArrayInputStream byteStream = new ByteArrayInputStream(bytes);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteStream);
            return objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            LOG.error("Failed to deserialize", e);
        } catch (IOException e) {
            LOG.error("Failed to deserialize", e);
        }
        return null;
    }

    /**
     * <p>
     * Description: 判断空
     * </p>
     * 
     * @param data 字节
     * @return 结果
     */
    public static boolean isEmpty(byte[] data) {
        return ArrayUtils.isEmpty(data);
    }

    /**
     * 序列化
     * 
     * @param object 数据
     * @return 字节
     */
    public static byte[] serialize(Object object) {
        if (object == null) {
            return new byte[0];
        }
        try {
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream(Constants.NUMBER_128);
            if (!(object instanceof Serializable)) {
                throw new IllegalArgumentException(
                        SerializeUtils.class.getSimpleName() + " requires a Serializable payload "
                                + "but received an object of type [" + object.getClass().getName() + "]");
            }
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteStream);
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
            return byteStream.toByteArray();
        } catch (IOException ex) {
            LOG.error("Failed to serialize", ex);
        }
        return new byte[0];
    }
}
