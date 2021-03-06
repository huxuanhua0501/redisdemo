package net.xuanhuahu.core.common.redis;

import com.alibaba.fastjson.JSON;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by win7 on 2017/5/11.
 */
@Component
public class RedisUtils {
    private static Logger logger = LoggerFactory.getLogger(RedisUtils.class);

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private RedisConstant redisConstant;

    /**
     * 存数据，并设定失效时间
     * @param cacheName
     * @param key
     * @param value
     */
    public void put(String cacheName, String key, String value) {
        if (cacheName == null || "".equals(cacheName) || key == null || "".equals(key)) {
            return;
        }
        //放入redis
        redisTemplate.opsForHash().put(cacheName, key, value);

        long expireTime = redisConstant.getExpireTime(cacheName);
        //如果不等于-1，则该cacheName配置有过期时间
        if (expireTime != -1) {
            redisTemplate.expire(cacheName, expireTime, TimeUnit.SECONDS);
        }
    }

    /**
     * 同上
     * @param cacheName
     * @param key
     * @param value
     */
    public void put(String cacheName, String key, Object value) {
        if (cacheName == null || "".equals(cacheName) || key == null || "".equals(key)) {
            return;
        }
        //放入redis
        redisTemplate.opsForHash().put(cacheName, key, JSON.toJSONString(value));
        long expireTime = redisConstant.getExpireTime(cacheName);
        //如果不等于-1，则该cacheName配置有过期时间
        if (expireTime != -1) {
            redisTemplate.expire(cacheName, expireTime, TimeUnit.SECONDS);
        }
    }
    /**
     * 取数据
     * @param cacheName
     * @param key
     * @param
     */
    public <T> T get(String cacheName, String key, Class<T> className) {
        Object obj = redisTemplate.opsForHash().get(cacheName, key);
        if (obj == null) {
            return null;
        }
        return JSON.parseObject("" + obj, className);
    }

    /**
     * 压栈,先进后出
     *
     * @param key
     * @param value
     * @return
     */
    public Long leftpush(String key, String value) {
        return redisTemplate.opsForList().leftPush(key, value);
    }
    /**
     * 出栈,最后压入 的先出
     *
     * @param key
     * @return
     */
    public String leftpop(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    /**
     * 入队，左边入队
     * @param key
     * @param value
     */
    public void rightPush(String key,String value){
        redisTemplate.opsForList().rightPush(key,value);
    }

    /**
     * 如果像这样也是从右边取，那么就是先进后出，反之则先进先出
     * @param key
     * @return
     */
    public String rightPop(String key) {
        return redisTemplate.opsForList().rightPop(key);
    }

    /**
     * queue 消息队列模式
     * @param channel
     * @param message
     */
    public void sendMessage(String channel, Object message) {
        redisTemplate.convertAndSend(channel,message);
    }
//    public boolean getByName(String cacheName) {
//        if (cacheName == null || "".equals(cacheName)) {
//            return false;
//        }
//        return redisTemplate.hasKey(cacheName);
//    }
//
//    public boolean getByKey(String cacheName, String key) {
//        if (cacheName == null || "".equals(cacheName) || key == null || "".equals(key)) {
//            return false;
//        }
//        return redisTemplate.opsForHash().hasKey(cacheName, key);
//    }
//
//    public <T> List<T> getAll(String cacheName, Class<T> className) {
//
//        if (cacheName == null || "".equals(cacheName)) {
//            return null;
//        }
//
//        Set<Object> keys = redisTemplate.opsForHash().keys(cacheName);
//
//        List<T> datas = new ArrayList<T>();
//
//        for (Object obj : keys) {
//            datas.add(get(cacheName, obj + "", className));
//        }
//        if (datas.size() == 0) {
//            return null;
//        }
//
//        return datas;
//    }
//
//    public void remove(String cacheName, String key) {
//        if (cacheName == null || "".equals(cacheName) || key == null || "".equals(key)) {
//            return;
//        }
//        redisTemplate.opsForHash().delete(cacheName, key);
//    }
//
//    public void remove(String cacheName, List<String> keys) {
//        if (cacheName == null || "".equals(cacheName) || keys == null || keys.size() == 0) {
//            return;
//        }
//        for (String key : keys) {
//            redisTemplate.opsForHash().delete(cacheName, key);
//        }
//    }
//
//    public void removeAll(String cacheName) {
//        if (cacheName == null || "".equals(cacheName)) {
//            return;
//        }
//        redisTemplate.delete(cacheName);
//
//    }
//
//    /**
//     * @param args
//     */
//    public static void main(String[] args) {
//        // TODO Auto-generated method stub
//
//    }
//
//    public String[] findAllName() {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    public List getValueByKey(String cacheName, String key) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    public Long getKeyList(String cacheName) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    public String onlyValue(String cacheName, String key) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    public List getAllKeyAndValue(String cacheName, int numPerPage, int pageNum, Long totalCount) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    public void lpush(String key, Object obj) {
//        // TODO Auto-generated method stub
//        if (key == null || "".equals(key)) {
//            return;
//        }
//        //放入redis
//        redisTemplate.opsForList().leftPush(key, JSON.toJSONString(obj));
//        long expireTime = redisConstant.getExpireTime("smsRecordCache");
//        //如果不等于-1，则该cacheName配置有过期时间
//        if (expireTime != -1) {
//            redisTemplate.expire(key, expireTime, TimeUnit.DAYS);
//        }
//    }
//
//    public <T> List<T> lpop(String key, Class<T> className) {
//        // TODO Auto-generated method stub
//        List<T> datas = new ArrayList<T>();
//        if (key == null || "".equals(key)) {
//            return datas;
//        }
//        List<String> list = redisTemplate.opsForList().range(key, 0, -1);
//        for (String str : list) {
//            datas.add(JSON.parseObject(str, className));
//        }
//        if (datas.size() == 0) {
//            return datas;
//        }
//
//        return datas;
//    }
}
