package com.zc58s.springbootinfdemo.jna.service.base;

import com.zc58s.springbootinfdemo.jna.base.Sdk;
import com.zc58s.springbootinfdemo.jna.response.PtzResponse;
import com.zc58s.springbootinfdemo.jna.sdk.InfNetSdk;
import org.apache.commons.lang.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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


    protected final String ASYNC_SEARCH_STATUS_ACTION = "/CMS/action/record/record/asyncSearch.do";
    protected final String SEARCH_FILE_STATUS_ACTION = "/CMS/action/record/record/getAsyncSearchResult.do";
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
        if (this.m_nLoginHandle < 0) {
            response.setCode(Sdk.CODE_PLATFORM_NO_LOGIN_HANDLE);
            return response;
        }
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
    public PtzResponse Preset(String szCameraId, int nPresetIndex) {
        PtzResponse response = new PtzResponse();
        if (this.m_nLoginHandle < 0) {
            response.setCode(Sdk.CODE_PLATFORM_NO_LOGIN_HANDLE);
            return response;
        }
        if (StringUtils.isEmpty(szCameraId)) {
            response.setCode(Sdk.CODE_PTZ_CAMERA_ID_INVALID);
            return response;
        }
        InfNetSdk.INSTANCE.INF_NET_PtzPreset(this.m_nLoginHandle, szCameraId, nPresetIndex);
        response.setStatus(true);
        response.setCode(Sdk.CODE_PTZ_SUCCESS);
        return response;
    }

    /**
     * 设置摄像机云台预置位
     *
     * @param szCameraId 播放摄像机的ID或伪编码
     * @param nIndex     预置位号
     * @return
     */
    public PtzResponse SetPreset(String szCameraId, int nIndex) {
        PtzResponse response = new PtzResponse();
        if (this.m_nLoginHandle < 0) {
            response.setCode(Sdk.CODE_PLATFORM_NO_LOGIN_HANDLE);
            return response;
        }
        if (StringUtils.isEmpty(szCameraId)) {
            response.setCode(Sdk.CODE_PTZ_CAMERA_ID_INVALID);
            return response;
        }
        InfNetSdk.INSTANCE.INF_NET_PtzPresetSet(this.m_nLoginHandle, szCameraId, nIndex);
        response.setStatus(true);
        response.setCode(Sdk.CODE_PTZ_SUCCESS);
        return response;
    }

    public boolean DateFormat(String datetimeStr) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        boolean fate_flag = true;
        try {
            Date date = format.parse(datetimeStr);
        } catch (ParseException e) {
            fate_flag = false;
        } finally {
            System.out.println("日期是否满足要求：" + fate_flag);
        }
        return fate_flag;
    }

}
