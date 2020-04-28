package org.zongf.tools.http.utils;

import com.alibaba.fastjson.TypeReference;
import org.zongf.tools.http.config.HttpConfig;

import java.util.Map;

/** Http Get 请求类
 * @author zongf
 * @date 2020-04-28
 */
public class HttpGetUtil {

    /**@see HttpGetUtil#get(java.lang.String, java.util.Map, java.util.Map, java.lang.Class, org.zongf.tools.http.config.HttpConfig)
     * @return T
     * @author zongf
     * @date 2020-04-28
     */
    public static <T> T get(String url, Map<String, Object> params, TypeReference<T> resultType) {
        return get(url, null, params, resultType, null);
    }

    /**@see HttpGetUtil#get(java.lang.String, java.util.Map, java.util.Map, java.lang.Class, org.zongf.tools.http.config.HttpConfig)
     * @return T
     * @author zongf
     * @date 2020-04-28
     */
    public static <T> T get(String url, Map<String, Object> params, TypeReference<T> resultType, HttpConfig config) {
        return get(url, null, params, resultType, config);
    }

    /**@see HttpGetUtil#get(java.lang.String, java.util.Map, java.util.Map, java.lang.Class, org.zongf.tools.http.config.HttpConfig)
     * @return T
     * @author zongf
     * @date 2020-04-28
     */
    public static <T> T get(String url, Map<String, String> headers, Map<String, Object> params, TypeReference<T> resultType, HttpConfig config) {
        String response = HttpUtil.postForm(url, headers, params, config);
        return HttpUtil.parseResponse(response, resultType);
    }

    /**@see HttpGetUtil#get(java.lang.String, java.util.Map, java.util.Map, java.lang.Class, org.zongf.tools.http.config.HttpConfig)
     * @return T
     * @author zongf
     * @date 2020-04-28
     */
    public static <T> T get(String url, Map<String, Object> params, Class<T> resultType) {
        return get(url, null, params, resultType, null);
    }

    /**@see HttpGetUtil#get(java.lang.String, java.util.Map, java.util.Map, java.lang.Class, org.zongf.tools.http.config.HttpConfig)
     * @return T
     * @author zongf
     * @date 2020-04-28
     */
    public static <T> T get(String url, Map<String, Object> params, Class<T> resultType, HttpConfig config) {
        return get(url, null, params, resultType, config);
    }

    /** 发送get请求
     * @param url 请求地址
     * @param headers 请求头
     * @param params 请求参数
     * @param resultType 响应类型
     * @param config 请求配置
     * @return T
     * @author zongf
     * @date 2020-04-28
     */
    public static <T> T get(String url, Map<String, String> headers, Map<String, Object> params, Class<T> resultType, HttpConfig config) {
        String response = HttpUtil.postForm(url, headers, params, config);
        return HttpUtil.parseResponse(response, resultType);
    }

}
