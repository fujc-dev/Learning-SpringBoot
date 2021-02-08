package com.zc58s.springbootinfdemo.jna.service;

import com.zc58s.springbootinfdemo.jna.response.OrgNodeResponse;

import java.util.List;

/**
 * 视频服务接口，业务接口，用于获取组织机构信息，以及视频设备信息。
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/2/4 14:45
 */
public interface IBusinessService {

    /**
     * 获取所有组织列表
     *
     * @return
     */
    String GetOrgs();

    /**
     * 获取所有组织列表
     *
     * @return
     */
    String GetAllResourceList();

    /**
     * 获取所有服务器信息：即v2200平台服务器模块，包含aps smt acs vqs kbs nvr
     *
     * @return
     */
    String GetAllServer();
}
