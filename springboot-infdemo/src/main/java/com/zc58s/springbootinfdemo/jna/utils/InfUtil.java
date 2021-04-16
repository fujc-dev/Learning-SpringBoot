package com.zc58s.springbootinfdemo.jna.utils;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/4/16 14:19
 */
public class InfUtil {

    /**
     * 检测文件夹是否存在，如果不存在，并创建
     *
     * @param path
     */
    public static void CheckFile(String path) {
        File baseFile = new File(path);
        try {
            if (!baseFile.exists()) {
                baseFile.mkdirs();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 时间转换
     *
     * @param startDate 当前时间
     * @param param     时间
     * @param bj        0=减，1=加
     * @return
     */
    public static Date CaleTime(Date startDate, String param, int bj) {
        Calendar leftNow = Calendar.getInstance();
        leftNow.setTime(startDate);
        leftNow.add(Calendar.MINUTE, -15);
        if (bj == 0) {
            leftNow.add(Calendar.SECOND, -Integer.parseInt(param));
        } else {
            leftNow.add(Calendar.SECOND, Integer.parseInt(param));
        }
        return leftNow.getTime();
    }

    /**
     *
     * @param result
     * @param code
     * @param msg
     * @return
     */
    public static Map<String, Object> SendMap(Map<String, Object> result, int code, String msg) {
        result.put("code", code);
        result.put("msg", msg);
        return result;
    }
}
