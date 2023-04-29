package com.cattle.house.config;

import com.cattle.house.interceptor.HouseInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web配置类
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public HouseInterceptor houseInterceptor() {
        return new HouseInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截所有请求
        // 对获取token和登录放行
        registry.addInterceptor(houseInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/cattle/house/token/getToken", "/cattle/house/user/login");
    }
}
