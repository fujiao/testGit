package com.lemon.java.apiAuto.dataObject;

/**
 * @Description:用例类，参数为用例表的列名
 * @ClassName: Case
 * @Author: admin
 * @Date: 2021/6/9 7:31 上午
 * @Version: 1.0
 */
public class Case {
    private String caseId;
    private String apiId;
    private String desc;
    private String expectResponseData;
    private String actualResponseData
            ;

    public String getActualResponseData() {
        return actualResponseData;
    }

    @Override
    public String toString() {
        return "Case{" +
                "caseId='" + caseId + '\'' +
                ", apiId='" + apiId + '\'' +
                ", desc='" + desc + '\'' +
                ", expectResponseData='" + expectResponseData + '\'' +
                ", actualResponseData='" + actualResponseData + '\'' +
                ", params='" + params + '\'' +
                '}';
    }

    public void setActualResponseData(String actualResponseData) {
        this.actualResponseData = actualResponseData;
    }

    public String getExpectResponseData() {
        return expectResponseData;
    }


    public void setExpectResponseData(String expected) {
        this.expectResponseData = expected;
    }

    private String params;

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }
}
