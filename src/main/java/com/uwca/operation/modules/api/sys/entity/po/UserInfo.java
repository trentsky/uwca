package com.uwca.operation.modules.api.sys.entity.po;

public class UserInfo {
	
	private String userid;
	private String passwd;
	private String mobile;
	private String companyid;
	private String itemcheckstate;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getCompanyid() {
		return companyid;
	}
	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}
	public String getItemcheckstate() {
		return itemcheckstate;
	}
	public void setItemcheckstate(String itemcheckstate) {
		this.itemcheckstate = itemcheckstate;
	}
}
