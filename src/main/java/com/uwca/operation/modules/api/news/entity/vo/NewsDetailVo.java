package com.uwca.operation.modules.api.news.entity.vo;

import com.uwca.operation.common.utils.BaseEntity;


public class NewsDetailVo extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	private Result result;

	public class Result {
		
		NewsDetailResult news;

		public NewsDetailResult getNews() {
			return news;
		}

		public void setNews(NewsDetailResult news) {
			this.news = news;
		}
		
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}
}
