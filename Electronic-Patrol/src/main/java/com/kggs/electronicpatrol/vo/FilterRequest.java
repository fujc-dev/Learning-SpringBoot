package com.kggs.electronicpatrol.vo;

import com.kggs.electronicpatrol.pojo.Rule;
import com.kggs.electronicpatrol.pojo.Flow;

import java.util.List;
import java.util.Map;

/**
 * 过滤请求对象
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/8/18 11:03
 */
public class FilterRequest {
    /**
     * 数据源
     */
    private List<Flow> datasource;
    /**
     * 过滤规则
     */
    private Map<String, List<Rule>> rules;

    public FilterRequest(List<Flow> datasource, Map<String,   List<Rule>> rules) {
        this.datasource = datasource;
        this.rules = rules;
    }

    public List<Flow> getDatasource() {
        return datasource;
    }

    public void setDatasource(List<Flow> datasource) {
        this.datasource = datasource;
    }

    public Map<String, List<Rule>> getRules() {
        return rules;
    }

    public void setRules(Map<String, List<Rule>> rules) {
        this.rules = rules;
    }
}
