# redis的springmvc的整合复习

# 主要会整合redis的主流用法

spring-data-redis实现的缓存模式
   *  完成redis的存取，默认存在3号库,设置了redis的失效时间
   *  添加进栈和出栈的部分，RedisUtils中，在一些特殊的环境中可以应用
   *  队列，但是貌似只发现topic这种广播订阅模式，接下会看看有没有生产和消费模式
   * 集群模式这里就不在列出来了。大家百度一下也可以找到
   
jredis的研究代码，我会在代码中加标示，加以识别

   * shardedJedisPool切片源的配置
   
   * 实现类似spring-data-redis的功能
   
   * 有集群的配置，但是本地没有集群，因此无法保证是否可行
   
# 共同学习-xuanhua.hu-胡宣化-qq-1084773661

# 越学越小白