package com.uwca.operation.modules.api.company.entity.vo;

import java.util.ArrayList;
import java.util.List;

import com.uwca.operation.common.utils.BaseEntity;

public class CompanyDescribeVo extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	private Result result;

	public class Result {
		
		List<CompanyDscResult> desc = new ArrayList<CompanyDscResult>();

		public List<CompanyDscResult> getDesc() {
			return desc;
		}

		public void setDesc(List<CompanyDscResult> desc) {
			this.desc = desc;
		}
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}
}
