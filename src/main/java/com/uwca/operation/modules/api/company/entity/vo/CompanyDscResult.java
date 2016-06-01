package com.uwca.operation.modules.api.company.entity.vo;

import com.uwca.operation.common.persistence.DataEntity;

public class CompanyDscResult extends DataEntity<CompanyDscResult>{
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String content;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
