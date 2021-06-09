package com.lemon.java.apiAuto.testCase;

import com.alibaba.fastjson.JSONObject;
import com.lemon.java.apiAuto.dataObject.WriteBackData;
import com.lemon.java.apiAuto.util.AssertUtil;
import com.lemon.java.apiAuto.util.ExcelUtil;
import com.lemon.java.apiAuto.util.HttpUtil;
import com.lemon.java.apiAuto.util.RestUtil;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import java.util.Map;

/**
 * @Description: 执行用例
 * @ClassName: BaseProcessor
 * @Author: admin
 * @Date: 2021/6/9 7:35 上午
 * @Version: 1.0
 */
public class BaseProcessor {
    @Test(dataProvider = "datas")
    public static void test1(String caseId,String apiId,String parameter,String expectResponseData){
        String url = RestUtil.getUrlByApiId(apiId);
        String type = RestUtil.getTypeByApiId(apiId);
        Map<String,String> params = (Map<String, String>) JSONObject.parse(parameter);
        String actualResponseData = HttpUtil.doService(url, type, params);
        actualResponseData = AssertUtil.assertEquals(actualResponseData, expectResponseData);
        WriteBackData writeBackData = new WriteBackData("用例", caseId, "ActualResponseData", actualResponseData);
        ExcelUtil.writeBackDatas.add(writeBackData);
        System.out.println(writeBackData);
    }


    @AfterSuite
    public void batchWriteBackData() {
        ExcelUtil.batchWriteBackData("src/main/resources/cases_V5.xlsx");

    }
}
