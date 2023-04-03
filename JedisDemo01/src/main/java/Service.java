import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisDataException;

/**
 * @ClassName Service
 * @Description: TODO
 * @Author: itwang@qq.com
 */
public class Service {
    private String level; //用户级别
    private int num; //操作次数

    public Service(String level, int num) {
        this.level = level;
        this.num = num;
    }

    /**
     * 权限控制单元
     */
    public void service(){
        Jedis jedis = RedisUtils.getJedis();
        String value = jedis.get("UserLevel:" + level);

        //判断这个key是否存在
        try{
            if (value == null){
                jedis.setex("UserLevel:" + level,50,Long.MAX_VALUE - num + "");
            }else {
                //如果存在,先自增,再执行下载
                Long incrLevel = jedis.incr("UserLevel:" + level);
                download(level,num -(Long.MAX_VALUE-incrLevel));
            }
        }catch (JedisDataException e){
            System.out.println(Thread.currentThread().getName() + ">>> 下载已经达到次数上线,请升级会员级别");
            return;
        }finally {
            jedis.close();
        }

    }

    private void download(String level, long val) {
        System.out.println("用户：" + level + "已经下载第" + val + "次");
    }
}
