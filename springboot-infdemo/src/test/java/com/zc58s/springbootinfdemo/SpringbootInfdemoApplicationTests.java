package com.zc58s.springbootinfdemo;

import com.zc58s.springbootinfdemo.jna.request.DownVideoRequest;
import com.zc58s.springbootinfdemo.jna.request.params.DownParam;
import com.zc58s.springbootinfdemo.jna.response.DownVideoResponse;
import com.zc58s.springbootinfdemo.jna.sdk.InfNetSdk;
import com.zc58s.springbootinfdemo.jna.service.IPlatformService;
import com.zc58s.springbootinfdemo.jna.service.IPtzControlService;
import com.zc58s.springbootinfdemo.jna.service.impl.InfPlatformServiceImp;
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
//        IPlatformService platformService = new InfPlatformServiceImp();
//        InfNetSdk.INSTANCE.INF_NET_Init();
//        String version = InfNetSdk.INSTANCE.INF_NET_GetVersion();
//        System.out.println("SDK Version: " + version);
//        String szCameraId = "3b97f7edab8c4a7ea1f69c68ee1351f7";
//        String dwBeginTime = "2021-05-20 09:01:01";
//        String dwEndTime = "2021-05-20 22:01:01";
//        String archiveServerUrl  ="nvr://192.168.1.229:5003";
//        //有条件的话将这个图片地址放置到配置文件中
//        String szFilePath = "D:\\infImgs\\" + DateUtil.formatByMillisecond() + ".avi";
//        DownParam szDownParam = new DownParam(szCameraId, dwBeginTime, dwEndTime, "2x", archiveServerUrl);
//        DownVideoRequest request = new DownVideoRequest(szFilePath, szDownParam);
//        DownVideoResponse response = platformService.Download(request);
//        System.out.println(response);
    }

}
