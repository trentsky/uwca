package com.uwca.operation.modules.api.company.entity.po;

import com.uwca.operation.common.persistence.DataEntity;

public class CompanyDescribe extends DataEntity<CompanyDescribe>{

	private static final long serialVersionUID = 1L;
	
	private String companyid;
	private String content;
	public String getCompanyid() {
		return companyid;
	}
	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
