package com.zc58s.springbootinfdemo.jna.service.base;

import com.zc58s.springbootinfdemo.jna.base.Sdk;
import com.zc58s.springbootinfdemo.jna.response.PtzResponse;
import com.zc58s.springbootinfdemo.jna.sdk.InfNetSdk;
import org.apache.commons.lang.StringUtils;

/**
 * 基类
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/27 9:47
 */
public class ServiceBase {

    /**
     * 服务返回，根据厂家demo分析，这个参数用于控制登录回调函数是否会返回数据，默认为false，
     */
    protected boolean m_bServerReturned = false;
    /**
     * 登录状态，表示登录成功或者失败，默认为false。
     */
    protected boolean m_bIsLogin = false;
    /**
     * 登录句柄，默认为-1
     */
    protected int m_nLoginHandle = -1;
    /**
     * 登录成功后。回调szResult参数值
     */
    protected final String LOGIN_STATUS = "OK";
    /**
     * 登录成功后，回调szAction参数值
     */
    protected final String LOGIN_STATUS_ACTION = "/CMS/main/login.do";
    /**
     * 控制方法阻塞，记录当前方法的线程。
     */
    protected Thread m_CurrentThread;
    /**
     * 摄像机镜头转动给速度
     */
    protected int m_Speed = 5;
    /**
     *
     */
    protected String error_str = "error:InvalidPara";


    /**
     * 摄像机云台控制
     *
     * @param szCameraId 播放摄像机的ID或伪编码
     * @param fPan       Ptz左右动的参数(-1 - 1，0表示停止)
     * @param fTilt      Ptz上下动的参数(-1 - 1，0表示停止)
     * @param fZoom      tz缩放的参数(-1 - 1，0表示停止)
     * @return PtzResponse
     */
    public PtzResponse Ptz(String szCameraId, float fPan, float fTilt, float fZoom) {
        PtzResponse response = new PtzResponse();
        //登录平台句柄无效
        if (this.m_nLoginHandle < 0) {
            response.setCode(Sdk.CODE_PLATFORM_NO_LOGIN_HANDLE);
            return response;
        }
        //输入的摄像机唯一编号为空
        if (StringUtils.isEmpty(szCameraId)) {
            response.setCode(Sdk.CODE_PTZ_CAMERA_ID_INVALID);
            return response;
        }
        InfNetSdk.INSTANCE.INF_NET_PtzControl(this.m_nLoginHandle, szCameraId, fPan, fTilt, fZoom);
        response.setStatus(true);
        response.setCode(Sdk.CODE_PTZ_SUCCESS);
        return response;
    }

    /**
     * 摄像机云台预置位
     *
     * @param szCameraId   播放摄像机的ID或伪编码
     * @param nPresetIndex 预置位号
     * @return
     */
    public PtzResponse Ptz(String szCameraId, int nPresetIndex) {
        PtzResponse response = new PtzResponse();
        //登录平台句柄无效
        if (this.m_nLoginHandle < 0) {
            response.setCode(Sdk.CODE_PLATFORM_NO_LOGIN_HANDLE);
            return response;
        }
        //输入的摄像机唯一编号为空
        if (StringUtils.isEmpty(szCameraId)) {
            response.setCode(Sdk.CODE_PTZ_CAMERA_ID_INVALID);
            return response;
        }
        InfNetSdk.INSTANCE.INF_NET_PtzPreset(this.m_nLoginHandle, szCameraId, nPresetIndex);
        response.setStatus(true);
        response.setCode(Sdk.CODE_PTZ_SUCCESS);
        return response;
    }

}
