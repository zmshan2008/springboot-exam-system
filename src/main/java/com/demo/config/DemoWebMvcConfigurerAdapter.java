package com.demo.config;

import com.demo.common.ConstDatas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
public class DemoWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {


    @Autowired
    AccountArgumentResolver accountArgumentResolver;
    /**
     * 配置静态访问资源
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**").addResourceLocations(ConstDatas.UPLOAD_FILE_PATH);
        super.addResourceHandlers(registry);
    }
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(accountArgumentResolver);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AccountInterceptor()).addPathPatterns("/manage/**").excludePathPatterns("/manage/login");
        registry.addInterceptor(new AccountForeInterceptor()).addPathPatterns("/account/**").excludePathPatterns("/account/api/login");
    }
}
