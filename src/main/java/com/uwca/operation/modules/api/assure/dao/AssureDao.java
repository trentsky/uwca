package com.uwca.operation.modules.api.assure.dao;

import java.util.List;

import com.uwca.operation.common.persistence.CrudDao;
import com.uwca.operation.common.persistence.annotation.MyBatisDao;
import com.uwca.operation.modules.api.assure.entity.po.Assure;

@MyBatisDao
public interface AssureDao extends CrudDao<Assure> {

	List<Assure> getAssureCompanys(String mobile);

	List<Assure> getAssuredCompanys(String mobile);

	void delAssureCompany(String id);

	Assure getAssureCompanyByid(String id);

}
