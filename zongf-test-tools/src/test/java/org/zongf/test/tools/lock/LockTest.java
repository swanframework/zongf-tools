package org.zongf.test.tools.lock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zongf.tools.common.lock.ILocker;
import org.zongf.tools.common.utils.LockUtil;
import org.zongf.tools.spring.utils.SpringContextUtil;

import java.time.LocalDateTime;

/** 分布式锁工具测试类
 * @author zongf
 * @date 2020-05-15
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LockTest {

    @Autowired
    private ILocker locker;

    @Test
    public void test() throws Exception{

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                String name = LockUtil.execute(this::sayHello, "zhangsan", locker, "order101", 30);
            }).start();
        }

        Thread.sleep(3000);
    }

    private String sayHello(String name){
        System.out.println(Thread.currentThread().getName() + ":" + LocalDateTime.now().toString());
        return name;
    }

}
