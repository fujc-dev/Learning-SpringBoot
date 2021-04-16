package com.zc58s.springbootinfdemo.jna.sdk;

import com.sun.jna.Native;
import com.sun.jna.NativeLong;
import com.sun.jna.win32.StdCallLibrary;
import com.zc58s.springbootinfdemo.jna.sdk.callback.MessageCallback;
import com.zc58s.springbootinfdemo.jna.sdk.callback.SystemEventCallback;
import com.zc58s.springbootinfdemo.jna.sdk.callback.StreamCallBack;

/**
 * V2200平台sdk，基于英飞拓Win32动态链接库InfNetSdk.DLL的Java调用封装。
 *
 * <p>
 * SDK版本号：V2.0.0.201704101636
 * </p>
 * <p>
 * 基于jna框架对C++动态库进行封装。
 * </p>
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/25 17:19
 */
public interface InfNetSdk extends StdCallLibrary {

    //绝对地址
    String filePath = "C:\\Users\\Lenovo\\Documents\\WeChat Files\\xhh_lite\\FileStorage\\File\\2021-01\\VMS V2200 2.0 sdk 20190425\\VMS V2200 2.0 sdk 20190425\\app\\InfNetSdk.dll";

    InfNetSdk INSTANCE = Native.loadLibrary(filePath, InfNetSdk.class);


    enum enPTZOtherType {
        Ptz_IrisOn,
        Ptz_IrisOff,
        Ptz_FocusFar,
        Ptz_FocusNear,
        Ptz_AuxWiperOn,
        Ptz_AuxWiperOff,
        Ptz_AuxLightOn,
        Ptz_AuxLightOff,
        Ptz_AuxHeaterOn,
        Ptz_AuxHeaterOff,
        Ptz_AfFocusFar,
        Ptz_AfFocusNear
    }


    //###########################################################################################
    // SDK初始化
    //###########################################################################################
    void INF_NET_Init();

    void INF_NET_UnInit();

    //###########################################################################################
    // 登录、注销
    //###########################################################################################

    /**
     * V2200平台sdk库登录
     *
     * @param szUrl      登录平台的服务器的地址
     * @param szUser     登录平台的服务器的用户名
     * @param szPassword 登录平台的服务器的密码
     * @param szParam    登录平台的服务器的参数，一般为""
     * @param callback   登录平台的服务器的回调函数 通过szAction的地址判断做什么动作
     */
    int INF_NET_Login(String szUrl, String szUser, String szPassword, String szParam, MessageCallback callback);

    /**
     * @param nLoginHandle
     * @return
     */
    String INF_NET_Logout(int nLoginHandle);


    /**
     * 设置局域网或公网访问
     *
     * @param szNetType 局域网：”LAN”; 公网：”WAN”
     */
    void INF_NET_SetNetType(String szNetType);

    /**
     * 获取sdk版本信息
     *
     * @return
     */
    String INF_NET_GetVersion();


    /**
     * 设置事件回调
     *
     * @param callback
     */
    void INF_NET_SetSystemEventCallback(SystemEventCallback callback);


    //###########################################################################################
    // PTZ控制
    //###########################################################################################

    /**
     * 摄像机云台控制
     *
     * @param nLoginHandle 登录返回的句柄
     * @param szCameraId   播放摄像机的ID或伪编码
     * @param fPan         Ptz左右动的参数(-1 - 1，0表示停止)
     * @param fTilt        Ptz上下动的参数(-1 - 1，0表示停止)
     * @param fZoom        Ptz缩放的参数(-1 - 1，0表示停止)
     * @return 函数执行的错误 参数错误返回"error:InvalidPara" 其它错误回调返回
     */
    String INF_NET_PtzControl(int nLoginHandle, String szCameraId, float fPan, float fTilt, float fZoom);

    /**
     * 摄像机云台预置位
     *
     * @param nLoginHandle 登录返回的句柄
     * @param szCameraId   播放摄像机的ID或伪编码
     * @param nPresetIndex 预置位号
     * @return
     */
    String INF_NET_PtzPreset(int nLoginHandle, String szCameraId, int nPresetIndex);

    //###########################################################################################
    // 资源获取
    //###########################################################################################
    String INF_NET_GetAllOrgList(int nLoginHandle);

    String INF_NET_GetResourceListByorgId(int nLoginHandle, String szOrgId);

    String INF_NET_GetAllResourceList(int nLoginHandle);

    String INF_NET_GetCameraInfoByCameraId(int nHandle, String szCameraId);

    String INF_NET_GetAllCameraList(int nLoginHandle, NativeLong nPage, NativeLong nLimit, String szFilter, String szSort);

    String INF_NET_GetCameraList(int nHandle, String szOrgId, NativeLong nPage, NativeLong nLimit, NativeLong NativeLong, boolean bRescursion, String szQueryParam, String szParamValue);


    //设备状态
    String INF_NET_GetDeviceState(int nHanlde, String szDeviceType, String szOrgIdList, String fieldsCN);

    String INF_NET_GetDeviceResource(int nHanlde, NativeLong nPage, NativeLong nLimit, String orgId, String resourceName, boolean bRescursion);


    //###########################################################################################
    // 快照
    //###########################################################################################

    /**
     * 快照，这里面缺少一个本地存储的路径，后续要取文件，需要从平台拿
     *
     * @param nPlayHandle    登录句柄
     * @param szSnapFileName 文件名
     * @param iType          快照类型，0为Bmp，1为Jpeg，2为Png
     * @return 函数执行的错误
     */
    String INF_NET_Snapshot(int nPlayHandle, String szSnapFileName, int iType);

    /**
     * 只需要传递一个设备唯一编号就能进行拍照
     *
     * @param nLoginHandle 登录句柄
     * @param szCameraId   摄像头唯一编号
     * @param szFilePath   文件路径
     * @param iImageType   快照类型，0为Bmp，1为Jpeg，2为Png
     * @return
     */
    String INF_NET_SnapshotWithoutReal(int nLoginHandle, String szCameraId, String szFilePath, int iImageType);

    /**
     * 手动录像，开始
     *
     * @param nLoginHandle
     * @param szCameraId
     * @return
     */
    String INF_NET_StartRecord(int nLoginHandle, String szCameraId);

    /**
     * 手动录像，停止
     *
     * @param nLoginHandle
     * @param szCameraId
     * @return
     */
    String INF_NET_StopRecord(int nLoginHandle, String szCameraId);

    //下载
    void INF_NET_StartDownload(int nLoginHandle, String szDownParam, String szFileName, String szTastId);


    /**
     * 获取所有服务器信息：即v2200平台服务器模块，包含aps smt acs vqs kbs nvr
     *
     * @param nLoginHandle
     * @return 登录句柄
     */
    String INF_NET_GetAllServer(int nLoginHandle);


}
