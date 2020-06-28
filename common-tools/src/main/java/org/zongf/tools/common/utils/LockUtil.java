package org.zongf.tools.common.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zongf.tools.common.exception.AbsBaseException;
import org.zongf.tools.common.exception.LockException;
import org.zongf.tools.common.lock.ILocker;

import java.util.function.Consumer;
import java.util.function.Function;

/** 应用锁工具类
 * @author zongf
 * @date 2020-05-15
 */
public class LockUtil {

    private static Logger log = LoggerFactory.getLogger(LockUtil.class);

    /** 加锁执行单参有返回值方法
     * @param function 目标方法
     * @param param 目标方法参数
     * @param locker 锁
     * @param lockName 锁名称
     * @param lockTime 锁定时间, 过期自动释放锁
     * @return R
     * @author zongf
     * @date 2020-05-15
     */
    public static <T, R> R execute(Function<T, R> function, T param, ILocker locker, String lockName, long lockTime) {

        try {

            boolean lockSuccess = locker.lock(lockName, lockTime);

            if (lockSuccess) {
                return function.apply(param);
            }else {
                throw new LockException("获取锁失败");
            }
        } catch (AbsBaseException bex) {
            throw bex;
        } catch (Exception ex) {
            throw new LockException("加锁执行异常");
        } finally {
            try {
                locker.unlock(lockName);
            } catch (Exception ex) {
                log.warn("释放锁失败, 锁名称:" + lockName);
            }
        }
    }

    /** 执行执行单参无返回值方法
     * @param consumer 目标方法
     * @param param 目标方法参数
     * @param locker 锁
     * @param lockName 锁名称
     * @param lockTime 锁定时间, 过期自动释放锁
     * @return R
     * @author zongf
     * @date 2020-05-15
     */
    public static <T> void execute(Consumer<T> consumer, T param, ILocker locker, String lockName, long lockTime) {

        try {

            boolean lockSuccess = locker.lock(lockName, lockTime);

            if (lockSuccess) {
                consumer.accept(param);
            }else {
                throw new LockException("获取锁失败");
            }
        } catch (AbsBaseException bex) {
            throw bex;
        } catch (Exception ex) {
            throw new LockException("加锁执行异常");
        } finally {
            try {
                locker.unlock(lockName);
            } catch (Exception ex) {
                log.warn("释放锁失败, 锁名称:" + lockName);
            }
        }
    }

}
