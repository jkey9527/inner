package com.cattle.inner;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.cattle.inner.mapper")
@PropertySource("classpath:log4j.properties")
@EnableTransactionManagement
public class CattleInnerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CattleInnerApplication.class, args);
    }
}
