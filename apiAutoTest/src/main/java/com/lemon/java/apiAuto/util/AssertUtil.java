package com.lemon.java.apiAuto.util;

import org.testng.Assert;

/**
 * @Description:判断工具类
 * @ClassName: AssertUtil
 * @Author: admin
 * @Date: 2021/6/9 7:36 上午
 * @Version: 1.0
 */
public class AssertUtil {
//    判断实际响应结果与期望响应结果是否一致，来返回通过或者接口返回值
    public static String assertEquals(String actualResponseData, String expectResponseData) {
        String result ="通过";
        try {
            Assert.assertEquals(actualResponseData,expectResponseData);

        }catch (Throwable e){
            result=actualResponseData;
        }
        return result;
    }
}
