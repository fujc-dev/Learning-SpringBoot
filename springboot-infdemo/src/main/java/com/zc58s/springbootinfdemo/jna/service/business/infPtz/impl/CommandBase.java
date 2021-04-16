package com.zc58s.springbootinfdemo.jna.service.business.infPtz.impl;

import com.zc58s.springbootinfdemo.jna.service.business.infPtz.InfPtzCommand;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/4/16 11:35
 */
public abstract class CommandBase implements InfPtzCommand {

    protected String szCameraId;

    public CommandBase(String szCameraId) {
        this.szCameraId = szCameraId;
    }
}
