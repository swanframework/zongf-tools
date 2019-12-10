package org.zongf.tools.common.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/** 动态代理基类
 * @author zongf
 * @date 2019-06-28
 */
public class BaseInvocationHandler implements InvocationHandler {

    // 需要被代理的对象
    private Object target;

    public BaseInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(target, args);
    }

    public Object getTarget() {
        return target;
    }


}
