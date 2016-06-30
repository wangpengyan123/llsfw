/**
 * RedisSessionDao.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.core.security.session.redis;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.llsfw.core.common.Constants;
import com.llsfw.core.exception.SystemRuntimeException;
import com.llsfw.core.security.session.SerializeUtils;

/**
 * <p>
 * ClassName: RedisSessionDAO
 * </p>
 * <p>
 * Description: redisSessionDao
 * </p>
 * <p>
 * Author: kkll
 * </p>
 * <p>
 * Date: 2014年7月7日
 * </p>
 */
public class RedisSessionDao extends AbstractSessionDAO {

    /**
     * <p>
     * Field log: 日志
     * </p>
     */
    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * <p>
     * Field keyPrefix: 键前缀
     * </p>
     */
    private String keyPrefix;

    /**
     * <p>
     * Field dbManager: 数据管理
     * </p>
     */
    private DbManager dbManager;

    public String getKeyPrefix() {
        return this.keyPrefix;
    }

    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }

    public DbManager getDbManager() {
        return this.dbManager;
    }

    public void setDbManager(DbManager dbManager) {
        this.dbManager = dbManager;
    }

    /**
     * <p>
     * Description: 拼接完整的键
     * </p>
     * 
     * @param sessionId 会话ID
     * @return 键的字节数组
     */
    public byte[] getByteKey(Serializable sessionId) {
        String preKey = this.keyPrefix + sessionId;
        try {
            return preKey.getBytes(Constants.DEF_CHARACTER_SET_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new SystemRuntimeException(e);
        }
    }

    /**
     * <p>
     * Description: 存储会话
     * </p>
     * 
     * @param session 会话
     * @throws UnknownSessionException 异常
     */
    private void saveSession(Session session) {
        if (session == null || session.getId() == null) {
            this.log.error("session or session id is null");
            return;
        }
        byte[] key = getByteKey(session.getId());
        byte[] value = SerializeUtils.serialize(session);
        this.dbManager.set(key, value, session.getTimeout() / Constants.NUMBER_1000);
    }

    @Override
    public void update(Session session) {
        this.log.debug("更新session");
        this.saveSession(session);
    }

    @Override
    public void delete(Session session) {
        this.log.debug("删除session");
        if (session == null || session.getId() == null) {
            this.log.error("session or session id is null");
            return;
        }
        this.dbManager.del(getByteKey(session.getId()));
    }

    @Override
    public Collection<Session> getActiveSessions() {
        this.log.debug("获取正在活动中的session列表");
        Set<Session> sessions = new HashSet<Session>();
        Set<byte[]> keys = this.dbManager.keys(this.keyPrefix + "*");
        if (!CollectionUtils.isEmpty(keys)) {
            for (byte[] key : keys) {
                Session s = (Session) SerializeUtils.deserialize(this.dbManager.get(key));
                sessions.add(s);
            }
        }
        return sessions;
    }

    @Override
    protected Serializable doCreate(Session session) {
        this.log.debug("创建session");
        Serializable sessionId = this.generateSessionId(session);
        this.assignSessionId(session, sessionId);
        this.saveSession(session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        this.log.debug("读取session");
        if (sessionId == null) {
            this.log.error("session id is null");
            return null;
        }
        return (Session) SerializeUtils.deserialize(this.dbManager.get(getByteKey(sessionId)));
    }
}
