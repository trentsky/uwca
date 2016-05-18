package com.uwca.operation.modules.api.sys.entity.po;

import java.util.Date;

import com.uwca.operation.common.persistence.DataEntity;

public class UwcaUser extends DataEntity<UwcaUser>{
	
	private static final long serialVersionUID = 1L;
	private String mobile;
	private String passwd="";
	private String companyid;
	private String name;
	private String deviceid;
	private String position;
	private Date createTime;
	private Date updaDate;
	private String companyName;
	
	public String getDeviceid() {
		return deviceid;
	}
	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getCompanyid() {
		return companyid;
	}
	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdaDate() {
		return updaDate;
	}
	public void setUpdaDate(Date updaDate) {
		this.updaDate = updaDate;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
}
