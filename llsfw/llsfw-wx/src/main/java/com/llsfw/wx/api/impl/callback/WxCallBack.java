/**
 * WxCallBack.java
 * Created at 2016-04-23
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.wx.api.impl.callback;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.llsfw.core.common.Constants;
import com.llsfw.wx.api.IMsgHandler;
import com.llsfw.wx.api.IWxCallBack;
import com.llsfw.wx.api.impl.message.MsgType;
import com.llsfw.wx.api.impl.message.input.InputMsg;
import com.llsfw.wx.api.impl.message.output.AbstractOutputMessage;
import com.llsfw.wx.common.ShaOne;
import com.llsfw.wx.exception.WxException;

/**
 * <p>
 * ClassName: WxCallBack
 * </p>
 * <p>
 * Description: 微信回调
 * <p>
 * Description: 为应用提供URL算法<br />
 * 根据不同的URL返回不同的Token，以适应多微站的需求<br />
 * 根据配置的系统Token不同，而改变
 * </p>
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年4月23日
 * </p>
 */
public class WxCallBack implements IWxCallBack {

    /**
     * <p>
     * Field log: 日志
     * </p>
     */
    public Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * <p>
     * Field msgHandlerMap: 普通消息处理器对象(需注入)
     * </p>
     */
    private Map<String, IMsgHandler> normalMsgHandlerMap;

    /**
     * <p>
     * Field msgHandlerMap: 事件消息处理器对象(需注入)
     * </p>
     */
    private Map<String, IMsgHandler> eventMsgHandlerMap;

    /**
     * <p>
     * Description: 构造函数
     * </p>
     */
    public WxCallBack() {

    }

    @Override
    public void checkSignature(String systemToken, String signature, String timestamp, String nonce)
            throws WxException {
        List<String> params = new ArrayList<String>();
        params.add(systemToken);
        params.add(timestamp);
        params.add(nonce);
        //1. 将token、timestamp、nonce三个参数进行字典序排序
        Collections.sort(params, new Comparator<String>() {
            @Override
            public int compare(String oa, String ob) {
                return oa.compareTo(ob);
            }
        });
        //2. 将三个参数字符串拼接成一个字符串进行sha1加密
        String temp = ShaOne.encode(params.get(0) + params.get(1) + params.get(Constants.NUMBER_2));
        //3. 开发者获得加密后的字符串可与signature对比，如果不一致,则抛出异常
        if (!temp.equals(signature)) {
            throw new WxException("checkSignature Fail");
        }
    }

    @Override
    public String msessageHandler(String businessCode, String systemToken, String signature, String timestamp,
            String nonce, ServletInputStream inputStream) throws WxException {
        try {
            this.checkSignature(systemToken, signature, timestamp, nonce);
            String inputXml = Constants.getData(inputStream);
            //TODO 后续需增加解密流程
            InputMsg inputMsg = xmlToObject(inputXml, InputMsg.class);
            AbstractOutputMessage outputMessage = null;
            if (MsgType.event.toString().equals(inputMsg.getMsgType())) { //匹配到事件消息处理器
                if (this.eventMsgHandlerMap != null && this.eventMsgHandlerMap.containsKey(inputMsg.getEvent())) {
                    IMsgHandler msgHandler = this.eventMsgHandlerMap.get(inputMsg.getEvent());
                    outputMessage = msgHandler.handler(businessCode, inputXml);
                } else { //没有匹配到消息类型
                    this.log.warn("没有匹配到事件消息处理器:" + inputXml);
                }
            } else { //匹配到普通消息处理器
                if (this.normalMsgHandlerMap != null && this.normalMsgHandlerMap.containsKey(inputMsg.getMsgType())) {
                    IMsgHandler msgHandler = this.normalMsgHandlerMap.get(inputMsg.getMsgType());
                    outputMessage = msgHandler.handler(businessCode, inputXml);
                } else { //没有匹配到消息类型
                    this.log.warn("没有匹配到普通消息处理器:" + inputXml);
                }
            }
            String outputXml = "success";
            if (outputMessage != null) {
                outputXml = outputMessage.toXml();
                //TODO 后续需增加加密流程
                this.log.info(outputXml);
            }
            return outputXml;
        } catch (IOException e) {
            throw new WxException(e.getMessage(), e);
        }
    }

    /**
     * <p>
     * Description: 将XML转换为对象
     * </p>
     * 
     * @param inputXml xml
     * @param t 对象类型
     * @param <T> 类型
     * @return 对象
     * @throws WxException 异常
     */
    @SuppressWarnings("unchecked")
    public static <T> T xmlToObject(String inputXml, Class<T> t) throws WxException {
        try {
            JAXBContext context = JAXBContext.newInstance(t);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (T) unmarshaller.unmarshal(new StringReader(inputXml));
        } catch (JAXBException e) {
            throw new WxException(e.getMessage(), e);
        }
    }

    /**
     * <p>
     * Description: 注入普通消息处理Map
     * </p>
     * 
     * @param normalMsgHandlerMap 普通消息处理Map
     */
    public void setNormalMsgHandlerMap(Map<String, IMsgHandler> normalMsgHandlerMap) {
        this.normalMsgHandlerMap = normalMsgHandlerMap;
    }

    /**
     * <p>
     * Description: 注入事件消息处理Map
     * </p>
     * 
     * @param eventMsgHandlerMap 事件消息处理Map
     */
    public void setEventMsgHandlerMap(Map<String, IMsgHandler> eventMsgHandlerMap) {
        this.eventMsgHandlerMap = eventMsgHandlerMap;
    }

}
