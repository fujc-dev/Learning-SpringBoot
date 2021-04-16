package com.zc58s.springbootinfdemo.jna.service.business.service;

import com.zc58s.springbootinfdemo.bean.Command;
import com.zc58s.springbootinfdemo.bean.LiResource;

import java.util.Map;

/**
 * 抽象软网关视频公共处理服务
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/4/16 9:39
 */
public interface ICommandService {

    /**
     * 摄像机云台控制，转向、调焦、预置位
     * @param bean
     * @param liResource
     * @param result
     * @return
     */
    Map<String, Object> ptzControl(Command bean, LiResource liResource, Map<String, Object> result);

    /**
     * 摄像机控制拍照，拍照张数，拍照间隔
     * @param bean
     * @param liResource
     * @param result
     * @return
     */
    Map<String, Object> captureJpegPictureCamera(Command bean, LiResource liResource, Map<String, Object> result);

    /**
     * 下载视频录像
     * @param bean
     * @param liResource
     * @param result
     * @return
     */
    Map<String, Object> videoDownload(Command bean, LiResource liResource, Map<String, Object> result);

    /**
     * 视频回放， 登录NVR，获取某个摄像头时间内回放流，存入临时文件，进行推流
     * @param bean
     * @param liResource
     * @param result
     * @return
     */
    Map<String, Object> camerasBackPlay(Command bean, LiResource liResource, Map<String, Object> result);

    /**
     * 视频直播，登录对应NVR，找到某个摄像头，进行实时推流
     * @param bean
     * @param liResource
     * @param result
     * @return
     */
    Map<String, Object> camerasPlay(Command bean, LiResource liResource, Map<String, Object> result);
}
