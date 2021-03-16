package com.zc58s.springbootinfdemo.ffmpeg;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/2/23 9:37
 */
public class Utils {

    //    直播命令：
    //    ffmpeg -re  -rtsp_transport tcp -i "rtsp://账号:密码@IP地址/Streaming/Channels/101?transportmode=unicast" -f flv -vcodec libx264 -vprofile baseline -acodec aac -ar 44100 -strict -2 -ac 1 -f flv -s 1280x720 -q 10 "rtmp://127.0.0.1:1935/live/test"
    //    回放命令：
    //    ffmpeg -re -rtsp_transport tcp -i "rtsp://账号:密码@IP地址/Streaming/tracks/101?starttime=20200302t000000z&endtime=20200302t235959z" -f flv -r 25 -s 1280x720 -an "rtmp://127.0.0.1:1935/live/test
    //ffmpeg
    private final String FFMPEG_BASE = "ffmpeg";


}
