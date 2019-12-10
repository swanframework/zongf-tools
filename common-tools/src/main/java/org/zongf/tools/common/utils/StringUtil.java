package org.zongf.tools.common.utils;


import org.zongf.tools.common.exception.TypeConversionException;

/** 字符串工具类
 * @author zongf
 * @date 2019-07-01
 */
public class StringUtil {


    /** 将某类型的字符串转换为其对应类型. 需要注意的是, 对于布尔类型, 除了true字符串以外, 其它均会被转换为false, 不会抛出异常.
     * @param value 字符
     * @param clz 字符
     * @return 目标类型的对象, int/float..等基本类型会转换为其对应的包装类型Integer/Float...
     * @throws TypeConversionException
     * @author zongf
     * @date 2019-07-01
     */
    public static <T> T convert( String value, Class<T> clz) {

        try {

            // 转换String类型
            if (clz.equals(String.class)) {
                return (T) value;
            }

            // 转换为Integer/int类型
            if (clz.equals(Integer.class) || clz.equals(int.class)) {
                return (T) Integer.valueOf(value);
            }

            // 转换为Double/double类型
            if (clz.equals(Double.class) || clz.equals(double.class)) {
                return (T) Double.valueOf(value);
            }

            // 转换为Float/float类型
            if (clz.equals(Float.class) || clz.equals(float.class)) {
                return (T) Float.valueOf(value);
            }

            // 转换为Boolean/boolean 类型
            if (clz.equals(Boolean.class) || clz.equals(boolean.class)) {
                return (T) Boolean.valueOf(value);
            }

            // 转换为Byte/byte 类型
            if (clz.equals(Byte.class) || clz.equals(byte.class)) {
                return (T) Byte.valueOf(value);
            }

            // 转换为Short/short类型
            if (clz.equals(Short.class) || clz.equals(short.class)) {
                return (T) Short.valueOf(value);
            }

            // 转换为Character/char 类型
            if (clz.equals(Character.class) || clz.equals(char.class)) {
                return (T) Character.valueOf(value.charAt(0));
            }

            return (T) value;
        } catch (Exception e) {
            throw new TypeConversionException("类型转换异常!", e);
        }

    }
}
