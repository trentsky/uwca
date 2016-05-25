package com.uwca.operation.modules.api.assure.entity.vo;

import java.util.ArrayList;
import java.util.List;

import com.uwca.operation.common.utils.BaseEntity;

public class AssureVo extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	private Result result;

	public class Result {
		private long pagecount;
		private long rowcount;
		List<AssureResult> list = new ArrayList<AssureResult>();
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
		public List<AssureResult> getList() {
			return list;
		}
		public void setList(List<AssureResult> list) {
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
