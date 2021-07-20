package com.kggs.c9000sdk.service.impl;

import com.kggs.c9000sdk.annotations.ServiceImpl;
import com.kggs.c9000sdk.exception.CsstLHB9000Exception;
import com.kggs.c9000sdk.rxbus.RxBus;
import com.kggs.c9000sdk.rxbus.event.Event;
import com.kggs.c9000sdk.sdk.LHB9000NetSdk;
import com.kggs.c9000sdk.sdk.callback.SDK9000ClientCallBack;
import com.kggs.c9000sdk.service.IntrusionAlarmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

/**
 * 依照《CSST-R&D-豪恩9000主机系统网络版V1.0_SDK规范说明书》对进行进行二次包装
 * <p>
 * l
 * </p>
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/7/19 9:16
 */
@ServiceImpl(classz = IntrusionAlarmService.class)
public class IntrusionAlarmServiceImp implements IntrusionAlarmService {

    private static final Logger log = LoggerFactory.getLogger(IntrusionAlarmService.class);

    public boolean Init() throws CsstLHB9000Exception {
        boolean _status = false;
        try {
            log.debug("CSST：----Init Begin");
            SDK9000ClientCallBack _callback = new SDK9000ClientCallBackImp();
            _status = LHB9000NetSdk.INSTANCE.csst_lhb9000_client_init(_callback, true);
            log.debug("CSST：----，{}，Init End", _status);
        } catch (Exception e) {
            throw  new CsstLHB9000Exception(e);
        }
        return _status;
    }

    public boolean UnInit() throws CsstLHB9000Exception {
        boolean _status = false;
        try {
            log.debug("CSST：----UnInit Begin");
            _status = LHB9000NetSdk.INSTANCE.csst_lhb9000_client_uninit();
            log.debug("CSST：----，{}，UnInit End", _status);
        } catch (Exception e) {
            throw  new CsstLHB9000Exception(e);
        }
        return _status;
    }

    public boolean Connect(String szIP, int nPort, int nTimeoutSecond) throws CsstLHB9000Exception {
        boolean _status = false;
        try {
            log.debug("CSST：----Connect Begin");
            _status = LHB9000NetSdk.INSTANCE.csst_lhb9000_client_connect(szIP, nPort, nTimeoutSecond);
            log.debug("CSST：----，{}，Connect End", _status);
        } catch (Exception e) {
           throw  new CsstLHB9000Exception(e);
        }
        return _status;
    }

    public boolean Connect(String szIP, int nTimeoutSecond) throws CsstLHB9000Exception {
        boolean _status = false;
        try {
            log.debug("CSST：----Connect Begin");
            _status = LHB9000NetSdk.INSTANCE.csst_lhb9000_client_connect(szIP, 6769, nTimeoutSecond);
            log.debug("CSST：----，{}，Connect End", _status);
        } catch (Exception e) {
            throw  new CsstLHB9000Exception(e);
        }
        return _status;
    }

    public boolean DisConnect() throws CsstLHB9000Exception {
        log.debug("CSST：----DisConnect Begin");
        boolean _status = false;
        try {
            _status = LHB9000NetSdk.INSTANCE.csst_lhb9000_client_disconnect();
            log.debug("CSST：----，{}，DisConnect End", _status);
        } catch (Exception e) {
            throw  new CsstLHB9000Exception(e);
        }
        return _status;
    }

    public boolean OperatePlace(int nMachine, int nPlaceType, int nAreaNo) throws CsstLHB9000Exception {
        boolean _status = false;
        try {
            log.debug("CSST：----OperatePlace Begin");
            _status = LHB9000NetSdk.INSTANCE.csst_lhb9000_client_operate_place(nMachine, nPlaceType, nAreaNo);
            log.debug("CSST：----，{}，OperatePlace End", _status);
        } catch (Exception e) {
            throw  new CsstLHB9000Exception(e);
        }
        return _status;
    }

    public boolean OperateRemove(int nMachine, int nRemoveType, int nAreaNo) throws CsstLHB9000Exception {
        boolean _status = false;
        try {
            log.debug("CSST：----OperateRemove Begin");
            _status = LHB9000NetSdk.INSTANCE.csst_lhb9000_client_operate_remove(nMachine, nRemoveType, nAreaNo);
            log.debug("CSST：----，{}，OperateRemove End", _status);
        } catch (Exception e) {
            throw  new CsstLHB9000Exception(e);
        }
        return _status;
    }

    public final class SDK9000ClientCallBackImp implements SDK9000ClientCallBack {

        public void invoke(String szData, int nDataLength) throws UnsupportedEncodingException {
            //
            log.debug(szData);
            //RxBus.getDefault().post(new Event(connectNotify));
        }
    }
}
