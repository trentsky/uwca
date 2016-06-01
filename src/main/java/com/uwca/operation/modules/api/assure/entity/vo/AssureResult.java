package com.uwca.operation.modules.api.assure.entity.vo;

import java.math.BigDecimal;

import com.uwca.operation.common.persistence.DataEntity;

public class AssureResult extends DataEntity<AssureResult>{
	
	private static final long serialVersionUID = 1L;
	private String assureid;
	private String assuredid;
	private BigDecimal money;
	private String mobile;
	private String name;
	private String companyname;
	private String state;
	
	public String getAssureid() {
		return assureid;
	}
	public void setAssureid(String assureid) {
		this.assureid = assureid;
	}
	public String getAssuredid() {
		return assuredid;
	}
	public void setAssuredid(String assuredid) {
		this.assuredid = assuredid;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
