package com.lemon.java.apiAuto.testCase;

import com.alibaba.fastjson.JSONObject;
import com.lemon.java.apiAuto.dataObject.WriteBackData;
import com.lemon.java.apiAuto.util.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;

/**
 * @Description: apiId=1
 * @ClassName: TestApiIdEqualsOne
 * @Author: admin
 * @Date: 2021/6/9 7:48 上午
 * @Version: 1.0
 */
public class TestApiIdEqualsOne extends BaseProcessor{
    @DataProvider
    public Object[][] datas(){
        String[] cellNames = {"CaseId","ApiId","Params","ExpectResponseData"};
        Object[][] datas = CaseUtil.getCaseDatasByApiId("1",cellNames);
        return datas;
    }

}
