package com.zc58s.springbootinfdemo.jna.service.business.infPtz.factory;

import com.zc58s.springbootinfdemo.jna.request.PhotographRequest;
import com.zc58s.springbootinfdemo.jna.response.PhotographResponse;
import com.zc58s.springbootinfdemo.jna.service.IPtzControlService;
import com.zc58s.springbootinfdemo.jna.service.IVideoService;
import com.zc58s.springbootinfdemo.jna.service.business.infPtz.bean.InfImage;
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
     * //TODO  这个拍照是不是需要优化下，之前是只要包含一个Flag=false就表示拍照失败
     *
     * @param stationId  站点主键
     * @param szCameraId 播放摄像机的ID或伪编码
     * @param serial     连拍数
     * @return
     */
    public static List<InfImage> Photograph(String stationId, String szCameraId, int serial) {

        List<InfImage> pictureList = new ArrayList<>();
        try {
            Boolean flag = true;
            IVideoService videoService = SpringContextUtil.getBean(IVideoService.class);
            for (int i = 0; i < serial; i++) {
                String uuid = UUID.randomUUID() + ".jpg";
                //以站点Id+image+年月创建存储拍照的文件夹
                StringBuilder pathStr = new StringBuilder(stationId + "/image/" + DateUtil.dateToString(new Date(), "yyyymm") + "/");
                //构建存储图片的绝对路径
                String szFilePath = infPhoto.BuilderFilePath(pathStr.toString(), uuid);
                PhotographRequest request = new PhotographRequest(szCameraId, szFilePath, PhotographRequest.PhotographType.Jpeg);
                PhotographResponse response = videoService.Photograph(request);
                if (!response.getStatus()) {
                    flag = false;
                } else {
                    String path = Constants.CAMERA_BEFORE_PATH_IMAGE + pathStr + uuid;
                    InfImage image = new InfImage(path, flag);
                    pictureList.add(image);
                }
                Thread.sleep(1000 * Integer.parseInt(stationId));//抓图间隔
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {

        }
        return pictureList;
    }

    /**
     * @param images
     * @return
     */
    public static List<String> GetImageList(List<InfImage> images) {
        List<String> pictureList = new ArrayList<String>();
        for (InfImage img : images) {
            if (img.getFlag()) {
                pictureList.add(img.getPath());
            }
        }
        return pictureList;
    }

}
