package com.zc58s.springbootinfdemo.jna.service.impl;

import com.alibaba.fastjson.JSON;
import com.sun.jna.Pointer;
import com.zc58s.springbootinfdemo.jna.request.*;
import com.zc58s.springbootinfdemo.jna.response.*;
import com.zc58s.springbootinfdemo.jna.sdk.InfNetSdk;
import com.zc58s.springbootinfdemo.jna.sdk.callback.Message;
import com.zc58s.springbootinfdemo.jna.sdk.callback.MessageCallback;
import com.zc58s.springbootinfdemo.jna.sdk.callback.StreamCallBack;
import com.zc58s.springbootinfdemo.jna.sdk.callback.SystemEventCallback;
import com.zc58s.springbootinfdemo.jna.service.*;
import com.zc58s.springbootinfdemo.jna.base.Sdk;
import com.zc58s.springbootinfdemo.jna.service.base.ServiceBase;
import com.zc58s.springbootinfdemo.jna.service.impl.params.SzPlayParam;
import com.zc58s.springbootinfdemo.jna.utils.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.locks.LockSupport;

/**
 * <font size=6>
 * <b>英飞拓平台SDK调用封装与实现</b>
 * </font>
 *
 * <p>1、服务必须的单例的有效。所有的操作都依赖登录句柄；</p>
 * <p>
 * 2、//
 * </p>
 * <p><b><i>修改记录</i></b></p>
 * <p></p>
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/26 17:34
 */
@Service
public class VideoPlatformServiceImp extends ServiceBase implements IPlatformService,
        IPtzControlService,
        IBusinessService,
        IVideoService,
        ILivePlaybackService {

    /*
     * 1、服务必须的单例的有效。所有的操作都依赖登录句柄；
     *
     * 2、如果后续拍照性能有问题，需要维护一个登录请求一个服务；
     * */
    //
    private Logger logger = LoggerFactory.getLogger(VideoPlatformServiceImp.class);

    public VideoPlatformServiceImp() {
        System.out.println(new Date());
    }

    /**
     * 回调方法返回的消息对象
     */
    private Message mCurrentMessage;

    @Override
    public LoginResponse Login(LoginRequest request) {
        this.m_CurrentThread = Thread.currentThread();
        LoginResponse response = new LoginResponse();
        try {
            this.m_bServerReturned = false;
            //1、声明一个定时器，控制登录超时时间，该模式是按照英飞拓给的C++源码模拟的。
            Timer login_timer = new Timer();
            login_timer.schedule(new LoginTimerTask(), request.getSzTimeout());
            //2、执行登录，这个回调需要经过测试，是否会在Login方法执行完毕后，释放MessageCallbackImp的资源
            InfNetSdk.INSTANCE.INF_NET_Login(request.getSzUrl(),
                    request.getSzUser(),
                    request.getSzPassword(),
                    request.getSzParam(),
                    new MessageCallbackImp());
            //3、阻塞当前方法，然后等待执行结果，要么超时，要么登录SDK的回调被执行
            LockSupport.park(m_CurrentThread);
            //解除阻塞，继续执行
            if (this.mCurrentMessage != null) {
                if (LOGIN_STATUS_ACTION.equals(this.mCurrentMessage.getSzAction())) {
                    m_bServerReturned = true;
                    if (LOGIN_STATUS.equals(this.mCurrentMessage.getSzResult())) {
                        m_nLoginHandle = this.mCurrentMessage.getSzHandle();
                        m_bIsLogin = true;
                        response.setStatus(true);
                        response.setCode(Sdk.CODE_PLATFORM_LOGIN_SUCCESS);
                    } else {
                        response.setCode(Sdk.CODE_PLATFORM_LOGIN_ERROR);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            m_CurrentThread = null;
        }
        return response;
    }

    @Override
    public void LoginOut() {
        String out_result = InfNetSdk.INSTANCE.INF_NET_Logout(m_nLoginHandle);
        logger.debug("INF_NET_Logout ----> " + out_result);
    }

    @Override
    public Boolean LoginStatus() {
        return super.m_bIsLogin;
    }

    @Override
    public PtzResponse PtzUp(String szCameraId) {
        return super.Ptz(szCameraId, 0, super.m_Speed / 10.0F, 0);
    }

    @Override
    public PtzResponse PtzDown(String szCameraId) {
        return super.Ptz(szCameraId, 0, -super.m_Speed / 10.0F, 0);
    }

    @Override
    public PtzResponse PtzRight(String szCameraId) {
        return super.Ptz(szCameraId, super.m_Speed / 10.0F, 0, 0);
    }

    @Override
    public PtzResponse PtzLeft(String szCameraId) {
        return super.Ptz(szCameraId, -super.m_Speed / 10.0F, 0, 0);
    }

    @Override
    public PtzResponse PtzLeftDown(String szCameraId) {
        return super.Ptz(szCameraId, -super.m_Speed / 10.0F, -super.m_Speed / 10.0F, 0);
    }

    @Override
    public PtzResponse PtzUpLeft(String szCameraId) {
        return super.Ptz(szCameraId, -super.m_Speed / 10.0F, super.m_Speed / 10.0F, 0);
    }

    @Override
    public PtzResponse PtzUpRight(String szCameraId) {
        return super.Ptz(szCameraId, super.m_Speed / 10.0F, super.m_Speed / 10.0F, 0);
    }

    @Override
    public PtzResponse PtzRightDown(String szCameraId) {
        return super.Ptz(szCameraId, super.m_Speed / 10.0F, -super.m_Speed / 10.0F, 0);
    }

    @Override
    public PtzResponse PtzZooMin(String szCameraId) {
        return super.Ptz(szCameraId, 0, 0, super.m_Speed / 10.0F);
    }

    @Override
    public PtzResponse PtzZoomOut(String szCameraId) {
        return super.Ptz(szCameraId, 0, 0, -super.m_Speed / 10.0F);
    }

    @Override
    public PtzResponse PtzStop(String szCameraId) {
        return super.Ptz(szCameraId, 0, 0, 0);
    }

    @Override
    public PtzResponse PtzPreset(String szCameraId, int nPresetIndex) {
        return super.Preset(szCameraId, nPresetIndex);
    }

    @Override
    public PtzResponse PtzSetPreset(String szCameraId, int nIndex) {
        return super.SetPreset(szCameraId, nIndex);
    }

    //TODO 经过测试发现，句柄为0时，偶尔请求不会被执行，不知道为啥，也就是说，要登录两次，当句柄为1时，请求有效，需要咨询英飞拓技术专家；
    //TODO 句柄虽是0，但是是从登录成果的回调返回的，意思就是登录是有效的。

    @Override
    public String GetOrgs() {
        //InfNetSdk.INSTANCE.INF_NET_SetSystemEventCallback(new SystemEventCallbackImp());
        String orgsStr = InfNetSdk.INSTANCE.INF_NET_GetAllOrgList(this.m_nLoginHandle);
        return orgsStr;
    }

    @Override
    public String GetAllResourceList() {
        //InfNetSdk.INSTANCE.INF_NET_SetSystemEventCallback(new SystemEventCallbackImp());
        System.out.println(this.m_nLoginHandle);
        String orgsStr = InfNetSdk.INSTANCE.INF_NET_GetAllResourceList(this.m_nLoginHandle);
        return orgsStr;
    }

    @Override
    public String GetAllServer() {
        String orgsStr = InfNetSdk.INSTANCE.INF_NET_GetAllServer(this.m_nLoginHandle);
        return orgsStr;
    }

    //===========================拍照、下载视频、录像================================
    @Override
    public void Download(DownVideoRequest request) {

    }

    @Override
    public PhotographResponse Photograph(PhotographRequest request) {
        PhotographResponse response = new PhotographResponse();
        if (this.m_nLoginHandle < 0) {
            response.setCode(Sdk.CODE_PLATFORM_NO_LOGIN_HANDLE);
            return response;
        }
        if (StringUtils.isEmpty(request.getSzCameraId())) {
            response.setCode(Sdk.CODE_PTZ_CAMERA_ID_INVALID);
            return response;
        }
        //TODO
        System.out.println("---------------请求参数---------------");
        System.out.println("登录句柄：" + this.m_nLoginHandle);
        System.out.println(request.toString());
        System.out.println("---------------执行拍照---------------");
        //存储路径
        //文件格式
        //拍照成功与失败
        String _execResult = InfNetSdk.INSTANCE.INF_NET_SnapshotWithoutReal(this.m_nLoginHandle,
                request.getSzCameraId(),
                request.getSzFilePath(),
                request.getiType().getType() - 1);
        System.out.println("INF_NET_SnapshotWithoutReal：" + _execResult);
        return response;
    }

    @Override
    public void VideoTape(VideoTapeRequest request) {

    }

    //===========================拍照、下载视频、录像================================
    @Override
    public LivePlaybackResponse StartBackPlay(LivePlaybackRequest request) {
        System.out.println(request.toString());
        LivePlaybackResponse response = new LivePlaybackResponse();
        if (this.m_nLoginHandle < 0) {
            response.setCode(Sdk.CODE_PLATFORM_NO_LOGIN_HANDLE);
            return response;
        }
        if (StringUtils.isEmpty(request.getSzCameraId())) {
            response.setCode(Sdk.CODE_PTZ_CAMERA_ID_INVALID);
            return response;
        }
        //1、根据摄像头ID + 开始日期 + 结束日期 查询是否包含录像文件，
        //当检测到视频文化后，播放

        //当不包含视频文件时，返回CODE_PLAYBACK_FILE_INVALID

        //  response.setCode(Sdk.CODE_PLAYBACK_FILE_INVALID);
        //error:InvalidPara
        // String InvalidPara = InfNetSdk.INSTANCE.INF_NET_SearchFile(this.m_nLoginHandle, request.getSzSearchId(), request.getSzCameraId(), request.getDwBeginTime(), request.getDwEndTime(), request.getSzRecordType().getType(), request.getiBackType().getType());
        //String InvalidPara = InfNetSdk.INSTANCE.INF_NET_SearchFile(this.m_nLoginHandle, request.getSzSearchId(), request.getSzCameraId(), 1621474659, 1621521459, "all", 1);
        //System.out.println(InvalidPara);

        //2、依次执行SDK的播放
        SzPlayParam playParam = new SzPlayParam();
        playParam.setCameraId(request.getSzCameraId());
        playParam.setBeginDateTime("2021-05-20T10:00:00");
        playParam.setEndDateTime("2021-07-20T20:00:00");
        playParam.setDateTimePosition(DateUtil.dateToString(new Date(), "yyyy-mm-dd hh24:mi:ss"));
        playParam.setArchiveServerUrl("nvr://192.168.1.229:5003");
        String szPlayParam = JSON.toJSONString(playParam);
        InfNetSdk.INSTANCE.INF_NET_StartBackPlay(this.m_nLoginHandle, szPlayParam, new StreamCallBack() {
            @Override
            public void invoke(Pointer pUser, int nHandle, String szType, String szError, byte[] pBuf, int nSize) {
                System.out.println(pBuf);
            }
        });
        System.out.println(playParam.toString());
        //3、在回调函数中获取视频流，通过FFMPEG执行流转码。推流到我们自己的流媒体服务器

        //4、停止回放，
        return response;
    }

    //===========================拍照、下载视频、录像================================

    /**
     * 登录定时器任务
     * <p>
     * 在登录的过程中，进行超时检测
     * </p>
     *
     * @author : fjc.dane@gmail.com
     * @createtime : 2021/1/27 10:42
     */
    public class LoginTimerTask extends TimerTask {
        @Override
        public void run() {
            if (!m_bServerReturned && !m_bIsLogin) {    //在指定的时间内，未登录成功，
                m_bServerReturned = true;
                LoginOut();   //执行一次退出登录
                this.cancel();
                LockSupport.unpark(m_CurrentThread);
            }
        }
    }

    /**
     * 系统事件回调接口，就目前测试来看，当我们修改了平台相关的参数时，会将信息回调到该接口
     * <p>在平台修改了数据，回调会被执行，感觉对目前业务没有什么帮助</p>
     */
    public class SystemEventCallbackImp implements SystemEventCallback {

        @Override
        public void invoke(int nHandle, String szResult, Pointer pUser) throws UnsupportedEncodingException {
            System.out.println(nHandle);
            System.out.println(szResult);
            System.out.println(pUser);
        }
    }

    /**
     * {@link MessageCallback}消息回调接口的实现，不出意外，所有的消息都是从这里返回的
     */
    public class MessageCallbackImp implements MessageCallback {

        @Override
        public void invoke(String szCmdId, int nHandle, String szAction, String szResult, Pointer pUser) {
            mCurrentMessage = new Message(nHandle, szAction, szResult);
            System.out.println(mCurrentMessage.toString());
            logger.debug(mCurrentMessage.toString());
            if (m_CurrentThread != null) {
                LockSupport.unpark(m_CurrentThread);
            }
        }
    }
}
