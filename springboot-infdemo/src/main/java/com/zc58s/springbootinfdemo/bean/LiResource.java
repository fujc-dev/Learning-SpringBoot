package com.zc58s.springbootinfdemo.bean;

import java.io.Serializable;

/**
 * 数据库表名:li_resource
 * @author
 */
public class LiResource implements Serializable{
	
	private static final long serialVersionUID = -5701387341319066528L;
	private String id;//主键id
	private String name;//资源点名称
	private String code;//资源点编码
	private String type;//资源点类型
	private String lineId;//线路id
	private String lineName;//线路名称
	private String stationId;//站点ID
	private String stationName;//站点名称
	private String tierId;//站层id(li_station_map表id)
	private String subSystem;//子系统
	private String subCode;//子系统资源编码
	private String addressCode;//地址码
	private String port;//端口号
	private String ajjPort;//输送带端口号
	private String username;//用户名
	private String password;//密码
	private String xpoint;//x轴坐标
	private String ypoint;//y轴坐标
	private String width;//宽度
	private String rotate;//旋转参数
	private String deployLocation;//部署位置
	private String operatorId;//当前操作员id
	private String operatorName;//当前操作员姓名
	private String attachedInfo;//附属信息
	private String remark;//资源点简介
	private String icon;//图标
	private String parentName;//父节点名称
	private String sysSign;//系统标识
	private Integer status;//资源点状态(1正常，0不可用)
	private Integer deleted;//是否已删除（0未删除，1已删除）
	private String createUserId;//创建人
	private java.util.Date createTime;//创建时间
	private String updateUserId;//修改人
	private java.util.Date updateTime;//修改时间
	private String byx1;//通道号
	private String byx2;//资源点状态(0=离线，1=在线)
	private String byx3;//布防状态(0=撤防，1=布防-默认)
	private String brand;//品牌
	private String xlongit;//经度
	private String ylat;//纬度
	private String imageSrc;//图标路径

	private String tierName;//地图层级
	private String typeName;//资源点类型名称


    public LiResource(){
    	
    }
    public LiResource(String id,String name){
    	this.id = id;
        this.name = name;
    }

	public String getTierName() {
		return tierName;
	}

	public void setTierName(String tierName) {
		this.tierName = tierName;
	}

	public String getXlongit() {
		return xlongit;
	}

	public void setXlongit(String xlongit) {
		this.xlongit = xlongit;
	}

	public String getYlat() {
		return ylat;
	}

	public void setYlat(String ylat) {
		this.ylat = ylat;
	}

	public String getImageSrc() {
		return imageSrc;
	}

	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
	}

	/**
	 * 主键id的getter方法
	 */
	public String getId(){
		return id;
	}
	/**
	 * 主键id的setter方法
	 */
	public void setId(String id){
		this.id = id;
	}
	/**
	 * 资源点名称的getter方法
	 */
	public String getName(){
		return name;
	}
	/**
	 * 资源点名称的setter方法
	 */
	public void setName(String name){
		this.name = name;
	}
	/**
	 * 资源点编码的getter方法
	 */
	public String getCode(){
		return code;
	}
	/**
	 * 资源点编码的setter方法
	 */
	public void setCode(String code){
		this.code = code;
	}
	/**
	 * 资源点类型(1枪机，2球机)的getter方法
	 */
	public String getType(){
		return type;
	}
	/**
	 * 资源点类型(1枪机，2球机)的setter方法
	 */
	public void setType(String type){
		this.type = type;
	}
	/**
	 * 线路id的getter方法
	 */
	public String getLineId(){
		return lineId;
	}
	/**
	 * 线路id的setter方法
	 */
	public void setLineId(String lineId){
		this.lineId = lineId;
	}
	/**
	 * 线路名称的getter方法
	 */
	public String getLineName(){
		return lineName;
	}
	/**
	 * 线路名称的setter方法
	 */
	public void setLineName(String lineName){
		this.lineName = lineName;
	}
	/**
	 * 站点ID的getter方法
	 */
	public String getStationId(){
		return stationId;
	}
	/**
	 * 站点ID的setter方法
	 */
	public void setStationId(String stationId){
		this.stationId = stationId;
	}
	/**
	 * 站点名称的getter方法
	 */
	public String getStationName(){
		return stationName;
	}
	/**
	 * 站点名称的setter方法
	 */
	public void setStationName(String stationName){
		this.stationName = stationName;
	}
	/**
	 * 站层id(li_station_map表id)的getter方法
	 */
	public String getTierId(){
		return tierId;
	}
	/**
	 * 站层id(li_station_map表id)的setter方法
	 */
	public void setTierId(String tierId){
		this.tierId = tierId;
	}
	/**
	 * 子系统的getter方法
	 */
	public String getSubSystem(){
		return subSystem;
	}
	/**
	 * 子系统的setter方法
	 */
	public void setSubSystem(String subSystem){
		this.subSystem = subSystem;
	}
	/**
	 * 子系统资源编码的getter方法
	 */
	public String getSubCode(){
		return subCode;
	}
	/**
	 * 子系统资源编码的setter方法
	 */
	public void setSubCode(String subCode){
		this.subCode = subCode;
	}
	/**
	 * 地址码的getter方法
	 */
	public String getAddressCode(){
		return addressCode;
	}
	/**
	 * 地址码的setter方法
	 */
	public void setAddressCode(String addressCode){
		this.addressCode = addressCode;
	}
	/**
	 * 端口号的getter方法
	 */
	public String getPort(){
		return port;
	}
	/**
	 * 端口号的setter方法
	 */
	public void setPort(String port){
		this.port = port;
	}
	/**
	 * 用户名的getter方法
	 */
	public String getUsername(){
		return username;
	}
	/**
	 * 用户名的setter方法
	 */
	public void setUsername(String username){
		this.username = username;
	}
	/**
	 * 密码的getter方法
	 */
	public String getPassword(){
		return password;
	}
	/**
	 * 密码的setter方法
	 */
	public void setPassword(String password){
		this.password = password;
	}
	/**
	 * x轴坐标的getter方法
	 */
	public String getXpoint(){
		return xpoint;
	}
	/**
	 * x轴坐标的setter方法
	 */
	public void setXpoint(String xpoint){
		this.xpoint = xpoint;
	}
	/**
	 * y轴坐标的getter方法
	 */
	public String getYpoint(){
		return ypoint;
	}
	/**
	 * y轴坐标的setter方法
	 */
	public void setYpoint(String ypoint){
		this.ypoint = ypoint;
	}
	/**
	 * 部署位置的getter方法
	 */
	public String getDeployLocation(){
		return deployLocation;
	}
	/**
	 * 部署位置的setter方法
	 */
	public void setDeployLocation(String deployLocation){
		this.deployLocation = deployLocation;
	}
	/**
	 * 当前操作员id的getter方法
	 */
	public String getOperatorId(){
		return operatorId;
	}
	/**
	 * 当前操作员id的setter方法
	 */
	public void setOperatorId(String operatorId){
		this.operatorId = operatorId;
	}
	/**
	 * 当前操作员姓名的getter方法
	 */
	public String getOperatorName(){
		return operatorName;
	}
	/**
	 * 当前操作员姓名的setter方法
	 */
	public void setOperatorName(String operatorName){
		this.operatorName = operatorName;
	}
	/**
	 * 附属信息的getter方法
	 */
	public String getAttachedInfo(){
		return attachedInfo;
	}
	/**
	 * 附属信息的setter方法
	 */
	public void setAttachedInfo(String attachedInfo){
		this.attachedInfo = attachedInfo;
	}
	/**
	 * 资源点简介的getter方法
	 */
	public String getRemark(){
		return remark;
	}
	/**
	 * 资源点简介的setter方法
	 */
	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	/**
	 * 系统标识的getter方法
	 */
	public String getSysSign(){
		return sysSign;
	}
	/**
	 * 系统标识的setter方法
	 */
	public void setSysSign(String sysSign){
		this.sysSign = sysSign;
	}
	/**
	 * 资源点状态(1正常，0不可用)的getter方法
	 */
	public Integer getStatus(){
		return status;
	}
	/**
	 * 资源点状态(1正常，0不可用)的setter方法
	 */
	public void setStatus(Integer status){
		this.status = status;
	}
	/**
	 * 是否已删除（0未删除，1已删除）的getter方法
	 */
	public Integer getDeleted(){
		return deleted;
	}
	/**
	 * 是否已删除（0未删除，1已删除）的setter方法
	 */
	public void setDeleted(Integer deleted){
		this.deleted = deleted;
	}
	/**
	 * 创建人的getter方法
	 */
	public String getCreateUserId(){
		return createUserId;
	}
	/**
	 * 创建人的setter方法
	 */
	public void setCreateUserId(String createUserId){
		this.createUserId = createUserId;
	}
	/**
	 * 创建时间的getter方法
	 */
	public java.util.Date getCreateTime(){
		return createTime;
	}
	/**
	 * 创建时间的setter方法
	 */
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
	/**
	 * 修改人的getter方法
	 */
	public String getUpdateUserId(){
		return updateUserId;
	}
	/**
	 * 修改人的setter方法
	 */
	public void setUpdateUserId(String updateUserId){
		this.updateUserId = updateUserId;
	}
	/**
	 * 修改时间的getter方法
	 */
	public java.util.Date getUpdateTime(){
		return updateTime;
	}
	/**
	 * 修改时间的setter方法
	 */
	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime = updateTime;
	}
	/**
	 * 备用项1的getter方法
	 */
	public String getByx1(){
		return byx1;
	}
	/**
	 * 备用项1的setter方法
	 */
	public void setByx1(String byx1){
		this.byx1 = byx1;
	}
	/**
	 * 备用项2的getter方法
	 */
	public String getByx2(){
		return byx2;
	}
	/**
	 * 备用项2的setter方法
	 */
	public void setByx2(String byx2){
		this.byx2 = byx2;
	}
	/**
	 * 备用项3的getter方法
	 */
	public String getByx3(){
		return byx3;
	}
	/**
	 * 备用项3的setter方法
	 */
	public void setByx3(String byx3){
		this.byx3 = byx3;
	}

	public String getBrand() { return brand; }

	public void setBrand(String brand) { this.brand = brand; }

	public String getTypeName() { return typeName; }

	public void setTypeName(String typeName) { this.typeName = typeName; }

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getRotate() {
		return rotate;
	}

	public void setRotate(String rotate) {
		this.rotate = rotate;
	}

	public String getAjjPort() {
		return ajjPort;
	}

	public void setAjjPort(String ajjPort) {
		this.ajjPort = ajjPort;
	}
}
