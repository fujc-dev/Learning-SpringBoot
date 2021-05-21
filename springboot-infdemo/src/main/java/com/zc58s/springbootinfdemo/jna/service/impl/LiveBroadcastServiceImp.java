package com.zc58s.springbootinfdemo.jna.service.impl;

import com.zc58s.springbootinfdemo.jna.response.LiveResponse;
import com.zc58s.springbootinfdemo.jna.service.ILiveBroadcastService;
import org.springframework.stereotype.Service;

/**
 * 基于RTSP协议实现英飞拓直播推流
 *
 * <p>
 * 具体实现是，通过RTSP协议获取视频流，依赖FFMPEG将视频转码，然后将转码后的FLV流在网页端直接播放，通过消耗服务器CPU资源，提升网页体验和性能。
 * </p>
 *
 * <p>
 * 1、在服务器安装FFMPEG；
 * 2、在服务器安装nodejs；
 * </p>
 *
 * <p>
 * 转码流程：
 * 1、客户端调用一个摄像头打开的浏览请求；
 * 2、服务基于FFMPEG转码，将流推到node服务器；
 * 3、在node服务器反推一个FLV的WS协议流
 * </p>
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/2/4 10:17
 */
@Service
public class LiveBroadcastServiceImp implements ILiveBroadcastService {
    @Override
    public LiveResponse Play() {
        return null;
    }

    /**
     * 构建RTSP协议取流命令字符串
     *
     * @return
     */
    private String BuilderRTSP() {
        //  执行推流任务，拼接推流命令，视频流获取rtsp地址


        StringBuilder builder = new StringBuilder();

        return builder.toString();
    }


    /**
     * 基于FFMPEG将视频流转码，
     */
    public class LiveBroadcastRunnable implements Runnable {

        @Override
        public void run() {

        }
    }
}
