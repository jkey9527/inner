package com.cattle.house;

import cn.hutool.core.convert.Convert;
import cn.hutool.json.JSONUtil;
import com.cattle.house.bean.UserBean;
import com.cattle.house.service.UserService;
import com.cattle.house.util.RedisUtil;
import com.cattle.house.util.UuIdUtil;
import com.github.pagehelper.PageInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

@SpringBootTest
class CattleHouseApplicationTests {

    private static final Logger LOGGER = LogManager.getLogger(CattleHouseApplicationTests.class);

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UserService userService;

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

    @Test
    public void testPage() throws Exception {
        PageInfo<UserBean> userBeanPageInfo = userService.getUserList4Page(new UserBean());
        List<UserBean> list = userBeanPageInfo.getList();
        System.out.println(list.size());
        System.out.println(JSONUtil.toJsonStr(userBeanPageInfo));
    }

}
