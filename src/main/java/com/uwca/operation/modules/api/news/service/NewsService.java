package com.uwca.operation.modules.api.news.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uwca.operation.common.service.CrudService;
import com.uwca.operation.modules.api.news.dao.NewsDao;
import com.uwca.operation.modules.api.news.dao.StoreNewsDao;
import com.uwca.operation.modules.api.news.entity.po.News;
import com.uwca.operation.modules.api.news.entity.po.StoreNews;
import com.uwca.operation.modules.api.news.entity.vo.NewsResult;

@Service
@Transactional
public class NewsService extends CrudService<NewsDao, News> {

	@Autowired
	private StoreNewsDao storeNewsDao;

	public boolean isExistNews(News news) {
		return null == dao.getNews(news) ? false : true;
	}

	public void addNews(News news) {
		news.preInsert();
		dao.insert(news);
	}

	public List<NewsResult> getNewsByType(Map<String, Object> map) {
		map.put("endpage", ((int)map.get("pageindex")
				+ (int)map.get("pagesize") - 1));
		return dao.getNewsByType(map);
	}

	public void delNews(String id) {
		dao.delNews(id);
	}

	public boolean isExistNews(String id) {
		return null == dao.getNewsById(id) ? false : true;
	}

	public void modifyNews(Map<String, Object> map) {
		dao.modifyNews(map);
	}

	public boolean isExistStoreNews(String userid, String newsid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", userid);
		map.put("newsid", newsid);
		return null == storeNewsDao.getStoreNews(map)?false:true;
	}

	public void addStoreNews(StoreNews storeNews) {
		storeNews.preInsert();
		storeNewsDao.insert(storeNews);
	}

	public void delStoreNews(String id) {
		storeNewsDao.delStoreNews(id);
	}

	public List<NewsResult> searchNews(String text) {
		return dao.searchNews(text);
	}

}
