package com.lemon.java.apiAuto.testCase;

import com.alibaba.fastjson.JSONObject;
import com.lemon.java.apiAuto.util.ExcelUtil;
import com.lemon.java.apiAuto.util.HttpUtil;
import org.apache.poi.ss.usermodel.charts.ScatterChartSeries;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;

/**
 * @Description:
 * @ClassName: TestMethodCase_V6
 * @Author: admin
 * @Date: 2021/5/30 6:32 下午
 * @Version: 1.0
 */
public class TestMethodCase_V6 {
    @Test(dataProvider = "datas")
    public void test1(String apiIdFromCase,String parameter){
        int[] rows = {2,3,4};
        int[] cells = {1,3,4};
        Object[][] datas = ExcelUtil.datas("/Users/domain/JAVA_Learning/TestGit0529/testGit/apiAutoTest/src/main/resources/cases_V3.xlsx", "接口信息",rows, cells);
        String url = "";
        String type = "";
        for (Object[] objects : datas) {
            String apiIdFormRest = objects[0].toString();
            if (apiIdFormRest.equals(apiIdFromCase)){
                type = objects[1].toString();
                url=objects[2].toString();
                break;
            }
        }
        Map<String,String> params = (Map<String, String>) JSONObject.parse(parameter);
        String result = HttpUtil.doService(url, type, params);
    }



    @DataProvider
    public Object[][] datas(){
        int[] rows = {2,3,4,5};
        int[] cells = {3,4};
        Object[][] datas = ExcelUtil.datas("/Users/domain/JAVA_Learning/TestGit0529/testGit/apiAutoTest/src/main/resources/cases_V3.xlsx","用例",rows,cells);
        System.out.println(datas);
        return datas;
    }

}
