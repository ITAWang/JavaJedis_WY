import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ResourceBundle;

/**
 * @ClassName RedisUtils
 * @Description: TODO
 * @Author: itwang@qq.com
 */
public class RedisUtils {
    private static String host = null; //链接IP地址
    private static String port = null;  //端口号
    private static String maxTotal = null; //最大连接数
    private static String maxIdle = null;   //最大活跃数

    private static JedisPool jedisPool = null;

    static {
        //获取配置文件对象
        ResourceBundle resourceBundle = ResourceBundle.getBundle("redis");

        //通过配置文件中的键获取值
        host = resourceBundle.getString("redis.host");
        port = resourceBundle.getString("redis.port");
        maxTotal = resourceBundle.getString("redis.maxTotal");
        maxIdle = resourceBundle.getString("redis.maxIdle");

       //创建Redis配置对象
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

        //设置详细配置参数
        jedisPoolConfig.setMaxTotal(Integer.parseInt(maxTotal));
        jedisPoolConfig.setMaxIdle(Integer.parseInt(maxIdle));

        //创建连接
        jedisPool = new JedisPool(jedisPoolConfig,host,Integer.parseInt(port));
    }


    public static Jedis getJedis(){
        return jedisPool.getResource();
    }
}
