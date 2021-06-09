package com.lemon.java.apiAuto.util;

import com.lemon.java.apiAuto.dataObject.Case;
import com.lemon.java.apiAuto.dataObject.Rest;
import com.lemon.java.apiAuto.dataObject.WriteBackData;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Description: 获取表格信息的工具类
 * @ClassName: ExcelUtil
 * @Author: admin
 * @Date: 2021/5/30 6:08 下午
 * @Version: 1.0
 */
public class ExcelUtil {
    public static Map<String,Integer> caseIdRownumMapping = new HashMap<String,Integer>();
    public static Map<String,Integer> cellNameCellnumMapping = new HashMap<String,Integer>();
    public static List<WriteBackData> writeBackDatas = new ArrayList<WriteBackData>();
    static {
//        caseIdRownumMapping = {}

        try {
            loadRownumAndCellnumMapping("src/main/resources/cases_V5.xlsx","用例");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
//    获取caseId和对应的行索引
//    获取cellName和对应的列索引

    private static void loadRownumAndCellnumMapping(String excelPath, String sheetName) throws FileNotFoundException {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(excelPath));
//            InputStream inputStream = new FileInputStream(new File(excelPath));

            Workbook workbook = WorkbookFactory.create(inputStream);
            File file = new File(excelPath);
            if(file.length() == 0) {
                System.out.println("文件为空！");
            }

            Sheet sheet = workbook.getSheet(sheetName);
            Row titleRow = sheet.getRow(0);
            if (titleRow!=null){
//                isEmptyRow(titleRow)
                int lastCellNum = titleRow.getLastCellNum();
                for (int i = 0; i < lastCellNum; i++) {
                    Cell cell = titleRow.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    cell.setCellType(CellType.STRING);
                    String value = cell.getStringCellValue();
                    String title = value.substring(0, value.indexOf("("));
                    int cellnum = cell.getAddress().getColumn();
                    cellNameCellnumMapping.put(title,cellnum);
                }
            }
//            从第二行开始，获取所有数据行
            int lastRowNum = sheet.getLastRowNum();
            for (int i = 1; i <= lastRowNum; i++) {
                Row datarow = sheet.getRow(i);
                Cell firstCellOfRow = datarow.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                firstCellOfRow.setCellType(CellType.STRING);
                String caseId = firstCellOfRow.getStringCellValue();
                int rowNum = datarow.getRowNum();
                caseIdRownumMapping.put(caseId,rowNum);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }


    public static Object[][] datas(String excelPath,String sheetName,int[] rows,int[] cells){
        Object[][] datas = null;
        try {
            //        获取workbook
            Workbook workbook = WorkbookFactory.create(new File(excelPath));
            //        获取sheet
            Sheet sheet = workbook.getSheet(sheetName);
            //        获取行
            datas = new Object[rows.length][cells.length];
//            FOR EACH在这里怎么用
            for (int i = 0; i < rows.length; i++) {
                Row row = sheet.getRow(rows[i]-1);
                for (int j = 0; j < cells.length; j++) {
                    Cell cell = row.getCell(cells[j]-1,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    cell.setCellType(CellType.STRING);
                    String value = cell.getStringCellValue();
                    datas[i][j]=value;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return datas;
    }
    public static <T> void load(String path, String sheetName, Class<T> clazz,String s)  {
//        //获取类的字节码
//        Class<T> caseClass = Case.class;
//        Workbook workbook = null;
        InputStream inputStream = null;
        try {
//step1：获取列名
            //创建workbook对象
            inputStream = new FileInputStream(new File(path));
            Workbook workbook = WorkbookFactory.create(inputStream);
            //获取表
            Sheet sheet = workbook.getSheet(sheetName);
            //返回第一行
            Row titleRow = sheet.getRow(0);
            //获取第一行的内容总数
            int lastCellNum = titleRow.getLastCellNum();
            //根据列数返回的值创建key数组
            String[] fields = new String[lastCellNum];
            //循环列，获取列名
            for (int i = 0; i < lastCellNum; i++) {
                //获取列
                Cell cell = titleRow.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                //设置列的类型
                cell.setCellType(CellType.STRING);
                //获取完整列名
                String totolTitle = cell.getStringCellValue();
                //分隔获取需要的列名
                String title = totolTitle.substring(0,totolTitle.indexOf("("));
                //赋值给数组
                fields[i]=title;
            }
// step2:获取值
//            ?
            //获取表的内容总数 返回行数
            int lastRowNum = sheet.getLastRowNum();
            //循环行
            for (int i = 1; i <= lastRowNum; i++) {
                Row row = sheet.getRow(i);//第i行
                //创建实例化对象
                Object obj = clazz.newInstance();
                Row dataRow = sheet.getRow(i);
                for (int j = 0; j < fields.length; j++) {
                    //表格有数据被删除后，可能还是被获取到
                    if (dataRow==null){
                        continue;
                    }

                    //获取行里面的第i个单元格
                    Cell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    cell.setCellType(CellType.STRING);
                    String value = cell.getStringCellValue();
                    String key = fields[j];
                    String methodName = "set"+key;
                    Method method = clazz.getMethod(methodName, String.class);
                    method.invoke(obj,value);
                }
                if (obj instanceof Case){
                    Case cs = (Case) obj;
                    CaseUtil.cases.add(cs);
                }else if(obj instanceof Rest){
                    Rest rest = (Rest) obj;
                    RestUtil.rests.add(rest);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (inputStream==null){
                    inputStream.close();
                    System.out.println("关闭文件");
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public static void batchWriteBackData(String excelPath)  {
        InputStream inputStream = null;
        OutputStream outputStream = null;
//        inputStream.close();
        try {
            inputStream = new FileInputStream(new File(excelPath));
            Workbook workbook = WorkbookFactory.create(inputStream);
            for (WriteBackData writeBackData : writeBackDatas) {
                String sheetName = writeBackData.getSheetName();
                Sheet sheet = workbook.getSheet(sheetName);
                String caseId = writeBackData.getCaseId();
                int rownum = caseIdRownumMapping.get(caseId);
                Row row = sheet.getRow(rownum);
                String cellName = writeBackData.getCellName();
                int cellnum = cellNameCellnumMapping.get(cellName);
                Cell cell = row.getCell(cellnum, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                cell.setCellType(CellType.STRING);
                String result = writeBackData.getResult();
                cell.setCellValue(result);
                outputStream = new FileOutputStream(new File(excelPath));
                workbook.write(outputStream);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try{
                if (inputStream!=null){
                    inputStream.close();
                    System.out.println("关闭输入");
                }
                if (outputStream!=null){
                    outputStream.close();
                    System.out.println("关闭输出");
                }


            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }


}
