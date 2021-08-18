package com.kggs.electronicpatrol.service;

import com.kggs.electronicpatrol.pojo.Flow;
import com.kggs.electronicpatrol.vo.FilterRequest;

import java.util.List;

/**
 * 定义数据过滤接口
 * <p>
 * 用于过滤无效的巡更数据
 * </p>
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/8/18 10:46
 */
public interface IFilter {

    /**
     * 过滤
     *
     * @param request
     * @return
     */
    List<Flow> Filter(FilterRequest request);
}
