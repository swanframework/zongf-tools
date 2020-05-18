package org.zongf.tools.http.utils;

import org.zongf.tools.common.utils.ReflectUtil;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/** 表单式参数工具类
 * @author zongf
 * @date 2020-05-18
 */
public class FormParamUtil {

    /** 将对象转换为key=val&key=val... 格式字符串, 利用getter方法
     * @param object
     * @return String
     * @author zongf
     * @date 2020-05-18
     */
    public static String toString(Object object){

        // 如果对象为null, 则返回null
        if(object == null) return null;
        StringBuffer sb = new StringBuffer();

        // 获取所有getter方法
        List<Method> getterMethods = ReflectUtil.getGetterMethods(object.getClass());

        for (Method getterMethod : getterMethods) {
            // 解析方法对应的属性名
            String fieldName = ReflectUtil.parseFieldNameAsGetterMethod(getterMethod);
            // 调用方法, 获取属性值
            Object value = ReflectUtil.invokeMethod(getterMethod, object);
            // 拼接参数
            sb.append(fieldName).append("=").append(value).append("&");
        }
        // 处理最后的&
        if (sb.length() > 1) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }


    /** 将map转换为form请求串
     * @param paramMap 参数
     * @return String
     * @author zongf
     * @date 2020-05-18
     */
    public static String toString(Map paramMap) {
        // 如果map为空, 则返回null
        if(paramMap == null || paramMap.isEmpty()) return null;

        // 拼接参数
        StringBuffer sb = new StringBuffer();
        paramMap.forEach((key, val) -> sb.append(key).append("=").append(val).append("&"));
        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }

}
