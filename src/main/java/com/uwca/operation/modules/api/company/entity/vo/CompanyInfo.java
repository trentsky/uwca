package com.uwca.operation.modules.api.company.entity.vo;

import com.uwca.operation.common.persistence.DataEntity;

public class CompanyInfo extends DataEntity<CompanyInfo>{
	private static final long serialVersionUID = 1L;
	private String id;
	private String companyname;
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
	
}
