package com.zc58s.springbootinfdemo.jna.service.business.infPtz;

import com.zc58s.springbootinfdemo.jna.response.PtzResponse;

/**
 * 云控制台命令抽象
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/4/16 11:06
 */
public interface InfPtzCommand {
    /**
     * @return
     */
    PtzResponse Ptz();
}
