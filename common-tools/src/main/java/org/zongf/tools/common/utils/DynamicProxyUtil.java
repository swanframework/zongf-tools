package org.zongf.tools.common.utils;



import org.zongf.tools.common.handler.BaseInvocationHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/** 动态代理工具类
 * @since 1.0
 * @author zongf
 * @created 2019-06-28
 */
public class DynamicProxyUtil {

    /** 获取动态代理对象
     * @param target 动态代理目标对象
     * @param invocationHandler 动态代理处理器
     * @return T 可返回目标对象实现的任意接口类型对象
     * @since 1.0
     * @author zongf
     * @created 2019-06-28
     */
    public static <T> T getProxy(Object target, InvocationHandler invocationHandler) {
        Class<?> clz = target.getClass();
        Class<?>[] allInterfaces = getAllInterfaces(clz);
        return (T) Proxy.newProxyInstance(clz.getClassLoader(), allInterfaces, invocationHandler);
    }

    /** 获取动态代理对象
     * @param baseInvocationHandler 动态代理处理器
     * @return T 可返回目标对象实现的任意接口类型对象
     * @since 1.0
     * @author zongf
     * @created 2019-06-28
     */
    public static <T> T getProxy(BaseInvocationHandler baseInvocationHandler) {
        Class<?> clz = baseInvocationHandler.getTarget().getClass();
        Class<?>[] allInterfaces = getAllInterfaces(clz);
        return (T) Proxy.newProxyInstance(clz.getClassLoader(), allInterfaces, baseInvocationHandler);
    }

    /** 获取类所实现的所有接口, 包含当父类实现的接口
     * @param clz 类型
     * @return 当前类以及父类所实现的所有接口
     * @since 1.0
     * @author zongf
     * @created 2019-06-28
     */
    private static Class<?>[] getAllInterfaces(Class<?> clz) {
        // 获取当前类所实现的所有接口
        Class<?>[] interfaces = clz.getInterfaces();

        // 获取父类所实现的所有接口
        Class<?>[] supperInterfaces = clz.getSuperclass().getInterfaces();

        // 合并所实现的所有接口
        Class<?>[] allInterfaces = new Class<?>[interfaces.length + supperInterfaces.length];

        for(int i=0; i<interfaces.length; i++){
            allInterfaces[i] = interfaces[i];
        }
        for(int i=0; i<supperInterfaces.length; i++){
            allInterfaces[interfaces.length + i] = interfaces[i];
        }
        return allInterfaces;
    }

}
