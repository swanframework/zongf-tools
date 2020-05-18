package org.zongf.tools.common.utils;


import org.zongf.tools.common.exception.ReflectException;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

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

    /** 获取类所有声明的属性
     * @param clz
     * @param includeParents 是否递归父类
     * @return List<Field>
     * @author zongf
     * @date 2020-05-18
     */
    public static List<Field> getDeclaredFields(Class clz, boolean includeParents) {
        LinkedHashSet<Field> fields = new LinkedHashSet();

        // 如果父类不是Object类, 则递归获取父类
        if (includeParents && !Object.class.equals(clz.getSuperclass())) {
            fields.addAll(getDeclaredFields(clz.getSuperclass(), includeParents));
        }

        // 获取当前类声明的属性
        for (Field declaredField : clz.getDeclaredFields()) {
            fields.add(declaredField);
        }

        return new ArrayList<>(fields);
    }

    /** 获取类所有声明的属性名
     * @param clz 类
     * @param searchParents 是否递归父类
     * @return List<String>
     * @author zongf
     * @date 2020-05-18
     */
    public static List<String> getDeclaredFieldNames(Class clz, boolean searchParents){
        List<Field> declaredFields = getDeclaredFields(clz, searchParents);
        return declaredFields.stream().map(field -> field.getName()).collect(Collectors.toList());
    }

    /** 假定此方法为setter方法, 解析该方法对应的属性名
     * @param getMethod 方法名
     * @return String 如果不以get/is开头, 则返回null
     * @author zongf
     * @date 2020-05-18
     */
    public static String parseFieldNameAsGetterMethod(Method getMethod) {
        String methodName = getMethod.getName();
        String fieldName = null;

        if (boolean.class.equals(getMethod.getReturnType())) {
            if ("is".equals(methodName.substring(0, 2))) {
                fieldName = methodName.substring(2);
            }
        }else {
            if ("get".equals(methodName.substring(0, 3))) {
                fieldName = methodName.substring(3);
            }
        }

        if (fieldName == null || StringCaseUtil.isUpperCase(fieldName.charAt(1))) {
            return fieldName;
        }else {
            return StringCaseUtil.firstLower(fieldName);
        }
    }

    /** 假定此方法为setter方法, 解析该方法对应的属性名
     * @param setMethod 方法名
     * @return String
     * @author zongf
     * @date 2020-05-18
     */
    public static String parseFieldNameAsSetterMethod(Method setMethod) {
        String methodName = setMethod.getName();
        String fieldName = null;

        if ("set".equals(methodName.substring(0, 3))) {
            fieldName = methodName.substring(3);
        }
        if (fieldName == null || StringCaseUtil.isUpperCase(fieldName.charAt(1))) {
            return fieldName;
        }else {
            return StringCaseUtil.firstLower(fieldName);
        }
    }

    /** 获取类可访问(public)的所有getter方法
     * @param clz
     * @return List<Method>
     * @author zongf
     * @date 2020-05-18
     */
    public static List<Method> getGetterMethods(Class clz) {

        // 获取所有可访问的公用方法
        List<Method> methods = Arrays.asList(clz.getMethods());

        // 获取所有声明的属性名
        List<String> declaredFieldNames = getDeclaredFieldNames(clz, true);

        // 过滤Objec.class 中声明的方法
        methods = methods.stream()
                .filter(method -> !Object.class.equals(method.getDeclaringClass()))
                .filter(method -> !Modifier.isStatic(method.getModifiers()))
                .filter(method -> method.getParameters().length == 0)
                .filter(method -> declaredFieldNames.contains(parseFieldNameAsGetterMethod(method)))
                .collect(Collectors.toList());

        return methods;
    }

    /** 获取类可访问(public)的所有getter方法
     * @param clz
     * @return List<Method>
     * @author zongf
     * @date 2020-05-18
     */
    public static List<Method> getSetterMethods(Class clz) {

        // 获取所有可访问的公用方法
        List<Method> methods = Arrays.asList(clz.getMethods());

        // 获取所有声明的属性名
        List<String> declaredFieldNames = getDeclaredFieldNames(clz, true);

        // 过滤Objec.class 中声明的方法
        methods = methods.stream()
                .filter(method -> !Object.class.equals(method.getDeclaringClass()))
                .filter(method -> !Modifier.isStatic(method.getModifiers()))
                .filter(method -> method.getParameters().length == 1)
                .filter(method -> declaredFieldNames.contains(parseFieldNameAsSetterMethod(method)))
                .collect(Collectors.toList());

        return methods;
    }

    /** 调用方法
     * @param method 方法名
     * @param object 实例
     * @param args 方法参数
     * @return Object
     * @author zongf
     * @date 2020-05-18
     */
    public static Object invokeMethod(Method method, Object object, Object... args) {
        try {
           return method.invoke(object, args);
        } catch (Exception e) {
            throw new ReflectException("调用方法异常, 方法名:{}", method.getName());
        }
    }

}
