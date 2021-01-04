package com.zc58s.springbootbasic.controller;

/*
 * itcast-springboot
 * com.zc58s.springbootbasic.controller
 *
 * @author      : fjc.dane@gmail.com
 * @create time : 2020/12/23 17:32
 */

import com.zc58s.springbootbasic.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;


@Controller
@RequestMapping("/home")
public class IndexController {

    /**
     * 默认页面
     *
     * @param model
     * @return
     */
    @RequestMapping("/")
    public String index(Model model) {
        System.out.println("1");
        model.addAttribute("message", "第一个基于SpringBoot2.3.4构建的JSP项目！");
        return "index";
    }

    /**
     * SpringMvc使用JSP视图
     *
     * @return
     */
    @RequestMapping("/details")
    public ModelAndView details() {
        User user = new User();
        user.setUsername("Lucy");
        user.setId(1L);
        user.setNode("英语老好了！");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("web/details");  //视图模型，格式\类型不一样
        mv.addObject(user);
        //mv.addObject("aaa",user);
        return mv;

    }

    /**
     * SpringMvc使用JSON视图
     *
     * @return
     */
    @RequestMapping("/json")
    public ModelAndView json() {
        User user = new User();
        user.setUsername("Lucy");
        user.setId(1L);
        user.setNode("英语老好了！");
        ModelAndView mv = new ModelAndView();
        MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
        mv.setView(jsonView);  //视图模型，格式\类型不一样
        mv.addObject(user);
        return mv;

    }

}
