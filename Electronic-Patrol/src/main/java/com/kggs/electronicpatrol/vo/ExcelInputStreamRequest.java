package com.kggs.electronicpatrol.vo;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/8/17 16:52
 */
public class ExcelInputStreamRequest {

    public ExcelInputStreamRequest() {

    }

    public ExcelInputStreamRequest(int rowIndex, FileInputStream inputStream) {
        this.rowIndex = rowIndex;
        this.inputStream = inputStream;
    }

    /**
     * 开始行
     */
    private int rowIndex;
    /**
     * Excel文件流
     */
    private FileInputStream inputStream;


    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public FileInputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(FileInputStream inputStream) {
        this.inputStream = inputStream;
    }
}
