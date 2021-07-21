package com.kggs.wg5000sdk.req;

import com.kggs.wg5000sdk.enums.Status;

/**
 * TCP远程控制出入口系统指令对象
 * <p>
 * N3000 -USER "abc" -PASSWORD "123" -Open "m001-1号"
 * </p>
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/7/21 8:59
 */
public class Instructions {

    private  RemoteAddress address;

    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 操作状态命令
     */
    private Status status;
    /**
     * 门号
     */
    private String doorNumber;


    public RemoteAddress getAddress() {
        return address;
    }

    public void setAddress(RemoteAddress address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDoorNumber() {
        return doorNumber;
    }

    public void setDoorNumber(String doorNumber) {
        this.doorNumber = doorNumber;
    }
}
