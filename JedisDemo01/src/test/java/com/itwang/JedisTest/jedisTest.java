package com.itwang.JedisTest;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @ClassName jedisTest
 * @Description: TODO
 * @Author: itwang@qq.com
 */
public class jedisTest {
    @Test
    public void test01(){
        //连接Redis
        Jedis jedis = new Jedis("127.0.0.1", 6379);

        //设置键值
        jedis.set("name","itwang");
        //获取键值
        String name = jedis.get("name");
        System.out.println("JedisName:" + name);

        //释放资源
        jedis.close();
    }

    @Test
    public void test02(){
        Jedis jedis = new Jedis("127.0.0.1", 6379);

        Long rPush = jedis.rpush("myList", "Andy", "Jack", "Lucy", "Perter");
        System.out.println("rPush:" + rPush);

        List<String> myList = jedis.lrange("myList", 0, -1);

        for (String name : myList) {
            System.out.println(name);
        }

        jedis.close();
    }
}
