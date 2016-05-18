package com.uwca.operation.modules.api.news.entity.po;

import com.uwca.operation.common.persistence.DataEntity;

public class News extends DataEntity<News>{

	private static final long serialVersionUID = 1L;
	
	private String title;
	private String content;
	private int type;
	private String userid;
	private String companyid;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getCompanyid() {
		return companyid;
	}
	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}
	
	
	
	
}
