package com.zc58s.springmvcdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/5 17:27
 * springmvc-demo
 * com.zc58s.springmvcdemo.controller
 */
@Controller
@RequestMapping("/containers")
public class ContainersController {
    /**
     * http://localhost:8090/containers/no/annotation?intVal=10&longVal=200&str=不使用注解的方式参数获取
     *
     * @param intVal
     * @param longVal
     * @param str
     * @return
     */
    @RequestMapping("/no/annotation")
    @ResponseBody
    public Map<String, Object> noAnnotation(Integer intVal, Long longVal, String str) {
        Map<String, Object> map = new HashMap<>();
        map.put("intVal", intVal);
        map.put("longVal", longVal);
        map.put("str", str);
        return map;
    }
}
