/**
 * WxTest.java
 * Created at 2016-04-23
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.wx.api;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.llsfw.core.common.Constants;
import com.llsfw.wx.api.impl.base.CallBackIp;
import com.llsfw.wx.api.impl.user.group.Group;
import com.llsfw.wx.common.OAuthToken;
import com.llsfw.wx.exception.WxException;

/**
 * <p>
 * ClassName: WxTest
 * </p>
 * <p>
 * Description: 测试
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年4月23日
 * </p>
 */
public class WxTest {

    /**
     * <p>
     * Field wx: 微信对象
     * </p>
     */
    private Wx wx;

    /**
     * <p>
     * Description: 前置方法
     * </p>
     * 
     * @throws WxException 异常
     * 
     */
    @Before
    public void setUp() throws WxException {
        //实例化微信对象
        String appId = "wx6628ff216888b83e";
        String appSecret = "81824aeef3a1037431f7c470a82b1e75";
        String grantType = "client_credential";
        String accessToken = null;
        int expiresIn = 0;
        long createTime = 0;
        this.wx = new Wx();
        this.wx.init(appId, appSecret, grantType, accessToken, expiresIn, createTime);

        //能否正常拿到token
        OAuthToken oauthToken = this.wx.getOauthToken();
        org.junit.Assert.assertNotNull(oauthToken);

        //使用上次拿到的toklen,再次实例化微信对象
        accessToken = oauthToken.getAccess_token();
        expiresIn = oauthToken.getExpires_in();
        createTime = oauthToken.getCreateTime();
        this.wx.init(appId, appSecret, grantType, accessToken, expiresIn, createTime);
        org.junit.Assert.assertEquals(this.wx.getOauthToken().getAccess_token(), accessToken);

    }

    /**
     * <p>
     * Description: testGetOauthToken
     * </p>
     * 
     * @throws WxException 异常
     */
    @Test
    public void testGetOauthToken() throws WxException {
        org.junit.Assert.assertNotNull(this.wx.getOauthToken());
    }

    /**
     * <p>
     * Description: testGetCallBackIp
     * </p>
     * 
     * @throws WxException 异常
     */
    @Test
    public void testGetCallBackIp() throws WxException {
        CallBackIp cbi = this.wx.getCallBackIp();
        org.junit.Assert.assertNotNull(cbi);
    }

    /**
     * <p>
     * Description: testGroupCreate
     * </p>
     * 
     * @throws WxException 异常
     */
    @Test
    public void testGroupCreate() throws WxException {
        String name = "testGroup";
        Group group = this.wx.groupCreate(name);
        org.junit.Assert.assertNotNull(group);
        org.junit.Assert.assertNotNull(group.getId());
        org.junit.Assert.assertEquals(name, group.getName());
    }

    /**
     * <p>
     * Description: testGroupsGet
     * </p>
     * 
     * @throws WxException 异常
     */
    @Test
    public void testGroupsGet() throws WxException {
        List<Group> groups = this.wx.groupsGet();
        org.junit.Assert.assertNotNull(groups);
    }

    /**
     * <p>
     * Description: testGroupsGetId
     * </p>
     * 
     * @throws WxException 异常
     */
    @Test
    public void testGroupsGetId() throws WxException {
        String openid = "okQBOwF-j4oXY1sroJ3Pdim1eUW8";
        Integer groupId = this.wx.groupsGetId(openid);
        org.junit.Assert.assertNotNull(groupId);
    }

    /**
     * <p>
     * Description: testGroupsGetId
     * </p>
     * 
     * @throws WxException 异常
     */
    @Test
    public void testGroupsUpdate() throws WxException {
        List<Group> groups = this.wx.groupsGet();
        for (Group g : groups) {
            if (g.getId() >= Constants.NUMBER_100) {
                this.wx.groupsUpdate(g.getId(), "我的分组");
            }
        }
    }

    /**
     * <p>
     * Description: testGroupsMembersUpdate
     * </p>
     * 
     * @throws WxException 异常
     */
    @Test
    public void testGroupsMembersUpdate() throws WxException {

    }

    /**
     * <p>
     * Description: testGroupsMembersBatchupdate
     * </p>
     * 
     * @throws WxException 异常
     */
    @Test
    public void testGroupsMembersBatchupdate() throws WxException {

    }

    /**
     * <p>
     * Description: testGroupsDelete
     * </p>
     * 
     * @throws WxException 异常
     */
    @Test
    public void testGroupsDelete() throws WxException {

    }

    /**
     * <p>
     * Description: testUserInfoUpdateremark
     * </p>
     * 
     * @throws WxException 异常
     */
    @Test
    public void testUserInfoUpdateremark() throws WxException {

    }

    /**
     * <p>
     * Description: testUserInfo
     * </p>
     * 
     * @throws WxException 异常
     */
    @Test
    public void testUserInfo() throws WxException {

    }

    /**
     * <p>
     * Description: testUserInfoBatchGet
     * </p>
     * 
     * @throws WxException 异常
     */
    @Test
    public void testUserInfoBatchGet() throws WxException {

    }

    /**
     * <p>
     * Description: testUserGet
     * </p>
     * 
     * @throws WxException 异常
     */
    @Test
    public void testUserGet() throws WxException {

    }

}
