package com.zc58s.springbootinfdemo;

import com.zc58s.springbootinfdemo.jna.service.IPtzControlService;
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
//            ILivePlaybackService platformService = new VideoPlatformServiceImp();
//            InfNetSdk.INSTANCE.INF_NET_Init();
//            String version = InfNetSdk.INSTANCE.INF_NET_GetVersion();
//            System.out.println("SDK Version: " + version);
//            String szCameraId ="3b97f7edab8c4a7ea1f69c68ee1351f7";
//            String dwBeginTime ="2021-05-20 09:01:01";
//            String dwEndTime ="2021-05-20 22:01:01";
//            PlaybackRequest request = new PlaybackRequest(szCameraId, dwBeginTime, dwEndTime);
//            Map<String, Object> map = new HashMap<>();
//            map.put("status", true);
//            LivePlaybackResponse response = platformService.StartBackPlay(request);
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

}
