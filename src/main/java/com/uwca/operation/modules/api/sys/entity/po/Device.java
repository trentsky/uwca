package com.uwca.operation.modules.api.sys.entity.po;

import com.uwca.operation.common.persistence.DataEntity;


public class Device extends DataEntity<Device>{
	
	private static final long serialVersionUID = 1L;
	private String userid;
	private String deviceid;
	private String channelid;
	private int islogin;
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getDeviceid() {
		return deviceid;
	}
	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}
	public String getChannelid() {
		return channelid;
	}
	public void setChannelid(String channelid) {
		this.channelid = channelid;
	}
	public int getIslogin() {
		return islogin;
	}
	public void setIslogin(int islogin) {
		this.islogin = islogin;
	}
	
}
