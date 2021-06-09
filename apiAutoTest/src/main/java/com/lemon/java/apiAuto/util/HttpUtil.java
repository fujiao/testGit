package com.lemon.java.apiAuto.util;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


import java.io.IOException;
import java.util.*;


/**
 * @Description: 执行get post方法的工具类
 * @ClassName: HttpUtil
 * @Author: admin
 * @Date: 2021/5/30 9:38 上午
 * @Version: 1.0
 */
public class HttpUtil {

//    将cookies信息放到map中
    public static Map<String,String> cookies = new HashMap<String,String>();

//    执行get方法
    public static String doGet(String url, Map<String,String> params){
        int mark =1;
        Set<String> keys = params.keySet();
        if(keys.size()==1){
            for (String key : keys) {
                String value = params.get(key);
                String keyAndValue = key+"="+value;
                url+="&"+keyAndValue;
            }
        }else {
            for (String key:keys){
                if (mark==1){
                    String value = params.get(key);
                    String keyAndValue = key+"="+value;
                    url+="?"+keyAndValue;
                    mark++;
                }else if(mark!=1){
                    String value = params.get(key);
                    String keyAndValue = key+"="+value;
                    url+="&"+keyAndValue;
                }
            }
        }

        HttpGet get = new HttpGet(url);
        String result = "";
        try{
            HttpClient client = HttpClients.createDefault();
            addCookieInRequestHeaderBeforeRequest(get);
            HttpResponse httpResponse = client.execute(get);
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            result = EntityUtils.toString(httpResponse.getEntity());
            System.out.println("【statusCode】"+statusCode+"，【result】"+result);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;

    }

//    执行post方法
    public static String doPost(String url, Map<String,String> params){
        //        指定请求方式
        HttpPost post = new HttpPost(url);
        List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
//        通过循环将参数和值传到parameters
        Set<String> keys = params.keySet();
        for(String key:keys){
            String value = params.get(key);
            parameters.add(new BasicNameValuePair(key,value));
        }
        String result = "";

        try{
            post.setEntity(new UrlEncodedFormEntity(parameters,"utf-8"));
            //        发起请求
            HttpClient client = HttpClients.createDefault();
            addCookieInRequestHeaderBeforeRequest(post);
            HttpResponse httpResponse = client.execute(post);
//            获取cookie
            getAndStoreCookiesFromResponseHeader(httpResponse);

//        获取响应数据 code和响应报文
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            HttpEntity entity = httpResponse.getEntity();
            result = EntityUtils.toString(entity);

        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

//    新增cookie
    private static void addCookieInRequestHeaderBeforeRequest(HttpRequest request) {
        String jsessionIdCookie = cookies.get("JSESSION");
        if (jsessionIdCookie!=null){
            request.addHeader("Cookie",jsessionIdCookie);

        }

    }

//    获取cookie
    private static void getAndStoreCookiesFromResponseHeader(HttpResponse httpResponse) {
        Header setCookieHeader = httpResponse.getFirstHeader("Set-Cookie");
        if (setCookieHeader!=null){
//            根据返回的cookie格式获取session
            String cookiePairsString = setCookieHeader.getValue();
            if (cookiePairsString!=null&&cookiePairsString.trim().length()>0){
                String[] cookiePairs = cookiePairsString.split(";");
                if(cookiePairs!=null){
                    for (String cookiePair : cookiePairs) {
                        if (cookiePair.contains("JSESSION")){
                            cookies.put("JSESSION",cookiePair);
                        }
                    }
                }

            }

        }


    }

//    根据type判断执行get post方法
    public static String doService(String url,String type,Map<String,String> params){
        String result="";
        //        判断执行方法
        if ("post".equalsIgnoreCase(type)){
            result=HttpUtil.doPost(url,params);
        } else if ("get".equalsIgnoreCase(type)){
            result=HttpUtil.doGet(url,params);
        }
        return result;
    }

}
