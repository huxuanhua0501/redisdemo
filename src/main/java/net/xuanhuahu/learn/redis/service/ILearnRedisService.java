package net.xuanhuahu.learn.redis.service;

import java.util.Map;

/**
 * Created by win7 on 2017/5/12.
 */
public interface ILearnRedisService {
    public void insertRedis();

    public Map getRedis();
    public  void leftPush(String text);
    public String leftPop();
    public  void  rightPush(String text);
    public  String rigthPop();
    public void sendMessage(String channel, Object message);
}
