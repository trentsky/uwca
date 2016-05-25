package com.uwca.operation.modules.api.oplog.entity.vo;

import java.util.ArrayList;
import java.util.List;

import com.uwca.operation.common.utils.BaseEntity;

public class OplogVo extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	private Result result;

	public class Result {
		
		private long pagecount;
		private long rowcount;
		private List<OplogResult> list = new ArrayList<OplogResult>();
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
		public List<OplogResult> getList() {
			return list;
		}
		public void setList(List<OplogResult> list) {
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
