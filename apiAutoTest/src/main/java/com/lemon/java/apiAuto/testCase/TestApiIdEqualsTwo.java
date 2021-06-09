package com.lemon.java.apiAuto.testCase;

import com.alibaba.fastjson.JSONObject;
import com.lemon.java.apiAuto.dataObject.WriteBackData;
import com.lemon.java.apiAuto.util.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;

/**
 * @Description:apiId=2
 * @ClassName: TestApiIdEqualsTwo
 * @Author: admin
 * @Date: 2021/6/9 7:49 上午
 * @Version: 1.0
 */
public class TestApiIdEqualsTwo extends BaseProcessor{
    @DataProvider
    public Object[][] datas(){
        String[] cellNames = {"CaseId","ApiId","Params","ExpectResponseData"};
        Object[][] datas = CaseUtil.getCaseDatasByApiId("2",cellNames);

        return datas;
    }

}
