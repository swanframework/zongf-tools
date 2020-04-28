package org.zongf.tools.wx.utils;

import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zongf
 * @date 2020-03-27
 */
public class HttpUtil {

    /** 从连接池获取连接的超时时间 */
    private static final int DEFAULT_CONNECT_REQUEST_TIME = 3_000;

    /** 客户端和服务器建立连接的超时时间, 默认3秒, 超时后抛出: ConnectionTimeOutException*/
    public static final int DEFAULT_CONNECT_TIME_OUT = 3_000;

    /** 客户端读取响应数据的超时时间, 默认30秒, 超时后抛出: SocketTimeOutException*/
    public static final int DEFAULT_SOCKET_TIME_OUT = 30_000;

    /** 默认编码 */
    public static final String DEFAULT_CHARSET = "utf-8";

    public static String doGet(String url){
        return doGet(url, null, null, null, null, null, null, null);
    }

    public static String doGet(String url, Map<String, Object> paramMap){
        return doGet(url, paramMap, null, null, null, null, null, null);
    }

    public static String doGet(String url, Map<String, Object> paramMap, String responseCharset){
        return doGet(url, paramMap, null, null, responseCharset, null, null, null);
    }

    public static String doGet(String url, final Map<String, Object> paramMap, Map<String, String> headerMap){
        return doGet(url, paramMap, headerMap, null, null, null, null, null);
    }

    public static String doGet(String url, final Map<String, Object> paramMap, Map<String, String> headerMap, String responseCharset){
        return doGet(url, paramMap, headerMap, responseCharset, null, null, null, null);
    }

    public static String doGet(String url, final Map<String, Object> paramMap, Map<String, String> headerMap, String responseCharset, String requestCharSet, HttpHost httpProxy){
        return doGet(url, paramMap, headerMap, responseCharset, requestCharSet, httpProxy, null, null);
    }

    public static String doGet(String url, final Map<String, Object> paramMap, Map<String, String> headerMap, String responseCharset, String requestCharSet, HttpHost httpProxy, Integer connectTimeOut, Integer readTimeOut) {

        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;

        // 默认参数处理
        if(requestCharSet == null) requestCharSet = DEFAULT_CHARSET;
        if(responseCharset == null) responseCharset = DEFAULT_CHARSET;
        if(connectTimeOut == null) connectTimeOut = DEFAULT_CONNECT_TIME_OUT;
        if(readTimeOut == null) readTimeOut = DEFAULT_SOCKET_TIME_OUT;

        try {

            String fullUrl = getGetUrl(url, paramMap, requestCharSet);
            HttpGet httpGet = new HttpGet(fullUrl);

            // 设置请求头
            if (headerMap != null) {
                headerMap.forEach((key, val) -> httpGet.addHeader(key, val));
            }

            // 超时处理
            RequestConfig requestConfig = RequestConfig.custom()
                    .setProxy(httpProxy)
                    .setSocketTimeout(readTimeOut)
                    .setConnectTimeout(connectTimeOut)
                    .build();
            httpGet.setConfig(requestConfig);

            httpClient = HttpClients.createDefault();

            response = httpClient.execute(httpGet);

            // 得到响应信息
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                throw new RuntimeException("HttpClient,error status code :" + response.getStatusLine().getStatusCode());
            }

            return  response.getEntity() == null ? null : EntityUtils.toString(response.getEntity(), responseCharset);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(httpClient);
            close(response);
        }
        return null;
    }

    public static String getGetUrl(String url, Map<String, Object> paramMap, String charSet){
        try {

            // 参数列表为空,直接返回
            if (paramMap == null || paramMap.size() == 0) return url;

            // 参数编码为空, 则按默认编码处理
            if(charSet == null) charSet = DEFAULT_CHARSET;

            // 转换参数
            List<NameValuePair> paramList = new ArrayList<>();
            paramMap.forEach((key, val) -> paramList.add(new BasicNameValuePair(key, String.valueOf(val))));
            String paramStr = EntityUtils.toString(new UrlEncodedFormEntity(paramList, charSet));
            return url + "?" + paramStr;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String doPost(String url, String jsonBody, Map<String, String> headerMap, String responseCharset, String requestCharSet, HttpHost httpProxy, Integer connectTimeOut, Integer readTimeOut) {

        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;

        // 默认参数处理
        if(requestCharSet == null) requestCharSet = DEFAULT_CHARSET;
        if(responseCharset == null) responseCharset = DEFAULT_CHARSET;
        if(connectTimeOut == null) connectTimeOut = DEFAULT_CONNECT_TIME_OUT;
        if(readTimeOut == null) readTimeOut = DEFAULT_SOCKET_TIME_OUT;

        try {

            HttpPost httpPost = new HttpPost(url);


            // 设置请求头
            if (headerMap != null) {
                headerMap.forEach((key, val) -> httpPost.addHeader(key, val));
            }

            StringEntity jsonEntity = new StringEntity(jsonBody, requestCharSet);
            jsonEntity.setContentEncoding(responseCharset);
            jsonEntity.setContentType("application/json");
            httpPost.setEntity(jsonEntity);

            httpPost.setEntity(jsonEntity);

            // 超时处理
            RequestConfig requestConfig = RequestConfig.custom()
                    .setProxy(httpProxy)
                    .setSocketTimeout(readTimeOut)
                    .setConnectTimeout(connectTimeOut)
                    .build();
            httpPost.setConfig(requestConfig);

            httpClient = HttpClients.createDefault();

            response = httpClient.execute(httpPost);

            // 得到响应信息
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                throw new RuntimeException("HttpClient,error status code :" + response.getStatusLine().getStatusCode());
            }

            return  response.getEntity() == null ? null : EntityUtils.toString(response.getEntity(), responseCharset);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(httpClient);
            close(response);
        }
        return null;
    }

    private static void close(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
