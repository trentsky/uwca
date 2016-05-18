package com.uwca.operation.modules.api.assure.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uwca.operation.common.service.CrudService;
import com.uwca.operation.modules.api.assure.dao.AssureDao;
import com.uwca.operation.modules.api.assure.entity.po.Assure;
import com.uwca.operation.modules.api.company.dao.CompanyDao;
import com.uwca.operation.modules.api.company.entity.po.Company;

@Service
@Transactional
public class AssureService extends CrudService<AssureDao, Assure>{
	
	@Autowired
	AssureDao assureDao;
	
	@Autowired
	CompanyDao companyDao;

	public List<Assure> getAssureCompanys(String mobile) {
		return assureDao.getAssureCompanys(mobile);
	}

	public List<Assure> getAssuredCompanys(String mobile) {
		return assureDao.getAssuredCompanys(mobile);
	}

	public void delAssureCompany(String id) {
		assureDao.delAssureCompany(id);
	}

	public void addAssureCompany(Assure assure) {
		
		//发送邀请担保短信
		
		
		assure.preInsert();
		assureDao.insert(assure);
	}

	public String reviewedAssureCompany(String id) {
		Assure assure = dao.getAssureCompanyByid(id);
		if (null == assure) {
			return "担保信息不存在";
		}
		Company company = companyDao.getCompanyByUserid(assure.getAssureid());
		
		
		
		
		return null;
	}
}
