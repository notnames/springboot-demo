package com.example.springbootdemo2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

@SpringBootTest
public class RedisTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testSet(){
        stringRedisTemplate.opsForValue().set("token1","token-value-1");
        stringRedisTemplate.opsForValue().set("token2","token-value-2", 10, TimeUnit.SECONDS);
    }

    @Test
    public void testGet(){
        String token1 = stringRedisTemplate.opsForValue().get("token1");
        String token2 = stringRedisTemplate.opsForValue().get("token2");
        System.out.println(token1);
        System.out.println(token2);
    }


    @Test
    public void testDelete(){
        stringRedisTemplate.delete("token1");
    }
}
