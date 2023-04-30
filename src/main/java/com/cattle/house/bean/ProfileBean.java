package com.cattle.house.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 配置实体
 *
 * @author niujie
 * @date 2023/4/30 15:55
 */
@Component
@ConfigurationProperties(prefix = "profile")
@Data
public class ProfileBean {

    /**
     * 是否开发模式
     */
    private boolean development;

}