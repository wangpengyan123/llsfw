/**
 * Wx.java
 * Created at 2016-04-23
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.wx.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.llsfw.core.common.Constants;
import com.llsfw.wx.api.impl.base.BaseResult;
import com.llsfw.wx.api.impl.base.CallBackIp;
import com.llsfw.wx.api.impl.base.Token;
import com.llsfw.wx.api.impl.base.WxBase;
import com.llsfw.wx.api.impl.message.customservice.KfList;
import com.llsfw.wx.api.impl.message.customservice.KfOption;
import com.llsfw.wx.api.impl.message.media.BaseMedia;
import com.llsfw.wx.api.impl.message.media.MediaResut;
import com.llsfw.wx.api.impl.message.output.SendMessage;
import com.llsfw.wx.api.impl.message.output.active.Articles;
import com.llsfw.wx.api.impl.message.output.active.CustomserviceSend;
import com.llsfw.wx.api.impl.message.output.active.Image;
import com.llsfw.wx.api.impl.message.output.active.Music;
import com.llsfw.wx.api.impl.message.output.active.Text;
import com.llsfw.wx.api.impl.message.output.active.Video;
import com.llsfw.wx.api.impl.message.output.active.Voice;
import com.llsfw.wx.api.impl.message.output.active.Wxcard;
import com.llsfw.wx.api.impl.message.output.active.mass.Filter;
import com.llsfw.wx.api.impl.message.output.active.mass.MassResult;
import com.llsfw.wx.api.impl.message.output.active.mass.MediaOption;
import com.llsfw.wx.api.impl.message.output.active.mass.SendMassMessage;
import com.llsfw.wx.api.impl.user.User;
import com.llsfw.wx.api.impl.user.UserBatch;
import com.llsfw.wx.api.impl.user.UserList;
import com.llsfw.wx.api.impl.user.UserOption;
import com.llsfw.wx.api.impl.user.group.Group;
import com.llsfw.wx.api.impl.user.group.GroupOption;
import com.llsfw.wx.common.OAuth;
import com.llsfw.wx.common.OAuthToken;
import com.llsfw.wx.exception.WxException;

/**
 * <p>
 * ClassName: Wx
 * </p>
 * <p>
 * Description: 微信平台API
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年4月23日
 * </p>
 */
public class Wx extends WxBase {

    /**
     * <p>
     * Field serialVersionUID: ID
     * </p>
     */
    private static final long serialVersionUID = -4357615861458271219L;

    /**
     * <p>
     * Field PARAM_ERROR: 参数错误提醒
     * </p>
     */
    private static final String PARAM_ERROR = "param_error";

    /**
     * <p>
     * Field WXCARD: 卡券消息类型
     * </p>
     */
    private static final String WXCARD = "wxcard";

    /**
     * <p>
     * Field oauth: 公众号对象
     * </p>
     */
    private OAuth oauth = null;

    /**
     * <p>
     * Field oauthToken: 公众号Token对象
     * </p>
     */
    private OAuthToken oauthToken = null;

    /**
     * <p>
     * Description: 构造函数
     * </p>
     */
    public Wx() {

    }

    /**
     * <p>
     * Description: 微信基础支持
     * </p>
     * 
     * @param appId 开发者Id
     * @param appSecret 开发者密钥
     * @param grantType 获取access_token填写client_credential
     * @param accessToken 凭证
     * @param expiresIn 凭证有效时间，单位：秒
     * @param createTime 凭证创建时间
     * @throws WxException 异常
     */
    public void init(String appId, String appSecret, String grantType, String accessToken, int expiresIn,
            long createTime) throws WxException {
        //校验开发者信息
        if (StringUtils.isEmpty(appId) || StringUtils.isEmpty(appSecret) || StringUtils.isEmpty(grantType)) {
            throw new WxException("invalid null, appid or appSecret or grantType is null.");
        }
        //初始化开发者信息
        this.oauth = new OAuth(appId, appSecret, grantType);

        //初始化凭证信息
        if (!StringUtils.isEmpty(accessToken)) {
            this.oauthToken = new OAuthToken(accessToken, expiresIn, createTime);
        }

        //验证用户登录
        this.checkLogin();
    }

    /**
     * <p>
     * Description: 获取登录后的OauthToken对象
     * </p>
     * 
     * @return 如果已登录过返回OAuthToken对象，否则返回null
     */
    public OAuthToken getOauthToken() {
        return this.oauthToken;
    }

    /**
     * <p>
     * Description: 获取微信服务器IP地址
     * </p>
     * 
     * @return ip地址列表
     * @throws WxException 异常
     */
    public CallBackIp getCallBackIp() throws WxException {
        //检查
        checkLogin();
        //请求
        String result = this.get(this.getUrl("1", null));
        //处理
        CallBackIp callBackIp = Wx.jsonToObject(result, CallBackIp.class);
        this.checkResult(callBackIp);
        return callBackIp;
    }

    /**
     * <p>
     * Description: 创建分组
     * </p>
     * 
     * @param name 分组名字（30个字符以内）
     * @return 创建成功，返回带Id的Group对象
     * @throws WxException 异常
     */
    public Group groupCreate(String name) throws WxException {
        //检查
        checkLogin();
        if (name == null || ("").equals(name)) {
            throw new IllegalStateException(PARAM_ERROR);
        }
        //准备参数
        Group group = new Group();
        group.setName(name);
        GroupOption u = new GroupOption();
        u.setGroup(group);
        String json = objectToJson(u, false);
        //请求
        String result = this.post(this.getUrl("2", null), json);
        //处理
        GroupOption go = Wx.jsonToObject(result, GroupOption.class);
        this.checkResult(go);
        return go.getGroup();
    }

    /**
     * <p>
     * Description: 查询所有分组
     * </p>
     * 
     * @return 分组列表
     * @throws WxException 异常
     */
    public List<Group> groupsGet() throws WxException {
        //检查
        checkLogin();
        //请求
        String result = this.get(this.getUrl("3", null));
        //处理
        GroupOption go = Wx.jsonToObject(result, GroupOption.class);
        this.checkResult(go);
        return go.getGroups();
    }

    /**
     * <p>
     * Description: 查询用户所在分组
     * </p>
     * 
     * @param openId 用户唯一标识符
     * @return 返回用户所在分组Id
     * @throws WxException 异常
     */
    public Integer groupsGetId(String openId) throws WxException {
        //检查
        checkLogin();
        if (StringUtils.isEmpty(openId)) {
            throw new IllegalStateException(PARAM_ERROR);
        }
        //准备参数
        GroupOption u = new GroupOption();
        u.setOpenid(openId);
        String json = objectToJson(u, false);
        //请求
        String result = this.post(this.getUrl("4", null), json);
        //处理
        GroupOption go = Wx.jsonToObject(result, GroupOption.class);
        this.checkResult(go);
        return go.getGroupid();
    }

    /**
     * <p>
     * Description: 修改分组名
     * </p>
     * 
     * @param id 分组id，由微信分配
     * @param name 分组名字（30个字符以内）
     * @throws WxException 异常
     */
    public void groupsUpdate(Integer id, String name) throws WxException {
        //检查
        checkLogin();
        if (StringUtils.isEmpty(name) || id == null) {
            throw new IllegalStateException(PARAM_ERROR);
        }
        if (id < Constants.NUMBER_100) { //100以下为系统组,不能修改
            throw new IllegalStateException(PARAM_ERROR);
        }
        //准备参数
        Group group = new Group();
        group.setId(id);
        group.setName(name);
        GroupOption u = new GroupOption();
        u.setGroup(group);
        String json = objectToJson(u, false);
        //请求
        String result = this.post(this.getUrl("5", null), json);
        //处理
        GroupOption go = Wx.jsonToObject(result, GroupOption.class);
        this.checkResult(go);
    }

    /**
     * <p>
     * Description: 移动用户分组
     * </p>
     * 
     * @param openId 用户唯一标识符
     * @param toGroupid 分组id
     * @throws WxException 异常
     */
    public void groupsMembersUpdate(String openId, Integer toGroupid) throws WxException {
        //检查
        checkLogin();
        if (StringUtils.isEmpty(openId) || toGroupid == null) {
            throw new IllegalStateException(PARAM_ERROR);
        }
        //准备参数
        GroupOption go = new GroupOption();
        go.setOpenid(openId);
        go.setTo_groupid(toGroupid);
        String json = objectToJson(go, false);
        //请求
        String result = this.post(this.getUrl("6", null), json);
        //处理
        go = Wx.jsonToObject(result, GroupOption.class);
        this.checkResult(go);
    }

    /**
     * <p>
     * Description: 批量移动用户分组
     * </p>
     * 
     * @param openidList 用户唯一标识符openid的列表（size不能超过50）
     * @param to_groupid 分组id
     * @throws WxException 异常
     */
    public void groupsMembersBatchupdate(List<String> openidList, Integer to_groupid) throws WxException {
        //检查
        checkLogin();
        if (to_groupid == null || openidList == null || openidList.size() > Constants.NUMBER_50) {
            throw new IllegalStateException(PARAM_ERROR);
        }
        //准备参数
        GroupOption go = new GroupOption();
        go.setOpenid_list(openidList);
        go.setTo_groupid(to_groupid);
        String json = objectToJson(go, false);
        //请求
        String result = this.post(this.getUrl("7", null), json);
        //处理
        go = Wx.jsonToObject(result, GroupOption.class);
        this.checkResult(go);
    }

    /**
     * <p>
     * Description: 删除分组
     * </p>
     * 
     * @param id 分组Id
     * @throws WxException 异常
     */
    public void groupsDelete(Integer id) throws WxException {
        //检查
        checkLogin();
        if (id == null) {
            throw new IllegalStateException(PARAM_ERROR);
        }
        //准备参数
        Group group = new Group();
        group.setId(id);
        GroupOption u = new GroupOption();
        u.setGroup(group);
        String json = objectToJson(u, false);
        //请求
        String result = this.post(this.getUrl("8", null), json);
        //处理
        GroupOption go = Wx.jsonToObject(result, GroupOption.class);
        this.checkResult(go);
    }

    /**
     * <p>
     * Description: 设置用户备注名
     * </p>
     * 
     * @param openid 用户标识
     * @param remark 新的备注名，长度必须小于30字符
     * @throws WxException 异常
     */
    public void userInfoUpdateremark(String openid, String remark) throws WxException {
        //检查
        checkLogin();
        if (StringUtils.isEmpty(openid) || StringUtils.isEmpty(remark)) {
            throw new IllegalStateException(PARAM_ERROR);
        }
        //准备参数
        User u = new User();
        u.setOpenid(openid);
        u.setRemark(remark);
        String json = objectToJson(u, false);
        //请求
        String result = this.post(this.getUrl("9", null), json);
        //处理
        UserOption uo = Wx.jsonToObject(result, UserOption.class);
        this.checkResult(uo);
    }

    /**
     * <p>
     * Description: 获取用户基本信息（包括UnionID机制）
     * </p>
     * 
     * @param openid 普通用户的标识，对当前公众号唯一
     * @param lang 返回国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语
     * @return 用户基本信息
     * @throws WxException 异常
     */
    public User userInfo(String openid, String lang) throws WxException {
        //检查
        checkLogin();
        if (StringUtils.isEmpty(openid) || StringUtils.isEmpty(lang)) {
            throw new IllegalStateException(PARAM_ERROR);
        }
        //准备参数
        String param = "&openid=" + openid + "&lang=" + lang;
        //请求
        String result = this.get(this.getUrl("10", param));
        //处理
        User u = Wx.jsonToObject(result, User.class);
        this.checkResult(u);
        return u;
    }

    /**
     * <p>
     * Description: 批量获取用户基本信息
     * </p>
     * 
     * @param user_list 希望获取的用户列表(需填充openid:用户的标识，对当前公众号唯一 , lang:国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语，默认为zh-CN)
     * @return 用户基本信息
     * @throws WxException 异常
     */
    public List<User> userInfoBatchGet(List<UserBatch> user_list) throws WxException {
        //检查
        checkLogin();
        if (CollectionUtils.isEmpty(user_list)) {
            throw new IllegalStateException(PARAM_ERROR);
        }
        //准备参数
        String json = objectToJson(user_list, false);
        //请求
        String result = this.post(this.getUrl("11", null), json);
        //处理
        UserOption uo = Wx.jsonToObject(result, UserOption.class);
        this.checkResult(uo);
        return uo.getUser_info_list();
    }

    /**
     * <p>
     * Description: 获取用户列表
     * </p>
     * 
     * @param next_openid 第一个拉取的OPENID，不填默认从头开始拉取
     * @return 用户列表(关注者列表已返回完时，返回next_openid为空)
     * @throws WxException 异常
     */
    public UserList userGet(String next_openid) throws WxException {
        //检查
        checkLogin();
        //准备参数
        String param = "";
        if (!StringUtils.isEmpty(next_openid)) {
            param = "&next_openid=" + next_openid;
        }
        //请求
        String result = this.get(this.getUrl("31", param));
        //处理
        UserList u = Wx.jsonToObject(result, UserList.class);
        this.checkResult(u);
        return u;
    }

    /**
     * <p>
     * Description: 添加客服帐号
     * </p>
     * 
     * @param kf_account 客服账号
     * @param nickname 客服昵称
     * @param password 密码
     * @throws WxException 异常
     */
    public void customserviceKfaccountAdd(String kf_account, String nickname, String password) throws WxException {
        //检查
        checkLogin();
        if (StringUtils.isEmpty(kf_account) || StringUtils.isEmpty(nickname) || StringUtils.isEmpty(password)) {
            throw new IllegalStateException(PARAM_ERROR);
        }
        //准备参数
        KfOption ko = new KfOption();
        ko.setKf_account(kf_account);
        ko.setNickname(nickname);
        ko.setPassword(password);
        String json = objectToJson(ko, false);
        //请求
        String result = this.post(this.getUrl("17", null), json);
        //处理
        ko = Wx.jsonToObject(result, KfOption.class);
        this.checkResult(ko);
    }

    /**
     * <p>
     * Description: 修改客服帐号
     * </p>
     * 
     * @param kf_account 客服账号
     * @param nickname 客服昵称
     * @param password 密码
     * @throws WxException 异常
     */
    public void customserviceKfaccountUpdate(String kf_account, String nickname, String password) throws WxException {
        //检查
        checkLogin();
        if (StringUtils.isEmpty(kf_account) || StringUtils.isEmpty(nickname) || StringUtils.isEmpty(password)) {
            throw new IllegalStateException(PARAM_ERROR);
        }
        //准备参数
        KfOption ko = new KfOption();
        ko.setKf_account(kf_account);
        ko.setNickname(nickname);
        ko.setPassword(password);
        String json = objectToJson(ko, false);
        //请求
        String result = this.post(this.getUrl("18", null), json);
        //处理
        ko = Wx.jsonToObject(result, KfOption.class);
        this.checkResult(ko);
    }

    /**
     * <p>
     * Description: 删除客服帐号
     * </p>
     * 
     * @param kf_account 客服账号
     * @param nickname 客服昵称
     * @param password 密码
     * @throws WxException 异常
     */
    public void customserviceKfaccountDel(String kf_account, String nickname, String password) throws WxException {
        //检查
        checkLogin();
        if (StringUtils.isEmpty(kf_account) || StringUtils.isEmpty(nickname) || StringUtils.isEmpty(password)) {
            throw new IllegalStateException(PARAM_ERROR);
        }
        //准备参数
        KfOption ko = new KfOption();
        ko.setKf_account(kf_account);
        ko.setNickname(nickname);
        ko.setPassword(password);
        String json = objectToJson(ko, false);
        //请求
        String result = this.post(this.getUrl("19", null), json);
        //处理
        ko = Wx.jsonToObject(result, KfOption.class);
        this.checkResult(ko);
    }

    /**
     * <p>
     * Description: 设置客服帐号的头像
     * </p>
     * 
     * @param kf_account 客服账号
     * @param filePath 头像文件路径
     * @throws WxException 异常
     */
    public void customserviceKfaccountUploadheadimg(String kf_account, String filePath) throws WxException {
        //检查
        checkLogin();
        if (StringUtils.isEmpty(kf_account)) {
            throw new IllegalStateException(PARAM_ERROR);
        }
        //准备参数
        String param = "&kf_account=" + kf_account;
        //请求
        String result = this.upload(this.getUrl("20", param), filePath, false);
        //处理
        BaseResult br = Wx.jsonToObject(result, BaseResult.class);
        this.checkResult(br);
    }

    /**
     * <p>
     * Description: 获取所有客服账号
     * </p>
     * 
     * @return 所有客服账号
     * @throws WxException 异常
     */
    public List<KfList> customserviceGetkflist() throws WxException {
        //检查
        checkLogin();
        //请求
        String result = this.get(this.getUrl("21", null));
        //处理
        KfOption u = Wx.jsonToObject(result, KfOption.class);
        this.checkResult(u);
        return u.getKf_list();
    }

    /**
     * <p>
     * Description: 客服接口-发消息(卡券)
     * </p>
     * 
     * @param openId 客户ID
     * @param msg 消息
     * @param cust 客服(可以填null)
     * @throws WxException 异常
     */
    public void messageCustomSend(String openId, Wxcard msg, CustomserviceSend cust) throws WxException {
        //检查
        if (msg == null) {
            throw new IllegalStateException(PARAM_ERROR);
        }
        //准备参数
        SendMessage sendMessage = new SendMessage();
        sendMessage.setWxcard(msg);
        //请求-处理
        this.messageCustomSend(openId, WXCARD, sendMessage, cust);
    }

    /**
     * <p>
     * Description: 客服接口-发消息(图文)
     * </p>
     * 
     * @param openId 客户ID
     * @param msg 消息
     * @param cust 客服(可以填null)
     * @throws WxException 异常
     */
    public void messageCustomSend(String openId, List<Articles> msg, CustomserviceSend cust) throws WxException {
        //检查
        if (msg == null || msg.size() > Constants.NUMBER_10) {
            throw new IllegalStateException(PARAM_ERROR);
        }
        //准备参数
        SendMessage sendMessage = new SendMessage();
        sendMessage.setNews(msg);
        //请求-处理
        this.messageCustomSend(openId, "news", sendMessage, cust);
    }

    /**
     * <p>
     * Description: 客服接口-发消息(音乐)
     * </p>
     * 
     * @param openId 客户ID
     * @param msg 消息
     * @param cust 客服(可以填null)
     * @throws WxException 异常
     */
    public void messageCustomSend(String openId, Music msg, CustomserviceSend cust) throws WxException {
        //检查
        if (msg == null) {
            throw new IllegalStateException(PARAM_ERROR);
        }
        //准备参数
        SendMessage sendMessage = new SendMessage();
        sendMessage.setMusic(msg);
        //请求-处理
        this.messageCustomSend(openId, "music", sendMessage, cust);
    }

    /**
     * <p>
     * Description: 客服接口-发消息(视频)
     * </p>
     * 
     * @param openId 客户ID
     * @param msg 消息
     * @param cust 客服(可以填null)
     * @throws WxException 异常
     */
    public void messageCustomSend(String openId, Video msg, CustomserviceSend cust) throws WxException {
        //检查
        if (msg == null) {
            throw new IllegalStateException(PARAM_ERROR);
        }
        //准备参数
        SendMessage sendMessage = new SendMessage();
        sendMessage.setVideo(msg);
        //请求-处理
        this.messageCustomSend(openId, "video", sendMessage, cust);
    }

    /**
     * <p>
     * Description: 客服接口-发消息(声音)
     * </p>
     * 
     * @param openId 客户ID
     * @param msg 消息
     * @param cust 客服(可以填null)
     * @throws WxException 异常
     */
    public void messageCustomSend(String openId, Voice msg, CustomserviceSend cust) throws WxException {
        //检查
        if (msg == null) {
            throw new IllegalStateException(PARAM_ERROR);
        }
        //准备参数
        SendMessage sendMessage = new SendMessage();
        sendMessage.setVoice(msg);
        //请求-处理
        this.messageCustomSend(openId, "voice", sendMessage, cust);
    }

    /**
     * <p>
     * Description: 客服接口-发消息(图片)
     * </p>
     * 
     * @param openId 客户ID
     * @param msg 消息
     * @param cust 客服(可以填null)
     * @throws WxException 异常
     */
    public void messageCustomSend(String openId, Image msg, CustomserviceSend cust) throws WxException {
        //检查
        if (msg == null) {
            throw new IllegalStateException(PARAM_ERROR);
        }
        //准备参数
        SendMessage sendMessage = new SendMessage();
        sendMessage.setImage(msg);
        //请求-处理
        this.messageCustomSend(openId, "image", sendMessage, cust);
    }

    /**
     * <p>
     * Description: 客服接口-发消息(文字)
     * </p>
     * 
     * @param openId 客户ID
     * @param msg 消息
     * @param cust 客服(可以填null)
     * @throws WxException 异常
     */
    public void messageCustomSend(String openId, Text msg, CustomserviceSend cust) throws WxException {
        //检查
        if (msg == null) {
            throw new IllegalStateException(PARAM_ERROR);
        }
        //准备参数
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(msg);
        //请求-处理
        this.messageCustomSend(openId, "text", sendMessage, cust);
    }

    /**
     * <p>
     * Description: 客服接口-发消息
     * </p>
     * 
     * @param openId 客户ID
     * @param msgType 消息类型
     * @param sendMessage 消息
     * @param cust 客服
     * @throws WxException 异常
     */
    private void messageCustomSend(String openId, String msgType, SendMessage sendMessage, CustomserviceSend cust)
            throws WxException {
        //检查
        checkLogin();
        if (sendMessage == null || StringUtils.isEmpty(openId) || StringUtils.isEmpty(msgType)) {
            throw new IllegalStateException(PARAM_ERROR);
        }
        //准备参数
        sendMessage.setMsgtype(msgType);
        sendMessage.setTouser(openId);
        if (cust != null && !StringUtils.isEmpty(cust.getKf_account())) {
            sendMessage.setCustomservice(cust);
        }
        String json = objectToJson(sendMessage, false);
        //请求
        String result = this.post(this.getUrl("22", null), json);
        //处理
        BaseResult br = Wx.jsonToObject(result, BaseResult.class);
        this.checkResult(br);
    }

    /**
     * <p>
     * Description: 上传图文消息内的图片获取URL
     * </p>
     * <p>
     * 请注意，本接口所上传的图片不占用公众号的素材库中图片数量的5000个的限制。图片仅支持jpg/png格式，大小必须在1MB以下。
     * </p>
     * 
     * @param filePath 文件路径
     * @return 图片url
     * @throws WxException 异常
     */
    public String mediaUploadimg(String filePath) throws WxException {
        //检查
        checkLogin();
        if (StringUtils.isEmpty(filePath)) {
            throw new IllegalStateException(PARAM_ERROR);
        }
        //请求
        String result = this.upload(this.getUrl("23", null), filePath, false);
        //处理
        MediaOption br = Wx.jsonToObject(result, MediaOption.class);
        this.checkResult(br);
        return br.getUrl();
    }

    /**
     * <p>
     * Description: 上传图文消息素材
     * </p>
     * 
     * @param articles 图文消息
     * @return 素材ID
     * @throws WxException 异常
     */
    public MediaResut mediaUploadnews(List<com.llsfw.wx.api.impl.message.output.active.mass.Articles> articles)
            throws WxException {
        //检查
        checkLogin();
        if (CollectionUtils.isEmpty(articles) || articles.size() > Constants.NUMBER_10) {
            throw new IllegalStateException(PARAM_ERROR);
        }
        //准备参数
        MediaOption mo = new MediaOption();
        mo.setArticles(articles);
        String json = objectToJson(mo, false);
        //请求
        String result = this.post(this.getUrl("24", null), json);
        //处理
        MediaResut ar = Wx.jsonToObject(result, MediaResut.class);
        this.checkResult(ar);
        return ar;
    }

    /**
     * <p>
     * Description: 视频上传
     * </p>
     * 
     * @param filePath 文件路径
     * @return 素材结果
     * @throws WxException 异常
     */
    public MediaResut mediaUploadvideo(String filePath) throws WxException {
        //检查
        checkLogin();
        if (StringUtils.isEmpty(filePath)) {
            throw new IllegalStateException(PARAM_ERROR);
        }
        //请求
        String result = this.upload(this.getUrl("25", null), filePath, false);
        //处理
        MediaResut mr = Wx.jsonToObject(result, MediaResut.class);
        this.checkResult(mr);
        return mr;
    }

    /**
     * <p>
     * Description: 根据分组进行群发(卡券)
     * </p>
     * 
     * @param filter 过滤器
     * @param card 卡券ID
     * @return 发送结果
     * @throws WxException 异常
     */
    public MassResult messageMassSendallWxcard(Filter filter, Wxcard card) throws WxException {
        //检查
        if (card == null) {
            throw new IllegalStateException(PARAM_ERROR);
        }
        //准备参数
        SendMassMessage massMsg = new SendMassMessage();
        massMsg.setWxcard(card);
        //请求-处理
        return this.messageMassSendall(filter, WXCARD, massMsg);
    }

    /**
     * <p>
     * Description: 根据分组进行群发(卡券)
     * </p>
     * 
     * @param filter 过滤器
     * @param card_id 卡券ID
     * @return 发送结果
     * @throws WxException 异常
     */
    public MassResult messageMassSendallWxcard(Filter filter, String card_id) throws WxException {
        //检查
        if (StringUtils.isEmpty(card_id)) {
            throw new IllegalStateException(PARAM_ERROR);
        }
        //准备参数
        Wxcard c = new Wxcard();
        c.setCard_id(card_id);
        SendMassMessage massMsg = new SendMassMessage();
        massMsg.setWxcard(c);
        //请求-处理
        return this.messageMassSendall(filter, WXCARD, massMsg);
    }

    /**
     * <p>
     * Description: 根据分组进行群发(视频)
     * </p>
     * 
     * @param filter 过滤器
     * @param media_id 素材ID
     * @param title 标题
     * @param description 描述
     * @return 发送结果
     * @throws WxException 异常
     */
    public MassResult messageMassSendallVideo(Filter filter, String media_id, String title, String description)
            throws WxException {
        //检查
        if (StringUtils.isEmpty(media_id)) {
            throw new IllegalStateException(PARAM_ERROR);
        }
        //准备参数
        com.llsfw.wx.api.impl.message.output.active.mass.Video v = null;
        v = new com.llsfw.wx.api.impl.message.output.active.mass.Video();
        v.setDescription(description);
        v.setMedia_id(media_id);
        v.setTitle(title);
        SendMassMessage massMsg = new SendMassMessage();
        massMsg.setVideo(v);
        //请求-处理
        return this.messageMassSendall(filter, "video", massMsg);
    }

    /**
     * <p>
     * Description: 根据分组进行群发(视频)
     * </p>
     * 
     * @param filter 过滤器
     * @param media_id 素材Id
     * @return 发送结果
     * @throws WxException 异常
     */
    public MassResult messageMassSendallMpvideo(Filter filter, String media_id) throws WxException {
        //检查
        if (StringUtils.isEmpty(media_id)) {
            throw new IllegalStateException(PARAM_ERROR);
        }
        //准备参数
        BaseMedia bm = new BaseMedia();
        bm.setMedia_id(media_id);
        SendMassMessage massMsg = new SendMassMessage();
        massMsg.setMpvideo(bm);
        //请求-处理
        return this.messageMassSendall(filter, "mpvideo", massMsg);
    }

    /**
     * <p>
     * Description: 根据分组进行群发(图片)
     * </p>
     * 
     * @param filter 过滤器
     * @param media_id 素材Id
     * @return 发送结果
     * @throws WxException 异常
     */
    public MassResult messageMassSendallImage(Filter filter, String media_id) throws WxException {
        //检查
        if (StringUtils.isEmpty(media_id)) {
            throw new IllegalStateException(PARAM_ERROR);
        }
        //准备参数
        BaseMedia bm = new BaseMedia();
        bm.setMedia_id(media_id);
        SendMassMessage massMsg = new SendMassMessage();
        massMsg.setImage(bm);
        //请求-处理
        return this.messageMassSendall(filter, "image", massMsg);
    }

    /**
     * <p>
     * Description: 根据分组进行群发(语音)
     * </p>
     * 
     * @param filter 过滤器
     * @param media_id 素材Id
     * @return 发送结果
     * @throws WxException 异常
     */
    public MassResult messageMassSendallVoice(Filter filter, String media_id) throws WxException {
        //检查
        if (StringUtils.isEmpty(media_id)) {
            throw new IllegalStateException(PARAM_ERROR);
        }
        //准备参数
        BaseMedia bm = new BaseMedia();
        bm.setMedia_id(media_id);
        SendMassMessage massMsg = new SendMassMessage();
        massMsg.setVoice(bm);
        //请求-处理
        return this.messageMassSendall(filter, "voice", massMsg);
    }

    /**
     * <p>
     * Description: 根据分组进行群发(文字)
     * </p>
     * 
     * @param filter 过滤器
     * @param content 文字
     * @return 发送结果
     * @throws WxException 异常
     */
    public MassResult messageMassSendallText(Filter filter, String content) throws WxException {
        //检查
        if (StringUtils.isEmpty(content)) {
            throw new IllegalStateException(PARAM_ERROR);
        }
        //准备参数
        Text bm = new Text();
        bm.setContent(content);
        SendMassMessage massMsg = new SendMassMessage();
        massMsg.setText(bm);
        //请求-处理
        return this.messageMassSendall(filter, "text", massMsg);
    }

    /**
     * <p>
     * Description: 根据分组进行群发(图文)
     * </p>
     * 
     * @param filter 过滤器
     * @param media_id 素材Id
     * @return 发送结果
     * @throws WxException 异常
     */
    public MassResult messageMassSendallMpnews(Filter filter, String media_id) throws WxException {
        //检查
        if (StringUtils.isEmpty(media_id)) {
            throw new IllegalStateException(PARAM_ERROR);
        }
        //准备参数
        BaseMedia bm = new BaseMedia();
        bm.setMedia_id(media_id);
        SendMassMessage massMsg = new SendMassMessage();
        massMsg.setMpnews(bm);
        //请求-处理
        return this.messageMassSendall(filter, "mpnews", massMsg);
    }

    /**
     * <p>
     * Description: 根据分组进行群发
     * </p>
     * 
     * @param filter 过滤器
     * @param msgtype 消息类型
     * @param massMsg 消息
     * @return 发送结果
     * @throws WxException 异常
     */
    private MassResult messageMassSendall(Filter filter, String msgtype, SendMassMessage massMsg) throws WxException {
        //检查
        checkLogin();
        if (filter == null || massMsg == null || StringUtils.isEmpty(msgtype)) {
            throw new IllegalStateException(PARAM_ERROR);
        }
        //准备参数
        massMsg.setMsgtype(msgtype);
        massMsg.setFilter(filter);
        String json = objectToJson(massMsg, false);
        //请求
        String result = this.post(this.getUrl("26", null), json);
        //处理
        MassResult mr = Wx.jsonToObject(result, MassResult.class);
        this.checkResult(mr);
        return mr;
    }

    /**
     * <p>
     * Description: 根据分组进行群发(卡券)
     * </p>
     * 
     * @param touser 填写图文消息的接收者，一串OpenID列表，OpenID最少2个，最多10000个
     * @param card 卡券ID
     * @return 发送结果
     * @throws WxException 异常
     */
    public MassResult messageMassSendWxcard(List<String> touser, Wxcard card) throws WxException {
        //检查
        if (card == null) {
            throw new IllegalStateException(PARAM_ERROR);
        }
        //准备参数
        SendMassMessage massMsg = new SendMassMessage();
        massMsg.setWxcard(card);
        //请求-处理
        return this.messageMassSend(touser, WXCARD, massMsg);
    }

    /**
     * <p>
     * Description: 根据分组进行群发(卡券)
     * </p>
     * 
     * @param touser 填写图文消息的接收者，一串OpenID列表，OpenID最少2个，最多10000个
     * @param card_id 卡券ID
     * @return 发送结果
     * @throws WxException 异常
     */
    public MassResult messageMassSendWxcard(List<String> touser, String card_id) throws WxException {
        //检查
        if (StringUtils.isEmpty(card_id)) {
            throw new IllegalStateException(PARAM_ERROR);
        }
        //准备参数
        Wxcard c = new Wxcard();
        c.setCard_id(card_id);
        SendMassMessage massMsg = new SendMassMessage();
        massMsg.setWxcard(c);
        //请求-处理
        return this.messageMassSend(touser, WXCARD, massMsg);
    }

    /**
     * <p>
     * Description: 根据OpenID列表群发(视频)
     * </p>
     * 
     * @param touser 填写图文消息的接收者，一串OpenID列表，OpenID最少2个，最多10000个
     * @param media_id 素材ID
     * @param title 标题
     * @param description 描述
     * @return 发送结果
     * @throws WxException 异常
     */
    public MassResult messageMassSendVideo(List<String> touser, String media_id, String title, String description)
            throws WxException {
        //检查
        if (StringUtils.isEmpty(media_id)) {
            throw new IllegalStateException(PARAM_ERROR);
        }
        //准备参数
        com.llsfw.wx.api.impl.message.output.active.mass.Video v = null;
        v = new com.llsfw.wx.api.impl.message.output.active.mass.Video();
        v.setDescription(description);
        v.setMedia_id(media_id);
        v.setTitle(title);
        SendMassMessage massMsg = new SendMassMessage();
        massMsg.setVideo(v);
        //请求-处理
        return this.messageMassSend(touser, "video", massMsg);
    }

    /**
     * <p>
     * Description: 根据分组进行群发(视频)
     * </p>
     * 
     * @param touser 填写图文消息的接收者，一串OpenID列表，OpenID最少2个，最多10000个
     * @param media_id 素材Id
     * @return 发送结果
     * @throws WxException 异常
     */
    public MassResult messageMassSendMpvideo(List<String> touser, String media_id) throws WxException {
        //检查
        if (StringUtils.isEmpty(media_id)) {
            throw new IllegalStateException(PARAM_ERROR);
        }
        //准备参数
        BaseMedia bm = new BaseMedia();
        bm.setMedia_id(media_id);
        SendMassMessage massMsg = new SendMassMessage();
        massMsg.setMpvideo(bm);
        //请求-处理
        return this.messageMassSend(touser, "mpvideo", massMsg);
    }

    /**
     * <p>
     * Description: 根据OpenID列表群发(图片)
     * </p>
     * 
     * @param touser 填写图文消息的接收者，一串OpenID列表，OpenID最少2个，最多10000个
     * @param media_id 素材ID
     * @return 发送结果
     * @throws WxException 异常
     */
    public MassResult messageMassSendImage(List<String> touser, String media_id) throws WxException {
        //检查
        if (StringUtils.isEmpty(media_id)) {
            throw new IllegalStateException(PARAM_ERROR);
        }
        //准备参数
        BaseMedia bm = new BaseMedia();
        bm.setMedia_id(media_id);
        SendMassMessage massMsg = new SendMassMessage();
        massMsg.setImage(bm);
        //请求-处理
        return this.messageMassSend(touser, "image", massMsg);
    }

    /**
     * <p>
     * Description: 根据OpenID列表群发(声音)
     * </p>
     * 
     * @param touser 填写图文消息的接收者，一串OpenID列表，OpenID最少2个，最多10000个
     * @param media_id 素材ID
     * @return 发送结果
     * @throws WxException 异常
     */
    public MassResult messageMassSendVoice(List<String> touser, String media_id) throws WxException {
        //检查
        if (StringUtils.isEmpty(media_id)) {
            throw new IllegalStateException(PARAM_ERROR);
        }
        //准备参数
        BaseMedia bm = new BaseMedia();
        bm.setMedia_id(media_id);
        SendMassMessage massMsg = new SendMassMessage();
        massMsg.setVoice(bm);
        //请求-处理
        return this.messageMassSend(touser, "voice", massMsg);
    }

    /**
     * <p>
     * Description: 根据OpenID列表群发(文字)
     * </p>
     * 
     * @param touser 填写图文消息的接收者，一串OpenID列表，OpenID最少2个，最多10000个
     * @param content 文字
     * @return 发送结果
     * @throws WxException 异常
     */
    public MassResult messageMassSendText(List<String> touser, String content) throws WxException {
        //检查
        if (StringUtils.isEmpty(content)) {
            throw new IllegalStateException(PARAM_ERROR);
        }
        //准备参数
        Text bm = new Text();
        bm.setContent(content);
        SendMassMessage massMsg = new SendMassMessage();
        massMsg.setText(bm);
        //请求-处理
        return this.messageMassSend(touser, "text", massMsg);
    }

    /**
     * <p>
     * Description: 根据OpenID列表群发
     * </p>
     * 
     * @param touser 填写图文消息的接收者，一串OpenID列表，OpenID最少2个，最多10000个
     * @param media_id 素材ID
     * @return 发送结果
     * @throws WxException 异常
     */
    public MassResult messageMassSendMpnews(List<String> touser, String media_id) throws WxException {
        //检查
        if (StringUtils.isEmpty(media_id)) {
            throw new IllegalStateException(PARAM_ERROR);
        }
        //准备参数
        BaseMedia bm = new BaseMedia();
        bm.setMedia_id(media_id);
        SendMassMessage massMsg = new SendMassMessage();
        massMsg.setMpnews(bm);
        //请求-处理
        return this.messageMassSend(touser, "mpnews", massMsg);
    }

    /**
     * <p>
     * Description: 根据OpenID列表群发
     * </p>
     * 
     * @param touser 填写图文消息的接收者，一串OpenID列表，OpenID最少2个，最多10000个
     * @param msgtype 消息类型
     * @param massMsg 消息
     * @return 发送结果
     * @throws WxException 异常
     */
    private MassResult messageMassSend(List<String> touser, String msgtype, SendMassMessage massMsg)
            throws WxException {
        //检查
        checkLogin();
        if (touser == null || touser.size() < Constants.NUMBER_2 || touser.size() > Constants.NUMBER_10000
                || massMsg == null || StringUtils.isEmpty(msgtype)) {
            throw new IllegalStateException(PARAM_ERROR);
        }
        //准备参数
        massMsg.setMsgtype(msgtype);
        massMsg.setTouser(touser);
        String json = objectToJson(massMsg, false);
        //请求
        String result = this.post(this.getUrl("27", null), json);
        //处理
        MassResult mr = Wx.jsonToObject(result, MassResult.class);
        this.checkResult(mr);
        return mr;
    }

    /**
     * <p>
     * Description: 删除群发
     * </p>
     * 
     * @param msg_id 发送出去的消息ID
     * @throws WxException 异常
     */
    public void messageMassDelete(String msg_id) throws WxException {
        //检查
        checkLogin();
        if (StringUtils.isEmpty(msg_id)) {
            throw new IllegalStateException(PARAM_ERROR);
        }
        //准备参数
        Map<String, String> param = new HashMap<String, String>();
        param.put("msg_id", msg_id);
        String json = objectToJson(param, false);
        //请求
        String result = this.post(this.getUrl("28", null), json);
        //处理
        BaseResult br = Wx.jsonToObject(result, BaseResult.class);
        this.checkResult(br);
    }

    /**
     * <p>
     * Description: 预览接口(卡券)
     * </p>
     * 
     * @param touser 接收消息用户对应该公众号的openid，该字段也可以改为towxname，以实现对微信号的预览
     * @param card_id 卡券ID
     * @return 发送结果
     * @throws WxException 异常
     */
    public MassResult messageMassSendRreviewWxcard(String touser, String card_id) throws WxException {
        //检查
        if (StringUtils.isEmpty(card_id)) {
            throw new IllegalStateException(PARAM_ERROR);
        }
        //准备参数
        Wxcard c = new Wxcard();
        c.setCard_id(card_id);
        SendMassMessage massMsg = new SendMassMessage();
        massMsg.setWxcard(c);
        //请求-处理
        return this.messageMassSendPreview(touser, WXCARD, massMsg);
    }

    /**
     * <p>
     * Description: 预览接口(卡券)
     * </p>
     * 
     * @param touser 接收消息用户对应该公众号的openid，该字段也可以改为towxname，以实现对微信号的预览
     * @param wxcard 卡券
     * @return 发送结果
     * @throws WxException 异常
     */
    public MassResult messageMassSendRreviewWxcard(String touser, Wxcard wxcard) throws WxException {
        //检查
        if (wxcard == null) {
            throw new IllegalStateException(PARAM_ERROR);
        }
        //准备参数
        SendMassMessage massMsg = new SendMassMessage();
        massMsg.setWxcard(wxcard);
        //请求-处理
        return this.messageMassSendPreview(touser, WXCARD, massMsg);
    }

    /**
     * <p>
     * Description: 预览接口(视频)
     * </p>
     * 
     * @param touser 接收消息用户对应该公众号的openid，该字段也可以改为towxname，以实现对微信号的预览
     * @param media_id 素材ID
     * @param title 标题
     * @param description 描述
     * @return 发送结果
     * @throws WxException 异常
     */
    public MassResult messageMassSendRreviewVideo(String touser, String media_id, String title, String description)
            throws WxException {
        //检查
        if (StringUtils.isEmpty(media_id)) {
            throw new IllegalStateException(PARAM_ERROR);
        }
        //准备参数
        com.llsfw.wx.api.impl.message.output.active.mass.Video v = null;
        v = new com.llsfw.wx.api.impl.message.output.active.mass.Video();
        v.setDescription(description);
        v.setMedia_id(media_id);
        v.setTitle(title);
        SendMassMessage massMsg = new SendMassMessage();
        massMsg.setVideo(v);
        //请求-处理
        return this.messageMassSendPreview(touser, "video", massMsg);
    }

    /**
     * <p>
     * Description: 预览接口(视频)
     * </p>
     * 
     * @param touser 接收消息用户对应该公众号的openid，该字段也可以改为towxname，以实现对微信号的预览
     * @param media_id 素材Id
     * @return 发送结果
     * @throws WxException 异常
     */
    public MassResult messageMassSendRreviewMpvideo(String touser, String media_id) throws WxException {
        //检查
        if (StringUtils.isEmpty(media_id)) {
            throw new IllegalStateException(PARAM_ERROR);
        }
        //准备参数
        BaseMedia bm = new BaseMedia();
        bm.setMedia_id(media_id);
        SendMassMessage massMsg = new SendMassMessage();
        massMsg.setMpvideo(bm);
        //请求-处理
        return this.messageMassSendPreview(touser, "mpvideo", massMsg);
    }

    /**
     * <p>
     * Description: 预览接口(图片)
     * </p>
     * 
     * @param touser 接收消息用户对应该公众号的openid，该字段也可以改为towxname，以实现对微信号的预览
     * @param media_id 素材Id
     * @return 发送结果
     * @throws WxException 异常
     */
    public MassResult messageMassSendRreviewImage(String touser, String media_id) throws WxException {
        //检查
        if (StringUtils.isEmpty(media_id)) {
            throw new IllegalStateException(PARAM_ERROR);
        }
        //准备参数
        BaseMedia bm = new BaseMedia();
        bm.setMedia_id(media_id);
        SendMassMessage massMsg = new SendMassMessage();
        massMsg.setImage(bm);
        //请求-处理
        return this.messageMassSendPreview(touser, "image", massMsg);
    }

    /**
     * <p>
     * Description: 预览接口(语音)
     * </p>
     * 
     * @param touser 接收消息用户对应该公众号的openid，该字段也可以改为towxname，以实现对微信号的预览
     * @param media_id 素材Id
     * @return 发送结果
     * @throws WxException 异常
     */
    public MassResult messageMassSendRreviewVoice(String touser, String media_id) throws WxException {
        //检查
        if (StringUtils.isEmpty(media_id)) {
            throw new IllegalStateException(PARAM_ERROR);
        }
        //准备参数
        BaseMedia bm = new BaseMedia();
        bm.setMedia_id(media_id);
        SendMassMessage massMsg = new SendMassMessage();
        massMsg.setVoice(bm);
        //请求-处理
        return this.messageMassSendPreview(touser, "voice", massMsg);
    }

    /**
     * <p>
     * Description: 预览接口(文字)
     * </p>
     * 
     * @param touser 接收消息用户对应该公众号的openid，该字段也可以改为towxname，以实现对微信号的预览
     * @param content 文字
     * @return 发送结果
     * @throws WxException 异常
     */
    public MassResult messageMassSendRreviewText(String touser, String content) throws WxException {
        //检查
        if (StringUtils.isEmpty(content)) {
            throw new IllegalStateException(PARAM_ERROR);
        }
        //准备参数
        Text bm = new Text();
        bm.setContent(content);
        SendMassMessage massMsg = new SendMassMessage();
        massMsg.setText(bm);
        //请求-处理
        return this.messageMassSendPreview(touser, "text", massMsg);
    }

    /**
     * <p>
     * Description: 预览接口(图文)
     * </p>
     * 
     * @param touser 接收消息用户对应该公众号的openid，该字段也可以改为towxname，以实现对微信号的预览
     * @param media_id 素材Id
     * @return 发送结果
     * @throws WxException 异常
     */
    public MassResult messageMassSendRreviewMpnews(String touser, String media_id) throws WxException {
        //检查
        if (StringUtils.isEmpty(media_id)) {
            throw new IllegalStateException(PARAM_ERROR);
        }
        //准备参数
        BaseMedia bm = new BaseMedia();
        bm.setMedia_id(media_id);
        SendMassMessage massMsg = new SendMassMessage();
        massMsg.setMpnews(bm);
        //请求-处理
        return this.messageMassSendPreview(touser, "mpnews", massMsg);
    }

    /**
     * <p>
     * Description: 预览接口
     * </p>
     * 
     * @param touser 接收消息用户对应该公众号的openid，该字段也可以改为towxname，以实现对微信号的预览
     * @param msgtype 消息类型
     * @param massMsg 消息
     * @return 发送结果
     * @throws WxException 异常
     */
    private MassResult messageMassSendPreview(String touser, String msgtype, SendMassMessage massMsg)
            throws WxException {
        //检查
        checkLogin();
        if (StringUtils.isEmpty(touser) || massMsg == null || StringUtils.isEmpty(msgtype)) {
            throw new IllegalStateException(PARAM_ERROR);
        }
        //准备参数
        massMsg.setMsgtype(msgtype);
        massMsg.setTowxname(touser);
        String json = objectToJson(massMsg, false);
        //请求
        String result = this.post(this.getUrl("29", null), json);
        //处理
        MassResult mr = Wx.jsonToObject(result, MassResult.class);
        this.checkResult(mr);
        return mr;
    }

    /**
     * <p>
     * Description: 查询群发消息发送状态
     * </p>
     * 
     * @param msg_id 消息ID
     * @return 状态
     * @throws WxException 异常
     */
    public String messageMassGet(String msg_id) throws WxException {
        //检查
        checkLogin();
        if (StringUtils.isEmpty(msg_id)) {
            throw new IllegalStateException(PARAM_ERROR);
        }
        //准备参数
        Map<String, String> param = new HashMap<String, String>();
        param.put("msg_id", msg_id);
        String json = objectToJson(param, false);
        //请求
        String result = this.post(this.getUrl("30", null), json);
        //处理
        MassResult br = Wx.jsonToObject(result, MassResult.class);
        this.checkResult(br);
        return br.getMsg_status();
    }

    /**
     * <p>
     * Description: 验证用户登录
     * <p>
     * 调用所有方法之前，应先调用此方法检查用户是否已经登录，或者Token是否失效<br/>
     * 如果没有登录，则跑出异常提示登录，如果失效，密码正确的情况下回自动重新登录。
     * </p>
     * </p>
     * 
     * @throws WxException 异常
     */
    private void checkLogin() throws WxException {
        if (this.oauthToken == null || this.oauthToken.isExprexpired()) {
            if (this.oauth == null) {
                throw new WxException("oauth is null and oauthToken is exprexpired, please log in again!");
            }
        }
        login();
    }

    /**
     * <p>
     * Description: 获取access_token
     * </p>
     * 
     * @throws WxException 异常
     */
    private void login() throws WxException {
        //准备参数
        String param = "?grant_type=" + this.oauth.getGrant_type();
        param += "&appid=" + this.oauth.getAppId() + "&secret=" + this.oauth.getSecret();
        //请求
        String result = this.get(WxBase.getWxApiUrl("0") + param);
        //处理
        Token token = Wx.jsonToObject(result, Token.class);
        this.checkResult(token);
        this.oauthToken = new OAuthToken(token.getAccess_token(), token.getExpires_in());
    }

    /**
     * <p>
     * Description: 返回url
     * </p>
     * 
     * @param id url编号
     * @param param 参数(从&开始)
     * @return 完整url
     */
    public String getUrl(String id, String param) {
        return WxBase.getWxApiUrl(id) + "?access_token=" + this.oauthToken.getAccess_token() + param;
    }

}
