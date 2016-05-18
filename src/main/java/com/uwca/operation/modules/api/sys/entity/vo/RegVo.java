package com.uwca.operation.modules.api.sys.entity.vo;

import com.uwca.operation.common.utils.BaseEntity;

public class RegVo extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	private Result result;

	public class Result {
		
		private String userid;
		private String token;
		public String getUserid() {
			return userid;
		}
		public void setUserid(String userid) {
			this.userid = userid;
		}
		public String getToken() {
			return token;
		}
		public void setToken(String token) {
			this.token = token;
		}
		
		
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

}
