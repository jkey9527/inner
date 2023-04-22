package com.cattle.house;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.cattle.house.mapper")
@EnableTransactionManagement
public class CattleHouseApplication {

    public static void main(String[] args) {
        SpringApplication.run(CattleHouseApplication.class, args);
    }

}
