package com.uwca.operation.modules.api.company.entity.vo;

import com.uwca.operation.common.utils.BaseEntity;

public class CompanyVo extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	private Result result;

	public class Result {
		private CompanyResult companyResult;

		public CompanyResult getCompanyResult() {
			return companyResult;
		}

		public void setCompanyResult(CompanyResult companyResult) {
			this.companyResult = companyResult;
		}

	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}
	
	

}
