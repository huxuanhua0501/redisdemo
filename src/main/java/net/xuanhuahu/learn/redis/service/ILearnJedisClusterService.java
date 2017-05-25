package net.xuanhuahu.learn.redis.service;



/**
 * Created by win7 on 2017/5/24.
 */
public interface ILearnJedisClusterService {

    public <T> void putCache(String key, T obj);
    public <T> String  getValue(String key);

}
