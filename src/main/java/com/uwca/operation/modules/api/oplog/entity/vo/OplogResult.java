package com.uwca.operation.modules.api.oplog.entity.vo;

import com.uwca.operation.common.persistence.DataEntity;


public class OplogResult extends DataEntity<OplogResult>{
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String userid;
	private int type;
	private String content;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
