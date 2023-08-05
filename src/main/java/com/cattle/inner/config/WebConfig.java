package com.cattle.inner.config;

import com.cattle.inner.interceptor.InnerInterceptor;
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
    public InnerInterceptor houseInterceptor() {
        return new InnerInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截所有请求
        // 对获取token和登录放行
        registry.addInterceptor(houseInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/cattle/inner/token/getToken", "/cattle/inner/user/login", "/cattle/inner/user/loginOut");
    }
}
