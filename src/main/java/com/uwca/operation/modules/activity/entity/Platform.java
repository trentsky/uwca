package com.uwca.operation.modules.activity.entity;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.uwca.operation.common.persistence.DataEntity;
import com.uwca.operation.modules.sys.entity.User;
import com.uwca.operation.modules.sys.utils.UserUtils;

public class Platform extends DataEntity<Platform> {
	
	private static final long serialVersionUID = 1L;
	private Integer platformid;
	private Integer app;
	private String version;
	
	public Integer getPlatformid() {
		return platformid;
	}
	public void setPlatformid(Integer platformid) {
		this.platformid = platformid;
	}
	public Integer getApp() {
		return app;
	}
	public void setApp(Integer app) {
		this.app = app;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
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
}
