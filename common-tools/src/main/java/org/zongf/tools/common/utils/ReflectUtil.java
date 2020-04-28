package org.zongf.tools.common.utils;


import org.zongf.tools.common.exception.ReflectException;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/** 反射工具类
 * @author zongf
 * @date 2019-06-12
 */
public class ReflectUtil {

    /**为对象属性赋值, 通过属性setter方法.
     * @param target 目标对象
     * @param property 属性名
     * @param value 属性值, 如果属性类型非基本类型, 则value类型必须和属性类型一致
     * @throws Exception 属性没有set方法或set方法不可访问, 抛出异常
     * @author zongf
     * @date 2019-06-12
     */
    public static void setValueByWriteMethod(Object target, String property, Object value) {
        try {
            PropertyDescriptor descriptor = new PropertyDescriptor(property, target.getClass());
            Method writeMethod = descriptor.getWriteMethod();
            // 如果类型不一致, 进行类型转换.
            if (!descriptor.getPropertyType().equals(value.getClass())) {
                value = StringUtil.convert(value.toString(), descriptor.getPropertyType());
            }
            writeMethod.invoke(target, value);
        } catch (Exception e) {
            throw new ReflectException(e, "对象属性赋值异常!");
        }
    }

    /**获取对象属性值, 通过属性getter方法.
     * @param target 目标对象
     * @param property 属性
     * @return T 返回属性值
     * @throws Exception 属性没有getter方法, 或getter方法不可访问时,抛出异常
     * @author zongf
     * @date 2019-06-12
     */
    public static <T> T getValueByReadMethod(Object target, String property) {
        try {
            PropertyDescriptor descriptor = new PropertyDescriptor(property, target.getClass());
            Method readMethod = descriptor.getReadMethod();
            return (T) readMethod.invoke(target);
        } catch (Exception e) {
            throw new ReflectException(e, "获取对象属性值异常");
        }
    }

    /**为对象属性赋值, 通过属性直接赋值
     * @param target 目标对象
     * @param property 属性名
     * @param value 属性值, 如果属性类型非基本类型, 则value类型必须和属性类型一致
     * @author zongf
     * @date 2019-06-12
     */
    public static void setValueByField(Object target, String property, Object value) {

        Field declaredField = null;

        // 是否修改了Accessible 属性
        boolean changeAccessible = false;

        try {
            declaredField = target.getClass().getDeclaredField(property);

            // 属性不可访问, 则设置可访问
            if (!declaredField.isAccessible()) {
                declaredField.setAccessible(true);
            }

            // 如果类型不一致, 进行类型转换.
            if (!declaredField.getType().equals(value.getClass())) {
                value = StringUtil.convert(value.toString(), declaredField.getType());
            }
            declaredField.set(target, value);
        } catch (Exception e) {
            throw new RuntimeException("对象属性赋值异常!",e);
        } finally {
            // 如果字段不为空, 且修改了访问属性, 则回滚访问属性
            if (declaredField != null && changeAccessible) {
                declaredField.setAccessible(false);
            }
        }
    }

    /**获取对象属性值, 通过属性直接获取
     * @param target 目标对象
     * @param property 属性名
     * @return: T 返回属性值
     * @author zongf
     * @date 2019-06-12
     */
    public static <T> T getValueByField(Object target, String property) {

        Field declaredField = null;

        // 是否修改了Accessible 属性
        boolean changeAccessible = false;
        
        try {
            declaredField = target.getClass().getDeclaredField(property);

            // 属性不可访问, 则设置可访问
            if (!declaredField.isAccessible()) {
                declaredField.setAccessible(true);
            }

            return (T) declaredField.get(target);
        } catch (Exception e) {
            throw new ReflectException(e, "获取对象属性值异常!");
        } finally {
            // 如果字段不为空, 且修改了访问属性, 则回滚访问属性
            if (declaredField != null && changeAccessible) {
                declaredField.setAccessible(false);
            }
        }
    }

}
