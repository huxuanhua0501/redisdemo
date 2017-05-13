package net.xuanhuahu.learn.redis.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * Created by win7 on 2017/5/13.
 */
public class SendMessage {
    @Autowired
    private StringRedisTemplate redisTemplate;
    public void sendMessage(String channel, Object message) {
        redisTemplate.convertAndSend(channel,message);
    }
}
