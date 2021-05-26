package com.zc58s.springbootinfdemo.jna.service.impl;

import com.alibaba.fastjson.JSON;
import com.sun.jna.Pointer;
import com.zc58s.springbootinfdemo.jna.request.*;
import com.zc58s.springbootinfdemo.jna.response.*;
import com.zc58s.springbootinfdemo.jna.sdk.InfNetSdk;
import com.zc58s.springbootinfdemo.jna.sdk.callback.Message;
import com.zc58s.springbootinfdemo.jna.sdk.callback.MessageCallback;
import com.zc58s.springbootinfdemo.jna.sdk.callback.SystemEventCallback;
import com.zc58s.springbootinfdemo.jna.service.*;
import com.zc58s.springbootinfdemo.jna.base.Sdk;
import com.zc58s.springbootinfdemo.jna.service.base.ServiceBase;
import com.zc58s.springbootinfdemo.jna.request.PlaybackRequest;
import com.zc58s.springbootinfdemo.jna.service.impl.response.SearchFileSzResult;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.*;
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
public class InfPlatformServiceImp extends ServiceBase implements IPlatformService {

    /*
     * 1、服务必须的单例的有效。所有的操作都依赖登录句柄；
     *
     * 2、如果后续拍照性能有问题，需要维护一个登录请求一个服务；
     * */
    //
    private Logger logger = LoggerFactory.getLogger(InfPlatformServiceImp.class);
    /**
     * 登录服务器的回调函数 通过szAction的地址判断做什么动作
     */
    private MessageCallback _callback = new MessageCallbackImp();
    /**
     * 回放任务Maps
     */
    private Map<String, BackPlayThread> _backPlayThreadMap = new HashMap<>();

    public InfPlatformServiceImp() {
        System.out.println(new Date());
    }

    /**
     * 回调方法返回的消息对象
     */
    private Message mCurrentMessage;

    @Override
    public LoginResponse Login(LoginRequest request) {
        this.m_CurrentThread = Thread.currentThread();
        System.out.println("====>Login CurrentThread：" + m_CurrentThread);
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
                    request.getSzParam(), _callback);
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
            mCurrentMessage = null;
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
    public DownVideoResponse Download(DownVideoRequest request) {
        DownVideoResponse response = new DownVideoResponse();
        System.out.println("---------------请求参数---------------");
        System.out.println("登录句柄：" + this.m_nLoginHandle);
        System.out.println(request.toString());
        System.out.println("---------------下载视频---------------");

        System.out.println(request.toString());
        String szDownParam = JSON.toJSONString(request.getSzDownParam());
        String szDownloadTaskId = "";
        DownThread downThread = new DownThread(request.getSzFileName(), szDownParam, szDownloadTaskId);
        downThread.run();
        return response;
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
        System.out.println("---------------请求参数---------------");
        System.out.println("登录句柄：" + this.m_nLoginHandle);
        System.out.println(request.toString());
        System.out.println("---------------执行拍照---------------");
        //存储路径
        //文件格式
        //拍照成功与失败
        try {
            InfNetSdk.INSTANCE.INF_NET_SnapshotWithoutReal(this.m_nLoginHandle,
                    request.getSzCameraId(),
                    request.getSzFilePath(),
                    request.getiType().getType() - 1);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return response;
    }


    //===========================回放================================


    //TODO  客户端批量发起请求后，会出问题
    //TODO
    @Override
    public SearchFileResponse SearchFile(SearchFileRequest request) {
        System.out.println("---------------请求参数---------------");
        System.out.println("登录句柄：" + this.m_nLoginHandle);
        System.out.println(request.toString());
        System.out.println("---------------查询回放文件---------------");
        this.m_CurrentThread = Thread.currentThread();
        System.out.println("====>SearchFile CurrentThread：" + this.m_CurrentThread);
        SearchFileResponse response = new SearchFileResponse();
        //根据摄像头ID + 开始日期 + 结束日期 查询是否包含录像文件
        System.out.println(request.toString());
        String InvalidPara = InfNetSdk.INSTANCE.INF_NET_SearchFile(this.m_nLoginHandle,
                request.getSzSearchId(),
                request.getSzCameraId(),
                request.getDwBeginTime(),
                request.getDwEndTime(),
                request.getSzRecordType().getType(),
                request.getiBackType().getType());
        System.out.println(InvalidPara);
        if (error_str.equals(InvalidPara)) {
            return response;
        }
        //阻塞当前线程
        LockSupport.park(m_CurrentThread);
        //解除阻塞，继续执行
        if (this.mCurrentMessage != null) {
            System.out.println("=====>：2" + this.mCurrentMessage.getSzAction());
            if (SEARCH_FILE_STATUS_ACTION.equals(this.mCurrentMessage.getSzAction())) {
                //System.out.println("=====> ：" + this.mCurrentMessage.getSzResult());
                SearchFileSzResult szResult = JSON.parseObject(this.mCurrentMessage.getSzResult(), SearchFileSzResult.class);
                if (szResult.getMsg() != null && szResult.getMsg().getRecordFile() != null && szResult.getMsg().getRecordFile().size() > 0) {
                    System.out.println(szResult.getMsg().getRecordFile().size());
                    response.setCode(Sdk.CODE_PLAYBACK_FILE_SUCCESS);
                } else {
                    response.setCode(Sdk.CODE_PLAYBACK_FILE_INVALID);
                }
            } else {
                System.out.println("=====> ：" + "....");
            }
        }
        return response;
    }


    @Override
    public PlaybackResponse StartBackPlay(PlaybackRequest request) {
        System.out.println("---------------请求参数---------------");
        System.out.println("登录句柄：" + this.m_nLoginHandle);
        System.out.println(request.toString());
        System.out.println("---------------执行回放---------------");
        this.m_CurrentThread = Thread.currentThread();
        System.out.println("====>StartBackPlay CurrentThread：" + m_CurrentThread);
        PlaybackResponse response = new PlaybackResponse(request.getCameraId());
        //1、执行SDK的播放
        String szPlayParam = JSON.toJSONString(request);
        System.out.println(szPlayParam);
        //2、在回调函数中获取视频流，通过FFMPEG执行流转码。推流到流媒体服务器

        BackPlayThread playThread = new BackPlayThread(szPlayParam);
        playThread.run();
        //维护一个当前回放的历史记录，用于关闭回放记录，终止线程
        _backPlayThreadMap.put(request.getCameraId(), playThread);
        //4、停止回放，
        return response;
    }

    //===========================拍照、下载视频、录像================================

    /**
     * 登录定时器任务
     * <p>
     * 在登录的过程中，进行超时检测
     * </p>
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
     * 超时任务回调
     */
    public class OvertimeTask extends TimerTask {
        @Override
        public void run() {
            this.cancel();
            LockSupport.unpark(m_CurrentThread);
        }
    }

    /**
     * 回放线程，用于回放英飞拓视频，后续需要将创建一个维护回放的线程队列
     */
    public class BackPlayThread extends Thread {

        private String szPlayParam;
        private int nConHandle = -1;

        public BackPlayThread(String szPlayParam) {
            this.szPlayParam = szPlayParam;
        }

        @Override
        public void run() {
            try {
                //返回 nConHandle 回放句柄
                this.nConHandle = InfNetSdk.INSTANCE.INF_NET_StartBackPlay(m_nLoginHandle, this.szPlayParam,
                        (pUser, nHandle, szType, szError, pBuf, nSize) -> {
                            if (szType.equals("clientClose")) {
                                //
                            } else {
                                //
                            }
                            System.out.println("====> nHandle：" + nHandle);
                            System.out.println("====> szType：" + szType);
                            System.out.println("====> szError：" + szError);
                            System.out.println("====> pBuf：" + pBuf);
                            System.out.println("====> nSize：" + nSize);
                            System.out.println("====================================");
                            byte[] _pBuf = pBuf.getByteArray(0, nSize);
                            System.out.println(Arrays.toString(_pBuf));
                            System.out.println("====================================");
                            //厂家的技术人员说，pBuf是标准的H.264或者H.265
                            return 0;
                        });
                System.out.println("====> nConHandle：" + nConHandle);
                System.out.println("====================================");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void close() {
            //执行终止播放\回放
            InfNetSdk.INSTANCE.INF_NET_StopPlay(this.nConHandle);
            //终止当前现场
            this.interrupt();
        }
    }

    public class DownThread extends Thread {

        private String SzFileName;
        private String szDownParam;
        private String szDownloadTaskId;

        public DownThread(String SzFileName, String szDownParam, String szDownloadTaskId) {
            this.SzFileName = SzFileName;
            this.szDownParam = szDownParam;
            this.szDownloadTaskId = szDownloadTaskId;
        }

        @Override
        public void run() {
            InfNetSdk.INSTANCE.INF_NET_StartDownload(m_nLoginHandle, this.szDownParam, this.SzFileName, this.szDownloadTaskId);
            System.out.println("szDownloadTaskId：" + this.szDownloadTaskId);
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
            System.out.println("=====> ：" + szAction);
            if (m_CurrentThread != null) {
                //后续有必要，就增加一个公共判断
                if (LOGIN_STATUS_ACTION.equals(szAction))
                    LockSupport.unpark(m_CurrentThread);
                if (SEARCH_FILE_STATUS_ACTION.equals(szAction))
                    LockSupport.unpark(m_CurrentThread);
            }
        }
    }
}
