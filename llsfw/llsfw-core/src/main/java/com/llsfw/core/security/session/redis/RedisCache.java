/**
 * RedisCache.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.core.security.session.redis;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.llsfw.core.common.Constants;
import com.llsfw.core.exception.SystemRuntimeException;
import com.llsfw.core.security.session.SerializeUtils;

/**
 * <p>
 * ClassName: RedisCache
 * </p>
 * <p>
 * Description: redis缓存
 * </p>
 * <p>
 * Author: kkll
 * </p>
 * <p>
 * Date: 2014年7月7日
 * </p>
 */
public class RedisCache<K, V> implements Cache<K, V> {

    /**
     * <p>
     * Field log: 日志
     * </p>
     */
    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * <p>
     * Field cache: 数据管理
     * </p>
     */
    private DbManager cache;

    /**
     * <p>
     * Field keyPrefix: 键前缀
     * </p>
     */
    private String keyPrefix;

    /**
     * <p>
     * Description: 构造函数
     * </p>
     * 
     * @param cache 数据管理
     * @param prefix 键前缀
     */
    public RedisCache(DbManager cache, String prefix) {
        this(cache);
        this.keyPrefix = prefix;
    }

    /**
     * <p>
     * Description: 构造函数
     * </p>
     * 
     * @param cache 数据管理
     */
    public RedisCache(DbManager cache) {
        if (cache == null) {
            throw new IllegalArgumentException("Cache argument cannot be null.");
        }
        this.cache = cache;
    }

    /**
     * <p>
     * Description: 拼接完整的键
     * </p>
     * 
     * @param key 键
     * @return 键的字节数组
     */
    private byte[] getByteKey(K key) {
        if (key instanceof String) {
            String preKey = this.keyPrefix + key;
            try {
                return preKey.getBytes(Constants.DEF_CHARACTER_SET_ENCODING);
            } catch (UnsupportedEncodingException e) {
                throw new SystemRuntimeException(e);
            }
        } else {
            return SerializeUtils.serialize(key);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public V get(K key) {
        this.log.debug("[cache]根据key从Redis中获取对象 key [" + key + "]");
        if (key == null) {
            return null;
        } else {
            byte[] rawValue = this.cache.get(getByteKey(key));
            return (V) SerializeUtils.deserialize(rawValue);
        }

    }

    @Override
    public V put(K key, V value) {
        this.log.debug("[cache]根据key从存储 key [" + key + "]");
        this.cache.set(getByteKey(key), SerializeUtils.serialize(value));
        return value;
    }

    @Override
    public V remove(K key) {
        this.log.debug("[cache]从redis中删除 key [" + key + "]");
        V previous = get(key);
        this.cache.del(getByteKey(key));
        return previous;
    }

    @Override
    public void clear() {
        this.log.debug("[cache]从redis中删除所有元素");
        this.cache.flushDb();
    }

    @Override
    public int size() {
        Long longSize = Long.valueOf(this.cache.dbSize());
        return longSize.intValue();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Set<K> keys() {
        this.log.debug("[cache]从redis中获取所有的key");
        Set<byte[]> keys = this.cache.keys(this.keyPrefix + "*");
        if (CollectionUtils.isEmpty(keys)) {
            return Collections.emptySet();
        } else {
            Set<K> newKeys = new HashSet<K>();
            for (byte[] key : keys) {
                newKeys.add((K) key);
            }
            return newKeys;
        }
    }

    @Override
    public Collection<V> values() {
        this.log.debug("[cache]从redis中获取所有的values");
        Set<byte[]> keys = this.cache.keys(this.keyPrefix + "*");
        if (!CollectionUtils.isEmpty(keys)) {
            List<V> values = new ArrayList<V>(keys.size());
            for (byte[] key : keys) {
                @SuppressWarnings("unchecked")
                V value = get((K) key);
                if (value != null) {
                    values.add(value);
                }
            }
            return Collections.unmodifiableList(values);
        } else {
            return Collections.emptyList();
        }
    }
}
