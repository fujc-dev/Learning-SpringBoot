package com.zc58s.springbootinfdemo.jna.sdk;

import com.sun.jna.*;
import com.sun.jna.win32.StdCallLibrary;

import com.zc58s.springbootinfdemo.jna.sdk.callback.MessageCallback;
import com.zc58s.springbootinfdemo.jna.sdk.callback.SystemEventCallback;
import com.zc58s.springbootinfdemo.jna.sdk.callback.streamCallBack;


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
    String filePath = "D:\\infSdk\\InfNetSdk";

    InfNetSdk INSTANCE = (InfNetSdk) Native.loadLibrary(filePath, InfNetSdk.class);


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

    /**
     * 设置摄像机云台预置位
     *
     * @param nHandle    登录返回的句柄
     * @param szCameraId 播放摄像机的ID或伪编码
     * @param nIndex     预置位号
     * @return
     */
    String INF_NET_PtzPresetSet(int nHandle, String szCameraId, int nIndex);

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
     * @param nPlayHandle    播放句柄
     * @param szSnapFileName 文件名
     * @param iType          快照类型，0为Bmp，1为Jpeg，2为Png
     * @return 函数执行的错误
     */
    String INF_NET_Snapshot(int nPlayHandle, String szSnapFileName, int iType);

    /**
     * 非实时模式快照，不播放视频快照
     * 只需要传递一个设备唯一编号就能进行拍照
     *
     * @param nLoginHandle 登录句柄
     * @param szCameraId   摄像头唯一编号
     * @param szFilePath   文件名
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
    void INF_NET_StartDownload(int nLoginHandle, String szDownParam, String szFileName, byte[]  szTastId);


    /**
     * 获取所有服务器信息：即v2200平台服务器模块，包含aps smt acs vqs kbs nvr
     *
     * @param nLoginHandle
     * @return 登录句柄
     */
    String INF_NET_GetAllServer(int nLoginHandle);


    //###########################################################################################
    // 回放
    //###########################################################################################

    /**
     * 查询摄像机的录像文件。
     *
     * @param nLoginHandle 登录返回的句柄
     * @param szSearchId   标志该次的录像搜索ID，格式为uuid
     * @param szCameraId   播放摄像机的ID
     * @param dwBeginTime  查询录像的开始时间(1970年1月1日开始的秒数1483642884)
     * @param dwEndTime    查询录像的结束时间(1970年1月1日开始的秒数1483743684)
     * @param szRecordType 搜索的录像类型 ("all" 所有 "auto" 自动 "alarm" 报警 一般赋值"all")
     * @param playBackType 回放类型（自适应0，内部1，外部2 一般赋值0）
     * //@param lTimeout     服务器返回结果的最大超时时间。
     * @return
     */
    String INF_NET_SearchFile(int nLoginHandle, String szSearchId, String szCameraId, int dwBeginTime, int dwEndTime, String szRecordType, int playBackType);


    /**
     * 回放录像
     *
     * @param nLoginHandle 登录返回的句柄
     * @param szPlayParam  回放历史视频参数 Json格式的字符串
     * @param lpCallback   流数据回调：媒体流格式见LPMEDIAFRAME_INFO
     * @return
     */
    int INF_NET_StartBackPlay(int nLoginHandle, String szPlayParam, streamCallBack lpCallback);


    /**
     * 停止实时预览视频（厂家那边的技术说，这个接口也是用来关闭回放的）
     *
     * @param nConHandle 实时流连接句柄
     * @return
     */
    int INF_NET_StopPlay(int nConHandle);

    /**
     * 回放控制（服务器）播放，暂停，快进，慢进，帧退，帧进，倒放
     *
     * @param nConHandle         回放流连接句柄
     * @param szPlayControlParam 回放控制的参数Json格式的字符串
     * @return
     */
    int INF_NET_StartBackControl(int nConHandle, String szPlayControlParam);
}
