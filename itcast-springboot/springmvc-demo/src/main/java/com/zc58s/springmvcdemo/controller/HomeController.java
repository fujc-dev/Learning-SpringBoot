package com.zc58s.springmvcdemo.controller;

import com.zc58s.springmvcdemo.enumeration.Sex;
import com.zc58s.springmvcdemo.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 * itcast-springboot
 * com.zc58s.springmvcdemo.controller
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2020/12/23 17:32
 */
@Controller
@RequestMapping
public class HomeController {

    /**
     * 默认页面
     *
     * @param model
     * @return
     */
    @RequestMapping("")
    public String index(Model model) {
        model.addAttribute("message", "第一个基于SpringBoot2.x构建的JSP项目！");
        return "index";
    }

    @RequestMapping("/tables")
    public String tables(Model model) {
        model.addAttribute("message", "第一个基于SpringBoot2.x构建的JSP项目！");
        return "web/tables";
    }

    @RequestMapping("/calendar")
    public String calendar(Model model) {
        model.addAttribute("message", "第一个基于SpringBoot2.x构建的JSP项目！");
        return "web/calendar";
    }

    @RequestMapping("/form")
    public String form(Model model) {
        model.addAttribute("message", "第一个基于SpringBoot2.x构建的JSP项目！");
        return "web/form";
    }

    @RequestMapping("/chart")
    public String chart(Model model) {
        model.addAttribute("message", "第一个基于SpringBoot2.x构建的JSP项目！");
        return "web/chart";
    }

    @RequestMapping("/table-list")
    public String tableList(Model model) {
        model.addAttribute("message", "第一个基于SpringBoot2.x构建的JSP项目！");
        return "web/table-list";
    }

    @RequestMapping("/table-list-img")
    public String tableListImg(Model model) {
        model.addAttribute("message", "第一个基于SpringBoot2.x构建的JSP项目！");
        return "web/table-list-img";
    }

    @RequestMapping("/sign-up")
    public String signUp(Model model) {
        model.addAttribute("message", "第一个基于SpringBoot2.x构建的JSP项目！");
        return "web/sign-up";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("message", "第一个基于SpringBoot2.x构建的JSP项目！");
        return "web/login";
    }

    @RequestMapping("/404")
    public String error(Model model) {
        model.addAttribute("message", "第一个基于SpringBoot2.x构建的JSP项目！");
        return "web/404";
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
        user.setNote("英语老好了！");
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
        user.setSex(Sex.FEMALE);
        user.setNote("英语老好了！");
        ModelAndView mv = new ModelAndView();
        MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
        mv.setView(jsonView);  //视图模型，格式\类型不一样
        mv.addObject(user);
        return mv;

    }

}
