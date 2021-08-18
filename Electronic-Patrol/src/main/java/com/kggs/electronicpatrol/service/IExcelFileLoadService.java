package com.kggs.electronicpatrol.service;

import com.kggs.electronicpatrol.exception.ExcelLoadException;
import com.kggs.electronicpatrol.pojo.Flow;
import com.kggs.electronicpatrol.vo.ExcelInputStreamRequest;

import java.util.List;

/**
 * Excel文件读取服务，用于将有效的Excel数据，采集到内存中
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/8/17 16:39
 */
public interface IExcelFileLoadService {

    /**
     * 读取文件流，并将有效的Excel数据，采集到内存中
     *
     * @param request Excel文件流对象
     * @return
     */
    List<Flow> Load(ExcelInputStreamRequest request) throws ExcelLoadException;

}
