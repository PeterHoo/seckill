package org.seckill.dao.cache;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.seckill.entity.SecKill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by huqingze on 2017/3/9.
 */
public class RedisDao {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private JedisPool jedisPool;

    public RedisDao(String ip, int port) {
        jedisPool = new JedisPool(ip, port);
    }

    private RuntimeSchema<SecKill> schema = RuntimeSchema.createFrom(SecKill.class);

    public SecKill getSeckill(long seckillId) {
        //redis操作逻辑
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                String key = "seckill:" + seckillId;
                //redis没有实现内部序列化的操作
                //get->byte[]->反序列化->Object(Seckill)
                //不使用 Serializable，采用自定义序列化（性能优化更好，速度快，转化字节数更少）
                //protostuff:pojo
                byte[] bytes = jedis.get(key.getBytes());
                //缓存中获取到
                if (bytes != null) {
                    //创建一个空对象
                    SecKill secKill = schema.newMessage();
                    ProtostuffIOUtil.mergeFrom(bytes, secKill, schema);
                    //seckill被反序列化
                    return secKill;
                }
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public String setSeckill(SecKill secKill) {
        //set Object(Seckill)->序列化->byte[]
        try {
            Jedis jedis = jedisPool.getResource();
            try {
                String key = "seckill:" + secKill.getSeckillId();
                byte[] bytes = ProtostuffIOUtil.toByteArray(secKill, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                //超时缓存
                int timeout = 60 * 60;//1小时
                String result = jedis.setex(key.getBytes(), timeout, bytes);
                return result;
            } finally {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
}
