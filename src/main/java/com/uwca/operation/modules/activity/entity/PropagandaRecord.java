package com.uwca.operation.modules.activity.entity;

import com.uwca.operation.common.persistence.DataEntity;

public class PropagandaRecord extends DataEntity<PropagandaRecord> {
	
	private static final long serialVersionUID = 1L;
	
	private String propagandaid;
	
	private String areaid;
	
	private String appconfigid;
	
	@Override
	public void preInsert(){}

	public String getPropagandaid() {
		return propagandaid;
	}

	public void setPropagandaid(String propagandaid) {
		this.propagandaid = propagandaid;
	}

	public String getAreaid() {
		return areaid;
	}

	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}

	public String getAppconfigid() {
		return appconfigid;
	}

	public void setAppconfigid(String appconfigid) {
		this.appconfigid = appconfigid;
	}
	
}
