package com.cattle.house;

import cn.hutool.core.convert.Convert;
import com.cattle.house.util.RedisUtil;
import com.cattle.house.util.UuIdUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class CattleHouseApplicationTests {

    private static final Logger LOGGER = LogManager.getLogger(CattleHouseApplicationTests.class);

    @Autowired
    private RedisUtil redisUtil;

    @Test
    void contextLoads() {
        UUID uuid = UUID.randomUUID();
        int length = Convert.toStr(uuid).length();
        System.out.println(length);
        LOGGER.info("log-----");
    }

    @Test
    void testRedis() throws InterruptedException {
        redisUtil.set("test", "测试超时", 10);
        for (int i = 0; i < 20; i++) {
            System.out.println(redisUtil.get("test"));
            Thread.sleep(1 * 1000);
        }

        System.out.println(redisUtil.get("test"));
    }

    @Test
    public void getUUid() {
        LOGGER.info(UuIdUtil.getUUID());
    }

}
