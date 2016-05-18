package com.uwca.operation.modules.api.company.entity.vo;

import java.math.BigDecimal;

public class CompanyResult {
	
	private String id;
	private String companyname;
	private String legalperson;
	private String organizationcode;
	private String businesslicense;
	private String fax;
	private String mail;
	private String website;
	private String address;
	private int state;
	private String itemcheckstate;
	private BigDecimal guarantee;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	
}
