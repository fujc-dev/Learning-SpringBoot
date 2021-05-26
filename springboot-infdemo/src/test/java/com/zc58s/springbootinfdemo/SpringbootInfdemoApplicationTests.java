package com.zc58s.springbootinfdemo;

import com.alibaba.fastjson.JSON;
import com.zc58s.springbootinfdemo.jna.request.DownVideoRequest;
import com.zc58s.springbootinfdemo.jna.request.params.DownParam;
import com.zc58s.springbootinfdemo.jna.response.DownVideoResponse;
import com.zc58s.springbootinfdemo.jna.sdk.InfNetSdk;
import com.zc58s.springbootinfdemo.jna.service.IPlatformService;
import com.zc58s.springbootinfdemo.jna.service.IPtzControlService;
import com.zc58s.springbootinfdemo.jna.service.impl.InfPlatformServiceImp;
import com.zc58s.springbootinfdemo.jna.service.impl.response.SearchFileSzResult;
import com.zc58s.springbootinfdemo.jna.utils.DateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class SpringbootInfdemoApplicationTests {

    @Autowired
    private IPtzControlService service;

    @Test
    void contextLoads() {
        System.out.println(DateUtil.dateToString(new Date(), "yyyy-mm-dd hh24:mi:ss"));
        try {
//            System.out.println(service.PtzLeft("111"));
//            IPlaybackService platformService = new InfPlatformServiceImp();
//            InfNetSdk.INSTANCE.INF_NET_Init();
//            String version = InfNetSdk.INSTANCE.INF_NET_GetVersion();
//            System.out.println("SDK Version: " + version);
//            String szCameraId ="3b97f7edab8c4a7ea1f69c68ee1351f7";
//            String dwBeginTime ="2021-05-20 09:01:01";
//            String dwEndTime ="2021-05-20 22:01:01";
//            SearchFileRequest request = new SearchFileRequest(szCameraId, dwBeginTime, dwEndTime);
//            Map<String, Object> map = new HashMap<>();
//            map.put("status", true);
//            SearchFileResponse response = platformService.SearchFile(request);
//            System.out.println(response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @Test
    void log() {
//        Logger logger = LoggerFactory.getLogger(SpringbootInfdemoApplicationTests.class);
//        logger.debug("This is a debug message");//注意 spring 默认日志输出级别为 info 所以默认情况下 这句不会打印到控制台
//        logger.info("This is an info message");
//        logger.warn("This is a warn message");
//        logger.error("This is an error message");
    }

    @Test
    void ptz() {

        // InfPtzCommand command = InfPtzFactory.GetCommand("", 21, 0);
        //   System.out.println(command.Ptz());
    }

    @Test
    void down() {
        IPlatformService platformService = new InfPlatformServiceImp();
        InfNetSdk.INSTANCE.INF_NET_Init();
        String version = InfNetSdk.INSTANCE.INF_NET_GetVersion();
        System.out.println("SDK Version: " + version);
        String szCameraId = "3b97f7edab8c4a7ea1f69c68ee1351f7";
        String dwBeginTime = "2021-05-20 09:01:01";
        String dwEndTime = "2021-05-20 22:01:01";
        String archiveServerUrl = "nvr://192.168.1.229:5003";
        //有条件的话将这个图片地址放置到配置文件中
        String szFilePath = "D:\\infImgs\\" + DateUtil.formatByMillisecond() + ".avi";
        DownParam szDownParam = new DownParam(szCameraId, dwBeginTime, dwEndTime, "2x", "mp4", archiveServerUrl);
        DownVideoRequest request = new DownVideoRequest(szFilePath, szDownParam);
        DownVideoResponse response = platformService.Download(request);
        System.out.println(response);
    }

    @Test
    void ser() {
//        String jsonStr ="{\"code\":\"0\",\"desc\":\"success\",\"msg\":{\"searchId\":\"ed05b080-7a6e-4213-ac46-2edb7876fc19\",\"cameraId\":\"3b97f7edab8c4a7ea1f69c68ee1351f7\",\"recordFile\":[{\"serverUrl\":\"nvr://192.168.1.229:5003\",\"fileName\":\"60aa5176.IFV\",\"beginDateTime\":\"2021-05-23T12:58:30\",\"endDateTime\":\"2021-05-23T13:38:40\",\"recordType\":\"auto\",\"fileSize\":\"299\",\"cameraId\":null,\"lockStatus\":\"unlock\"},{\"serverUrl\":\"nvr://192.168.1.229:5003\",\"fileName\":\"60aa5ae0.IFV\",\"beginDateTime\":\"2021-05-23T13:38:40\",\"endDateTime\":\"2021-05-23T14:18:38\",\"recordType\":\"auto\",\"fileSize\":\"299\",\"cameraId\":null,\"lockStatus\":\"unlock\"},{\"serverUrl\":\"nvr://192.168.1.229:5003\",\"fileName\":\"60aa643e.IFV\",\"beginDateTime\":\"2021-05-23T14:18:38\",\"endDateTime\":\"2021-05-23T14:58:36\",\"recordType\":\"auto\",\"fileSize\":\"299\",\"cameraId\":null,\"lockStatus\":\"unlock\"},{\"serverUrl\":\"nvr://192.168.1.229:5003\",\"fileName\":\"60aa6d9c.IFV\",\"beginDateTime\":\"2021-05-23T14:58:36\",\"endDateTime\":\"2021-05-23T15:38:42\",\"recordType\":\"auto\",\"fileSize\":\"299\",\"cameraId\":null,\"lockStatus\":\"unlock\"},{\"serverUrl\":\"nvr://192.168.1.229:5003\",\"fileName\":\"60aa7702.IFV\",\"beginDateTime\":\"2021-05-23T15:38:42\",\"endDateTime\":\"2021-05-23T16:18:56\",\"recordType\":\"auto\",\"fileSize\":\"299\",\"cameraId\":null,\"lockStatus\":\"unlock\"},{\"serverUrl\":\"nvr://192.168.1.229:5003\",\"fileName\":\"60aa8070.IFV\",\"beginDateTime\":\"2021-05-23T16:18:56\",\"endDateTime\":\"2021-05-23T16:59:14\",\"recordType\":\"auto\",\"fileSize\":\"299\",\"cameraId\":null,\"lockStatus\":\"unlock\"},{\"serverUrl\":\"nvr://192.168.1.229:5003\",\"fileName\":\"60aa89e2.IFV\",\"beginDateTime\":\"2021-05-23T16:59:14\",\"endDateTime\":\"2021-05-23T17:39:28\",\"recordType\":\"auto\",\"fileSize\":\"299\",\"cameraId\":null,\"lockStatus\":\"unlock\"},{\"serverUrl\":\"nvr://192.168.1.229:5003\",\"fileName\":\"60aa9350.IFV\",\"beginDateTime\":\"2021-05-23T17:39:28\",\"endDateTime\":\"2021-05-23T18:19:46\",\"recordType\":\"auto\",\"fileSize\":\"299\",\"cameraId\":null,\"lockStatus\":\"unlock\"},{\"serverUrl\":\"nvr://192.168.1.229:5003\",\"fileName\":\"60aa9cc2.IFV\",\"beginDateTime\":\"2021-05-23T18:19:46\",\"endDateTime\":\"2021-05-23T19:00:08\",\"recordType\":\"auto\",\"fileSize\":\"299\",\"cameraId\":null,\"lockStatus\":\"unlock\"},{\"serverUrl\":\"nvr://192.168.1.229:5003\",\"fileName\":\"60aaa638.IFV\",\"beginDateTime\":\"2021-05-23T19:00:08\",\"endDateTime\":\"2021-05-23T19:40:30\",\"recordType\":\"auto\",\"fileSize\":\"299\",\"cameraId\":null,\"lockStatus\":\"unlock\"},{\"serverUrl\":\"nvr://192.168.1.229:5003\",\"fileName\":\"60aaafae.IFV\",\"beginDateTime\":\"2021-05-23T19:40:30\",\"endDateTime\":\"2021-05-23T20:20:52\",\"recordType\":\"auto\",\"fileSize\":\"299\",\"cameraId\":null,\"lockStatus\":\"unlock\"},{\"serverUrl\":\"nvr://192.168.1.229:5003\",\"fileName\":\"60aab924.IFV\",\"beginDateTime\":\"2021-05-23T20:20:52\",\"endDateTime\":\"2021-05-23T21:01:14\",\"recordType\":\"auto\",\"fileSize\":\"299\",\"cameraId\":null,\"lockStatus\":\"unlock\"},{\"serverUrl\":\"nvr://192.168.1.229:5003\",\"fileName\":\"60aac29a.IFV\",\"beginDateTime\":\"2021-05-23T21:01:14\",\"endDateTime\":\"2021-05-23T21:44:36\",\"recordType\":\"auto\",\"fileSize\":\"299\",\"cameraId\":null,\"lockStatus\":\"unlock\"},{\"serverUrl\":\"nvr://192.168.1.229:5003\",\"fileName\":\"60aaccc4.IFV\",\"beginDateTime\":\"2021-05-23T21:44:36\",\"endDateTime\":\"2021-05-23T22:33:15\",\"recordType\":\"auto\",\"fileSize\":\"299\",\"cameraId\":null,\"lockStatus\":\"unlock\"},{\"serverUrl\":\"nvr://192.168.1.229:5003\",\"fileName\":\"60aad82b.IFV\",\"beginDateTime\":\"2021-05-23T22:33:15\",\"endDateTime\":\"2021-05-23T23:24:17\",\"recordType\":\"auto\",\"fileSize\":\"299\",\"cameraId\":null,\"lockStatus\":\"unlock\"},{\"serverUrl\":\"nvr://192.168.1.229:5003\",\"fileName\":\"60aae421.IFV\",\"beginDateTime\":\"2021-05-23T23:24:17\",\"endDateTime\":\"2021-05-24T00:16:12\",\"recordType\":\"auto\",\"fileSize\":\"299\",\"cameraId\":null,\"lockStatus\":\"unlock\"},{\"serverUrl\":\"nvr://192.168.1.229:5003\",\"fileName\":\"60aaf04c.IFV\",\"beginDateTime\":\"2021-05-24T00:16:12\",\"endDateTime\":\"2021-05-24T01:07:03\",\"recordType\":\"auto\",\"fileSize\":\"299\",\"cameraId\":null,\"lockStatus\":\"unlock\"},{\"serverUrl\":\"nvr://192.168.1.229:5003\",\"fileName\":\"60aafc37.IFV\",\"beginDateTime\":\"2021-05-24T01:07:03\",\"endDateTime\":\"2021-05-24T01:57:41\",\"recordType\":\"auto\",\"fileSize\":\"299\",\"cameraId\":null,\"lockStatus\":\"unlock\"},{\"serverUrl\":\"nvr://192.168.1.229:5003\",\"fileName\":\"60ab0815.IFV\",\"beginDateTime\":\"2021-05-24T01:57:41\",\"endDateTime\":\"2021-05-24T02:47:35\",\"recordType\":\"auto\",\"fileSize\":\"299\",\"cameraId\":null,\"lockStatus\":\"unlock\"},{\"serverUrl\":\"nvr://192.168.1.229:5003\",\"fileName\":\"60ab13c7.IFV\",\"beginDateTime\":\"2021-05-24T02:47:35\",\"endDateTime\":\"2021-05-24T03:38:14\",\"recordType\":\"auto\",\"fileSize\":\"299\",\"cameraId\":null,\"lockStatus\":\"unlock\"},{\"serverUrl\":\"nvr://192.168.1.229:5003\",\"fileName\":\"60ab1fa6.IFV\",\"beginDateTime\":\"2021-05-24T03:38:14\",\"endDateTime\":\"2021-05-24T04:28:41\",\"recordType\":\"auto\",\"fileSize\":\"299\",\"cameraId\":null,\"lockStatus\":\"unlock\"},{\"serverUrl\":\"nvr://192.168.1.229:5003\",\"fileName\":\"60ab2b79.IFV\",\"beginDateTime\":\"2021-05-24T04:28:41\",\"endDateTime\":\"2021-05-24T05:16:46\",\"recordType\":\"auto\",\"fileSize\":\"299\",\"cameraId\":null,\"lockStatus\":\"unlock\"},{\"serverUrl\":\"nvr://192.168.1.229:5003\",\"fileName\":\"60ab36be.IFV\",\"beginDateTime\":\"2021-05-24T05:16:46\",\"endDateTime\":\"2021-05-24T06:00:13\",\"recordType\":\"auto\",\"fileSize\":\"299\",\"cameraId\":null,\"lockStatus\":\"unlock\"},{\"serverUrl\":\"nvr://192.168.1.229:5003\",\"fileName\":\"60ab40ed.IFV\",\"beginDateTime\":\"2021-05-24T06:00:13\",\"endDateTime\":\"2021-05-24T06:44:05\",\"recordType\":\"auto\",\"fileSize\":\"299\",\"cameraId\":null,\"lockStatus\":\"unlock\"},{\"serverUrl\":\"nvr://192.168.1.229:5003\",\"fileName\":\"60ab4b36.IFV\",\"beginDateTime\":\"2021-05-24T06:44:06\",\"endDateTime\":\"2021-05-24T07:28:16\",\"recordType\":\"auto\",\"fileSize\":\"299\",\"cameraId\":null,\"lockStatus\":\"unlock\"},{\"serverUrl\":\"nvr://192.168.1.229:5003\",\"fileName\":\"60ab5590.IFV\",\"beginDateTime\":\"2021-05-24T07:28:16\",\"endDateTime\":\"2021-05-24T08:12:54\",\"recordType\":\"auto\",\"fileSize\":\"299\",\"cameraId\":null,\"lockStatus\":\"unlock\"},{\"serverUrl\":\"nvr://192.168.1.229:5003\",\"fileName\":\"60ab6006.IFV\",\"beginDateTime\":\"2021-05-24T08:12:54\",\"endDateTime\":\"2021-05-24T08:56:27\",\"recordType\":\"auto\",\"fileSize\":\"299\",\"cameraId\":null,\"lockStatus\":\"unlock\"},{\"serverUrl\":\"nvr://192.168.1.229:5003\",\"fileName\":\"60ab6a3b.IFV\",\"beginDateTime\":\"2021-05-24T08:56:27\",\"endDateTime\":\"2021-05-24T09:40:06\",\"recordType\":\"auto\",\"fileSize\":\"299\",\"cameraId\":null,\"lockStatus\":\"unlock\"},{\"serverUrl\":\"nvr://192.168.1.229:5003\",\"fileName\":\"60ab7476.IFV\",\"beginDateTime\":\"2021-05-24T09:40:06\",\"endDateTime\":\"2021-05-24T10:22:48\",\"recordType\":\"auto\",\"fileSize\":\"299\",\"cameraId\":null,\"lockStatus\":\"unlock\"},{\"serverUrl\":\"nvr://192.168.1.229:5003\",\"fileName\":\"60ab7e78.IFV\",\"beginDateTime\":\"2021-05-24T10:22:48\",\"endDateTime\":\"2021-05-24T11:03:18\",\"recordType\":\"auto\",\"fileSize\":\"299\",\"cameraId\":null,\"lockStatus\":\"unlock\"},{\"serverUrl\":\"nvr://192.168.1.229:5003\",\"fileName\":\"60ab87f6.IFV\",\"beginDateTime\":\"2021-05-24T11:03:18\",\"endDateTime\":\"2021-05-24T11:42:40\",\"recordType\":\"auto\",\"fileSize\":\"299\",\"cameraId\":null,\"lockStatus\":\"unlock\"},{\"serverUrl\":\"nvr://192.168.1.229:5003\",\"fileName\":\"60ab9130.IFV\",\"beginDateTime\":\"2021-05-24T11:42:40\",\"endDateTime\":\"2021-05-24T12:21:42\",\"recordType\":\"auto\",\"fileSize\":\"299\",\"cameraId\":null,\"lockStatus\":\"unlock\"},{\"serverUrl\":\"nvr://192.168.1.229:5003\",\"fileName\":\"60ab9a56.IFV\",\"beginDateTime\":\"2021-05-24T12:21:42\",\"endDateTime\":\"2021-05-24T13:00:48\",\"recordType\":\"auto\",\"fileSize\":\"299\",\"cameraId\":null,\"lockStatus\":\"unlock\"},{\"serverUrl\":\"nvr://192.168.1.229:5003\",\"fileName\":\"60aba380.IFV\",\"beginDateTime\":\"2021-05-24T13:00:48\",\"endDateTime\":\"2021-05-24T13:40:02\",\"recordType\":\"auto\",\"fileSize\":\"299\",\"cameraId\":null,\"lockStatus\":\"unlock\"},{\"serverUrl\":\"nvr://192.168.1.229:5003\",\"fileName\":\"60abacb2.IFV\",\"beginDateTime\":\"2021-05-24T13:40:02\",\"endDateTime\":\"2021-05-24T14:19:24\",\"recordType\":\"auto\",\"fileSize\":\"299\",\"cameraId\":null,\"lockStatus\":\"unlock\"},{\"serverUrl\":\"nvr://192.168.1.229:5003\",\"fileName\":\"60abb5ec.IFV\",\"beginDateTime\":\"2021-05-24T14:19:24\",\"endDateTime\":\"2021-05-24T14:58:42\",\"recordType\":\"auto\",\"fileSize\":\"299\",\"cameraId\":null,\"lockStatus\":\"unlock\"}]}}\n";
//        SearchFileSzResult szResult = JSON.parseObject(jsonStr, SearchFileSzResult.class);
//        System.out.println(szResult);
    }

}
