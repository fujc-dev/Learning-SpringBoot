package com.zc58s.springbootinfdemo.controller;

import com.zc58s.springbootinfdemo.jna.request.LoginRequest;
import com.zc58s.springbootinfdemo.jna.request.PhotographRequest;
import com.zc58s.springbootinfdemo.jna.response.LoginResponse;
import com.zc58s.springbootinfdemo.jna.response.PhotographResponse;
import com.zc58s.springbootinfdemo.jna.response.PtzResponse;
import com.zc58s.springbootinfdemo.jna.sdk.InfNetSdk;
import com.zc58s.springbootinfdemo.jna.service.IBusinessService;
import com.zc58s.springbootinfdemo.jna.service.IPlatformService;
import com.zc58s.springbootinfdemo.jna.service.IPtzControlService;
import com.zc58s.springbootinfdemo.jna.service.IVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
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
    //摄像头控制服务
    private final IPtzControlService ptzService;
    private final IVideoService videoService;

    @Autowired
    public LoginController(IPlatformService platformService,
                           IBusinessService businessService,
                           IPtzControlService ptzService,
                           IVideoService videoService) {
        this.platformService = platformService;
        this.businessService = businessService;
        this.ptzService = ptzService;
        this.videoService = videoService;
    }

    @ResponseBody
    @RequestMapping("/demo")
    public Map<String, Object> demo() {
        Map<String, Object> map = new HashMap<>();
        map.put("status", true);
        map.put("data", "inf-demo");
        return map;
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
        LoginRequest request = new LoginRequest(szUrl, szUser, szPassword);
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


    /**
     * @return
     */
    @ResponseBody
    @RequestMapping("/PtzUp")
    public Map<String, Object> PtzUp(String szCameraId) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", true);
        PtzResponse response = this.ptzService.PtzUp(szCameraId);
        this.ptzService.PtzStop(szCameraId);
        map.put("data", response);
        return map;
    }

    /**
     * @return
     */
    @ResponseBody
    @RequestMapping("/PtzDown")
    public Map<String, Object> PtzDown(String szCameraId) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", true);
        PtzResponse response = this.ptzService.PtzDown(szCameraId);
        this.ptzService.PtzStop(szCameraId);
        map.put("data", response);
        return map;
    }

    //拍照

    /**
     * @return
     */
    @ResponseBody
    @RequestMapping("/photo")
    public Map<String, Object> photo(String szCameraId, String szFilePath) {
        PhotographRequest photographRequest = new PhotographRequest(
                szCameraId,
                szFilePath,
                PhotographRequest.PhotographType.Png);
        Map<String, Object> map = new HashMap<>();
        map.put("status", true);
        PhotographResponse response = this.videoService.Photograph(photographRequest);
        map.put("data", response);
        return map;
    }


}
