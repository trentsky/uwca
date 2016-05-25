package com.uwca.operation.modules.api.oplog.entity.po;

import com.uwca.operation.common.persistence.DataEntity;

public class Oplog extends DataEntity<Oplog>{

	private static final long serialVersionUID = 1L;
	
	private String userid;
	private int type;
	private String content;
	private String objectid;
	private int behaviortype;
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
	public String getObjectid() {
		return objectid;
	}
	public void setObjectid(String objectid) {
		this.objectid = objectid;
	}
	public int getBehaviortype() {
		return behaviortype;
	}
	public void setBehaviortype(int behaviortype) {
		this.behaviortype = behaviortype;
	}
}
