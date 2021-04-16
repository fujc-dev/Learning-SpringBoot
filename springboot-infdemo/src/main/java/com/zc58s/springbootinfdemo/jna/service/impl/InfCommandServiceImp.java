package com.zc58s.springbootinfdemo.jna.service.impl;

import com.zc58s.springbootinfdemo.bean.Command;
import com.zc58s.springbootinfdemo.bean.LiResource;
import com.zc58s.springbootinfdemo.jna.request.LoginRequest;
import com.zc58s.springbootinfdemo.jna.response.LoginResponse;
import com.zc58s.springbootinfdemo.jna.service.ICommandService;
import com.zc58s.springbootinfdemo.jna.service.IPlatformService;
import com.zc58s.springbootinfdemo.jna.service.IPztControlService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * 基于英飞拓视频设备实现的视频公共处理服务
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/4/16 10:00
 */
public class InfCommandServiceImp implements ICommandService {

    private IPztControlService pztService;
    private IPlatformService platformService;

    private LoginResponse Login() {
        LoginRequest request = new LoginRequest("10.20.2.165", "admin", "admin");
        return platformService.Login(request);
    }

    @Autowired
    public InfCommandServiceImp(IPlatformService platformService, IPztControlService pztService) {
        this.pztService = pztService;
        this.platformService = platformService;
    }

    @Override
    public Map<String, Object> ptzControl(Command bean, LiResource liResource, Map<String, Object> result) {
        //执行结果，0=成功，1=失败，默认成功
        result.put("code", 0);
        //获取当前平台的登录状态，如果为非登录状态。
        if (!this.platformService.LoginStatus()) {
            LoginResponse loginResponse = this.Login();
            if (!loginResponse.getStatus()) {
                result.put("code", 1); // 登录失败
                return result;
            }
        }

        /**
         *          海康定义的API约定
         *         dwPTZCommand：云台控制命令，11=焦距变大，12=焦距变小，13=焦点前调，14=焦点后调，15=光圈扩大，16=光圈扩小
         *         21=上仰，22=下俯，23=左转，24=右转，25=左上，26=右上，27=左下，28=右下，29=自动扫描
         *         8=设置预置点，9=清除预置点，39=转到预置点
         *         dwStop：云台开始或停止动作：0=开始，1=停止
         */

        int params2 = Integer.parseInt(bean.getParams2());
        int params3 = Integer.parseInt(bean.getParams3());
        //在英飞拓的视频中
        if (params2 == 8 || params2 == 9 || params2 == 39) {

        } else {

        }
        return null;
    }

    @Override
    public Map<String, Object> captureJpegPictureCamera(Command bean, LiResource liResource, Map<String, Object> result) {
        return null;
    }

    @Override
    public Map<String, Object> videoDownload(Command bean, LiResource liResource, Map<String, Object> result) {
        return null;
    }

    @Override
    public Map<String, Object> camerasBackPlay(Command bean, LiResource liResource, Map<String, Object> result) {
        return null;
    }

    @Override
    public Map<String, Object> camerasPlay(Command bean, LiResource liResource, Map<String, Object> result) {
        return null;
    }
}
