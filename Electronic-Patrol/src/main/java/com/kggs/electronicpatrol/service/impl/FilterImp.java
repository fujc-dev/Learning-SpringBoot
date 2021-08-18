package com.kggs.electronicpatrol.service.impl;

import com.kggs.electronicpatrol.pojo.Flow;
import com.kggs.electronicpatrol.pojo.Rule;
import com.kggs.electronicpatrol.service.IFilter;
import com.kggs.electronicpatrol.vo.FilterRequest;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/8/18 10:48
 */
@Service
public class FilterImp implements IFilter {
    @Override
    public List<Flow> Filter(FilterRequest request) {
        List<Flow> containers = new ArrayList<>();
        //1、获取指定巡检地址的巡检时间范围
        for (Flow flow : request.getDatasource()) {
            //2、获取规则
            List<Rule> rule = request.getRules().get(flow.getTargetCode());
            //3、利用当前的巡检时间对比，在指定的时间范围内，为有效巡检
            if (this.Calculating(rule, flow.getDate())) {
                containers.add(flow);
            }
        }
        return containers;
    }

    /**
     * 计算当前传入的日期是否在规则范围内
     *
     * @param rules
     * @param date
     * @return
     */
    private Boolean Calculating(List<Rule> rules, Date date) {
        if (rules != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            LocalTime currentTime = LocalTime.of(cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE));
            for (Rule rule : rules) {
                if (IsTimeRange(rule.getBeginDate(), rule.getEndDate(), currentTime)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断是否在某个时间范围内
     *
     * @param startTime   范围开始时间  格式为"xx:xx"
     * @param endTime     范围结束时间  格式为"xx:xx"
     * @param currentTime 当前时间
     * @return true/false
     */
    public static boolean IsTimeRange(String startTime, String endTime, LocalTime currentTime) {
        LocalTime startTimeLocal = LocalTime.parse(startTime);
        LocalTime endTimeLocal = LocalTime.parse(endTime);
        //一天内的时间对比，不跨天数，结束为10点，开始时间为9点
        if (startTimeLocal.isBefore(endTimeLocal)) {
            //"18:30", "19:00" 18:55在范围内
            if (startTimeLocal.isBefore(startTimeLocal) && currentTime.isBefore(endTimeLocal)) {
                System.out.println("==========>startTimeLocal：" + startTime + "，endTime：" + endTime + "，currentTime：" + currentTime.toString());
                return true;
            }
            //被检测的时间与检测时间相等
            else if (currentTime.equals(startTimeLocal) || endTimeLocal.equals(currentTime)) {
                System.out.println("==========>startTimeLocal：" + startTime + "，endTime：" + endTime + "，currentTime：" + currentTime.toString());
                return true;
            }
        }
        //跨天数，一般是第二天 "12:55", "11:00"，表示昨天的12:55 到今天的11:00点，当前时间为13:02
        else {
            //12:55在传入的13：02之前，  11:10在13:02之前
            //传入的是昨天的值：12:55 - 23:59
            if (startTimeLocal.isBefore(currentTime) && endTimeLocal.isBefore(currentTime)) {
                System.out.println("==========>startTimeLocal：" + startTime + "，endTime：" + endTime + "，currentTime：" + currentTime.toString());
                return true;
            }
            //23:00在传入的00:03之后，00:05在传入的00:03之前
            //传入值为今天的值时，00:00 - 00:05
            else if (currentTime.isBefore(startTimeLocal) && currentTime.isBefore(endTimeLocal)) {
                System.out.println("==========>startTimeLocal：" + startTime + "，endTime：" + endTime + "，currentTime：" + currentTime.toString());
                return true;
            }
        }
        return false;
    }
}
