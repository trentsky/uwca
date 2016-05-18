package com.uwca.operation.modules.api.company.entity.vo;

import java.util.ArrayList;
import java.util.List;

import com.uwca.operation.common.utils.BaseEntity;


public class CompanysVo extends BaseEntity{

	private static final long serialVersionUID = 1L;

	private Result result;

	public class Result {
		private List<CompanyResult> companys = new ArrayList<CompanyResult>();

		public List<CompanyResult> getCompanys() {
			return companys;
		}

		public void setCompanys(List<CompanyResult> companys) {
			this.companys = companys;
		}
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}
}
