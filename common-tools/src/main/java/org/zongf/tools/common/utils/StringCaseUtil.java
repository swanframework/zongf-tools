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

}
