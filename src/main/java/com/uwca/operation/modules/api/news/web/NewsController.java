package com.uwca.operation.modules.api.news.web;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uwca.operation.common.persistence.Page;
import com.uwca.operation.common.utils.BaseEntity;
import com.uwca.operation.common.utils.TokenTool;
import com.uwca.operation.modules.api.news.entity.po.News;
import com.uwca.operation.modules.api.news.entity.po.StoreNews;
import com.uwca.operation.modules.api.news.entity.vo.NewsDetailResult;
import com.uwca.operation.modules.api.news.entity.vo.NewsDetailVo;
import com.uwca.operation.modules.api.news.entity.vo.NewsResult;
import com.uwca.operation.modules.api.news.entity.vo.NewsVo;
import com.uwca.operation.modules.api.news.entity.vo.NewsVo.Result;
import com.uwca.operation.modules.api.news.service.NewsService;

@Controller
@RequestMapping(value = "/api/news/")
public class NewsController {

	@Autowired
	private NewsService newsService;

	@RequestMapping(value = "addNews")
	@ResponseBody
	public BaseEntity addNews(@RequestParam("token") String token,
			@RequestParam("type") String type,
			@RequestParam("title") String title,
			@RequestParam("content") String content,
			@RequestParam("sign") String sign) {

		BaseEntity baseEntity = new BaseEntity();
		try {
			if (StringUtils.isEmpty(token) || StringUtils.isEmpty(type)
					|| StringUtils.isEmpty(title)
					|| StringUtils.isEmpty(content)
					|| StringUtils.isEmpty(sign)) {
				baseEntity.setReturncode(1);
				baseEntity.setMessage("参数不完整");
				return baseEntity;
			}

			News news = new News();
			news.setCompanyid(TokenTool.getCompanyid(token));
			news.setUserid(TokenTool.getUserid(token));
			news.setType(Integer.parseInt(type));
			news.setTitle(title);
			news.setContent(content);
			if (newsService.isExistNews(news)) {
				baseEntity.setReturncode(1);
				baseEntity.setMessage("该供需信息已存在");
				return baseEntity;
			}
			newsService.addNews(news);
			baseEntity.setReturncode(0);
			baseEntity.setMessage("ok");
			return baseEntity;
		} catch (Exception e) {
			baseEntity.setReturncode(1);
			baseEntity.setMessage("添加供需信息失败");
			e.printStackTrace();
			return baseEntity;
		}
	}

	@RequestMapping(value = "getNews")
	@ResponseBody
	public NewsVo getNews(@RequestParam("pagesize") int pagesize,
			@RequestParam("pageindex") int pageindex,
			@RequestParam("type") int type) {
		NewsVo newsVo = new NewsVo();
		Result result = newsVo.new Result();
		try {
			Page<NewsResult> page = newsService.getNewsByType(pageindex,
					pagesize, type);
			result.setPagecount(page.getTotalPage());
			result.setRowcount(page.getList().size());
			result.setList(page.getList());
			newsVo.setResult(result);
			newsVo.setReturncode(0);
			newsVo.setMessage("ok");
			return newsVo;
		} catch (Exception e) {
			newsVo.setReturncode(1);
			newsVo.setMessage("获取供需信息列表异常");
			e.printStackTrace();
			return newsVo;
		}
	}

	@RequestMapping(value = "getNewsByid")
	@ResponseBody
	public NewsDetailVo getNewsByid(@RequestParam("id") String id) {
		NewsDetailVo newsDetailVo = new NewsDetailVo();
		com.uwca.operation.modules.api.news.entity.vo.NewsDetailVo.Result result = newsDetailVo.new Result();
		try {
			if (StringUtils.isEmpty(id)) {
				newsDetailVo.setReturncode(1);
				newsDetailVo.setMessage("参数不完整");
			}
			NewsDetailResult newsDetailResult = newsService.getNewsByid(id);
			result.setNews(newsDetailResult);
			newsDetailVo.setResult(result);
			newsDetailVo.setReturncode(0);
			newsDetailVo.setMessage("ok");
			return newsDetailVo;
		} catch (Exception e) {
			newsDetailVo.setReturncode(1);
			newsDetailVo.setMessage("获取供需信息异常");
			e.printStackTrace();
			return newsDetailVo;
		}
	}

	@RequestMapping(value = "searchNews")
	@ResponseBody
	public BaseEntity searchNews(@RequestParam("token") String token,
			@RequestParam("pagesize") int pagesize,

			@RequestParam("pageindex") int pageindex,
			@RequestParam("text") String text) {
		NewsVo newsVo = new NewsVo();
		Result result = newsVo.new Result();
		try {
			if (StringUtils.isEmpty(text)||StringUtils.isEmpty(token)) {
				newsVo.setReturncode(1);
				newsVo.setMessage("参数不完整");
			}
			Page<NewsResult> page = newsService.searchNews(pageindex, pagesize,
					text,TokenTool.getUserid(token));
			result.setPagecount(page.getTotalPage());
			result.setRowcount(page.getList().size());
			result.setList(page.getList());
			newsVo.setResult(result);
			newsVo.setReturncode(0);
			newsVo.setMessage("ok");
			return newsVo;
		} catch (Exception e) {
			newsVo.setReturncode(1);
			newsVo.setMessage("搜寻供需信息列表异常");
			e.printStackTrace();
			return newsVo;
		}
	}

	@RequestMapping(value = "delNews")
	@ResponseBody
	public BaseEntity delNews(@RequestParam("id") String id,
			@RequestParam("sign") String sign) {
		BaseEntity baseEntity = new BaseEntity();
		try {
			if (StringUtils.isEmpty(id)) {
				baseEntity.setReturncode(1);
				baseEntity.setMessage("参数不完整");
			}

			newsService.delNews(id);

			baseEntity.setReturncode(0);
			baseEntity.setMessage("ok");
			return baseEntity;
		} catch (Exception e) {
			baseEntity.setReturncode(1);
			baseEntity.setMessage("删除供需信息列表异常");
			e.printStackTrace();
			return baseEntity;
		}
	}

	@RequestMapping(value = "modifyNews")
	@ResponseBody
	public BaseEntity modifyNews(@RequestParam("id") String id,
			@RequestParam("title") String title,
			@RequestParam("content") String content,
			@RequestParam("sign") String sign) {
		BaseEntity baseEntity = new BaseEntity();
		try {
			if (StringUtils.isEmpty(id)) {
				baseEntity.setReturncode(1);
				baseEntity.setMessage("参数不完整");
				return baseEntity;
			}

			if (!newsService.isExistNews(id)) {
				baseEntity.setReturncode(1);
				baseEntity.setMessage("要修改的供需信息不存在");
				return baseEntity;
			}

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			map.put("title", title);
			map.put("content", content);
			newsService.modifyNews(map);

			baseEntity.setReturncode(0);
			baseEntity.setMessage("ok");
			return baseEntity;
		} catch (Exception e) {
			baseEntity.setReturncode(1);
			baseEntity.setMessage("修改供需信息异常");
			e.printStackTrace();
			return baseEntity;
		}
	}

	@RequestMapping(value = "storeNews")
	@ResponseBody
	public BaseEntity storeNews(@RequestParam("token") String token,
			@RequestParam("newsid") String newsid,
			@RequestParam("sign") String sign) {
		BaseEntity baseEntity = new BaseEntity();
		try {
			if (StringUtils.isEmpty(token) || StringUtils.isEmpty(newsid)
					|| StringUtils.isEmpty(sign)) {
				baseEntity.setReturncode(1);
				baseEntity.setMessage("参数不完整");
				return baseEntity;
			}

			String userid = TokenTool.getUserid(token);

			if (newsService.isExistStoreNews(userid, newsid)) {
				baseEntity.setReturncode(1);
				baseEntity.setMessage("重复收藏");
				return baseEntity;
			}

			StoreNews storeNews = new StoreNews();
			storeNews.setUserid(userid);
			storeNews.setNewsid(newsid);
			newsService.addStoreNews(storeNews);
			baseEntity.setReturncode(0);
			baseEntity.setMessage("ok");
			return baseEntity;
		} catch (Exception e) {
			baseEntity.setReturncode(1);
			baseEntity.setMessage("收藏供需信息异常");
			e.printStackTrace();
			return baseEntity;
		}
	}

	@RequestMapping(value = "delStoreNews")
	@ResponseBody
	public BaseEntity delStoreNews(@RequestParam("id") String id,
			@RequestParam("sign") String sign) {
		BaseEntity baseEntity = new BaseEntity();
		try {
			if (StringUtils.isEmpty(id) || StringUtils.isEmpty(sign)) {
				baseEntity.setReturncode(1);
				baseEntity.setMessage("参数不完整");
				return baseEntity;
			}

			newsService.delStoreNews(id);

			baseEntity.setReturncode(0);
			baseEntity.setMessage("ok");
			return baseEntity;
		} catch (Exception e) {
			baseEntity.setReturncode(1);
			baseEntity.setMessage("取消收藏供需信息异常");
			e.printStackTrace();
			return baseEntity;
		}
	}

	@RequestMapping(value = "getStoreNews")
	@ResponseBody
	public NewsVo getStoreNews(@RequestParam("token") String token,
			@RequestParam("pagesize") int pagesize,
			@RequestParam("pageindex") int pageindex) {
		NewsVo newsVo = new NewsVo();
		Result result = newsVo.new Result();
		try {
			if (StringUtils.isEmpty(token)) {
				newsVo.setReturncode(1);
				newsVo.setMessage("参数不完整");
			}

			Page<NewsResult> page = newsService.getStoreNews(pageindex,
					pagesize, TokenTool.getUserid(token));
			result.setPagecount(page.getTotalPage());
			result.setRowcount(page.getList().size());
			result.setList(page.getList());
			newsVo.setResult(result);
			newsVo.setReturncode(0);
			newsVo.setMessage("ok");
			return newsVo;
		} catch (Exception e) {
			newsVo.setReturncode(1);
			newsVo.setMessage("获取收藏供需信息列表异常");
			e.printStackTrace();
			return newsVo;
		}
	}
}
