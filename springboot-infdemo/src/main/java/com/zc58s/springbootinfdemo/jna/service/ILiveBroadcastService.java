package com.zc58s.springbootinfdemo.jna.service;

import com.zc58s.springbootinfdemo.jna.response.LiveResponse;

/**
 * 视频服务接口，直播服务
 * <p>
 * 依赖RTSP协议的视频推流
 * </p>
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/2/4 9:47
 */
public interface ILiveBroadcastService {

    /**
     * 基于RTSP协议的推流，实际网页端播放是调用的代理地址
     *
     * @param
     * @return
     */
    LiveResponse Play();



}
