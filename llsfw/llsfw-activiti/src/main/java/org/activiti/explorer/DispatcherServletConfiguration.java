/**
 * DispatcherServletConfiguration.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package org.activiti.explorer;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * <p>
 * ClassName: DispatcherServletConfiguration
 * </p>
 * <p>
 * Description: 工作流配置
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月24日
 * </p>
 */
@Configuration
@ComponentScan({ "org.activiti.rest,org.activiti.conf" })
@EnableAsync
public class DispatcherServletConfiguration extends WebMvcConfigurationSupport {

    /**
     * <p>
     * Field log: 日志
     * </p>
     */
    private final Logger log = LoggerFactory.getLogger(DispatcherServletConfiguration.class);

    /**
     * <p>
     * Field objectMapper: json转换
     * </p>
     */
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * <p>
     * Field environment: 环境
     * </p>
     */
    @SuppressWarnings("unused")
    @Autowired
    private Environment environment;

    /**
     * <p>
     * Description: 配置
     * </p>
     * 
     * @return 结果
     */
    @Bean
    public SessionLocaleResolver localeResolver() {
        return new SessionLocaleResolver();
    }

    /**
     * <p>
     * Description: 配置
     * </p>
     * 
     * @return 结果
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        this.log.debug("Configuring localeChangeInterceptor");
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("language");
        return localeChangeInterceptor;
    }

    /**
     * <p>
     * Description: 配置
     * </p>
     * 
     * @return 结果
     */
    @Bean
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
        this.log.debug("Creating requestMappingHandlerMapping");
        RequestMappingHandlerMapping requestMappingHandlerMapping = new RequestMappingHandlerMapping();
        requestMappingHandlerMapping.setUseSuffixPatternMatch(false);
        Object[] interceptors = { localeChangeInterceptor() };
        requestMappingHandlerMapping.setInterceptors(interceptors);
        return requestMappingHandlerMapping;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        addDefaultHttpMessageConverters(converters);
        for (HttpMessageConverter<?> converter : converters) {
            if (converter instanceof MappingJackson2HttpMessageConverter) {
                MappingJackson2HttpMessageConverter jacksonToHttpMessageConverter = null;
                jacksonToHttpMessageConverter = (MappingJackson2HttpMessageConverter) converter;
                jacksonToHttpMessageConverter.setObjectMapper(this.objectMapper);
                break;
            }
        }
    }

    @Override
    protected void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false);
    }

}
