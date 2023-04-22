package com.cattle.house;

import cn.hutool.core.convert.Convert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class CattleHouseApplicationTests {
    private static final Logger LOGGER = LogManager.getLogger(CattleHouseApplicationTests.class);
    @Test
    void contextLoads() {
        UUID uuid = UUID.randomUUID();
        int length = Convert.toStr(uuid).length();
        System.out.println(length);
        LOGGER.info("log-----");
    }

}
