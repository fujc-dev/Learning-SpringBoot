package com.zc58s.springbootbasic.controller;

/*
 * itcast-springboot
 * com.zc58s.springbootbasic.controller
 *
 * @author      : fjc.dane@gmail.com
 * @create time : 2020/12/23 17:32
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class IndexController {

    /**
     * 默认页面
     * @param model
     * @return
     */
    @RequestMapping("/")
    public String index(Model model){
        System.out.println("1");
        model.addAttribute("message", "第一个基于SpringBoot2.3.4构建的JSP项目！");
        return "index";
    }

}
