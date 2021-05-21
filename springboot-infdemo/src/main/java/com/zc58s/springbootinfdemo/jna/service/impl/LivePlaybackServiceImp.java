package com.zc58s.springbootinfdemo.jna.service.impl;

import com.zc58s.springbootinfdemo.jna.request.LivePlaybackRequest;
import com.zc58s.springbootinfdemo.jna.response.LivePlaybackResponse;
import com.zc58s.springbootinfdemo.jna.response.LiveResponse;
import com.zc58s.springbootinfdemo.jna.service.ILivePlaybackService;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/5/19 14:02
 */
public class LivePlaybackServiceImp implements ILivePlaybackService {
    @Override
    public LivePlaybackResponse StartBackPlay(LivePlaybackRequest request) {

        //执行登录？

        //1、根据摄像头ID + 开始日期 + 结束日期 查询是否包含录像文件

        //2、依次执行SDK的播放

        //3、在回调函数中获取视频流，通过FFMPEG执行流转码。推流到我们自己的流媒体服务器

        //4、停止回放，
        return null;
    }
}
