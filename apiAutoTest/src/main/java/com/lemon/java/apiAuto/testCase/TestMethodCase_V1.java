package com.lemon.java.apiAuto.testCase;

import com.lemon.java.apiAuto.util.HttpUtil;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 接口测试用例版本1：手动填写接口链接入参
 * @ClassName: TestMethodCase_V1
 * @Author: admin
 * @Date: 2021/5/30 10:09 上午
 * @Version: 1.0
 */
public class TestMethodCase_V1 {
    String url = "http://www.kuaidi100.com/query";
    @Test
    public void test1(){
        String type = "ems";
        String postid = "";
        Map<String,String> params = new HashMap<String, String>();
        params.put("type",type);
        params.put("postid",postid);
        HttpUtil.doGet(url,params);

    }


}
