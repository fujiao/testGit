package com.lemon.java.apiAuto.util;

import com.lemon.java.apiAuto.dataObject.Case;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:测试用例工具类
 * @ClassName: CaseUtil
 * @Author: admin
 * @Date: 2021/6/9 7:33 上午
 * @Version: 1.0
 */
public class CaseUtil {
    //保存所有的用例对象
    public static List<Case> cases = new ArrayList<Case>();

    static {
        //将所有数据封装到cases
        ExcelUtil.load("src/main/resources/cases_V5.xlsx","用例", Case.class,"");
    }

    /**
     * 根据接口编号获取接口数据
     * @param apiId  接口id
     * @param cellNames 需要获取的列名
     * @return
     */
    public static Object[][] getCaseDatasByApiId(String apiId,String[] cellNames) {
        Class<Case> clazz = Case.class;
        //保存指定接口编号的case对象的集合
        ArrayList<Case> csList = new ArrayList<Case>();
        //循环找到指定编号对应的用例数据
        for (Case cs : cases) {
            if (cs.getApiId().equals(apiId)){
                csList.add(cs);
            }
        }
        Object[][] datas = new Object[csList.size()][cellNames.length];

        for (int i = 0; i < csList.size(); i++) {
            Case cs = csList.get(i);
            for (int j = 0; j < cellNames.length; j++) {
//                要反射的方法名
                String methodName = "get"+cellNames[j];
                try {
                    Method method = clazz.getMethod(methodName);
                    String value = (String) method.invoke(cs);
                    datas[i][j]=value;
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return datas;
    }
}
