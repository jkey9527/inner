package com.cattle.house.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author niujie
 * @date 2023/4/30 15:55
 */
@Component
@ConfigurationProperties(prefix = "profile")
@Data
public class Profile {

    private boolean development;
}