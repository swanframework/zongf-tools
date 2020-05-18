package org.zongf.tools.http.utils;

import com.alibaba.fastjson.TypeReference;
import org.zongf.tools.http.config.HttpRequestConfig;

import java.io.File;
import java.util.Map;

/** Http Post请求工具类
 * @author zongf
 * @date 2020-04-28
 */
public class HttpPostUtil {

    /**@see HttpPostUtil#postForm(java.lang.String, java.util.Map, java.util.Map, java.lang.Class, HttpRequestConfig)
     * @return T
     * @author zongf
     * @date 2020-04-28
     */
    public static <T> T postForm(String url,  Map<String, Object> params, TypeReference<T> resultType) {
        return postForm(url, null, params, resultType, null);
    }

    /**@see HttpPostUtil#postForm(java.lang.String, java.util.Map, java.util.Map, java.lang.Class, HttpRequestConfig)
     * @return T
     * @author zongf
     * @date 2020-04-28
     */
    public static <T> T postForm(String url, Map<String, String> headers, Map<String, Object> params, TypeReference<T> resultType) {
        return postForm(url, headers, params, resultType, null);
    }

    /**@see HttpPostUtil#postForm(java.lang.String, java.util.Map, java.util.Map, java.lang.Class, HttpRequestConfig)
     * @return T
     * @author zongf
     * @date 2020-04-28
     */
    public static <T> T postForm(String url, Map<String, Object> params, TypeReference<T> resultType, HttpRequestConfig config) {
        return postForm(url, null, params, resultType, config);
    }

    /**@see HttpPostUtil#postForm(java.lang.String, java.util.Map, java.util.Map, java.lang.Class, HttpRequestConfig)
     * @return T
     * @author zongf
     * @date 2020-04-28
     */
    public static <T> T postForm(String url, Map<String, String> headers, Map<String, Object> params, TypeReference<T> resultType, HttpRequestConfig config) {
        String response = HttpUtil.postForm(url, headers, params, config);
        return HttpUtil.parseResponse(response, resultType);
    }

    /**@see HttpPostUtil#postForm(java.lang.String, java.util.Map, java.util.Map, java.lang.Class, HttpRequestConfig)
     * @return T
     * @author zongf
     * @date 2020-04-28
     */
    public static <T> T postForm(String url,  Map<String, Object> params, Class<T> resultType) {
        return postForm(url, null, params, resultType, null);
    }

    /**@see HttpPostUtil#postForm(java.lang.String, java.util.Map, java.util.Map, java.lang.Class, HttpRequestConfig)
     * @return T
     * @author zongf
     * @date 2020-04-28
     */
    public static <T> T postForm(String url, Map<String, String> headers, Map<String, Object> params, Class<T> resultType) {
        return postForm(url, headers, params, resultType, null);
    }

    /**@see HttpPostUtil#postForm(java.lang.String, java.util.Map, java.util.Map, java.lang.Class, HttpRequestConfig)
     * @return T
     * @author zongf
     * @date 2020-04-28
     */
    public static <T> T postForm(String url, Map<String, Object> params, Class<T> resultType, HttpRequestConfig config) {
        return postForm(url, null, params, resultType, config);
    }

    /** form表单形式, 发送post请求
     * @param url 请求地址
     * @param headers 请求头
     * @param params 请求参数
     * @param resultType 响应类型
     * @param config 请求配置
     * @return T
     * @author zongf
     * @date 2020-04-28
     */
    public static <T> T postForm(String url, Map<String, String> headers, Map<String, Object> params, Class<T> resultType, HttpRequestConfig config) {
        String response = HttpUtil.postForm(url, headers, params, config);
        return HttpUtil.parseResponse(response, resultType);
    }

    /**@see HttpPostUtil#postJson(java.lang.String, java.util.Map, java.lang.String, com.alibaba.fastjson.TypeReference, HttpRequestConfig)
     * @return T
     * @author zongf
     * @date 2020-04-28
     */
    public static <T> T postJson(String url, String requestBody, Class<T> resultType) {
        String response = HttpUtil.postJson(url, null, requestBody, null);
        return HttpUtil.parseResponse(response, resultType);
    }

    /**@see HttpPostUtil#postJson(java.lang.String, java.util.Map, java.lang.String, com.alibaba.fastjson.TypeReference, HttpRequestConfig)
     * @return T
     * @author zongf
     * @date 2020-04-28
     */
    public static <T> T postJson(String url, Map<String, String> headers, String requestBody, Class<T> resultType) {
        String response = HttpUtil.postJson(url, headers, requestBody, null);
        return HttpUtil.parseResponse(response, resultType);
    }

    /**@see HttpPostUtil#postJson(java.lang.String, java.util.Map, java.lang.String, com.alibaba.fastjson.TypeReference, HttpRequestConfig)
     * @return T
     * @author zongf
     * @date 2020-04-28
     */
    public static <T> T postJson(String url, String requestBody, Class<T> resultType, HttpRequestConfig config) {
        String response = HttpUtil.postJson(url, null, requestBody, config);
        return HttpUtil.parseResponse(response, resultType);
    }

    /** 发送json格式post请求
     * @param url 请求地址
     * @param headers 请求头
     * @param requestBody 请求体, json字符串
     * @param resultType 响应类型
     * @param config 请求配置
     * @return T
     * @author zongf
     * @date 2020-04-28
     */
    public static <T> T postJson(String url, Map<String, String> headers, String requestBody, Class<T> resultType, HttpRequestConfig config) {
        String response = HttpUtil.postJson(url, null, requestBody, config);
        return HttpUtil.parseResponse(response, resultType);
    }

    /**@see HttpPostUtil#postJson(java.lang.String, java.util.Map, java.lang.String, com.alibaba.fastjson.TypeReference, HttpRequestConfig)
     * @return T
     * @author zongf
     * @date 2020-04-28
     */
    public static <T> T postJson(String url, String requestBody, TypeReference<T> resultType) {
        String response = HttpUtil.postJson(url, null, requestBody, null);
        return HttpUtil.parseResponse(response, resultType);
    }

    /**@see HttpPostUtil#postJson(java.lang.String, java.util.Map, java.lang.String, com.alibaba.fastjson.TypeReference, HttpRequestConfig)
     * @return T
     * @author zongf
     * @date 2020-04-28
     */
    public static <T> T postJson(String url, Map<String, String> headers, String requestBody, TypeReference<T> resultType) {
        String response = HttpUtil.postJson(url, headers, requestBody, null);
        return HttpUtil.parseResponse(response, resultType);
    }

    /**@see HttpPostUtil#postJson(java.lang.String, java.util.Map, java.lang.String, com.alibaba.fastjson.TypeReference, HttpRequestConfig)
     * @return T
     * @author zongf
     * @date 2020-04-28
     */
    public static <T> T postJson(String url, String requestBody, TypeReference<T> resultType, HttpRequestConfig config) {
        String response = HttpUtil.postJson(url, null, requestBody, config);
        return HttpUtil.parseResponse(response, resultType);
    }

    /** 发送json格式post请求
     * @param url 请求地址
     * @param headers 请求头
     * @param requestBody 请求体, json字符串
     * @param resultType 响应类型
     * @param config 请求配置
     * @return T
     * @author zongf
     * @date 2020-04-28
     */
    public static <T> T postJson(String url, Map<String, String> headers, String requestBody, TypeReference<T> resultType, HttpRequestConfig config) {
        String response = HttpUtil.postJson(url, null, requestBody, config);
        return HttpUtil.parseResponse(response, resultType);
    }

    /**@see HttpPostUtil#postFiles(java.lang.String, java.util.Map, java.util.Map, java.lang.Class, HttpRequestConfig, java.lang.String, java.io.File...)
     * @return T
     * @author zongf
     * @date 2020-04-28
     */
    public static <T> T postFiles(String url, Class<T> resultType, String fileParamName, File... files) {
        return postFiles(url, null, null, resultType, null, fileParamName, files);
    }

    /**@see HttpPostUtil#postFiles(java.lang.String, java.util.Map, java.util.Map, java.lang.Class, HttpRequestConfig, java.lang.String, java.io.File...)
     * @return T
     * @author zongf
     * @date 2020-04-28
     */
    public static <T> T postFiles(String url, Map<String, Object> params, Class<T> resultType, String fileParamName, File... files) {
        return postFiles(url, null, params, resultType, null, fileParamName, files);
    }

    /**@see HttpPostUtil#postFiles(java.lang.String, java.util.Map, java.util.Map, java.lang.Class, HttpRequestConfig, java.lang.String, java.io.File...)
     * @return T
     * @author zongf
     * @date 2020-04-28
     */
    public static <T> T postFiles(String url, Class<T> resultType, HttpRequestConfig config, String fileParamName, File... files) {
        return postFiles(url, null, null, resultType, config, fileParamName, files);
    }

    /**@see HttpPostUtil#postFiles(java.lang.String, java.util.Map, java.util.Map, java.lang.Class, HttpRequestConfig, java.lang.String, java.io.File...)
     * @return T
     * @author zongf
     * @date 2020-04-28
     */
    public static <T> T postFiles(String url, Map<String, Object> params, Class<T> resultType, HttpRequestConfig config, String fileParamName, File... files) {
        return postFiles(url, null, params, resultType, config, fileParamName, files);
    }

    /** 上传文件
     * @param url 请求地址
     * @param headers 请求头
     * @param params 请求参数
     * @param resultType 响应类型
     * @param config 请求配置
     * @param fileParamName 文件参数名
     * @param files 文件
     * @return T
     * @author zongf
     * @date 2020-04-28
     */
    public static <T> T postFiles(String url, Map<String, String> headers, Map<String, Object> params, Class<T> resultType, HttpRequestConfig config, String fileParamName, File... files) {
        String response =  HttpUtil.postFiles(url, headers, params, fileParamName, files, config);
        return HttpUtil.parseResponse(response, resultType);
    }




}
