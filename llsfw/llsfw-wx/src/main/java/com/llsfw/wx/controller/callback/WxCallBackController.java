/**
 * WxCallBackController.java
 * Created at 2016-04-23
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.wx.controller.callback;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.llsfw.core.controller.base.BaseController;
import com.llsfw.wx.api.IWxCallBack;
import com.llsfw.wx.api.impl.callback.WxCallBack;
import com.llsfw.wx.exception.WxException;

/**
 * <p>
 * ClassName: WxCallBackController
 * </p>
 * <p>
 * Description: 微信验证控制器
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年4月23日
 * </p>
 */
@Controller
@RequestMapping("/wx/callBack/{businessCode}")
public class WxCallBackController extends BaseController {

    /**
     * <p>
     * Description: 微信验证(GET)
     * </p>
     * 
     * @param res 响应对象
     * @param businessCode 业务代码
     * @param signature 微信加密签名
     * @param timestamp 时间戳
     * @param nonce 随机数
     * @param echostr 随机数
     * @throws IOException 异常
     * @throws WxException 异常
     */
    @RequestMapping(method = RequestMethod.GET)
    public void wxCallBack(ServletResponse res, @PathVariable("businessCode") String businessCode, String signature,
            String timestamp, String nonce, String echostr) throws IOException, WxException {
        IWxCallBack wcb = new WxCallBack();
        wcb.checkSignature("wangkang80", signature, timestamp, nonce);
        res.getWriter().write(echostr);
    }

    /**
     * <p>
     * Description: 微信消息推送(POST)
     * </p>
     * 
     * @param req 响应对象
     * @param res 响应对象
     * @param businessCode 业务代码
     * @param signature 微信加密签名
     * @param timestamp 时间戳
     * @param nonce 随机数
     * @param echostr 随机数
     * @throws WxException 异常
     * @throws IOException 异常
     */
    @RequestMapping(method = RequestMethod.POST)
    public void wxCallBack(ServletRequest req, ServletResponse res, @PathVariable("businessCode") String businessCode,
            String signature, String timestamp, String nonce, String echostr) throws WxException, IOException {
        IWxCallBack wcb = new WxCallBack();
        String outputXml = wcb.msessageHandler(businessCode, "wangkang80", signature, timestamp, nonce,
                req.getInputStream());
        res.getWriter().write(outputXml);
    }
}
