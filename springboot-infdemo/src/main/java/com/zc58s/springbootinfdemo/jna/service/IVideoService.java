package com.zc58s.springbootinfdemo.jna.service;

import com.zc58s.springbootinfdemo.jna.request.DownVideoRequest;
import com.zc58s.springbootinfdemo.jna.request.PhotographRequest;
import com.zc58s.springbootinfdemo.jna.request.VideoTapeRequest;

/**
 * 视频服务接口，用于对摄像头视频的操作
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/28 10:05
 */
public interface IVideoService {

    /**
     * 录像下载
     *
     * @param request
     */
    void Download(DownVideoRequest request);

    /**
     * 文档上说是快照，但是没有指明摄像头的唯一编号，感觉这个接口不对。
     *
     * @param request
     */
    void Photograph(PhotographRequest request);

    /**
     * 录像
     *
     * @param request
     */
    void VideoTape(VideoTapeRequest request);


}
