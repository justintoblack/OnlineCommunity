package com.onlinecommunity;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class RedisTest {

    @Test
    public void redisTest()
    {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        HashMap<String, String> map = new HashMap<>();
        map.put("ymc1","1");
        map.put("ymc1","2");
        map.put("ymc1","3");
        map.put("ymc2","1");
        map.put("ymc3","1");
        map.put("ymc4","1");
        map.put("ymc5","1");
        jedis.hmset("ymc",map);
        String hget = jedis.hget("ymc", "ymc1");
        System.out.println(hget);
//        List<String> ymc1 = jedis.hvals("ymc1");
//        for (String str : ymc1)
//            System.out.println(str);

        jedis.close();

    }
}
