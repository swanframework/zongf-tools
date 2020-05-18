package org.zongf.tools.common.utils;

/** 字符串大小写工具类
 * @author zongf
 * @date 2020-05-18
 */
public class StringCaseUtil {

    /** 首字母大写
     * @param str
     * @return String
     * @author zongf
     * @date 2020-05-18
     */
    public static String firstUpper(String str){
        if(str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /** 首字母小写
     * @param str
     * @return String
     * @author zongf
     * @date 2020-05-18
     */
    public static String firstLower(String str){
        if(str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    /** 判断字符是否是大写字母
     * @param c 字符
     * @return boolean
     * @author zongf
     * @date 2020-05-18
     */
    public static boolean isUpperCase(char c) {
        return Character.isUpperCase(c);
    }

    /** 判断字符是否是小写字母
     * @param c 字符
     * @return boolean
     * @author zongf
     * @date 2020-05-18
     */
    public static boolean isLowerCase(char c) {
        return Character.isLowerCase(c);
    }

    /** 判断字符是否是数字
     * @param c 字符
     * @return boolean
     * @author zongf
     * @date 2020-05-18
     */
    public static boolean isDigit(char c) {
        return Character.isDigit(c);
    }

}
