package com.zc58s.springbootinfdemo.jna.service.business.infPtz.util;

import com.zc58s.springbootinfdemo.jna.utils.Constants;
import com.zc58s.springbootinfdemo.jna.utils.DateUtil;
import com.zc58s.springbootinfdemo.jna.utils.InfUtil;

import java.util.Date;
import java.util.UUID;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/4/16 15:35
 */
public class infPhoto {

    /**
     * 构建英飞拓视频拍照图片文件存储地址
     *
     * @return
     */
    public static String BuilderFilePath(String pathStr, String photoName) {
        //站点Id+image+年月

        StringBuilder szFilePath = new StringBuilder();
        //文件存储存储地址，不出意外软网关会部署在windows服务器中
        szFilePath.append(Constants.CAMERA_IMAGE);
        szFilePath.append(pathStr);
        //检测并创建文件夹
        InfUtil.CheckFile(szFilePath.toString());
        //图片名称

        szFilePath.append(photoName);
        return szFilePath.toString();
    }
}
