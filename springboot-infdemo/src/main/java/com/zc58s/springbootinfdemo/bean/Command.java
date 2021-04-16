package com.zc58s.springbootinfdemo.bean;

import java.util.Date;

/**
 * @author 
 */
public class Command {
	private String addressCode;//IP地址
	private String port;//端口号
	private String username;//账号
	private String password;//密码
	private String channel;//通道号
	private String subCode;//子系统编码
	private String liResourceId;//资源点ID
	private String liResourceType;//资源点类型
	private String source;//来源，系统或者某用户
	private String params;//指令信息
	private String params2;//参数1
	private String params3;//参数2
	private String paramsName;//指令内容
	private String userId;//当前操作用户ID，如果是系统自动执行则传0
	private int levelType;//部署层级类型，1=站点系统，2=线路/路网系统；为第一要素
	private int controlType;//指令类型，1=联动控制，2=预案控制，3=手动控制；为第二要素
	private String stationId;//站点ID

	/**
	 * 视频播放参数-头部
	 */
	private String token;
	/**
	 * 在线观看人数
	 */
	private int count;
	/**
	 * 最后一次播放时间
	 */
	private Date playTime;

	public String getAddressCode() {
		return addressCode;
	}
	public void setAddressCode(String addressCode) {
		this.addressCode = addressCode;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
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
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getSubCode() {
		return subCode;
	}
	public void setSubCode(String subCode) {
		this.subCode = subCode;
	}
	public String getLiResourceId() {
		return liResourceId;
	}
	public void setLiResourceId(String liResourceId) {
		this.liResourceId = liResourceId;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getParams() {
		return params;
	}
	public void setParams(String params) {
		this.params = params;
	}
	public String getParams2() {
		return params2;
	}
	public void setParams2(String params2) {
		this.params2 = params2;
	}
	public String getParams3() {
		return params3;
	}
	public void setParams3(String params3) {
		this.params3 = params3;
	}
	public String getParamsName() {
		return paramsName;
	}
	public void setParamsName(String paramsName) {
		this.paramsName = paramsName;
	}
	public String getLiResourceType() {
		return liResourceType;
	}
	public void setLiResourceType(String liResourceType) {
		this.liResourceType = liResourceType;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getLevelType() {
		return levelType;
	}
	public void setLevelType(int levelType) {
		this.levelType = levelType;
	}
	public int getControlType() {
		return controlType;
	}
	public void setControlType(int controlType) {
		this.controlType = controlType;
	}
	public String getToken() { return token; }
	public void setToken(String token) { this.token = token; }
	public int getCount() { return count; }
	public void setCount(int count) { this.count = count; }
	public Date getPlayTime() { return playTime; }
	public void setPlayTime(Date playTime) { this.playTime = playTime; }
	public String getStationId() { return stationId; }
	public void setStationId(String stationId) { this.stationId = stationId; }
}
