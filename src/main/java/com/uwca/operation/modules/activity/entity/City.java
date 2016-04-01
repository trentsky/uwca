package com.uwca.operation.modules.activity.entity;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.uwca.operation.common.persistence.DataEntity;
import com.uwca.operation.modules.sys.entity.User;
import com.uwca.operation.modules.sys.utils.UserUtils;

public class City extends DataEntity<City> {
	
	private static final long serialVersionUID = 1L;
	private String areaid;
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public void preInsert(){
		User user = UserUtils.getUser();
		if (StringUtils.isNotBlank(user.getId())){
			this.updateBy = user;
			this.createBy = user;
		}
		this.updateDate = new Date();
		this.createDate = this.updateDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAreaid() {
		return areaid;
	}
	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}
	
}
