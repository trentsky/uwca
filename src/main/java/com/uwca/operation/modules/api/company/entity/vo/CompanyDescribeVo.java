package com.uwca.operation.modules.api.company.entity.vo;

import java.util.ArrayList;
import java.util.List;

import com.uwca.operation.common.utils.BaseEntity;

public class CompanyDescribeVo extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	private Result result;

	public class Result {
		
		private long pagecount;
		private long rowcount;
		List<CompanyDscResult> list = new ArrayList<CompanyDscResult>();
		public long getPagecount() {
			return pagecount;
		}
		public void setPagecount(long pagecount) {
			this.pagecount = pagecount;
		}
		public long getRowcount() {
			return rowcount;
		}
		public void setRowcount(long rowcount) {
			this.rowcount = rowcount;
		}
		public List<CompanyDscResult> getList() {
			return list;
		}
		public void setList(List<CompanyDscResult> list) {
			this.list = list;
		}
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}
}
