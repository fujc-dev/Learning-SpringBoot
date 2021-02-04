package com.zc58s.springbootinfdemo.jna.service.impl;

import com.zc58s.springbootinfdemo.jna.request.LiveRequest;
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
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/2/4 10:17
 */
@Service
public class LiveBroadcastServiceImp implements ILiveBroadcastService {
    @Override
    public LiveResponse Play(LiveRequest request) {
        return null;
    }

    /**
     * 构建RTSP协议取流命令字符串
     *
     * @return
     */
    private String BuilderRTSP() {
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
