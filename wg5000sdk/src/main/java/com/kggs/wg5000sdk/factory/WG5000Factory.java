package com.kggs.wg5000sdk.factory;

import com.kggs.wg5000sdk.enums.Status;
import com.kggs.wg5000sdk.service.WG5000ClientService;

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
    public static boolean Open(String ip, int port, String username, String password, String doorNumber) {
        try {
            WG5000ClientService service = new WG5000ClientService(
                    ip,
                    port,
                    username,
                    password,
                    Status.OPEN,
                    doorNumber);
            return service.Open();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 远程开门
     *
     * @param ip         ip地址
     * @param username   登录用户名
     * @param password   登录密码
     * @param doorNumber 门号
     */
    public static boolean Open(String ip, String username, String password, String doorNumber) {
        return Open(ip, 60006, username, password, doorNumber);
    }

}
