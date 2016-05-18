package com.uwca.operation.modules.api.news.entity.vo;

import java.util.ArrayList;
import java.util.List;

import com.uwca.operation.common.utils.BaseEntity;

public class NewsVo extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	private Result result;

	public class Result {
		
		List<NewsResult> news = new ArrayList<NewsResult>();

		public List<NewsResult> getNews() {
			return news;
		}

		public void setNews(List<NewsResult> news) {
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
