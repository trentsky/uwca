package com.uwca.operation.modules.api.news.dao;

import java.util.List;
import java.util.Map;

import com.uwca.operation.common.persistence.CrudDao;
import com.uwca.operation.common.persistence.annotation.MyBatisDao;
import com.uwca.operation.modules.api.news.entity.po.StoreNews;
import com.uwca.operation.modules.api.news.entity.vo.NewsResult;

@MyBatisDao
public interface StoreNewsDao extends CrudDao<StoreNews> {

	StoreNews getStoreNews(Map<String, Object> map);

	void delStoreNews(String id);

	List<NewsResult> getStoreNewsByUserid(String userid);

}
