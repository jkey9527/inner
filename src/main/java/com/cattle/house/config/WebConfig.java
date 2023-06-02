package com.cattle.house.config;

import com.cattle.house.interceptor.HouseInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * web配置类，用于过滤前端请求，判断token与登录token
 *
 * @author niujie
 * @date 2023/4/30 14:45
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
                .excludePathPatterns("/cattle/house/token/getToken", "/cattle/house/user/login", "/cattle/house/user/loginOut");
    }
}
