package com.uwca.operation.modules.api.news.dao;

import java.util.List;
import java.util.Map;

import com.uwca.operation.common.persistence.CrudDao;
import com.uwca.operation.common.persistence.annotation.MyBatisDao;
import com.uwca.operation.modules.api.news.entity.po.News;
import com.uwca.operation.modules.api.news.entity.vo.NewsResult;

@MyBatisDao
public interface NewsDao extends CrudDao<News> {

	News getNews(News news);

	List<NewsResult> getNewsByType(Map<String, Object> map);

	void delNews(String id);

	News getNewsById(String id);
 
	void modifyNews(Map<String, Object> map);

	List<NewsResult> searchNews(String text);
}
