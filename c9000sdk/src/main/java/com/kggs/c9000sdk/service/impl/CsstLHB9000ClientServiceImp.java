package com.kggs.c9000sdk.service.impl;



import com.kggs.c9000sdk.annotations.ServiceImpl;
import com.kggs.c9000sdk.factory.ServiceFactory;
import com.kggs.c9000sdk.service.ICsstLHB9000ClientService;
import com.kggs.c9000sdk.service.IntrusionAlarmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/7/19 9:25
 */
@ServiceImpl(classz = ICsstLHB9000ClientService.class)
public class CsstLHB9000ClientServiceImp implements ICsstLHB9000ClientService {

    private IntrusionAlarmService intrusionService = ServiceFactory.GetService(IntrusionAlarmService.class);
    private static final Logger logger = LoggerFactory.getLogger(CsstLHB9000ClientServiceImp.class);

    public boolean OperatePlace(String szIP, int nMachine, int nPlaceType, int nAreaNo) {
        return OperatePlace(szIP, 6769, nMachine, nPlaceType, nAreaNo);
    }

    public boolean OperatePlace(String szIP, int nPort, int nMachine, int nPlaceType, int nAreaNo) {
        try {
            //1、初始化
            boolean _init_status = intrusionService.Init();
            if (_init_status) {
                logger.info("CSST：----初始化平台Sdk成功，即将连接到平台");
                //2、连接平台
                boolean _connect_status = intrusionService.Connect(szIP, nPort, 5);
                if (_connect_status) {
                    //3、设置布防
                    boolean _execute_status = intrusionService.OperatePlace(nMachine,nPlaceType,nAreaNo);

                    return _execute_status;
                }
                logger.info("CSST：----连接平台失败");
                return false;
            }
            logger.info("CSST：----初始化平台Sdk失败");
            return false;
        } finally {
            //4、断开连接
            intrusionService.DisConnect();
            //5、释放SDK资源，在结束之前最后调用。
            intrusionService.UnInit();
        }
    }

    public boolean OperateRemove(String szIP, int nMachine, int nRemoveType, int nAreaNo) {
        return OperateRemove(szIP, 6769, nMachine, nRemoveType, nAreaNo);
    }

    public boolean OperateRemove(String szIP, int nPort, int nMachine, int nRemoveType, int nAreaNo) {
        try {
            //1、初始化
            boolean _init_status = intrusionService.Init();
            //2、连接平台
            boolean _connect_status = intrusionService.Connect(szIP, nPort, 5);
            //3、设置撤防
            boolean _execute_status = false;
        } finally {
            //4、断开连接
            intrusionService.DisConnect();
            //5、释放SDK资源，在结束之前最后调用。
            intrusionService.UnInit();
        }
        return false;
    }
}
