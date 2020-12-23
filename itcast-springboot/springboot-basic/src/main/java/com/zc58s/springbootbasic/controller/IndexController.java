package com.zc58s.springbootbasic.controller;

/*
 * itcast-springboot
 * com.zc58s.springbootbasic.controller
 *
 * @author      : fjc.dane@gmail.com
 * @create time : 2020/12/23 17:32
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/index")
    public String index() {
        return "index";
    }
}
