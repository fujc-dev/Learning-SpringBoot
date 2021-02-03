package com.zc58s.springbootinfdemo.jna.base;

/**
 * 状态码
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/27 16:55
 */
public final class Sdk {
    //====================================================================
    //通用的操作部分
    //====================================================================
    /**
     * SDK默认返回的一个函数执行的错误，参数错误返回"error:InvalidPara"
     */
    public static int CODE_ERROR_INVALID_PARA = 500;
    /**
     * 操作成功
     */
    public static int CODE_SUCCESS = 90001;

    /**
     * 未知错误
     */
    public static int CODE_UNKNOWN = 99999;
    /**
     * 登录成功
     */
    public static int CODE_PLATFORM_LOGIN_SUCCESS = 10000;
    /**
     * 登录失败
     */
    public static int CODE_PLATFORM_LOGIN_ERROR = 10001;
    /**
     * 未登录平台，该操作须登录平台
     */
    public static int CODE_PLATFORM_NO_LOGIN_HANDLE = 10001;
    /**
     * 登录平台超时
     */
    public static int CODE_PLATFORM_LOGIN_TIMEOUT = 10002;


    //====================================================================
    //摄像头操作处理编码部分
    //====================================================================
    /**
     * 控制摄像头成功
     */
    public static int CODE_PTZ_SUCCESS = 10080;
    /**
     * 无效的摄像机识别码
     */
    public static int CODE_PTZ_CAMERA_ID_INVALID = 10081;
}
