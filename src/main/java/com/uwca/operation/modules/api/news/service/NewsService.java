package com.uwca.operation.modules.api.news.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uwca.operation.common.persistence.Page;
import com.uwca.operation.common.service.CrudService;
import com.uwca.operation.modules.api.news.dao.NewsDao;
import com.uwca.operation.modules.api.news.dao.StoreNewsDao;
import com.uwca.operation.modules.api.news.entity.po.News;
import com.uwca.operation.modules.api.news.entity.po.StoreNews;
import com.uwca.operation.modules.api.news.entity.vo.NewsDetailResult;
import com.uwca.operation.modules.api.news.entity.vo.NewsResult;
import com.uwca.operation.modules.api.oplog.entity.po.Oplog;
import com.uwca.operation.modules.api.oplog.service.OplogService;

@Service
@Transactional
public class NewsService extends CrudService<NewsDao, News> {

	@Autowired
	private StoreNewsDao storeNewsDao;
	
	@Autowired
	private OplogService oplogService;

	public boolean isExistNews(News news) {
		return null == dao.getNews(news) ? false : true;
	}

	public void addNews(News news) {
		news.preInsert();
		dao.insert(news);
		
		//添加操作日志
		Oplog oplog = new Oplog();
		oplog.setUserid(news.getUserid());
		oplog.setObjectid(news.getId());
		oplog.setBehaviortype(1);
		if (1==news.getType()) {
			oplog.setType(1);
			oplog.setContent("发布了 "+news.getTitle() +" 供货信息");
		}
		if (2==news.getType()) {
			oplog.setType(2);
			oplog.setContent("发布了 "+news.getTitle() +" 需求信息");
		}
		oplogService.addOplong(oplog);
		
	}

	public Page<NewsResult> getNewsByType(int pageindex,int pagesize,int type) {
		Page<NewsResult> page = new Page<NewsResult>(pageindex, pagesize);
		page.setList(dao.getNewsByType(type));
		return page;
	}

	public void delNews(String id) {
		dao.delNews(id);
		
		NewsDetailResult newsDetailResult = getNewsByid(id);
		//添加操作日志
		Oplog oplog = new Oplog();
		oplog.setUserid(newsDetailResult.getUserid());
		oplog.setObjectid(newsDetailResult.getId());
		oplog.setBehaviortype(1);
		if (1==newsDetailResult.getType()) {
			oplog.setType(5);
			oplog.setContent("删除了 "+newsDetailResult.getTitle() +" 供货信息");
		}
		if (2==newsDetailResult.getType()) {
			oplog.setType(6);
			oplog.setContent("删除了 "+newsDetailResult.getTitle() +" 需求信息");
		}
		oplogService.addOplong(oplog);
	}

	public boolean isExistNews(String id) {
		return null == dao.getNewsById(id) ? false : true;
	}

	public void modifyNews(Map<String, Object> map) {
		dao.modifyNews(map);
		
		//添加操作日志
		NewsDetailResult newsDetailResult = getNewsByid((String) map.get("id"));
		Oplog oplog = new Oplog();
		oplog.setUserid(newsDetailResult.getUserid());
		oplog.setObjectid(newsDetailResult.getId());
		oplog.setBehaviortype(1);
		if (1==newsDetailResult.getType()) {
			oplog.setType(3);
			oplog.setContent("修改了 "+newsDetailResult.getTitle() +" 供货信息");
		}
		if (2==newsDetailResult.getType()) {
			oplog.setType(4);
			oplog.setContent("修改了 "+newsDetailResult.getTitle() +" 需求信息");
		}
		oplogService.addOplong(oplog);
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
		
		//操作轨迹
		NewsDetailResult newsDetailResult = dao.getNewsById(storeNews.getNewsid());
		Oplog oplog = new Oplog();
		oplog.setUserid(newsDetailResult.getUserid());
		oplog.setObjectid(newsDetailResult.getId());
		oplog.setType(15);
		oplog.setContent("收藏了 "+ newsDetailResult.getTitle() +"信息");
		oplog.setBehaviortype(2);
		oplogService.addOplong(oplog);
	}

	public void delStoreNews(String id) {
		storeNewsDao.delStoreNews(id);
	}

	public Page<NewsResult> searchNews(int pageindex,int pagesize,String text,String userid) {
		Page<NewsResult> page = new Page<NewsResult>(pageindex, pagesize);
		page.setList(dao.searchNews(text));
		
		//操作轨迹
		Oplog oplog = new Oplog();
		oplog.setUserid(userid);
		oplog.setObjectid("");
		oplog.setType(14);
		oplog.setContent("搜索了【"+text +"】相关的供需信息");
		oplog.setBehaviortype(2);
		oplogService.addOplong(oplog);
		return page;
	}

	public Page<NewsResult> getStoreNews(int pageindex, int pagesize,
			String userid) {
		Page<NewsResult> page = new Page<NewsResult>(pageindex, pagesize);
		page.setList(storeNewsDao.getStoreNewsByUserid(userid));
		return page;
	}

	public NewsDetailResult getNewsByid(String id) {
		NewsDetailResult newsDetailResult = dao.getNewsById(id);
		//操作轨迹
		Oplog oplog = new Oplog();
		oplog.setUserid(newsDetailResult.getUserid());
		oplog.setObjectid(newsDetailResult.getId());
		oplog.setType(12);
		oplog.setContent("阅读了 "+newsDetailResult.getTitle() +" 信息");
		oplog.setBehaviortype(2);
		oplogService.addOplong(oplog);
		return newsDetailResult;
	}

}
