package org.zongf.tools.wx.utils;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.zongf.tools.wx.utils.entity.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zongf
 * @date 2020-03-27
 */
public class HttpUtilTest {

    @Test
    public void getToken(){

        String url = "https://api.weixin.qq.com/cgi-bin/token";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("grant_type", "client_credential");
        paramMap.put("appid", "wx1587b789e22236dd");
        paramMap.put("secret", "62b6e13395e0833aa27ee93e7391e817");

        String content = HttpUtil.doGet(url, paramMap);


        System.out.println(content);
        // 31_QVNBcfPuAp1GTVXHOnSY9q3Gu4joGbGpQHnCF6MhYFPLzeR4-XhmhksGAfOxcOp4hxqz2UoA6rlP2gPMXUoUGvYUVqA5Q4hUvvGieUMi3C9ztcNCqXYERv7-zplPsNXSx-XmqUOf9LfoQH9KDWNaAIAWPG
    }

    String token = "31_QVNBcfPuAp1GTVXHOnSY9q3Gu4joGbGpQHnCF6MhYFPLzeR4-XhmhksGAfOxcOp4hxqz2UoA6rlP2gPMXUoUGvYUVqA5Q4hUvvGieUMi3C9ztcNCqXYERv7-zplPsNXSx-XmqUOf9LfoQH9KDWNaAIAWPG";
    @Test
    public void createCollection(){
        String cloudId = "zongf-prod";
        String url  = "https://api.weixin.qq.com/tcb/databasecollectionadd?access_token=" + token;
        String collectionName = "student";

        String jsonBody = "{\"env\":\"" + cloudId + "\", \"collection_name\":\"" + collectionName + "\"}";

        String result = HttpUtil.doPost(url, jsonBody, null, null, null, null, null, null);
        System.out.println("result:" + result);
    }

    @Test
    public void addData(){
        String url = "https://api.weixin.qq.com/tcb/databaseadd?access_token=" + token;
        String collectionName = "student";
        String cloudId = "zongf-prod";

        List<Student> stuList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            stuList.add(new Student(i, "stu_" + i, "aa_" + i));
        }

        StringBuffer querySb = new StringBuffer();
        querySb.append("db.collection(\\\"").append(collectionName).append("\\\")")
                .append(".add(\\\"").append(JSONObject.toJSONString(stuList)).append("\\\")");
        String jsonBody = "{\"env\":\"" + cloudId + "\", \"query\":\"{" + querySb.toString() + "}\"}";

        System.out.println(jsonBody);

        String result = HttpUtil.doPost(url, jsonBody, null, null, null, null, null, null);
        System.out.println("result:" + result);
    }


}
