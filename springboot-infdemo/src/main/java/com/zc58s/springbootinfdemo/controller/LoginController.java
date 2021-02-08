package com.zc58s.springbootinfdemo.controller;

import com.zc58s.springbootinfdemo.jna.request.LoginRequest;
import com.zc58s.springbootinfdemo.jna.response.LoginResponse;
import com.zc58s.springbootinfdemo.jna.response.OrgNodeResponse;
import com.zc58s.springbootinfdemo.jna.sdk.InfNetSdk;
import com.zc58s.springbootinfdemo.jna.service.IBusinessService;
import com.zc58s.springbootinfdemo.jna.service.IPlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/2/4 14:50
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    private final IPlatformService platformService;
    private final IBusinessService businessService;

    @Autowired
    public LoginController(IPlatformService platformService, IBusinessService businessService) {
        this.platformService = platformService;
        this.businessService = businessService;
    }

    /**
     * @param szUrl      服务器地址
     * @param szUser     用户名
     * @param szPassword 密码
     * @return 登录结果
     */
    @ResponseBody
    @RequestMapping("/in")
    public Map<String, Object> in(String szUrl, String szUser, String szPassword) {
        Map<String, Object> map = new HashMap<>();

        String version = InfNetSdk.INSTANCE.INF_NET_GetVersion();
        System.out.println("SDK Version: " + version);
        LoginRequest request = new LoginRequest("10.20.2.165", "admin", "admin");
        LoginResponse response = this.platformService.Login(request);
        map.put("status", true);
        map.put("data", response);
        return map;
    }

    /**
     * @return
     */
    @ResponseBody
    @RequestMapping("/get_orgs")
    public Map<String, Object> getOrgs() {
        Map<String, Object> map = new HashMap<>();
        map.put("status", true);
        String response = this.businessService.GetOrgs();
        map.put("data", response);
        return map;
    }

    /**
     * @return
     */
    @ResponseBody
    @RequestMapping("/get_all_resources")
    public Map<String, Object> getAllResources() {
        Map<String, Object> map = new HashMap<>();
        map.put("status", true);
        String response = this.businessService.GetAllResourceList();
        map.put("data", response);
        return map;
    }

    /**
     * @return
     */
    @ResponseBody
    @RequestMapping("/get_all_server")
    public Map<String, Object> GetAllServer() {
        Map<String, Object> map = new HashMap<>();
        map.put("status", true);
        String response = this.businessService.GetAllServer();
        map.put("data", response);
        return map;
    }


}
