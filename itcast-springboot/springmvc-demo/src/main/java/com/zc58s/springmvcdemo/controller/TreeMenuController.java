package com.zc58s.springmvcdemo.controller;

import com.zc58s.springmvcdemo.entity.TreeMenu;
import com.zc58s.springmvcdemo.service.TreeMenuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 树形菜单(TreeMenu)表控制层
 * itcast-springboot
 * com.zc58s.springmvcdemo.controller
 *
 * @author : fjc.dane@gmail.com
 * @createtime 2021-01-05 11:50:21
 */
@Controller
@RequestMapping("tree")
public class TreeMenuController {
    /**
     * 服务对象
     */
    @Resource
    private TreeMenuService treeMenuService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @RequestMapping("get")
    @ResponseBody
    public Map<String, Object> get(long id) {
        Object obj = this.treeMenuService.queryById(id);
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("data", obj);
        return map;


    }

}