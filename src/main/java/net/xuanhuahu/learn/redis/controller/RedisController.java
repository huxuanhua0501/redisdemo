package net.xuanhuahu.learn.redis.controller;

import com.alibaba.fastjson.JSON;
import net.xuanhuahu.learn.redis.service.ILearnRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by win7 on 2017/5/12.
 */
@RestController
@RequestMapping(value = "/redis")
public class RedisController {
    @Autowired
    private ILearnRedisService learnRedisService;

    @GetMapping("/insertKey")
    public String insertKey() {
        learnRedisService.insertRedis();
//        Map<String, Object> map = learnRedisService.getRedis();
//        System.err.println(map);
        return null;
    }

    @GetMapping("/getValue")
    public String getValue() {
        Map<String, Object> map = learnRedisService.getRedis();
        return JSON.toJSONString(map);
    }

    @GetMapping("leftpush")
    public void leftpush(String text) {
        learnRedisService.leftPush(text);
    }

    @GetMapping("leftpop")
    public String leftpop() {
        return learnRedisService.leftPop();
    }

    @GetMapping("rightPush")
    public void rightPush(String text) {
        learnRedisService.rightPush(text);
    }

    @GetMapping("rightPop")
    public String rightPop() {
        return learnRedisService.rigthPop();
    }
}
