package com.zc58s.springbootinfdemo.jna.service;

import com.zc58s.springbootinfdemo.jna.response.PtzResponse;

/**
 * 视频服务接口，用于对摄像头转向控制
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/27 14:04
 */
public interface IPztControlService {
    /**
     * 控制摄像机镜头向上移动
     *
     * @param szCameraId
     */
    PtzResponse PtzUp(String szCameraId);

    /**
     * 控制摄像机镜头向右移动
     *
     * @param szCameraId
     */
    PtzResponse PtzRight(String szCameraId);

    /**
     * 控制摄像机镜头向左移动
     *
     * @param szCameraId
     */
    PtzResponse PtzLeft(String szCameraId);

    /**
     * 控制摄像机镜头向左下移动
     *
     * @param szCameraId
     */
    PtzResponse PtzLeftDown(String szCameraId);

    /**
     * 控制摄像机镜头向左上移动
     *
     * @param szCameraId
     */
    PtzResponse PtzUpLeft(String szCameraId);

    /**
     * 控制摄像机镜头向右上移动
     *
     * @param szCameraId
     */
    PtzResponse PtzUpRight(String szCameraId);

    /**
     * 控制摄像机镜头向右下移动
     *
     * @param szCameraId
     */
    PtzResponse PtzRightDown(String szCameraId);

    /**
     * 控制摄像机镜头变小缩放
     *
     * @param szCameraId
     */
    PtzResponse PtzZooMin(String szCameraId);

    /**
     * 控制摄像机镜头变大缩放
     *
     * @param szCameraId
     */
    PtzResponse PtzZoomOut(String szCameraId);

    /**
     * 控制摄像机镜头终止移动
     *
     * @param szCameraId
     */
    PtzResponse PtzStop(String szCameraId);
}
