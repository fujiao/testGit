package com.lemon.java.apiAuto.util;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;


/**
 * @Description: 获取表格信息的工具类
 * @ClassName: ExcelUtil
 * @Author: admin
 * @Date: 2021/5/30 6:08 下午
 * @Version: 1.0
 */
public class ExcelUtil {
    public static Object[][] datas(String excelPath,String sheetName,int[] rows,int[] cells){
        Object[][] datas = null;

        try {
            Workbook workbook = WorkbookFactory.create(new File(excelPath));
            Sheet sheet = workbook.getSheet(sheetName);
            datas=new Object[rows.length][cells.length];
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


}
