package org.zongf.tools.spring.utils;

import org.slf4j.Logger;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.zongf.tools.log.util.LogUtil;
import org.zongf.tools.spring.excetion.TxException;

import java.util.function.Supplier;

/** 手工操作事务工具类
 * @author zongf
 * @date 2020-06-28
 */
public class TranscationUtil {

    private static Logger log = LogUtil.getLogger(TranscationUtil.class);

    private static final PlatformTransactionManager transactionManager;

    static {
        transactionManager = SpringContextUtil.getBean(PlatformTransactionManager.class);
    }

    /** 执行事务, 默认传播机制: 开启新的事务
     * @param supplier
     * @return T
     * @author zongf
     * @date 2020-06-28
     */
    public static  <T> T execute(Supplier<T> supplier) {
        return execute(supplier,TransactionDefinition.PROPAGATION_REQUIRES_NEW);
    }

    /** 执行事务
     * @param supplier
     * @param propagation 事务传播行为
     * @return T
     * @author zongf
     * @date 2020-06-28
     */
    public static <T> T execute(Supplier<T> supplier, int propagation) {
        DefaultTransactionDefinition txDefinition = new DefaultTransactionDefinition();
        txDefinition.setPropagationBehavior(propagation); // 设置事务传播行为: TransactionDefinition.PROPAGATION_REQUIRES_NEW
        // 开启事务
        TransactionStatus status = transactionManager.getTransaction(txDefinition);

        try {
            // 执行目标方法
            T result = supplier.get();

            // 提交事务
            log.info("准备提交事务...");
            transactionManager.commit(status);

            // 返回结果
            return result;
        } catch (Exception e) {
            log.error("事务执行异常, 准备回滚事务...", e);
            try {
                transactionManager.rollback(status);
                log.error("事务回滚-成功!", e);
            } catch (Exception ex) {
                log.error("回滚事务-异常!");
            }
            throw new TxException("事务执行异常");
        }
    }
}
