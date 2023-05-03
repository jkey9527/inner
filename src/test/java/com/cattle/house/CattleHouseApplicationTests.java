package com.cattle.house;

import com.cattle.house.service.UserService;
import com.cattle.house.util.RedisUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CattleHouseApplicationTests {

    private static final Logger LOGGER = Logger.getLogger(CattleHouseApplicationTests.class);

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UserService userService;


}
