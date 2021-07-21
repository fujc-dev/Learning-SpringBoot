package com.kggs.wg5000sdk.factory;

import com.kggs.wg5000sdk.enums.Status;
import com.kggs.wg5000sdk.req.Instructions;
import com.kggs.wg5000sdk.req.RemoteAddress;
import com.kggs.wg5000sdk.service.WG5000RemoteService;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/7/21 9:40
 */
public class WG5000Factory {

    /**
     * 远程开门
     *
     * @param ip         ip地址
     * @param port       端口
     * @param username   登录用户名
     * @param password   登录密码
     * @param doorNumber 门号
     */
    public static void Open(String ip, int port, String username, String password, String doorNumber) {
        try {
            WG5000RemoteService service = new WG5000RemoteService();
            Instructions instructions = new Instructions(ip, port,username,password,doorNumber);
            service.Open(instructions);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
