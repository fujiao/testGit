package com.lemon.java.apiAuto.util;

import com.lemon.java.apiAuto.dataObject.Rest;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @ClassName: RestUtil
 * @Author: admin
 * @Date: 2021/6/9 7:41 上午
 * @Version: 1.0
 */
public class RestUtil {
    public static List<Rest> rests = new ArrayList<Rest>();
    static {
        ExcelUtil.load("src/main/resources/cases_V5.xlsx","接口信息",Rest.class,"");
    }

    public static String getUrlByApiId(String apiId) {
        for (Rest rest : rests) {
            if (rest.getApiId().equals(apiId)){
                return rest.getUrl();
            }
        }
        return "";
    }

    public static String getTypeByApiId(String apiId) {
        for (Rest rest : rests) {
            if (rest.getApiId().equals(apiId)){
                return rest.getType();
            }
        }
        return "";
    }
}
