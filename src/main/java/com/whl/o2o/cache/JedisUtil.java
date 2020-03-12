package com.whl.o2o.cache;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.util.SafeEncoder;

import java.util.Set;

/**
 * @author whl
 * @version V1.0
 * @Title:
 * @Description:
 */
public class JedisUtil {
    private final int expire = 60000;//缓存生存时间
    public Keys KEYS;//操作Key的方法
    public Strings STRINGS;//存储结构为String类型的操作
    private JedisPool jedisPool;//Redis连接池实例

    /**
     * 获取redis连接池
     * @return
     */
    public JedisPool getJedisPool() {
        return jedisPool;
    }

    /**
     * 设置redis连接池
     * @return
     */
    public void setJedisPool(JedisPoolWriper jedisPoolWriper) {
        this.jedisPool = jedisPoolWriper.getJedisPool();
    }

    /**
     * 从jedis连接池中获取获取jedis对象
     * @return
     */
    public Jedis getJedis() {
        return jedisPool.getResource();
    }

    /**
     * 设置过期时间
     * @author xiangze
     * @param key
     * @param seconds
     */
    public void expire(String key, int seconds) {
        if (seconds <= 0) {
            return;
        }
        Jedis jedis = getJedis();
        jedis.expire(key, seconds);
        jedis.close();
    }

    /**
     * 设置默认过期时间
     * @author xiangze
     * @param key
     */
    public void expire(String key) {
        expire(key, expire);
    }

    // *******************************************Keys******************************************* //
    public class Keys {
        /**
         * 清空所有key
         */
        public String flushAll() {
            Jedis jedis = getJedis();
            String state = jedis.flushAll();
            jedis.close();
            return state;
        }

        /**
         * 删除keys对应的记录,可以是多个key
         * @param String ... keys
         * @return 删除的记录数
         */
        public long del(String... keys) {
            Jedis jedis = getJedis();
            long count = jedis.del(keys);
            jedis.close();
            return count;
        }

        /**
         * 判断key是否存在
         * @param String key
         * @return boolean
         */
        public boolean exists(String key) {
            Jedis jedis = getJedis();
            boolean exist = jedis.exists(key);
            jedis.close();
            return exist;
        }

        /**
         * 查找所有匹配给定的模式的键
         * @param String  key的表达式, *表示多个, ?表示一个
         */
        public Set<String> keys(String pattern) {
            Jedis jedis = getJedis();
            Set<String> set = jedis.keys(pattern);
            jedis.close();
            return set;
        }
    }

    // *******************************************Strings*******************************************//
    public class Strings {
        /**
         * 根据key获取记录
         *
         * @param String key
         * @return value
         */
        public String get(String key) {
            Jedis jedis = getJedis();
            String value = jedis.get(key);
            jedis.close();
            return value;
        }

        /**
         * 添加记录,如果记录已存在将覆盖原有的value
         *
         * @param key
         * @param value
         * @return 状态码
         */
        public String set(String key, String value) {
            return set(SafeEncoder.encode(key), SafeEncoder.encode(value));
        }

        /**
         * 添加记录,如果记录已存在将覆盖原有的value
         *
         * @param key
         * @param value
         * @return 状态码
         */
        public String set(String key, byte[] value) {
            return set(SafeEncoder.encode(key), value);
        }

        /**
         * 添加记录,如果记录已存在将覆盖原有的value
         *
         * @param key
         * @param value
         * @return 状态码
         */
        public String set(byte[] key, byte[] value) {
            Jedis jedis = getJedis();
            String status = jedis.set(key, value);
            jedis.close();
            return status;
        }
    }
}
