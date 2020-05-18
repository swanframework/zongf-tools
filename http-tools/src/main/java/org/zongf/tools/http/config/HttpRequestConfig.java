package org.zongf.tools.http.config;

import org.apache.http.HttpHost;
import org.zongf.tools.common.enums.CharsetEnum;

/** http请求配置类
 * @author zongf
 * @date 2020-04-28
 */
public class HttpRequestConfig {

    /** 客户端和服务器建立连接的超时时间, 默认10秒, 超时后抛出: ConnectionTimeOutException */
    public static int DEFAULT_CONNECT_TIMEOUT = 10_000;

    /** 客户端读取响应数据的超时时间, 默认30秒, 超时后抛出: SocketTimeOutException */
    public static int DEFAULT_READ_TIMEOUT = 30_000;

    /** 连接超时时间, 单位毫秒 */
    private Integer connectTimeout = DEFAULT_CONNECT_TIMEOUT;

    /** 读取超时时间, 单位毫秒 */
    private Integer readTimeout = DEFAULT_READ_TIMEOUT;

    /** 响应状态码 */
    private CharsetEnum responseCharset = CharsetEnum.UTF8;

    /** 请求体状态码 */
    private CharsetEnum requestCharset = CharsetEnum.UTF8;

    /** 代理服务器 */
    private HttpHost httpProxy;

    public HttpRequestConfig() {
    }

    public void setConnectTimeout(Integer connectTimeout){
		this.connectTimeout=connectTimeout;
	}

	public Integer getConnectTimeout(){
		return this.connectTimeout;
	}

    public void setReadTimeout(Integer readTimeout){
		this.readTimeout=readTimeout;
	}

	public Integer getReadTimeout(){
		return this.readTimeout;
	}

    public void setResponseCharset(CharsetEnum responseCharset){
		this.responseCharset=responseCharset;
	}

	public CharsetEnum getResponseCharset(){
		return this.responseCharset;
	}

    public void setRequestCharset(CharsetEnum requestCharset){
		this.requestCharset=requestCharset;
	}

	public CharsetEnum getRequestCharset(){
		return this.requestCharset;
	}

    public HttpHost getHttpProxy() {
        return httpProxy;
    }

    public void setHttpProxy(HttpHost httpProxy) {
        this.httpProxy = httpProxy;
    }
}
