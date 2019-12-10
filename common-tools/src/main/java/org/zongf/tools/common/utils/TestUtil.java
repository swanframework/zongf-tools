package org.zongf.tools.common.utils;

import java.util.Collection;
import java.util.Map;

/**验证工具类
 * @author zongf
 * @date 2019-07-01
 */
public class TestUtil {

    /**判断对象是否为null
     * @param object 任意类型对象
     * @return 对象为null 返回true, 否则返回false
     * @author zongf
     * @date 2019-07-01
     */
    public static boolean isNull(Object object) {
        return object == null ? true : false;
    }

    /**判断字符串是否为空. null和纯空格均视为空
     * @param str 字符串
     * @return 字符串为null或纯空格 返回true, 否则返回false
     * @author zongf
     * @date 2019-07-01
     */
    public static boolean isEmpty(String str) {
        if(str == null || "".equals(str.trim())){
            return true;
        }
        return false;
    }

    /**判断集合是否为空. null和size为0均视为空
     * @param collection 集合或其子类型, 如:collection, list, set等
     * @return 集合为null或集合元素个数为0 返回true, 否则返回false
     * @author zongf
     * @date 2019-07-01
     */
    public static boolean isEmpty(Collection collection) {
        if (collection == null || collection.size() == 0) {
            return true;
        }
        return false;
    }

    /**判断map是否为空. null和size为0均视为空
     * @param map 类型
     * @return map为null或map的size为0 返回true, 否则返回false.
     * @author zongf
     * @date 2019-07-01
     */
    public static boolean isEmpty(Map map) {
        if (map == null || map.size() == 0) {
            return true;
        }
        return false;
    }

    /**判断数组是否为空. null和length为0均视为空
     * @param array 任意类型数组
     * @return 对象为null或者长度为0返回true, 否则返回false.
     * @author zongf
     * @date 2019-07-01
     */
    public static boolean isEmpty(Object[] array) {
        if (array == null || array.length == 0) {
            return true;
        }
        return false;
    }

    //****************************************** 反义校验 **********************************************************

    /**判断对象不为null
     * @param object 任意类型对象
     * @return 对象为null 返回false, 否则返回true
     * @author zongf
     * @date 2019-07-01
     */
    public static boolean isNotNull(Object object) {
        return !isNotNull(object);
    }

    /**判断字符串是否非空. null和纯空格均视为空
     * @param str 字符串
     * @return 字符串为null或为纯空格返回fasle, 否则返回true
     * @author zongf
     * @date 2019-07-01
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**判断集合是否非空. null和size为0均视为空
     * @param collection 集合或其子类型, 如:collection, list, set等
     * @return 集合为null或集合的size为0返回false, 否则返回true
     * @author zongf
     * @date 2019-07-01
     */
    public static boolean isNotEmpty(Collection collection) {
        return !isEmpty(collection);
    }

    /**判断map是否非空. null和size为0均视为空
     * @param map 类型
     * @return map为null或map的size为0返回false, 否则返回true
     * @author zongf
     * @date 2019-07-01
     */
    public static boolean isNotEmpty(Map map) {
        return !isEmpty(map);
    }

    /**判断数组是否非空. null和length为0均视为空
     * @param array 任意类型数组
     * @return 数组为null或数组长度为0返回false, 否则返回true
     * @author zongf
     * @date 2019-07-01
     */
    public static boolean isNotEmpty(Object[] array) {
        return !isEmpty(array);
    }
}
