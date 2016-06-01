package com.uwca.operation.modules.api.news.entity.vo;

import com.uwca.operation.common.persistence.DataEntity;


public class NewsResult extends DataEntity<NewsResult>{
	private static final long serialVersionUID = 1L;
	private int type;
	private String title;
	private String companyname;
	private String createtime;
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
}
