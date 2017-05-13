package net.xuanhuahu.learn.redis.service.impl;

import net.xuanhuahu.core.common.redis.RedisUtils;
import net.xuanhuahu.learn.redis.service.ILearnRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by win7 on 2017/5/12.
 */
@Service
public class LearnRedisService implements ILearnRedisService {
    @Autowired
    private RedisUtils redisUtils;
    @Override
    public void insertRedis() {
        Map<String, Object> map = new HashMap<>();
        map.put("jack", "妹妹");
        map.put("tom", "哥哥");
        redisUtils.put("reidstable","key_01", map);
    }

    @Override
    public Map getRedis() {
        return redisUtils.get("reidstable","key_01",Map.class);
    }

    @Override
    public void leftPush(String text) {
        redisUtils.leftpush("go_01", text);
    }

    @Override
    public String leftPop() {
        return redisUtils.leftpop("go_01");
    }

    @Override
    public void rightPush(String text) {
        redisUtils.rightPush("go_02",text);
    }

    @Override
    public String rigthPop() {
        return redisUtils.rightPop("go_02");
    }
}
