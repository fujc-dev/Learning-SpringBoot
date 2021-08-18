package com.kggs.electronicpatrol.factory;

import com.kggs.electronicpatrol.exception.ExcelLoadException;
import com.kggs.electronicpatrol.pojo.Flow;
import com.kggs.electronicpatrol.pojo.Rule;
import com.kggs.electronicpatrol.service.IExcelFileLoadService;
import com.kggs.electronicpatrol.service.IFilter;
import com.kggs.electronicpatrol.service.impl.ExcelFileLoadServiceImp;
import com.kggs.electronicpatrol.utils.SpringContextUtil;
import com.kggs.electronicpatrol.vo.ExcelInputStreamRequest;
import com.kggs.electronicpatrol.vo.FilterRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/8/18 11:35
 */
public class ElectronicPatrolFactory {

    private Map<String, List<Rule>> rules = new HashMap<>();
    private List<Flow> datasource = null;

    /**
     * 添加过滤规则
     *
     * @param rules
     * @return
     */
    public ElectronicPatrolFactory AddRules(Map<String, List<Rule>> rules) {
        this.rules = rules;
        return this;
    }

    /**
     * 加载文件
     *
     * @param rowIndex
     * @param inputStream
     * @return
     * @throws FileNotFoundException
     * @throws ExcelLoadException
     */
    public ElectronicPatrolFactory Load(int rowIndex, FileInputStream inputStream) throws FileNotFoundException, ExcelLoadException {
        IExcelFileLoadService excelFileLoadService = SpringContextUtil.getBean(IExcelFileLoadService.class);
        ExcelInputStreamRequest request = new ExcelInputStreamRequest(rowIndex, inputStream);
        datasource = excelFileLoadService.Load(request);
        return this;
    }

    /**
     * 过滤
     *
     * @return
     */
    public List<Flow> Filter() {
        FilterRequest filterRequest = new FilterRequest(datasource, rules);
        IFilter filter = SpringContextUtil.getBean(IFilter.class);
        return filter.Filter(filterRequest);
    }

}
