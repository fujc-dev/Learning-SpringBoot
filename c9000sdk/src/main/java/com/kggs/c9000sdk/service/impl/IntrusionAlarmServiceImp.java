package com.kggs.c9000sdk.service.impl;

import com.kggs.c9000sdk.exception.CsstLHB9000Exception;
import com.kggs.c9000sdk.factory.StateFactory;
import com.kggs.c9000sdk.factory.state.Status;
import com.kggs.c9000sdk.rxbus.RxBus;
import com.kggs.c9000sdk.rxbus.event.base.Event;
import com.kggs.c9000sdk.sdk.SDK9000Client;
import com.kggs.c9000sdk.service.IntrusionAlarmService;
import com.kggs.c9000sdk.utils.DateUtil;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * 依照《CSST-R&D-豪恩9000主机系统网络版V1.0_SDK规范说明书》对进行进行二次包装
 * <p>
 * l
 * </p>
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/7/19 9:16
 */
@Service
public class IntrusionAlarmServiceImp implements IntrusionAlarmService {
    private SDK9000Client.SDK9000ClientCallBack _callback = new SDK9000ClientCallBackImp();

    public boolean Init() throws CsstLHB9000Exception {
        boolean _status = false;
        try {
            System.out.println("CSST：----Init Begin");
            _status = SDK9000Client.INSTANCE.csst_lhb9000_client_init(_callback, false);
            System.out.println("CSST：----" + _status + "，Init End");
        } catch (Exception e) {
            throw new CsstLHB9000Exception(e);
        }
        return _status;
    }

    public boolean UnInit() throws CsstLHB9000Exception {
        boolean _status = false;
        try {
            System.out.println("CSST：----UnInit Begin");
            _status = SDK9000Client.INSTANCE.csst_lhb9000_client_uninit();
            System.out.println("CSST：----" + _status + "，UnInit End");
        } catch (Exception e) {
            throw new CsstLHB9000Exception(e);
        }
        return _status;
    }

    public boolean Connect(String szIP, int nPort, int nTimeoutSecond) throws CsstLHB9000Exception {
        boolean _status = false;
        try {
            System.out.println("CSST：----Connect Begin");
            _status = SDK9000Client.INSTANCE.csst_lhb9000_client_connect(szIP, nPort, nTimeoutSecond);
            System.out.println("CSST：----" + _status + "，Connect End");
        } catch (Exception e) {
            throw new CsstLHB9000Exception(e);
        }
        return _status;
    }

    public boolean Connect(String szIP, int nTimeoutSecond) throws CsstLHB9000Exception {
        boolean _status = false;
        try {
            System.out.println("CSST：----Connect Begin");
            _status = SDK9000Client.INSTANCE.csst_lhb9000_client_connect(szIP, 6769, nTimeoutSecond);
            System.out.println("CSST：----" + _status + "，Connect End");
        } catch (Exception e) {
            throw new CsstLHB9000Exception(e);
        }
        return _status;
    }

    @Override
    public boolean QueryMachinelist() throws CsstLHB9000Exception {
        boolean _status = false;
        try {
            System.out.println("CSST：----Query Machinelist Begin");
            _status = SDK9000Client.INSTANCE.csst_lhb9000_client_get_machinelist();
            System.out.println("CSST：----" + _status + "，Query Machinelist End");
        } catch (Exception e) {
            throw new CsstLHB9000Exception(e);
        }
        return _status;
    }

    @Override
    public boolean QueryMachineAreainfo(int nMachine) throws CsstLHB9000Exception {
        boolean _status = false;
        try {
            System.out.println("CSST：----Query Machine Areainfo Begin");
            _status = SDK9000Client.INSTANCE.csst_lhb9000_client_get_machine_areainfo(nMachine);
            System.out.println("CSST：----" + _status + "，Query Machine Areainfo End");
        } catch (Exception e) {
            throw new CsstLHB9000Exception(e);
        }
        return _status;
    }

    public boolean DisConnect() throws CsstLHB9000Exception {
        System.out.println("CSST：----DisConnect Begin");
        boolean _status = false;
        try {
            _status = SDK9000Client.INSTANCE.csst_lhb9000_client_disconnect();
            System.out.println("CSST：----" + _status + "，DisConnect End");
        } catch (Exception e) {
            throw new CsstLHB9000Exception(e);
        }
        return _status;
    }

    public boolean OperatePlace(int nMachine, int nPlaceType, int nAreaNo) throws CsstLHB9000Exception {
        boolean _status = false;
        try {
            System.out.println("CSST：----OperatePlace Begin");
            _status = SDK9000Client.INSTANCE.csst_lhb9000_client_operate_place(nMachine, nPlaceType, nAreaNo);
            System.out.println("CSST：----" + _status + "，OperatePlace End");
        } catch (Exception e) {
            throw new CsstLHB9000Exception(e);
        }
        return _status;
    }

    public boolean OperateRemove(int nMachine, int nRemoveType, int nAreaNo) throws CsstLHB9000Exception {
        boolean _status = false;
        try {
            System.out.println("CSST：----OperateRemove Begin");
            _status = SDK9000Client.INSTANCE.csst_lhb9000_client_operate_remove(nMachine, nRemoveType, nAreaNo);
            System.out.println("CSST：----" + _status + "，OperateRemove End");
        } catch (Exception e) {
            throw new CsstLHB9000Exception(e);
        }
        return _status;
    }

    public static class SDK9000ClientCallBackImp implements SDK9000Client.SDK9000ClientCallBack {

        public void invoke(String szData, int nDataLength) throws UnsupportedEncodingException {
            System.out.println(DateUtil.dateToStrLong(new Date()) + "接收到消息：" + szData);
            Enum<Status> status = StateFactory.Format(szData);
            Event vo = StateFactory.Serialize(status, szData);
            RxBus.getDefault().post(vo);
        }
    }
}
