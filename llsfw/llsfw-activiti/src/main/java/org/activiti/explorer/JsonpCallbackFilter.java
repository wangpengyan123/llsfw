/**
 * JsonpCallbackFilter.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package org.activiti.explorer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * ClassName: JsonpCallbackFilter
 * </p>
 * <p>
 * Description: 配置
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月24日
 * </p>
 */
public class JsonpCallbackFilter implements Filter {

    /**
     * <p>
     * Field LOG: 日志
     * </p>
     */
    private static final Logger LOG = LoggerFactory.getLogger(JsonpCallbackFilter.class);

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        Map<String, String[]> parms = httpRequest.getParameterMap();

        if (parms.containsKey("callback")) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Wrapping response with JSONP callback '" + parms.get("callback")[0] + "'");
            }

            OutputStream out = httpResponse.getOutputStream();

            GenericResponseWrapper wrapper = new GenericResponseWrapper(httpResponse);

            chain.doFilter(request, wrapper);

            // handles the content-size truncation
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            outputStream.write(new String(parms.get("callback")[0] + "(").getBytes());
            outputStream.write(wrapper.getData());
            outputStream.write(new String(");").getBytes());
            byte[] jsonpResponse = outputStream.toByteArray();

            wrapper.setContentType("text/javascript;charset=UTF-8");
            wrapper.setContentLength(jsonpResponse.length);

            out.write(jsonpResponse);

            out.close();

        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }
}