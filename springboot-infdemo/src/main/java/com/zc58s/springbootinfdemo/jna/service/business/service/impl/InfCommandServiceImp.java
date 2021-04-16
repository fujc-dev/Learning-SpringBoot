package com.zc58s.springbootinfdemo.jna.service.business.service.impl;

import com.zc58s.springbootinfdemo.bean.Command;
import com.zc58s.springbootinfdemo.bean.LiResource;
import com.zc58s.springbootinfdemo.jna.request.LoginRequest;
import com.zc58s.springbootinfdemo.jna.request.PhotographRequest;
import com.zc58s.springbootinfdemo.jna.response.LoginResponse;
import com.zc58s.springbootinfdemo.jna.response.PhotographResponse;
import com.zc58s.springbootinfdemo.jna.response.PtzResponse;
import com.zc58s.springbootinfdemo.jna.service.business.infPtz.util.infPhoto;
import com.zc58s.springbootinfdemo.jna.service.business.service.ICommandService;
import com.zc58s.springbootinfdemo.jna.service.IPlatformService;
import com.zc58s.springbootinfdemo.jna.service.IPtzControlService;
import com.zc58s.springbootinfdemo.jna.service.IVideoService;
import com.zc58s.springbootinfdemo.jna.service.business.infPtz.InfPtzCommand;
import com.zc58s.springbootinfdemo.jna.service.business.infPtz.factory.InfPtzFactory;
import com.zc58s.springbootinfdemo.jna.utils.Constants;
import com.zc58s.springbootinfdemo.jna.utils.DateUtil;
import com.zc58s.springbootinfdemo.jna.utils.InfUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 基于英飞拓视频设备实现的视频公共处理服务
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/4/16 10:00
 */
@Service
public class InfCommandServiceImp implements ICommandService {

    private final static Logger logger = LoggerFactory.getLogger(InfCommandServiceImp.class);
    private final static String SUCCESS = "操作成功";
    private final static String LOGIN_ERROR = "登录失败，请确认设备网络是否正常";
    private final static String OPERATION_ERROR = "操作失败，请确认设备网络是否正常";
    private final static String COMMAND_INVALID = "操作失败，无效的控制命令";

    private IPtzControlService ptzService;
    private IPlatformService platformService;
    private IVideoService videoService;

    private Boolean Login() {
        LoginRequest request = new LoginRequest("10.20.2.165", "admin", "admin");
        LoginResponse response = platformService.Login(request);
        return response.getStatus();
    }

    @Autowired
    public InfCommandServiceImp(IPlatformService platformService, IVideoService videoService) {
        this.videoService = videoService;
        this.platformService = platformService;
    }

    @Override
    public Map<String, Object> ptzControl(Command bean, LiResource liResource, Map<String, Object> result) {
        //执行结果，0=成功，1=失败，默认成功
        result.put("code", 0);
        try {
            if (!this.platformService.LoginStatus()) {
                if (!this.Login()) {
                    result.put("code", 1);
                    result.put("msg", LOGIN_ERROR);
                    return result;
                }
            }
            int infCommandKey = Integer.parseInt(bean.getParams2());
            int infCommandValue = Integer.parseInt(bean.getParams3());
            //在英飞拓的视频中，没有定义操作的点，默认使用海康定义，因为前端控制使用的是海康的API
            InfPtzCommand command = InfPtzFactory.GetCommand("", infCommandKey, infCommandValue);
            if (command != null) {
                PtzResponse response = command.Ptz();
                result.put("code", response.getStatus());
            } else {
                result.put("code", 1); //command==null ，没有找到该命令
                result.put("msg", COMMAND_INVALID);
            }
        } catch (Exception e) {
            result.put("code", 1); // 当连不通时，直接抛异常，异常捕获即可
            result.put("msg", OPERATION_ERROR);
        }
        return result;
    }

    @Override
    public Map<String, Object> captureJpegPictureCamera(Command bean, LiResource liResource, Map<String, Object> result) {
        result.put("code", 0);
        try {
            if (!this.platformService.LoginStatus()) {
                if (!this.Login()) {
                    result.put("code", 1);
                    result.put("msg", LOGIN_ERROR);
                    return result;
                }
            }
            List<String> pictureList = new ArrayList<String>();
            int num = Integer.parseInt(bean.getParams2());//拍照张数
            Boolean flag = true;
            for (int i = 0; i < num; i++) {
                String uuid = UUID.randomUUID() + ".jpg";
                //以站点Id+image+年月创建存储拍照的文件夹
                StringBuilder pathStr = new StringBuilder(bean.getStationId() + "/image/" + DateUtil.dateToString(new Date(), "yyyymm") + "/");
                //构建存储图片的绝对路径
                String szFilePath = infPhoto.BuilderFilePath(pathStr.toString(), uuid);
                PhotographRequest request = new PhotographRequest("", szFilePath, PhotographRequest.PhotographType.Jpeg);
                PhotographResponse response = this.videoService.Photograph(request);
                if (!response.getStatus()) {
                    flag = false;
                    result.put("code", 2);
                } else {
                    pictureList.add(Constants.CAMERA_BEFORE_PATH_IMAGE + pathStr + uuid);
                }
                Thread.sleep(1000 * Integer.parseInt(bean.getParams3()));//抓图间隔
            }
            if (flag) {
                result.put("msg", SUCCESS);
                result.put("code", 0);
                result.put("attachment", pictureList);
                logger.info("--拍照成功--");
            } else {
                result.put("msg", OPERATION_ERROR);
                result.put("code", 1);
            }
        } catch (Exception e) {
            result.put("code", 1); // 当连不通时，直接抛异常，异常捕获即可
            result.put("msg", OPERATION_ERROR);
        }
        return result;
    }

    @Override
    public Map<String, Object> videoDownload(Command bean, LiResource liResource, Map<String, Object> result) {
        result.put("code", 0);
        if (!this.platformService.LoginStatus()) {
            if (!this.Login()) {
                result.put("code", 1);
                result.put("msg", LOGIN_ERROR);
                return result;
            }
        }
        String oldPath = "";
        String newPath = "";
        String pathStr = bean.getStationId() + "/video/" + DateUtil.dateToString(new Date(), "yyyymm") + "/";
        String filePath = Constants.CAMERA_IMAGE + pathStr;
        InfUtil.CheckFile(filePath);
        String uuid1 = UUID.randomUUID() + ".mp4";
        oldPath = filePath + uuid1;
        String uuid2 = UUID.randomUUID() + ".mp4";
        newPath = filePath + uuid2;
        //记录当前时间为报警时间
        Date startDate = new Date();
        Date startTime = InfUtil.CaleTime(startDate, bean.getParams2(), Constants.INT_0);
        //事后时长，得延时记录
        Date endTime = InfUtil.CaleTime(startDate, bean.getParams3(), Constants.INT_1);
        System.out.println("录像开始时间：" + DateUtil.dateToString(startTime, "yyyy-mm-dd hh24:mi:ss") + "---录像结束时间：" + DateUtil.dateToString(endTime, "yyyy-mm-dd hh24:mi:ss"));


        return result;
    }

    @Override
    public Map<String, Object> camerasBackPlay(Command bean, LiResource liResource, Map<String, Object> result) {
        result.put("code", 0);
        if (!this.platformService.LoginStatus()) {
            if (!this.Login()) {
                result.put("code", 1);
                result.put("msg", LOGIN_ERROR);
                return result;
            }
        }
        return result;
    }

    @Override
    public Map<String, Object> camerasPlay(Command bean, LiResource liResource, Map<String, Object> result) {
        result.put("code", 0);
        return result;
    }
}
