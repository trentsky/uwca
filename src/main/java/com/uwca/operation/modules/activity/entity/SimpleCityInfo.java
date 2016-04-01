package com.uwca.operation.modules.activity.entity;

import java.io.Serializable;

public class SimpleCityInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String areaid;
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAreaid() {
		return areaid;
	}
	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}
	
}
