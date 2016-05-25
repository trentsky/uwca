package com.uwca.operation.modules.api.company.dao;

import java.util.List;
import java.util.Map;

import com.uwca.operation.common.persistence.CrudDao;
import com.uwca.operation.common.persistence.annotation.MyBatisDao;
import com.uwca.operation.modules.api.company.entity.po.Company;
import com.uwca.operation.modules.api.company.entity.po.CompanyDescribe;
import com.uwca.operation.modules.api.company.entity.vo.CompanyDscResult;
import com.uwca.operation.modules.api.company.entity.vo.CompanyInfo;

@MyBatisDao
public interface CompanyDao extends CrudDao<Company>{

	Company getCompanyByName(String companyName);

	Company isExistCompany(String companyName);

	Company getCompanyByMobile(String mobile);

	void updateCompany(Map<String, Object> map);

	List<CompanyDscResult> getCompanyDescs(String companyid);

	Object isExistCompanyDesc(Map<String, Object> map);

	void addCompanyDesc(CompanyDescribe companyDescribe);

	void delCompanyDesc(String descid);

	Company getCompanyInfoById(String id);

	List<CompanyInfo> searchCompany(String searchtext);

	Company getCompanyByUserid(String assureid);

	void updateCompanyDesc(Map<String, Object> map);

}
