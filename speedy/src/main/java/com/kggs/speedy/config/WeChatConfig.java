package com.kggs.speedy.config;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/4/14 11:30
 */
public class WeChatConfig {
    //小程序appid(开发者在微信公众平台查询)
    public static final String appid = "";
    //小程序appkey(开发者在微信公众平台查询)
    public static final String APP_SECRET = "";
    //微信支付的商户id(开发者在微信商户平台查询)
    public static final String mch_id = "";
    //微信支付的商户密钥(开发者在微信商户平台查询)
    public static final String key = "";
    //支付成功后的服务器回调url（后台随便的一个接口能接收到就行）
    public static final String notify_url = "";
    //签名方式，固定值
    public static final String SIGNTYPE = "MD5";
    //交易类型，小程序支付的固定值为JSAPI
    public static final String TRADETYPE = "JSAPI";
    //微信统一下单接口地址
    public static final String pay_url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
}
