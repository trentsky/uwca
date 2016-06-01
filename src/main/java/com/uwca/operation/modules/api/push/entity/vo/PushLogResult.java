package com.uwca.operation.modules.api.push.entity.vo;

import com.uwca.operation.common.persistence.DataEntity;

public class PushLogResult extends DataEntity<PushLogResult>{
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String type;
	private String pushtitle;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPushtitle() {
		return pushtitle;
	}
	public void setPushtitle(String pushtitle) {
		this.pushtitle = pushtitle;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
