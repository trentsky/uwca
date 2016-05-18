package com.uwca.operation.modules.api.news.entity.po;

import com.uwca.operation.common.persistence.DataEntity;

public class StoreNews extends DataEntity<News>{
	
	private static final long serialVersionUID = 1L;
	private String userid;
	private String newsid;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getNewsid() {
		return newsid;
	}
	public void setNewsid(String newsid) {
		this.newsid = newsid;
	}
}
