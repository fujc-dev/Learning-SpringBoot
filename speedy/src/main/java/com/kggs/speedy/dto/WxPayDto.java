package com.kggs.speedy.dto;

import java.io.Serializable;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/4/14 11:31
 */
public class WxPayDto implements Serializable {

    private static final long serialVersionUID = 1L;
    private String partyOrg;
    private String duesDate;
    private String money;   //缴费金额
    private String name;
    private String idCard;
    private String post;
    private String phone;
    private String orderId;
    private String receiverInfo;
    private String code;//获取openid临时凭证
    private String agyCode;
    private String agyName;

    public String getAgyCode() {
        return agyCode;
    }

    public void setAgyCode(String agyCode) {
        this.agyCode = agyCode;
    }

    public String getAgyName() {
        return agyName;
    }

    public void setAgyName(String agyName) {
        this.agyName = agyName;
    }

    public String getPartyOrg() {
        return partyOrg;
    }

    public void setPartyOrg(String partyOrg) {
        this.partyOrg = partyOrg;
    }

    public String getDuesDate() {
        return duesDate;
    }

    public void setDuesDate(String duesDate) {
        this.duesDate = duesDate;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getReceiverInfo() {
        return receiverInfo;
    }

    public void setReceiverInfo(String receiverInfo) {
        this.receiverInfo = receiverInfo;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
