package com.uwca.operation.modules.api.company.entity.po;

import java.math.BigDecimal;
import java.util.Date;

import com.uwca.operation.common.persistence.DataEntity;

public class Company extends DataEntity<Company>{
	
	private static final long serialVersionUID = 1L;
	private String companyname;
	private String legalperson;
	private String organizationcode;
	private String businesslicense;
	private String fax;
	private String mail;
	private String website;
	private String address;
	private Date createTime;
	private Date updateTime;
	private int state;
	private String itemcheckstate;
	private BigDecimal guarantee;
	private int starlevel;
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public String getLegalperson() {
		return legalperson;
	}
	public void setLegalperson(String legalperson) {
		this.legalperson = legalperson;
	}
	public String getOrganizationcode() {
		return organizationcode;
	}
	public void setOrganizationcode(String organizationcode) {
		this.organizationcode = organizationcode;
	}
	public String getBusinesslicense() {
		return businesslicense;
	}
	public void setBusinesslicense(String businesslicense) {
		this.businesslicense = businesslicense;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getItemcheckstate() {
		return itemcheckstate;
	}
	public void setItemcheckstate(String itemcheckstate) {
		this.itemcheckstate = itemcheckstate;
	}
	public BigDecimal getGuarantee() {
		return guarantee;
	}
	public void setGuarantee(BigDecimal guarantee) {
		this.guarantee = guarantee;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getStarlevel() {
		return starlevel;
	}
	public void setStarlevel(int starlevel) {
		this.starlevel = starlevel;
	}
	
}
