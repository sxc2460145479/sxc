package com.sxc.demo.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器，拦截指定的url
 */
@Component
public class InterceptorConfiguration implements WebMvcConfigurer {

    @Autowired
    private AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加一个拦截器，拦截以/为前缀的url路径
        // excludePathPatterns为不需要拦截的路径
        registry.addInterceptor(authInterceptor).addPathPatterns("/**").excludePathPatterns("/admin");
    }
}
