package com.zc58s.springbootinfdemo.jna.service;

import com.zc58s.springbootinfdemo.jna.request.PlaybackRequest;
import com.zc58s.springbootinfdemo.jna.request.SearchFileRequest;
import com.zc58s.springbootinfdemo.jna.response.PlaybackResponse;
import com.zc58s.springbootinfdemo.jna.response.SearchFileResponse;

/**
 * 基于SDK的视频回放。
 *
 * <p>
 * 操作人员在界面，选择相应的视频，然后选择时间段，批量的调用播放。
 * </p>
 *
 * <p>
 * 1、用户选择了多个视频，在前端先调用SearchFile依次查询每个视频是否包含回放记录；
 * 2、当包含回放记录后，用户StartBackPlay开始回放；
 * 3、StartBackPlay会返回一个推流的地址；
 * </p>
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/5/19 13:34
 */
public interface IPlaybackService {

    /**
     * @param request
     * @return
     */
    SearchFileResponse SearchFile(SearchFileRequest request);

    /**
     * 回放录像
     *
     * @param request
     * @return
     */
    PlaybackResponse StartBackPlay(PlaybackRequest request);
}
