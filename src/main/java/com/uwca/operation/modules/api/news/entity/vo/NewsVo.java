package com.uwca.operation.modules.api.news.entity.vo;

import java.util.ArrayList;
import java.util.List;

import com.uwca.operation.common.utils.BaseEntity;

public class NewsVo extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	private Result result;

	public class Result {
		
		private long pagecount;
		private long rowcount;
		private List<NewsResult> list = new ArrayList<NewsResult>();
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
		public List<NewsResult> getList() {
			return list;
		}
		public void setList(List<NewsResult> list) {
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
