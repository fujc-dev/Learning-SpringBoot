package com.zc58s.springbootinfdemo.jna.service.impl;

import com.sun.jna.Pointer;
import com.zc58s.springbootinfdemo.jna.request.LoginRequest;
import com.zc58s.springbootinfdemo.jna.response.LoginResponse;
import com.zc58s.springbootinfdemo.jna.response.PtzResponse;
import com.zc58s.springbootinfdemo.jna.sdk.InfNetSdk;
import com.zc58s.springbootinfdemo.jna.sdk.callback.Message;
import com.zc58s.springbootinfdemo.jna.sdk.callback.MessageCallback;
import com.zc58s.springbootinfdemo.jna.sdk.callback.SystemEventCallback;
import com.zc58s.springbootinfdemo.jna.service.IBusinessService;
import com.zc58s.springbootinfdemo.jna.service.IPztControlService;
import com.zc58s.springbootinfdemo.jna.service.IPlatformService;
import com.zc58s.springbootinfdemo.jna.base.Sdk;
import com.zc58s.springbootinfdemo.jna.service.base.ServiceBase;
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
public class VideoPlatformServiceImp extends ServiceBase implements IPlatformService, IPztControlService, IBusinessService {

    /*
     * 1、服务必须的单例的有效。所有的操作都依赖登录句柄
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
            //1、声明一个定时器，控制登录超时时间
            Timer login_timer = new Timer();
            login_timer.schedule(new LoginTimerTask(), request.getSzTimeout());
            //2、执行登录
            InfNetSdk.INSTANCE.INF_NET_Login(request.getSzUrl(), request.getSzUser(), request.getSzPassword(), request.getSzParam(), new MessageCallbackImp());
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
    public PtzResponse PtzUp(String szCameraId) {
        return super.Ptz(szCameraId, 0, super.m_Speed / 10.0F, 0);
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

    //TODO 经过测试发现，句柄为0时，请求不会被执行，不知道为啥，也就是说，要登录两次，当句柄为1时，请求有效，需要咨询英飞拓技术专家；
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
        public void invoke(String szCmdId, int nHandle, String szAction, String szResult, Pointer pUser) throws UnsupportedEncodingException {
            mCurrentMessage = new Message(nHandle, szAction, szResult);
            System.out.println(mCurrentMessage.toString());
            if (m_CurrentThread != null) {
                LockSupport.unpark(m_CurrentThread);
            }
        }
    }
}
