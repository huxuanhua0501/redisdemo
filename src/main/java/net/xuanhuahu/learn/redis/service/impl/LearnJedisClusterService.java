package net.xuanhuahu.learn.redis.service.impl;

import net.xuanhuahu.learn.redis.service.ILearnJedisClusterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

/**
 * Created by win7 on 2017/5/24.
 */
@Service
public class LearnJedisClusterService implements ILearnJedisClusterService{
    @Autowired
    private JedisCluster jedisCluster;
    @Override
    public <T> void putCache(String key, T obj) {

        jedisCluster.set(key, (String) obj);
    }

    @Override
    public <T> String getValue(String key) {
        return jedisCluster.get(key);
    }
}
