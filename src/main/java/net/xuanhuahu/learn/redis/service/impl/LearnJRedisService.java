package net.xuanhuahu.learn.redis.service.impl;

import net.xuanhuahu.learn.redis.service.ILearnJRedisService;
import org.springframework.stereotype.Service;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import javax.annotation.Resource;

/**
 * Created by win7 on 2017/5/22.
 */
@Service
public class LearnJRedisService implements ILearnJRedisService {
    @Resource
    private ShardedJedisPool shardedJedisPool;

    /**
     * 添加到Set中（同时设置过期时间）
     * <p>
     * 过期时间 单位s
     *
     * @param
     * @return 成功true
     */
    @Override
    public void put() {
        ShardedJedis jedis = null;
        try {
            jedis = shardedJedisPool.getResource();
            jedis.sadd("tt", "tt123");
            jedis.expire("tt", 30);
        } finally {
            jedis.close();
        }

    }

    /**
     * 获取Set
     *
     * @param
     * @return
     */
    @Override
    public void get() {
        ShardedJedis jedis = null;
        try {
            jedis = shardedJedisPool.getResource();
            System.err.println(jedis.smembers("tt"));
        } finally {
            jedis.close();
        }

    }

    /**
     * 从set中删除value
     *
     * @param key
     * @return
     */
    public boolean removeSetValue(String key, String... value) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            shardedJedis.srem(key, value);
            return true;
        } finally {
            shardedJedis.close();
        }
    }

    /**
     * 设置HashSet对象
     *
     * @param domain 域名
     * @param key    键值
     * @param value  Json String or String value
     * @return
     */
    public boolean setHSet(String domain, String key, String value) {
        if (value == null)
            return false;
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            shardedJedis.hset(domain, key, value);
            return true;
        } finally {
            shardedJedis.close();
        }
    }

    /**
     * 获得HashSet对象
     *
     * @param domain 域名
     * @param key    键值
     * @return Json String or String value
     */
    public String getHSet(String domain, String key) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            System.err.println(shardedJedis.hget(domain, key));
            return shardedJedis.hget(domain, key);
        } finally {
            shardedJedis.close();
        }
    }

    /**
     * 删除HashSet对象
     *
     * @param domain 域名
     * @param key    键值
     * @return 删除的记录数
     */
    @Override
    public long delHSet(String domain, String key) {
        ShardedJedis shardedJedis = null;
        long count = 0;
        try {
            shardedJedis = shardedJedisPool.getResource();
            count = shardedJedis.hdel(domain, key);
        } finally {
            shardedJedis.close();
        }
        return count;
    }

    /**
     * 删除HashSet对象
     *
     * @param domain 域名
     * @param key    键值
     * @return 删除的记录数
     */
    public long delHSet(String domain, String... key) {
        ShardedJedis shardedJedis = null;
        long count = 0;
        try {
            shardedJedis = shardedJedisPool.getResource();
            shardedJedis.hdel(domain, key);
        } finally {
            shardedJedis.close();
        }
        return count;
    }

    /**
     * 左栈入
     */
    public void lpush(String value){
       ShardedJedis shardedJedis = null;
       try{
           shardedJedis = shardedJedisPool.getResource();
           shardedJedis.lpush("ko",value);
       }finally {
           shardedJedis.close();
       }
    }

    /**
     * 右栈出
     */
    public  void rpop(){
        ShardedJedis shardedJedis = null;
        try{
            shardedJedis = shardedJedisPool.getResource();
            System.err.println(shardedJedis.rpop("ko"));
        }finally {
            shardedJedis.close();
        }
    }

}
