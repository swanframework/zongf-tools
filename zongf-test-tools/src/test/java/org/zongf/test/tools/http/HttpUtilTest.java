package org.zongf.test.tools.http;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.zongf.test.tools.vo.UserVO;
import org.zongf.tools.common.enums.CharsetEnum;
import org.zongf.tools.http.config.HttpRequestConfig;
import org.zongf.tools.http.utils.HttpUtil;

import java.io.File;
import java.util.*;

/**
 * @author zongf
 * @date 2020-04-28
 */
public class HttpUtilTest {

    @Test
    public void testGet(){
        String host = "http://localhost:7080/httpUtil";
        String url = host + "/get";

        Map<String, Object> params = new HashMap<>();
        params.put("name", "zongf");
        params.put("age", 20);
        Map<String, String> headers = new HashMap<>();
        HttpRequestConfig httpRequestConfig = new HttpRequestConfig();

        String response = HttpUtil.get(url, headers,  params, httpRequestConfig);
        System.out.println(response);
    }

    @Test
    public void testPostForm(){
        String host = "http://localhost:7080/httpUtil";
        String url = host + "/postForm";

        Map<String, Object> params = new HashMap<>();
        params.put("name", "zhangsan");
        params.put("age", 20);
        Map<String, String> headers = new HashMap<>();
        HttpRequestConfig httpRequestConfig = new HttpRequestConfig();

        String response = HttpUtil.postForm(url, headers, params, httpRequestConfig);
        System.out.println(response);
    }

    @Test
    public void testPostJson(){
        String host = "http://localhost:7080/httpUtil";
        String url = host + "/postJson";

        Map<String, Object> params = new HashMap<>();
        params.put("name", "zhangsan");
        params.put("age", 20);
        Map<String, String> headers = new HashMap<>();
        HttpRequestConfig httpRequestConfig = new HttpRequestConfig();
        UserVO userVO = new UserVO("zhangsn", 20, "man");
        String response = HttpUtil.postJson(url,  headers, JSONObject.toJSONString(userVO), httpRequestConfig);
        System.out.println(response);
    }

    @Test
    public void testPostFile(){
        String host = "http://localhost:7080/httpUtil";
        String url = host + "/postFile";

        Map<String, Object> params = new HashMap<>();
        params.put("name", "zhangsan");
        params.put("age", 20);

        List<File> files = new ArrayList<>();
        files.add(new File("/home/zongf/Desktop/表设计/保险-表设计.xlsx"));
        files.add(new File("/home/zongf/Desktop/表设计/积分返佣-表设计.xlsx"));


        Map<String, String> headers = new HashMap<>();
        HttpRequestConfig httpRequestConfig = new HttpRequestConfig();
    }


    @Test
    public void testGetHtml(){
        String url = "http://www.baidu.com";

        HttpRequestConfig httpRequestConfig = new HttpRequestConfig();
        httpRequestConfig.setResponseCharset(CharsetEnum.UTF8);
        String response = HttpUtil.get(url, null,  null, httpRequestConfig);
        System.out.println(response);
    }
}
