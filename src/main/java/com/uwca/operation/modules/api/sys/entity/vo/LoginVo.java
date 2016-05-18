package com.uwca.operation.modules.api.sys.entity.vo;

import com.uwca.operation.common.utils.BaseEntity;

public class LoginVo extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	private Result result;

	public class Result {
		
		private String userid;
		private String token;
		private String companyid;
		private String itemcheckState;
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
		public String getCompanyid() {
			return companyid;
		}
		public void setCompanyid(String companyid) {
			this.companyid = companyid;
		}
		public String getItemcheckState() {
			return itemcheckState;
		}
		public void setItemcheckState(String itemcheckState) {
			this.itemcheckState = itemcheckState;
		}
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

}
