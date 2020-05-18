package org.zongf.test.tools.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zongf.tools.spring.lock.RedisLocker;

/** Redis 配置
 * @author zongf
 * @date 2020-05-16
 */
@Configuration
public class RedisConfig {

    // 目前springboot不能扫描jar包中的组件,因此需要手工注入
    @Bean
    public RedisLocker redisLocker(){
        return new RedisLocker();
    }

}
