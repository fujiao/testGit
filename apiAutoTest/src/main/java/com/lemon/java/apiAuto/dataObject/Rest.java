package com.lemon.java.apiAuto.dataObject;

/**
 * @Description:接口信息类
 * @ClassName: Rest
 * @Author: admin
 * @Date: 2021/6/9 7:41 上午
 * @Version: 1.0
 */
public class Rest {
    private String apiId;
    private String apiName;
    private String type;
    private String url;

    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Rest{" +
                "apiId='" + apiId + '\'' +
                ", apiName='" + apiName + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
