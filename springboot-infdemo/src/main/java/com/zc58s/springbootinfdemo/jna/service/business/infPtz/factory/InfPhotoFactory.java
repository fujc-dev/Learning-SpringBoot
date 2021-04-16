package com.zc58s.springbootinfdemo.jna.service.business.infPtz.factory;

import com.zc58s.springbootinfdemo.jna.request.PhotographRequest;
import com.zc58s.springbootinfdemo.jna.response.PhotographResponse;
import com.zc58s.springbootinfdemo.jna.service.IPtzControlService;
import com.zc58s.springbootinfdemo.jna.service.IVideoService;
import com.zc58s.springbootinfdemo.jna.service.business.infPtz.util.infPhoto;
import com.zc58s.springbootinfdemo.jna.utils.Constants;
import com.zc58s.springbootinfdemo.jna.utils.DateUtil;
import com.zc58s.springbootinfdemo.jna.utils.SpringContextUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/4/16 15:54
 */
public class InfPhotoFactory {

    /**
     * @param stationId  站点主键
     * @param szCameraId 播放摄像机的ID或伪编码
     * @param serial     连拍数
     * @return
     */
    public static List<String> Photograph(String stationId, String szCameraId, int serial) {

        List<String> pictureList = new ArrayList<>();
        try {
            Boolean flag = true;
            IVideoService videoService = SpringContextUtil.getBean(IVideoService.class);
            for (int i = 0; i < serial; i++) {
                String uuid = UUID.randomUUID() + ".jpg";
                //以站点Id+image+年月创建存储拍照的文件夹
                StringBuilder pathStr = new StringBuilder(stationId + "/image/" + DateUtil.dateToString(new Date(), "yyyymm") + "/");
                //构建存储图片的绝对路径
                String szFilePath = infPhoto.BuilderFilePath(pathStr.toString(), uuid);
                PhotographRequest request = new PhotographRequest("", szFilePath, PhotographRequest.PhotographType.Jpeg);
                PhotographResponse response = videoService.Photograph(request);
                if (!response.getStatus()) {
                    flag = false;

                } else {
                    pictureList.add(Constants.CAMERA_BEFORE_PATH_IMAGE + pathStr + uuid);
                }
                Thread.sleep(1000 * Integer.parseInt(stationId));//抓图间隔
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {

        }
        return pictureList;
    }

}
