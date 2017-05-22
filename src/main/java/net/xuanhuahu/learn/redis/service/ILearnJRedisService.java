package net.xuanhuahu.learn.redis.service;

/**
 * Created by win7 on 2017/5/22.
 */
public interface ILearnJRedisService {
    public void put();

    public void get();

    public boolean removeSetValue(String key, String... value);

    public boolean setHSet(String domain, String key, String value);

    public String getHSet(String domain, String key);

    public long delHSet(String domain, String key);

    public long delHSet(String domain, String... key);

    public void lpush(String value);

    public void rpop();
}
