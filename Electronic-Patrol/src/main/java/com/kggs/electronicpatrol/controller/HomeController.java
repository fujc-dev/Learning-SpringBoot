package com.kggs.electronicpatrol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/8/17 16:47
 */
@Controller
public class HomeController {

    @ResponseBody
    @RequestMapping(value = "/excleimport", method = RequestMethod.POST)
    public Map<String, Object> excleimport(@RequestParam MultipartFile excelFile,
                                           HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Map<String, Object> map = new HashMap<String, Object>();
        String name = excelFile.getOriginalFilename();
        if (!name.endsWith(".xls") && !name.endsWith(".xlsx")) {
            System.out.println("文件不是excel类型");
            map.put("msg", "文件类型错误");
        } else {
            //ExcelImport.getDataFromExcel(excelFile.getInputStream());
        }
        map.put("code", 0);
        return map;
    }
}
