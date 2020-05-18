package org.zongf.tools.http.utils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.zongf.tools.common.exception.AbsBaseException;
import org.zongf.tools.common.utils.CloseUtil;
import org.zongf.tools.http.config.HttpRequestConfig;
import org.zongf.tools.http.exception.HttpException;
import org.zongf.tools.log.util.LogUtil;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/** http Get请求工具类
 * @author zongf
 * @date 2020-04-16
 */
public class HttpUtil {

    private static Logger log = LogUtil.getLogger(HttpUtil.class);

    /** 发送get请求
     * @param headers 请求头
     * @param params 请求参数
     * @param httpRequestConfig 代理服务器,默认为空
     * @return String 响应内容
     * @author zongf
     * @date 2020-04-16
     */
    public static String get(String url, Map<String, String> headers, Map<String, Object> params, HttpRequestConfig httpRequestConfig) {
        // 如果配置为null, 则创建新的配置
        if(httpRequestConfig == null) httpRequestConfig = new HttpRequestConfig();

        // 处理请求参数
        if (params != null && !params.isEmpty()) {

            List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
            params.forEach((key, val) -> list.add(new BasicNameValuePair(key, String.valueOf(val))));

            try {
                String urlParams = EntityUtils.toString(new UrlEncodedFormEntity(list, httpRequestConfig.getRequestCharset().toString()));
                url = url + "?" + urlParams;
            } catch (Exception ex) {
                throw new HttpException(ex, "HttpGet-参数转换异常");
            }
        }

        return executeHttp(new HttpGet(), url, headers, httpRequestConfig, null);
    }

    /** 发送get请求
     * @param headers 请求头
     * @param params 请求参数
     * @param httpRequestConfig 代理服务器,默认为空
     * @return String 响应内容
     * @author zongf
     * @date 2020-04-16
     */
    public static String postForm(String url, Map<String, String> headers, Map<String, Object> params, HttpRequestConfig httpRequestConfig) {

        // 如果配置为null, 则创建新的配置
        if(httpRequestConfig == null) httpRequestConfig = new HttpRequestConfig();

        HttpEntity httpEntity = null;

        // 处理请求参数
        if (params != null && !params.isEmpty()) {

            List<NameValuePair> list = new ArrayList<>();
            params.forEach((key, val)->{
                list.add(new BasicNameValuePair(key, String.valueOf(val)));
            });

            try {
                httpEntity = new UrlEncodedFormEntity(list, httpRequestConfig.getRequestCharset().toString());
            } catch (UnsupportedEncodingException e) {
                throw new HttpException("请求参数编码异常");
            }
        }
        return executeHttp(new HttpPost(), url, headers, httpRequestConfig, httpEntity);
    }

    /** 发送get请求
     * @param headers 请求头
     * @param httpRequestConfig 代理服务器,默认为空
     * @return String 响应内容
     * @author zongf
     * @date 2020-04-16
     */
    public static String postJson(String url, Map<String, String> headers, String json, HttpRequestConfig httpRequestConfig) {

        // 如果配置为null, 则创建新的配置
        if(httpRequestConfig == null) httpRequestConfig = new HttpRequestConfig();

        // 处理请求参数
        StringEntity jsonEntity = null;

        if (json != null) {
            jsonEntity = new StringEntity(json, httpRequestConfig.getRequestCharset().toString());
            jsonEntity.setContentType("application/json");
        }

        return executeHttp(new HttpPost(), url, headers, httpRequestConfig, jsonEntity);
    }

    /** post 方式上传文件
     * @param url 请求地址
     * @param headers 自定义请求头
     * @param params post参数
     * @param fileParamName 文件参数名
     * @param files 文件列表
     * @param httpRequestConfig 请求配置
     * @return String
     * @author zongf
     * @date 2020-04-28
     */
    public static String postFiles(String url, Map<String, String> headers, Map<String, Object> params, String fileParamName, File[] files, HttpRequestConfig httpRequestConfig) {

        // 如果配置为null, 则创建新的配置
        if(httpRequestConfig == null) httpRequestConfig = new HttpRequestConfig();

        // 校验参数
        if (files == null || files.length > 0) {
            throw new HttpException("请求参数异常: 文件为空");
        }

        MultipartEntityBuilder builder = MultipartEntityBuilder.create()
                .setCharset(Charset.forName(httpRequestConfig.getRequestCharset().toString()))
                .setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

        // 处理文件参数
        if(fileParamName == null || fileParamName.isEmpty()) fileParamName = "file";
        for (File file : files) {
            builder.addPart(fileParamName, new FileBody(file));
        }

        // 处理普通参数
        if (params != null && !params.isEmpty()) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                StringBody paramBody = new StringBody(String.valueOf(entry.getValue()), ContentType.create("text/plain", httpRequestConfig.getRequestCharset().toString()));
                builder.addPart(entry.getKey(), paramBody);
            }
        }
        return executeHttp(new HttpPost(), url, headers, httpRequestConfig, builder.build());
    }


    /** 执行http请求
     * @param headers 请求头
     * @param httpRequestConfig 代理服务器,默认为空
     * @return String 响应内容
     * @author zongf
     * @date 2020-04-16
     */
    private static String executeHttp(HttpRequestBase httpPost, String url, Map<String, String> headers, HttpRequestConfig httpRequestConfig, HttpEntity httpEntity) {

        // 记录请求耗时
        long start = System.currentTimeMillis();

        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        try {

            // 处理请求头
            if (headers != null && !headers.isEmpty()) {
                headers.forEach((key, val) -> httpPost.addHeader(key, val));
            }

            if (httpPost instanceof HttpEntityEnclosingRequestBase) {
                // 处理请求参数
                ((HttpEntityEnclosingRequestBase)httpPost).setEntity(httpEntity);
            }

            // 设置url
            httpPost.setURI(URI.create(url));

            // 设置请求超时时间和网络代理
            RequestConfig.Builder configBuilder = RequestConfig.custom();
            configBuilder.setConnectTimeout(httpRequestConfig.getConnectTimeout() != null ? httpRequestConfig.getConnectTimeout() : HttpRequestConfig.DEFAULT_CONNECT_TIMEOUT);
            configBuilder.setSocketTimeout(httpRequestConfig.getReadTimeout() != null ? httpRequestConfig.getReadTimeout() : HttpRequestConfig.DEFAULT_READ_TIMEOUT);
            configBuilder.setProxy(httpRequestConfig.getHttpProxy());
            httpPost.setConfig(configBuilder.build());

            // 执行请求
            try {
                httpClient = HttpClients.createDefault();
                httpResponse = httpClient.execute(httpPost);
            } catch (IOException ex) {
                log.error("HttpRequest-发送请求异常!");
                throw new HttpException(ex, "HttpGet-请求异常");
            }

            // 如果请求响应不为200
            if (httpResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                log.error("HttpRequest-状态码异常, 响应码:{}, 响应行:{}, 响应结果:{}",
                        httpResponse.getStatusLine().getStatusCode(), httpResponse.getStatusLine(), EntityUtils.toString(httpResponse.getEntity(), httpRequestConfig.getResponseCharset().toString()));
                throw new HttpException("Http请求异常, 状态码:%s", httpResponse.getStatusLine().getStatusCode());
            }

            try {
                String responseBody = EntityUtils.toString(httpResponse.getEntity(), httpRequestConfig.getResponseCharset().toString());
                log.info("HttpRequest-响应结果:{}", responseBody);

                return responseBody;
            } catch (IOException ex) {
                log.error("HttpRequest-结果解析异常!");
                throw new HttpException(ex, "HttpRequest-结果解析异常");
            }

        } catch (AbsBaseException ex) {
            throw ex;
        } catch (Exception ex) {
            log.error("HttpRequest-未知异常!");
            throw new HttpException(ex, "HttpRequest-未知异常");
        } finally {
            // 关闭流
            CloseUtil.close(httpClient, httpResponse);
            log.info("HttpRequest-耗时:{}ms", (System.currentTimeMillis() - start));
        }
    }

    /** 解析响应结果
     * @param json 响应字符串
     * @param clz 响应类型
     * @return T
     * @author zongf
     * @date 2020-04-28
     */
    public static <T> T parseResponse(String json, Class<T> clz) {
        try {
            return JSONObject.parseObject(json, clz);
        } catch (Exception ex) {
            throw new HttpException(ex, "http响应json解析异常");
        }
    }

    /** 解析响应结果
     * @param json 响应字符串
     * @param clz 响应类型
     * @return T
     * @author zongf
     * @date 2020-04-28
     */
    public static <T> T parseResponse(String json, TypeReference<T> clz) {
        try {
            return JSONObject.parseObject(json, clz);
        } catch (Exception ex) {
            throw new HttpException(ex, "http响应json解析异常");
        }
    }


}
