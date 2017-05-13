package net.xuanhuahu.learn.redis.queue;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

import java.io.UnsupportedEncodingException;

/**
 * Created by win7 on 2017/5/13.
 */
public class RedisQueueListener implements MessageListener {
    @Override
    public void onMessage(Message message, byte[] bytes) {
        try {
            System.out.println("channel:" + new String(message.getChannel(),"utf-8")
                    + ",message:" + new String(message.getBody(), "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
