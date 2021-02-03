package com.zc58s.springbootinfdemo.jna.service.impl;

import com.zc58s.springbootinfdemo.jna.request.LoginRequest;
import com.zc58s.springbootinfdemo.jna.response.LoginResponse;
import com.zc58s.springbootinfdemo.jna.response.PtzResponse;
import com.zc58s.springbootinfdemo.jna.sdk.InfNetSdk;
import com.zc58s.springbootinfdemo.jna.sdk.callback.MessageCallback;
import com.zc58s.springbootinfdemo.jna.service.IPztControlService;
import com.zc58s.springbootinfdemo.jna.service.IPlatformService;
import com.zc58s.springbootinfdemo.jna.base.Sdk;
import com.zc58s.springbootinfdemo.jna.service.base.ServiceBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.locks.LockSupport;

/**
 * <font size=6>
 * <b>英飞拓平台SDK调用封装与实现</b>
 * </font>
 *
 * <p><b><i>修改记录</i></b></p>
 * <p></p>
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/26 17:34
 */
@Service
public class VideoPlatformServiceImp extends ServiceBase implements IPlatformService, IPztControlService {

    private Logger logger = LoggerFactory.getLogger(VideoPlatformServiceImp.class);

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
            InfNetSdk.INSTANCE.INF_NET_Login(request.getSzUrl(), request.getSzUser(), request.getSzPassword(), request.getSzParam(), new MessageCallback() {
                @Override
                public void invoke(String szCmdId, int nHandle, String szAction, String szResult) throws UnsupportedEncodingException {
                    if (LOGIN_STATUS_ACTION.equals(szAction)) {
                        m_bServerReturned = true;
                        if (LOGIN_STATUS.equals(szResult)) {
                            m_nLoginHandle = nHandle;
                            m_bIsLogin = true;
                            response.setStatus(true);
                            response.setCode(Sdk.CODE_PLATFORM_LOGIN_SUCCESS);
                        } else {
                            response.setCode(Sdk.CODE_PLATFORM_LOGIN_ERROR);
                        }
                    }
                    LockSupport.unpark(m_CurrentThread);
                }
            });
            //3、阻塞当前方法，然后等待执行结果，要么超时，要么登录SDK的回调被执行
            LockSupport.park(m_CurrentThread);
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


}
