package com.uwca.operation.modules.api.company.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uwca.operation.common.service.CrudService;
import com.uwca.operation.modules.api.company.dao.CompanyDao;
import com.uwca.operation.modules.api.company.entity.po.Company;
import com.uwca.operation.modules.api.company.entity.po.CompanyDescribe;
import com.uwca.operation.modules.api.company.entity.vo.CompanyDscResult;
import com.uwca.operation.modules.api.company.entity.vo.CompanyResult;

@Service
@Transactional
public class CompanyService extends CrudService<CompanyDao, Company> {

	public Company getCompanyInfo(String mobile) {
		return dao.getCompanyByMobile(mobile);
	}
	
	public Company getCompanyInfoById(String id) {
		return dao.getCompanyInfoById(id);
	}

	public void updateCompany(Map<String, Object> map) {
		dao.updateCompany(map);
	}

	public List<CompanyDscResult> getCompanyDescs(String companyid) {
		return dao.getCompanyDescs(companyid);
	}

	public boolean isExistCompanyDesc(String userid, String content) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userid", userid);
		map.put("content", content);
		return null == dao.isExistCompanyDesc(map)?false:true;
	}

	public void addCompanyDesc(CompanyDescribe companyDescribe) {
		companyDescribe.preInsert();
		dao.addCompanyDesc(companyDescribe);
	}

	public void delCompanyDesc(String descid) {
		dao.delCompanyDesc(descid);
	}

	public List<CompanyResult> searchCompany(String searchtext) {
		return dao.searchCompany(searchtext);
	}
	
}
