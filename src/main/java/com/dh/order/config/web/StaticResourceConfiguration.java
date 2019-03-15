package com.dh.order.config.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author xjk
 * @date 2019/3/14 -  17:28
 **/
@Configuration
public class StaticResourceConfiguration extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        //addResourceHandler是指你想在url请求的路径

        //addResourceLocations是图片存放的真实路径

        registry.addResourceHandler("/foodPic/**").addResourceLocations("file:D:/foodPic/");
        super.addResourceHandlers(registry);
    }

}
