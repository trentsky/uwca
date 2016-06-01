package com.uwca.operation.modules.api.company.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uwca.operation.common.persistence.Page;
import com.uwca.operation.common.service.CrudService;
import com.uwca.operation.modules.api.company.dao.CompanyDao;
import com.uwca.operation.modules.api.company.entity.po.Company;
import com.uwca.operation.modules.api.company.entity.po.CompanyDescribe;
import com.uwca.operation.modules.api.company.entity.vo.CompanyDscResult;
import com.uwca.operation.modules.api.company.entity.vo.CompanyInfo;
import com.uwca.operation.modules.api.oplog.entity.po.Oplog;
import com.uwca.operation.modules.api.oplog.service.OplogService;

@Service
@Transactional
public class CompanyService extends CrudService<CompanyDao, Company> {

	@Autowired
	private OplogService oplogService;

	public Company getCompanyInfo(String mobile) {
		return dao.getCompanyByMobile(mobile);
	}

	public Company getCompanyInfoById(String id) {
		return dao.getCompanyInfoById(id);
	}

	public void updateCompany(Map<String, Object> map) {
		dao.updateCompany(map);

		// 添加操作日志
		Oplog oplog = new Oplog();
		oplog.setUserid((String) map.get("userid"));
		oplog.setObjectid((String) map.get("id"));
		oplog.setType(7);
		oplog.setBehaviortype(1);
		oplog.setContent("修改了公司信息");
		oplogService.addOplong(oplog);
	}

	public Page<CompanyDscResult> getCompanyDescs(String companyid,
			int pageindex, int pagesize) {
		CompanyDscResult companyDscResult = new CompanyDscResult();
		Page<CompanyDscResult> page = new Page<CompanyDscResult>(pageindex,
				pagesize);
		companyDscResult.setId(companyid);
		companyDscResult.setPage(page);
		page.setList(dao.getCompanyDescs(companyDscResult));
		return page;
	}

	public boolean isExistCompanyDesc(String userid, String content) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", userid);
		map.put("content", content);
		return null == dao.isExistCompanyDesc(map) ? false : true;
	}

	public void addCompanyDesc(CompanyDescribe companyDescribe) {
		companyDescribe.preInsert();
		dao.addCompanyDesc(companyDescribe);
	}

	public void delCompanyDesc(String descid) {
		dao.delCompanyDesc(descid);
	}

	public Page<CompanyInfo> searchCompany(int pageindex, int pagesize,
			String searchtext,String userid) {
		CompanyInfo companyInfo = new CompanyInfo();
		Page<CompanyInfo> page = new Page<CompanyInfo>(pageindex, pagesize);
		companyInfo.setPage(page);
		companyInfo.setCompanyname(searchtext);
		page.setList(dao.searchCompany(companyInfo));

		// 添加操作日志
		Oplog oplog = new Oplog();
		oplog.setUserid(userid);
		oplog.setObjectid("");
		oplog.setType(13);
		oplog.setBehaviortype(2);
		oplog.setContent("搜索了有关 " + searchtext + " 的公司");
		oplogService.addOplong(oplog);
		return page;
	}

	public void updateCompanyDesc(Map<String, Object> map) {
		dao.updateCompanyDesc(map);
	}

}
