package org.zongf.tools.spring.lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/** Redis 分布式锁
 * @author zongf
 * @date 2020-05-16
 */
@Service
public class RedisLocker implements ILocker {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public boolean lock(String lockName, long lockTime) {
        String createTime = String.valueOf(System.currentTimeMillis());
        return redisTemplate.opsForValue().setIfAbsent(lockName, createTime, lockTime, TimeUnit.SECONDS);
    }

    @Override
    public boolean unlock(String lockName) {
        return redisTemplate.delete(lockName);
    }

    @Override
    public boolean isLock(String lockName) {
        return redisTemplate.opsForValue().get(lockName) != null;
    }

    @Override
    public long leftTime(String lockName) {
        return redisTemplate.getExpire(lockName);
    }
}
