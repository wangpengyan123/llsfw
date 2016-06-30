/**
 * CookieUtil.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.core.common;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Cookie工具类
 * 
 */
public class CookieUtil {

    /**
     * <p>
     * Description: 构造函数
     * </p>
     */
    public CookieUtil() {

    }

    /**
     * <p>
     * Description: 设置 Cookie（生成时间为1天）
     * </p>
     * 
     * @param response 响应对象
     * @param name 名称
     * @param value 值
     * @param secure use SSL
     * @throws UnsupportedEncodingException 异常
     */
    public static void setCookie(HttpServletResponse response, String name, String value, boolean secure)
            throws UnsupportedEncodingException {
        setCookie(response, name, value, Constants.NUMBER_60 * Constants.NUMBER_60 * Constants.NUMBER_24, secure);
    }

    /**
     * 设置 Cookie
     * 
     * @param response 响应对象
     * @param name 名称
     * @param value 值
     * @param path 路径
     * @param secure use SSL
     * @throws UnsupportedEncodingException 异常
     */
    public static void setCookie(HttpServletResponse response, String name, String value, String path, boolean secure)
            throws UnsupportedEncodingException {
        setCookie(response, name, value, path, Constants.NUMBER_60 * Constants.NUMBER_60 * Constants.NUMBER_24, secure);
    }

    /**
     * 设置 Cookie
     * 
     * @param response 响应对象
     * @param name 名称
     * @param value 值
     * @param maxAge 生存时间（单位秒）
     * @param secure use SSL
     * @throws UnsupportedEncodingException 异常
     */
    public static void setCookie(HttpServletResponse response, String name, String value, int maxAge, boolean secure)
            throws UnsupportedEncodingException {
        setCookie(response, name, value, "/", maxAge, secure);
    }

    /**
     * 设置 Cookie
     * 
     * @param response 响应对象
     * @param name 名称
     * @param value 值
     * @param maxAge 生存时间（单位秒）
     * @param path 路径
     * @param secure use SSL
     * @throws UnsupportedEncodingException 异常
     */
    public static void setCookie(HttpServletResponse response, String name, String value, String path, int maxAge,
            boolean secure) throws UnsupportedEncodingException {
        Cookie cookie = new Cookie(name, null);
        cookie.setPath(path);
        cookie.setMaxAge(maxAge);
        cookie.setSecure(secure);
        cookie.setValue(URLEncoder.encode(value, "utf-8"));
        response.addCookie(cookie);
    }

    /**
     * 获得指定Cookie的值
     * 
     * @param request 请求对象
     * @param name 名称
     * @return 值
     * @throws UnsupportedEncodingException 异常
     */
    public static String getCookie(HttpServletRequest request, String name) throws UnsupportedEncodingException {
        return getCookie(request, null, name, false);
    }

    /**
     * 获得指定Cookie的值，并删除。
     * 
     * @param request 请求对象
     * @param response 响应对象
     * @param name 名称
     * @return 值
     * @throws UnsupportedEncodingException 异常
     */
    public static String getCookie(HttpServletRequest request, HttpServletResponse response, String name)
            throws UnsupportedEncodingException {
        return getCookie(request, response, name, true);
    }

    /**
     * 获得指定Cookie的值
     * 
     * @param request 请求对象
     * @param response 响应对象
     * @param name 名字
     * @param isRemove 是否移除
     * @return 值
     * @throws UnsupportedEncodingException 异常
     */
    public static String getCookie(HttpServletRequest request, HttpServletResponse response, String name,
            boolean isRemove) throws UnsupportedEncodingException {
        String value = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    value = URLDecoder.decode(cookie.getValue(), "utf-8");
                }
                if (cookie.getName().equals(name) && isRemove) {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }
        return value;
    }
}
