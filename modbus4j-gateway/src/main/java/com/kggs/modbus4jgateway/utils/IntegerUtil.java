package com.kggs.modbus4jgateway.utils;

/**
 * 工具类
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/6/21 9:19
 */
public class IntegerUtil {

    /**
     * 16进制转10进制
     *
     * @param hex
     * @return
     */
    public static int ConvertBy16Hex(String hex) {
        return Integer.parseInt(hex.substring(2), 16);//从第2个字符开始截取
    }

}
