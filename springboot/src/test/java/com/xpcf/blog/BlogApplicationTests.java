package com.xpcf.blog;

import com.xpcf.blog.mapper.UserMapper;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

@SpringBootTest
class BlogApplicationTests {

    @Autowired
    DataSource dataSource;
    @Autowired
    UserMapper userMapper;
    @Autowired
    RedisSessionDAO redisSessionDAO;

    @Autowired
    RedisCacheManager redisCacheManager;
    @Test
    void contextLoads() {


        System.out.println(redisSessionDAO.getClass());
    }

}
