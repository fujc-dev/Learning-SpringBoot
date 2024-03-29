package com.zc58s.springbootinfdemo.controller;

import com.zc58s.springbootinfdemo.jna.request.*;
import com.zc58s.springbootinfdemo.jna.request.params.DownParam;
import com.zc58s.springbootinfdemo.jna.response.*;
import com.zc58s.springbootinfdemo.jna.sdk.InfNetSdk;
import com.zc58s.springbootinfdemo.jna.service.*;
import com.zc58s.springbootinfdemo.jna.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
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


    @Autowired
    public LoginController(IPlatformService platformService) {
        //this.platformService = PlatformFactory.CreateNew();
        this.platformService = platformService;
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
        String response = this.platformService.GetOrgs();
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
        String response = this.platformService.GetAllResourceList();
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
        String response = this.platformService.GetAllServer();
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
        PtzResponse response = this.platformService.PtzUp(szCameraId);
        this.platformService.PtzStop(szCameraId);
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
        PtzResponse response = this.platformService.PtzDown(szCameraId);
        this.platformService.PtzStop(szCameraId);
        map.put("data", response);
        return map;
    }

    //拍照

    /**
     * @return
     */
    @ResponseBody
    @RequestMapping("/photo")
    public Map<String, Object> photo(String szCameraId) {
        //有条件的话将这个图片地址放置到配置文件中
        String szFilePath = "D:\\infImgs\\" + DateUtil.formatByMillisecond() + ".png";
        PhotographRequest photographRequest = new PhotographRequest(
                szCameraId,
                szFilePath,
                PhotographRequest.PhotographType.Png);
        Map<String, Object> map = new HashMap<>();
        map.put("status", true);
        PhotographResponse response = this.platformService.Photograph(photographRequest);
        map.put("data", response);
        return map;
    }

    @ResponseBody
    @RequestMapping("/back_play")
    public Map<String, Object> StartBackPlay(String szCameraId, String dwBeginTime, String dwEndTime, String archiveServerUrl) throws ParseException {
        PlaybackRequest request = new PlaybackRequest(szCameraId, dwBeginTime, dwEndTime, archiveServerUrl);
        Map<String, Object> map = new HashMap<>();
        map.put("status", true);
        PlaybackResponse response = this.platformService.StartBackPlay(request);
        map.put("data", response);
        return map;
    }


    @ResponseBody
    @RequestMapping("/search_file")
    public Map<String, Object> SearchFile(String szCameraId, String dwBeginTime, String dwEndTime) throws ParseException {
        SearchFileRequest request = new SearchFileRequest(szCameraId, dwBeginTime, dwEndTime);
        Map<String, Object> map = new HashMap<>();
        map.put("status", true);
        SearchFileResponse response = this.platformService.SearchFile(request);
        map.put("data", response);
        return map;
    }

    @ResponseBody
    @RequestMapping("/down")
    public Map<String, Object> Down(String szCameraId, String dwBeginTime, String dwEndTime, String downloadType, String archiveServerUrl) throws ParseException {
        //有条件的话将这个图片地址放置到配置文件中
        String szFilePath = "D:\\infVideos\\anyRouter\\3b97f7edab8c4a7ea1f69c68ee1351f7\\video\\202106\\C883F7497F0F45E5B69938AA2E68B041";
        DownParam szDownParam = new DownParam(szCameraId, dwBeginTime, dwEndTime, "2x", downloadType, archiveServerUrl);
        DownVideoRequest request = new DownVideoRequest(szFilePath, szDownParam);
        Map<String, Object> map = new HashMap<>();
        map.put("status", true);
        DownVideoResponse response = this.platformService.Download(request);
        map.put("data", response);
        return map;
    }


}
