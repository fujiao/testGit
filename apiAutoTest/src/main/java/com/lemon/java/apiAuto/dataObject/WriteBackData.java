package com.lemon.java.apiAuto.dataObject;

/**
 * @Description:回写信息类
 * @ClassName: WriteBackData
 * @Author: admin
 * @Date: 2021/6/9 7:42 上午
 * @Version: 1.0
 */
public class WriteBackData {
    private String SheetName;
    private String caseId;
    private String cellName;
    private String result;

    public WriteBackData() {
    }

    @Override
    public String toString() {
        return "WriteBackData{" +
                "SheetName='" + SheetName + '\'' +
                ", caseId='" + caseId + '\'' +
                ", cellName='" + cellName + '\'' +
                ", result='" + result + '\'' +
                '}';
    }

    public WriteBackData(String sheetName, String caseId, String cellName, String result) {
        SheetName = sheetName;
        this.caseId = caseId;
        this.cellName = cellName;
        this.result = result;
    }

    public String getSheetName() {
        return SheetName;
    }

    public void setSheetName(String sheetName) {
        SheetName = sheetName;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getCellName() {
        return cellName;
    }

    public void setCellName(String cellName) {
        this.cellName = cellName;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
