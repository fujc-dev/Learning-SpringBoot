package com.zc58s.springbootinfdemo.jna.service;

import com.zc58s.springbootinfdemo.jna.request.LivePlaybackRequest;
import com.zc58s.springbootinfdemo.jna.response.LivePlaybackResponse;
import com.zc58s.springbootinfdemo.jna.response.LiveResponse;

/**
 * 基于SDK的视频回放。
 *
 * <p>
 * 操作人员在界面，选择相应的视频，然后选择时间段，批量的调用播放，然后
 * </p>
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/5/19 13:34
 */
public interface ILivePlaybackService {

    /**
     * 回放录像
     * @param request
     * @return
     */
    LivePlaybackResponse StartBackPlay(LivePlaybackRequest request);
}
