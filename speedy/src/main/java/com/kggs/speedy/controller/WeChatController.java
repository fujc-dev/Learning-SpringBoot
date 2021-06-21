package com.kggs.speedy.controller;

import com.kggs.speedy.config.WeChatConfig;
import com.kggs.speedy.dto.WxPayDto;
import com.kggs.speedy.utils.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/4/14 11:32
 */
@RequestMapping("/wechatPay")
public class WeChatController {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(WeChatController.class);

    @ResponseBody
    @RequestMapping("wxPay")
    public String wxPay(WxPayDto dto, HttpServletRequest request) {
        Object result = new Object();
        try {
            //获取客户端的ip地址
            String spbill_create_ip = getIpAddr(request);
            //获取openid
            String openid = WeAppUtil.getOpenId(dto.getCode());
            //订单号  uuid
            String outTradeNo = IdGen.uuid();
            //支付业务
            result = wxPay(spbill_create_ip, openid, outTradeNo, dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return PayUtil.toJson(result);
    }

    //获取IP
    private String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            return ip;
        }
        return request.getRemoteAddr();
    }

    /**
     * 缴纳费用
     *
     * @author jsy
     * @version 2020/12/24
     **/
    public Map wxPay(String spbill_create_ip, String openId, String orderNumber, WxPayDto dto) {
        Map<String, String> payMap = new HashMap<String, String>();//返回给小程序端需要的参数
        try {
            logger.info("【小程序支付】 统一下单开始, 订单编号=" + orderNumber);
            //商品名称
            String body = dto.getAgyName() + "-" + dto.getName() + "-测试费用";
            //获取客户端的ip地址
            BigDecimal ￥ = new BigDecimal(dto.getMoney());
            //组装参数，用户生成统一下单接口的签名
            logger.info("----------下单接口签名-------");
            Map<String, String> packageParams = new HashMap<>();
            //微信分配的小程序ID
            packageParams.put("appid", WeChatConfig.appid);
            //微信支付分配的商户号
            packageParams.put("mch_id", WeChatConfig.mch_id);
            //随机字符串
            packageParams.put("nonce_str", System.currentTimeMillis() / 1000 + "");
            //签名类型
            packageParams.put("sign_type", "MD5");
            //充值订单 商品描述
            packageParams.put("body", body);
            //商户订单号
            packageParams.put("out_trade_no", orderNumber);
            //订单总金额，单位为分
            packageParams.put("total_fee", ￥.multiply(BigDecimal.valueOf(100)).intValue() + "");
            //终端IP
            packageParams.put("spbill_create_ip", spbill_create_ip);
            //通知回调地址
            packageParams.put("notify_url", WeChatConfig.notify_url);
            //交易类型
            packageParams.put("trade_type", WeChatConfig.TRADETYPE);
            //用户标识
            packageParams.put("openid", openId);
            //第一次签名
            String sign = WXPayUtil.generateSignature(packageParams, WeChatConfig.key);
            packageParams.put("sign", sign);
            //调用支付定义下单API,返回预付单信息 prepay_id
            String result = HttpKit.post(WeChatConfig.pay_url, PaymentKit.toXml(packageParams));
            logger.info("调试模式_统一下单接口 返回XML数据：" + result);
            // 将解析结果存储在HashMap中
            Map<String, String> map = PaymentKit.xmlToMap(result);
            String return_code = map.get("return_code");//返回状态码
            String result_code = map.get("result_code");//返回状态码
            if (return_code.equals("SUCCESS") || return_code.equals(result_code)) {
                //返回的预付单信息
                String prepay_id = map.get("prepay_id");
                payMap.put("appId", WeChatConfig.appid);
                payMap.put("timeStamp", System.currentTimeMillis() / 1000 + "");
                payMap.put("nonceStr", System.currentTimeMillis() + "");
                payMap.put("package", "prepay_id=" + prepay_id);
                payMap.put("signType", "MD5");
                //再次签名，这个签名用于小程序端调用wx.requesetPayment方法
                String paySign = WXPayUtil.generateSignature(payMap, WeChatConfig.key);
                logger.info("=======================第二次签名：", paySign + "============ ======");
                payMap.put("paySign", paySign);
                payMap.put("status", "success");
                //更新订单信息
            } else {
                logger.info("----------统一下单失败-------");
                payMap.put("status", "error");
                return payMap;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return payMap;
    }


}
