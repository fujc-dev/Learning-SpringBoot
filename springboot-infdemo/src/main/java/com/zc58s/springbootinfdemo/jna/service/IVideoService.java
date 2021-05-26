package com.zc58s.springbootinfdemo.jna.service;

import com.zc58s.springbootinfdemo.jna.request.DownVideoRequest;
import com.zc58s.springbootinfdemo.jna.request.PhotographRequest;
import com.zc58s.springbootinfdemo.jna.response.DownVideoResponse;
import com.zc58s.springbootinfdemo.jna.response.PhotographResponse;

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
    DownVideoResponse Download(DownVideoRequest request);

    /**
     * 文档上说是快照，但是没有指明摄像头的唯一编号，感觉这个接口不对。
     *
     * <p>
     * 修改记录：
     * 1、2021-5-19，厂家修改SDK，增加实时拍照
     * </p>
     *
     * @param request
     */
    PhotographResponse Photograph(PhotographRequest request);


}
