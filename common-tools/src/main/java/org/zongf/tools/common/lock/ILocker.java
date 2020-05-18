package org.zongf.tools.common.lock;

/** 应用锁
 * @author zongf
 * @date 2020-05-15
 */
public interface ILocker {

    /** 获取锁
     * @param lockName 锁名称
     * @param lockTime 锁定时间,超时后自动解锁. 单位毫秒
     * @return boolean
     * @author zongf
     * @date 2020-05-15
     */
    default public boolean lock(String lockName, long lockTime){
        throw new UnsupportedOperationException();
    };

    /** 释放锁
     * @param lockName 锁名称
     * @return boolean
     * @author zongf
     * @date 2020-05-15
     */
    default public boolean unlock(String lockName){
        throw new UnsupportedOperationException();
    };

    /** 判断是否加锁
     * @param lockName 锁名称
     * @return boolean
     * @author zongf
     * @date 2020-05-15
     */
    default public boolean isLock(String lockName){
        throw new UnsupportedOperationException();
    }

    /** 查询锁剩余时间
     * @param lockName 锁名称
     * @return long 时间毫秒
     * @author zongf
     * @date 2020-05-15
     */
    default public long leftTime(String lockName){
        throw new UnsupportedOperationException();
    }

}
