package com.lemon.java.apiAuto.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;


import java.io.IOException;
import java.util.Map;
import java.util.Set;


/**
 * @Description: 执行get post方法的工具类
 * @ClassName: HttpUtil
 * @Author: admin
 * @Date: 2021/5/30 9:38 上午
 * @Version: 1.0
 */
public class HttpUtil {

    public static void doGet(String url, Map<String, String> params) {
        Set<String> keys = params.keySet();
        int mark = 0;
        for (String key : keys) {
            String value = params.get(key);
            if (mark == 0) {
                url += "?" + key + "=" + value;
                mark++;
            } else {
                url += "&" + key + "=" + value;
            }
        }
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        String result = "";
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            System.out.println("statusCode:" + statusCode);
            HttpEntity entity = httpResponse.getEntity();
            result = EntityUtils.toString(entity);
            System.out.println("result" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
