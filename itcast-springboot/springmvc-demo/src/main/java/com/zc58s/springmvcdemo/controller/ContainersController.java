package com.zc58s.springmvcdemo.controller;

import com.zc58s.springmvcdemo.request.LoginRequest;
import com.zc58s.springmvcdemo.request.UserRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述获取控制器参数
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/5 17:27
 * springmvc-demo
 * com.zc58s.springmvcdemo.controller
 */
@Controller
@RequestMapping("/containers")
public class ContainersController {
    /**
     * http://localhost:8090/containers/no/annotation?intVal=10&longVal=200&str=不使用注解的方式参数获取；
     * <p>这个无注解的参数，默认是非必填，其参数结构相当于：</p>
     * <p>-@RequestParam(value = "intVal", required = false) Integer intVal</p>
     * <p>-@RequestParam(value = "longVal", required = false) Long longVal</p>
     * <p>-@RequestParam(value = "str", required = false) String str</p>
     *
     * @param intVal
     * @param longVal
     * @param str
     * @return
     */
    @RequestMapping("/no/annotation")
    @ResponseBody  //该注解会将返回结果转化为JSON数据集
    public Map<String, Object> noAnnotation(Integer intVal, Long longVal, String str) {
        Map<String, Object> map = new HashMap<>();
        map.put("intVal", intVal);
        map.put("longVal", longVal);
        map.put("str", str);
        return map;
    }

    /**
     * http://localhost:8090/containers/annotation?int_val=10&long_val=200&str_val=不使用注解的方式参数获取
     * 从代码中可以看出，在方法的参数中使用了注解@RequestParam，其目的是指定HTTP参数和方法参数的映射关系，
     * 这样处理器就会按照其配置的映射关系来得到参数，然后调用控制器的方法。
     * 另外，@RequestParam还可以配置required，表示参数是否必填，另外@RequestParam默认是必填
     *
     * @param intVal
     * @param longVal
     * @param str
     * @return
     */
    @RequestMapping("/annotation")
    @ResponseBody  //该注解会将返回结果转化为JSON数据集
    public Map<String, Object> annotation(
            @RequestParam(value = "int_val", required = false) Integer intVal,
            @RequestParam(value = "long_val", required = false) Long longVal,
            @RequestParam(value = "str_val", required = false) String str) {
        Map<String, Object> map = new HashMap<>();
        map.put("intVal", intVal);
        map.put("longVal", longVal);
        map.put("str", str);
        return map;
    }

    /**
     * 控制器接收数组参数
     * <p>
     * http://localhost:8090/containers/requestArray?intArr=1,2,3,4&longArr=1,2,3,4,5&strArr=1,2,3,4,5
     * </p>
     * <p>数组参数以逗号隔开。</p>
     *
     * @param intArr
     * @param longArr
     * @param strArr
     * @return
     */
    @RequestMapping("/requestArray")
    @ResponseBody
    public Map<String, Object> requestArray(int[] intArr, long[] longArr, String[] strArr) {
        Map<String, Object> map = new HashMap<>();
        map.put("intArr", intArr);
        map.put("longArr", longArr);
        map.put("strArr", strArr);
        return map;
    }

    /**
     * 控制器接收JSON数据集
     * <pre>
     *  $(document).ready(function () {
     *         $("#submit").click(function () {
     *             let username = $("#username").val();
     *             let note = $("#note").val();
     *             let param = {username: username, note: note}
     *             $.ajax({
     *                 type: "POST",
     *                 url: "containers/requestJson",
     *                 contentType: "application/json",
     *                 data: JSON.stringify(param),
     *                 success: function (data) {
     *                     console.log(data);
     *                 },
     *                 error: function (error) {
     *                     console.log(error);
     *                 }
     *             });
     *         });
     *     });
     * </pre>
     *
     * <p>
     * - @RequestBody 用于接收JSON对象，这玩意儿将会被序列化。
     * </p>
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/requestJson", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> requestJson(@RequestBody LoginRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("data", request);
        return map;
    }

    /**
     * 通过URL传递参数
     * <p>http://localhost:8090/containers/add/1/张三</p>
     * <p>@RequestMapping("/add/{id}/{name}")表示URL的格式；</p>
     * <p>@PathVariable与@RequestMapping中参数进行映射；</p>
     * <pre>
     * 首先，通过@RequestMapping指定一个URL格式，用{...}来标明参数的顺序和名称，
     * -@PathVariable配置的字符串的id，name对应URL的参数声明，这样Spring就知道，
     * 如何从URL中获取参数。
     *
     * </pre>
     *
     * @param id
     * @param name
     * @return
     */
    @RequestMapping("/add/{id}/{name}")
    @ResponseBody
    public Map<String, Object> get(@PathVariable("id") long id, @PathVariable("name") String name) {
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("data", id + " --> " + name);
        return map;
    }


    /**
     * 将URL参数中的字符串格式化为有效的约定格式。
     * <p>http://localhost:8090/containers/format?date=2017,05,31 12:59:59&number=8624.09</p>
     *
     * @param date
     * @param number
     * @return
     */
    @RequestMapping("/format")
    @ResponseBody
    public Map<String, Object> format(
            @DateTimeFormat(pattern = "yyyy,MM,dd") Date date,
            @NumberFormat(pattern = "#,###.##") Double number) {
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("date", date);
        map.put("number", number);
        return map;
    }

    /**
     *
     * @param request
     * @return
     */
    @RequestMapping("/converter")
    @ResponseBody
    public Map<String, Object> getUserRequestByConverter(UserRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("date", request);
        return map;
    }
}
